package com.example.movies.screens

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.movies.ui.theme.Conquer
import com.example.movies.ui.theme.ConquerFont


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(
    title : String,
    onClick : ()-> Unit
) {

    TopAppBar(
        title = {
            Text(
                text = title,
                color = Color.White,
                style = TextStyle(
                    fontFamily = Conquer,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        actions = {

                Icon(
                    modifier = Modifier.clickable {
                        onClick()
                    },
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )

        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF414242),
            titleContentColor = Color.White
        )
    )
}