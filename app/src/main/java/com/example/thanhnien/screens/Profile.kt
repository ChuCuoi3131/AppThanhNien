package com.example.thanhnien.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thanhnien.R
import com.example.thanhnien.firebase.getProfileFromFirebaseByEmail
import com.example.thanhnien.firebase.updateProfileToFirebase
import com.example.thanhnien.models.Profile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, DelicateCoroutinesApi::class)
@Composable
fun ProfileScreen(
    email: String,
    fullName: String,
    openServiceScreen: (email: String, fullName: String) -> Unit,
    openDiscoverScreen: (email: String, fullName: String) -> Unit,
    backNewsScreen: () -> Unit
) {
    var profileState by remember {
        mutableStateOf(Profile("", email, fullName, "", 0, "", "", "", "", ""))
    }
    LaunchedEffect(Unit) {
        val profile = getProfileFromFirebaseByEmail(email, fullName)
        profileState = profile
    }

    Scaffold(
        Modifier.background(Color.White),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Cá nhân",
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
                            imageVector = Icons.Default.Settings,
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
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 75.dp
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = "Xin chào, ${fullName}", fontSize = 16.sp, color = Color.White)
                    Text(text = "${email}", fontSize = 14.sp, color = Color.White)
                }
            }
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
                            backNewsScreen()
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
                        label = { Text(text = "Bảng tin", fontSize = 11.sp) }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            openServiceScreen(email, fullName)
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
                            Text(
                                text = "Công tác đoàn",
                                fontSize = 11.sp,
                            )
                        }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = {
                            openDiscoverScreen(email, fullName)
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
                            Text(text = "Khám phá", fontSize = 11.sp)
                        }
                    )
                    NavigationBarItem(
                        selected = false,
                        onClick = {},
                        icon = {
                            Icon(
                                painterResource(id = R.drawable.baseline_person_outline_24),
                                contentDescription = null,
                                tint = Color(0xFF1B4BC4),
                                modifier = Modifier
                                    .size(30.dp)
                                    .offset(y = (-2).dp)
                            )
                        },
                        label = {
                            Text(
                                text = "Cá nhân",
                                fontSize = 11.sp,
                                color = Color(0xFF1B4BC4)
                            )
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.LightGray)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(15.dp)
                    .clip(shape = RoundedCornerShape(10.dp))
                    .background(color = Color.White)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var name by remember {
                    mutableStateOf(profileState.profileName)
                }
                var dob by remember {
                    mutableStateOf(profileState.profileDob)
                }
                var gender = arrayOf("Nam", "Nữ")
                var expanded by remember {
                    mutableStateOf(false)
                }
                var colordropdownmenu_indicator by remember {
                    mutableStateOf(Color(0xFF08A045))
                }
                var selectedGender by remember {
                    mutableStateOf(if (profileState.profileGender == 0) "Nam" else "Nữ")
                }
                var joinedAt by remember {
                    mutableStateOf(profileState.profileJoinedAt)
                }
                var role by remember {
                    mutableStateOf(profileState.profileRole)
                }
                var act by remember {
                    mutableStateOf(profileState.profileACT)
                }
                var ethnicity by remember {
                    mutableStateOf(profileState.profileEthnicity)
                }
                var religion by remember {
                    mutableStateOf(profileState.profileReligion)
                }
                var context = LocalContext.current
                Spacer(modifier = Modifier.height(20.dp))
                OutlinedTextField(value = name, onValueChange = {
                    name = it
                }, label = {
                    Text(text = "Họ tên")
                })
                Spacer(modifier = Modifier.height(20.dp))
                dob?.let {
                    OutlinedTextField(value = it, onValueChange = {
                        dob = it
                    }, label = {
                        Text(text = "Ngày sinh")
                    })
                }
                Spacer(modifier = Modifier.height(20.dp))
                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = {
                        expanded = !expanded
                    },
                    modifier = Modifier
                        .width(350.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedTextField(
                            value = selectedGender,
                            onValueChange = {
                                selectedGender = it
                            },
                            colors = TextFieldDefaults.colors(
                                focusedContainerColor = Color.Transparent,
                                unfocusedContainerColor = Color.Transparent,
                                focusedTextColor = Color.Black,
                                unfocusedTextColor = Color.Black,
                            ),
                            readOnly = true,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                            ),
                            placeholder = {
                                Text(text = "Giới tính")
                            },
                            modifier = Modifier
                                .border(BorderStroke(1.dp, Color.Transparent))
                                .menuAnchor(),
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                            }
                        )

                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                                colordropdownmenu_indicator = Color.Transparent
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            gender.forEach { item ->
                                DropdownMenuItem(
                                    text = { Text(text = item) },
                                    onClick = {
                                        selectedGender = item
                                        expanded = false
                                        colordropdownmenu_indicator = Color.Transparent
                                    }
                                )
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                joinedAt?.let {
                    OutlinedTextField(value = it, onValueChange = {
                        joinedAt = it
                    }, label = {
                        Text(text = "Ngày vào Đoàn")
                    })
                }
                Spacer(modifier = Modifier.height(20.dp))
                role?.let {
                    OutlinedTextField(value = it, onValueChange = {
                        role = it
                    }, label = {
                        Text(text = "Chức vụ")
                    })
                }
                Spacer(modifier = Modifier.height(20.dp))
                act?.let {
                    OutlinedTextField(value = it, onValueChange = {
                        act = it
                    }, label = {
                        Text(text = "Nơi sinh hoạt Đoàn")
                    })
                }
                Spacer(modifier = Modifier.height(20.dp))
                ethnicity?.let {
                    OutlinedTextField(value = it, onValueChange = {
                        ethnicity = it
                    }, label = {
                        Text(text = "Dân tộc")
                    })
                }
                Spacer(modifier = Modifier.height(20.dp))
                religion?.let {
                    OutlinedTextField(value = it, onValueChange = {
                        religion = it
                    }, label = {
                        Text(text = "Tôn giáo")
                    })
                }
                Spacer(modifier = Modifier.height(20.dp))
                Button(
                    onClick = {
                        CoroutineScope(Dispatchers.Main).launch {
                            var flag = updateProfileToFirebase(
                                profileState.profileID!!,
                                email,
                                name,
                                dob!!,
                                if (selectedGender == "Nam") 0 else 1,
                                joinedAt!!,
                                role!!,
                                act!!,
                                ethnicity!!,
                                religion!!,
                                context
                            )
                            if (flag) {
                                backNewsScreen()
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2AB5F3),
                        contentColor = Color.White,
                    )
                ) {
                    Text(text = "Cập nhật thông tin")
                }
                Spacer(modifier = Modifier.height(20.dp))
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreviewProfileScreen() {
    ProfileScreen(
        email = "email",
        fullName = "fullName",
        openDiscoverScreen = { email, fullName -> },
        openServiceScreen = { email, fullName -> },
        backNewsScreen = {})
}
