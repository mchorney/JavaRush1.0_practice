package com.javarush.test.level18.lesson05.task03;

/* Разделение файла
Считать с консоли три имени файла: файл1, файл2, файл3.
Разделить файл1 по следующему критерию:
Первую половину байт записать в файл2, вторую половину байт записать в файл3.
Если в файл1 количество байт нечетное, то файл2 должен содержать бОльшую часть.
Закрыть потоки. Не использовать try-with-resources
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();
        FileInputStream inputStream = new FileInputStream(file1);
        int half = inputStream.available()/2 + inputStream.available()%2;
        FileOutputStream outputStream1 = new FileOutputStream(file2);
        FileOutputStream outputStream2 = new FileOutputStream(file3);

        if (inputStream.available() > 0) {

            byte[] buffer = new byte[inputStream.available()];
            int count = inputStream.read(buffer);
            outputStream1.write(buffer, 0, half);
            outputStream2.write(buffer, half, count - half);

        }
        reader.close();
        inputStream.close();
        outputStream1.close();
        outputStream2.close();

    }
}
