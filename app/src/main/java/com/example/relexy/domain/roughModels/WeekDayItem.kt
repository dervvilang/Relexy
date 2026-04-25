package com.example.relexy.domain.roughModels

import androidx.annotation.StringRes

data class WeekDayItem(
    @StringRes val label: Int,
    val isCurrent: Boolean
)