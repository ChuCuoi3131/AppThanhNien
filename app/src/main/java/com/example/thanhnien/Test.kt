package com.example.thanhnien

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun Screen() {
    val images = listOf(
        R.drawable.slider1,
        R.drawable.slider2,
        R.drawable.slider3
    )
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

        }
//        MyCard()
    }
}

//@Composable
//fun MyCard() {
//    Box(modifier = Modifier.fillMaxSize())
//    {
//        Card(
//            modifier = Modifier
//                .width(300.dp)
//                .height(270.dp),
////            shape = CutCornerShape(20.dp)
//            elevation = CardDefaults.cardElevation(10.dp),
//            border = BorderStroke(3.dp, Color.LightGray),
//            colors = CardDefaults.cardColors(
//                containerColor = Color.White
//            )
//        ) {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize()
//            ) {
//                Image(painterResource(id = R.drawable.slider3), contentDescription = null)
//                Text(text = "Title", fontWeight = FontWeight.Bold)
//                Text(
//                    text = "industry. Lorem Ipsum has been the industry's standard dummy texttu",
//                    maxLines = 2,
//                    overflow = TextOverflow.Ellipsis,
//                    color = Color.Gray
//                )
//            }
//        }
//    }
//}

@Preview
@Composable
fun DefaultPreviewNewsScreen() {
    Screen()
}