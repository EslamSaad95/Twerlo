package com.app.twerlo.presentation.common.view

import androidx.annotation.StringRes
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.intuit.sdp.R

@Composable
fun OutLineTextInput(
  value: String,
  @StringRes placeholder: Int,
  errorMessage: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  maxLength: Int = Int.MAX_VALUE,
  keyboardType: KeyboardType = KeyboardType.Text,
  colors: TextFieldColors = textFieldColors(),
  imeAction: ImeAction = ImeAction.Default,
  isError: Boolean = false,
) {

  OutlinedTextField(
    modifier = modifier,
    shape = RoundedCornerShape(dimensionResource(id = R.dimen._6sdp)),
    singleLine = true,
    enabled = true,
    isError = isError,
    value = value,
    onValueChange = {
      if (it.length <= maxLength) onValueChange.invoke(it)
    },
    placeholder = {
      Text(
        text = stringResource(id = placeholder),
        style = placeholderStyle()
      )
    },
    supportingText = {
      if (errorMessage.isNotEmpty()) {
        Text(
          text = errorMessage,
          color = MaterialTheme.colorScheme.error,
        )
      }
    },
    keyboardOptions = KeyboardOptions(
      keyboardType = keyboardType,
      imeAction = imeAction
    ),
    colors = colors,
    textStyle = textFieldStyle(),
  )
}

@Composable
fun OutlinePasswordTextField(
  value: String,
  @StringRes placeholder: Int,
  errorMessage: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  passwordVisible: MutableState<Boolean>,
  keyboardType: KeyboardType = KeyboardType.Text,
  imeAction: ImeAction = ImeAction.Default,
  enabled: Boolean = true,
  colors: TextFieldColors = textFieldColors(),
  isError: Boolean = false,
) {
  OutlinedTextField(
    modifier = modifier.horizontalScroll(rememberScrollState()),
    shape = RoundedCornerShape(dimensionResource(id = R.dimen._6sdp)),
    singleLine = true,
    value = value,
    isError = isError,
    enabled = enabled,
    onValueChange = {
      onValueChange.invoke(it)
    },
    placeholder = {
      Text(
        text = stringResource(id = placeholder),
        style = placeholderStyle()
      )
    },
    supportingText = {
      if (errorMessage.isNotEmpty()) {
        Text(
          text = errorMessage,
          color = MaterialTheme.colorScheme.error,
        )
      }
    },
    keyboardOptions = KeyboardOptions(
      keyboardType = keyboardType,
      imeAction = imeAction
    ),
    colors = colors,
    textStyle = textFieldStyle(),
    visualTransformation = if (passwordVisible.value) VisualTransformation.None
    else PasswordVisualTransformation(),
    trailingIcon = {
      if (enabled) {
        IconButton(onClick = { passwordVisible.value = !passwordVisible.value }) {
          Icon(
            painter = painterResource(
              id = if (passwordVisible.value) com.app.twerlo.R.drawable.ic_visibile else
                com.app.twerlo.R.drawable.ic_visibile_off
            ),
            tint = Color.Unspecified,
            contentDescription = ""
          )
        }
      }
    }
  )
}







