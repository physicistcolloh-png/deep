-keep class com.photovault.** { *; }
-keep class com.photovault.data.model.** { *; }
-keep class com.photovault.database.** { *; }

-keepclassmembers class * {
    @com.google.android.gms.common.internal.safeparcel.SafeParcelable *;
}

# Keep all Room entities
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao interface * { *; }

# Keep Kotlin data classes
-keep class * extends kotlin.Enum { *; }

# Preserve line numbers for debugging
-keepattributes SourceFile,LineNumberTable
-renamesourcefileattribute SourceFile
