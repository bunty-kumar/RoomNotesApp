package com.bunty.roomnotesapp

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(
    val context: Context,
    private val notesClick: NotesClick,
    private val notesDeleteClick: NotesDeleteClick
) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var allNotes = ArrayList<Notes>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.notes_itemview, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.notesTitle.text = allNotes[position].noteTitle
        holder.notesDate.text = allNotes[position].noteTimeStamp

        if (allNotes[position].noteDesc == "") {
            holder.notesDesc.text = "N/A"
        } else {
            holder.notesDesc.text = allNotes[position].noteDesc
        }

        holder.deleteBtn.setOnClickListener {
            notesDeleteClick.onNotesDeleteClick(allNotes[position])
        }

        holder.itemView.setOnClickListener {
            notesClick.onNotesClick(allNotes[position])
        }

        when {
            position == 0 || position % 5 == 0 -> {
                holder.notesCard.setBackgroundResource(R.drawable.red_card_bg)
            }
            position == 1 || (position + 5) % 5 == 1 -> {
                holder.notesCard.setBackgroundResource(R.drawable.blue_card)
            }
            position == 2 || (position + 5) % 5 == 2 -> {
                holder.notesCard.setBackgroundResource(R.drawable.orange_card)
            }
            position == 3 || (position + 5) % 5 == 3 -> {
                holder.notesCard.setBackgroundResource(R.drawable.sky_card)
            }
            position == 4 || (position + 5) % 5 == 4 -> {
                holder.notesCard.setBackgroundResource(R.drawable.pink_card)
            }

        }

    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<Notes>, tvNoDta: TextView) {
        allNotes.clear()
        tvNoDta.isVisible = newList.isEmpty()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }

    class NotesViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val notesTitle: TextView = v.findViewById(R.id.notesTitle)
        val notesDate: TextView = v.findViewById(R.id.notesDate)
        val deleteBtn: ImageView = v.findViewById(R.id.deleteBtn)
        val notesCard: LinearLayout = v.findViewById(R.id.llNotes)
        val notesDesc: TextView = v.findViewById(R.id.notesDesc)
    }

}

interface NotesClick {
    fun onNotesClick(notes: Notes)
}

interface NotesDeleteClick {
    fun onNotesDeleteClick(notes: Notes)
}