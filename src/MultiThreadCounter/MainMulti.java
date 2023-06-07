package MultiThreadCounter;

import java.util.concurrent.ExecutionException;

/**
 * Вывод должен соответствовать
 * 3111048 - total zero bit in file 'test2.docx'
 * 262 - total zero bit in file 'TestFile.txt'
 *
 * @author Vladislav Shilov
 */
public class MainMulti {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

//        Можно использовать максимальное кол-во доступных потоков процессора
//        int maxThreads = Runtime.getRuntime().availableProcessors();

        String fileName1 = "test2.docx";
        ZeroBitsCounterController controller1 = new ZeroBitsCounterController(fileName1, 4);
        long zeroBitsCount1 = controller1.countZeroBits();
        System.out.format("%d - total zero bit in file '%s'\n", zeroBitsCount1, fileName1);


        String fileName2 = "TestFile.txt";
        ZeroBitsCounterController controller2 = new ZeroBitsCounterController(fileName2, 2);
        long zeroBitsCount2 = controller2.countZeroBits();
        System.out.format("%d - total zero bit in file '%s'\n", zeroBitsCount2, fileName2);

    }
}