package com.example.Adapters

import android.app.Application
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.Entitys.Employee
import com.example.Repository.OnValueSum
import com.example.ViewModel.DataViewModel

class EmployeesSalariesAdapter(application:Application):RecyclerView.Adapter<EmployeesSalariesAdapter.EmployeesSalariesViewHolder>() {

    val viewmodel = DataViewModel(application)
    val listemployees = listOf<Employee>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesSalariesViewHolder {
        return EmployeesSalariesViewHolder(LinearLayout.inflate(parent.context ,com.example.roomdatabase.R.layout.card_salary_employee ,null))
    }

    override fun onBindViewHolder(holder: EmployeesSalariesViewHolder, position: Int) {
        holder.name.text = listemployees[position].name
        viewmodel.getSalariesSum(listemployees[position].id ,object :OnValueSum{
            override fun onValueChange(sum: Double) {
                holder.salary.text = "$sum"
            }

        })
    }

    override fun getItemCount(): Int {
        return listemployees.size
    }

    class EmployeesSalariesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val name = itemView.findViewById<TextView>(com.example.roomdatabase.R.id.name_employee)
        val salary = itemView.findViewById<TextView>(com.example.roomdatabase.R.id.salary_employee)
    }
}