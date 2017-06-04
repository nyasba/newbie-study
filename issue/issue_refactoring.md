# リファクタリングを行う

## 課題1 マジックナンバーの削除

マジックナンバーとは、

> 何らかの識別子もしくは定数として用いられる、プログラムのソースコード中に書かれた具体的な数値である。
そのプログラムを書いた時点では製作者は数値の意図を把握しているが、
他のプログラマーまたは製作者本人がマジックナンバーの意図を忘れたときに閲覧すると
「この数字の意味はわからないが、とにかくプログラムは正しく動く。まるで魔法の数字だ」
という皮肉を含む。

### サンプル
```com.newbie.testsample.domain.BookEntityjava
    public int canRental(LocalDate today, LocalDate returnDate) {
        if (this.type.equals("技術本") && !this.isFutureAndInRange(today, returnDate, 14L)) {
            return 1;
        }
        if (!this.type.equals("技術本") && !this.isFutureAndInRange(today, returnDate, 7L)) {
            return 2;
        }
        return 0;
    }
```

このサンプルでは0ならレンタル可能という例になっているが、
ソースを解析しないと0の意味が分からなくなっている

### テクニック

本来のマジックナンバーでは、
1:あり、0:なしのような数値に意味を与えたときにプログラム上では1や0で扱うのではなく、
YES,NOのような定数を使って意味が明確にわかるようにするためのものである。

```private static final String RESULT = "1"; ```のように単なる定数でも問題ないが、
ENUMを活用すると下記のように整理できる

```com.newbie.testsample.domain.BookEntity.java
    public BookRentalCheckStatus canRental(LocalDate today, LocalDate returnDate) {
        if (this.type.equals("技術本") && !this.isFutureAndInRange(today, returnDate, 14L)) {
            return BookRentalCheckStatus.OUT_OF_RANGE_TECH;
        }
        if (!this.type.equals("技術本") && !this.isFutureAndInRange(today, returnDate, 7L)) {
            return BookRentalCheckStatus.OUT_OF_RANGE_OTHERS;
        }
        return BookRentalCheckStatus.OK;
    }
```

```com.newbie.testsample.domain.BookRentalCheckStatus.java
public enum BookRentalCheckStatus {
    OK(""),
    OUT_OF_RANGE_TECH("返却予定日を2週間以内にしてください"),
    OUT_OF_RANGE_OTHERS("返却予定日を1週間以内にしてください");
    
    private String message;
    
    BookRentalCheckStatus(String message) {
        this.message = message;
    }
    
    public boolean isError() {
        return this != OK;
    }
    
    public String getMessage() {
        return message;
    }
}
```

既にコードはリファクタリング後となっているため、実践する内容はないので、
この課題は理解するだけ。申し訳ない。


## 課題2 メソッドの抽出

「重複を避けること(Don't repeat yourself)」というDRY原則はプログラミングにおいて非常に重要な概念である。

同じ処理を複数個所書いていればメソッドの抽出により、共通化することを検討する。
ただし、同じ処理だからといってその前提や背景などが違えば共通化しないほうがよいこともある。
重要なのは抽出したメソッドに名前を正しく付けられるかである。


### サンプル

```com.newbie.testsample.domain.BookEntity.java
    public BookRentalCheckStatus canRental(LocalDate today, LocalDate returnDate) {
        if (this.type.equals("技術本")) {
            LocalDate rentalLimitDate = today.plusDays(14L);
            if ((returnDate.isEqual(today) || returnDate.isAfter(today))
                    && (returnDate.isEqual(rentalLimitDate) || returnDate.isBefore(rentalLimitDate))){
                return BookRentalCheckStatus.OUT_OF_RANGE_TECH;
            }
            else {
                return BookRentalCheckStatus.OK;
            }
        }
        else {
            LocalDate rentalLimitDate = today.plusDays(7L);
            if ((returnDate.isEqual(today) || returnDate.isAfter(today))
                    && (returnDate.isEqual(rentalLimitDate) || returnDate.isBefore(rentalLimitDate))){
                return BookRentalCheckStatus.OUT_OF_RANGE_OTHERS;
            }
            else {
                return BookRentalCheckStatus.OK;
            }
        }
    }
 ```

### テクニック
日付の判定部分をメソッド抽出した例

```com.newbie.testsample.domain.BookEntity.java
    public BookRentalCheckStatus canRental(LocalDate today, LocalDate returnDate) {
        if (this.type.equals("技術本") && !this.isFutureAndInRange(today, returnDate, 14L)) {
            return BookRentalCheckStatus.OUT_OF_RANGE_TECH;
        }
        if (!this.type.equals("技術本") && !this.isFutureAndInRange(today, returnDate, 7L)) {
            return BookRentalCheckStatus.OUT_OF_RANGE_OTHERS;
        }
        return BookRentalCheckStatus.OK;
    }
    
    private boolean isFutureAndInRange(LocalDate today, LocalDate returnDate, long rentalDays) {
        LocalDate rentalLimitDate = today.plusDays(rentalDays);
        return (returnDate.isEqual(today) || returnDate.isAfter(today))
                && (returnDate.isEqual(rentalLimitDate) || returnDate.isBefore(rentalLimitDate));
    }
```

### 実践

このコードをリファクタリングしてみてください。
ユニットテストの課題の中で本メソッドに対するテストコードを作成しているはずなので、
リファクタリング前後にテストコードが全て通るようになっているかは最低限意識してください。



## 課題3 早期リターン

### サンプル

```com.newbie.testsample.service.BookRentalService.java

    public void _return(Integer rentalId) {
        BookRentalEntity bookRentalEntity = bookRentalRepository.findOne(rentalId);
        
        if (bookRentalEntity != null) {
            bookRentalEntity.setReturnedDate(LocalDate.now());
            bookRentalRepository.save(bookRentalEntity);
        } else {
            logger.info("rental entity not found");
        }
    }
```

idがDBに存在しなかった場合はBookRentalEntityがnullとなる
システム的には考慮が必要だが、コードの可読性を下げてしまう

### 考え方

> 条件記述は次の２つの形式に分類されます。
  １つは、条件判定のいずれも正常条件であって、どちらのルートを取るかを判定するものです。
  もう１つは、条件判定の結果の一方が正常条件で、それ以外は特殊条件である場合です。
>  
>  もし、両方が正常処理ならば、ifとelseを持った条件記述を使うべきです。
  特殊条件では、条件をチェックしてその結果が真のときにはリターンします。
>  
>  if-then-else構造が使われるときは、if部にもelse部にも同じウェイトが置かれています。
  これが、プログラムの読み手に対して両方とも等しく起こり得ること、等しく重要であることを伝えます。

### テクニック

リファクタ前
```
if ( entity != null ) {
    entity.method("execute");
} else {
    logger.debug("something wrong");
}
```

リファクタ後
```
if ( entity == null ) {
    logger.debug("something wrong");
    return;
}
entity.method("execute");
```

### 実践

com.newbie.testsample.service.BookRentalService.java をリファクタリングしてみましょう