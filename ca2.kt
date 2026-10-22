package com.example.finalproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class ca2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    StockDashboard()
                }
            }
        }
    }
}

// Cold Flow builder for stock price updates
fun stockPriceFlow(
    initialPrice: Double = 150.0
): Flow<Double> = flow {

    var price = initialPrice

    while (true) {
        delay(1000L)

        val fluctuation = Random.nextDouble(-2.0, 2.0)
        price += fluctuation

        emit(price)
    }
}

// UI layer
@Composable
fun StockDashboard() {

    val stockPrice by stockPriceFlow()
        .collectAsStateWithLifecycle(
            initialValue = 150.0
        )

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Stock Trading Dashboard",
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Stock Price",
            fontSize = 20.sp
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "₹${"%.2f".format(stockPrice)}",
            fontSize = 32.sp,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Updates every 1 second",
            fontSize = 14.sp
        )
    }
}
