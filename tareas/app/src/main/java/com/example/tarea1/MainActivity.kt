package com.example.tarea1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.example.tarea1.ui.theme.Tarea1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Tarea1Theme() {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.Black
                ) { innerPadding ->
                    AsciiArtDisplay(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AsciiArtDisplay(modifier: Modifier = Modifier) {
    val asciiArt = """
⣿⣿⣿⣿⣿⣿⠟⠁⠈⢻⣿⣿⣿⣿⣿⣿⣿⣿⡿⠿⣿⣿⣿⣿
⣿⣿⣿⣿⣿⡏⠂⠂⠂⠂⢙⠛⠙⠛⠻⢿⠟⠁⠂⠂⠸⣿⣿⣿
⣿⣿⣿⡿⠟⠂⠂⠂⠂⠂⠂⠂⠂⠂⠂⠂⠂⠂⠂⠂⠂⣿⣿⣿
⣿⡿⢋⣴⣤⣀⣀⣀⣀⣠⣤⣾⣷⣤⣀⠂⠂⠂⠂⠂⠂⢿⣿⣿
⣿⢁⣿⣿⣿⣿⣿⣿⣯⣽⣿⣿⣿⣿⣟⣿⣶⣦⣤⣤⣤⣦⠹⣿
⡇⣼⣿⣿⣿⣿⣿⢋⣭⠹⣿⣿⣿⣿⠟⡛⢿⣿⣿⣿⣿⣿⣇⢸
⠁⣿⣿⡟⡛⠛⢿⣄⣂⣴⣿⣿⣿⣿⡀⠉⢠⣿⣿⣿⣿⣿⣿⠂
⡇⢻⣿⣷⣿⣿⣾⣿⣿⣿⣙⠁⠛⣻⣿⣿⣣⣃⢉⢹⣿⣿⡟⢸
⣿⡌⢻⣿⣿⣿⣿⣿⣿⣿⣿⣍⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⢁⣾
⣿⡘⠦⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠟⣡⣾⣿
    """.trimIndent()

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = asciiArt,
            fontFamily = FontFamily.Monospace,
            color = Color.White
        )
    }
}

@Preview(showBackground = true)
@Composable
fun AsciiArtPreview() {
    Tarea1Theme() {
        Box(modifier = Modifier.fillMaxSize()) {
            AsciiArtDisplay()
        }
    }
}