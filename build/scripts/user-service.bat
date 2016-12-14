@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  user-service startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and USER_SERVICE_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%/app;%APP_HOME%\lib\user-service-1.0-SNAPSHOT.jar;%APP_HOME%\lib\ratpack-core-1.4.3.jar;%APP_HOME%\lib\ratpack-guice-1.4.3.jar;%APP_HOME%\lib\ratpack-rx-1.4.3.jar;%APP_HOME%\lib\neo4j-ogm-bolt-driver-2.0.4.jar;%APP_HOME%\lib\neo4j-ogm-core-2.0.4.jar;%APP_HOME%\lib\adbanner-entites-1.0-SNAPSHOT.jar;%APP_HOME%\lib\service-common-1.0-SNAPSHOT.jar;%APP_HOME%\lib\authorazation-service-1.0-SNAPSHOT.jar;%APP_HOME%\lib\sundial-2.0.1.jar;%APP_HOME%\lib\commons-validator-1.5.1.jar;%APP_HOME%\lib\netty-codec-http-4.1.5.Final.jar;%APP_HOME%\lib\netty-handler-4.1.5.Final.jar;%APP_HOME%\lib\netty-transport-native-epoll-4.1.5.Final-linux-x86_64.jar;%APP_HOME%\lib\guava-19.0.jar;%APP_HOME%\lib\slf4j-api-1.7.21.jar;%APP_HOME%\lib\reactive-streams-1.0.0.jar;%APP_HOME%\lib\caffeine-2.3.1.jar;%APP_HOME%\lib\javassist-3.19.0-GA.jar;%APP_HOME%\lib\jackson-databind-2.7.5.jar;%APP_HOME%\lib\jackson-dataformat-yaml-2.7.5.jar;%APP_HOME%\lib\jackson-datatype-guava-2.7.5.jar;%APP_HOME%\lib\snakeyaml-1.15.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.7.5.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.7.5.jar;%APP_HOME%\lib\guice-4.1.0.jar;%APP_HOME%\lib\guice-multibindings-4.1.0.jar;%APP_HOME%\lib\rxjava-1.1.2.jar;%APP_HOME%\lib\rxjava-reactive-streams-1.0.1.jar;%APP_HOME%\lib\neo4j-ogm-api-2.0.4.jar;%APP_HOME%\lib\neo4j-2.3.6.jar;%APP_HOME%\lib\neo4j-java-driver-1.0.1.jar;%APP_HOME%\lib\commons-io-2.4.jar;%APP_HOME%\lib\neo4j-ogm-compiler-2.0.4.jar;%APP_HOME%\lib\commons-lang3-3.4.jar;%APP_HOME%\lib\commons-collections4-4.1.jar;%APP_HOME%\lib\log4j-slf4j-impl-2.0.2.jar;%APP_HOME%\lib\log4j-api-2.0.2.jar;%APP_HOME%\lib\log4j-core-2.0.2.jar;%APP_HOME%\lib\commons-beanutils-1.9.2.jar;%APP_HOME%\lib\commons-digester-1.8.1.jar;%APP_HOME%\lib\commons-logging-1.2.jar;%APP_HOME%\lib\commons-collections-3.2.2.jar;%APP_HOME%\lib\netty-codec-4.1.5.Final.jar;%APP_HOME%\lib\netty-buffer-4.1.5.Final.jar;%APP_HOME%\lib\netty-transport-4.1.5.Final.jar;%APP_HOME%\lib\netty-common-4.1.5.Final.jar;%APP_HOME%\lib\jackson-annotations-2.7.0.jar;%APP_HOME%\lib\jackson-core-2.7.5.jar;%APP_HOME%\lib\commons-codec-1.10.jar;%APP_HOME%\lib\neo4j-kernel-2.3.6.jar;%APP_HOME%\lib\neo4j-lucene-index-2.3.6.jar;%APP_HOME%\lib\neo4j-graph-algo-2.3.6.jar;%APP_HOME%\lib\neo4j-udc-2.3.6.jar;%APP_HOME%\lib\neo4j-graph-matching-2.3.6.jar;%APP_HOME%\lib\neo4j-cypher-2.3.6.jar;%APP_HOME%\lib\neo4j-jmx-2.3.6.jar;%APP_HOME%\lib\neo4j-consistency-check-2.3.6.jar;%APP_HOME%\lib\netty-resolver-4.1.5.Final.jar;%APP_HOME%\lib\neo4j-primitive-collections-2.3.6.jar;%APP_HOME%\lib\neo4j-io-2.3.6.jar;%APP_HOME%\lib\neo4j-csv-2.3.6.jar;%APP_HOME%\lib\neo4j-logging-2.3.6.jar;%APP_HOME%\lib\lucene-core-3.6.2.jar;%APP_HOME%\lib\scala-library-2.11.7.jar;%APP_HOME%\lib\scala-reflect-2.11.7.jar;%APP_HOME%\lib\scala-parser-combinators_2.11-1.0.4.jar;%APP_HOME%\lib\neo4j-codegen-2.3.6.jar;%APP_HOME%\lib\neo4j-cypher-compiler-1.9_2.11-2.0.5.jar;%APP_HOME%\lib\neo4j-cypher-compiler-2.2_2.11-2.2.10.jar;%APP_HOME%\lib\neo4j-cypher-compiler-2.3-2.3.6.jar;%APP_HOME%\lib\parboiled-scala_2.11-1.1.7.jar;%APP_HOME%\lib\opencsv-2.3.jar;%APP_HOME%\lib\concurrentlinkedhashmap-lru-1.4.2.jar;%APP_HOME%\lib\neo4j-consistency-check-legacy-2.3.6.jar;%APP_HOME%\lib\neo4j-unsafe-2.3.6.jar;%APP_HOME%\lib\neo4j-function-2.3.6.jar;%APP_HOME%\lib\asm-5.0.2.jar;%APP_HOME%\lib\neo4j-cypher-frontend-2.3-2.3.6.jar;%APP_HOME%\lib\parboiled-core-1.1.7.jar;%APP_HOME%\lib\javax.inject-1.jar;%APP_HOME%\lib\aopalliance-1.0.jar;%APP_HOME%\lib\gson-2.8.0.jar
cd "%APP_HOME%/app"

@rem Execute user-service
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %USER_SERVICE_OPTS%  -classpath "%CLASSPATH%" StartUserService %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable USER_SERVICE_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%USER_SERVICE_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
