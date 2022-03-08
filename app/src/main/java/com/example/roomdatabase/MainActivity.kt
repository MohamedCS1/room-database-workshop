package com.example.roomdatabase

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
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
                Toast.makeText(this@MainActivity ,"Observed" ,Toast.LENGTH_SHORT).show()
                adapter!!.setList(t!!)
            }

        })



        activityresultlancher = registerForActivityResult(ActivityResultContracts.StartActivityForResult() ,object:ActivityResultCallback<ActivityResult>{
            @SuppressLint("NotifyDataSetChanged")
            override fun onActivityResult(result: ActivityResult?) {
                if (result!!.resultCode == RESULT_OK)
                {
                    val employee = result.data!!.getSerializableExtra("EmployeeKey") as Employee
                    viewModel!!.insertEmployee(employee)

                }
                else if (result.resultCode == 11)
                {
                    adapter!!.notifyDataSetChanged()
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
            val intent = Intent(this ,AddSalaryActivity::class.java)
            activityresultlancher!!.launch(intent)
        }
    }

}