package black.bracken.kmpplayground.androidapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import black.bracken.kmpplayground.Greeting
import black.bracken.kmpplayground.androidapp.ui.theme.KmpplaygroundTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KmpplaygroundTheme {
                val uiState by viewModel.uiState.collectAsState()

                LaunchedEffect(Unit) {
                    viewModel.initialize()
                }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(uiState = uiState)
                }
            }
        }
    }
}

data class MainUiState(
    val number: Int = 0,
    val textFromKmp: String = "Unknown",
)

class MainViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MainUiState())
    val uiState = _uiState.asStateFlow()

    fun initialize() {
        viewModelScope.launch {
            _uiState.update {
                it.copy(
                    textFromKmp = Greeting().greet()
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    uiState: MainUiState,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text("number = ${uiState.number}")
        Text("text: ${uiState.textFromKmp}")
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    KmpplaygroundTheme {
        MainScreen(MainUiState())
    }
}