# リファクタリングを行う

## 課題1 早期リターン

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