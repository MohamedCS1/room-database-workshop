package com.example.roomdatabase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.Adapters.EmployeesSalariesAdapter
import com.example.Entitys.Employee
import com.example.ViewModel.DataViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    var viewModel:DataViewModel? = null
    var main_rv:RecyclerView? = null
    var adapter:EmployeesSalariesAdapter? = null

    var buAddEmployee:FloatingActionButton? = null
    var activityresultlancher:ActivityResultLauncher<Intent>? = null

    var bu_activity_add_salary:ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[DataViewModel::class.java]

        val listOfEmployee = listOf<Employee>()

        main_rv = findViewById(R.id.rv_main)
        adapter = EmployeesSalariesAdapter(listOfEmployee ,viewModel!!)

        main_rv!!.adapter = adapter
        main_rv!!.layoutManager = LinearLayoutManager(applicationContext)

        viewModel!!.getAllEmployee().observe(this ,object :Observer<List<Employee>>
        {
            override fun onChanged(t: List<Employee>?) {
                adapter!!.setList(t!!)
            }

        })

        activityresultlancher = registerForActivityResult(ActivityResultContracts.StartActivityForResult() ,object:ActivityResultCallback<ActivityResult>{
            override fun onActivityResult(result: ActivityResult?) {
                if (result!!.resultCode == RESULT_OK && result.data != null)
                {
                    val employee = result.data!!.getSerializableExtra("EmployeeKey")
                    viewModel!!.insertEmployee(employee as Employee)
                }

            }

        })

        buAddEmployee = findViewById(R.id.floatingButtonAddEmployee)

        buAddEmployee!!.setOnClickListener {
            val intent = Intent(this ,AddEmployeeActivity::class.java)
            activityresultlancher!!.launch(intent)
        }

        bu_activity_add_salary = findViewById(R.id.bu_activity_addsalary)

        bu_activity_add_salary!!.setOnClickListener {
            startActivity(Intent(this ,AddSalaryActivity::class.java))
        }
    }

}