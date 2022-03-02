package com.example.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.Entitys.Employee
import com.example.roomdatabase.R

class SpinnerAdapter(val employees:List<Employee>): BaseAdapter() {
    override fun getCount(): Int {
        return employees.size
    }

    override fun getItem(position: Int): Employee {
        return employees[position]
    }

    override fun getItemId(position: Int): Long {
        return employees[position].id!!
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null)
        {
            view = LayoutInflater.from(parent!!.context).inflate(R.layout.card_spinner_item ,null ,false)
        }
        val tv = view!!.findViewById<TextView>(R.id.tv_name)
        val e:Employee = getItem(position)
        tv.text = "${e.name}"
        return view
    }
}