package com.example.synth.presentation.common

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.synth.R
import com.example.synth.domain.model.Article
import com.example.synth.domain.model.Source
import com.example.synth.presentation.Dimens.ArticleCardSize
import com.example.synth.presentation.Dimens.ExtraSmallPadding
import com.example.synth.presentation.Dimens.ExtraSmallPadding2
import com.example.synth.presentation.Dimens.MediumPadding1
import com.example.synth.presentation.Dimens.SmallIconSize
import com.example.synth.ui.theme.SynthTheme


@Composable
fun ArticleCard(
    modifier: Modifier = Modifier,
    article: Article,
    onClick: () -> Unit
) {
    val context = LocalContext.current

    Row (
        modifier = modifier
            .clickable { onClick() }.padding(15.dp)
    ){
        AsyncImage(
            modifier = Modifier
                .size(ArticleCardSize)
                .clip(MaterialTheme.shapes.medium),
            model = ImageRequest.Builder(context).data(article.urlToImage).build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )

        Spacer(modifier = Modifier.width(MediumPadding1))

        Column (
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = ExtraSmallPadding)
                .height(ArticleCardSize)
        ){
            Text(
                text = article.title,
                style = TextStyle(
                    color = Color.Black,
                    fontWeight = FontWeight.W500,
                    fontSize = 19.sp,
                    fontFamily = FontFamily(Font(R.font.spacegrotesk_bold))
                ),
//                style = MaterialTheme.typography.bodyMedium,
//                color = colorResource(
//                    id = R.color.text_title
//                ),
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = article.source.name,
//                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    style = TextStyle(
//                        color = Color.Black,
                        fontWeight = FontWeight.W300,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.spacegrotesk_semibold))
                    ),
                    color = colorResource(id = R.color.display_small),
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Icon(
                    painter = painterResource(id = R.drawable.ic_time),
                    contentDescription = null,
                    modifier = Modifier.size(SmallIconSize),
                    tint = colorResource(id = R.color.input_background)
                )
                Spacer(modifier = Modifier.width(ExtraSmallPadding2))
                Text(
                    text = article.publishedAt,
                    style = TextStyle(
//                        color = Color.Black,
                        fontWeight = FontWeight.W300,
                        fontSize = 13.sp,
                        fontFamily = FontFamily(Font(R.font.spacegrotesk_semibold))
                    ),
//                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    color = colorResource(id = R.color.display_small),
                )
            }
        }
    }

}




@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun ArticleCardPreview() {
    SynthTheme {
        ArticleCard(article = Article(
            author = "",
            content = "",
            description = "",
            publishedAt = "2 hours",
            source = Source(id = "", name = "BBC"),
            title = "Her train broke down, Her phone died, And then she met her saver in a",
            url = "",
            urlToImage = ""
        )
        ) {

        }
    }
}