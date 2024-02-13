package com.app.twerlo.presentation.common

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.app.twerlo.R
import com.app.twerlo.presentation.MainActivity

@Composable
fun clearUserSessions(removeCachedUser: () -> Unit) {

  val context = LocalContext.current
  Toast.makeText(context, stringResource(id = R.string.network_unauthorized), Toast.LENGTH_LONG).show()
  removeCachedUser.invoke()
  val intent = Intent(context, MainActivity::class.java)
  intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
  context.startActivity(intent)
}