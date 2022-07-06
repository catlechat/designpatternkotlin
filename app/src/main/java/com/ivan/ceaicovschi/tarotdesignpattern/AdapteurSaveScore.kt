package com.ivan.ceaicovschi.tarotdesignpattern

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.NonNull



class AdapteurSaveScore {

    internal class CustomAdapter(private var itemsList: List<SaveScore>) :
        RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
        internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var Title_Score_Player1_1: TextView = view.findViewById(R.id.Title_Score_Player1)
            var Title_Score_Player2_1: TextView = view.findViewById(R.id.Title_Score_Player2)
            var Title_Score_Player3_1: TextView = view.findViewById(R.id.Title_Score_Player3)
            var Title_Score_Player4_1: TextView = view.findViewById(R.id.Title_Score_Player4)
            var Date_Score_1: TextView = view.findViewById(R.id.Date_Score)
        }

        @NonNull
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
            return MyViewHolder(itemView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = itemsList[position]
            holder.Title_Score_Player1_1.text = item.player1
            holder.Title_Score_Player2_1.text = item.player2
            holder.Title_Score_Player3_1.text = item.player3
            holder.Title_Score_Player4_1.text = item.player4
            holder.Date_Score_1.text = item.date
        }
        override fun getItemCount(): Int {
            return itemsList.size
        }
    }
}
