package com.app.twerlo.presentation.common

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.app.twerlo.presentation.theme.AntiFlashWhite
import com.app.twerlo.presentation.theme.Black
import com.app.twerlo.presentation.theme.Gray80
import com.app.twerlo.presentation.theme.Madder
import com.intuit.sdp.R
import com.intuit.sdp.R.dimen._2sdp
import com.intuit.sdp.R.dimen._3sdp
import com.intuit.ssp.R.dimen._11ssp
import com.intuit.ssp.R.dimen._12ssp
import com.intuit.ssp.R.dimen._8ssp

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
  singleine: Boolean = true,
  minlines: Int = 1,
  maxLines: Int = 1,
  enabled: Boolean = true,
  icon: Int,
  isError: Boolean = false,
  disableFontValue: Boolean = false
) {

  OutlinedTextField(
    modifier = modifier,
    shape = RoundedCornerShape(dimensionResource(id = R.dimen._6sdp)),
    singleLine = singleine,
    minLines = minlines,
    enabled = enabled,
    maxLines = maxLines,
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

    leadingIcon = {
      Icon(
        painter = painterResource(id = icon),
        tint = Color.Unspecified,
        contentDescription = null,
        modifier = Modifier.offset(
          y = dimensionResource(
            id = if (!singleine) R.dimen._minus20sdp else R
              .dimen._1sdp
          )
        )
      )

    },
    textStyle = textFieldStyle(disableFontValue),
  )
}

@Composable
fun OutLineTextInputWithoutLeadingIcon(
  value: String,
  @StringRes placeholder: Int,
  errorMessage: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  maxLength: Int = Int.MAX_VALUE,
  keyboardType: KeyboardType = KeyboardType.Text,
  colors: TextFieldColors = textFieldColors(),
  imeAction: ImeAction = ImeAction.Default,
  singleine: Boolean = true,
  minlines: Int = 1,
  maxLines: Int = 1,
  enabled: Boolean = true,
  isError: Boolean = false,
  disableFontValue: Boolean = false
) {

  OutlinedTextField(
    modifier = modifier,
    shape = RoundedCornerShape(dimensionResource(id = R.dimen._6sdp)),
    singleLine = singleine,
    minLines = minlines,
    enabled = enabled,
    maxLines = maxLines,
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
    textStyle = textFieldStyle(disableFontValue),
  )
}

@Composable
fun InputTextField(
  value: String,
  @StringRes title: Int,
  @StringRes placeholder: Int,
  errorMessage: String,
  onValueChange: (String) -> Unit,
  modifier: Modifier = Modifier,
  maxLength: Int = Int.MAX_VALUE,
  keyboardType: KeyboardType = KeyboardType.Text,
  imeAction: ImeAction = ImeAction.Default
) {
  BasicTextField(
    modifier = modifier
      .background(color = Color.White)
      .padding(
        vertical = dimensionResource(id = _3sdp),
        horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._6sdp)
      )
      .horizontalScroll(rememberScrollState()),
    value = value,
    onValueChange = {
      if (it.length <= maxLength) onValueChange.invoke(it)
    },
    singleLine = true,
    decorationBox = { innerTextField ->
      Column {
        Text(
          text = stringResource(id = title),
          style = TextStyle(
            color = Gray80,
            fontWeight = FontWeight.W400,
            fontSize = dimensionResource(id = _11ssp).value.sp
          )
        )

        Spacer(modifier = Modifier.height(dimensionResource(id = _2sdp)))

        Box {
          if (value.isEmpty()) Text(
            text = stringResource(id = placeholder),
            style = TextStyle(
              color = AntiFlashWhite,
              fontWeight = FontWeight.W400,
              fontSize = dimensionResource(id = _11ssp).value.sp
            )
          )

          innerTextField()
        }

        Text(
          text = errorMessage,
          style = TextStyle(
            color = Madder,
            fontWeight = FontWeight.Thin,
            fontSize = dimensionResource(id = _8ssp).value.sp
          )
        )
      }
    },
    textStyle = TextStyle(
      color = Black,
      fontWeight = FontWeight.W500,
      fontSize = dimensionResource(id = _12ssp).value.sp
    ),
    keyboardOptions = KeyboardOptions(
      keyboardType = keyboardType,
      imeAction = imeAction
    )
  )
}

@Composable
fun DropDownInputTextField(
  value: String,
  @StringRes title: Int,
  @StringRes placeholder: Int,
  errorMessage: String,
  onValueChange: (String) -> Unit,
  keyboardType: KeyboardType = KeyboardType.Text,
  imeAction: ImeAction = ImeAction.Default,
  onClick: () -> Unit,

  ) {
  BasicTextField(
    modifier = Modifier
      .fillMaxWidth(1f)
      .background(color = Color.White)
      .padding(
        vertical = dimensionResource(id = _3sdp),
        horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._6sdp)
      )
      .horizontalScroll(rememberScrollState())
      .clickable {
        onClick.invoke()
      },
    value = value,
    onValueChange = {
      onValueChange.invoke(it)
    },
    singleLine = true,
    readOnly = true,
    enabled = false,
    decorationBox = { innerTextField ->
      Column {
        Text(
          text = stringResource(id = title),
          style = TextStyle(
            color = Gray80,
            fontWeight = FontWeight.W400,
            fontSize = dimensionResource(id = _11ssp).value.sp
          )
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = _2sdp)))

        Box {
          if (value.isEmpty()) Text(
            text = stringResource(id = placeholder),
            style = TextStyle(
              color = AntiFlashWhite,
              fontWeight = FontWeight.W400,
              fontSize = dimensionResource(id = _11ssp).value.sp
            )
          )
          innerTextField()
        }

        Text(
          text = errorMessage,
          style = TextStyle(
            color = Madder,
            fontWeight = FontWeight.Thin,
            fontSize = dimensionResource(id = _8ssp).value.sp
          )
        )
      }
    },
    textStyle = TextStyle(
      color = Black,
      fontWeight = FontWeight.W500,
      fontSize = dimensionResource(id = _12ssp).value.sp
    ),
    keyboardOptions = KeyboardOptions(
      keyboardType = keyboardType,
      imeAction = imeAction
    )
  )
}




