package com.example.todolist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val rvTodoList= findViewById<RecyclerView>(R.id.rvTodoList)
        val btnNewItem= findViewById<Button>(R.id.btnNewItem)
        val btnDeleteItem= findViewById<Button>(R.id.btnDeleteItem)

        todoAdapter = TodoItemAdapter(mutableListOf())
        rvTodoList.adapter = todoAdapter
        rvTodoList.layoutManager = LinearLayoutManager(this)

        btnNewItem.setOnClickListener {
            val todoTitle = findViewById<EditText>(R.id.etNewTodoTitle)
            if(todoTitle.text.toString().isNotEmpty()){
                val todoItem = TodoItem(todoTitle.text.toString())
                todoAdapter.addTodoItem(todoItem)
                todoTitle.text.clear()
            }
        }

        btnDeleteItem.setOnClickListener {
            todoAdapter.deleteTodoItems()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}