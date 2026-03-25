package com.example.assigment1ads;

import java.util.Scanner;

public class Assigment1AdsApplication {
    public static void printDigits(int n) {
        if (n < 10) {
            System.out.println(n);
            return;
        }
        printDigits(n / 10);
        System.out.println(n % 10);
    }
    public static int sum(int[] arr, int n) {
        if (n == 0) return 0;
        return arr[n - 1] + sum(arr, n - 1);
    }
    public static boolean isPrime(int n, int i) {
        if (n <= 2) return (n == 2);
        if (n % i == 0) return false;
        if (i * i > n) return true;
        return isPrime(n, i + 1);
    }
    public static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }
    public static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }
    public static int power(int a, int n) {
        if (n == 0) return 1;
        return a * power(a, n - 1);
    }
    public static void reversePrint(int n, Scanner sc) {
        if (n == 0) return;
        int x = sc.nextInt();
        reversePrint(n - 1, sc);
        System.out.print(x + " ");
    }
    public static boolean isDigits(String s, int i) {
        if (i == s.length()) return true;
        if (!Character.isDigit(s.charAt(i))) return false;
        return isDigits(s, i + 1);
    }
    public static int countChars(String s) {
        if (s.equals("")) return 0;
        return 1 + countChars(s.substring(1));
    }
    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        printDigits(5481);

        int[] arr = {3, 2, 4, 1};
        double avg = (double) sum(arr, arr.length) / arr.length;
        System.out.println("\nAverage: " + avg);

        System.out.println(isPrime(7, 2) ? "Prime" : "Composite");

        System.out.println("Factorial: " + factorial(5));

        System.out.println("Fibonacci: " + fibonacci(5));

        System.out.println("Power: " + power(2, 10));

        System.out.println("Enter n and numbers:");
        int n = sc.nextInt();
        reversePrint(n, sc);
        System.out.println();

        System.out.println(isDigits("123456", 0) ? "Yes" : "No");

        System.out.println("Length: " + countChars("hello"));

        System.out.println("GCD: " + gcd(32, 48));
    }
}
