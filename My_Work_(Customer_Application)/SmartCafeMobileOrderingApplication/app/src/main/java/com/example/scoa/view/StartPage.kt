package com.example.scoa.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StartPage(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onBrowseMenuClick: () -> Unit
) {

    Column(

        Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text (
            "Liber-tea Café",
            modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 100.dp),
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFFFFFFFF)
        )

        Button(onClick = onLoginClick) { Text("Login", fontSize = 30.sp) }

        Button(onClick = onRegisterClick, modifier = Modifier.padding(0.dp, 30.dp)) { Text("Register", fontSize = 30.sp) }

        Button(onClick = onBrowseMenuClick) { Text("Browse Menu", fontSize = 30.sp) }
    }
}