package com.example.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(foreignKeys = [ForeignKey(entity = Employee::class, parentColumns = arrayOf("id"), childColumns = arrayOf("empId"))])
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