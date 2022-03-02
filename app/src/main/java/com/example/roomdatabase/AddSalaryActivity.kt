package com.example.roomdatabase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.Entitys.Employee
import com.example.ViewModel.DataViewModel
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.*
import kotlin.collections.ArrayList

class AddSalaryActivity : AppCompatActivity() {

    var bu_date:Button? = null
    var selecteddate:Calendar? = null
    var viewmodel:DataViewModel? = null
    var spinner_employees: Spinner? = null

    var bu_save_salary:Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_salary)

        viewmodel = ViewModelProvider(this)[DataViewModel::class.java]

        bu_date = findViewById(R.id.bu_amount_date)

        bu_date!!.setOnClickListener {

            DatePickerDialog.newInstance(object: DatePickerDialog.OnDateSetListener{
                @SuppressLint("SetTextI18n")
                override fun onDateSet(
                    view: DatePickerDialog?,
                    year: Int,
                    monthOfYear: Int,
                    dayOfMonth: Int
                ) {
                    selecteddate = Calendar.getInstance()
                    selecteddate!!.set(Calendar.YEAR ,year)
                    selecteddate!!.set(Calendar.MONTH ,monthOfYear)
                    selecteddate!!.set(Calendar.DAY_OF_MONTH ,dayOfMonth)
                    finish()
                }

            }, Calendar.getInstance()).showsDialog

        }

        spinner_employees = findViewById(R.id.spinner_employees)

        val employees_name = viewmodel!!.getEmployeeByName() as ArrayList<String>

        val spinner_adapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, employees_name)

        spinner_employees!!.adapter = spinner_adapter

        viewmodel!!.getAllEmployee().observe(this ,object :Observer<List<Employee>>{
            override fun onChanged(t: List<Employee>?) {
                employees_name.add(t as String)
            }
        })

        bu_save_salary = findViewById(R.id.bu_save_salary)

        bu_save_salary!!.setOnClickListener {

        }
    }
}