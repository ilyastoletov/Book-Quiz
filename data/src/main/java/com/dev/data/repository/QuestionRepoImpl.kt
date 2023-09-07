package com.dev.data.repository

import android.util.Log
import com.dev.data.storage.questions.QuestionDao
import com.dev.data.storage.user.UserDao
import com.dev.data.storage.user.toAnswers
import com.dev.data.storage.user.toDbModel
import com.dev.domain.model.Answer
import com.dev.domain.model.Book
import com.dev.domain.model.Question
import com.dev.domain.repository.QuestionRepository
import kotlin.random.Random
import kotlin.random.nextInt

class QuestionRepoImpl(private val questionDao: QuestionDao, private val userDao: UserDao) : QuestionRepository {

    // TODO(Очень желательно сделать так, чтобы картинки не повторялись)
    override suspend fun getQuestion(): Question {
        val allQuestions = questionDao.getAllQuestions()
        val questionDb = questionDao.getQuestion(Random.nextInt(1, allQuestions.size))
        val questionsFiltered = allQuestions.filter { model -> model.bookImageUrl != questionDb.bookImageUrl }.toCollection(ArrayList())
        val books: ArrayList<Book> = arrayListOf()
        for (i in 0 until 2) {
            val randomIndex = Random.nextInt(1, questionsFiltered.size)
            books.add(Book(imageUrl = questionsFiltered[randomIndex].bookImageUrl, isCorrect = false))
        }
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

}