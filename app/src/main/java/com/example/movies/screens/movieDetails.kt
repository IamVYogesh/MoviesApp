package com.example.movies.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.movies.firebase.FirebaseRepository
import com.example.movies.R
import com.example.movies.room.RoomViewmodel
import com.example.movies.apiCall.Movie
import com.example.movies.firebase.FirebaseViewmodel
import com.example.movies.ui.theme.Conquer
import com.example.movies.ui.theme.Pirata


@Composable
fun MovieDetails(
    movie: Movie,
    modifier: Modifier = Modifier,
    viewModel: RoomViewmodel,
    firebaseViewmodel : FirebaseViewmodel

) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.4f)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = movie.posterPath,
                contentDescription = "Title",
                contentScale = ContentScale.Crop
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.1f)
                .background(Color.Black)
                .padding(5.dp)
        ) {
            Box (
                modifier = Modifier
                    .matchParentSize()
                    .background(Color(0xFF147676)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = movie.originalTitle,
                    fontFamily = Pirata,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.3f)
                .background(Color.Gray)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                content = {
                    item {

                        Row(modifier = Modifier.padding(5.dp)) {
                            Text(
                                text = "Rating: ",
                                fontSize = 18.sp,
                                fontFamily = Conquer,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${movie.voteAverage}",
                                fontSize = 16.sp,
                            )
                        }
                        Details("Overview", movie.overview)
                        Details("Release Date", movie.releaseDate)

                        Row(modifier = Modifier.padding(5.dp)) {
                            Text(
                                text = "Download details: ",
                                fontSize = 18.sp,
                                fontFamily = Conquer,
                                fontWeight = FontWeight.Bold
                            )

                            var expanded by remember { mutableStateOf(false) }
                            val context = LocalContext.current

                            Box{
                                Image(
                                    painter = painterResource(id = R.drawable.download),
                                    contentDescription = "Menu Icon",
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clickable { expanded = true }
                                )


                                DropdownMenu(
                                    modifier = Modifier.background(Color.LightGray),
                                    expanded = expanded,
                                    onDismissRequest = { expanded = false }
                                ) {
                                    DropdownMenuItem(
                                        text = { Text("Download to Room") },
                                        onClick = {
                                            viewModel.storeMovie(movie, context)
                                        }
                                    )

                                    DropdownMenuItem(
                                        text = { Text("Download to FireBase") },
                                        onClick = {
                                            firebaseViewmodel.storeToFirebase(movie, context)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.2f)
                .background(Color.Black)
        ) {
            LazyRow(
                modifier = Modifier.padding(5.dp)
            ) {
                items(movie.casts) { cast->
                    Card(
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(130.dp)
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                         Column(

                         ){
                             Box(
                                 modifier = Modifier
                                     .fillMaxSize()
                                     .weight(0.8f)
                             ){
                                 AsyncImage(
                                     model = cast.profilePath,
                                     contentDescription = "Image",
                                     contentScale = ContentScale.Crop
                                 )
                             }

                             Box(
                                 modifier = Modifier
                                     .fillMaxWidth()
                                     .background(Color(0xFF147676))
                                     .weight(0.2f),
                                 contentAlignment = Alignment.Center
                             ){
                                 Text(
                                     text = cast.name,
                                     color = Color.Black
                                 )
                             }
                         }
                    }
                }
            }
        }
    }

}


@Composable
fun Details (
    heading : String,
    text : String
){
    Row(modifier = Modifier.padding(5.dp)) {
        Text(
            text = heading,
            fontSize = 18.sp,
            fontFamily = Conquer,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = text,
            fontSize = 16.sp,
        )
    }
}