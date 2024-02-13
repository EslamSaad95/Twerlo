package com.app.twerlo.presentation.productDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import coil.compose.AsyncImage
import com.app.twerlo.domain.entity.ProductsEntity
import com.app.twerlo.presentation.theme.Cerulean
import com.app.twerlo.presentation.theme.Silver
import com.intuit.sdp.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun ProductDetailsScreen() {
}

@Destination
@Composable
fun ProductDetailsContent(productDetailsObj: ProductsEntity) {
  Scaffold { innerPadding ->

    Column(
      modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(rememberScrollState())
        .padding(horizontal = dimensionResource(id = com.intuit.sdp.R.dimen._10sdp))
        .padding(
          top = innerPadding.calculateTopPadding(),
          bottom = innerPadding.calculateBottomPadding()
        )
    ) {
      AsyncImage(
        modifier = Modifier
          .height(dimensionResource(id = R.dimen._120sdp)),
        model = productDetailsObj.image,
        contentScale = ContentScale.FillBounds,
        contentDescription = null,
        placeholder = painterResource(com.app.twerlo.R.drawable.ic_launcher_foreground)
      )
      Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._10sdp)))
      Text(text = productDetailsObj.title, color = Color.Black, fontWeight = FontWeight.Bold)
      Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._10sdp)))
      Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Text(
          text = productDetailsObj.category,
          color = Color.White,
          fontWeight = FontWeight.Light,
          textAlign = TextAlign.Center,
          modifier = Modifier
            .background(
              color = Cerulean,
              shape = RoundedCornerShape(dimensionResource(id = R.dimen._9sdp))
            )
            .padding(
              dimensionResource(id = com.intuit.sdp.R.dimen._5sdp),

              )
        )
        Modifier.weight(1f)
        Text(text = productDetailsObj.price.toString(), color = Color.Red, fontWeight = FontWeight.SemiBold)
      }
      Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._10sdp)))
      Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
          painter = painterResource(id = com.app.twerlo.R.drawable.ic_star),
          contentDescription = null,
          modifier = Modifier.size(dimensionResource(id = R.dimen._20sdp)),
          tint = Color.Unspecified,
        )
        Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen._10sdp)))
        Text(
          text = "${productDetailsObj.rating.rate} (${productDetailsObj.rating.count})", color = Color
            .Black,
          fontWeight =
          FontWeight.Medium
        )

      }
      Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen._10sdp)))
      Text(
        text = productDetailsObj.description, color = Silver,
        fontWeight = FontWeight.Normal
      )

    }
  }
}