package com.studio.sevenapp.android.data.challenge

import com.studio.sevenapp.android.data.challenge.localsource.ChallengeLocalSource
import com.studio.sevenapp.android.data.challenge.remotesource.MovieRemoteSource
import com.studio.sevenapp.android.data.infra.CollectionsEnum
import com.studio.sevenapp.android.data.infra.FieldTypeEnum
import com.studio.sevenapp.android.data.infra.Firestore
import com.studio.sevenapp.android.domain.challenge.ChallengeRepository
import com.studio.sevenapp.android.domain.challenge.business.QuestionStateEnum
import com.studio.sevenapp.android.domain.model.Challenge
import com.studio.sevenapp.android.domain.model.Movie
import com.studio.sevenapp.android.domain.model.Question
import com.studio.sevenapp.android.domain.user.UserRepository

class ChallengeRepositoryImpl(
    private val movieRemoteSource: MovieRemoteSource,
    private val challengeLocalSource: ChallengeLocalSource,
    private val userRepository: UserRepository,
    private val firestore: Firestore
) : ChallengeRepository {

    override suspend fun getMoviesByGenre(page: Int, genre: Int): List<Movie> {
        val sortBy = "popularity.desc"
        val includeAdult = false

        return movieRemoteSource.getMovies(
            sortBy = sortBy,
            includeAdult = includeAdult,
            page = page,
            withGenres = genre
        ).movieList
    }

    override suspend fun getChallengeByGenre(
        genre: String,
        questionStateEnum: QuestionStateEnum
    ): Challenge? {
        val challenge: Challenge? = challengeLocalSource.getChallengeByGenre(genre = genre)

        challenge?.let {
            it.apply {
                questionList = questionList.filter { question ->
                    question.state == questionStateEnum
                }
            }
        }

        return challenge
    }

    override suspend fun getQuestionsByState(state: QuestionStateEnum): List<Question> {
        return challengeLocalSource.getQuestionsByState(state = state.name)
    }

    override suspend fun insertChallenge(challenge: Challenge) =
        challengeLocalSource.insertChallenge(challenge = challenge)

    override suspend fun updateQuestion(question: Question) =
        challengeLocalSource.updateQuestion(question = question)

    override suspend fun updateChallenge(challenge: Challenge) {
        challengeLocalSource.updateChallenge(challenge = challenge)
        val userId = userRepository.getCurrentUser()!!.uid
        firestore.updateDocumentField(
            collectionRef = CollectionsEnum.USERS.name,
            documentRef = userId,
            fieldkey = "level",
            fieldValue = challenge.level.toString(),
            fieldValueType = FieldTypeEnum.INT
        )
    }

    override suspend fun deleteData(challenge: Challenge) =
        challengeLocalSource.deleteData(challenge = challenge)
}