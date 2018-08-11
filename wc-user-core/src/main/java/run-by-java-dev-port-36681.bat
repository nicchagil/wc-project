@echo off
title APP-36681

set dateTimeStr=%date:~0,4%-%date:~5,2%-%date:~8,2%T%time:~0,2%:%time:~3,2%:%time:~6,2%
set base-folder=D:/git_workspace/wc-user
set wc-all-dubbo-common-folder=%base-folder%/wc-all-dubbo-common
set wc-user-core-folder=%base-folder%/wc-user-core

::安装依赖的项目（保证依赖的项目本地仓库安装的版本是最新的）
cd %wc-all-dubbo-common-folder%
call mvn clean install

::如果没有下载依赖的JAR，则下载
cd %wc-user-folder%
call mvn dependency:copy-dependencies

::设置CLASSPATH，执行此脚本请确认已执行本项目的“export-jar.bat”脚本下载所依赖的JAR
cd %wc-user-core-folder%/target/classes
set CLASSPATH=../dependency/*;./classes;%CLASSPATH%
java com.nicchagil.WcUserApplication --spring.profiles.active=dev-port-36681