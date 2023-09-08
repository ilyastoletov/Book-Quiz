package com.dev.bookquiz.presentation.screens.results.contract

import com.dev.bookquiz.presentation.core.ViewEffect
import com.dev.bookquiz.presentation.core.ViewEvent
import com.dev.bookquiz.presentation.core.ViewState
import com.dev.domain.model.Answer

object ResultsContract {

    sealed class Event : ViewEvent {
        object GetAnswers : Event()
        object TurnToMainMenu : Event()
    }

    sealed class State : ViewState {
        object Loading : State()
        data class GotAnswers(val answers: List<Answer>) : State()
    }

    sealed class Effect : ViewEffect {
        object NavigateToMenu : Effect()
    }

}