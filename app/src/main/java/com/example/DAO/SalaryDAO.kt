package com.example.DAO

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.Entitys.Salary
import com.example.Tools.DateConverter
import java.util.*

@Dao
@TypeConverters(DateConverter::class)
interface SalaryDAO {

    @Insert
    fun insertSalary(salary: Salary)

    @Update
    fun updateSalary(salary: Salary)

    @Delete
    fun deleteSalary(salary: Salary)

    @Query("select * from Salary_table where empId=:empId")
    fun getAllSalariesByEmployeeId(empId:Long):LiveData<List<Salary>>

    @Query("select * from Salary_table where date>=:from AND date<=:from")
    fun getAllSalariesByDate(from:Date ,to:Date):LiveData<List<Salary>>

    @Query("select sum(amount) from Salary_table where empId=:empid")
    fun getSalariesSum(empid: Long?):Double
}