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


        addTaskBtn.setOnClickListener {
            if (task.text.isNotEmpty()) {
                adapter.addTask(task.text.toString())
                task.text.clear()
            }
        }

        deleteDoneBtn.setOnClickListener {
            adapter.deleteDone()
        }


    }
}