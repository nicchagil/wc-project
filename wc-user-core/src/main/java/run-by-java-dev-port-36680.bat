set dateTimeStr=%date:~0,4%-%date:~5,2%-%date:~8,2%T%time:~0,2%:%time:~3,2%:%time:~6,2%

# 设置CLASSPATH，执行此脚本请确认已执行本项目的“export-jar.bat”脚本下载所依赖的JAR
set CLASSPATH=../dependency/*;./classes;%CLASSPATH%
java com.nicchagil.WcUserApplication