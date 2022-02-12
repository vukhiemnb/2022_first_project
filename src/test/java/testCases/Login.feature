Feature: Test Login

  Scenario: Login with valid credential
  Given Open Browser
  And Access Login page
  When Enter valid credential on login page
  And Click button login on login page
  Then Login successfully and redirect to home page
