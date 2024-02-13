package com.app.twerlo.presentation.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.app.twerlo.presentation.NavGraphs
import com.app.twerlo.presentation.appCurrentDestinationAsState
import com.app.twerlo.presentation.destinations.TypedDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.delay

@Destination(start = true)
@Composable
fun MainScreen(
  action: String? = null,
  actionData: String? = null,
  navigator: DestinationsNavigator? = null,
  viewModel: MainViewModel = hiltViewModel()
) {
  val navController = rememberNavController()
  val currentDestination = navController.appCurrentDestinationAsState().value
  val isKeyboardOpen by keyboardAsState()

  Scaffold(
    content = { innerPadding ->
      val bottomPadding = max(
        innerPadding.calculateBottomPadding(),
        WindowInsets.ime.asPaddingValues().calculateBottomPadding()
      )
      Box(
        modifier = Modifier
          .padding(bottom = if (isKeyboardOpen.not()) bottomPadding else 0.dp)
      ) {
        DestinationsNavHost(navGraph = NavGraphs.root, navController = navController)
      }
    }
  )
}


@Composable
fun keyboardAsState(): State<Boolean> {
  val isImeVisible = WindowInsets.ime.getBottom(LocalDensity.current) > 0
  return rememberUpdatedState(isImeVisible)
}