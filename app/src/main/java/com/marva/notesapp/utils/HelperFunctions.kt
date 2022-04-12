package com.marva.notesapp.utils

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import com.marva.notesapp.data.entity.Notes
import com.marva.notesapp.data.entity.Priority
import com.marva.notesapp.R
import java.text.SimpleDateFormat
import java.util.*

object HelperFunctions {

    fun verifyDataFromUser(title: String, description: String): Boolean {
        return if (TextUtils.isEmpty(title) || TextUtils.isEmpty(description)) {
            false
        } else !(title.isEmpty() || description.isEmpty())
    }

    fun parseToPriority(context: Context?, priority: String): Priority {
        val expectedPriority = context?.resources?.getStringArray(R.array.priorities)
        return when (priority) {
            expectedPriority?.get(0) -> Priority.HIGH
            expectedPriority?.get(1) -> Priority.MEDIUM
            expectedPriority?.get(2) -> Priority.LOW
            else -> Priority.HIGH
        }
    }

    fun spinnerListener(context: Context?, priorityIndicator: CardView): AdapterView.OnItemSelectedListener =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                context?.let {
                    when (position) {
                        0 -> {
                            val red = ContextCompat.getColor(it, R.color.pink)
                            priorityIndicator.setCardBackgroundColor(red)
                        }

                        1 -> {
                            val yellow = ContextCompat.getColor(it, R.color.yellow)
                            priorityIndicator.setCardBackgroundColor(yellow)
                        }

                        2 -> {
                            val green = ContextCompat.getColor(it, R.color.green)
                            priorityIndicator.setCardBackgroundColor(green)
                        }
                    }
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)

    fun checkIfDatabaseEmpty(notes: List<Notes>) {
        emptyDatabase.value = notes.isEmpty()
    }

    fun dateTodaySimpleFormat() : String {
        val date = Date()
        val simpleFormat = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return simpleFormat.format(date.time)
    }
}