package com.studio.sevenapp.android.popkornstudio.features.game.result

import android.os.Bundle
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val PARAM_CHALLENGE = "CHALLENGE_PARAM"

class ChallengeResultActivity : BaseActivity<ChallengeResultViewModel>() {

    override val viewModel: ChallengeResultViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_result)
    }

    override fun onResume() {
        super.onResume()
        getChallenge()
    }

    private fun getChallenge() {
        intent.extras?.let { bundle ->
            if(!bundle.getString(PARAM_CHALLENGE).isNullOrEmpty()){
                viewModel.getChallengeById(bundle.getString(PARAM_CHALLENGE)!!)
            }
        }
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
