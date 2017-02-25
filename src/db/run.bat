@echo off
>nul chcp 1252
psql -U postgres -f 0_runAllScripts.sql
pause