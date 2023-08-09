package com.Brandon.androidmaster.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.Brandon.androidmaster.R
import com.Brandon.androidmaster.todoapp.TaskCategory.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TodoActivity : AppCompatActivity() {

    private val categories = listOf(Other, Business, Personal)
    private val tasks = mutableListOf<Task>(
        Task("prueba 1", Business),
        Task("prueba 2", Other),
        Task("prueba 3", Personal),
    )

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    private lateinit var fabAddTask: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)
        initComponents()
        initUI()
        initListeners()
    }

    private fun initListeners() {
        fabAddTask.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        //muestra el dialog
        val dialog = Dialog(this)
        //enganchar el view/xml al dialog
        dialog.setContentView(R.layout.dialog_task)
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)

        //selecionar al radio button seleccionado a partir del padre
        val rgCategories: RadioGroup = dialog.findViewById(R.id.rgCategories)
        btnAddTask.setOnClickListener {
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()) {
                //devuelve el id del radio button seleccionado
                val selectedId = rgCategories.checkedRadioButtonId
                //Buscar el radio button seleccionado
                val selectedRadioButton = rgCategories.findViewById<RadioButton>(selectedId)
                val currentCategory: TaskCategory = when (selectedRadioButton.text) {
                    getString(R.string.todo_dialog_category_business) -> Business
                    getString(R.string.todo_dialog_category_other) -> Other
                    getString(R.string.todo_dialog_category_personal) -> Personal
                    else -> {
                        Business
                    }
                }
                tasks.add(Task(currentTask, currentCategory))
                updateTasks()
                dialog.hide()
            }
        }

        //mostrar el dialog
        dialog.show()
    }

    private fun initComponents() {
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }

    private fun initUI() {
        categoriesAdapter =
            CategoriesAdapter(categories) { position -> updatedCategories(position) }
        rvCategories.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rvCategories.adapter = categoriesAdapter

        //recomienda sacar la función lambda del parentesis
        tasksAdapter = TasksAdapter(tasks) { position -> onItemSelected(position) }
        rvTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rvTasks.adapter = tasksAdapter
    }

    private fun onItemSelected(position: Int) {
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks()
    }

    private fun updatedCategories(position: Int) {
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }

    //avisar al adaptador que hay un nuevo elemento
    private fun updateTasks() {
        //filtrar cuales categorias estan selecionadas
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }

        //filtrar las tareas de esas categorias
        val newTask=tasks.filter {
            selectedCategories.contains(it.category)
        }
        tasksAdapter.tasks=newTask
        tasksAdapter.notifyDataSetChanged()
    }
}