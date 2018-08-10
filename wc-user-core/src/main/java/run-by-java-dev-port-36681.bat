@echo off
title APP-36681

::如果没有下载依赖的JAR，则下载
if not exist ../dependency/ (
	echo ../dependency/ not exist
    cd ../../
    call mvn dependency:copy-dependencies
)

::设置CLASSPATH，执行此脚本请确认已执行本项目的“export-jar.bat”脚本下载所依赖的JAR
cd ./target/classes
set dateTimeStr=%date:~0,4%-%date:~5,2%-%date:~8,2%T%time:~0,2%:%time:~3,2%:%time:~6,2%
set CLASSPATH=../dependency/*;./classes;%CLASSPATH%
java com.nicchagil.WcUserApplication --spring.profiles.active=dev-port-36681