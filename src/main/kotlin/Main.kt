fun main()
{

    println("Петя был(а) " + agoToText(38600))
    println("Миша был(а) " + agoToText(2580))
    println("Ваня был(а) " + agoToText(1600))
    println("Вася был(а) " + agoToText(30))
    println("Ира был(а) " + agoToText(86401))

    println(calcBribe("MasterCard", 30.0, 250.0))
    println(calcBribe("MasterCard", 300.0, 75030.0))
    println(calcBribe("Visa", 0.0, 5000.0))
}

fun getRight(count:Int, one:String, two:String, five:String): String
{
    return when {
        count % 100 in 5..20 -> five
        count % 10 == 1 -> one
        count % 10 in 2..4 -> two
        else -> five
    }
}

fun agoToText(seconds:Int): String
{
    return when (seconds) {
        in 0..60 -> "только что"
        in 61..60*60 -> (seconds/60).toString() + " " + getRight(seconds/60, "минуту", "минуты", "минут") + " назад"
        in 60*60+1..24*60*60 -> (seconds/60/60).toString() + " " + getRight(seconds/60/60, "час", "часа", "часов") + " назад"
        in 60*60*24..60*60*48 -> "вчера"
        in 60*60*48.. 60*60*72 -> "позавчера"
        else -> "давно"
    }
}

fun calcBribe(type: String, lastSum: Double, transferSum: Double): Double {
    return when (type)
    {
        "MasterCard", "Maestro" -> {
            if (transferSum + lastSum < 75000) 0.0 else transferSum * 0.006 + 20
        }
        "Visa", "Mir" -> {
            if (transferSum * 0.0075 < 35) 35.0 else transferSum * 0.0075
        }
        "VKPay" -> 0.0
        else -> 0.0
    }
}
