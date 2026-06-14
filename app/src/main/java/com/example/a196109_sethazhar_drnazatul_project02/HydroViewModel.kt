package com.example.a196109_sethazhar_drnazatul_project02

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HydroViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = HydroDatabase.getDatabase(application).waterLogDao()
    private val repository = HydroRepository(dao)

    // 🔹 UI state
    private val _state = MutableStateFlow(HydrationData())
    val state: StateFlow<HydrationData> = _state

    private val _message = MutableStateFlow("")
    val message: StateFlow<String> = _message

    private val _input = MutableStateFlow("")
    val input: StateFlow<String> = _input

    private val _note = MutableStateFlow("")
    val note: StateFlow<String> = _note

    // 🔹 Room logs
    val logs = repository.allLogs.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    // -------------------------
    // INPUT HANDLERS
    // -------------------------

    fun updateInput(value: String) {
        _input.value = value
    }

    fun updateNote(value: String) {
        _note.value = value
    }

    // -------------------------
    // INSERT WATER LOG
    // -------------------------

    fun logWater() {

        val inputValue = _input.value.toDoubleOrNull()

        if (inputValue != null) {

            _state.value = _state.value.copy(
                current = _state.value.current + inputValue / 1000
            )

            viewModelScope.launch {
                repository.insert(
                    WaterLogEntity(
                        amount = inputValue.toInt(),
                        time = System.currentTimeMillis().toString(),
                        note = _note.value
                    )
                )

                _input.value = ""
                _note.value = ""
            }

            _message.value = "You logged: $inputValue ml 💧"

        } else {
            _message.value = "Invalid input"
        }
    }

    // -------------------------
    // QUICK ADD
    // -------------------------

    fun quickAdd() {

        _state.value = _state.value.copy(
            current = _state.value.current + 0.25
        )

        viewModelScope.launch {
            repository.insert(
                WaterLogEntity(
                    amount = 250,
                    time = System.currentTimeMillis().toString(),
                    note = ""
                )
            )
        }

        _message.value = "Quick add: 250ml 💧"
    }

    // -------------------------
    // DELETE
    // -------------------------

    fun deleteLog(log: WaterLogEntity) {
        viewModelScope.launch {
            repository.delete(log)
        }
    }

    // -------------------------
    // EDIT NOTE ONLY
    // -------------------------

    fun updateLogNote(log: WaterLogEntity, newNote: String) {
        viewModelScope.launch {
            repository.update(
                log.copy(note = newNote)
            )
        }
    }
}