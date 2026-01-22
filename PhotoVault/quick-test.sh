#!/bin/bash

# PhotoVault - Quick Build & Test Helper
# Works with system Gradle installation

set -e

PROJECT_DIR="/workspaces/deep/PhotoVault"
cd "$PROJECT_DIR"

echo "========================================="
echo "  PhotoVault Build & Test Helper"
echo "========================================="
echo ""
echo "Your app is ready! Choose an option:"
echo ""
echo "1) Check environment & prerequisites"
echo "2) Build the app with Gradle"
echo "3) View project structure"
echo "4) Read testing guide"
echo "5) Open in Android Studio"
echo "6) Install on device (after build)"
echo "7) Run installed app"
echo "8) View app logs"
echo "9) Exit"
echo ""
read -p "Enter choice (1-9): " choice

case $choice in
    1)
        echo ""
        echo "Checking Android Development Environment..."
        echo ""
        echo "✓ Java:"
        java -version 2>&1 | head -1
        echo ""
        echo "✓ Gradle:"
        gradle --version | head -1
        echo ""
        echo "✓ Android SDK:"
        if [ -n "$ANDROID_SDK_ROOT" ]; then
            echo "  Found at: $ANDROID_SDK_ROOT"
        else
            echo "  ⚠ Not found. Set ANDROID_SDK_ROOT environment variable."
        fi
        echo ""
        echo "✓ ADB:"
        if command -v adb &> /dev/null; then
            echo "  $(adb version 2>&1 | head -1)"
            echo "  Connected devices:"
            adb devices | tail -n +2
        else
            echo "  ⚠ Not in PATH"
        fi
        ;;
    2)
        echo ""
        echo "Building PhotoVault..."
        echo ""
        cd "$PROJECT_DIR"
        gradle -p "$PROJECT_DIR" clean build -x test --no-daemon 2>&1 | tail -30
        echo ""
        echo "Build completed! Check app/build/outputs/apk/debug/"
        ;;
    3)
        echo ""
        echo "PhotoVault Project Structure:"
        echo ""
        tree -L 2 -I '.gradle|build|.git' "$PROJECT_DIR" 2>/dev/null || find "$PROJECT_DIR" -maxdepth 2 -not -path '*/\.*' -not -path '*/build/*' -type f | head -40
        ;;
    4)
        echo ""
        echo "Opening EASY_TESTING_GUIDE.md..."
        echo ""
        if [ -f "$PROJECT_DIR/EASY_TESTING_GUIDE.md" ]; then
            less "$PROJECT_DIR/EASY_TESTING_GUIDE.md"
        else
            echo "Guide not found!"
        fi
        ;;
    5)
        echo ""
        echo "Opening project in Android Studio..."
        if command -v android-studio &> /dev/null || command -v studio &> /dev/null; then
            android-studio "$PROJECT_DIR" 2>/dev/null &
            echo "Android Studio opening..."
        else
            echo "Android Studio not found in PATH"
            echo "Open manually: File → Open → $PROJECT_DIR"
        fi
        ;;
    6)
        echo ""
        echo "Installing on device..."
        if ! adb devices | grep -q "device$"; then
            echo "❌ No connected devices found!"
            echo "Connect a device or start emulator first."
        else
            cd "$PROJECT_DIR"
            gradle -p "$PROJECT_DIR" installDebug --no-daemon 2>&1 | tail -20
            echo "✓ Install complete!"
        fi
        ;;
    7)
        echo ""
        echo "Launching app..."
        if ! adb devices | grep -q "device$"; then
            echo "❌ No connected devices!"
        else
            adb shell am start -n com.photovault/.MainActivity
            echo "✓ App launched! Check your device."
        fi
        ;;
    8)
        echo ""
        echo "PhotoVault App Logs (Press Ctrl+C to stop)"
        echo ""
        adb logcat com.photovault:* -v brief
        ;;
    9)
        echo "Goodbye!"
        exit 0
        ;;
    *)
        echo "Invalid choice!"
        ;;
esac

echo ""
read -p "Press Enter to return to menu..."
exec "$0"
