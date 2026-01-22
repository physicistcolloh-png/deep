package com.photovault.automation.triggers

import java.util.UUID

data class FileCreatedTrigger(
    val id: String = UUID.randomUUID().toString(),
    val watchPath: String,
    val filePattern: String = "*"
)

data class TimeTrigger(
    val id: String = UUID.randomUUID().toString(),
    val time: Long, // milliseconds
    val repeating: Boolean = false,
    val interval: Long = 0L // interval if repeating
)
