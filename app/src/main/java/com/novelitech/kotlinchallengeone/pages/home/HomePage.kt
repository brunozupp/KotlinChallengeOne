package com.novelitech.kotlinchallengeone.pages.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.novelitech.kotlinchallengeone.components.AppButton
import com.novelitech.kotlinchallengeone.components.AppScaffold
import com.novelitech.kotlinchallengeone.components.AppTicketTile
import com.novelitech.kotlinchallengeone.models.TypeTicket
import kotlinx.coroutines.launch

@Composable
fun HomePage() {

    val viewModel = viewModel<HomeViewModel>()

    val tickets = viewModel.ticketsAvailables.collectAsState()

    val scope = rememberCoroutineScope()

    AppScaffold(
        textTopBar = "Tickets"
    ) { innerPadding, snackbarHostState ->
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Get a ticket",
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    AppButton(
                        text = "Normal",
                        onClick = {
                            viewModel.printNewTicket(TypeTicket.NORMAL)
                        },
                        color = 0xFF3477eb,
                        modifier = Modifier.weight(1F)
                    )
                    AppButton(
                        text = "Preferential",
                        onClick = {
                            viewModel.printNewTicket(TypeTicket.PREFERENTIAL)
                        },
                        color = 0xFF5b0aab,
                        modifier = Modifier.weight(1F)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
//            tickets.value.forEach { ticket ->
//                Text("${ticket.number} | ${ticket.type.name}")
//            }
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(tickets.value) { ticket ->
                        AppTicketTile(ticket = ticket)
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset((-16).dp, (-16).dp),
            ) {
                Button(

                    onClick = {
                        val ticket = viewModel.nextTicket()

                        if(ticket != null) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Sendo atendido: ${ticket.number}",
                                    withDismissAction = true,
                                )
                            }
                        }
                    }
                ) {
                    Text("Next")
                }
            }
        }
    }
}