package MultiThreadCounter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * В данном классе реализуется делигирование задач каждому треду в зависимости от их кол-ва
 */

public class ZeroBitsCounterController {
    private final String fileName;
    private final int threadCount;

    /**
     * В целом можно было использовать Runnable и передавать в него счетчик AtomicLong для изменения
     *
     */
    private Integer counter;

    public ZeroBitsCounterController(String fileName, int threadCount) {
        this.fileName = fileName;
        this.threadCount = threadCount;
        this.counter = 0;
    }

    public long countZeroBits() throws InterruptedException, ExecutionException {

        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        List<ZeroBitsCounter> tasks = new ArrayList<>();

        // Логика деления на блоки
        long fileSize = new File(fileName).length();
        long blockSize = (fileSize / threadCount);
        int remainingBytes = (int) (fileSize % threadCount);
        long startByte = 0;


        // Назначаем, в зависимости от кол-ва потоков, зону работы в файле для каждого треда
        for (int i = 0; i < threadCount; ++i) {
            long endByte = startByte + blockSize;
            if (i == threadCount - 1) {
                endByte += remainingBytes;
            }
            tasks.add(new ZeroBitsCounter(fileName, startByte, endByte));
            startByte = endByte;
        }

        // Запускаем все задачи и получаем лист Future
        List<Future<Integer>> futures = executorService.invokeAll(tasks);

        // Асинхронно получаем наши счетчики каждого потока из Future
        for (Future<Integer> future : futures) {
            counter += future.get();
        }

        // Завершаем работу executorService
        executorService.shutdown();

        return counter;
    }
}