package com.example.monkhoodpractice.RoomDB

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.View.inflate
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.monkhoodpractice.R

class WordListAdapter(private val context: Context) : RecyclerView.Adapter<WordListAdapter.UserViewHolder>() {


    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val user_name: TextView = itemView.findViewById(R.id.user_name)
        val user_pass: TextView = itemView.findViewById(R.id.user_pass)
        val user_id: TextView = itemView.findViewById(R.id.user_apID)
    }

    private val allUsers = ArrayList<Word>()

    fun updateList(newList:List<Word>){
        allUsers.clear()
        allUsers.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.user_view2, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.user_name.text = allUsers[position].name
        holder.user_pass.text = allUsers[position].pass
        holder.user_id.text = allUsers[position].ID

    }

    override fun getItemCount(): Int {
        return allUsers.size
    }
}

