package com.skywall.dagger.testing

import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class TestPrinterModule(private val printer: Printer) : PrinterModule() {
    override fun printer(display: Display) = printer
}

class TicketMachineTest {

    private val printerMock = mockk<Printer>(relaxed = true)

    @Test
    fun `given ticket machine when adult ticket selected and paid then ticket printed`() {
        val ticketMachine = DaggerTicketMachineComponent.builder()
            .printerModule(TestPrinterModule(printerMock))
            .build()
            .ticketMachine

        ticketMachine.selectTicket(TicketType.ADULT)
        ticketMachine.insertCoin()
        ticketMachine.insertCoin()

        verify { printerMock.printTicket(TicketType.ADULT) }
    }
}