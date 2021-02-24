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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.data.models.Cat
import com.example.androiddevchallenge.data.repositories.CatRepository
import com.example.androiddevchallenge.ui.theme.AndroidDevChallengeTheme

@Composable
fun ListScreen(navHostController: NavHostController?) {
    Scaffold(
        floatingActionButton = {
            ListFab(
                onClick = {
                    val randomId = CatRepository().getAll().shuffled()[0].id
                    navHostController?.navigate("Details?id=$randomId")
                }
            )
        }
    ) {
        ListContent(
            onClick = { clickedId ->
                navHostController?.navigate("Details?id=$clickedId")
            }
        )
    }
}

@Composable
private fun ListFab(onClick: () -> Unit) {
    FloatingActionButton(onClick = onClick) {
        Icon(
            imageVector = Icons.Default.Shuffle,
            contentDescription = "open a random entry"
        )
    }
}

@Composable
private fun ListContent(onClick: (id: String) -> Unit, modifier: Modifier = Modifier) {
    val items = CatRepository().getAll()

    LazyColumn(modifier = modifier) {
        items(items = items) { item ->
            ListCard(
                cat = item,
                onClick = { onClick(item.id) }
            )
        }
    }
}

@Composable
private fun ListCard(cat: Cat, onClick: () -> Unit) {
    val image: Painter = painterResource(id = cat.imageRes)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = image,
                contentDescription = "image of the cat",
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .width(50.dp)
            )
            Text(
                text = cat.name,
                fontSize = 20.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    AndroidDevChallengeTheme {
        ListScreen(null)
    }
}
