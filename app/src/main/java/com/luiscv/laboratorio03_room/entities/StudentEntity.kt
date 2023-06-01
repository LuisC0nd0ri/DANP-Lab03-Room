package com.luiscv.laboratorio03_room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "student")
data class StudentEntity (
    @PrimaryKey
    val studentId: Int,
    val fullname: String
    )