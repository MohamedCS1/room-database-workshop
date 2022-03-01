package com.example.database

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Employee_table")
class Employee {

    @PrimaryKey
    @NonNull
    var id:Long? = null
    @NonNull
    var name:String? = null
    @NonNull
    var email:String? = null
    @NonNull
    var birthdate:Date? = null

    constructor(id:Long? ,name:String? ,email:String? ,birthdate:Date?)
    {
        this.id = id
        this.name = name
        this.email = email
        this.birthdate = birthdate
    }

    constructor()
}