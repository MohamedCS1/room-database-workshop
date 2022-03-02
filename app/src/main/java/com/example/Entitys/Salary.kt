package com.example.Entitys

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.Tools.DateConverter
import java.util.*

@Entity(tableName = "Salary_table", foreignKeys = [ForeignKey(entity = Employee::class, parentColumns = arrayOf("id"), childColumns = arrayOf("empId"))])
@TypeConverters(DateConverter::class)
class Salary {

    @PrimaryKey(autoGenerate = true)
    var id:Int? = null
    @NonNull
    var amount:Double? = null
    @NonNull
    var date:Date? = null
    @NonNull
    var empId:Long? = null

    constructor(id:Int? ,amount:Double? ,date:Date? ,empId:Long?)
    {
        this.id = id
        this.amount = amount
        this.date = date
        this.empId = empId
    }

    constructor()
}