package MultiThreadCounter;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.Callable;

/**
 * В классе реализована логика подсчета 0х бит потоком в своей области файла
 */

public class ZeroBitsCounter implements Callable<Integer> {
    private final String fileName;
    private final long startByte;
    private final long endByte;

    public ZeroBitsCounter(String fileName, long startByte, long endByte) {
        this.fileName = fileName;
        this.startByte = startByte;
        this.endByte = endByte;
    }

    @Override
    public Integer call() {
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            int zerosCount = 0;
            int byteCount = 0;
            while ((bytesRead = bis.read(buffer)) != -1) {
                for (int i = 0; i < bytesRead; ++i) {
                    if (byteCount >= startByte && byteCount < endByte) {
                        for (int j = 0; j < 8; ++j) {
                            if ((buffer[i] & (1 << j)) == 0) {
                                ++zerosCount;
                            }
                        }
                    }
                    ++byteCount;
                }
            }
            return zerosCount;
        } catch (IOException e) {
            System.err.println("Error reading file");
            e.printStackTrace(System.err);
        }
        return 0;
    }
}
