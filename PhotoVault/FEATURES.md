# PhotoVault Features Guide

## 🎯 Quick Feature Overview

### Core Features

1. **File Management** ✅
   - Browse local files and directories
   - Create, copy, move, rename, delete files
   - Batch operations on multiple files
   - File sorting and filtering

2. **Photo Editing** ✅
   - Professional editing tools
   - Non-destructive editing with history
   - Multiple export formats
   - Custom presets

3. **Audio & Video** ✅
   - Audio player with metadata
   - Video playback
   - Organization tools
   - Metadata display

4. **Security** ✅
   - File encryption with AES-256
   - Biometric authentication
   - Secure vault for sensitive files
   - Device security verification

5. **Offline Sharing** ✅
   - QR code generation
   - Bluetooth file transfer
   - Xender integration
   - Local network sharing

6. **Automation** ✅
   - Custom automation rules
   - File triggers
   - Scheduled actions
   - Auto-organization

## 📱 Detailed Feature Descriptions

### File Browser

**Capabilities:**
- Navigate device storage
- View file details (size, date, type)
- Context menu operations (copy, move, delete)
- Search and filter files
- Drag-and-drop operations (if supported)
- Create new folders

**Operations:**
```kotlin
// Copy file
fileRepository.copyFile("/source/file.txt", "/destination/")

// Move file
fileRepository.moveFile("/old/file.txt", "/new/")

// Delete file
fileRepository.deleteFile("/path/file.txt")

// Rename file
fileRepository.renameFile("/path/file.txt", "newname.txt")
```

### Photo Editor

**Editing Tools:**

1. **Exposure Tool**
   - Adjust overall brightness
   - Separate highlights and shadows control
   - Range: -2 to +2

2. **Curves Tool**
   - Add control points to tone curve
   - Adjust individual RGB channels
   - Fine-grained tonal control

3. **HSL Tool**
   - Hue: -180 to +180 degrees
   - Saturation: -100 to +100%
   - Lightness: -100 to +100%

4. **Transform Tools**
   - Crop: Rectangular crop with presets
   - Rotate: Free rotation up to 360 degrees
   - Perspective: Corner point adjustment

5. **Retouch Tools**
   - Healing Tool: Remove blemishes
   - Clone Tool: Copy pixels from source
   - Adjustable brush size and opacity

**Workflow Example:**
```kotlin
// Load image
val editPipeline = EditPipeline()
editPipeline.loadImage(bitmap)

// Apply adjustments
editPipeline.applyExposure(1.5f)
editPipeline.applySaturation(0.8f)
editPipeline.applyContrast(-0.5f)

// Export result
val result = editPipeline.getCurrentBitmap()
exportManager.exportAsJPEG(result, file, quality = 90)
```

### Audio Player

**Features:**
- Play/pause controls
- Seek bar with duration
- Volume control
- Metadata display (artist, album, duration)
- Playlist support
- Equalizer (basic)

**Supported Formats:**
- MP3 (MPEG Audio)
- WAV (Waveform Audio)
- FLAC (Free Lossless Audio Codec)
- AAC (Advanced Audio Coding)
- OGG (Vorbis)

### Video Player

**Features:**
- Full playback controls
- Seek/timeline scrubbing
- Resolution and quality info
- Brightness/contrast adjustment
- Subtitle support (if embedded)
- Picture-in-picture mode (system dependent)

**Supported Formats:**
- MP4 (MPEG-4 Video)
- MKV (Matroska)
- WebM (WebM Video)
- 3GP (3GPP)
- AVI (Audio Video Interleave)

### Security Vault

**Features:**
- Encrypt files with AES-256
- Biometric authentication
- Hidden vault directory
- Lock timer (auto-lock after inactivity)
- Emergency wipe capability

**Encryption Flow:**
```kotlin
val encryptor = AESFileEncryptor()

// Encrypt file
encryptor.encryptFile(
    inputFile = File("/path/original.jpg"),
    outputFile = File("/vault/encrypted.jpg")
)

// Decrypt file (after biometric auth)
encryptor.decryptFile(
    inputFile = File("/vault/encrypted.jpg"),
    outputFile = File("/temp/decrypted.jpg")
)
```

### Offline Sharing

**Methods:**

1. **QR Code Sharing**
   - Generate QR code from file path
   - Share link via messaging apps
   - Recipient scans QR to receive file

2. **Bluetooth Sharing**
   - Direct device-to-device transfer
   - No internet required
   - Compatible with all Bluetooth devices

3. **Xender Integration**
   - Cross-platform file sharing
   - Works offline
   - Supports batch transfers

4. **Local Network Sharing**
   - Generate local share links
   - Access from other devices on network
   - Paste link to receive files

**Example:**
```kotlin
val shareUseCase = ShareFileUseCase(fileRepository)

// Generate share link
val link = shareUseCase.generateShareLink("/path/file.zip")
// Returns: "photovault://share/file.zip/uuid-123"

// Generate QR code content
val qrContent = shareUseCase.generateQRCodeContent("/path/file.zip")

// Share multiple files
shareUseCase.shareMultipleFiles(
    listOf("/path/file1.jpg", "/path/file2.jpg"),
    "/shared/"
)
```

### Automation & Rules

