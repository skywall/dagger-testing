package com.skywall.dagger.testing

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class TicketMachineModule {

    @Provides
    @Singleton
    fun printer(display: Display) = Printer(display)

    @Singleton
    @Provides
    fun display(): Display = Display()

    @Singleton
    @Provides
    fun coinCounter(): CoinCounter = CoinCounter()
}

@Singleton
@Component(modules = [
    TicketMachineModule::class
])
interface TicketMachineComponent {
    val ticketMachine: TicketMachine
}