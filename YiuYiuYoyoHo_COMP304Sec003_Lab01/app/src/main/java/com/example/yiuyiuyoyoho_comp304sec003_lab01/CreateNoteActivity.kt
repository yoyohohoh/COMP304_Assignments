@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.yiuyiuyoyoho_comp304sec003_lab01

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.*
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonColors
import androidx.compose.ui.Alignment
import com.example.yiuyiuyoyoho_comp304sec003_lab01.ui.theme.YiuYiuYoyoHo_COMP304Sec003_Lab01Theme

class CreateNoteActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CreateNote(onSaveClick = { title, content -> saveButton(title, content) })
        }
    }
// Create Note Activity: Includes a save button to save the note and return to the Home Activity.
    private fun saveButton(title: String, content: String) {
        notesList.add(Note(title, content))
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

@Composable
fun CreateNote(onSaveClick: (String, String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
// Create Note Activity: Use Column to arrange input fields vertically.
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(12.dp)
        ) {
// Create Note Activity: Contains input fields for the note title and note content.
            // Title
            TextField(
                value = title,
                onValueChange = { title = it },
                placeholder = {
                    Text(
                        text = "Title",
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                )
            )
            // Truncated content preview
            TextField(
                value = content,
                onValueChange = { content = it },
                placeholder = {
                    Text(
                        text = "Write something...",
                        color = Color.Gray,
                        fontSize = 18.sp
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                textStyle = TextStyle(
                    color = Color.White,
                    fontSize = 18.sp
                ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Black,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                )
            )
// Create Note Activity: Use Button for the save action
            // Save Button
            Button(
                onClick = { onSaveClick(title, content) },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.Black,
                    containerColor = Color.White,
                ),
                modifier = Modifier
                    .width(200.dp)
                    .height(40.dp)
                    .align(Alignment.CenterHorizontally)

            ) {
                Text("Save")
            }

        }
    }
}



