package com.example.composedemo

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.AmbientContext
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.example.composedemo.ui.ComposeDemoTheme

class ComposeAndroidViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PreviewCustomView()
                }
            }
        }
    }
}

@Composable
fun CustomView() {
    val selectedItem = remember { mutableStateOf(0) }

    val context = AmbientContext.current

    val customView = remember {
        // Creates custom view
        Button(context).apply {
            // Sets up listeners for View -> Compose communication
            setOnClickListener {
                selectedItem.value += 1
            }
        }
    }

    // Adds view to Compose
    AndroidView({ customView }) { view ->
        // View's been inflated - add logic here if necessary

        // As selectedItem is read here, AndroidView will recompose
        // whenever the state changes
        // Example of Compose -> View communication
        view.text = selectedItem.value.toString()
    }
}

@Composable
fun CustomView2() {
    val context = AmbientContext.current

    val customView = remember {
        // Creates custom view
        View.inflate(context, R.layout.layout_custom_view, null)
    }

    AndroidView({ customView })
}

@Preview(showBackground = true)
@Composable
fun PreviewCustomView() {
    Column(Modifier.fillMaxSize()) {
        Text("Look at this CustomView!")
        CustomView()
        CustomView2()
    }
}