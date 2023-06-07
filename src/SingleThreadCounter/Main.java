package SingleThreadCounter;

/**
 * Вывод должен соответствовать
 * 3111048 - total zero bit in file 'test2.docx'
 * 3111048 - total zero bit in file 'test2.docx'
 * 262 - total zero bit in file 'TestFile.txt'
 *
 * @author Vladislav Shilov
 */
public class Main {
    public static void main(String[] args) {

        String fileName1 = "test2.docx";
        String fileName2 = "TestFile.txt";

        System.out.format("%d - total zero bit in file '%s'\n", ZeroCounterSingleThread.countZerosInFile(fileName1), fileName1);
        System.out.format("%d - total zero bit in file '%s'\n", ZeroCounterSingleThread.countZerosInFile(fileName1), fileName1);
        System.out.format("%d - total zero bit in file '%s'\n", ZeroCounterSingleThread.countZerosInFile(fileName2), fileName2);
    }
}
