package com.example.composedemo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ListItem
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
import com.example.composedemo.bean.RouterBean
import com.example.composedemo.ui.ComposeDemoTheme
import com.example.composedemo.ui.typography

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeDemoTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    LazyColumnSample()
                }
            }
        }
    }

    @Composable
    fun LazyColumnSample() {

        val listData = mutableListOf<RouterBean>().apply {
            add(RouterBean("基础例子", "demo://activity_example"))
            add(RouterBean("结合ViewModel", "demo://activity_hello"))
            add(RouterBean("计数器", "demo://activity_count"))
            add(RouterBean("Compose结合原生View", "demo://activity_compose_android_view"))
            add(RouterBean("原生View结合Compose", "demo://activity_android_view_compose"))
        }

        LazyColumn(content = {
            //列表内容
            items(listData) {
                Box(
                    modifier = Modifier
                        .preferredHeight(100.dp)
                        .fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = {
                        val uri = Uri.parse(it.router)
                        val intent = Intent(Intent.ACTION_VIEW, uri)
                        startActivity(intent)
                    }) {
                        Text(text = it.name)
                    }
                }
            }
        })
    }

    @Preview
    @Composable
    fun PreviewLazyColumnSample() {
        LazyColumnSample()
    }
}