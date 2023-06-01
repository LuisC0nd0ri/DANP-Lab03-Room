package com.luiscv.laboratorio03_room.entities

import androidx.room.*

@Entity(tableName = "student_course",
    primaryKeys = ["studentId", "courseId"])
data class StudentCourseEntity(
    val studentId: Int,
    val courseId: Int
)

data class CourseWithStudents(
    @Embedded val courseEntity: CourseEntity,
    @Relation(
        parentColumn = "courseId",
        entityColumn = "studentId",
        associateBy = Junction(StudentCourseEntity::class)
    )
    val students: List<StudentEntity>
)

data class StudentWithCourses(
    @Embedded val studentEntity: StudentEntity,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "courseId",
        associateBy = Junction(StudentCourseEntity::class)
    )
    val courses: List<CourseEntity>
)