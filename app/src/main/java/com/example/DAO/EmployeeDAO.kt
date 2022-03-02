package com.example.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.Entitys.Employee
import com.example.Tools.DateConverter

@Dao
@TypeConverters(DateConverter::class)
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
    fun getEmployeeByEmail(email:String):LiveData<List<Employee>>

    @Query("select * from Employee_table where name=:name")
    fun getEmployeeByName(name:String):LiveData<List<Employee>>

}