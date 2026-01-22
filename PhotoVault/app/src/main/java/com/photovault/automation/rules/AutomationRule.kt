package com.photovault.automation.rules

import java.util.UUID

data class AutomationRule(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val triggers: List<String>, // trigger IDs
    val actions: List<String>, // action IDs
    val isEnabled: Boolean = true,
    val conditions: Map<String, Any> = emptyMap()
)
