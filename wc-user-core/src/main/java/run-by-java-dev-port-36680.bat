set dateTimeStr=%date:~0,4%-%date:~5,2%-%date:~8,2%T%time:~0,2%:%time:~3,2%:%time:~6,2%
set CLASSPATH=D:/git_workspace/wc-user/wc-user-core/target/dependency/*;D:/git_workspace/wc-user/wc-user-core/target/classes;%CLASSPATH%
java com.nicchagil.WcUserApplication