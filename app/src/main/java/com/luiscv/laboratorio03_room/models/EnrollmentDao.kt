package com.luiscv.laboratorio03_room.models

import androidx.room.*
import com.luiscv.laboratorio03_room.entities.*

@Dao
interface EnrollmentDao {
    @Query("SELECT * FROM student")
    suspend fun getAllStudents(): List<StudentEntity>

    @Query("SELECT * FROM course")
    suspend fun getAllCourses(): List<CourseEntity>
/*
    @Query("SELECT * FROM student_course WHERE studentId = :studentId")
    fun getCoursesForStudent(studentId: Int): List<CourseEntity>

    @Query("SELECT * FROM student_course WHERE courseId = :courseId")
    fun getStudentsInCourse(courseId: Int): List<StudentEntity>
*/
    @Transaction
    @Query("SELECT * FROM course")
    suspend fun getCourseWithStudents(): List<CourseWithStudents>

    @Transaction
    @Query("SELECT * FROM student")
    suspend fun getStudentWithCourses(): List<StudentWithCourses>


    @Insert
    suspend fun insertStudent(student: StudentEntity)

    @Insert
    suspend fun insertCourse(courseEntity: CourseEntity)

    @Insert
    suspend fun enrollStudentInCourse(student_course: StudentCourseEntity)

    @Delete
    suspend fun dropCourse(student_course: StudentCourseEntity)
}
