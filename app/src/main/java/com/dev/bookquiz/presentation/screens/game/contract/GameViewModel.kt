package com.dev.bookquiz.presentation.screens.game.contract

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.dev.bookquiz.presentation.core.BaseViewModel
import com.dev.domain.model.Answer
import com.dev.domain.usecase.GetQuestionUseCase
import com.dev.domain.usecase.WriteAnswerUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val getQuestionUseCase: GetQuestionUseCase,
                                        private val writeAnswerUseCase: WriteAnswerUseCase)
    : BaseViewModel<GameContract.Event, GameContract.State, GameContract.Effect>() {

    override fun setInitialState(): GameContract.State = GameContract.State.Pending

    override fun handleEvents(event: GameContract.Event) = when(event) {
        is GameContract.Event.GetQuestion -> loadQuestion()
        is GameContract.Event.WriteAnswer -> writeAnswer(event.answer)
    }

    private fun loadQuestion() {
        viewModelScope.launch(dispatcher) {
            val questionResult = getQuestionUseCase.invoke()
            setState {
                GameContract.State.ReceivedQuestion(questionResult)
            }
        }
    }

    private fun writeAnswer(answer: Answer) {
        viewModelScope.launch(dispatcher) {
            writeAnswerUseCase.invoke(answer)
        }
    }

}