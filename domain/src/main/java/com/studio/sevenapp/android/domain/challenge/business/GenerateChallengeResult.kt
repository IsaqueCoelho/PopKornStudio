package com.studio.sevenapp.android.domain.challenge.business

import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.ChallengeResult

class GenerateChallengeResult() {

    private lateinit var challenge: Challenge
    private var points: Int = 0

    fun create(challenge: Challenge): ChallengeResult {
        this.challenge = challenge

        return ChallengeResult(
            genre = challenge.genre,
            points = countPoints(),
            result = getResult()
        )
    }

    private fun countPoints(): Int {
        points = challenge.questionList.filter { question -> question.isCorrect }.size
        return points
    }

    private fun getResult(): String {
        return when {
            points < 6 -> {
                "Ihhhh, melhor maratonar mais um pouco ;)"
            }
            points < 10 -> {
                "Quaaase, falta pouquinho, tente novamente!"
            }
            else -> {
                when (challenge.division) {
                    ChallengeDivisionEnum.ALPHA -> {
                        challenge.division = ChallengeDivisionEnum.OMEGA
                        "Parabénsss, 1ª parte do Desafio Level ${challenge.level} Concluída"
                    }
                    else -> {
                        challenge.level++
                        "Aeeehooo, Agora seu nível de Desafio é ${challenge.level + 1}!!!"
                    }
                }

            }
        }
    }

}