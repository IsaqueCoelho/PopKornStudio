package com.studio.sevenapp.android.popkornstudio.features.game.result

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest.*
import com.google.android.gms.ads.InterstitialAd
import com.studio.sevenapp.android.domain.model.ChallengeResult
import com.studio.sevenapp.android.popkornstudio.BuildConfig.ADMOB_AFTER_RESULT_GAME_ID
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseActivity
import kotlinx.android.synthetic.main.activity_challenge_result.*
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val PARAM_CHALLENGE = "CHALLENGE_PARAM"

class ChallengeResultActivity : BaseActivity<ChallengeResultViewModel>() {

    override val viewModel: ChallengeResultViewModel by viewModel()

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge_result)
        prepareObservers()
        setComponent()
        prepareAds()
        getChallenge()
    }

    private fun prepareAds() {
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = ADMOB_AFTER_RESULT_GAME_ID
        mInterstitialAd.loadAd(Builder().build())

        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdLoaded() {
                setloadingState(false)
            }

            override fun onAdFailedToLoad(errorCode: Int) {
                val errorMsg = when (errorCode) {
                    ERROR_CODE_INTERNAL_ERROR -> "Something happened internally; for instance, an invalid response was received from the ad server."
                    ERROR_CODE_INVALID_REQUEST -> "he ad request was invalid; for instance, the ad unit ID was incorrect."
                    ERROR_CODE_NETWORK_ERROR -> "The ad request was unsuccessful due to network connectivity."
                    else -> "The ad request was successful, but no ad was returned due to lack of ad inventory. "
                }

                Log.e(ChallengeResultActivity::class.java.simpleName, "failed to load ads: $errorMsg")
                setloadingState(false)
            }

            override fun onAdOpened() {}

            override fun onAdClicked() {}

            override fun onAdLeftApplication() {
                mInterstitialAd.loadAd(Builder().build())
            }

            override fun onAdClosed() {
                finish()
            }
        }
    }

    private fun setComponent() {
        loadStateView = loadstate
        button_confirm.setOnClickListener {
            when {
                mInterstitialAd.isLoaded -> mInterstitialAd.show()
                else -> finish()
            }
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
