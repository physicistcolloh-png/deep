package com.photovault.security.biometric

import android.content.Context
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity

class BiometricManager(private val context: Context) {

    fun isBiometricAvailable(): Boolean {
        val biometricManager = BiometricManager.from(context)
        return biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG) == BiometricManager.BIOMETRIC_SUCCESS
    }

    fun authenticate(
        activity: FragmentActivity,
        onSuccess: () -> Unit,
        onError: (Int, String) -> Unit,
        onFailed: () -> Unit
    ) {
        val biometricPrompt = BiometricPrompt(
            activity,
            BiometricPrompt.AuthenticationCallback(
                onSuccess = { result -> onSuccess() },
                onError = { errorCode, errString -> onError(errorCode, errString) },
                onFailed = { onFailed() }
            )
        )

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Vault Access")
            .setSubtitle("Authenticate to access your vault")
            .setNegativeButtonText("Cancel")
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    fun isDeviceSecure(): Boolean {
        val keyguardManager = context.getSystemService(android.app.KeyguardManager::class.java)
        return keyguardManager?.isDeviceSecure ?: false
    }
}
