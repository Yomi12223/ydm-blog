@echo off
echo Starting YDM.LABO Markdown Editor App...
echo.

REM アプリのインストールと起動
gradlew.bat installDebug
adb shell am start -n com.ydmlabo.markdowneditor/.presentation.ui.main.MainActivity

echo.
echo App started!
pause
