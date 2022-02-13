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
  Examples:
  |username|password|message_content|
  |""|""|"この項目は入力が必須です。"|
  |""|"abc"|"この項目は入力が必須です。"|
  |"incorrect"|"incorrect"|"ユーザーIDまたはパスワードが間違っています"|
