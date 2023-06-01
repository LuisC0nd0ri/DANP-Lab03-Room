package com.luiscv.laboratorio03_room.operations

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.AlertDialog
import androidx.compose.material.TabRowDefaults.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun mostrarCursoConEstudiantes(
    showSelectDialog: MutableState<Boolean>,
    data: SnapshotStateList<List<String>>
){

    if(showSelectDialog.value) {

        AlertDialog(
            onDismissRequest = { /*TODO*/ showSelectDialog.value = false },
            confirmButton = {
                TextButton(onClick = { /*TODO*/
                    showSelectDialog.value = false
                }) {
                    Text(text = "Ok")
                }
            },
            dismissButton = {},
            title = { Text(text = "Visualizar Inscripciones") },
            text = {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .requiredHeight(300.dp)
                ) {
                    Text(text = "Ver tabla", modifier = Modifier.padding(bottom = 10.dp))

                    Divider(
                        color = Color.Black,
                        thickness = 1.dp,
                        modifier = Modifier.fillMaxWidth()
                    )

                    LazyColumn() {
                        itemsIndexed(data) { rowIndex, rowData ->

                            Column {
                                rowData.forEach { cellData ->
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(8.dp)
                                    ) {
                                        Text(
                                            text = cellData,
                                            modifier = Modifier.weight(1f)
                                        )
                                    }
                                }
                            }
                            Divider(color = Color.Black, thickness = 1.dp)

                            /*
                            Row {
                                rowData.forEach { cellData ->
                                    Text(
                                        text = cellData,
                                        modifier = Modifier.padding(8.dp)
                                    )
                                }
                            }*/

                        }
                    }


                }
            }
        )

    }

}