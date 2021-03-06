package com.javarush.test.level15.lesson12.home01;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* Разные методы для разных типов
1. Считать с консоли данные, пока не введено слово "exit".
2. Для каждого значения, кроме "exit", вызвать метод print. Если значение:
2.1. содержит точку '.', то вызвать метод print для Double;
2.2. больше нуля, но меньше 128, то вызвать метод print для short;
2.3. больше либо равно 128, то вызвать метод print для Integer;
2.4. иначе, вызвать метод print для MyString.
*/

public class Solution {
    public static void main(String[] args) throws IOException
    {
        //напиште тут ваш код
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String str;

        while (true)
        {
            str = reader.readLine();
            try{
                if (str.equals("exit")){
                    break;
                } else{
                    if (str.contains(".")){
                        print(Double.parseDouble(str));
                    } else if (Integer.parseInt(str) >= 128){
                        print((Integer) Integer.parseInt(str));

                    } else if ((Short.parseShort(str) > 0) && (Short.parseShort(str) < 128)){
                        print(Short.parseShort(str));
                    }
                }
            }catch(NumberFormatException e){
                print(str);
            }

            }
        }

    public static void print(Double value) {
        System.out.println("Это тип Double, значение " + value);
    }

    public static void print(String value) {
        System.out.println("Это тип MyString, значение " + value);
    }

    public static void print(short value) {
        System.out.println("Это тип short, значение " + value);
    }

    public static void print(Integer value) {
        System.out.println("Это тип Integer, значение " + value);
    }
}
