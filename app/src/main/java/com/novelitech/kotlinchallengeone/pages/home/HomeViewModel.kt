package com.novelitech.kotlinchallengeone.pages.home

import androidx.lifecycle.ViewModel
import com.novelitech.kotlinchallengeone.models.TicketModel
import com.novelitech.kotlinchallengeone.models.TypeTicket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel : ViewModel() {

    private val _ticketsAvailables = MutableStateFlow<MutableList<TicketModel>>(mutableListOf())
    val ticketsAvailables = _ticketsAvailables.asStateFlow()

    private val ticketsFromToday = _ticketsAvailables.value.toMutableList()

    fun printNewTicket(type: TypeTicket) {

        val ticket = TicketModel(
            number = ticketsFromToday.size,
            type = type,
        )

        // a thread-safe way to push to MutableStateFlow introduced in kotlinx.coroutines 1.5.1
        // it is especially useful when you copy an existing state and change its value(s) to produce a new state
        // e.g. _userFlow.update { it.copy(title = "Something") }
        _ticketsAvailables.update { old ->
            val aux = old.toMutableList()
            aux.add(ticket)
            aux
        }

        // I need to send a different object to the StateFlow be updated
//        val aux = _ticketsAvailables.value.toMutableList()
//        aux.add(ticket)
//        _ticketsAvailables.value = aux

        ticketsFromToday.add(ticket)
    }

    fun nextTicket() : TicketModel? {

        var ticketModel: TicketModel? = null

        if(_ticketsAvailables.value.isNotEmpty()) {

            _ticketsAvailables.update { old ->
//                val aux = old.subList(1, old.size).toMutableList()
//                aux
                val aux = old.toMutableList()
                ticketModel = aux.removeFirst()
                aux
            }

            return ticketModel
        }

        return ticketModel
    }
}