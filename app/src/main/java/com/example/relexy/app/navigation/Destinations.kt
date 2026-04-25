package com.example.relexy.app.navigation

object Destinations {
    const val LOGIN = "login"
    const val REGISTER = "register"

    const val LEARN_MAIN = "learn_main"
    const val DICTIONARY_MAIN = "dictionary_main"
    const val COMMUNITY_MAIN = "community_main"
    const val PROFILE_MAIN = "profile_main"
    const val SETTINGS = "settings"


    const val DICTIONARY_CHOOSE = "dictionary_choose"

    const val ARG_DICTIONARY_ID = "dictionaryId"
    const val ARG_WORD_ID = "wordId"

    const val DICTIONARY = "dictionary/{$ARG_DICTIONARY_ID}"
    fun dictionary(dictionaryId: String) = "dictionary/$dictionaryId"

    const val DICTIONARY_EDITOR = "dictionary_editor?$ARG_DICTIONARY_ID={$ARG_DICTIONARY_ID}"
    fun dictionaryEditor(dictionaryId: String? = null): String {
        return if (dictionaryId == null) {
            "dictionary_editor"
        } else {
            "dictionary_editor?$ARG_DICTIONARY_ID=$dictionaryId"
        }
    }

    const val WORD_EDITOR =
        "word_editor?$ARG_DICTIONARY_ID={$ARG_DICTIONARY_ID}&$ARG_WORD_ID={$ARG_WORD_ID}"

    fun wordEditor(
        dictionaryId: String,
        wordId: String? = null
    ): String {
        return if (wordId == null) {
            "word_editor?$ARG_DICTIONARY_ID=$dictionaryId"
        } else {
            "word_editor?$ARG_DICTIONARY_ID=$dictionaryId&$ARG_WORD_ID=$wordId"
        }
    }
}
