@file:Suppress("UNUSED_PARAMETER", "ConvertCallChainIntoSequence")

package lesson4.task1

import lesson1.task1.discriminant
import lesson1.task1.quadraticRootProduct
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.collections.listOf as listOf

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
    when {
        y < 0 -> listOf()
        y == 0.0 -> listOf(0.0)
        else -> {
            val root = sqrt(y)
            // Результат!
            listOf(-root, root)
        }
    }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * Из имеющихся целых чисел, заданного через vararg-параметр, сформировать массив их квадратов
 */
fun squares(vararg array: Int) = squares(array.toList()).toTypedArray()

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    if(v.isEmpty()) return 0.0
    var sum = 0.0
    for(element in v) sum += element * element
    return sqrt(sum)
}

/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double {
    if(list.isEmpty()) return 0.0
    return list.sum() / list.count()
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if(list.isEmpty()) return list
    val mean = list.sum() / list.count()
    for(i: Int in 0 until list.count()) list[i] -= mean
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.
 */
fun times(a: List<Int>, b: List<Int>): Int {
    if(a.isEmpty()) return 0
    var sum = 0
    for(i: Int in 0 until a.count()) sum += a[i] * b[i]
    return sum
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0 при любом x.
 */
fun polynom(p: List<Int>, x: Int): Int {
    if(p.isEmpty()) return 0
    var result = p[0]
    for(i: Int in 1 until p.count()) result += p[i] * x.toDouble().pow(i).toInt()
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Int>): MutableList<Int> {
    if(list.isEmpty()) return list
    for(i: Int in list.count() - 1 downTo 1) list[i] = list.subList(0, i + 1).sum()
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
//Проверка числа на простоту
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

fun factorize(n: Int): List<Int> {
    var resultList = listOf<Int>()
    for(i: Int in 2..sqrt(n.toDouble()).toInt()){
        //Если i - делитель n
        if(n % i == 0){
            //Проверяем, не является ли i простым числом
            //Если да, то добавляем в список простых делителей
            if(isPrime(i)) resultList = resultList + i
            //Если нет, то рекурсивно вызываем функцию для этого делителя
            else resultList = resultList + factorize(i)

            //Проверяем, является ли частное (n / i) простым числом
            //Если да, то добавляем в список простых делителей
            if(isPrime(n / i)) resultList = resultList + (n / i)
            //Если нет, то рекурсивно вызываем функцию для этого частного
            else resultList = resultList + factorize(n / i)

            break
        }
    }
    if(resultList.isEmpty()) resultList = resultList + n
    return resultList.sorted()
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 * Множители в результирующей строке должны располагаться по возрастанию.
 */
fun factorizeToString(n: Int): String {
    val list = factorize(n)
    return list.joinToString(separator = "*")
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var list = mutableListOf<Int>()
    var curN = n
    while(curN >= base){
        list.add(0, curN % base)
        curN /= base
    }
    list.add(0, curN)
    return list.toList()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, n.toString(base) и подобные), запрещается.
 */
fun convertToString(n: Int, base: Int): String {
    val convertedNumberList = convert(n, base)
    var resultStr: String = ""
    for(element in convertedNumberList){
        if(element < 10) resultStr += element
        else resultStr += (element + 87).toByte().toChar().toString()
    }
    return resultStr
}

/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result = 0
    val length = digits.count()
    for(i in 0 until length)
        result += digits[length - i - 1] * base.toDouble().pow(i).toInt()
    return result
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 *
 * Использовать функции стандартной библиотеки, напрямую и полностью решающие данную задачу
 * (например, str.toInt(base)), запрещается.
 */
fun decimalFromString(str: String, base: Int): Int {
    val length = str.length
    var list = mutableListOf<Int>()
    for(element in str) {
        if (element in '0'..'9') list.add(element.toString().toInt())
        else list.add(element.toByte().toInt() - 87)
    }
    return decimal(list.toList(), base)
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var resultStr = ""
    var curN = n
    var digit: Int
    //Переводим единицы
    digit = curN % 10
    when {
        digit < 4 -> resultStr = "I".repeat(digit)
        digit == 4 -> resultStr = "IV"
        digit >= 5 && digit < 9 -> resultStr = "V" + "I".repeat(digit - 5)
        digit == 9 -> resultStr = "IX"
        else -> resultStr = ""
    }
    curN /= 10
    //Переводим десятки
    if(curN > 0){
        digit = curN % 10
        when {
            digit < 4 -> resultStr = "X".repeat(digit) + resultStr
            digit == 4 -> resultStr = "XL" + resultStr
            digit >= 5 && digit < 9 -> resultStr = "L" + "X".repeat(digit - 5) + resultStr
            digit == 9 -> resultStr = "XC" + resultStr
            else -> resultStr = resultStr
        }
    }
    curN /= 10
    //Переводим сотни
    if(curN > 0){
        digit = curN % 10
        when {
            digit < 4 -> resultStr = "C".repeat(digit) + resultStr
            digit == 4 -> resultStr = "CD" + resultStr
            digit >= 5 && digit < 9 -> resultStr = "D" + "C".repeat(digit - 5) + resultStr
            digit == 9 -> resultStr = "CM" + resultStr
            else -> resultStr = resultStr
        }
    }
    curN /= 10
    //Переводим тысячи
    if(curN > 0) resultStr = "M".repeat(curN) + resultStr
    return resultStr
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
//Название одной цифры (0 - 9)
fun digitName(n: Int, isThousands: Boolean): String {
    return when(n){
        1 -> if(!isThousands) "один" else "одна"
        2 -> if(!isThousands) "два" else "две"
        3 -> "три"
        4 -> "четыре"
        5 -> "пять"
        6 -> "шесть"
        7 -> "семь"
        8 -> "восемь"
        9 -> "девять"
        else -> ""
    }
}

//Название десятков (10 - 19)
fun firstDecadeName(n: Int): String {
    return when(n){
        10 -> "десять"
        11 -> "одиннадцать"
        12 -> "двенадцать"
        13 -> "тринадцать"
        14 -> "четырнадцать"
        15 -> "пятнадцать"
        16 -> "шестнадцать"
        17 -> "семнадцать"
        18 -> "восемнадцать"
        19 -> "девятнадцать"
        else -> ""
    }
}

//Название десятков (20 - 90)
fun decadeName(n: Int): String{
    return when(n){
        2 -> "двадцать"
        3 -> "тридцать"
        4 -> "сорок"
        5 -> "пятьдесят"
        6 -> "шестьдесят"
        7 -> "семьдесят"
        8 -> "восемьдесят"
        9 -> "девяносто"
        else -> ""
    }
}

//Название сотен
fun hundredName(n: Int): String {
    return when(n){
        1 -> "сто"
        2 -> "двести"
        3 -> "триста"
        4 -> "четыреста"
        5 -> "пятьсот"
        6 -> "шестьсот"
        7 -> "семьсот"
        8 -> "восемьсот"
        9 -> "девятьсот"
        else -> ""
    }
}

//Пропись трёх младших разрядов
fun threeDigitNumber(n: Int, isThousands: Boolean): String {
    var strResult: String
    var tempNum = n
    //Проверяем десятки
    if(tempNum % 100 in 10..19){
        //Десятки
        strResult = firstDecadeName(tempNum % 100)
        tempNum /= 10
    }
    else {
        //Единицы
        strResult = digitName(tempNum % 10, isThousands)
        tempNum /= 10
        //Десятки
        if(tempNum > 0 && tempNum % 10 != 0) {
            strResult = decadeName(tempNum % 10) + " " + strResult
        }
    }
    strResult.trim()
    tempNum /= 10
    //Сотни
    if(tempNum > 0 && tempNum % 10 != 0){
        strResult = hundredName(tempNum % 10) + " " + strResult
        tempNum /= 10
    }
    return strResult.trim()
}

fun russian(n: Int): String {
    var strResult: String
    val thousands = n / 1000
    //Сотни, десятки и единицы
    strResult = threeDigitNumber(n % 1000, false)
    //Тысячи
    if(thousands != 0) {
        val thousandStr: String
        if(thousands % 10 == 1 && thousands % 10 != 11) thousandStr = "тысяча"
        else if(thousands % 10 in 2..4) thousandStr = "тысячи"
        else thousandStr = "тысяч"
        strResult = threeDigitNumber(thousands, true) + " " + thousandStr + " " + strResult
    }
    return strResult.trim()
}