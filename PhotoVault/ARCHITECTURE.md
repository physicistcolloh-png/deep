# PhotoVault Architecture & Implementation Guide

## 🏗️ Architecture Overview

PhotoVault follows **Clean Architecture** principles with clear separation of concerns:

```
Presentation Layer (UI)
        ↓
Domain Layer (Use Cases)
        ↓
Data Layer (Repositories)
        ↓
Core Layer (Infrastructure)
```

## 📦 Layer Breakdown

### 1. **UI Layer** (`ui/`)
- **Purpose**: Display data and handle user interactions
- **Technology**: Jetpack Compose
- **Components**:
  - `screens/`: Individual screen implementations
  - `components/`: Reusable UI components
  - `navigation/`: Navigation graph and routes
  
**Key Screens**:
- `HomeScreen`: Main navigation hub
- `FileBrowserScreen`: File exploration
- `EditorScreen`: Photo editing
- `AudioPlayerScreen`: Audio playback
- `VideoPlayerScreen`: Video playback
- `VaultScreen`: Encrypted vault access
- `ShareScreen`: Offline sharing options
- `SettingsScreen`: App configuration

### 2. **Domain Layer** (`domain/`)
- **Purpose**: Business logic and use cases
- **Technology**: Pure Kotlin (no Android dependencies)
- **Components**:
  - `usecase/`: Business logic operations
  - `engine/`: Processing engines (EditPipeline)

**Use Cases**:
- `LoadPhotosUseCase`: Fetch and cache photos
- `LoadAudioUseCase`: Fetch audio files
- `LoadVideoUseCase`: Fetch videos
- `ApplyEditUseCase`: Apply editing presets
- `DetectDuplicatesUseCase`: Find duplicate files
- `ShareFileUseCase`: Handle offline sharing
- `ExportMediaUseCase`: Export media files
- `BatchEditUseCase`: Bulk file operations

### 3. **Data Layer** (`data/`)
- **Purpose**: Data access and repository pattern
- **Technology**: Room Database, DataStore
- **Components**:
  - `model/`: Data transfer objects
  - `repository/`: Abstract data access
  - `source/`: Concrete data sources
  - `database/`: Room entities and DAOs

**Repositories**:
- `PhotoRepository`: Photo data operations
- `AudioRepository`: Audio data operations
- `VideoRepository`: Video data operations
- `FileRepository`: File operations
- `PresetRepository`: Edit presets management

**Data Sources**:
- `ImageDataSource`: Query MediaStore for images
- `AudioDataSource`: Query MediaStore for audio
- `VideoDataSource`: Query MediaStore for videos
- `FileDataSource`: File system operations

### 4. **Core Layer** (`core/`)
- **Purpose**: Infrastructure and cross-cutting concerns
- **Components**:
  - `dispatcher/`: Coroutine dispatchers
  - `permissions/`: Runtime permission management
  - `offline/`: Offline state management
  - `settings/`: User preferences

**Key Classes**:
- `AppDispatchers`: Central coroutine dispatch management
- `StoragePermissionManager`: Storage permission handling
- `MediaPermissionManager`: Media permission handling
- `OfflineStateManager`: Offline functionality tracking
- `PreferencesManager`: User settings persistence

### 5. **Editor Module** (`editor/`)
- **Purpose**: Photo editing tools and effects
- **Components**:
  - `tools/`: Individual editing tools
    - `exposure/`: Brightness/contrast adjustment
    - `curves/`: Tone curve editor
    - `hsl/`: Hue/Saturation/Lightness
    - `retouch/`: Healing and clone tools
    - `transform/`: Crop, rotate, perspective
  - `layers/`: Layer management
  - `presets/`: Preset management
  - `export/`: Export functionality

### 6. **Storage Module** (`storage/`)
- **Purpose**: Storage management and analysis
- **Components**:
  - `internal/`: App internal storage (cache, files)
  - `external/`: External storage access
  - `analyzer/`: Storage usage analysis
  - `DuplicateScanner`: Duplicate detection

### 7. **Security Module** (`security/`)
- **Purpose**: Encryption and authentication
- **Components**:
  - `encryption/`: AES-256 file encryption
  - `biometric/`: Biometric authentication
  - `vault/`: Secure vault management

### 8. **Automation Module** (`automation/`)
- **Purpose**: Automated task execution
- **Components**:
  - `rules/`: Automation rule definitions
  - `triggers/`: Event triggers
  - `actions/`: Automated actions

