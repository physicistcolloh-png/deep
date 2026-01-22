#!/bin/bash

# PhotoVault Build & Test Helper Script
# Makes building and testing the app super easy

set -e  # Exit on any error

PROJECT_DIR="/workspaces/deep/PhotoVault"
cd "$PROJECT_DIR"

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

print_header() {
    echo -e "${BLUE}========================================${NC}"
    echo -e "${BLUE}$1${NC}"
    echo -e "${BLUE}========================================${NC}"
}

print_success() {
    echo -e "${GREEN}✅ $1${NC}"
}

print_error() {
    echo -e "${RED}❌ $1${NC}"
}

print_info() {
    echo -e "${YELLOW}ℹ️  $1${NC}"
}

# Main menu
show_menu() {
    echo
    print_header "PhotoVault Build & Test Helper"
    echo "1) Clean & Build"
    echo "2) Build & Install"
    echo "3) Install on Device"
    echo "4) Run App"
    echo "5) View Logs"
    echo "6) Full Test (Build → Install → Run)"
    echo "7) Quick Build (No Clean)"
    echo "8) Check Emulator/Device"
    echo "9) Exit"
    echo
}

build_app() {
    print_header "Building PhotoVault..."
    
    if [ ! -f "gradlew" ]; then
        print_info "Creating gradlew wrapper..."
        gradle wrapper --gradle-version=9.2.1 > /dev/null 2>&1
        chmod +x gradlew
    fi
    
    print_info "Starting build..."
    ./gradlew clean build --info 2>&1 | tail -50
    
    if [ $? -eq 0 ]; then
        print_success "Build completed successfully!"
        echo "APK location: app/build/outputs/apk/debug/app-debug.apk"
    else
        print_error "Build failed!"
        return 1
    fi
}

install_app() {
    print_header "Installing PhotoVault..."
    
    print_info "Checking for connected devices..."
    adb devices | grep -v "List of attached" | grep -v "^$"
    
    if [ $(adb devices | grep -c "device$") -eq 0 ]; then
        print_error "No devices/emulators detected!"
        print_info "Make sure your device is connected or emulator is running"
        return 1
    fi
    
    print_info "Installing app..."
    ./gradlew installDebug 2>&1 | tail -20
    
    if [ $? -eq 0 ]; then
        print_success "App installed successfully!"
    else
        print_error "Installation failed!"
        return 1
    fi
}

run_app() {
    print_header "Launching PhotoVault..."
    
    print_info "Starting main activity..."
    adb shell am start -n com.photovault/.MainActivity
    
    sleep 2
    print_success "App launched! Check your device/emulator"
}

view_logs() {
    print_header "PhotoVault Logs (Press Ctrl+C to stop)"
    print_info "Filtering for com.photovault and errors..."
    echo
    
    adb logcat -v brief com.photovault:* | grep -E "D/|E/|W/|I/"
}

check_devices() {
    print_header "Connected Devices/Emulators"
    echo
    
    devices=$(adb devices | grep -v "List of attached" | grep -v "^$")
    
    if [ -z "$devices" ]; then
        print_error "No devices detected"
        echo
        print_info "To start emulator:"
        echo "  emulator -avd Pixel_6_API_34"
        return 1
    fi
    
    echo "$devices"
    echo
    print_success "Ready to install!"
}

full_test() {
    print_header "Full Test: Build → Install → Run"
    
    build_app || return 1
    echo
    install_app || return 1
    echo
    run_app || return 1
    echo
    print_header "Next Steps"
    print_info "1. View logs: run this script and select '5) View Logs'"
    print_info "2. Check TEST_SETUP.md for detailed testing procedures"
    print_info "3. Test all features on your device"
}

quick_build() {
    print_header "Quick Build (No Clean)"
    
    if [ ! -f "gradlew" ]; then
        print_info "Creating gradlew wrapper..."
        gradle wrapper --gradle-version=9.2.1 > /dev/null 2>&1
        chmod +x gradlew
    fi
    
    print_info "Building..."
    ./gradlew build --info 2>&1 | tail -30
    
    if [ $? -eq 0 ]; then
        print_success "Build successful!"
    else
        print_error "Build failed!"
        return 1
    fi
}

# Main loop
while true; do
    show_menu
    read -p "Select option (1-9): " choice
    
    case $choice in
        1) build_app ;;
        2) build_app && echo && install_app ;;
        3) install_app ;;
        4) run_app ;;
        5) view_logs ;;
        6) full_test ;;
        7) quick_build ;;
        8) check_devices ;;
        9) print_info "Goodbye!"; exit 0 ;;
        *) print_error "Invalid option!" ;;
    esac
    
    echo
    read -p "Press Enter to continue..."
done
