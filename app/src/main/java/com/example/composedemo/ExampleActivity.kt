package com.example.composedemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composedemo.ui.ComposeDemoTheme
import com.example.composedemo.ui.typography

class ExampleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    Greeting("Android")
                    NewsStory()
//                    TestColumnRow()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!", modifier = Modifier.padding(20.dp).fillMaxWidth())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeDemoTheme {
        Greeting("Android")
    }
}

@Composable
fun NewsStory() {
    val imageResource = imageResource(id = R.drawable.header)
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        val imageModifier = Modifier
            .preferredHeight(180.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))

        Image(bitmap = imageResource, modifier = imageModifier, contentScale = ContentScale.Crop)

        Spacer(modifier = Modifier.preferredHeight(16.dp))

        Text(
            "A day wandering through the sandhills " +
                    "in Shark Fin Cove, and a few of the " +
                    "sights I saw",
            style = typography.h6,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Text("Davenport, California", style = typography.body2)
        Text("December 2018", style = typography.body2)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    NewsStory()
}

@Composable
fun TestColumnRow() {
    Column(
        modifier = Modifier.fillMaxHeight().background(Color.Yellow),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = "java")
        Text(text = "android")
        Text(text = "python")
    }

    Row(
        modifier = Modifier.fillMaxWidth().background(Color.LightGray),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = "java")
        Text(text = "android")
        Text(text = "python")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview3() {
    TestColumnRow()
}