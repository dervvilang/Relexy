package com.example.relexy.domain.roughModels

import androidx.annotation.DrawableRes

data class Dictionary(
    val id: String,
    val title: String,
    @DrawableRes val leadIcon: Int,
    val wordCount: Int,
    val progress: Int,
    val isOwnedByUser: Boolean
)
