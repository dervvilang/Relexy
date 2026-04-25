package com.example.relexy.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.relexy.feature.auth.login.view.LoginScreen
import com.example.relexy.feature.auth.register.view.RegisterScreen
import com.example.relexy.feature.community.CommunityMainScreen
import com.example.relexy.feature.dictionary.DictionaryChooseScreen
import com.example.relexy.feature.dictionary.DictionaryRoute
import com.example.relexy.feature.dictionary.dictionaryMain.DictionaryMainRoute
import com.example.relexy.feature.dictionary.DictionaryScreen
import com.example.relexy.feature.dictionary.dictionaryEditor.DictionaryEditorRoute
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
            DictionaryMainRoute(
                onGoToDictionaryScreen = { dictionaryId ->
                    navController.navigate(Destinations.dictionary(dictionaryId))
                },
                onGoToDictionaryEditorScreen = { dictionaryId ->
                    navController.navigate(Destinations.dictionaryEditor(dictionaryId))
                },
                onImportClick = {
                    // позже сюда import CSV
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

        composable(
            route = Destinations.DICTIONARY,
            arguments = listOf(
                androidx.navigation.navArgument(Destinations.ARG_DICTIONARY_ID) {
                    type = androidx.navigation.NavType.StringType
                }
            )
        ) { backStackEntry ->
            val dictionaryId = requireNotNull(
                backStackEntry.arguments?.getString(Destinations.ARG_DICTIONARY_ID)
            )

            DictionaryRoute(
                dictionaryId = dictionaryId,
                onBackClick = {
                    navController.popBackStack()
                },
                onGoToDictionaryEditorScreen = { targetDictionaryId ->
                    navController.navigate(Destinations.dictionaryEditor(targetDictionaryId))
                },
                onGoToWordEditorScreen = { targetDictionaryId, wordId ->
                    navController.navigate(
                        Destinations.wordEditor(
                            dictionaryId = targetDictionaryId,
                            wordId = wordId
                        )
                    )
                }
            )
        }

        composable(
            route = Destinations.DICTIONARY_EDITOR,
            arguments = listOf(
                androidx.navigation.navArgument(Destinations.ARG_DICTIONARY_ID) {
                    type = androidx.navigation.NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val dictionaryId =
                backStackEntry.arguments?.getString(Destinations.ARG_DICTIONARY_ID)

            DictionaryEditorRoute(
                dictionaryId = dictionaryId,
                onBackClick = {
                    navController.popBackStack()
                },
                onOpenDictionary = { savedDictionaryId ->
                    navController.navigate(Destinations.dictionary(savedDictionaryId)) {
                        popUpTo(Destinations.DICTIONARY_MAIN) {
                            inclusive = false
                        }
                    }
                }
            )
        }

        composable(
            route = Destinations.WORD_EDITOR,
            arguments = listOf(
                navArgument(Destinations.ARG_DICTIONARY_ID) {
                    type = NavType.StringType
                },
                navArgument(Destinations.ARG_WORD_ID) {
                    type = NavType.StringType
                    nullable = true
                }
            )
        ) { backStackEntry ->
            val dictionaryId = requireNotNull(
                backStackEntry.arguments?.getString(Destinations.ARG_DICTIONARY_ID)
            )
            val wordId =
                backStackEntry.arguments?.getString(Destinations.ARG_WORD_ID)

            WordEditorScreen(
                dictionaryId = dictionaryId,
                wordId = wordId,
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