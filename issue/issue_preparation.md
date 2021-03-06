# アプリケーションを起動する

Windowsを想定して記載しています。

## 課題1 アプリケーションの起動

### ソースの取得、eclipseへのインポート

```
git clone https://github.com/nyasba/newbie-study.git
cd newbie-study
git checkout -b <名前など別ブランチ>
.\mvnw.cmd eclipse:eclipse
```
masterブランチに間違ってコミット・プッシュしないように自分のブランチで作業する
その後、eclipseを起動してプロジェクトをインポートする


### Webアプリ起動方法(1)コマンドラインから実行する

```
.\mvnw.cmd clean spring-boot:run
```

### Webアプリ起動方法(2)eclipseから起動する

これを実行する
src/main/java/com.newbie.testsample.TestsampleApplication

この際、profileが「dev」になることを確認する
```
2017-06-05 22:31:35.999  INFO 12956 --- [           main] c.n.testsample.TestsampleApplication     : The following profiles are active: dev
```

「dev」になっていない場合、eclipseのプロジェクトプロパティ -> Javaのビルドパス -> ソース(src/main/resources)でapplication.ymlが除外設定されていないかを確認する。
除外されてればその設定を削除する。

### ブラウザで確認する

本の管理システムという表示が確認できれば成功
[http://localhost:8080/books](http://localhost:8080/books)

## 課題2 アプリケーションの操作

以下の手順に沿って操作ができることを確認してください

1. 本の作成を行う
   (1)  タイトル:Cloud First Architecture 設計ガイド, 分類: 技術
   (2)  タイトル:日経ビジネス, 分類: ビジネス
1. 「レンタル」ボタンを押して「Cloud First Architecture 設計ガイド」を返却予定を1週間後にしてレンタルする
1. 「返却」ボタンを押して本を返却する

