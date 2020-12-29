package com.example.composedemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp

class AndroidViewComposeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_android_view_compose)

        findViewById<ComposeView>(R.id.compose_view_text).setContent {
            MaterialTheme {
                Text("Hello Compose!")
            }
        }

        findViewById<ComposeView>(R.id.compose_view_img).setContent {
            val imageResource = imageResource(id = R.drawable.header)
            val imageModifier = Modifier
                .preferredHeight(180.dp)
                .fillMaxWidth()
                .padding(16.dp)
                .clip(RoundedCornerShape(4.dp))
            MaterialTheme {
                Image(
                    bitmap = imageResource,
                    modifier = imageModifier,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}