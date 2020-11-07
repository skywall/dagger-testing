package com.skywall.dagger.testing

import javax.inject.Inject

enum class TicketType(val coins: Int) {
    ADULT(2),
    CHILD(1),
}

class Printer(
    private val display: Display,
) {
    fun printTicket(selectedTicketType: TicketType) {
        display.show("Ticket printed: ${selectedTicketType.name}")
    }
}

class Display {
    fun show(text: String) { println("> $text") }
}

class CoinCounter {
    private var insertedCoins = 0

    fun insertCoin() { insertedCoins += 1 }

    fun getMissingCoins(selectedTicketType: TicketType): Int {
        return selectedTicketType.coins - insertedCoins
    }

    fun getInsertedCoins() = insertedCoins

    fun reset() { insertedCoins = 0 }
}

class TicketMachine @Inject constructor(
    private val printer: Printer,
    private val display: Display,
    private val coinCounter: CoinCounter,
) {
    private var selectedTicketType: TicketType? = null

    init {
        display.show("Select ticket:")
    }

    fun selectTicket(ticketType: TicketType) {
        selectedTicketType = ticketType
        val missingCoins = coinCounter.getMissingCoins(ticketType)
        display.show("Ticket selected: ${ticketType.name}. Insert coins: $missingCoins")
    }

    fun insertCoin() {
        val selectedTicketType = this.selectedTicketType
        if (selectedTicketType == null) {
            display.show("Returning coin. Select ticket first!")
            return
        }

        coinCounter.insertCoin()
        val missingCoins = coinCounter.getMissingCoins(selectedTicketType)
        when (missingCoins) {
            0 -> {
                printer.printTicket(selectedTicketType)
                reset()
            }
            else -> {
                display.show("Insert coins: $missingCoins")
            }
        }
    }

    fun abort() {
        display.show("Abort. Returning coins: ${coinCounter.getInsertedCoins()}")
        reset()
    }

    private fun reset() {
        selectedTicketType = null
        coinCounter.reset()
        display.show("Select ticket:")
    }
}