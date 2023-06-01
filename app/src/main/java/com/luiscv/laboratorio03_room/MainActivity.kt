package com.luiscv.laboratorio03_room

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.room.Room
import com.luiscv.laboratorio03_room.entities.CourseEntity
import com.luiscv.laboratorio03_room.entities.StudentCourseEntity
import com.luiscv.laboratorio03_room.entities.StudentEntity
import com.luiscv.laboratorio03_room.models.EnrollmentDao
import com.luiscv.laboratorio03_room.models.EnrollmentDatabase
import com.luiscv.laboratorio03_room.operations.mostrarCursoConEstudiantes
import com.luiscv.laboratorio03_room.ui.theme.Laboratorio03_RoomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio03_RoomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    MyApplication()

                }
            }
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun MyApplication() {

    //Jetpack Room============================
    val context = LocalContext.current
    val db = remember {
        Room.databaseBuilder(
            context,
            EnrollmentDatabase::class.java, "enrollment-db"
        ).build()
    }
    val dao = db.enrollmentDao()
    //===============================================

    val scope = rememberCoroutineScope()
    var showSelectDialog: MutableState<Boolean> =
        remember { mutableStateOf(false) }

    //para la visualizacion de las incripciones
    val data = mutableStateListOf<List<String>>()

    Column(
        modifier = Modifier
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val fillDataOnClick = {
            fillTables(dao, scope)
        }

        val courseWithStudentsOnClick: () -> Unit = {
            scope.launch {

                dao.getAllStudents().forEach{
                    Log.d("Estudiante: ", it.toString())
                }

                dao.getCourseWithStudents().forEach{
                    Log.d("Curso con estudiantes: ", it.toString())
                    //song.map es para ver solo los nombres de los estudiantes
                    var listStudents = it.students.map{ it.fullname }.toString()

                    data += listOf(it.courseEntity.name, listStudents.substring(1,listStudents.length-1) )
                }
                showSelectDialog.value = true

            }
        }

        mostrarCursoConEstudiantes(showSelectDialog, data)

        Text(
            text = "Inscripciones",
            fontSize = 24.sp,
            modifier = Modifier
                .padding(bottom = 20.dp)
        )

        Button(onClick = fillDataOnClick ,
                modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Generar inscripciones")
        }

        Button(onClick = courseWithStudentsOnClick ,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Visualizar inscripciones")
        }


    }

}

fun fillTables(dao: EnrollmentDao, scope: CoroutineScope) {

    val student1 = StudentEntity(0,"Luis Condori")
    val student2 = StudentEntity(1, "Juan Lopez")
    val student3 = StudentEntity(2, "Alberto Jimenez")


    val course1 = CourseEntity(0, "Matematicas")
    val course2 = CourseEntity(1, "Algebra")

    val student_course_1 = StudentCourseEntity(
        student1.studentId, course1.courseId)

    val student_course_2 = StudentCourseEntity(
        student2.studentId, course2.courseId)

    val student_course_3 = StudentCourseEntity(
        student3.studentId, course1.courseId)

    val student_course_4 = StudentCourseEntity(
        student3.studentId, course2.courseId)


    scope.launch {
        dao.insertStudent(student1)
        dao.insertStudent(student2)
        dao.insertStudent(student3)

        dao.insertCourse(course1)
        dao.insertCourse(course2)

        dao.enrollStudentInCourse(student_course_1)
        dao.enrollStudentInCourse(student_course_2)
        dao.enrollStudentInCourse(student_course_3)
        dao.enrollStudentInCourse(student_course_4)
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Laboratorio03_RoomTheme {
        MyApplication()
    }
}