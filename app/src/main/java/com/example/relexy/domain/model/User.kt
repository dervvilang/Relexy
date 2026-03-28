package com.example.relexy.domain.model

import androidx.annotation.DrawableRes

data class User(
    val id: String,
    val nickname: String,
    @DrawableRes val icon: Int,
)
