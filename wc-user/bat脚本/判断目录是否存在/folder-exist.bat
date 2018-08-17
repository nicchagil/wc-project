@echo off
@title check-the-folder-is-exist

::特别注意：=前后不可用空格，否则会报语法异常
set folder=d:\2018\

if exist %folder% (
    echo %folder% exists
) else (
    echo %folder% not exists
)

if not exist d:\2019\ (
    echo d:\2019\ not exists
)

pause