@echo off
title APP-MGR-36682

@rem set dateTimeStr=%date:~0,4%-%date:~5,2%-%date:~8,2%T%time:~0,2%:%time:~3,2%:%time:~6,2%

set dependent_project_a_directory=D:/git_workspace/wc-project/wc-all-dubbo-common
set current_project_directory=D:/git_workspace/wc-project/wc-user

cd %dependent_project_a_directory%
call mvn clean install

cd %current_project_directory%
call mvn dependency:copy-dependencies

cd %current_project_directory%/target/classes

set CLASSPATH=../dependency/*;./classes;%CLASSPATH%

set vm_args=
set vm_args=%vm_args% -Xms256m -Xmx256m -Xss512k
set vm_args=%vm_args% -Ddubbo.resolve.file=%current_project_directory%/direct-provider.properties

java %vm_args% com.nicchagil.WcUserApplication --spring.profiles.active=dev-port-36682