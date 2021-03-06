package com.javarush.test.level18.lesson10.home05;

/* Округление чисел
Считать с консоли 2 имени файла
Первый файл содержит вещественные(дробные) числа, разделенные пробелом. Например, 3.1415
Округлить числа до целых и записать через пробел во второй файл
Закрыть потоки. Не использовать try-with-resources
Принцип округления:
3.49 - 3
3.50 - 4
3.51 - 4
-3.49 - -3
-3.50 - -3
-3.51 - -4
*/

import java.io.*;

public class Solution
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String fileName1 = bufferedReader.readLine();
        String fileName2 = bufferedReader.readLine();
        bufferedReader.close();

        BufferedReader reader = new BufferedReader(new FileReader(fileName1));
        FileOutputStream outToFile = new FileOutputStream(fileName2);
        String str = "";
        while ((str = reader.readLine()) != null)
        {
            String[] strs = str.split(" ");
            for (int i = 0; i < strs.length; i++)
            {
                int num = (int) Math.round(Double.parseDouble(strs[i]));
                if (i == strs.length - 1)
                {
                    outToFile.write((num + "").getBytes());
                    break;
                }
                outToFile.write((num + " ").getBytes());
            }
        }
        reader.close();
        outToFile.close();
    }
}
