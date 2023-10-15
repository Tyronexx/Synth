package com.example.synth.presentation.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.synth.R
import com.example.synth.domain.model.Article
import com.example.synth.presentation.Dimens.MediumPadding1
import com.example.synth.presentation.common.ArticlesList
import com.example.synth.presentation.common.SearchBar


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    articles: LazyPagingItems<Article>,
    navigateToSearch: () -> Unit,
    navigateToDetails: (Article) -> Unit,
) {

//    news sticker
    //        horizontal scroll list
    val titles: String by remember {
//        derivedStateOf cause we're depending on another state
        derivedStateOf {
//            check for number of articles returned and return only 10
            if (articles.itemCount > 10) {
                articles.itemSnapshotList.items.slice(
                    IntRange(
                        start = 0, endInclusive = 9
                    )
                )             //slice to only 10 items
                    .joinToString(separator = "\uD83d\uDFE5") { it.title }                    //get only the title
            } else {
                ""
            }

        }
    }

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            // TODO: background color)
            .padding(top = MediumPadding1)
            .statusBarsPadding()  //draw UI behind status bar
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo_no_background),
            contentDescription = null,
            modifier = Modifier
                .width(150.dp)
                .height(70.dp)
                .padding(horizontal = MediumPadding1)
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(MediumPadding1 - 14.dp))

        SearchBar(
            modifier = Modifier.padding(horizontal = MediumPadding1),
            text = "",
            readOnly = true,
            onValueChange = {},
            onClick = {
//                navigate to search page when clicked
                navigateToSearch()
            },
            onSearch = {},
        )

        Spacer(modifier = androidx.compose.ui.Modifier.height(MediumPadding1))

//        horizontal scroll list
        Text(
            text = titles,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = MediumPadding1)
                .basicMarquee(),
            fontSize = 12.sp,
            color = colorResource(id = R.color.placeholder)
        )

        Spacer(modifier = Modifier.height(MediumPadding1))

        ArticlesList(modifier = Modifier.padding(horizontal = MediumPadding1),
            articles = articles,
            onClick = { article ->
//                navigate to details screen
                navigateToDetails(article)
            }
        )

    }

}