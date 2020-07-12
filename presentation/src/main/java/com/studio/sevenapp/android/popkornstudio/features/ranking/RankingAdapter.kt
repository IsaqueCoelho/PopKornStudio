package com.studio.sevenapp.android.popkornstudio.features.ranking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.studio.sevenapp.android.domain.model.User
import com.studio.sevenapp.android.popkornstudio.R
import kotlinx.android.synthetic.main.item_ranking.view.*

class RankingAdapter(
    private var userList: List<User>
) : RecyclerView.Adapter<RankingAdapter.RankingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ranking, parent, false)
        return RankingViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        val user: User = userList[position]
        val userPoints = "Level: ${user.level}"

        holder.itemView.textview_user_position.text = (position + 1).toString()
        holder.itemView.textview_user_name.text = user.name

        holder.itemView.textview_user_points.text = userPoints

        Glide.with(holder.itemView.context)
            .load(user.pictureUrl)
            .into(holder.itemView.circleimageview_profile)
    }

    fun updatedList(newUserList: List<User>){
        userList = newUserList.toMutableList()
        notifyDataSetChanged()
    }

    class RankingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}