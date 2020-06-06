package com.studio.sevenapp.android.popkornstudio.features.game.challenge

import android.os.Bundle
import android.os.CountDownTimer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.studio.sevenapp.android.domain.model.Question
import com.studio.sevenapp.android.domain.model.Answer
import com.studio.sevenapp.android.popkornstudio.R
import com.studio.sevenapp.android.popkornstudio.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_challenge_question.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

private const val ARG_PARAM_CHALLENGE_QUESTION = "PARAM_CHALLENGE_QUESTION"
private const val ARG_PARAM_QUESTION_COUNT = "PARAM_QUESTION_COUNT"

class ChallengeQuestionFragment :
    BaseFragment<ChallengeQuestionViewModel>(R.layout.fragment_challenge_question),
    ChallengeAnswerOptionsAdapter.ChallengeAnswerOptionItemClickListener {

    override val viewModel: ChallengeQuestionViewModel by viewModel()

    private val answerOptionsAdapter: ChallengeAnswerOptionsAdapter by inject {
        parametersOf(this)
    }

    private lateinit var questionChallenge: Question
    private var questionCount: Int = 0

    private var totalTimer: Long = 15000L
    private val intervalTimer = 1000L
    private lateinit var timer: CountDownTimer

    override fun onResume() {
        super.onResume()
        getArquments()
        setComponents()
    }

    override fun onClick(answer: Answer) {
        answer.isChecked = true
        viewModel.saveAnswer(answer = answer)
        swipeToNextScreen()
    }

    private fun getArquments() {
        questionChallenge = arguments!!.let { bundle ->
            bundle.getParcelable(ARG_PARAM_CHALLENGE_QUESTION) as Question
        }

        questionCount = arguments!!.getInt(ARG_PARAM_QUESTION_COUNT)

        totalTimer = questionChallenge.time.toLong()
    }

    private fun setComponents() {
        val topic = "${questionCount}. ${questionChallenge.topic}"
        textview_question_topic.text = topic
        textview_question_context.text = questionChallenge.context

        answerOptionsAdapter.updateList(questionChallenge.answerList)

        recyclerview.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerview.adapter = answerOptionsAdapter

        setCountDownTimer()
        timer.start()
    }

    private fun setCountDownTimer() {
        timer = object : CountDownTimer(totalTimer, intervalTimer) {
            override fun onFinish() {
                swipeToNextScreen()
            }

            override fun onTick(millisUntilFinished: Long) {
                val currentTimer =
                    if (millisUntilFinished > 0) (millisUntilFinished / 1000).toInt()
                    else millisUntilFinished.toInt()
                setTimerIndicator(currentTimer)
            }
        }
    }

    private fun swipeToNextScreen() {
        timer.cancel()
        context?.let {
            (it as ChallengeActivity).swipeToNext()
        }
    }

    private fun setTimerIndicator(currentTimer: Int) {
        textview_indicator?.let {indicator ->
            indicator.text = currentTimer.toString()
        }
        imageview_container_indicator?.setImageDrawable(
            context?.getDrawable(
                getIndicatorColor(currentTimer)
            )
        )
    }

    private fun getIndicatorColor(currentTimer: Int): Int {
        return when {
            currentTimer > 10 -> R.color.DEFAULT_0FBCF9
            currentTimer > 5 -> R.color.DEFAULT_FFD32A
            else -> R.color.DEFAULT_FF3F34
        }
    }

    companion object {
        fun newInstance(
            questionParam: Question,
            questionCount: Int
        ) =
            ChallengeQuestionFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM_CHALLENGE_QUESTION, questionParam)
                    putInt(ARG_PARAM_QUESTION_COUNT, questionCount)
                }
            }
    }
}
