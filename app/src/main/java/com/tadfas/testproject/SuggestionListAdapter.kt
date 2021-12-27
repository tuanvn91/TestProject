package com.tadfas.testproject

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tadfas.testproject.SuggestionListAdapter.SuggestionItemHolder
import java.util.*

class SuggestionListAdapter(private val context: Context) :
    RecyclerView.Adapter<SuggestionItemHolder>() {
    private val items = ArrayList<SuggestionItem>()
    private var listener: OnSuggestionItemSelected? = null
    fun setItems(items: List<SuggestionItem>?) {
        this.items.clear()
        this.items.addAll(items!!)
        notifyDataSetChanged()
    }

    fun setListener(listener: OnSuggestionItemSelected?) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionItemHolder {
        return SuggestionItemHolder(
            LayoutInflater.from(context)
                .inflate(R.layout.item_search_suggestion, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SuggestionItemHolder, position: Int) {
        val currentItem = getItem(position)
        holder.updateFrom(currentItem)
        holder.queryView.setOnClickListener { v: View? ->
            if (listener != null) {
                listener!!.onSuggestionItemSelected(currentItem)
            }
        }
        holder.queryView.setOnLongClickListener { v: View? ->
            if (listener != null) {
                listener!!.onSuggestionItemLongClick(currentItem)
            }
            true
        }
        holder.insertView.setOnClickListener { v: View? ->
            if (listener != null) {
                listener!!.onSuggestionItemInserted(currentItem)
            }
        }
    }

    fun getItem(position: Int): SuggestionItem {
        return items[position]
    }

    override fun getItemCount(): Int {
        return items.size
    }

    val isEmpty: Boolean
        get() = itemCount == 0

    interface OnSuggestionItemSelected {
        fun onSuggestionItemSelected(item: SuggestionItem?)
        fun onSuggestionItemInserted(item: SuggestionItem?)
        fun onSuggestionItemLongClick(item: SuggestionItem?)
    }

    class SuggestionItemHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        private val itemSuggestionQuery: TextView = rootView.findViewById(R.id.item_suggestion_query)
        private val suggestionIcon: ImageView = rootView.findViewById(R.id.item_suggestion_icon)
        val queryView: View = rootView.findViewById(R.id.suggestion_search)
        val insertView: View = rootView.findViewById(R.id.suggestion_insert)

        // Cache some ids, as they can potentially be constantly updated/recycled
        private val historyResId: Int = R.drawable.ic_history
        private val searchResId: Int = R.drawable.ic_search
        fun updateFrom(item: SuggestionItem) {
            suggestionIcon.setImageResource(if (item.fromHistory) historyResId else searchResId)
            itemSuggestionQuery.text = item.query
        }

    }
}