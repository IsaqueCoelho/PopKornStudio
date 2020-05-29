package com.studio.sevenapp.android.popkornstudio.features.game.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studio.sevenapp.android.domain.model.MovieGenre
import com.studio.sevenapp.android.popkornstudio.R
import kotlinx.android.synthetic.main.item_game_category.view.*

class GameCategoryAdapter(
    private var genreList: List<MovieGenre>,
    private val itemClickListener: GameCategoryItemClickListener
) : RecyclerView.Adapter<GameCategoryAdapter.GameCategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameCategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game_category, parent, false)
        return GameCategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return genreList.size
    }

    override fun onBindViewHolder(holder: GameCategoryViewHolder, position: Int) {
        genreList.let { categoryList ->
            holder.itemView.cardview_category.setOnClickListener {
                itemClickListener.onItemClicked(categoryList[position])
            }
            holder.itemView.textview_category_name.text = categoryList[position].name
        }

    }

    fun updatedList(newGenreList: List<MovieGenre>){
        genreList = newGenreList.toMutableList()
        notifyDataSetChanged()
    }

    class GameCategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    interface GameCategoryItemClickListener {
        fun onItemClicked(movieGenre: MovieGenre)
    }
}