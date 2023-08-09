package com.Brandon.androidmaster.todoapp

import android.content.res.ColorStateList
import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.Brandon.androidmaster.R

class TasksViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val tvTask: TextView = view.findViewById(R.id.tvTask)
    private val cbTask: CheckBox = view.findViewById(R.id.cbTask)

    fun render(task: Task) {
        //rayar el texto si es completada o no
        if (task.isSelected) {
            tvTask.paintFlags = tvTask.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            tvTask.paintFlags = tvTask.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }

        //marcar el checkbox dependiendo si esta terminada
        cbTask.isChecked = task.isSelected

        //seleccionar el color dependiendo de que categoria es
        val color = when (task.category) {
            TaskCategory.Business -> {
                R.color.todo_business_category
            }

            TaskCategory.Other -> {
                R.color.todo_other_category
            }

            TaskCategory.Personal -> {
                R.color.todo_personal_category
            }
        }
        //se setea el color al checkbox
        cbTask.buttonTintList = ColorStateList.valueOf(
            //todas las vistas tienen el contexto de la actividad
            ContextCompat.getColor(cbTask.context, color)
        )

        tvTask.text = task.name
    }
}