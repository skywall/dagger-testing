package com.skywall.dagger.testing

fun main(args: Array<String>) {
    val ticketMachine = DaggerTicketMachineComponent
        .create()
        .ticketMachine

    ticketMachine.selectTicket(TicketType.ADULT)
    ticketMachine.insertCoin()
    ticketMachine.insertCoin()
}