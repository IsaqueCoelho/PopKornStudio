package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.model.*

class ChallengeUseCaseImpl(
    private val challengeRepository: ChallengeRepository
) : ChallengeUseCase {
    override suspend fun getChallenge(genre: MovieGenre): Challenge {
        val movieList = challengeRepository.getMoviesByGenre(genre = genre.id)
        val challenge = CreateChallengeMovie().generateChallenge(
            genreName = genre.name!!,
            movieList = movieList
        )
        challengeRepository.insertChallenge(challenge = challenge)
        return challenge
    }

    override suspend fun getChallengeById(challengeId: String): Challenge {
        return challengeRepository.getChallengeById(challengeId = challengeId)
    }

    override suspend fun saveAnswer(answer: Answer) {
        challengeRepository.updatedAnswer(answer = answer)
    }

    override suspend fun getChallengeResult(challengeId: String): ChallengeResult {
        val challenge = challengeRepository.getChallengeById(challengeId = challengeId)
        return getChallengeResult(challenge = challenge)
    }

    private fun getChallengeResult(challenge: Challenge): ChallengeResult {
        var points = 0

        challenge.questionList.forEach {question ->
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
}