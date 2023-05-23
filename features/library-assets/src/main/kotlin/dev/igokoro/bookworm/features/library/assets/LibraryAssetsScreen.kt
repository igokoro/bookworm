package dev.igokoro.bookworm.features.library.assets

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
import dev.igokoro.bookworm.data.CheckedOutItem
import dev.igokoro.bookworm.data.LibraryAccountId
import dev.igokoro.bookworm.data.LibraryRepo
import kotlinx.parcelize.Parcelize

@Parcelize
object LibraryAssetsScreen : Screen {

    sealed interface State : CircuitUiState {

        val isRefreshing: Boolean

        object Loading : State {

            override val isRefreshing: Boolean = false
        }

//        data class NoAnimals(override val isRefreshing: Boolean) : State

        data class Success(
            val content: UiAssetsScreenContent,
            override val isRefreshing: Boolean,
//            val filters: Filters = Filters(),
//            val isUpdateFiltersModalShowing: Boolean = false,
            val eventSink: (Event) -> Unit = {},
        ) : State
    }

    sealed interface Event : CircuitUiEvent {
        data class ClickAsset(
            val assetId: String,
        ) : Event

        object Refresh : Event

//        object UpdateFilters : Event

//        data class UpdatedFilters(val newFilters: Filters) : Event
    }
}

class LibraryAssetsPresenter @AssistedInject constructor(
    @Assisted private val navigator: Navigator,
    private val libraryRepo: LibraryRepo,
) : Presenter<LibraryAssetsScreen.State> {

    @Composable
    override fun present(): LibraryAssetsScreen.State {
        var isRefreshing by remember { mutableStateOf(false) }
        if (isRefreshing) {
            LaunchedEffect(Unit) {
//                libraryRepo.refreshData()
                isRefreshing = false
            }
        }

        val contentState by
        produceState<UiAssetsScreenContent?>(
            initialValue = null,
            libraryRepo
        ) {
            Log.d(
                "GGGGGG",
                "loading checkouts"
            )
            val loanedItems = libraryRepo
                .checkouts(LibraryAccountId("21379008100011"))
                .map { it.toUiModel() }

            Log.d(
                "GGGGGG",
                "loading complete: checkouts = $loanedItems"
            )
            value = UiAssetsScreenContent(
                loanedItems = loanedItems,
            )
        }

//        var isUpdateFiltersModalShowing by rememberSaveable { mutableStateOf(false) }
//        var filters by rememberSaveable { mutableStateOf(Filters()) }

        val content = contentState
        return when {
            content == null -> LibraryAssetsScreen.State.Loading
//            content.isEmpty() -> PetListScreen.State.NoAnimals(isRefreshing)
            else ->
                LibraryAssetsScreen.State.Success(
                    content = content,
                    isRefreshing = false,
                ) { event ->
                    when (event) {
                        is LibraryAssetsScreen.Event.ClickAsset -> {
                            Log.d(
                                "GGGGGG",
                                "clicked!"
                            )
//                            navigator.goTo(
//                                PetDetailScreen(
//                                    event.petId,
//                                    event.photoUrlMemoryCacheKey
//                                )
//                            )
                        }

//                        is PetListScreen.Event.UpdatedFilters -> {
//                            isUpdateFiltersModalShowing = false
//                            filters = event.newFilters
//                        }

//                        PetListScreen.Event.UpdateFilters -> {
//                            isUpdateFiltersModalShowing = true
//                        }

                        LibraryAssetsScreen.Event.Refresh -> isRefreshing = true
                    }
                }
        }
    }

    @CircuitInject(
        LibraryAssetsScreen::class,
        AppScope::class
    )
    @AssistedFactory
    interface Factory {

        fun create(navigator: Navigator): LibraryAssetsPresenter
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@CircuitInject(
    LibraryAssetsScreen::class,
    AppScope::class
)
@Composable
fun LibraryAssets(
    state: LibraryAssetsScreen.State,
    modifier: Modifier = Modifier,
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Assets",
                        fontSize = 22.sp,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                scrollBehavior = scrollBehavior,
//                actions = {
//                    if (state is LibraryAssetsScreen.State.Success) {
//                        val eventSink = state.eventSink
//                        IconButton(onClick = { eventSink(LibraryAssetsScreen.Event.UpdateFilters) }) {
//                            Icon(
//                                imageVector = Icons.Default.FilterList,
//                                contentDescription = "Filter pet list",
//                                tint = MaterialTheme.colorScheme.onBackground
//                            )
//                        }
//                    }
//                },
            )
        },
    ) { paddingValues ->
        when (state) {
            LibraryAssetsScreen.State.Loading ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.testTag("progress"),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

//            is LibraryAssetsScreen.State.NoAnimals ->
//                Box(
//                    modifier = Modifier.fillMaxSize(),
//                    contentAlignment = Alignment.Center
//                ) {
//                    Text(
//                        modifier = Modifier.testTag(NO_ANIMALS_TAG),
//                        text = stringResource(id = R.string.no_animals)
//                    )
//                }

            is LibraryAssetsScreen.State.Success ->
                AssetsList(
                    modifier = Modifier.padding(paddingValues),
                    content = state.content,
                )
        }
    }
}

@Composable
fun AssetsList(
    modifier: Modifier = Modifier,
    content: UiAssetsScreenContent,
) {
    Box(
        modifier = modifier
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(text = "Loaned")
            }
            items(
                items = content.loanedItems,
                key = { it.id },
            ) {
                Column(
                    modifier = Modifier.padding(horizontal = 16.dp),
                ) {
                    Text(text = it.title)
                    Text(text = it.status)
                }

            }
        }
    }
}

@Immutable
data class UiLoanedItem(
    val id: String,
    val title: String,
    val status: String,
    val dueDate: String,
    val dueTimestamp: Long,
)

internal fun CheckedOutItem.toUiModel(): UiLoanedItem {
    return UiLoanedItem(
        id = id,
        title = title,
        status = status,
        dueDate = dueDate,
        dueTimestamp = dueTimestamp,
    )
}

@Immutable
data class UiAssetsScreenContent(
    val loanedItems: List<UiLoanedItem>,
)
