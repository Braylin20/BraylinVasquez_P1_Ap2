package edu.ucne.braylinvasquez_p1_ap2.presentation.navigation.algo

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.ucne.braylinvasquez_p1_ap2.data.repository.AlgoRepository
import javax.inject.Inject

@HiltViewModel
class AlgoViewModel @Inject constructor(
    private val algoRepository: AlgoRepository
): ViewModel() {
}