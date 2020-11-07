package com.skywall.dagger.testing

import dagger.BindsInstance
import dagger.Component
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import javax.inject.Singleton

@Singleton
@Component(modules = [
    TicketMachineModule::class,
])
interface TestTicketMachineComponent {

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance printer: Printer
        ): TestTicketMachineComponent
    }

    val ticketMachine: TicketMachine
}

class TicketMachineTest {

    private val printerMock = mockk<Printer>(relaxed = true)

    @Test
    fun `given ticket machine when adult ticket selected and paid then ticket printed`() {
        val ticketMachine = DaggerTestTicketMachineComponent.factory()
            .create(printerMock)
            .ticketMachine

        ticketMachine.selectTicket(TicketType.ADULT)
        ticketMachine.insertCoin()
        ticketMachine.insertCoin()

        verify { printerMock.printTicket(TicketType.ADULT) }
    }
}