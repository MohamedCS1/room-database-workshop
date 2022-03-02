package com.example.Adapters

import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.Entitys.Employee
import com.example.Repository.OnValueSum
import com.example.ViewModel.DataViewModel
import com.example.roomdatabase.R

class EmployeesSalariesAdapter(listemployees:List<Employee> ,viewModel: DataViewModel):RecyclerView.Adapter<EmployeesSalariesAdapter.EmployeesSalariesViewHolder>() {

    val viewmodel = viewModel
    private var listemployees = listemployees

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeesSalariesViewHolder {
        return EmployeesSalariesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_salary_employee ,parent ,false))
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

    @SuppressLint("NotifyDataSetChanged")
    fun setList(listemployee: List<Employee>)
    {
        listemployees = listemployee
        notifyDataSetChanged()
    }

    class EmployeesSalariesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
    {
        val name = itemView.findViewById<TextView>(com.example.roomdatabase.R.id.name_employee)
        val salary = itemView.findViewById<TextView>(com.example.roomdatabase.R.id.salary_employee)
    }
}