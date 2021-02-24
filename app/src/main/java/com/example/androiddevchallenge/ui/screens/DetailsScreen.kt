/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.androiddevchallenge.R
import com.example.androiddevchallenge.data.repositories.CatRepository
import com.example.androiddevchallenge.ui.theme.AndroidDevChallengeTheme

@Composable
fun DetailsScreen(id: String?, navHostController: NavHostController?) {
    val cat = when {
        (id == null) -> null
        else -> CatRepository().getById(id)
    }

    val image: Painter = painterResource(id = cat?.imageRes ?: R.drawable.ic_launcher_foreground)

    var isImageFullScreen by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 16.dp)
        ) {
            Image(
                painter = image,
                contentDescription = "image of the cat",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(150.dp)
                    .clickable { isImageFullScreen = true }
            )
            Text(
                text = cat?.name ?: "unknown cat",
                fontSize = 32.sp
            )
        }
        Text(text = cat?.description ?: "")
    }

    if (isImageFullScreen) {
        Surface(color = MaterialTheme.colors.background.copy(alpha = 0.9f)) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = image,
                    contentDescription = "image of the cat",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.padding(32.dp).fillMaxSize()
                        .clickable { isImageFullScreen = false }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    AndroidDevChallengeTheme {
        DetailsScreen("1", null)
    }
}
