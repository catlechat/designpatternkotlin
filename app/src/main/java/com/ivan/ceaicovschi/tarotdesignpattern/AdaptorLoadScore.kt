package com.ivan.ceaicovschi.tarotdesignpattern

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

class AdaptorLoadScore {
    internal class CustomAdapter(private var itemsList: List<Game>) :
        RecyclerView.Adapter<CustomAdapter.MyViewHolder>() {
        internal inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var titleScorePlayer1: TextView = view.findViewById(R.id.Title_Score_Player1)
            var titleScorePlayer2: TextView = view.findViewById(R.id.Title_Score_Player2)
            var titleScorePlayer3: TextView = view.findViewById(R.id.Title_Score_Player3)
            var titleScorePlayer4: TextView = view.findViewById(R.id.Title_Score_Player4)
            var dateScore: TextView = view.findViewById(R.id.Date_Score)
            var resume: Button = view.findViewById<Button>(R.id.resume)
            var delete: Button = view.findViewById<Button>(R.id.delete)
        }

        @NonNull
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
            return MyViewHolder(itemView)
        }

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            val item = itemsList[position]
            holder.titleScorePlayer1.text = item.player1.name + " with " + item.player1.score
            holder.titleScorePlayer2.text = item.player2.name + " with " + item.player2.score

            if(item.player3 != null) {
                holder.titleScorePlayer3.isVisible = true
                holder.titleScorePlayer3.text = item.player3?.name + " with " + item.player3?.score
            }

            if(item.player4 != null) {
                holder.titleScorePlayer4.isVisible = true
                holder.titleScorePlayer4.text = item.player4?.name + " with " + item.player4?.score
            }

            holder.dateScore.text = item.date

            holder.resume.setOnClickListener{
                val playersStringNames = arrayListOf(
                    item.player1.name,
                    item.player2.name)

                if(item.player3?.name == null){
                    playersStringNames.add("")
                } else {
                    playersStringNames.add(item.player3?.name.toString())
                }

                if(item.player4?.name == null){
                    playersStringNames.add("")
                } else {
                    playersStringNames.add(item.player4?.name.toString())
                }

                val scores = arrayListOf<CharSequence>(
                    item.player1.score,
                    item.player2.score,
                    item.player3?.score.toString(),
                    item.player4?.score.toString()
                )

                val intent = Intent(it.context, GameActivity::class.java)
                val args = Bundle()
                args.putSerializable("players", playersStringNames)
                args.putSerializable("scores", scores)
                intent.putExtra("args", args)
                startActivity(it.context, intent, null)
            }

            holder.delete.setOnClickListener{
                val db = Room.databaseBuilder(
                    it.context,
                    AppDatabase::class.java, "tarot-db"
                ).allowMainThreadQueries().build()
                val gameDao = db.gameDao()
                gameDao.delete(item)
                val intent = Intent(it.context, LoadGameActivity::class.java)
                (it.context as Activity).finish()
                startActivity(it.context, intent, null)
            }
        }

        override fun getItemCount(): Int {
            return itemsList.size
        }
    }
}
