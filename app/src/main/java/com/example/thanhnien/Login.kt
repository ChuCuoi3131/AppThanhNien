package com.example.thanhnien

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.thanhnien.firebase.checkLoginAuthentication
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(openRegister: () -> Unit,
                openNewsScreen:() -> Unit
                ) {

    Surface(
        color = Color.White,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
//        horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(Color(0xFF2AB5F3))
                .fillMaxSize()
                .padding(5.dp, 0.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))
            Icon(Icons.Default.ArrowBack, contentDescription = null, tint = Color.White);
            Spacer(modifier = Modifier.height(55.dp));
            Text(text = "Đăng nhập", color = Color.White, fontSize = 40.sp);
            Spacer(modifier = Modifier.height(30.dp));
            Text(
                text = "Xin hãy nhập đủ thông tin để đăng nhập",
                color = Color.White,
                fontSize = 16.sp
            );
            Spacer(modifier = Modifier.height(22.dp));
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(8.dp, 5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .height(530.dp)
                        .width(380.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(12.dp))
                        .padding(10.dp, 5.dp)
                ) {
                    var email by remember {
                        mutableStateOf("")
                    }
                    var password by remember {
                        mutableStateOf("")
                    }
                    var isShowPassword by remember {
                        mutableStateOf(false)
                    }
                    var isCheck by remember {
                        mutableStateOf(false)
                    }
                    var context = LocalContext.current
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "Email",
                        color = Color.Black,
                        fontSize = 17.sp
                    );
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                                        email = it
                                        },
                        modifier = Modifier.width(370.dp),
                        placeholder = {},
                        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "Mật khẩu", color = Color.Black, fontSize = 17.sp);
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                                        password = it
                        },
                        modifier = Modifier.width(370.dp),
                        placeholder = {},
                        trailingIcon = {
                            IconButton(onClick = {
                                isShowPassword = !isShowPassword
                            }) {
                                Icon(
                                    painterResource(
                                        id = if (isShowPassword) R.drawable.baseline_visibility_24
                                        else R.drawable.baseline_visibility_off_24
                                    ),
                                    contentDescription = null,
                                    tint = Color.Black,
                                )
                            }
                        },
                        textStyle = TextStyle(color = Color.Black, fontWeight = FontWeight.Bold),
                        visualTransformation = if (isShowPassword) VisualTransformation.None
                        else PasswordVisualTransformation(),
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .width(380.dp)
//                    verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.selectable(
                                selected = isCheck,
                                onClick = {
                                          isCheck = !isCheck
                                },
                                role = Role.Checkbox
                            )
                        ) {
                            Checkbox(
                                checked = isCheck, onCheckedChange = null,
                                colors = CheckboxDefaults.colors(
                                    checkedColor = Color(0xFF2AB5F3),
                                    uncheckedColor = Color.Black
                                )
                            )
                            Text(text = "Ghi nhớ đăng nhập", modifier = Modifier.padding(start = 3.dp), color = Color.Black)
                        }
                        Text(
                            text = "Quên mật khẩu",
                            fontSize = 15.sp,
                            color = Color.Black,
                            style = TextStyle(textDecoration = TextDecoration.Underline)
                        )
                    };
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = {
                            if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
                                CoroutineScope(Dispatchers.Main).launch {
                                    password = encodeToMD5(password)
                                    val flag = checkLoginAuthentication(email, password, context)
                                    if (flag) {
                                        openNewsScreen()
                                    }
                                }

                            } else {
                                Toast.makeText(
                                    context,
                                    "Vui lòng nhập đầy đủ thông tin!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .width(360.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2AB5F3),
                            contentColor = Color.White,
                        )
                    ) {
                        Text(text = "Đăng nhập", fontSize = 20.sp)
                    };
                    Spacer(modifier = Modifier.height(120.dp))
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .width(380.dp),
                    ) {
                        Text(
                            text = "Bạn chưa có tài khoản?",
                            fontSize = 17.sp,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "ĐĂNG KÍ NGAY", fontSize = 17.sp, color = Color.Blue,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            ),
                            modifier =  Modifier.clickable {
                                openRegister()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreviewOfLoginScreen() {
    LoginScreen(
        openRegister = {},
        openNewsScreen = {}
        )
}