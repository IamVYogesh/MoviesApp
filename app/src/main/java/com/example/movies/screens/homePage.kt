package com.example.movies.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.movies.apiCall.FakeViewModel
import androidx.compose.runtime.livedata.observeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import com.example.movies.ui.theme.Conquer


@Composable
fun HomePage (
    modifier: Modifier,
    viewModel: FakeViewModel =  hiltViewModel(),
    navController: NavController,
) {

    var isClicked by remember { mutableStateOf(true) }

    Scaffold(
        topBar = {
            MyTopBar(
                title = "MyMovies",
                onClick = { isClicked != isClicked }
            )
        },

        ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            var searchText by remember { mutableStateOf(TextFieldValue("")) }
            val onTextChange: (TextFieldValue) -> Unit = { searchText = it }

            val movies by viewModel.movies.observeAsState(emptyList())

            val filterMovies = movies.filter { movie ->
                movie.originalTitle.contains(searchText.text, ignoreCase = true)
            }

            var interactionSource = remember { MutableInteractionSource() }
            val isPressed by interactionSource.collectIsPressedAsState()

            val backgroundColor =
                if (isPressed) Color.Black else Color(0xFF414242) // Darker blue when pressed

            val currentPage by viewModel.currentPage.observeAsState()


            Box(
                modifier = Modifier.background(Color.Gray)
            ) {
                Column(
                    modifier = Modifier.padding(5.dp)
                ) {
                    if (isClicked) {
                        Row(
                            modifier = Modifier
                                .background(Color.White, RoundedCornerShape(15.dp))
                                .padding(horizontal = 12.dp)
                                .padding(end = 8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            BasicTextField(
                                value = searchText,
                                onValueChange = onTextChange,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(top = 15.dp, bottom = 15.dp)
                                    .wrapContentHeight(),
                                textStyle = TextStyle(fontSize = 18.sp, color = Color.Black),
                                singleLine = true,
                                decorationBox = { innerTextField ->
                                    Box(
                                        modifier = Modifier
                                            .background(Color.Transparent)
                                            .padding(start = 15.dp),
                                        contentAlignment = Alignment.CenterStart
                                    ) {
                                        if (searchText.text.isEmpty()) {
                                            Text(
                                                text = "Search...",
                                                fontSize = 18.sp,
                                                color = Color.Gray
                                            )
                                        }
                                        innerTextField()
                                    }
                                }
                            )
                        }
                    }



                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp, bottom = 50.dp),
                        content = {
                            items(filterMovies) { movie ->

                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(180.dp)
                                        .padding(5.dp),
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        AsyncImage(
                                            model = movie.posterPath,
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .clickable {
                                                    navController.navigate("details/${movie.movieId}")
                                                },
                                            contentDescription = "movie.originalTitle",
                                            contentScale = ContentScale.Crop

                                        )
                                    }
                                }
                            }

                        }
                    )


                }
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.BottomCenter,

                    ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(0.4f)
                                .shadow(52.dp)
                                .background(color = backgroundColor)
                                .clickable {
                                    viewModel.prevPage()
                                    interactionSource = interactionSource
                                },
                            contentAlignment = Alignment.Center,
                        ) {
                            Text(
                                text = ("Back"),
                                style = TextStyle(
                                    fontFamily = Conquer,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.White,
                            )
                        }

                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(0.3f)
                                .background(color = Color(0xFF414242)),
                            contentAlignment = Alignment.Center

                        ) {
                            Text(
                                text = "$currentPage",
                                fontSize = 24.sp,
                                color = Color.White
                            )
                        }

                        Box(
                            modifier = Modifier
                                .height(50.dp)
                                .fillMaxWidth(1f)
                                .shadow(12.dp)
                                .background(color = backgroundColor)
                                .clickable {
                                    viewModel.nextPage()
                                    interactionSource = interactionSource
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = ("Next"),
                                style = TextStyle(
                                    fontFamily = Conquer,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                                color = Color.White,
                            )
                        }
                    }
                }
            }
        }
    }
}