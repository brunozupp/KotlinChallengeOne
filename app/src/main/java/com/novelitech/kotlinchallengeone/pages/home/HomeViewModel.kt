package com.novelitech.kotlinchallengeone.pages.home

import androidx.lifecycle.ViewModel
import com.novelitech.kotlinchallengeone.models.TicketModel
import com.novelitech.kotlinchallengeone.models.TypeTicket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import java.util.concurrent.Flow

class HomeViewModel : ViewModel() {

    private val _ticketsAvailables = MutableStateFlow<MutableList<TicketModel>>(mutableListOf())
    val ticketsAvailables = _ticketsAvailables.asStateFlow()

    fun printNewTicket(type: TypeTicket) {

        val ticket = TicketModel(
            number = _ticketsAvailables.value.size,
            type = type,
        )

        // I need to send a different object to the StateFlow be updated
        val aux = _ticketsAvailables.value.toMutableList()
        aux.add(ticket)

        _ticketsAvailables.value = aux
    }
}