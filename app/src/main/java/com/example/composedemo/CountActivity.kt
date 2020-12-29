package com.example.composedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.KeyboardArrowDown
import androidx.compose.material.icons.sharp.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.example.composedemo.ui.ComposeDemoTheme
import com.example.composedemo.viewmodel.CountViewModel

class CountActivity : AppCompatActivity() {

    private val viewModel by lazy { CountViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    PreviewCounter()
                    ExpandingCard(
                        "Machine Learning with Android 11: What’s new",
                        "This blog demonstrates how you can get started with on-device ML with tools or plugins specifically launched with Android 11. If you have earlier worked with ML in Android, you will explore easier ways to integrate your ML applications with your Android apps. If you have not worked with ML in Android earlier, this could be a starting point for you to do so and start super powering your Android app with Machine Learning. In this blog, I majorly demonstrate the two biggest updates with Android 11: ML Model Binding Plugin and the new ML Kit. All of the example apps code as we discuss below is present in this GitHub repo."
                    )
                }
            }
        }
    }
}

@Composable
fun CounterInner() {
    val count = remember { mutableStateOf(0) }
    Button(onClick = { count.value += 1 }) {
        Text(text = "Count: ${count.value}")
    }
}

@Composable
fun Counter(countViewModel: CountViewModel = viewModel()) {

    val observeAsState = countViewModel.count.observeAsState(0)
    val count = observeAsState.value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonCount(count = count, onCountChanged = { countViewModel.onCountChanged(it) })
    }
}

@Composable
fun ButtonCount(
    /* state */ count: Int,
    /* event */ onCountChanged: (Int) -> Unit
) {
    Button(
        colors = ButtonConstants.defaultButtonColors(backgroundColor = if (count > 5) Color.Green else Color.White),
        onClick = { onCountChanged(count + 1) },
    ) {
        Text(text = "I've been clicked $count times")
    }
}

@Preview
@Composable
fun PreviewCounter() {
    CounterInner()
}

@Composable
fun ExpandingCard(title: String, body: String) {

    val expand = remember { mutableStateOf(false) }

    // describe the card for the current state of expanded
    Card {
        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .animateContentSize() // automatically animate size when it changes
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
        ) {
            Text(text = title)

            // content of the card depends on the current value of expanded
            if (expand.value) {
                Text(text = body, Modifier.padding(top = 8.dp))
                // change expanded in response to click events
                IconButton(
                    onClick = { expand.value = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Sharp.KeyboardArrowUp)
                }
            } else {
                // change expanded in response to click events
                IconButton(
                    onClick = { expand.value = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(Icons.Sharp.KeyboardArrowDown)
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewExpandingCard() {
    ExpandingCard(
        "Machine Learning with Android 11: What’s new",
        "This blog demonstrates how you can get started with on-device ML with tools or plugins specifically launched with Android 11. If you have earlier worked with ML in Android, you will explore easier ways to integrate your ML applications with your Android apps. If you have not worked with ML in Android earlier, this could be a starting point for you to do so and start super powering your Android app with Machine Learning. In this blog, I majorly demonstrate the two biggest updates with Android 11: ML Model Binding Plugin and the new ML Kit. All of the example apps code as we discuss below is present in this GitHub repo."
    )
}
