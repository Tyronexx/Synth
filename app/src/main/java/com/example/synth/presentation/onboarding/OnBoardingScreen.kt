package com.example.synth.presentation.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.synth.presentation.Dimens.MediumPadding2
import com.example.synth.presentation.Dimens.PageIndicatorWidth
import com.example.synth.presentation.common.NewsButton
import com.example.synth.presentation.common.NewsTextButton
import com.example.synth.presentation.onboarding.components.OnBoardingPage
import com.example.synth.presentation.onboarding.components.PageIndicator
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
//    call the event instead of calling entire viewModel
    event: (OnboardingEvent) -> Unit,
){
    Column (
        modifier = Modifier
            .fillMaxSize()
//            .background(color = MaterialTheme.colorScheme.primary)
    ){
        val pagerState = rememberPagerState (initialPage = 0){
            pages.size
        }

//        button configuration for each page
        val buttonState = remember {
            derivedStateOf {
                when(pagerState.currentPage){
                    0 -> listOf("", "Next")
                    1 -> listOf("Back", "Next")
                    2 -> listOf("Back", "Get Started")
                    else -> listOf("","")
                }
            }
        }

        HorizontalPager(state = pagerState) { index->
            OnBoardingPage(page = pages[index])
        }
        Spacer(modifier = Modifier.weight(1f))
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = MediumPadding2)
                .navigationBarsPadding(),     //helps us draw behind the navigation bar
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically

        ) {
            PageIndicator(
                modifier = Modifier.width(PageIndicatorWidth),
                pageSize = pages.size,
                selectedPage = pagerState.currentPage
            )


            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                val scope = rememberCoroutineScope()

//            check that we arent on first page,(dont show button on first page cause value is empty)
                if (buttonState.value[0].isNotEmpty()) {
                    NewsTextButton(
                        text = buttonState.value[0],
                        onClick = {
//                        Go to previous page
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage - 1)
                            }
                        }
                    )
                }

                NewsButton(
                    text = buttonState.value[1],
                    onClick = {

                        scope.launch {
//                        check what page we are on before determining action
//                        if we are on last page navigate to home screen
//                        if we are on second or first page, go to next page

                            if (pagerState.currentPage == 2) {
                                event(OnboardingEvent.SaveAppEntry)                            } else {
                                //Go to next page
                                pagerState.animateScrollToPage(
                                    page = pagerState.currentPage + 1
                                )
                            }

                        }
                    }
                )
            }
        }
        Spacer(modifier = Modifier.weight(0.5f))
    }
}