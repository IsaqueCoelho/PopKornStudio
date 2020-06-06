package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.challenge.business.CreateChallenge
import com.studio.sevenapp.android.domain.challenge.business.GenerateChallengeDataLists
import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.ChallengeResult
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.model.Question

class ChallengeUseCaseImpl(
    private val challengeRepository: ChallengeRepository
) : ChallengeUseCase {

    override suspend fun getQuestionsByState(state: QuestionStateEnum): List<Question> {
        return challengeRepository.getQuestionsByState(state = state)
    }

    override suspend fun getChallenged(genre: Genre): Challenge {
        GENRE_NAME = genre.name!!
        var questionList = getQuestionsByState(QuestionStateEnum.AVAILABLE)

        if (questionList.isEmpty()) {
            questionList = createQuestions(genre)
        }

        return CreateChallenge().create(GENRE_NAME, questionList)
    }

    private suspend fun createQuestions(genre: Genre): List<Question> {
        GENRE_NAME = genre.name!!
        val movieList = challengeRepository.getMoviesByGenre(genre = genre.id)

        val generateData = GenerateChallengeDataLists(movieList)
        val questionList: List<Question> = generateData.getQuestionList()

        challengeRepository.insertQuestions(questionList = questionList)
        return questionList
    }


    /////////////////////////////
    private fun getChallengeResult(challenge: Challenge): ChallengeResult {
        var points = 0

        challenge.questionList.forEach { question ->
            points += question.answerList.filter { answer ->
                answer.isCorrect && answer.isChecked
            }.size
        }

        return ChallengeResult(
            genre = challenge.genre,
            points = points,
            result = getResult(points)
        )
    }

    private fun getResult(points: Int): String {
        return when {
            points == 10 -> {
                "Parabéns você passou de nível!"
            }
            points > 5 -> {
                "Quaaase lá, falta pouco não desista!!!"
            }
            else -> {
                "Ihhh, acho melhor maratonar mais um pouco!"
            }
        }
    }

    companion object {
        private var GENRE_NAME: String = ""
    }
}