package xyz.teamgravity.externalstoragemediafiles

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.launch

class MainViewModel(
    private val manager: MediaManager
) : ViewModel() {

    var files: ImmutableList<MediaModel> by mutableStateOf(persistentListOf())
        private set

    ///////////////////////////////////////////////////////////////////////////
    // API
    ///////////////////////////////////////////////////////////////////////////

    fun onRefresh() {
        viewModelScope.launch {
            files = manager.getAll()
        }
    }
}