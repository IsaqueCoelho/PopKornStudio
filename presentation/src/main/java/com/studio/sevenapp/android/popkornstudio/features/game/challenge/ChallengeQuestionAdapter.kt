package com.studio.sevenapp.android.popkornstudio.features.game.challenge

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ChallengeQuestionAdapter(
    fa: FragmentActivity,
    private var fragmentList: List<ChallengeQuestionFragment>
) :
    FragmentStateAdapter(fa) {

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun setFragmentList(newList: List<ChallengeQuestionFragment> ){
        fragmentList = newList
        notifyDataSetChanged()
    }
}