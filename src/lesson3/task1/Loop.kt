@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import java.lang.Math.pow
import kotlin.math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    var temp = n
    var digitCount = 1
    while (temp / 10 > 0) {
        temp /= 10
        digitCount++
    }
    return digitCount
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    var result = 1
    if(n >= 3) result = fib(n - 1) + fib(n - 2)
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    val min = min(abs(m), abs(n))
    val max = max(abs(m), abs(n))
    for(i in 1..max) {
        if(min * i % max == 0) return min * i
    }
    return 0
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    for(i: Int in 2..n){
        if(n % i == 0) return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    for(i: Int in (n - 1) downTo 1) {
        if(n % i == 0) return i
    }
    return 1
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    val min = min(m, n)
    for(i: Int in 2..min){
        if(n % i == 0 && m % i == 0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    for(i in ceil(sqrt(m.toFloat())).toInt()..floor(sqrt(n.toFloat())).toInt()){
        if(i * i in m..n) return true
    }
    return false
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    val stepCount: Int //количество требуемых шагов
    if (x == 1) stepCount = 0
    else if(x % 2 == 0) stepCount = collatzSteps(x / 2) + 1
    else stepCount = collatzSteps(3 * x + 1) + 1
    return stepCount
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    val simpleX = x % (2 * PI);
    var member = 1.0;
    var i: Int = 2;
    var result: Double = 1.0;
    while(abs(member * simpleX) >= eps){
        member = (-1.0).pow(i / 2) * simpleX.pow(i) / factorial(i + 1);
        result += member;
        i += 2;
    }
    return simpleX * result;
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    val simpleX = x % (2 * PI);
    var member: Double = 0.0;
    var i: Int = 2;
    var result: Double = 0.0;
    do {
        member = (-1.0).pow(i / 2) * simpleX.pow(i) / factorial(i);
        result += member;
        i += 2;
    } while(abs(member) >= eps);
    return 1 + result;
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var numberLen: Int = 0;
    var result: Int = 0;
    do{
        numberLen += 1;
        result = result * 10 + (n / floor((10.0).pow(numberLen - 1)).toInt()) % 10

    } while(n / floor((10.0).pow(numberLen)).toInt() > 0)
    return result;
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean {
    val numberLen: Int = digitNumber(n);
    if(numberLen == 1) return true;
    for(i: Int in 1..(numberLen / 2)){
        val digit1: Int = (n / floor((10.0).pow(i - 1)).toInt()) % 10;
        val digit2: Int = (n / floor((10.0).pow(numberLen - i)).toInt()) % 10;
        if(digit1 != digit2) return false
    }
    return true
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    val firstDigit: Int = n % 10;
    var currentNumber: Int = n / 10;
    for(i: Int in 2..digitNumber(n)) {
        if(currentNumber % 10 != firstDigit) return true;
        currentNumber /= 10;
    }
    return false;
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun squareSequenceDigit(n: Int): Int {
    var currentLen: Int = 0; //текущая длина пройденной последовательности (суммарное количество цифр в уже обработанных квадратах)
    var i = 1;
    var result: Int = 1; //искомая цифра последовательности
    while(currentLen < n){
        val numberLen = digitNumber(i * i);
        //Если мы дошли до нужного квадрата
        if(currentLen + numberLen >= n){
            val digitNum = currentLen + numberLen - n + 1;
            result = (i * i / floor((10.0).pow(digitNum - 1)).toInt()) % 10;
        }
        i++;
        currentLen += numberLen;
    }
    return result;
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var currentLen: Int = 0; //текущая длина пройденной последовательности (суммарное количество цифр в уже обработанных числах Фибоначчи)
    var i = 1;
    var result: Int = 1; //искомая цифра последовательности
    while(currentLen < n) {
        val fibNumber: Int = fib(i);
        val numberLen = digitNumber(fibNumber);
        //Если мы дошли до нужного квадрата
        if(currentLen + numberLen >= n){
            val digitNum = currentLen + numberLen - n + 1;
            result = (fibNumber / floor((10.0).pow(digitNum - 1)).toInt()) % 10;
        }
        i++;
        currentLen += numberLen;
    }
    return result;
}