### 9. **Image Engine** (`image_engine/`)
- **Purpose**: Image processing and effects
- **Components**:
  - `filters/`: Image filters
  - `math/`: Color and matrix mathematics
  - `pipeline/`: Effect rendering pipeline

### 10. **Utilities** (`utils/`)
- **Purpose**: Helper functions and utilities
- **Utilities**:
  - `FileUtils`: File operations and formatting
  - `ImageUtils`: Image manipulation
  - `AudioUtils`: Audio utilities
  - `VideoUtils`: Video utilities
  - `DateUtils`: Date formatting

## 🔄 Data Flow Example: Loading Photos

```
UI Layer (HomeScreen)
    ↓ (User clicks "Browse Files")
Domain Layer (LoadPhotosUseCase.execute())
    ↓
Data Layer (PhotoRepository.getAllPhotos())
    ↓
Data Source (ImageDataSource.loadPhotosFromStorage())
    ↓
Database (PhotoDao.getAllPhotos()) + MediaStore Query
    ↓
Flow<List<PhotoEntity>>
    ↓ (Map to domain models)
UI Layer (Display photos)
```

## 📊 Database Schema

### Room Database Structure

```sql
-- Photos Table
CREATE TABLE photos (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    path TEXT NOT NULL,
    mimeType TEXT,
    size LONG,
    dateCreated LONG,
    dateModified LONG,
    width INTEGER,
    height INTEGER,
    isEncrypted BOOLEAN,
    tags TEXT,
    rating INTEGER,
    isFavorite BOOLEAN,
    checksum TEXT
);

-- Audio Table (similar structure)
-- Videos Table (similar structure)
-- Edit Steps Table (for edit history)
-- Presets Table (for saved presets)
```

## 🔐 Security Flow

```
User opens Vault
    ↓
BiometricManager.authenticate()
    ↓
Device Biometric Authentication
    ↓ (if successful)
VaultManager.getVaultedFiles()
    ↓
AESFileEncryptor.decryptFile() (for encrypted files)
    ↓
Display decrypted content
```

## 🎨 Editing Pipeline

```
User selects photo
    ↓
EditPipeline.loadImage(bitmap)
    ↓
User applies edits:
  - ExposureTool.apply()
  - CurvesTool.addControlPoint()
  - HslTool.applySaturation()
  - TransformTools (Crop, Rotate, Perspective)
  ↓
EditPipeline.applyExposure()
EditPipeline.applyContrast()
EditPipeline.applySaturation()
    ↓
RenderPipeline executes filters
    ↓
ExportManager exports result (JPEG, PNG, WebP)
```

## 🔄 Offline Sharing Flow

```
User selects file to share
    ↓
ShareFileUseCase.generateShareLink()
    ↓
Options:
  1. QR Code → Generate QR code content
  2. Bluetooth → Get file path for BT transfer
  3. Xender → Get file path for Xender
  4. Local Link → Generate local network link
    ↓
File transferred offline (no internet required)
```

## 📱 Permission Handling

### Runtime Permissions
- Storage: Android 13+ uses `MANAGE_EXTERNAL_STORAGE`
- Media: Android 13+ uses granular `READ_MEDIA_*`
- Biometric: Optional for vault
- Bluetooth: Optional for offline sharing
- Camera: Optional for QR scanning

## 🚀 Performance Optimizations

1. **Database Caching**: Room entities cached locally
2. **Lazy Loading**: Images loaded on-demand
3. **Coroutines**: Non-blocking async operations
4. **Bitmap Pooling**: Efficient image memory management
5. **Compression**: Export formats optimized for size
6. **Indexing**: Database queries optimized with indexes

## 📝 Dependency Injection

Current implementation uses a simple Service Locator pattern in `PhotoVaultApp`:
- All repositories initialized in `onCreate()`
- Passed via constructor to activities/use cases
- Can be upgraded to Hilt for larger projects

## 🔄 Reactive Programming

- **StateFlow**: Used for UI state management
- **Flow**: Used for data streams from database
- **Coroutines**: Used for async operations

## 🎯 Future Architecture Improvements

1. **Hilt DI**: Replace manual DI with Hilt
2. **ViewModel**: Add ViewModel for screen state
3. **State Management**: Implement Redux-like patterns
4. **Testing**: Add unit and instrumentation tests
5. **Jetpack Libraries**: Implement WorkManager for automation
6. **Paging**: Add Paging 3 for large file lists

## 📚 Resources

- [Android Architecture Components](https://developer.android.com/topic/architecture)
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Room Database](https://developer.android.com/training/data-storage/room)
