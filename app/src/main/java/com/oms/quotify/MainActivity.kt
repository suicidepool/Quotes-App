package com.oms.quotify

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelProvider
import com.oms.quotify.ui.component.BottomBar
import com.oms.quotify.ui.component.QuoteCard
import com.oms.quotify.ui.component.Title
import com.oms.quotify.ui.theme.QuotifyTheme
import com.oms.quotify.viewModel.MainViewModel
import com.oms.quotify.viewModel.MainViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuotifyTheme {
                App(
                    context = this
                )
            }
        }
    }
}

//@Preview
@Composable
private fun App(
    modifier: Modifier = Modifier,
    context: Context,
    mainViewModel: MainViewModel = hiltViewModel()
){
    val shareText = mainViewModel.shareQuoteEvent

    shareText?.let {
        LaunchedEffect(it) {
            val intent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_TEXT, it)
            }
            context.startActivity(
                Intent.createChooser(intent, "Share Quote via")
            )
            mainViewModel.onShareHandled()
        }
    }
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF0C308B),
                        Color(0xFF8C0AC4)
                    )
                )
            )
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 20.dp
                )
                .statusBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Title()
            QuoteCard(
                quoteIcon = Icons.Default.Info,
                shareButtonIcon = Icons.Default.Share,
                quote = mainViewModel.currentQuote.quote,
                writer = mainViewModel.currentQuote.author,
                onShare = mainViewModel::onShareClicked
            )
            BottomBar(
             onNextClick = mainViewModel::nextQuote,
                onPreviousClick = mainViewModel::prevQuote
            )
        }
    }
}