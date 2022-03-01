package com.example.DAO

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.Entitys.Employee

interface EmployeeDAO {
    @Insert
    fun insertEmployee(employee:Employee)

    @Update
    fun updateEmployee(employee:Employee)

    @Delete
    fun deleteEmployee(employee:Employee)

    @Query("delete from Employee_table where email=:email")
    fun deleteEmployeeByEmail(email:String)

    @Query("Select * from Employee_table")
    fun getAllEmployee():LiveData<List<Employee>>

    @Query("select * from Employee_table where email=:email")
    fun getEmployeeByEmail(email:String)

    @Query("select * from Employee_table where name=:name")
    fun getEmployeeByName(name:String):LiveData<List<Employee>>

}