package com.example.roomdatabase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.Adapters.EmployeesSalariesAdapter
import com.example.Adapters.SpinnerAdapter
import com.example.Entitys.Employee
import com.example.Entitys.Salary
import com.example.ViewModel.DataViewModel
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.*

class AddSalaryActivity : AppCompatActivity() {

    var bu_date_amount:Button? = null
    var selecteddate:Calendar? = null
    var viewmodel:DataViewModel? = null
    var spinner_employees: Spinner? = null

    var bu_save_salary:Button? = null

    var et_amount:EditText? = null

    var adapter: EmployeesSalariesAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_salary)

        viewmodel = ViewModelProvider(this)[DataViewModel::class.java]

        bu_date_amount = findViewById(R.id.bu_amount_date)

        bu_date_amount!!.setOnClickListener {

            DatePickerDialog.newInstance(object: DatePickerDialog.OnDateSetListener{
                @SuppressLint("SetTextI18n")
                override fun onDateSet(
                    view: DatePickerDialog?,
                    year: Int,
                    monthOfYear: Int,
                    dayOfMonth: Int
                ) {
                    bu_date_amount!!.text = "$dayOfMonth/$monthOfYear/$year"
                    selecteddate = Calendar.getInstance()
                    selecteddate!!.set(Calendar.YEAR ,year)
                    selecteddate!!.set(Calendar.MONTH ,monthOfYear)
                    selecteddate!!.set(Calendar.DAY_OF_MONTH ,dayOfMonth)
                }

            }, Calendar.getInstance()).show(supportFragmentManager ,"")

        }

        spinner_employees = findViewById(R.id.spinner_employees)

        val lsit_employees = listOf<Employee>()

        viewmodel!!.getAllEmployee()

        val spinner_adapter = SpinnerAdapter(lsit_employees)

        spinner_employees!!.adapter = spinner_adapter

        viewmodel!!.getAllEmployee().observe(this ,object :Observer<List<Employee>>{
            override fun onChanged(t: List<Employee>?) {
                spinner_adapter.SetList(t!!)
            }
        })

        bu_save_salary = findViewById(R.id.bu_save_salary)

        et_amount = findViewById(R.id.edittext_amount)

        bu_save_salary!!.setOnClickListener {
            if (et_amount!!.text.isEmpty() || selecteddate == null)
            {
                Toast.makeText(this ,"enter a valid data" ,Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewmodel!!.insertSalary(Salary(et_amount!!.text.toString().toDouble() ,selecteddate!!.time ,spinner_adapter.getItemId()))
            val intent = Intent()
            setResult(11, intent)
            finish()
        }
    }
}