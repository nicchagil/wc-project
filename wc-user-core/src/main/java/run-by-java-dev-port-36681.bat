@echo off
title APP-36681

::设置可读的时间戳
::set dateTimeStr=%date:~0,4%-%date:~5,2%-%date:~8,2%T%time:~0,2%:%time:~3,2%:%time:~6,2%

::依赖的项目的根目录
set dependent_project_a_directory=D:/git_workspace/wc-user/wc-all-dubbo-common
::当前项目的根目录
set current_project_directory=D:/git_workspace/wc-user/wc-user-core

::安装依赖的项目（保证依赖的项目本地仓库安装的版本是最新的）
cd %dependent_project_a_directory%
call mvn clean install

::如果没有下载依赖的JAR，则下载
cd %current_project_directory%
call mvn dependency:copy-dependencies

::设置CLASSPATH，执行此脚本请确认已执行本项目的“export-jar.bat”脚本下载所依赖的JAR
cd %current_project_directory%/target/classes
set CLASSPATH=../dependency/*;./classes;%CLASSPATH%
java com.nicchagil.WcUserApplication --spring.profiles.active=dev-port-36681