package com.example.examen.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val startDestination = "product_list"

    NavHost(
        navController = navController,
        startDestination = "product_list"
    ) {
        composable(route = "product_list") {
            ProductListScreen(
                onProductClick = { productId ->
                    navController.navigate("product_detail/$productId")
                }
            )
        }

        composable(
            route = "product_detail/{productId}",
            arguments = listOf(
                navArgument("productId") { type = NavType.IntType }
            )
        ) { backStackEntry -> val productId = backStackEntry.arguments?.getInt("productId") ?: 0
            ProductDetailScreen(productId = productId)
        }
    }
}

@Composable
fun ProductListScreen(onProductClick: (Int) -> Unit) {
}

@Composable
fun ProductDetailScreen(productId: Int) {
}

