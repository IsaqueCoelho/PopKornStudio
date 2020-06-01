package com.studio.sevenapp.android.popkornstudio.features.game.challenge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.popkornstudio.R
import kotlinx.android.synthetic.main.item_challenge_options.view.*

class ChallengeAnswerOptionsAdapter(
    private var challengeQuestionOptions: List<Answer>,
    private val listener: ChallengeAnswerOptionItemClickListener
) :
    RecyclerView.Adapter<ChallengeAnswerOptionsAdapter.ChallengeAnswerOptionsViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ChallengeAnswerOptionsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_challenge_options, parent, false)
        context = parent.context
        return ChallengeAnswerOptionsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return challengeQuestionOptions.size
    }

    override fun onBindViewHolder(holder: ChallengeAnswerOptionsViewHolder, position: Int) {
        val answerOption = challengeQuestionOptions[position]
        holder.itemView.cardview_option.setOnClickListener {
            listener.onClick(answerOption)
        }
        holder.itemView.textview_option_answer.text = answerOption.text
        holder.itemView.textview_option_name.text = getOptionName(position)
        holder.itemView.imageview_container_option.setImageDrawable(
            context!!.getDrawable(
                getResource(
                    position
                )
            )
        )

    }

    fun updateList(newList: List<Answer>){
        challengeQuestionOptions = newList
        notifyDataSetChanged()
    }

    private fun getOptionName(position: Int): CharSequence? {
        return when (position) {
            0 -> {
                "A"
            }
            1 -> {
                "B"
            }
            else -> {
                "C"
            }
        }
    }

    private fun getResource(position: Int): Int {
        return when (position) {
            0 -> {
                R.color.DEFAULT_0BE881
            }
            1 -> {
                R.color.DEFAULT_FFD32A
            }
            else -> {
                R.color.DEFAULT_E65100
            }
        }
    }

    class ChallengeAnswerOptionsViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView)

    interface ChallengeAnswerOptionItemClickListener {
        fun onClick(answer: Answer)
    }
}