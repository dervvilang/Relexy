package com.example.relexy.feature.dictionary

import androidx.annotation.StringRes
import com.example.relexy.R

enum class DictionarySortMode(
    @StringRes val titleRes: Int
) {
    BY_STATUS(R.string.dictionary_filter_by_status),
    BY_NOVELTY(R.string.dictionary_filter_by_novelty),
    BY_ALPHABET(R.string.dictionary_filter_by_alphabet)
}