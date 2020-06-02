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
    }

    private fun setComponent() {
        button_confirm.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        getChallenge()
    }

    private fun getChallenge() {
        intent.extras?.let { bundle ->
            if(!bundle.getString(PARAM_CHALLENGE).isNullOrEmpty()){
                viewModel.getChallengeResult(bundle.getString(PARAM_CHALLENGE)!!)
            }
        }
    }

    private fun prepareObservers() {
        viewModel.showResult().observe(this, Observer {challengeResult ->
            setResultComponents(challengeResult)
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
        fun paramsChallengeType(challengeId: String)
                : Bundle {
            return Bundle().apply {
                putString(PARAM_CHALLENGE, challengeId)
            }
        }
    }
}
