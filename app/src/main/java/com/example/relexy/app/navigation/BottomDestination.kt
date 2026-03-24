package com.example.relexy.app.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.relexy.R

sealed class BottomDestination(
    val route: String,
    @StringRes val titleRes: Int,
    @DrawableRes val iconRes: Int
) {
    object Learn : BottomDestination(
        route = Destinations.LEARN_MAIN,
        titleRes = R.string.nav_learn,
        iconRes = R.drawable.ic_hat_graduation__2_
    )

    object Dictionary : BottomDestination(
        route = Destinations.DICTIONARY_MAIN,
        titleRes = R.string.nav_dictionaries,
        iconRes = R.drawable.ic_library_1
    )

    object Community : BottomDestination(
        route = Destinations.COMMUNITY_MAIN,
        titleRes = R.string.nav_community,
        iconRes = R.drawable.ic_people_team_1
    )

    object Profile : BottomDestination(
        route = Destinations.PROFILE_MAIN,
        titleRes = R.string.nav_profile,
        iconRes = R.drawable.ic_person_1
    )
}