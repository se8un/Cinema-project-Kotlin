package cinema

import java.util.*

fun main() {
    println("Enter the number of rows:")
    val rows = readln().toInt()
    println("Enter the number of seats in each row:")
    val seats = readln().toInt()

    val cinema = MutableList(rows) { MutableList(seats) { 'S' } }

    var numberOfPurchasedTickets = 0
    var percentage = 0.00
    var currentIncome = 0
    val totalIncome =
        if (rows * seats < 60) rows * seats * 10
        else (((rows / 2 + 1) * 8) + (rows / 2 * 10)) * seats

    val menu = """
        1. Show the seats
        2. Buy a ticket
        3. Statistics
        0. Exit
    """.trimIndent()
    println("\n$menu")

    fun showTheSeats() {
        print("\nCinema:\n ")
        for (i in 1..cinema[0].size) print(" $i")
        for (i in 1..cinema.size) print("\n${i} ${cinema[i - 1].joinToString(" ")}")
        println()
    }

    fun buyATicket() {
        try {
            println("\nEnter a row number:")
            val rowNumber = readln().toInt()
            println("Enter a seat number in that row:")
            val seatNumber = readln().toInt()

            if (cinema[rowNumber - 1][seatNumber - 1] == 'B') {
                println("\nThat ticket has already been purchased!")
                buyATicket()
            } else {
                cinema[rowNumber - 1][seatNumber - 1] = 'B'
                val cost = if (rows * seats > 60 && rowNumber > rows / 2) 8 else 10
                println("\nTicket price: $$cost")

                numberOfPurchasedTickets++
                percentage = (numberOfPurchasedTickets.toDouble() / (rows * seats)) * 100
                currentIncome += cost
            }
        } catch (e: IndexOutOfBoundsException) {
            println("\nWrong input!")
            buyATicket()
        }
    }

    fun statistics() {
        println("\nNumber of purchased tickets: $numberOfPurchasedTickets")

        val percentageForm = String.format(Locale.US, "%.2f%%", percentage)
        println("Percentage: $percentageForm")

        println("Current income: \$$currentIncome")

        println("Total income: \$$totalIncome")
    }

    while (true) {
        when (readln().toInt()) {
            1 -> showTheSeats()
            2 -> buyATicket()
            3 -> statistics()
            0 -> return
        }
        println("\n$menu")
    }
}