package com.example.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.Entitys.Employee
import com.example.Entitys.Salary
import com.example.Repository.OnValueSum
import com.example.Repository.RoomRepository
import java.util.*

class DataViewModel(application: Application):AndroidViewModel(application)  {

    private val repository = RoomRepository(application)

    fun insertEmployee(employee: Employee)
    {
        repository.insertEmployee(employee)
    }

    fun updateEmployee(employee: Employee)
    {
        repository.updateEmployee(employee)
    }

    fun deleteEmployee(employee: Employee)
    {
        repository.deleteEmployee(employee)
    }

    fun deleteEmployeeByEmail(email:String)
    {
        repository.deleteEmployeeByEmail(email)
    }

    fun getAllEmployee(): LiveData<List<Employee>>
    {
        return  repository.getAllEmployee()
    }

    fun getEmployeeByEmail(email:String)
    {
        return repository.getEmployeeByEmail(email)
    }

    fun getEmployeeByName(name:String): LiveData<List<Employee>>
    {
        return repository.getEmployeeByName(name)
    }

    fun getEmployeeByName(): LiveData<List<String>>
    {
        return repository.getEmployeeByName()
    }

    // Salary

    fun insertSalary(salary: Salary)
    {
        repository.insertSalary(salary)
    }

    fun updateSalary(salary: Salary)
    {
        repository.updateSalary(salary)
    }

    fun deleteSalary(salary: Salary)
    {
        repository.deleteSalary(salary)
    }

    fun getAllSalariesByEmployeeId(empId:Long): LiveData<List<Salary>>
    {
        return repository.getAllSalariesByEmployeeId(empId)
    }

    fun getAllSalariesByDate(from: Date, to: Date): LiveData<List<Salary>>
    {
        return repository.getAllSalariesByDate(from ,to)
    }

    fun getSalariesSum(empId: Long?, listener: OnValueSum)
    {
        val value = repository.getSalariesSum(empId ,listener)
    }


}