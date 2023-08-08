package com.Brandon.androidmaster.todoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Brandon.androidmaster.R

class TodoActivity : AppCompatActivity() {

    //7:32:18
    private val categories= listOf(TaskCategory.Other, TaskCategory.Business, TaskCategory.Personal)

    private lateinit var rvCategories:RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initComponents()
        initUI()
    }

    private fun initComponents() {
        rvCategories=findViewById(R.id.rvCategories)
    }

    private fun initUI() {
        categoriesAdapter= CategoriesAdapter(categories)
        rvCategories.layoutManager=LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter=categoriesAdapter
    }

}