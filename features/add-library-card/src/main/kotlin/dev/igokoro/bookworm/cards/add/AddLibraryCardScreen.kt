package dev.igokoro.bookworm.cards.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.slack.circuit.codegen.annotations.CircuitInject
import com.slack.circuit.runtime.CircuitUiEvent
import com.slack.circuit.runtime.CircuitUiState
import com.slack.circuit.runtime.Navigator
import com.slack.circuit.runtime.Screen
import com.slack.circuit.runtime.presenter.Presenter
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dev.igokoro.bookworm.anvil.AppScope
import dev.igokoro.bookworm.cards.add.AddLibraryCardScreen.Event
import dev.igokoro.bookworm.data.LibraryAccountId
import dev.igokoro.bookworm.data.LibraryCard
import dev.igokoro.bookworm.data.LibraryCardOwner
import dev.igokoro.bookworm.data.LibraryCardsRepo
import kotlinx.coroutines.launch
import kotlinx.parcelize.Parcelize

@Parcelize
object AddLibraryCardScreen : Screen {

    sealed interface State : CircuitUiState { data class Success(
        val eventSink: (Event) -> Unit = {},
    ) : State
    }

    sealed interface Event : CircuitUiEvent { data class AddCardClick(
        val cardId: String,
        val password: String,
    ) : Event
    }
}

class AddLibraryCardPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
//    private val cardsRepo: LibraryCardsRepo,
    private val addNewLibraryCardUseCase: AddNewLibraryCardUseCase,
) : Presenter<AddLibraryCardScreen.State> {

    @Composable
    override fun present(): AddLibraryCardScreen.State {
        val scope = rememberCoroutineScope()

        return AddLibraryCardScreen.State.Success(
            eventSink = {
                when (it) {
                    is Event.AddCardClick -> {
                        scope.launch {
                            addNewLibraryCardUseCase.execute(
                                accountId = LibraryAccountId(it.cardId),
                                password = it.password
                            )
//                            cardsRepo.addCard(
//                                card = LibraryCard(
//                                    id = LibraryAccountId(it.cardId),
//                                    owner = LibraryCardOwner("", ""),
//                                    expiration = "",
//                                )
//                            )
                        }

                    }
                }
            }
        )
    }

    @CircuitInject(
        AddLibraryCardScreen::class,
        AppScope::class
    )
    @AssistedFactory
    interface Factory {

        fun create(navigator: Navigator): AddLibraryCardPresenter
    }
}

@CircuitInject(
    AddLibraryCardScreen::class,
    AppScope::class
)
@Composable
fun AddLibraryCardUi(
    modifier: Modifier = Modifier,
    state: AddLibraryCardScreen.State,
) {
    when (state) {
        is AddLibraryCardScreen.State.Success -> AddCardFormUi(
            eventSink = state.eventSink
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddCardFormUi(
    modifier: Modifier = Modifier,
    eventSink: (Event) -> Unit,
) {
    Box(modifier = modifier) {
        Card {
            Column {
                var cardId by remember {
                    mutableStateOf("")
                }

                var password by remember {
                    mutableStateOf("")
                }

                TextField(
                    value = cardId,
                    onValueChange = { cardId = it },
                    label = { Text(text = "Card Number") },
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Password") },
                )
                Button(
                    onClick = {
                        eventSink(
                            Event.AddCardClick(
                                cardId = cardId,
                                password = password,
                            )
                        )
                    }) {
                    Text(text = "Add Card")
                }
            }
        }
    }
}
