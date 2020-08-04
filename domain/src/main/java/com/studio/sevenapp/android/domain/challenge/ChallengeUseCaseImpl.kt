package com.studio.sevenapp.android.domain.challenge

import com.studio.sevenapp.android.domain.challenge.business.GenerateChallenge
import com.studio.sevenapp.android.domain.challenge.business.GenerateChallengeDataLists
import com.studio.sevenapp.android.domain.challenge.business.GenerateChallengeResult
import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.ChallengeResult
import com.studio.sevenapp.android.domain.model.Genre
import com.studio.sevenapp.android.domain.model.Question

class ChallengeUseCaseImpl(
    private val challengeRepository: ChallengeRepository
) : ChallengeUseCase {

    override suspend fun getChallenged(genre: Genre): Challenge {
        var challenge: Challenge? = challengeRepository.getChallengeByGenre(
            genre = genre.name,
            questionStateEnum = QuestionStateEnum.AVAILABLE
        )

        challenge = validateChallenge(challenge = challenge, genre = genre)

        return challenge
    }

    override suspend fun saveQuestion(question: Question) {
        challengeRepository.updateQuestion(question = question)
    }

    override suspend fun getChallengeResult(genre: String): ChallengeResult {
        val challenge: Challenge? = challengeRepository.getChallengeByGenre(
            genre = genre,
            questionStateEnum = QuestionStateEnum.TO_VALIDATE
        )

        return generateChallengeResult(challenge = challenge!!)
    }

    private suspend fun validateChallenge(challenge: Challenge?, genre: Genre): Challenge {
        return when {
            challenge == null -> {
                createChallenge(genre = genre)
            }
            challenge.questionList.isEmpty() -> {
                nextLevelChallenge(challenge = challenge, genre = genre)
            }
            else -> {
                GenerateChallenge().reOrganize(challenge = challenge)
            }
        }
    }

    private suspend fun createChallenge(genre: Genre, level: Int = 1): Challenge {
        val generateChallenge = GenerateChallenge()
        val challenge: Challenge = generateChallenge.create(
            genre = genre,
            level = level,
            questionList = createQuestions(genreId = genre.id, page = level)
        )

        challengeRepository.insertChallenge(challenge = challenge)

        return generateChallenge.reOrganize(challenge = challenge)
    }

    private suspend fun nextLevelChallenge(challenge: Challenge, genre: Genre): Challenge {
        challengeRepository.deleteData(challenge = challenge)
        return createChallenge(genre = genre, level = challenge.level + 1)
    }

    private suspend fun createQuestions(genreId: Int, page: Int = 1): List<Question> {
        val movieList = challengeRepository.getMoviesByGenre(page = page, genre = genreId)

        val generateData = GenerateChallengeDataLists(movieList)
        return generateData.getQuestionList()
    }

    private suspend fun generateChallengeResult(challenge: Challenge): ChallengeResult {
        val challengeResult = GenerateChallengeResult().create(challenge = challenge)

        if (challengeResult.points == 10) {
            saveChallenge(challenge = challenge, state = QuestionStateEnum.RESOLVED)
        } else {
            saveChallenge(challenge = challenge, state = QuestionStateEnum.AVAILABLE)
        }

        return challengeResult
    }

    override suspend fun saveChallenge(challenge: Challenge, state: QuestionStateEnum) {
        challenge.questionList
            .filter { question ->
                question.state != QuestionStateEnum.RESOLVED
            }.apply {
                forEach { question ->
                    question.state = state
                    question.isCorrect = false
                }
            }

        challengeRepository.updateChallenge(challenge = challenge)
    }
}