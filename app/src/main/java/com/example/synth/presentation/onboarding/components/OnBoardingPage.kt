package com.example.synth.presentation.onboarding.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.synth.R
import com.example.synth.presentation.Dimens.MediumPadding1
import com.example.synth.presentation.Dimens.MediumPadding2
import com.example.synth.presentation.onboarding.Page


@Composable
fun OnBoardingPage(
    modifier: Modifier = Modifier,
    page: Page
){
    Column (
        modifier = modifier
    ){
        Image(
            modifier = Modifier
                .clip(RoundedCornerShape(bottomEnd = 200.dp, bottomStart = 200.dp))
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.65f),
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        Text(
            text = page.title,
            modifier = Modifier.padding(horizontal = MediumPadding2),
//            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Bold),
            style = TextStyle(
//                color = Color.Black,
                fontWeight = FontWeight.W400,
                fontSize = 25.sp,
                fontFamily = FontFamily(Font(R.font.spacegrotesk_bold))
            ),
            color = colorResource(id = R.color.display_small)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(
            text = page.description,
            modifier = Modifier.padding(horizontal = MediumPadding2),
//            style = MaterialTheme.typography.bodyMedium,
            style = TextStyle(
//                color = Color.Black,
                fontWeight = FontWeight.W400,
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.spacegrotesk_bold))
            ),
            color = colorResource(id = R.color.text_medium)
        )
    }
}