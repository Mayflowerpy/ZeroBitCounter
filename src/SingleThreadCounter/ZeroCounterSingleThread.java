
package SingleThreadCounter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Класс со статическим методом countZerosInFile(String fileName), возвращающий кол-во нулевых бит в файле.
 * Используется для однопоточного выполнения счета.
 *
 * @author Vladislav Shilov
 */
public class ZeroCounterSingleThread {
    public static long countZerosInFile(String fileName) {

        long totalZerosCount = 0;

        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = bis.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; ++i) {
                    for (int j = 0; j < 8; ++j) {
                        if ((buffer[i] & (1 << j)) == 0) {
                            ++totalZerosCount;
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return totalZerosCount;
    }
}
