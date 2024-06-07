package com.example.thanhnien

import android.support.v4.os.IResultReceiver2.Default
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(openLoginScreen: () -> Unit) {
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
            Spacer(modifier = Modifier.height(10.dp));
            Text(text = "Đăng kí", color = Color.White, fontSize = 40.sp);
            Spacer(modifier = Modifier.height(17.dp));
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
                        .height(650.dp)
                        .width(380.dp)
                        .fillMaxWidth()
                        .clip(shape = RoundedCornerShape(12.dp))
                        .padding(10.dp, 5.dp)
                ) {
                    var email by rememberSaveable {
                        mutableStateOf("")
                    }
                    var sdt by rememberSaveable {
                        mutableStateOf("")
                    }
                    var username by rememberSaveable {
                        mutableStateOf("")
                    }
                    var password by rememberSaveable {
                        mutableStateOf("")
                    }
                    var repassword by rememberSaveable {
                        mutableStateOf("")
                    }
                    var isShowPassword by rememberSaveable {
                        mutableStateOf(false)
                    }
                    var isCheck by remember {
                        mutableStateOf(false)
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Họ và tên", color = Color.Black, fontSize = 17.sp);
                    TextFieldRegister(email, onTextChange = {
                        email = it
                    })
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Số điện thoại", color = Color.Black, fontSize = 17.sp);
                    TextFieldRegister(sdt, onTextChange = {
                        sdt = it
                    })
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Tên đăng nhập", color = Color.Black, fontSize = 17.sp);
                    TextFieldRegister(username, onTextChange = {
                        username = it
                    })
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Mật khẩu", color = Color.Black, fontSize = 17.sp);
                    TextFieldPasswordRegister(password, isShowPassword, onPasswordChange = {
                        password = it
                    }, showPasswordChange = {
                        isShowPassword = !isShowPassword
                    })
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Nhập lại mật khẩu", color = Color.Black, fontSize = 17.sp);
                    TextFieldPasswordRegister(repassword, isShowPassword, onPasswordChange = {
                        repassword = it
                    }, showPasswordChange = {
                        isShowPassword = !isShowPassword
                    })
                    Spacer(modifier = Modifier.height(30.dp))
                    CustomCheckBox("Đồng ý với các điều khoản", isCheck, onCheckBoxChange = {
                        isCheck = !isCheck
                    })
                    Spacer(modifier = Modifier.height(40.dp))
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .width(360.dp)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF2AB5F3),
                            contentColor = Color.White,
                        )
                    ) {
                        Text(text = "Đăng kí", fontSize = 20.sp)
                    };
                    Spacer(
                        modifier = Modifier.height(30.dp)
                    )
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .width(380.dp),
                    ) {
                        Text(text = "Bạn đã có tài khoản?", fontSize = 17.sp, color = Color.Black)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "ĐĂNG NHẬP NGAY",
                            fontSize = 17.sp,
                            color = Color.Blue,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            ),
                            modifier = Modifier.clickable {
                                openLoginScreen()
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TextFieldRegister(text: String, onTextChange: (String) -> Unit) {
    OutlinedTextField(
        value = text,
        onValueChange = {onTextChange},
        modifier = Modifier
            .width(370.dp)
            .height(47.dp),
        placeholder = {},
        textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
    )
}

@Composable
fun TextFieldPasswordRegister(password: String, isShowPassword: Boolean, onPasswordChange: (String) -> Unit, showPasswordChange: (Boolean) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = {onPasswordChange},
        modifier = Modifier
            .width(370.dp)
            .height(47.dp),
        placeholder = {},
        trailingIcon = {
            IconButton(onClick = {showPasswordChange}) {
                Icon(
                    painterResource(
                        id = if (isShowPassword) R.drawable.baseline_visibility_24
                        else R.drawable.baseline_visibility_off_24
                    ),
                    contentDescription = null,
                    tint = Color.Black,
//                        modifier = Modifier.background(Color.Transparent)
                )
            }
        },
        textStyle = TextStyle(color = Color.Black),
        visualTransformation = if (isShowPassword) VisualTransformation.None
        else PasswordVisualTransformation(),
    )
}

@Preview
@Composable
fun DefaultPreviewRegister() {
    RegisterScreen(openLoginScreen = {})
}

