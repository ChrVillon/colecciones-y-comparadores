/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.ejemplppilas;

import java.util.Comparator;


/**
 *
 * @author Usuario
 */

import java.util.Stack;


public class EjemplosPilas {
    
    public static String convertirAPosfijo (String infija) {
        String postfija = "";
        Stack<Character> s = new Stack<>();
        for (int i = 0; i < infija.length(); i++) {
            char c = infija.charAt(i);
            if (isOperand(c)) {
                postfija += c;
            } else { // es operador
                if (s.isEmpty()) {  
                    s.push(c);
                } else {
                    int p1 = getPriority(c);
                    int p2 = getPriority(s.peek());
                    if (p1 > p2) {
                        s.push(c);
                    } else {
                        while (!s.isEmpty() && p1 <= getPriority(s.peek())) {
                            Character p = s.pop();
                            postfija += p;
                        }
                        s.push(c);
                    }
                }
            }
        }
        while (!s.isEmpty()) {
            postfija += s.pop();
        }
        return postfija;
    }
    
    
    public static boolean isCorrect (String expression) {
        Stack<Character> s = new Stack();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c == '(') {
                s.push(c);
            } else if (c == ')') {
                try {
                    s.pop();
                } catch (Exception e) {
                    return false;
                }
            }
        }
        return s.isEmpty();
    }
    
    
    
    
    
    

    public static void main(String[] args) {
        
        
//        Stack<Integer> s = new Stack<>();
//        Deque<Integer> s = new ArrayDeque<>(); // pila estática
//        Deque<Integer> s = new LinkedList<>(); // pila dinámica
//        
//        s.push(2);
//        s.push(4);
//        s.push(8);
//        s.push(5);
        
        
//        Iterator<Integer> it = s.iterator();
//        while (it.hasNext()) {
//            Integer next = it.next();
//            System.out.println(next);
//        }
        
        
//        while (!s.isEmpty()) {            
//            Integer n = s.pop();
//            System.out.println(n);
//        }
        
//        if (!s.isEmpty()) {
//            Integer n = s.pop();
//            System.out.println(n);
//        } else {
//            System.out.println("vacía");
//        }
        
        
        
        String infija = "A+B*C-D";
        String postfija = convertirAPosfijo(infija);
        
        System.out.println("infija: " + infija + " postfija: " + postfija);
        System.out.println("Respuesta correcta: ABC*+D-");
    }

    public static void m1() {
        System.out.println("m1");
        m2();
    }

    public static void m2() {
        System.out.println("m2");
        m3();
        m4();
    }

    public static void m3() {
        System.out.println("m3");

    }

    public static void m4() {
        System.out.println("m4");
        m5();
    }

    public static void m5() {
        System.out.println("m5");
    }

    private static boolean isOperand(char c) {
        return !isOperator (c);
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/'; 
    }

    private static int getPriority(char c) {
        if (c == '+' || c == '-') {
            return 0;
        } else if (c == '*' || c == '/') {
            return 1;
        }
        return 2;
    }
    

