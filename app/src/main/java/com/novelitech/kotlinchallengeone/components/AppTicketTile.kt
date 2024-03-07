package com.novelitech.kotlinchallengeone.components

import android.graphics.Paint.Style
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.novelitech.kotlinchallengeone.models.TicketModel
import com.novelitech.kotlinchallengeone.models.TypeTicket

@Composable
fun AppTicketTile(
    ticket: TicketModel
) {

    val colorNumber = Color(
        if(ticket.type == TypeTicket.NORMAL) 0xFF3477eb else 0xFF5b0aab
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFe8eaeb))
            .padding(16.dp)
    ) {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Bold,
                        color = colorNumber
                    ),
                ) {
                    append(ticket.number.toString())
                }
                append(" | ")
                append(ticket.type.name)
            },
            fontSize = 20.sp,
        )
    }
}