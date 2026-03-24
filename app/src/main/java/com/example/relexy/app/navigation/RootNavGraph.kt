package com.example.relexy.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.relexy.feature.auth.login.view.LoginScreen
import com.example.relexy.feature.auth.register.view.RegisterScreen
import com.example.relexy.feature.community.CommunityMainScreen
import com.example.relexy.feature.dictionary.DictionaryMainScreen
import com.example.relexy.feature.learn.LearnMainScreen
import com.example.relexy.feature.profile.ProfileMainScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.LOGIN,
        modifier = modifier
    ) {
        composable(Destinations.LOGIN) {
            LoginScreen(
                onGoToRegister = {
                    navController.navigate(Destinations.REGISTER) {
                        popUpTo(Destinations.LOGIN) { inclusive = true }
                        launchSingleTop = true
                    }
                },
                onLoginClick = {
                    navController.navigate(Destinations.LEARN_MAIN) {
                        popUpTo(Destinations.LOGIN) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Destinations.REGISTER) {
            RegisterScreen(
                onGoToLogin = {
                    navController.navigate(Destinations.LOGIN) {
                        popUpTo(Destinations.REGISTER) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }

        composable(Destinations.LEARN_MAIN) {
            LearnMainScreen()
        }

        composable(Destinations.DICTIONARY_MAIN) {
            DictionaryMainScreen()
        }

        composable(Destinations.COMMUNITY_MAIN) {
            CommunityMainScreen()
        }

        composable(Destinations.PROFILE_MAIN) {
            ProfileMainScreen()
        }
    }
}