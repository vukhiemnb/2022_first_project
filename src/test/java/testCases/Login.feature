Feature: Test Login

Background:
  Given Open Browser
  And Access Login page


  Scenario: Login with valid credential
  When Enter valid credential on login page
  And Click button login on login page
  Then Login successfully
  And redirect to home page
  
  Scenario Outline: Login with invalid credential
  When Enter username as <username> and password as <password>
  And Click button login on login page
  Then Error message with content <message_content> is displayed
  And Take screenshot with name as <evidence_name>
  Examples:
  |username|password|message_content|evidence_name|
  |""|""|"この項目は入力が必須です。"|"login with null input"|
  |""|"abc"|"この項目は入力が必須です。"|"login with blank username"|
  |"incorrect"|"incorrect"|"ユーザーIDまたはパスワードが間違っています"|"login with invalid credential"|
