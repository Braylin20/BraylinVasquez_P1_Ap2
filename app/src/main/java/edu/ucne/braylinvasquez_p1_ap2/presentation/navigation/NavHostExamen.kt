package edu.ucne.braylinvasquez_p1_ap2.presentation.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavHostExamen(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController,startDestination =Screen.ListScreen ) {
        composable<Screen.ListScreen> {
            Scaffold { innerPadding->
                Column(modifier = Modifier.padding(innerPadding)) {
                    OutlinedButton(onClick = {navHostController.navigate(Screen.RegistroScreen(0))},
                    ) {
                        Text("Ir a la pantalla")
                    }
                }

            }
        }
        composable<Screen.RegistroScreen> {
            Scaffold { innerPadding->
                Column(modifier = Modifier.padding(innerPadding)) {
                    Text("Hola")
                }

            }
        }
    }
}