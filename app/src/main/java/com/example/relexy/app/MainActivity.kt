package com.example.relexy.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.relexy.app.navigation.BottomBar
import com.example.relexy.app.navigation.Destinations
import com.example.relexy.app.navigation.FloatingBottomBarOverlay
import com.example.relexy.app.navigation.RootNavGraph
import com.example.relexy.core.ui.theme.RelexyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            RelexyTheme {
                val navController = rememberNavController()
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                val showBottomBar = currentRoute in setOf(
                    Destinations.LEARN_MAIN,
                    Destinations.DICTIONARY_MAIN,
                    Destinations.COMMUNITY_MAIN,
                    Destinations.PROFILE_MAIN
                )

                Scaffold(
                    containerColor = MaterialTheme.colorScheme.background
                )
                { innerPadding ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        RootNavGraph(
                            navController = navController,
                            modifier = Modifier.fillMaxSize()
                        )

                        if (showBottomBar) {
                            FloatingBottomBarOverlay(
                                currentRoute = currentRoute,
                                onItemClick = { route ->
                                    navController.navigate(route) {
                                        popUpTo(navController.graph.startDestinationId) { //подъем по стеку то стартового destination графа
                                            saveState = true
                                        }
                                        launchSingleTop =
                                            true //чтобы не создавать повторно открытый ранее экран
                                        restoreState =
                                            true //попытка восстановления состояния ранее открытого экрана
                                    }
                                },
                                modifier = Modifier.align(Alignment.BottomCenter)
                            )
                        }
                    }
                }
            }
        }
    }
}