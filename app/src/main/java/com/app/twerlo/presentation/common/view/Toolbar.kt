package com.app.twerlo.presentation.common.view

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.app.twerlo.presentation.theme.CelestialBlue
import com.intuit.sdp.R
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Composable
fun MainAppBar(
  title: String,
  actions: @Composable RowScope.() -> Unit = {},
  navigator: DestinationsNavigator? = null,
) {
  Surface(
    shadowElevation = dimensionResource(id = R.dimen._10sdp)
  ) {
    CenterAlignedTopAppBar(
      colors = TopAppBarDefaults.mediumTopAppBarColors(
        containerColor = CelestialBlue
      ),
      title = {
        Text(
          text = title,
          color = Color.White,
          fontWeight = FontWeight.SemiBold,
          fontSize = dimensionResource(id = com.intuit.ssp.R.dimen._15ssp).value.sp
        )
      },
      navigationIcon = {
        if (navigator != null) IconButton(
          onClick = {
              navigator.popBackStack()
          }) {
          Icon(
            painter = painterResource(id = com.app.twerlo.R.drawable.ic_back),
            contentDescription = null,
            tint = Color.Unspecified,
          )
        }
      },
      actions = actions
    )
  }
}