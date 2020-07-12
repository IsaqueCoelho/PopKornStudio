package com.studio.sevenapp.android.popkornstudio.features.ranking

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.popkornstudio.R
import kotlinx.android.synthetic.main.item_game_category.view.*

class RankingCategoryAdapter(
    private var genreList: List<Genre>,
    private val itemClickListener: RankingCategoryItemClickListener
) : RecyclerView.Adapter<RankingCategoryAdapter.RankingCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingCategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game_category, parent, false)
        return RankingCategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: RankingCategoryViewHolder, position: Int) {
        genreList.let { categoryList ->
            holder.itemView.cardview_category.setOnClickListener {
                itemClickListener.onItemClicked(categoryList[position])
            }
            holder.itemView.textview_category_name.text = categoryList[position].name
        }
    }

    fun updatedList(newGenreList: List<Genre>){
        genreList = newGenreList.toMutableList()
        notifyDataSetChanged()
    }

    class RankingCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface RankingCategoryItemClickListener {
        fun onItemClicked(genre: Genre)
    }
}