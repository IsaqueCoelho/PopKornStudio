package com.studio.sevenapp.android.popkornstudio.features.game.result

import android.os.Bundle
import androidx.lifecycle.Observer
import com.studio.sevenapp.android.domain.model.ChallengeResult
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import kotlinx.android.synthetic.main.activity_challenge_result.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val PARAM_CHALLENGE = "CHALLENGE_PARAM"

class ChallengeResultActivity : BaseActivity<ChallengeResultViewModel>() {

    override val viewModel: ChallengeResultViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_result)
        prepareObservers()
        setComponent()
        getChallenge()
    }

    private fun setComponent() {
        loadStateView = loadstate
        button_confirm.setOnClickListener {
            finish()
        }
    }

    private fun getChallenge() {
        intent.extras?.let { bundle ->
            if (!bundle.getString(PARAM_CHALLENGE).isNullOrEmpty()) {
                viewModel.getChallengeResult(challengeGenre = bundle.getString(PARAM_CHALLENGE)!!)
            }
        }
    }

    private fun prepareObservers() {
        viewModel.showResult().observe(this, Observer { challengeResult ->
            setResultComponents(challengeResult)
            setloadingState(false)
        })
    }

    private fun setResultComponents(challengeResult: ChallengeResult) {
        val pointsIndicator = "${challengeResult.points}/10"
        textview_genre_topic.text = challengeResult.genre
        textview_indicator.text = pointsIndicator
        textview_result.text = challengeResult.result
        indicator.progress = challengeResult.points
    }

    companion object {
        fun paramsChallengeType(challengeGenre: String)
                : Bundle {
            return Bundle().apply {
                putString(PARAM_CHALLENGE, challengeGenre)
            }
        }
    }
}
