fun main() {

    println("Петя был(а) " + agoToText(38600))
    println("Миша был(а) " + agoToText(2580))
    println("Ваня был(а) " + agoToText(1600))
    println("Вася был(а) " + agoToText(30))
    println("Ира был(а) " + agoToText(86401))

    processPayment("MasterCard", 30.0, 151_000.0, 250.0)
    processPayment("MasterCard", 300.0, monthSum = 75030.0)
    processPayment("Visa", 0.0, 5000.0)
    processPayment("VKPay", 50000.0)
}

fun getRight(count: Int, one: String, two: String, five: String): String {
    return when {
        count % 100 in 5..20 -> five
        count % 10 == 1 -> one
        count % 10 in 2..4 -> two
        else -> five
    }
}

fun agoToText(seconds: Int): String {
    return when (seconds) {
        in 0..60 -> "только что"
        in 61..60 * 60 -> (seconds / 60).toString() + " " + getRight(
            seconds / 60,
            "минуту",
            "минуты",
            "минут"
        ) + " назад"

        in 60 * 60 + 1..24 * 60 * 60 -> (seconds / 60 / 60).toString() + " " + getRight(
            seconds / 60 / 60,
            "час",
            "часа",
            "часов"
        ) + " назад"

        in 60 * 60 * 24..60 * 60 * 48 -> "вчера"
        in 60 * 60 * 48..60 * 60 * 72 -> "позавчера"
        else -> "давно"
    }
}

fun processPayment(type: String = "VKPay", newSum: Double, dailySum: Double = 0.0, monthSum: Double = 0.0) {
    if (checkLimits(type, newSum, dailySum, monthSum)) {
        println("Платеж на сумму $newSum с типом $type отклонён по причине превышения лимита!")
        return
    }
    val bribe: Double = calcBribe(type, monthSum, newSum)
    if (bribe > 0.0)
        println("Комиссия по платежу на $newSum руб с типом $type составляет $bribe руб")
    else
        println("Комиссия по платежу на $newSum руб с типом $type отсутствует")
}

fun checkLimits(type: String = "VKPay", newSum: Double, dailySum: Double = 0.0, monthSum: Double = 0.0): Boolean {
    return when {
        type == "VKPay" && dailySum <= 15_000.0 && monthSum <= 40_000.0 -> false
        type != "VKPay" && dailySum <= 150_000.0 && monthSum <= 600_000.0 -> false
        else -> true
    }
}

fun calcBribe(type: String = "VKPay", monthSum: Double = 0.0, newSum: Double): Double {
    return when (type) {
        "MasterCard", "Maestro" -> {
            if (newSum >= 300 && monthSum < 75_000) 0.0 else newSum * 0.006 + 20
        }

        "Visa", "Mir" -> {
            if (newSum * 0.0075 < 35) 35.0 else newSum * 0.0075
        }

        "VKPay" -> 0.0
        else -> throw Exception("Неизвестный тип карты")
    }
}
