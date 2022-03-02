package com.example.roomdatabase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.Entitys.Employee
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import java.util.*

class AddEmployeeActivity : AppCompatActivity() {

    var fullname: EditText? = null
    var idnumber: EditText? = null
    var email: EditText? = null
    var birthdate: TextView? = null
    var bu_save: Button? = null

    var selecteddate: Calendar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)

        fullname = findViewById(R.id.edittext_full_name)
        idnumber = findViewById(R.id.edittext_id)
        email = findViewById(R.id.edittext_email)
        birthdate = findViewById(R.id.edittext_birth_day)
        bu_save = findViewById(R.id.bu_addEmployee)

        birthdate!!.setOnClickListener {

            DatePickerDialog.newInstance(object : DatePickerDialog.OnDateSetListener {
                @SuppressLint("SetTextI18n")
                override fun onDateSet(
                    view: DatePickerDialog?,
                    year: Int,
                    monthOfYear: Int,
                    dayOfMonth: Int
                ) {
                    birthdate!!.text = "$dayOfMonth/$monthOfYear/$year"
                    selecteddate = Calendar.getInstance()
                    selecteddate!!.set(Calendar.YEAR, year)
                    selecteddate!!.set(Calendar.MONTH, monthOfYear)
                    selecteddate!!.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                }

            }, Calendar.getInstance()).show(supportFragmentManager, "")

        }

        bu_save!!.setOnClickListener {
            if (fullname!!.text.isEmpty() || idnumber!!.text.isEmpty() || email!!.text.isEmpty() || birthdate!!.text.isEmpty()) {
                Toast.makeText(this, "Please enter a valid data", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val intent = Intent()
            intent.putExtra(
                "EmployeeKey",
                Employee(
                    idnumber!!.text.toString().toLong(),
                    fullname!!.text.toString(),
                    email!!.text.toString(),
                    selecteddate!!.time
                )
            )

            setResult(RESULT_OK, intent)
            finish()
        }

    }
}
