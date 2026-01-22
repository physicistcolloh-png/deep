# Development Setup & Build Instructions

## Prerequisites

- **Android Studio**: Latest version (Giraffe or newer)
- **Android SDK**: API 34 (Android 14)
- **Java Development Kit (JDK)**: 17 or newer
- **Gradle**: 8.1.0 or newer
- **Kotlin**: 1.9.0 or newer

## Setup Instructions

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/PhotoVault.git
cd PhotoVault
```

### 2. Configure Gradle

Edit `local.properties` (create if doesn't exist):

```properties
sdk.dir=/path/to/android/sdk
ndk.dir=/path/to/android/ndk
```

### 3. Build the Project

```bash
# Build debug APK
./gradlew build

# Build release APK
./gradlew assembleRelease

# Build with specific variant
./gradlew buildDebug
```

### 4. Run on Device/Emulator

```bash
# Install and run debug app
./gradlew installDebug

# Run with verbose logging
./gradlew installDebug -i

# Run on specific device
adb devices  # List devices
adb -s DEVICE_ID install app/build/outputs/apk/debug/app-debug.apk
```

## Project Structure

```
PhotoVault/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/photovault/
│   │   │   ├── AndroidManifest.xml
│   │   │   └── res/
│   │   └── test/
│   │       └── java/com/photovault/
│   └── build.gradle
├── build.gradle
├── settings.gradle
├── README.md
├── ARCHITECTURE.md
└── DEVELOPMENT.md
```

## Testing

```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest

# Run tests with coverage
./gradlew testDebugUnitTest jacocoTestReport
```

## Linting & Code Quality

```bash
# Run linter
./gradlew lint

# Check formatting
./gradlew checkstyle
```

## Debugging

### Enable Debug Logging

In `MainActivity.kt`:
```kotlin
Log.d("PhotoVault", "Debug message")
```

### Android Studio Debugger

1. Set breakpoints in code
2. Run `./gradlew installDebug` 
3. Click "Debug app" in Android Studio
4. Step through code

### View Logs

```bash
# All logs
adb logcat

# Specific app logs
adb logcat | grep PhotoVault

# Save logs
adb logcat > logfile.txt
```

## Performance Profiling

### Memory Profiler
1. Android Studio → Profile → Memory Profiler
2. Record memory allocations
3. Identify leaks and optimize

### CPU Profiler
1. Android Studio → Profile → CPU Profiler
2. Record method traces
3. Analyze performance bottlenecks

## Building for Release

```bash
# Create signed release APK
./gradlew assembleRelease \
  -Pandroid.injected.signing.store.file=/path/to/keystore.jks \
  -Pandroid.injected.signing.store.password=storepass \
  -Pandroid.injected.signing.key.alias=keyalias \
  -Pandroid.injected.signing.key.password=keypass

# Output: app/build/outputs/apk/release/app-release.apk
```

## Troubleshooting

### Build Issues

```bash
# Clean build
./gradlew clean build

# Clear gradle cache
rm -rf ~/.gradle/caches

# Update dependencies
./gradlew dependencies --refresh-dependencies
```

### Runtime Issues

1. Check device SDK version (min API 26)
2. Ensure permissions granted
3. Check device storage space
4. Review logs with `adb logcat`

## CI/CD Pipeline

Example GitHub Actions workflow:

```yaml
name: Build

on: [push]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v2
      - uses: actions/setup-java@v2
        with:
          java-version: '17'
      - run: ./gradlew build
      - run: ./gradlew test
```

## Documentation Generation

Generate Kotlin documentation:

```bash
./gradlew dokkaHtml
# Output: build/dokka/html/
```

## Version Management

Update version in `app/build.gradle`:

```gradle
defaultConfig {
    versionCode 1
    versionName "1.0.0"
}
```

## Git Workflow

```bash
# Create feature branch
git checkout -b feature/your-feature

# Commit changes
git add .
git commit -m "feat: description"

# Push and create PR
git push origin feature/your-feature
```

## Additional Resources

- [Android Developer Guide](https://developer.android.com/)
- [Kotlin Documentation](https://kotlinlang.org/docs/)
- [Gradle Documentation](https://gradle.org/documentation/)
- [Jetpack Compose Guide](https://developer.android.com/jetpack/compose/documentation)
