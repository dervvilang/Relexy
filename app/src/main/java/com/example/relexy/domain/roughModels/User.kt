package com.example.relexy.domain.roughModels

import androidx.annotation.DrawableRes

data class User(
    val id: String,
    val nickname: String,
    @DrawableRes val icon: Int,
)
