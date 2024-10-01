@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.yiuyiuyoyoho_comp304sec003_lab01

import android.os.Bundle
import android.content.Intent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.yiuyiuyoyoho_comp304sec003_lab01.ui.theme.YiuYiuYoyoHo_COMP304Sec003_Lab01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Home(
                notes = notesList,
                onFabClick = { navigateToCreateNote() },
                onNoteClick = { note -> navigateToViewEditNoteActivity(note)},
            )
        }
    }

    private fun navigateToCreateNote() {
        val intent = Intent(this, CreateNoteActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToViewEditNoteActivity(note: Note) {
        val intent = Intent(this, ViewEditNoteActivity::class.java).apply {
            putExtra("noteIndex", notesList.indexOf(note))
            putExtra("noteTitle", note.title)
            putExtra("noteContent", note.content)
        }
        startActivity(intent)
    }

}

@Composable
fun Home(notes: List<Note>, onFabClick: () -> Unit, onNoteClick: (Note) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Black,
                    titleContentColor = Color.White
                ),
                title = {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "QuickNotes",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp
                        )
                    }
                }
            )
        },
// Home Activity: Includes a Floating Action Button (FAB) to navigate to the Create Note Activity.
        floatingActionButton = {
            FloatingActionButton(
                onClick = onFabClick,
                containerColor = Color.White
                ) {
                Text(
                    text = "+",
                    fontSize = 24.sp
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color.Black) // Set the background color to black
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                DisplayNotes(notes, onNoteClick)
            }
        }
    }
}
// Home Activity: Displays a list of notes using a LazyColumn.
@Composable
fun DisplayNotes(notes: List<Note>, onNoteClick: (Note) -> Unit) {
    LazyColumn {
        items(notes) { note ->
            NoteCard(note = note, onClick = { onNoteClick(note)})
        }
    }
}

// Home Activity: Each note item displays a title and a truncated content preview.
// Home Activity: Use Card for individual note items.
@Composable
fun NoteCard(note: Note, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable(onClick = onClick)
            .fillMaxWidth()
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.DarkGray
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            // Title
            Text(
                text = note.title,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            // Truncated content preview
            Text(
                text = note.content,
                style = MaterialTheme.typography.titleSmall,
                color = Color.LightGray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

