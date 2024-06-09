package com.example.thanhnien.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.thanhnien.R
import com.example.thanhnien.firebase.checkLoginAuthentication
import com.example.thanhnien.firebase.encodeToMD5
import com.example.thanhnien.firebase.getGenreFromFirebase
import com.example.thanhnien.firebase.getNewsFromFirebase
import com.example.thanhnien.models.Genre
import com.example.thanhnien.models.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(
    openServiceScreen : () -> Unit,
    openDiscoverScreen: () -> Unit,
    openProfileScreen: () -> Unit
) {
    val images = listOf(
        R.drawable.slider1,
        R.drawable.slider2,
        R.drawable.slider3
    )

    var genreListState by remember { mutableStateOf<List<Genre>>(emptyList()) }
    var newsListState by remember { mutableStateOf<List<News>>(emptyList()) }

    LaunchedEffect(Unit) {
        val genreList = getGenreFromFirebase()
        genreListState = genreList
        val newsList = getNewsFromFirebase(0)
        newsListState = newsList
    }

    Scaffold(
        Modifier.background(White),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Bảng tin",
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
                            tint = White,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                },
            )
            Box {
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 75.dp,
                            start = 20.dp
                        ),
                    horizontalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    itemsIndexed(
                        items = genreListState
                    ) { _, item ->
                        Text(
                            text = item.genreName,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            fontSize = 15.sp,
                            color = White
                        )
                    }
                }
            }
        },
        bottomBar = {
            NavigationBar(
            ) {
                BottomAppBar(
                    containerColor = Color(0xFFECF1F3),
                    modifier = Modifier
                        .height(80.dp)
                )
                {
                    NavigationBarItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.baseline_bookmark_border_24),
                                contentDescription = null,
                                tint = Color(0xFF1B4BC4),
                                modifier = Modifier
                                    .size(30.dp)
                                    .offset(y = (-2).dp)
                            )
                        },
                        label = {
                            Text(
                                text = "Bảng tin",
                                fontSize = 11.sp,
                                color = Color(0xFF1B4BC4)
                                )
                        }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                                  openServiceScreen()
                        },
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.outline_work_outline_24),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(23.dp)
                                    .offset(y = (5).dp)
                            )
                        },
                        label = {
                            Text(text = "Công tác đoàn",fontSize = 11.sp)
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
                            Text(text = "Khám phá",fontSize = 11.sp,)
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
                            Text(text = "Cá nhân",fontSize = 11.sp,)
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
//                    .background(Yellow)
                    .padding(3.dp),
                contentPadding = PaddingValues(horizontal = 1.dp, vertical = 4.dp)
            ) {
                item() {
                    ImageSlider(images = images)
                }
                itemsIndexed(
                    items = newsListState
                ) { _, item ->
                    MyCard(item)
                }
            }
        }
    }
}

@Composable
fun ImageSliderItem(imageRes: Int) {
    Image(
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.FillBounds,
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .clip(RoundedCornerShape(15.dp))
    )
}

@Composable
fun ImageSlider(images: List<Int>) {
    val currentIndex = remember {
        mutableStateOf(0)
    }

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            currentIndex.value = (currentIndex.value + 1) % images.size
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(235.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .weight(1f)
        ) {
            ImageSliderItem(imageRes = images[currentIndex.value])
        }
    }
}

@Composable
fun MyCard(news: News) {
    Box(modifier = Modifier.fillMaxSize())
    {
        Card(
            modifier = Modifier
                .width(450.dp)
                .height(380.dp)
                .padding(bottom = 10.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            border = BorderStroke(3.dp, Color.LightGray),
            colors = CardDefaults.cardColors(
                containerColor = White
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Image(
                    painter = rememberAsyncImagePainter(news.newsImgUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(20.dp))
                )
                Text(
                    text = news.newsTitle,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(
                        top = 15.dp,
                        start = 15.dp,
                        end = 15.dp,
                        bottom = 10.dp,
                        )
                )
                Text(
                    text = news.newsDesc,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Gray,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 15.dp, bottom = 10.dp,end = 15.dp)
                )
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 15.dp, top = 10.dp)
                ){
                    Icon(
                        Icons.Default.DateRange,
                        contentDescription = null,
                        modifier = Modifier
                            .size(17.dp),
                        tint = Color(0xFF1B4BC4)
                        )
                    Text(
                        text = news.newsCreatedAt,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier.padding(start = 15.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun DefaultPreviewScreen() {
    NewsScreen(
        openDiscoverScreen = {},
        openServiceScreen = {},
        openProfileScreen = {}
    )
}