package com.luiscv.laboratorio03_room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "course")
data class CourseEntity (

    @PrimaryKey
    val courseId: Int,
    val name: String

    )