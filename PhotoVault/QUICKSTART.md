# PhotoVault - Quick Start Guide

## ⚡ 5-Minute Setup

### Prerequisites
- Android Studio Giraffe or newer
- Android SDK 34
- Java 17+
- Gradle 8.1.0+

### Step 1: Build the Project
```bash
cd PhotoVault
./gradlew build
```

### Step 2: Run on Device/Emulator
```bash
./gradlew installDebug
```

### Step 3: Launch the App
```bash
adb shell am start -n com.photovault/.MainActivity
```

## 🎯 Main Features at a Glance

| Feature | Location | How to Use |
|---------|----------|-----------|
| **Browse Files** | Home → Browse Files | Navigate device storage, manage files |
| **Edit Photos** | Home → Photo (from browser) | Use advanced editing tools |
| **Play Audio** | Home → Audio Player | Play and organize audio files |
| **Play Videos** | Home → Video Player | Watch videos offline |
| **Security Vault** | Home → Security Vault | Encrypt and protect files |
| **Share Offline** | Home → Share Files | QR, Bluetooth, Xender, Links |
| **Settings** | Home → Settings | Configure app behavior |

## 📁 File Organization

```
PhotoVault/
├── Build & Run Files
│   ├── settings.gradle
│   ├── build.gradle
│   └── app/build.gradle
│
├── Documentation (Start here!)
│   ├── README.md                 ← Main guide
│   ├── ARCHITECTURE.md           ← Architecture details
│   ├── FEATURES.md              ← Feature guide
│   ├── DEVELOPMENT.md           ← Dev setup
│   ├── PROJECT_OVERVIEW.md      ← Project stats
│   └── COMPLETION_SUMMARY.md    ← This project's summary
│
├── App Source Code
│   └── app/src/main/java/com/photovault/
│       ├── MainActivity.kt       ← Start here (code)
│       ├── PhotoVaultApp.kt
│       ├── core/               ← Core infrastructure
│       ├── data/               ← Database & repos
│       ├── domain/             ← Business logic
│       ├── ui/                 ← Screens & UI
│       ├── editor/             ← Photo editing
│       ├── storage/            ← File storage
│       ├── security/           ← Encryption
│       ├── automation/         ← Rules engine
│       ├── database/           ← Room DB
│       ├── image_engine/       ← Image filters
│       └── utils/              ← Utilities
│
└── Resources
    └── app/src/main/res/values/
        ├── strings.xml
        ├── colors.xml
        └── themes.xml
```

## 🔧 Common Tasks

### Add a New Feature
1. Create Use Case in `domain/usecase/`
2. Create or update Repository in `data/repository/`
3. Create Screen in `ui/screens/`
4. Update Navigation in `ui/navigation/NavGraph.kt`

### Modify Photo Editor Tools
- Edit tools in `editor/tools/`
- Filters in `image_engine/filters/`
- Update `EditPipeline.kt` for new effects

### Add Automation Rules
- Define triggers in `automation/triggers/`
- Define actions in `automation/actions/`
- Register in `RuleEngine`

### Add Database Entity
1. Create Entity in `database/entities/`
2. Create DAO in `database/dao/`
3. Add to `AppDatabase.kt`
4. Create Repository

## 🔐 Security Setup

### Enable Encryption
```kotlin
// In SettingsScreen
encryptionEnabled.value = true
// Or via PreferencesManager
preferencesManager.setEncryptionEnabled(true)
```

### Enable Biometric
```kotlin
// In SettingsScreen
biometricEnabled.value = true
// Check availability
biometricManager.isBiometricAvailable()
```

## 📱 Testing Checklist

- [ ] Build successfully: `./gradlew build`
- [ ] Install works: `./gradlew installDebug`
- [ ] App launches without crash
- [ ] Navigate between screens
- [ ] Browse files (needs permissions)
- [ ] Load photos (needs permissions)
- [ ] Open settings
- [ ] Test offline functionality

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| Build fails | Run `./gradlew clean build` |
| Permissions denied | Check AndroidManifest.xml, grant runtime permissions |
| App crashes on launch | Check Logcat: `adb logcat \| grep PhotoVault` |
| Files not showing | Ensure Storage permission granted on device |
| Database error | Delete app data: `adb shell pm clear com.photovault` |

## 📞 Getting Help

1. **Read documentation**: Check README.md and ARCHITECTURE.md
2. **Check logs**: `adb logcat | grep PhotoVault`
3. **Review code**: Look at similar implementations
4. **Build errors**: Check Gradle console output

## 🚀 Performance Tips

1. **Device permissions**: Grant all permissions for full functionality
2. **Storage space**: Ensure at least 100MB free for app
3. **Emulator**: Use x86_64 architecture for better performance
4. **RAM**: 2GB+ recommended for smooth editing

## 📚 Key Code Locations

| Task | File |
|------|------|
| Add screen | `ui/screens/` |
| Add tool | `editor/tools/` |
| Database query | `data/repository/` |
| Business logic | `domain/usecase/` |
| String resource | `res/values/strings.xml` |
| Permission | `AndroidManifest.xml` |

## 🎨 Customization

### Change App Colors
Edit `app/src/main/res/values/colors.xml`:
```xml
<color name="primary">#FF6200EE</color>
```

### Change App Name
Edit `app/src/main/res/values/strings.xml`:
```xml
<string name="app_name">Your App Name</string>
```

### Change Permissions
Edit `app/src/main/AndroidManifest.xml`:
```xml
<uses-permission android:name="..." />
```

## 🔄 Build Variants

```bash
# Debug build (default, fastest)
./gradlew installDebug

# Release build (optimized, slower)
./gradlew assembleRelease

# Clean build
./gradlew clean build

# Build with specific variant
./gradlew buildDebug
```

## 📊 Project Metrics

- **Total Kotlin Files**: 60+
- **Lines of Code**: 15,000+
- **Compilation Time**: ~30-60 seconds
- **APK Size**: ~10-15 MB (debug)
- **Min API**: 26 (Android 8.0)
- **Target API**: 34 (Android 14)

## ✨ Quick Tips

1. **Use logcat**: `adb logcat` for debugging
2. **Hot reload**: Hot reload Compose changes in Android Studio
3. **Database inspection**: Use Android Studio Database Inspector
4. **Profiling**: Use Android Studio Profiler for performance
5. **Lint**: Run `./gradlew lint` to check code quality

## 🎓 Learning Resources

- [Android Developer Docs](https://developer.android.com/)
- [Kotlin Documentation](https://kotlinlang.org/docs/)
- [Jetpack Compose Tutorial](https://developer.android.com/jetpack/compose/tutorial)
- [Room Database Guide](https://developer.android.com/training/data-storage/room)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

## 🎯 Next Steps

1. ✅ Build and run the app
2. ✅ Test core features
3. ✅ Explore the codebase
4. ✅ Read ARCHITECTURE.md for details
5. ✅ Customize for your needs
6. ✅ Deploy to users

---

**You're all set!** PhotoVault is ready to use. Start with the app, then explore the code to understand the implementation.

For detailed information, see [README.md](README.md) and [ARCHITECTURE.md](ARCHITECTURE.md).
