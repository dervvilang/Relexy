package com.example.relexy.domain.model

import androidx.annotation.StringRes

data class WeekDayItem(
    @StringRes val label: Int,
    val isCurrent: Boolean
)