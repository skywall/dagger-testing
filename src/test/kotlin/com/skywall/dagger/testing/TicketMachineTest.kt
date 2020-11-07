package com.skywall.dagger.testing

import dagger.Component
import dagger.Module
import dagger.Provides
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import javax.inject.Singleton

@Module
class TestPrinterModule(private val printer: Printer) {

    @Provides
    @Singleton
    fun printer() = printer
}

@Singleton
@Component(modules = [
    TicketMachineModule::class,
    TestPrinterModule::class
])
interface TestTicketMachineComponent {
    val ticketMachine: TicketMachine
}

class TicketMachineTest {

    private val printerMock = mockk<Printer>(relaxed = true)

    @Test
    fun `given ticket machine when adult ticket selected and paid then ticket printed`() {
        val ticketMachine = DaggerTestTicketMachineComponent.builder()
            .testPrinterModule(TestPrinterModule(printerMock))
            .build()
            .ticketMachine

        ticketMachine.selectTicket(TicketType.ADULT)
        ticketMachine.insertCoin()
        ticketMachine.insertCoin()

        verify { printerMock.printTicket(TicketType.ADULT) }
    }
}