:: Name:     generate-pg.cmd
:: Purpose:  Generate DB on PostgreSQL Server

rem eg. generate-pg.cmd 192.168.0.55 5432 nmsdb nms_scada acos postgres postgres

@echo.
@echo off

SET HOST=%1
SET PORT=%2
SET DB=%3
SET USER=%4
SET PASSWORD=%5
SET MANAGEMENT_USER=%6
SET MANAGEMENT_PASSWORD=%7
SET DIR=%8

echo HOST: %HOST%
echo PORT: %PORT%
echo DB: %DB%
echo USER: %USER%
echo MANAGEMENT_USER: %MANAGEMENT_USER%
echo DIR: %DIR%
echo.



SET PGPASSWORD=%MANAGEMENT_PASSWORD%

echo closing active connections
"C:\Program Files\PostgreSQL\12\bin\psql.exe" -h %HOST% -p %PORT% -U %MANAGEMENT_USER% -c "SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activity WHERE pg_stat_activity.datname = '%DB%' AND pid <> pg_backend_pid();"

(    
    echo DROP DATABASE IF EXISTS "%DB%";
    echo DROP USER IF EXISTS "%USER%";
    echo CREATE DATABASE "%DB%" ENCODING = 'UTF8';
    echo CREATE USER "%USER%" WITH PASSWORD '%PASSWORD%' LOGIN;
    echo ALTER DATABASE "%DB%" OWNER TO "%USER%";
    echo GRANT ALL PRIVILEGES ON DATABASE "%DB%" TO "%USER%";
    echo ALTER ROLE "%USER%" SET search_path = 'demoappreact';
    echo \q
    
)  |  "C:\Program Files\PostgreSQL\12\bin\psql.exe" -h %HOST% -p %PORT% -U %MANAGEMENT_USER% -v ON_ERROR_STOP=1
if not %errorlevel%==0 goto error


SET PGPASSWORD=%PASSWORD%
"C:\Program Files\PostgreSQL\12\bin\psql.exe" -w -h %HOST% -d %DB% -U %USER% -v usr=%USER% -v ON_ERROR_STOP=1 -f %DIR%/schemas.sql
"C:\Program Files\PostgreSQL\12\bin\psql.exe" -w -h %HOST% -d %DB% -U %USER% -v usr=%USER% -v ON_ERROR_STOP=1 -f %DIR%/tables.sql
"C:\Program Files\PostgreSQL\12\bin\psql.exe" -w -h %HOST% -d %DB% -U %USER% -v usr=%USER% -v ON_ERROR_STOP=1 -f %DIR%/insert.sql
if not %errorlevel%==0 goto error


:finish
popd
echo.
echo [END Install database schema] finished successful.
echo -----------------------------------------------------
exit /b 0

:error
popd
echo.
echo ### ERROR [%errorlevel%] 
echo [Usecase: Install database] finished with errors.
echo -----------------------------------------------------
exit /b 2200