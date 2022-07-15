package com.bignerdranch.android.notebookexample.controller

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.notebookexample.constants.IntentConstants
import com.bignerdranch.android.notebookexample.model.Item

class NotebookAdapter(listMain: ArrayList<Item>, contextMain: Context) :
    RecyclerView.Adapter<NotebookAdapter.NotebookViewHolder>() {
    var listArray = listMain
    var context = contextMain

    class NotebookViewHolder(itemView: View, contextViewHolder: Context) :
        RecyclerView.ViewHolder(itemView) {
        val tvTitle = itemView.findViewById<TextView>(R.id.tvItemTitle)
        val context = contextViewHolder

        fun setData(item: Item) {
            tvTitle.text = item.title

            itemView.setOnClickListener{
                val intent = Intent(context, EditActivity::class.java).apply {
                    putExtra(IntentConstants.INTENT_TITLE_KEY, item.title)
                    putExtra(IntentConstants.INTENT_DESCRIPTION_KEY, item.desc)
                    putExtra(IntentConstants.INTENT_IMAGE_URI_KEY, item.imageUri)
                }

                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotebookViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NotebookViewHolder(inflater.inflate(R.layout.rc_item, parent, false), context)
    }

    override fun onBindViewHolder(holder: NotebookViewHolder, position: Int) {
        holder.setData(listArray[position])
    }

    override fun getItemCount(): Int {
        return listArray.size
    }

    fun updateAdapter(listItems: ArrayList<Item>) {
        listArray.clear()
        listArray.addAll(listItems)
        notifyDataSetChanged()
    }
}