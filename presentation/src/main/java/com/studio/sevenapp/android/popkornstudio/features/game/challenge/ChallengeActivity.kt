package com.studio.sevenapp.android.popkornstudio.features.game.challenge

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import com.studio.sevenapp.android.popkornstudio.features.game.result.ChallengeResultActivity
import kotlinx.android.synthetic.main.activity_challenge.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val ARG_PARAM_CHALLENGE_TYPE = "PARAM_CHALLENGE_TYPE"

class ChallengeActivity : BaseActivity<ChallengeViewModel>() {

    override val viewModel: ChallengeViewModel by viewModel()

    private val challengeQuestionAdapter: ChallengeQuestionAdapter by inject {
        parametersOf(this)
    }

    private lateinit var challengeId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge)

        getChallengeType()
        setComponents()
        prepareObservers()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getChallengeType() {
        intent.extras?.let { extra ->
            val movieGenre = extra.getParcelable(ARG_PARAM_CHALLENGE_TYPE) as Genre
            viewModel.getChallenge(genre = movieGenre)
        }
    }

    private fun setComponents() {
        setSupportActionBar(toolbar as Toolbar?)
        setToolbarBackButton()
        setToolbarTitle(getString(R.string.title_challenge))

        loadStateView = loadstate

        viewpagerchallenge.isUserInputEnabled = false
    }

    private fun prepareObservers() {
        viewModel.showQuestionsFragments()
            .observe(this, Observer { challengeQuestion ->
                challengeQuestionAdapter.setFragmentList(challengeQuestion)
                setViewPager(challengeQuestionAdapter)
            })

        viewModel.getChallenge()
            .observe(this, Observer { challenge ->
                challengeId = challenge.id
                viewModel.createChallengeQuestionFragments(
                    questionList = challenge.questionList
                )
            })
    }

    private fun setViewPager(challengeQuestionAdapter: ChallengeQuestionAdapter) {
        viewpagerchallenge.adapter = challengeQuestionAdapter
        setloadingState(false)
    }

    fun swipeToNext() {
        if (viewpagerchallenge.currentItem < viewpagerchallenge.adapter!!.itemCount - 1) {
            viewpagerchallenge.setCurrentItem(viewpagerchallenge.currentItem + 1, true)
        } else {
            changeScreen(
                intent = Intent(this, ChallengeResultActivity::class.java).putExtras(
                    ChallengeResultActivity.paramsChallengeType(challengeId = challengeId)
                ),
                addToStack = false
            )
        }
    }

    companion object {
        fun paramsChallengeType(challengeTypeParam: Genre)
                : Bundle {
            return Bundle().apply {
                putParcelable(ARG_PARAM_CHALLENGE_TYPE, challengeTypeParam)
            }
        }
    }
}
