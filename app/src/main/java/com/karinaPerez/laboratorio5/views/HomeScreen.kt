package com.karinaPerez.laboratorio5.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.White
import com.karinaPerez.laboratorio5.ui.theme.DarkPurple
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karinaPerez.laboratorio5.data.Task
import com.karinaPerez.laboratorio5.views.components.Modal
import com.karinaPerez.laboratorio5.views.components.TodoCard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: GeneralViewModel
    //onBackClick: () -> Unit = {}
) {

    val tasks = viewModel.tasks.collectAsState()

    var openDialog = remember { mutableStateOf(false) }

    fun changeDialogState() {
        openDialog.value = !openDialog.value
    }
    Scaffold(
        topBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Blue)
                    .padding(5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = { }, colors = ButtonDefaults.buttonColors(
                        containerColor = Blue,
                        contentColor = White
                    )
                ) {
                    Text(text = "<", fontSize = 40.sp, color = Color.Black)
                }
                Spacer(Modifier.width(25.dp))
                Text("TODOS", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            }
        },
        modifier = Modifier.fillMaxSize(),
        floatingActionButton = {
            Button(
                onClick = {
                    changeDialogState()
                },
                modifier = Modifier.padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = DarkPurple,
                    contentColor = White
                )
            ) {
                Text(
                    text = "+",
                    color = White,
                    modifier = Modifier.padding(8.dp),
                    style = MaterialTheme.typography.displayMedium
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(tasks.value) { task ->
                TodoCard(
                    task = task,
                    onDelete = { id ->
                        viewModel.removeTask(id)
                    },
                    onClick = { id ->
                        viewModel.changeStatus(id)
                    }
                )
            }
        }
        if (openDialog.value)
            Modal(
                onDismiss = { changeDialogState() },
                onConfirm = { newCard ->
                    val task = Task(
                        id = tasks.value.size + 1,
                        title = newCard.value.title,
                        description = newCard.value.description,
                        endDate = newCard.value.endDate,
                        isCompleted = newCard.value.isCompleted
                    )
                    viewModel.addTask(task)
                    changeDialogState()
                }
            )
    }
}