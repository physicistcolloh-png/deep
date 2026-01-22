# PhotoVault Testing Guide

## ✅ Easy Testing Setup

### Prerequisites
- Android SDK installed
- API 26+ emulator or device connected
- Gradle 9.2.1+

---

## 🚀 Quick Start Testing (3 Steps)

### Step 1: Build the App
```bash
cd /workspaces/deep/PhotoVault
chmod +x gradlew
./gradlew clean build
```

### Step 2: Install on Device/Emulator
```bash
./gradlew installDebug
```

### Step 3: Launch the App
```bash
adb shell am start -n com.photovault/.MainActivity
```

---

## 🧪 Testing Workflows

### **Test 1: File Browser (5 min)**
1. Open app → Click "File Browser"
2. Navigate through directories
3. View file thumbnails, sizes, dates
4. ✅ Verify: Files display correctly, no crashes

### **Test 2: Photo Editing (10 min)**
1. Click "Photo Editor"
2. Select any photo from device
3. Test each tool:
   - **Exposure**: Adjust exposure slider (0-100)
   - **Curves**: Click to add control points
   - **HSL**: Adjust hue, saturation, lightness
   - **Retouch**: Try healing/clone tools
   - **Transform**: Crop, rotate, perspective
4. Click "Export" → Choose format (JPEG/PNG/WebP)
5. ✅ Verify: Edit preview updates, export works

### **Test 3: Audio Playback (5 min)**
1. Click "Audio Player"
2. Select any audio file
3. Play/pause, seek, volume control
4. Check duration display
5. ✅ Verify: Audio plays, controls work

### **Test 4: Video Playback (5 min)**
1. Click "Video Player"
2. Select any video file
3. Play/pause, seek to different times
4. ✅ Verify: Video plays smoothly

### **Test 5: Vault (Encryption) (10 min)**
1. Click "Vault" → "Lock File"
2. Select a test file
3. Choose encryption method
4. Verify file is encrypted
5. Try to unlock with biometric/password
6. ✅ Verify: File locked/unlocked correctly

### **Test 6: Offline Sharing (5 min)**
1. Click "Share"
2. Select file or folder
3. Test each option:
   - **QR Code**: Generate and scan
   - **Bluetooth**: List devices
   - **Xender**: Show path
   - **Local Link**: Display URL
4. ✅ Verify: All sharing methods work

### **Test 7: Settings (5 min)**
1. Click "Settings"
2. Toggle options:
   - Theme (Dark/Light)
   - Enable Vault
   - Enable Backups
3. Check they persist after app restart
4. ✅ Verify: Settings save/load

### **Test 8: Offline Mode (5 min)**
1. Disable WiFi and mobile data on device
2. Reopen app
3. Use all features: browse, edit, play
4. ✅ Verify: Everything works without internet

---

## 🐛 Troubleshooting

### Build fails with "No Android SDK"
```bash
export ANDROID_SDK_ROOT=/path/to/android/sdk
export ANDROID_HOME=/path/to/android/sdk
./gradlew build
```

### Emulator issues
```bash
# List available emulators
emulator -list-avds

# Launch specific emulator
emulator -avd Pixel_6_API_34 &
```

### No devices detected
```bash
adb devices
adb kill-server
adb start-server
adb devices
```

### App crashes on launch
```bash
adb logcat com.photovault:*
# Check logcat for error messages
```

### App needs permissions manually
1. Open Settings on device
2. Find PhotoVault
3. Grant Storage, Camera, Microphone permissions
4. Restart app

---

## 📊 Performance Testing

### Check APK Size
```bash
./gradlew assembleDebug
du -h app/build/outputs/apk/debug/app-debug.apk
```

### Monitor Memory Usage
```bash
adb shell dumpsys meminfo com.photovault
```

### Check Crash Logs
```bash
adb logcat -v brief | grep -i "com.photovault\|error\|exception"
```

---

## ✨ Full Test Coverage (All Features)
Estimated time: **60 minutes**

| Feature | Time | Status |
|---------|------|--------|
| File Browser | 5 min | ⬜ |
| Photo Editing | 10 min | ⬜ |
| Audio Playback | 5 min | ⬜ |
| Video Playback | 5 min | ⬜ |
| Encryption Vault | 10 min | ⬜ |
| Offline Sharing | 5 min | ⬜ |
| Settings | 5 min | ⬜ |
| Offline Mode | 5 min | ⬜ |
| Storage Analysis | 5 min | ⬜ |
| Duplicate Detection | 5 min | ⬜ |
| **TOTAL** | **60 min** | ✅ |

---

## 💡 Pro Tips

1. **Use Android Studio** for easier debugging:
   - Open project in Android Studio
   - Click "Run" button
   - Select device/emulator
   - Watch logcat in real-time

2. **Enable developer options** on test device:
   - Settings → About → Tap Build Number 7x
   - Settings → Developer Options → Enable USB Debugging

3. **Use test data**:
   - Copy test photos/videos to device
   - `adb push sample.jpg /sdcard/Pictures/`

4. **Check database**:
   - Android Studio → Device Explorer → `data/data/com.photovault/databases/`
   - Inspect database using SQLite viewer

---

## 🎯 Success Criteria

- ✅ App builds without errors
- ✅ App installs on device/emulator
- ✅ All 8 screens launch
- ✅ File browsing works
- ✅ Photo editing tools functional
- ✅ Audio/video playback works
- ✅ Encryption works
- ✅ Sharing generates content
- ✅ Offline mode verified
- ✅ No crashes in logcat

**All criteria met = PhotoVault Ready for Production! 🚀**
