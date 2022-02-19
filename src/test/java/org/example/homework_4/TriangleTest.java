package org.example.homework_4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.example.homework_4.Triangle.areaTriangle;
import static org.example.homework_4.Triangle.isTriangle;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Konstantin Babenko
 * @create 13.02.2022
 */

public class TriangleTest {
    int a = 5;
    int b = 5;
    int c = 5;
    double areaABC = 10.825317547305483;

    static Logger logger = LoggerFactory.getLogger(TriangleTest.class);

    @Test
    @DisplayName("Тест возможности треугольника по 3 сторонам")
    void myIsTriangleABCTest() {
        logger.info("Тест возможности треугольника по 3 сторонам");
        assertTrue(!isTriangle(a, b, c), "Стороны не образуют треугольник.");
    }

    @ParameterizedTest
    @DisplayName("Тест возможности треугольника по набору данных")
    @CsvSource({"1, 1, 1", "3, 4, 5", "5, 5, 5"})
    void myIsTriangleTrueTest(int first, int second, int third) {
        logger.info("Тест возможности треугольника по набору данных");
        assertTrue(!isTriangle(first, second, third), "Треугольник с заданными сторонами возможен.");
//        assertFalse(isTriangle(first, second, third), "Треугольник с заданными сторонами возможен.");
//        if (isTriangle(first, second, third)) {
//            System.out.println("Стороны ("+ first + ", " + second + ", "  + third + ") не образуют треугольник.");
//        } else {
//            System.out.println("Треугольник с заданными сторонами ("+ first + ", " + second + ", "  + third + ") возможен, его площадь: " + areaTriangle(first, second, third));
//        }
    }

    @ParameterizedTest
    @DisplayName("Тест НЕ возможности треугольника по набору данных")
    @CsvSource({"2, 2, 10", "-1, 0, 5", "-1, 100, 5"})
    void myIsTriangleFalseTest(int first, int second, int third) {
        logger.info("Тест НЕ возможности треугольника по набору данных");
        assertFalse(!isTriangle(first, second, third), "Стороны не образуют треугольник.");
    }

    @Test
    @DisplayName("Тест вычисления площади равностороннего треугольника по 3 сторонам")
    public void equalArealTriangleABCTest() {
        logger.info("Тест вычисления площади равностороннего треугольника по 3 сторона");
        assertEquals(areaABC, areaTriangle(a, b, c), "Ошибка вычисления площади!");
//        System.out.println("Стороны = " + w);
//        System.out.println("Площадь треугольника: " + areaTriangle(w, w, w));
    }

    @ParameterizedTest
    @DisplayName("Тест вычисления")
    // передаем три стороны и ожидаемую площадь
    @CsvSource({"1, 1, 1, 0.4330127018922193", "3, 4, 5, 6", "5, 5, 5, 10.825317547305483"})
    public void equalArealTriangleTest(int first, int second, int third, double area) {
        logger.info("Тест вычисления");
        assertEquals(area, areaTriangle(first, second, third), "Ошибка вычисления площади!");
//        System.out.println("Стороны = " + first + ", " + second + ", " + third + ", ожидаемая площадь - " + area);
//        System.out.println("Площадь треугольника: " + areaTriangle(first, second, third));
    }

    @ParameterizedTest
    @DisplayName("Тест исключения")
    @CsvSource({"-1, 1, 1", "3, 0, 5", "1, 5, -5"})
    void testException(int first, int second, int third) {
        logger.info("Тест исключения");
        Assertions.assertThrows(Triangle.checkNumbersException.class, () -> Triangle.checkNumbers(first, second, third), "Нет исключения");
    }

//------------------------оставил для себя---------------------------
    //    @Test
//    @DisplayName("Вычисление площади треугольника по 3 сторонам")
//    void myPositiveTest() {
//        System.out.println("Площадь треугольника: " + areaTriangle(a, b, c));
//    }
//
//    @Test
//    @DisplayName("Тест вычисления площади треугольника по 3 сторонам")
//    void myIsTriangleTestABC() {
//        if (isTriangle(a, b, c)) {
//            System.out.println("Стороны не образуют треугольник");
//        } else {
//            System.out.println("Треугольник с заданными сторонами возможен, его площадь: " + areaTriangle(a, b, c));
//        }
//    }
//
//    @ParameterizedTest
//    @DisplayName("Тест вычисления площади равностороннего треугольника по 3 сторонам")
//    @ValueSource(ints = {-1, 2, 3, 4, 5})
//    public void equilateralTriangleTest(int w) {
//        System.out.println("Стороны = " + w);
//        System.out.println("Площадь треугольника: " + areaTriangle(w, w, w));
//    }
}
