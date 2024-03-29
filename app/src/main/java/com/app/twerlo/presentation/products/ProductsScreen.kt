package com.app.twerlo.presentation.products

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.app.twerlo.R
import com.app.twerlo.domain.entity.ProductsEntity
import com.app.twerlo.presentation.common.DataState
import com.app.twerlo.presentation.common.authentication.ClearUserSessions
import com.app.twerlo.presentation.common.cast
import com.app.twerlo.presentation.common.view.ErrorView
import com.app.twerlo.presentation.common.view.LoadingDialog
import com.app.twerlo.presentation.common.view.MainAppBar
import com.app.twerlo.presentation.destinations.ProductDetailsScreenDestination
import com.app.twerlo.presentation.theme.Cerulean
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ProductsScreen(
  viewModel: ProductsViewModel = hiltViewModel(),
  navigator: DestinationsNavigator? = null,
) {
  LaunchedEffect(Unit) {
    viewModel.getProducts()
  }

  val state by viewModel.state.collectAsState()
  val restartApp by viewModel.restartAppState.collectAsState()
  var data by remember { mutableStateOf<List<ProductsEntity>?>(null) }
  if (restartApp)
    ClearUserSessions()

  when (state) {
    is DataState.Idle -> {}
    is DataState.Loading -> {
      LoadingDialog()
    }

    is DataState.Success<*> -> data = state.cast<DataState.Success<List<ProductsEntity>>>().result
    is DataState.Error -> {
      val error = state.cast<DataState.Error>().error.asString()
      ErrorView(message = error) { viewModel.getProducts() }
    }
  }

  data?.let { ProductsScreenContent(it, navigator) }
}

@Composable
fun ProductsScreenContent(
  products: List<ProductsEntity>,
  navigator: DestinationsNavigator?
) {
  Scaffold(
    topBar = {
      MainAppBar(
        title = stringResource(id = R.string.products_title),
        navigator = navigator
      )
    }) { innerPadding ->
    LazyColumn(
      modifier = Modifier.padding(
        top = innerPadding.calculateTopPadding(), bottom =
        dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)
      )
    ) {
      itemsIndexed(products) { _, productItem ->
        ProductItem(product = productItem, navigator)
      }
    }
  }
}

@Composable
fun ProductItem(product: ProductsEntity, navigator: DestinationsNavigator?) {
  Row(modifier = Modifier
    .fillMaxWidth()
    .clickable { navigator?.navigate(ProductDetailsScreenDestination(product.id)) }
    .padding(
      vertical = dimensionResource(id = com.intuit.sdp.R.dimen._6sdp),
      horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)
    )) {
    AsyncImage(
      modifier = Modifier
        .size(dimensionResource(id = com.intuit.sdp.R.dimen._100sdp)),
      model = product.image,
      contentScale = ContentScale.FillBounds,
      contentDescription = null,
      placeholder = painterResource(R.drawable.ic_launcher_foreground)
    )
    Spacer(modifier = Modifier.width(dimensionResource(id = com.intuit.sdp.R.dimen._10sdp)))
    Column(modifier = Modifier.fillMaxWidth()) {
      Text(text = product.title, color = Color.Black, fontWeight = FontWeight.Bold)
      Spacer(modifier = Modifier.height(dimensionResource(id = com.intuit.sdp.R.dimen._5sdp)))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = product.category,
          color = Color.White,
          fontWeight = FontWeight.Light,
          textAlign = TextAlign.Center,
          modifier = Modifier
            .background(
              color = Cerulean,
              shape = RoundedCornerShape(dimensionResource(id = com.intuit.sdp.R.dimen._9sdp))
            )
            .padding(
              dimensionResource(id = com.intuit.sdp.R.dimen._7sdp),

              )
        )
        Modifier.weight(1f)
        Text(text = "${product.price}$", color = Color.Red)
      }
    }
  }
}