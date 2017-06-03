# 脆弱性をついた攻撃

## 課題1 SQLインジェクション

### アプリ準備

起動パラメータに ```-Dspring.profiles.active=bug``` をつけて実行する

### 本を検索する

分類 : ```技術本' or type is not '```  →　全件取得できてしまう

### 原因・影響を考える

SQLインジェクションとは何か、どういうことが起こりうるか整理してください

### 参考

これも面白そう
https://github.com/yo1000/insecure-user-admin