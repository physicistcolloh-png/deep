# PhotoVault - Offline File & Photo Editor App

A fully-featured offline Android application for managing, editing, and sharing files, photos, audio, and videos without requiring internet access after installation.

## 📱 Features

### File Management
- **Complete File Browser**: Navigate and manage files locally
- **File Operations**: Copy, move, rename, delete files
- **Batch Operations**: Apply actions to multiple files at once
- **Storage Analysis**: Analyze disk usage and file types
- **Duplicate Detection**: Find and remove duplicate files using SHA-256 checksums

### Photo Editing
- **Advanced Tools**:
  - Exposure, Highlights, Shadows adjustment
  - Curves editor with control points
  - HSL (Hue, Saturation, Lightness) tuning
  - Crop, Perspective, Rotate transforms
  - Healing and Clone retouch tools
  - Mask layers and adjustment layers

- **Non-Destructive Editing**: Full edit history with undo/redo
- **Presets**: Create, save, and apply custom edit presets
- **Export Options**: JPEG, PNG, WebP formats with quality control
- **Image Tools**: Scaling, rotation, cropping, watermarks

### Audio Management
- **Audio Player**: Full playback control for audio files
- **Metadata Support**: Display artist, album, duration information
- **Organization**: Sort by artist, album, or date
- **Format Support**: MP3, WAV, FLAC, AAC, OGG

### Video Playback
- **Full Video Support**: Play videos with offline controls
- **Metadata**: View resolution, frame rate, bitrate
- **Favorites**: Mark and organize favorite videos
- **Format Support**: MP4, MKV, WebM, 3GP, AVI

### Security & Privacy
- **AES-256 Encryption**: Encrypt sensitive files with AndroidKeystore
- **Biometric Authentication**: Fingerprint/Face recognition for vault access
- **Security Vault**: Dedicated encrypted storage for sensitive files
- **Device Security Check**: Verify device security status

### Offline Sharing
- **QR Code Generation**: Generate QR codes for file sharing links
- **Bluetooth Sharing**: Direct peer-to-peer file transfer
- **Xender Integration**: Support for Xender file transfer
- **Local Share Links**: Generate local network share links
- **Link Pasting**: Paste share links to receive files

### Automation
- **Automation Rules**: Create custom automation rules
- **File Triggers**: Trigger actions on file creation
- **Time-Based Actions**: Schedule automated tasks
- **File Actions**: Move, delete, organize files automatically

### Additional Features
- **Settings & Preferences**: Customize app behavior
- **Backup Support**: Auto-backup capabilities
- **Theme Support**: Light/Dark/System theme
- **Performance Optimized**: Efficient local processing

## 🏗️ Architecture

### Clean Architecture Pattern
- **UI Layer**: Jetpack Compose screens and components
- **Domain Layer**: Use cases and business logic
- **Data Layer**: Repositories, data sources, and database
- **Core Layer**: Permissions, settings, offline state management

### Database
- **Room Database**: Local SQLite storage
- **Entities**: Photo, Audio, Video, Preset, EditStep
- **DAOs**: Type-safe database access

### Key Components
- **AppDispatchers**: Coroutine dispatcher management
- **StoragePermissionManager**: Handle storage permissions
- **MediaPermissionManager**: Handle media permissions
- **OfflineStateManager**: Manage offline functionality
- **PreferencesManager**: User settings using DataStore
- **AESFileEncryptor**: File encryption/decryption
- **BiometricManager**: Biometric authentication
- **VaultManager**: Vault file management

## 📁 Project Structure

```
PhotoVault/
├── app/
│   ├── src/main/
│   │   ├── java/com/photovault/
│   │   │   ├── MainActivity.kt
│   │   │   ├── PhotoVaultApp.kt
│   │   │   │
│   │   │   ├── core/ (Dispatchers, Permissions, Settings, Offline)
│   │   │   ├── data/ (Models, Repositories, Data Sources, Database)
│   │   │   ├── domain/ (Use Cases, Engines)
│   │   │   ├── ui/ (Navigation, Screens, Components)
│   │   │   ├── editor/ (Tools, Layers, Presets, Export)
│   │   │   ├── storage/ (Internal, External, Analysis)
│   │   │   ├── security/ (Encryption, Biometric, Vault)
│   │   │   ├── automation/ (Rules, Triggers, Actions)
│   │   │   ├── database/ (Entities, DAOs, AppDatabase)
│   │   │   ├── image_engine/ (Filters, Math, Pipeline)
│   │   │   └── utils/ (File, Image, Audio, Video, Date)
│   │   │
│   │   └── res/
│   └── build.gradle
│
├── build.gradle
└── settings.gradle
```

