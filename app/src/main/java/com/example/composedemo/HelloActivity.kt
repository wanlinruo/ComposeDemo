package com.example.composedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.composedemo.ui.ComposeDemoTheme
import com.example.composedemo.viewmodel.HelloViewModel

class HelloActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PreviewHelloScreen()
                }
            }
        }
    }
}

@Composable
fun HelloScreen(helloViewModel: HelloViewModel = viewModel()) {
    // by default, viewModel() follows the Lifecycle as the Activity or Fragment
    // that calls HelloScreen(). This lifecycle can be modified by callers of HelloScreen.

    // name is the _current_ value of [helloViewModel.name]
    // with an initial value of ""
    val observeAsState = helloViewModel.name.observeAsState("")

    Column {
        Text(text = observeAsState.value)
        TextField(
            value = observeAsState.value,
            onValueChange = { helloViewModel.onNameChanged(it) },
            label = { Text("Name") }
        )
    }
}


@Preview
@Composable
fun PreviewHelloScreen() {
    Column(
        modifier = Modifier.fillMaxSize().background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HelloScreen()
    }
}