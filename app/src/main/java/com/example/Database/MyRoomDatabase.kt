package com.example.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.example.Entitys.Employee
import com.example.Entitys.Salary
import androidx.room.RoomDatabase
import com.example.DAO.EmployeeDAO
import com.example.DAO.SalaryDAO

@Database(entities = [Employee::class, Salary::class], version = 1, exportSchema = false)
abstract class MyRoomDatabase : RoomDatabase() {

    abstract fun EmployeeDao(): EmployeeDAO
    abstract fun SalaryDao(): SalaryDAO

    companion object {

        @Volatile
        private var INSTANCE: MyRoomDatabase? = null
        fun getDatabase(context: Context): MyRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}