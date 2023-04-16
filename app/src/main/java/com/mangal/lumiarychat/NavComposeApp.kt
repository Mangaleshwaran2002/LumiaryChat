package com.mangal.lumiarychat


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.mangal.lumiarychat.nav.Action
import com.mangal.lumiarychat.nav.Destination.AuthenticationOption
import com.mangal.lumiarychat.nav.Destination.Home
import com.mangal.lumiarychat.nav.Destination.Login
import com.mangal.lumiarychat.nav.Destination.Register
import com.mangal.lumiarychat.ui.theme.LumiaryChatTheme
import com.mangal.lumiarychat.view.AuthenticationView
import com.mangal.lumiarychat.view.home.HomeView
import com.mangal.lumiarychat.view.login.LoginView
import com.mangal.lumiarychat.view.register.RegisterView

/**
 * The main Navigation composable which will handle all the navigation stack.
 */

@Composable
fun NavComposeApp() {
    val navController = rememberNavController()
    val actions = remember(navController) { Action(navController) }
    LumiaryChatTheme {
        NavHost(
            navController = navController,
            startDestination =
            if (FirebaseAuth.getInstance().currentUser != null)
                Home
            else
                AuthenticationOption
        ) {
            composable(AuthenticationOption) {
                AuthenticationView(
                    register = actions.register,
                    login = actions.login
                )
            }
            composable(Register) {
                RegisterView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Login) {
                LoginView(
                    home = actions.home,
                    back = actions.navigateBack
                )
            }
            composable(Home) {
                HomeView(
                    home = actions.login
                )
            }
        }
    }
}
