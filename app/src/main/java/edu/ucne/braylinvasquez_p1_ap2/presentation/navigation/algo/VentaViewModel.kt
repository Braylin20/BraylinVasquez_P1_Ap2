package edu.ucne.braylinvasquez_p1_ap2.presentation.navigation.algo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.braylinvasquez_p1_ap2.data.repository.VentaRepository
import javax.inject.Inject

@HiltViewModel
class VentaViewModel @Inject constructor(
    private val algoRepository: VentaRepository
): ViewModel() {

    //private val _uiState = MutableStateFlow(UiState())
    //val uiState= _uiState.asStateFlow()
}