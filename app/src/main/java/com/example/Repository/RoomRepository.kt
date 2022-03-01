package com.example.Repository

import android.content.Context
import androidx.lifecycle.LiveData

import com.example.Database.MyRoomDatabase
import com.example.Entitys.Employee
import com.example.Entitys.Salary
import java.util.*

class RoomRepository(val context: Context) {

    val db = MyRoomDatabase.getDatabase(context)
    val employeeDAO = db.EmployeeDao()
    val salaryDAO = db.SalaryDao()

    fun insertEmployee(employee: Employee)
    {
        db.queryExecutor.execute{
            employeeDAO.insertEmployee(employee)
        }
    }

    fun updateEmployee(employee: Employee)
    {
        db.queryExecutor.execute {
            employeeDAO.updateEmployee(employee)
        }
    }

    fun deleteEmployee(employee: Employee)
    {
        db.queryExecutor.execute {
            employeeDAO.deleteEmployee(employee)
        }
    }

    fun deleteEmployeeByEmail(email:String)
    {
        db.queryExecutor.execute {
            employeeDAO.deleteEmployeeByEmail(email)
        }
    }

    fun getAllEmployee(): LiveData<List<Employee>>
    {
        return  employeeDAO.getAllEmployee()
    }

    fun getEmployeeByEmail(email:String)
    {
        return employeeDAO.getEmployeeByEmail(email)
    }

    fun getEmployeeByName(name:String): LiveData<List<Employee>>
    {
        return employeeDAO.getEmployeeByName(name)
    }

    // Salary

    fun insertSalary(salary: Salary)
    {
        db.queryExecutor.execute {
            salaryDAO.insertSalary(salary)
        }
    }

    fun updateSalary(salary: Salary)
    {
        db.queryExecutor.execute {
            salaryDAO.updateSalary(salary)
        }
    }

    fun deleteSalary(salary: Salary)
    {
        db.queryExecutor.execute {
            salaryDAO.deleteSalary(salary)
        }
    }

    fun getAllSalariesByEmployeeId(empId:Long):LiveData<List<Salary>>
    {
        return salaryDAO.getAllSalariesByEmployeeId(empId)
    }

    fun getAllSalariesByDate(from: Date, to: Date):LiveData<List<Salary>>
    {
        return getAllSalariesByDate(from ,to)
    }
}