package com.example.todo2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.main)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView = binding.todoList
        val task = binding.task
        val addTaskBtn = binding.addTaskBtn
        val deleteDoneBtn = binding.deleteDoneBtn

        recyclerView.layoutManager = LinearLayoutManager(this)

        //list of tasks
        val data = mutableListOf<Todo>()

        //connect tasks to adapter
        val adapter = TodoAdapter(data)
        recyclerView.adapter = adapter

        fun addTask() {
            if (task.text.isNotEmpty()) {
                data.add(Todo(task.text.toString(), false))
                adapter.notifyItemInserted(data.size-1)
                task.text.clear()
            }
        }

        fun deleteDone() {
//            for (i in data.size-1 downTo 0) {
//                if (data[i].checked) {
//                    data.removeAt(i)
//                    adapter.notifyItemRemoved(i)
//                }
//            }
            data.removeAll { todo -> todo.checked }
            adapter.notifyDataSetChanged()
        }

        addTaskBtn.setOnClickListener { addTask() }
        deleteDoneBtn.setOnClickListener { deleteDone() }


    }
}