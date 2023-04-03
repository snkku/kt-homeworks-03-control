import org.junit.Assert.*
import org.junit.Test

class MainKtTest {

    @Test
    fun checkLimitsVKPayNormal() {
        val sum = 999.0
        val dailySum = 5_000.0 // < 15_000
        val monthSum = 10_000.0 // < 40_000
        val result = checkLimits("VKPay", sum, dailySum, monthSum)
        assertEquals(false, result)
    }

    @Test
    fun checkLimitsVKPayOverLimit() {
        val sum = 999.0
        val dailySum = 25_000.0 // < 15_000
        val monthSum = 60_000.0 // < 40_000
        val result = checkLimits("VKPay", sum, dailySum, monthSum)
        assertEquals(true, result)
    }

    @Test
    fun checkLimitsVKPayOverLimit1() {
        val sum = 999.0
        val dailySum = 5_000.0 // < 15_000
        val monthSum = 60_000.0 // < 40_000
        val result = checkLimits("VKPay", sum, dailySum, monthSum)
        assertEquals(true, result)
    }

    @Test
    fun checkLimitsVKPayOverLimit2() {
        val sum = 999.0
        val dailySum = 25_000.0 // < 15_000
        val monthSum = 30_000.0 // < 40_000
        val result = checkLimits("VKPay", sum, dailySum, monthSum)
        assertEquals(true, result)
    }


    @Test
    fun checkLimitsNonVKPayNormal() {
        val sum = 999.0
        val dailySum = 65_000.0 // < 150_000
        val monthSum = 360_000.0 // < 600_000
        val result = checkLimits("Visa", sum, dailySum, monthSum)
        assertEquals(false, result)
    }

    @Test
    fun checkLimitsNonVKPayOverLimit() {
        val sum = 999.0
        val dailySum = 205_000.0 // < 150_000
        val monthSum = 660_000.0 // < 600_000
        val result = checkLimits("Visa", sum, dailySum, monthSum)
        assertEquals(true, result)
    }

    @Test
    fun checkLimitsNonVKPayOverLimit1() {
        val sum = 999.0
        val dailySum = 105_000.0 // < 150_000
        val monthSum = 660_000.0 // < 600_000
        val result = checkLimits("Visa", sum, dailySum, monthSum)
        assertEquals(true, result)
    }

    @Test
    fun checkLimitsNonVKPayOverLimit2() {
        val sum = 999.0
        val dailySum = 205_000.0 // < 150_000
        val monthSum = 30_000.0 // < 600_000
        val result = checkLimits("Visa", sum, dailySum, monthSum)
        assertEquals(true, result)
    }

    @Test
    fun checkLimitsDefaults() {
        val sum = 999.0
        val result = checkLimits(newSum = sum)
        assertEquals(false, result)
    }
    @Test
    fun calcBribeMasterCardMaestro() {
        val type = "MasterCard"
        val monthSum = 10_000.0
        val newSum = 15_000.0
        val result = calcBribe(type, monthSum, newSum)
        assertEquals(0, result.toInt())
    }
    @Test
    fun calcBribeMasterCardMaestroBribe1() {
        val type = "MasterCard"
        val monthSum = 76_000.0
        val newSum = 15_000.0
        val result = calcBribe(type, monthSum, newSum)
        assertEquals(110, result.toInt())
    }
    @Test
    fun calcBribeMasterCardMaestroBribe2() {
        val type = "MasterCard"
        val monthSum = 71_000.0
        val newSum = 12.0
        val result = calcBribe(type, monthSum, newSum)
        assertEquals(20.072, result, .3)
    }

    @Test
    fun calcBribeVisaMirBribe1() {
        val type = "Mir"
        val newSum = 12.0
        val result = calcBribe(type, newSum = newSum)
        assertEquals(35.0, result, .3)
    }

    @Test
    fun calcBribeVisaMirBribe2() {
        val type = "Mir"
        val newSum = 5_000.0
        val result = calcBribe(type, newSum = newSum)
        assertEquals(37.5, result, .3)
    }

    @Test
    fun calcBribeVKPayBribe() {
        val type = "VKPay"
        val newSum = 6_000.0
        val result = calcBribe(type, newSum = newSum)
        assertEquals(0, result.toInt())

    }

    @Test
    fun agoTextTest1()
    {
        val int = 10
        val result = agoToText(int)
        assertEquals("только что", result)
    }
    @Test
    fun agoTextTest2()
    {
        val int = 120
        val result = agoToText(int)
        assertEquals("2 минуты назад", result)
    }
    @Test
    fun agoTextTest3()
    {
        val int = 60*60*2
        val result = agoToText(int)
        assertEquals("2 часа назад", result)
    }

    @Test
    fun agoTextTest4()
    {
        val int = 60 * 60 * 25
        val result = agoToText(int)
        assertEquals("вчера", result)
    }
    @Test
    fun agoTextTest5()
    {
        val int = 60 * 60 * 48 + 1
        val result = agoToText(int)
        assertEquals("позавчера", result)
    }

    @Test
    fun agoTextTest6()
    {
        val int = 60 * 60 * 24 * 5
        val result = agoToText(int)
        assertEquals("давно", result)
    }

}