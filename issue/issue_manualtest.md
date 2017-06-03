# 手動テスト

## 課題1 本を登録する機能のバリデーションテスト

本を登録する機能については以下の観点を確認できれば品質保証ができていると考えられる

* 正常系が正しくシステムに登録されること
* 以下の場合は、画面上エラーを表示し登録ができないこと
  - タイトルを空白にした場合
  - 分類を空白にした場合
  - 分類を10文字より大きくした場合

よって以下のテストケースを実施することとする


| No | ケース      | タイトル        | 分類     |
|:--:|:------------|:----------------|:---------|
| 1 | 正常系       | テストタイトル1 | プログラミング技術本   |
| 2 | タイトル空白でエラー |                 | 技術本   |
| 3 | 分類が空白でエラー   | テストタイトル3 |          |
| 4 | 分類11文字でエラー   | テストタイトル4 | プログラミング技術本4 |


テストを実施し、バグがないかを確認してください。


## 課題2 レンタル・返却機能の機能テスト

レンタル・返却については以下の観点を確認すれば品質を満たしていると考えられる。

* レンタルされていない本がレンタルできること
* 技術本については2週間レンタルできること
* 技術本以外については1週間レンタルできること
* レンタルされた本が返却できること
* 返却された本が再度レンタルできること

テストケースを考えて、テストを実施してください

## 課題3 バグ修正

ここまでに発見したバグを整理し、修正方針をまとめてください。
可能であれば修正してもOK