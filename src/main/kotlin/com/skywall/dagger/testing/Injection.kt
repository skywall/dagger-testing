package com.skywall.dagger.testing

import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class PrinterModule {

    @Provides
    @Singleton
    open fun printer(display: Display): Printer = Printer(display)
}

@Module
class TicketMachineModule {

    @Singleton
    @Provides
    fun display(): Display = Display()

    @Singleton
    @Provides
    fun coinCounter(): CoinCounter = CoinCounter()
}

@Singleton
@Component(modules = [
    TicketMachineModule::class,
    PrinterModule::class
])
interface TicketMachineComponent {
    val ticketMachine: TicketMachine
}