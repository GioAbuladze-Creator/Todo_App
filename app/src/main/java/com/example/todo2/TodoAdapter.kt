package com.example.todo2

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter (private val todoList:MutableList<Todo>) : RecyclerView.Adapter<TodoAdapter.ViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.todo_list_item,parent,false)
        return ViewHolder(view)
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView, isChecked: Boolean) {
        if (isChecked) {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onBindViewHolder(holder: TodoAdapter.ViewHolder, position: Int) {
        val itemsViewModel = todoList[position]
        holder.title.text=itemsViewModel.title
        holder.checked.isChecked=itemsViewModel.checked
        toggleStrikeThrough(holder.title,itemsViewModel.checked)
        holder.checked.setOnClickListener{
            toggleStrikeThrough(holder.title,holder.checked.isChecked)
            itemsViewModel.checked=!itemsViewModel.checked
        }
    }

    override fun getItemCount(): Int {
        return todoList.size
    }
    class ViewHolder (ItemView:View) : RecyclerView.ViewHolder(ItemView){
        val title = itemView.findViewById<TextView>(R.id.title)
        var checked = itemView.findViewById<CheckBox>(R.id.checkBox)
    }
}