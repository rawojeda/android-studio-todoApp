package com.example.todolist

import android.graphics.Paint
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoItemAdapter
    (private val todoItems: MutableList<TodoItem>)
    : RecyclerView.Adapter<TodoItemAdapter.TodoViewHolder>(){

    class TodoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun addTodoItem( todoItem: TodoItem) {
        todoItems.add(todoItem);
        notifyItemInserted(todoItems.size-1);
    }

    fun deleteTodoItems() {
        todoItems.removeAll {todoItem: TodoItem ->  todoItem.isSelected}
        notifyDataSetChanged()
    }
    private fun toggleTextLinedView(tvTodoItemTitle: TextView, checkBoxSelected: Boolean){
        if(checkBoxSelected) {
            tvTodoItemTitle.paintFlags = tvTodoItemTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTodoItemTitle.paintFlags = tvTodoItemTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.itemView.apply {
            val tvTodoItemTitle = findViewById<TextView>(R.id.tvTodoItemTitle)
            tvTodoItemTitle.text = todoItems[position].title
            val checkBoxSelected = findViewById<CheckBox>(R.id.checkBoxSelected)
            checkBoxSelected.isChecked = todoItems[position].isSelected
            toggleTextLinedView(tvTodoItemTitle, checkBoxSelected.isChecked)
            checkBoxSelected.setOnCheckedChangeListener { _, isChecked ->
                toggleTextLinedView(tvTodoItemTitle, isChecked)
                todoItems[position].isSelected = !todoItems[position].isSelected
            }
        }
    }

    override fun getItemCount(): Int {
        return todoItems.size;
    }
}