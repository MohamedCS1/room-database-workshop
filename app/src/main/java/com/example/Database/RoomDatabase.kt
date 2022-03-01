package com.example.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.Entitys.Employee
import com.example.Entitys.Salary
import androidx.room.RoomDatabase
import com.example.DAO.EmployeeDAO
import com.example.DAO.SalaryDAO

@Database(entities = arrayOf(Employee::class ,Salary::class), version = 1, exportSchema = false)
public abstract class RoomDatabase : RoomDatabase() {

    abstract fun EmployeeDao(): EmployeeDAO
    abstract fun SalaryDao(): SalaryDAO

    companion object {
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getDatabase(context: Context): RoomDatabase {

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDatabase::class.java,
                    "employees_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}