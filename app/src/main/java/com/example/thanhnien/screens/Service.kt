package com.example.thanhnien.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thanhnien.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ServiceScreen(
    openNewsScreen: () -> Unit,
    openDiscoverScreen: () -> Unit,
    openProfileScreen: () -> Unit
){
    Scaffold(
        Modifier.background(Color.White),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Công tác đoàn",
                        modifier = Modifier.padding(top = 30.dp),
                        fontSize = 20.sp,
                        color = Color.White
                    )
                },
                navigationIcon = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(
                            top = 30.dp,
                            start = 10.dp
                        )
                    ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier
                                .size(30.dp)
                        )
                    }
                },
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 0.dp,
                            topEnd = 0.dp,
                            bottomStart = 20.dp,
                            bottomEnd = 20.dp
                        )
                    )
                    .height(125.dp),
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFF2AB5F3)),
                actions = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(
                                top = 30.dp,
                                end = 10.dp
                            )
                    ) {
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
            )
        },
        bottomBar = {
            NavigationBar(
            ) {
                BottomAppBar(
                    containerColor = Color(0xFFECF1F3),
                )
                {
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                                  openNewsScreen()
                        },
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.baseline_bookmark_border_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(23.dp)
                                    .offset(y = (5).dp)
                            )
                        },
                        label = { Text(text = "Bảng tin",fontSize = 11.sp) }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = { /*TODO*/ },
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.outline_work_outline_24),
                                contentDescription = null,
                                tint = Color(0xFF1B4BC4),
                                modifier = Modifier
                                    .size(30.dp)
                                    .offset(y = (-2).dp)
                            )
                        },
                        label = {
                                Text(
                                    text = "Công tác đoàn",
                                    fontSize = 11.sp,
                                    color = Color(0xFF1B4BC4)
                                )
                        }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            openDiscoverScreen()
                        },
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.outline_window_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(23.dp)
                                    .offset(y = (5).dp)
                            )
                        },
                        label = {
                            Text(text = "Khám phá",fontSize = 11.sp)
                        }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            openProfileScreen()
                        },
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.baseline_person_outline_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(23.dp)
                                    .offset(y = (5).dp)
                            )
                        },
                        label = {
                            Text(text = "Cá nhân",fontSize = 11.sp)
                        }
                    )
                }
            }
        }
    ) { paddingValue ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 10.dp,
                    top = paddingValue.calculateTopPadding(),
                    end = 10.dp,
                    bottom = paddingValue.calculateBottomPadding()
                )
                .clip(RoundedCornerShape(17.dp)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

        }
    }
}

@Preview
@Composable
fun DefaultPreviewServiceScreen(){
    ServiceScreen(
        openNewsScreen = {},
        openDiscoverScreen = {},
        openProfileScreen = {}
    )
}