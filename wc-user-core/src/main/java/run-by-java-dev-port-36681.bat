@echo off
title APP-36681

set dateTimeStr=%date:~0,4%-%date:~5,2%-%date:~8,2%T%time:~0,2%:%time:~3,2%:%time:~6,2%
set CLASSPATH=../dependency/*;./classes;%CLASSPATH%
java com.nicchagil.WcUserApplication --spring.profiles.active=dev-port-36681