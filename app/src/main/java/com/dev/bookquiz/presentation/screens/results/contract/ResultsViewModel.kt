package com.dev.bookquiz.presentation.screens.results.contract

import androidx.lifecycle.viewModelScope
import com.dev.bookquiz.presentation.core.BaseViewModel
import com.dev.domain.usecase.GetFinishAnswersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(private val getFinishAnswersUseCase: GetFinishAnswersUseCase)
    : BaseViewModel<ResultsContract.Event, ResultsContract.State, ResultsContract.Effect>() {

    override fun setInitialState(): ResultsContract.State = ResultsContract.State.Loading

    override fun handleEvents(event: ResultsContract.Event) = when(event) {
        is ResultsContract.Event.GetAnswers -> getAnswers()
        is ResultsContract.Event.TurnToMainMenu -> setEffect { ResultsContract.Effect.NavigateToMenu }
    }

    private fun getAnswers() {
        viewModelScope.launch(dispatcher) {
            val answers = getFinishAnswersUseCase.invoke()
            setState { ResultsContract.State.GotAnswers(answers) }
        }
    }

}