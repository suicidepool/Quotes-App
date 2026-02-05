package com.oms.quotify.viewModel

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.oms.quotify.model.QuoteModel
import com.oms.quotify.R
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @param:ApplicationContext val context: Context
): ViewModel() {
    private var quoteList: Array<QuoteModel>
    private var index = 0
    var currentQuote by mutableStateOf(QuoteModel("",""))
    var shareQuoteEvent by mutableStateOf<String?>(null)

    init {
        quoteList = loadQuotes()
        currentQuote = quoteList[index]
    }

    private fun loadQuotes(): Array<QuoteModel>{
        val inputStream = context.resources.openRawResource(R.raw.quotes)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, Array<QuoteModel>::class.java)
    }

    fun nextQuote(){
        index = (index+1) % quoteList.size
        currentQuote = quoteList[index]
    }

    fun prevQuote(){
        index = if(index - 1 < 0) quoteList.size - 1 else index - 1
        currentQuote = quoteList[index]
    }

    fun onShareClicked() {
        shareQuoteEvent =
            "\"${currentQuote.quote}\" - ${currentQuote.author}"
    }

    fun onShareHandled() {
        shareQuoteEvent = null
    }
}