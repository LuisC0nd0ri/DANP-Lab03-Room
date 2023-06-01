package com.luiscv.laboratorio03_room.models

import androidx.room.Database
import androidx.room.RoomDatabase
import com.luiscv.laboratorio03_room.entities.CourseEntity
import com.luiscv.laboratorio03_room.entities.StudentCourseEntity
import com.luiscv.laboratorio03_room.entities.StudentEntity

@Database(entities = [StudentEntity::class, CourseEntity::class, StudentCourseEntity::class], version = 1)
abstract class EnrollmentDatabase : RoomDatabase() {
    abstract fun enrollmentDao(): EnrollmentDao
}