## 🛠️ Technologies Used

### Android & Kotlin
- **Kotlin**: Modern Android development language
- **Jetpack Compose**: Modern declarative UI framework
- **Android Architecture Components**: ViewModel, LiveData, Flow
- **Room Database**: Type-safe database access
- **Coroutines**: Asynchronous programming

### Security
- **AndroidKeystore**: Secure key storage
- **AES-256 Encryption**: File encryption
- **Biometric API**: Fingerprint/Face recognition
- **DataStore**: Secure preferences

### UI/UX
- **Material Design 3**: Modern design system
- **Compose Navigation**: Type-safe navigation
- **Flow**: Reactive data updates

## 📋 Permissions

```xml
<!-- Storage -->
MANAGE_EXTERNAL_STORAGE / READ_EXTERNAL_STORAGE / WRITE_EXTERNAL_STORAGE

<!-- Media -->
READ_MEDIA_IMAGES / READ_MEDIA_VIDEO / READ_MEDIA_AUDIO

<!-- Security -->
USE_BIOMETRIC

<!-- Offline Sharing -->
BLUETOOTH / BLUETOOTH_ADMIN / BLUETOOTH_CONNECT / BLUETOOTH_SCAN
CAMERA (for QR code scanning)
```

## 🚀 Getting Started

### Prerequisites
- Android API 26+ (Android 8.0)
- Gradle 8.1.0+
- Kotlin 1.9.0+

### Build & Run

```bash
# Clone the repository
cd PhotoVault

# Build the project
./gradlew build

# Run on device/emulator
./gradlew installDebug
```

## 💾 Database Schema

### Photos Table
- id (Primary Key)
- name, path, mimeType
- size, dateCreated, dateModified
- width, height
- isEncrypted, tags, rating, isFavorite
- checksum (for duplicate detection)

### Audio Table
- id (Primary Key)
- name, path, mimeType
- size, dateCreated, dateModified
- duration, artist, album
- isEncrypted, tags

### Videos Table
- id (Primary Key)
- name, path, mimeType
- size, dateCreated, dateModified
- duration, width, height, frameRate
- isEncrypted, tags, isFavorite

### Presets Table
- id (Primary Key)
- name, description
- exposure, contrast, saturation, temperature, tint
- highlights, shadows, clarity, vibrance
- tags, isFavorite, dateCreated, dateModified

## 🔐 Security Features

1. **File Encryption**: AES-256 encryption for vault files
2. **Biometric Authentication**: Optional biometric lock for vault
3. **Secure Storage**: Credentials stored in AndroidKeystore
4. **Permission Management**: Strict runtime permission handling
5. **Device Security**: Verification of device security status

## 📊 Performance Considerations

- **Non-Destructive Editing**: Edit history doesn't modify original files
- **Lazy Loading**: Images loaded on demand
- **Database Caching**: Local cache of metadata
- **Efficient Filters**: Optimized image processing
- **Memory Management**: Bitmap pooling and recycling

## 🔄 Offline Functionality

- **100% Offline**: All features work without internet after installation
- **No Cloud Dependency**: Files stored locally on device
- **Local Sharing**: QR codes, Bluetooth, Xender use local networks
- **Auto-Sync**: Optional local backup capabilities

## 📝 Future Enhancements

- [ ] Advanced video editing
- [ ] RAW photo support
- [ ] Batch photo editing
- [ ] Advanced curves editor UI
- [ ] Cloud backup integration (optional)
- [ ] Plugin system for custom filters
- [ ] Advanced color grading tools
- [ ] AI-powered duplicate detection

## 📄 License

This project is provided as-is for offline file and media management.

## 🤝 Contributing

Contributions are welcome! Please feel free to submit pull requests.

## 📞 Support

For issues and questions, please refer to the project documentation or create an issue in the repository.

---

**PhotoVault** - Your personal offline multimedia vault with professional editing capabilities.
