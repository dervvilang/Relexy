package com.example.relexy.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.relexy.feature.auth.login.view.LoginScreen
import com.example.relexy.feature.auth.register.view.RegisterScreen
import com.example.relexy.feature.community.CommunityMainScreen
import com.example.relexy.feature.dictionary.DictionaryChooseScreen
import com.example.relexy.feature.dictionary.DictionaryEditorScreen
import com.example.relexy.feature.dictionary.DictionaryMainScreen
import com.example.relexy.feature.dictionary.DictionaryScreen
import com.example.relexy.feature.dictionary.wordEditor.WordEditorScreen
import com.example.relexy.feature.learn.LearnMainScreen
import com.example.relexy.feature.profile.ProfileMainScreen
import com.example.relexy.feature.settings.SettingsScreen

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
            LearnMainScreen(
                onGoToDictionaryChoose = {
                    navController.navigate(Destinations.DICTIONARY_CHOOSE)
                }
            )
        }

        composable(Destinations.DICTIONARY_MAIN) {
            DictionaryMainScreen(
                onGoToDictionaryScreen = {
                    navController.navigate(Destinations.DICTIONARY)
                },
                onGoToDictionaryEditorScreen = {
                    navController.navigate(Destinations.DICTIONARY_EDITOR)
                }
            )
        }

        composable(Destinations.COMMUNITY_MAIN) {
            CommunityMainScreen()
        }

        composable(Destinations.PROFILE_MAIN) {
            ProfileMainScreen(
                onGoToSettings = {
                    navController.navigate(Destinations.SETTINGS)
                }
            )
        }

        composable(Destinations.DICTIONARY_CHOOSE) {
            DictionaryChooseScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Destinations.DICTIONARY) {
            DictionaryScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onGoToWordEditorScreen = {
                    navController.navigate(Destinations.WORD_EDITOR)
                }
            )
        }

        composable(Destinations.DICTIONARY_EDITOR) {
            DictionaryEditorScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Destinations.WORD_EDITOR) {
            WordEditorScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable(Destinations.SETTINGS) {
            SettingsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}