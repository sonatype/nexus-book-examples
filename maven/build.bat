@echo off
if "%BUILD_DEBUG_ECHO%" == "on" echo on

if "%OS%" == "Windows_NT" setlocal enableextensions

:begin

rem Detect base directory, this will return a value suffixed with \
set "dirname=%~dp0"
if "%dirname%" == "" set "dirname=.\"

rem Force evalguide maven to be used
set "M2_HOME=%dirname%apache-maven"

rem Avoid pulling in RC which might break things
set "MAVEN_SKIP_RC=true"

rem Have to append "." to basedir to avoid \" causing mvn to not read arguments
rem (not sure why this is needed though, but it is)
set "basedir=%dirname%."

:execute

rem Run maven with custom settings
call "%M2_HOME%\bin\mvn.bat" "-Devalguide.basedir=%basedir%" -s "%dirname%\settings\settings-localrepo.xml" %*

:end

if "%OS%" == "Windows_NT" endlocal

