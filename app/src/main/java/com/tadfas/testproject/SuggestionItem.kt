package com.tadfas.testproject

import androidx.recyclerview.widget.RecyclerView
import com.tadfas.testproject.SuggestionListAdapter.SuggestionItemHolder
import com.tadfas.testproject.SuggestionItem
import com.tadfas.testproject.SuggestionListAdapter.OnSuggestionItemSelected
import android.view.ViewGroup
import android.view.LayoutInflater
import com.tadfas.testproject.R
import android.view.View.OnLongClickListener
import android.widget.TextView

class SuggestionItem(val fromHistory: Boolean, val query: String) {
    override fun equals(o: Any?): Boolean {
        return if (o is SuggestionItem) {
            query == o.query
        } else false
    }

    override fun hashCode(): Int {
        return query.hashCode()
    }

    override fun toString(): String {
        return "[$fromHistory→$query]"
    }
}