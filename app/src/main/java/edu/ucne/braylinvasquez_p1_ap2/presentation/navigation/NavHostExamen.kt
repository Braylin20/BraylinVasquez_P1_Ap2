package edu.ucne.braylinvasquez_p1_ap2.presentation.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import edu.ucne.braylinvasquez_p1_ap2.presentation.navigation.algo.VentaCreateScreen
import edu.ucne.braylinvasquez_p1_ap2.presentation.navigation.algo.VentaListScreen


@Composable
fun NavHostExamen(
    navHostController: NavHostController
) {
    NavHost(navController = navHostController,startDestination =Screen.ListScreen ) {
        composable<Screen.ListScreen> {
            VentaListScreen(
                onGoCreate = { navHostController.navigate(Screen.RegistroScreen(0)) },
                onGoEdit = { navHostController.navigate(Screen.RegistroScreen(it)) }
            )
        }
        composable<Screen.RegistroScreen> {
            val ventaId = it.toRoute<Screen.RegistroScreen>().id
            VentaCreateScreen(
                onBack = { navHostController.popBackStack() },
                ventaId = ventaId
            )
        }
    }
}