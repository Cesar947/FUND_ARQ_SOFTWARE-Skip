@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  Skip_Backend startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and SKIP_BACKEND_OPTS to pass JVM options to this script.
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

set CLASSPATH=%APP_HOME%\lib\Skip_Backend-0.0.1-SNAPSHOT.jar;%APP_HOME%\lib\spring-boot-starter-websocket-2.1.5.RELEASE.jar;%APP_HOME%\lib\jjwt-0.9.1.jar;%APP_HOME%\lib\spring-websocket-5.1.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-data-jpa-2.1.5.RELEASE.jar;%APP_HOME%\lib\spring-data-jpa-2.1.8.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-web-2.1.5.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-5.1.7.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-security-2.1.5.RELEASE.jar;%APP_HOME%\lib\spring-security-config-5.1.5.RELEASE.jar;%APP_HOME%\lib\spring-security-test-5.1.5.RELEASE.jar;%APP_HOME%\lib\spring-security-web-5.1.5.RELEASE.jar;%APP_HOME%\lib\spring-security-core-5.1.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-aop-2.1.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-2.1.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-json-2.1.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-2.1.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.1.5.RELEASE.jar;%APP_HOME%\lib\spring-boot-2.1.5.RELEASE.jar;%APP_HOME%\lib\spring-context-5.1.14.RELEASE.jar;%APP_HOME%\lib\cucumber-spring-2.3.0.jar;%APP_HOME%\lib\mysql-socket-factory-connector-j-8-1.0.16.jar;%APP_HOME%\lib\javax.servlet-api-4.0.1.jar;%APP_HOME%\lib\mysql-connector-java-8.0.16.jar;%APP_HOME%\lib\spring-messaging-5.1.7.RELEASE.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.9.8.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.9.8.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.9.8.jar;%APP_HOME%\lib\jackson-databind-2.9.8.jar;%APP_HOME%\lib\spring-aop-5.1.7.RELEASE.jar;%APP_HOME%\lib\spring-web-5.1.7.RELEASE.jar;%APP_HOME%\lib\spring-data-commons-2.1.8.RELEASE.jar;%APP_HOME%\lib\spring-orm-5.1.7.RELEASE.jar;%APP_HOME%\lib\spring-jdbc-5.1.7.RELEASE.jar;%APP_HOME%\lib\spring-tx-5.1.7.RELEASE.jar;%APP_HOME%\lib\spring-beans-5.1.7.RELEASE.jar;%APP_HOME%\lib\spring-test-5.1.7.RELEASE.jar;%APP_HOME%\lib\spring-expression-5.1.7.RELEASE.jar;%APP_HOME%\lib\spring-core-5.1.7.RELEASE.jar;%APP_HOME%\lib\cucumber-java-2.3.0.jar;%APP_HOME%\lib\jdbc-socket-factory-core-1.0.16.jar;%APP_HOME%\lib\jnr-unixsocket-0.30.jar;%APP_HOME%\lib\asm-util-8.0.1.jar;%APP_HOME%\lib\javax.transaction-api-1.3.jar;%APP_HOME%\lib\jaxb-api-2.3.1.jar;%APP_HOME%\lib\hibernate-core-5.3.10.Final.jar;%APP_HOME%\lib\spring-aspects-5.1.7.RELEASE.jar;%APP_HOME%\lib\hibernate-validator-6.0.16.Final.jar;%APP_HOME%\lib\jackson-annotations-2.9.0.jar;%APP_HOME%\lib\google-auth-library-oauth2-http-0.20.0.jar;%APP_HOME%\lib\google-api-services-sqladmin-v1beta4-rev20190827-1.30.1.jar;%APP_HOME%\lib\google-api-client-1.30.1.jar;%APP_HOME%\lib\google-http-client-jackson2-1.34.0.jar;%APP_HOME%\lib\jackson-core-2.9.8.jar;%APP_HOME%\lib\spring-jcl-5.1.7.RELEASE.jar;%APP_HOME%\lib\cucumber-core-2.3.0.jar;%APP_HOME%\lib\jnr-enxio-0.26.jar;%APP_HOME%\lib\jnr-posix-3.0.55.jar;%APP_HOME%\lib\jnr-ffi-2.1.13.jar;%APP_HOME%\lib\jnr-constants-0.9.15.jar;%APP_HOME%\lib\asm-commons-7.1.jar;%APP_HOME%\lib\asm-analysis-8.0.1.jar;%APP_HOME%\lib\asm-tree-8.0.1.jar;%APP_HOME%\lib\asm-8.0.1.jar;%APP_HOME%\lib\aspectjweaver-1.9.4.jar;%APP_HOME%\lib\HikariCP-3.2.0.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.0.4.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\javax.persistence-api-2.2.jar;%APP_HOME%\lib\javassist-3.23.2-GA.jar;%APP_HOME%\lib\byte-buddy-1.9.12.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\jandex-2.0.5.Final.jar;%APP_HOME%\lib\classmate-1.4.0.jar;%APP_HOME%\lib\dom4j-2.1.1.jar;%APP_HOME%\lib\spring-boot-starter-logging-2.1.5.RELEASE.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\log4j-to-slf4j-2.11.2.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.26.jar;%APP_HOME%\lib\slf4j-api-1.7.26.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\snakeyaml-1.23.jar;%APP_HOME%\lib\validation-api-2.0.1.Final.jar;%APP_HOME%\lib\cucumber-html-0.2.6.jar;%APP_HOME%\lib\cucumber-jvm-deps-1.0.6.jar;%APP_HOME%\lib\gherkin-5.0.0.jar;%APP_HOME%\lib\tag-expressions-1.1.1.jar;%APP_HOME%\lib\auto-value-annotations-1.7.jar;%APP_HOME%\lib\google-oauth-client-1.30.1.jar;%APP_HOME%\lib\google-http-client-1.34.0.jar;%APP_HOME%\lib\opencensus-contrib-http-util-0.24.0.jar;%APP_HOME%\lib\guava-28.2-android.jar;%APP_HOME%\lib\jsr305-3.0.2.jar;%APP_HOME%\lib\google-auth-library-credentials-0.20.0.jar;%APP_HOME%\lib\jffi-1.2.23.jar;%APP_HOME%\lib\jffi-1.2.23-native.jar;%APP_HOME%\lib\jnr-a64asm-1.0.0.jar;%APP_HOME%\lib\jnr-x86asm-1.0.2.jar;%APP_HOME%\lib\httpclient-4.5.8.jar;%APP_HOME%\lib\httpcore-4.4.11.jar;%APP_HOME%\lib\j2objc-annotations-1.3.jar;%APP_HOME%\lib\opencensus-api-0.24.0.jar;%APP_HOME%\lib\failureaccess-1.0.1.jar;%APP_HOME%\lib\listenablefuture-9999.0-empty-to-avoid-conflict-with-guava.jar;%APP_HOME%\lib\checker-compat-qual-2.5.5.jar;%APP_HOME%\lib\error_prone_annotations-2.3.4.jar;%APP_HOME%\lib\logback-core-1.2.3.jar;%APP_HOME%\lib\log4j-api-2.11.2.jar;%APP_HOME%\lib\commons-codec-1.11.jar;%APP_HOME%\lib\grpc-context-1.22.1.jar

@rem Execute Skip_Backend
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %SKIP_BACKEND_OPTS%  -classpath "%CLASSPATH%" com.simplife.skip.Application %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable SKIP_BACKEND_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%SKIP_BACKEND_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
