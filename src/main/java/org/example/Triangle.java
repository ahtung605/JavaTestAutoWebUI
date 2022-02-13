package org.example;

import java.util.Scanner;

/**
 * @author Konstantin Babenko
 * @create 13.02.2022
 */

public class Triangle {
    public static void main(String[] args) throws checkNumbersException {

        /*  Напишите функцию, вычисляющую площадь треугольника по трём сторонам (int a, int b, int c).
            Разместите класс с функцией в src/main/java.
        */

        // получаем стороны треугольника
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите первую сторону. a = ");
        int a = sc.nextInt();
        System.out.print("Введите вторую сторону. b = ");
        int b = sc.nextInt();
        System.out.print("Введите третью сторону. c = ");
        int c = sc.nextInt();
        sc.close();

        checkNumbers(a, b, c);

        if (isTriangle(a, b, c)) {
            System.out.println("Стороны не образуют треугольник");
        } else {
            System.out.println("Площадь треугольника (main): " + areaTriangle(a, b, c));
        }
    }

    public static double areaTriangle(int a, int b, int c) {
        /** формула Герона для вычисления площади по 3-м сторонам
         S = √(p·(p - a)·(p - b)·(p - c)), P = (a+b+c)/2,
         где a, b, c - стороны треугольника, p - полупериметр.
         */
        // полупериметр для формулы Герона
        double p = (double) (a + b + c) / 2;
        // определяем площадь по формуле с одновременной проверкой на возможность образования треугольника
        return (isTriangle(a, b, c)) ? 0 : Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }

    public static boolean isTriangle(int a, int b, int c) {
        // проверяем получается ли треугольник
        return (a + b < c || a + c < b || b + c < a);
    }

    // для проверки символов создаем свое исключение checkNumbersException наследником от Exception
    public static class checkNumbersException extends Exception {
        public checkNumbersException(String message) {
            super(message);
        }
    }

    // проверка чисел
    public static void checkNumbers(int a, int b, int c) throws checkNumbersException {
        if (a <= 0 || b <= 0 || c <= 0) throw new checkNumbersException("Число не может быть нулем или отрицательным!");
    }
}