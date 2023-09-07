package com.dev.bookquiz.presentation.screens.game.contract

import com.dev.bookquiz.presentation.core.ViewEffect
import com.dev.bookquiz.presentation.core.ViewEvent
import com.dev.bookquiz.presentation.core.ViewState
import com.dev.domain.model.Answer
import com.dev.domain.model.Question

object GameContract  {

    sealed class State : ViewState {
        object Pending : State()
        data class ReceivedQuestion(val data: Question) : State()
    }

    sealed class Event : ViewEvent {
        object GetQuestion : Event()
        data class WriteAnswer(val answer: Answer) : Event()
        // object GameEnded : Event()
    }

    sealed class Effect : ViewEffect {
        // object LoadFinishScreen : Effect()
    }

}