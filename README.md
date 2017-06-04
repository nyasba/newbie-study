# newbie-study
新卒研修用の教材を考えてみる

### 事前準備

* JDK1.8のインストール
* eclipseのインストール(IntellijでもOK)


### 課題用サンプルアプリの仕様

* 本の管理用アプリケーション
* 管理対象の本の一覧が確認することができる
  - タイトルと分類
  - レンタルされている本は返却予定日も確認することができる
* レンタルされていない本は返却予定日を決めてレンタルすることができる
  - 技術本は2週間レンタル可能
  - 技術本以外は1週間レンタル可能
* レンタルした本の返却を登録することができる
  - 返却予定日を過ぎたとしても特に何もしない
* 新規に管理対象となった本を登録することができる
  - 既に登録されている本であっても登録することはできるものとする（レンタルを管理するため）
  - 新規に登録された本はレンタルされていない状態となる
  - 分類は10文字までとする
* 誰が借りているかは管理しなくてもよい

### 課題

1. [アプリ起動](issue/issue_preparation.md) : アプリを立ち上げて正常系の動作確認を行う・仕様を理解する
1. [手動テスト](issue/issue_manualtest.md) : アプリの手動テストを実施してバグを見つける
1. [DB操作](issue/issue_dbconnect.md) : DBを直接操作して、データの状況を把握する
1. [脆弱性をついた攻撃](issue/issue_attack.md) : SQLインジェクション
1. [ユニットテスト](issue/issue_unittest.md) : ユニットテストを作成してバグを改修する
1. [リファクタリング](issue/issue_refactoring.md) : 汚いコードをリファクタリングする


以降の課題は未作成  
7. [機能追加]() : 新しい機能を追加する
1. [E2Eテスト]() : Selenideを活用してブラウザを操作してテストを自動化する
