package com.dev.data.repository

import android.util.Log
import com.dev.data.storage.questions.QuestionDao
import com.dev.data.storage.questions.QuestionsModel
import com.dev.data.storage.user.UserDao
import com.dev.data.storage.user.toAnswers
import com.dev.data.storage.user.toDbModel
import com.dev.domain.model.Answer
import com.dev.domain.model.Book
import com.dev.domain.model.Question
import com.dev.domain.repository.QuestionRepository
import java.lang.reflect.Array
import kotlin.random.Random
import kotlin.random.nextInt

class QuestionRepoImpl(private val questionDao: QuestionDao, private val userDao: UserDao) : QuestionRepository {

    override suspend fun getQuestion(): Question {
        val allQuestions = questionDao.getAllQuestions()
        val randomIndex = Random.nextInt(1, allQuestions.size)
        Log.d("random idex", randomIndex.toString())
        val questionDb = questionDao.getQuestion(randomIndex)
        val questionsFiltered = allQuestions.filter { model -> model.bookImageUrl != questionDb.bookImageUrl }.toCollection(ArrayList())
        val books: ArrayList<Book> = getRandomBooks(questionsFiltered)
        books.add(Book(imageUrl = questionDb.bookImageUrl, isCorrect = true))
        return Question(quote = questionDb.quote, books = books.shuffled())
    }

    override suspend fun writeAnswer(answer: Answer) {
        userDao.writeAnswerToDatabase(answer.toDbModel())
    }

    override suspend fun getFinishAnswers(): List<Answer> {
        val allUserAnswers = userDao.getAllAnswers()
        return allUserAnswers.toAnswers().takeLast(10)
    }

    private fun getRandomBooks(questionsList: List<QuestionsModel>): ArrayList<Book> {
        val books: ArrayList<Book> = arrayListOf()
        for (i in 0 until 2) {
            val randomIndex = Random.nextInt(1, questionsList.size)
            books.add(Book(imageUrl = questionsList[randomIndex].bookImageUrl, isCorrect = false))
        }
        if (books[0].imageUrl == books[1].imageUrl) {
            val droppingIndex = Random.nextInt(0, 1)
            val randomIndex = Random.nextInt(1, questionsList.size)
            books.removeAt(droppingIndex)
            books.add(droppingIndex, Book(questionsList[randomIndex].bookImageUrl, isCorrect = false))
        }
        return books
    }

}