**Rule Creation:**
```kotlin
// Create automation rule
val rule = AutomationRule(
    name = "Auto-organize Photos",
    triggers = listOf("fileCreatedTrigger"),
    actions = listOf("moveFileAction"),
    conditions = mapOf(
        "fileExtension" to "jpg",
        "directory" to "/Pictures"
    )
)

// Add to engine
ruleEngine.addRule(rule)

// Enable/disable
ruleEngine.enableRule(rule.id)
```

**Triggers:**
- File created in directory
- Time-based (daily, weekly, monthly)
- Storage space threshold
- Battery level
- Network change

**Actions:**
- Move file to directory
- Delete file
- Rename file (with pattern)
- Copy file
- Encrypt file
- Create backup

### Storage Analysis

**Capabilities:**
- Analyze directory usage
- File type breakdown
- Large file detection
- Storage health report

**Usage Example:**
```kotlin
val analyzer = StorageAnalyzer()
val analysis = analyzer.analyzeDirectory("/storage/emulated/0")

println("Total Size: ${analysis.totalSize / (1024*1024)} MB")
println("Files: ${analysis.fileCount}")
println("Folders: ${analysis.folderCount}")
analysis.fileTypeMap.forEach { (ext, size) ->
    println("$ext: ${size / 1024} KB")
}
```

### Duplicate Detection

**Process:**
1. Scans directory recursively
2. Calculates SHA-256 hash for each file
3. Groups identical files
4. Offers to keep/delete

**Usage Example:**
```kotlin
val scanner = DuplicateScanner()
val duplicates = scanner.scanForDuplicates("/storage/emulated/0")

// Remove duplicates (keep first of each group)
val deleted = scanner.removeDuplicates(duplicates, keepFirst = true)
println("Deleted $deleted duplicate files")
```

### Settings & Preferences

**Available Settings:**
- **Theme**: Light, Dark, System
- **Auto-Backup**: Enable/disable automatic backups
- **Encryption**: Enable/disable file encryption
- **Biometric**: Enable/disable biometric authentication
- **Sort Order**: Date, Name, Size (ascending/descending)
- **Show Hidden Files**: Toggle visibility
- **Default Export Format**: JPEG, PNG, WebP
- **Auto-Delete Thumbnails**: Clean up cache

**Storage Locations:**
- Internal App Storage: `/data/data/com.photovault/files/`
- External Storage: `/storage/emulated/0/PhotoVault/`
- Cache Directory: `/cache/com.photovault/`

### Presets Management

**Preset Creation:**
```kotlin
val presetManager = PresetManager()

// Create custom preset
val preset = presetManager.createPreset(
    name = "Warm Sunset",
    exposure = 0.5f,
    contrast = 0.3f,
    saturation = 1.2f,
    temperature = 1500f,
    tint = 200f,
    highlights = 0.2f,
    shadows = -0.1f,
    clarity = 0.4f,
    vibrance = 0.8f
)

// Apply preset to photo
applyEditUseCase.applyPreset(preset)

// Duplicate preset
presetManager.duplicatePreset(preset.id)
```

## 🔧 Advanced Features

### Batch Processing

**Batch Rename:**
```kotlin
val batchUseCase = BatchEditUseCase(fileRepository)
val result = batchUseCase.renameMultipleFiles(
    files = photoList,
    newNamePattern = "Photo_{index}.jpg"
)
println("Renamed: ${result.first}, Failed: ${result.second}")
```

**Batch Move:**
```kotlin
val result = batchUseCase.moveMultipleFiles(
    files = selectedFiles,
    destinationDir = "/storage/emulated/0/Archive"
)
```

**Batch Delete:**
```kotlin
val result = batchUseCase.deleteMultipleFiles(selectedFiles)
```

### Non-Destructive Editing

All edits maintain original file integrity:
- Original file never modified
- Edit history stored separately
- Can revert to any previous state
- Multiple versions can be exported

## 📊 Performance Features

- **Lazy Loading**: Images loaded on demand
- **Database Caching**: Metadata cached locally
- **Bitmap Optimization**: Efficient memory usage
- **Async Processing**: Non-blocking operations
- **Compression**: Optimized export formats

## 🎨 UI/UX Features

- **Material Design 3**: Modern design system
- **Dark Mode**: Full dark theme support
- **Responsive Layout**: Adapts to screen sizes
- **Haptic Feedback**: Tactile interactions
- **Accessibility**: Screen reader support

## 🔄 Sync & Backup

- **Local Backup**: Create backup copies
- **Auto-Sync**: Sync metadata automatically
- **Restore from Backup**: Recover deleted files
- **Export Settings**: Save app configuration

## 📈 Future Enhancements

- Advanced video editing capabilities
- RAW photo format support
- Machine learning-based duplicate detection
- Cloud backup integration (optional)
- Plugin system for custom filters
- Advanced color grading suite
- Batch AI enhancement
- Social media integration

## 💡 Tips & Tricks

1. **Quick Access**: Pin frequently used folders
2. **Collections**: Organize files into collections
3. **Smart Tags**: Auto-tag files by type/date
4. **Keyboard Shortcuts**: Use hardware keyboard
5. **Gestures**: Swipe to navigate screens
6. **Voice Commands**: Control via voice (future)

---

For more information, visit the [README.md](README.md) or [ARCHITECTURE.md](ARCHITECTURE.md)
