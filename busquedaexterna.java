import java.io.*;

public class ExternalSequentialBlockSearch {

    public static void main(String[] args) throws Exception {
        String filePath = "blocks.txt";
        int blockSize = 4;

        int[] data = {10, 14, 18, 22, 30, 34, 42, 50, 66, 82, 90};
        saveDataInBlocks(filePath, data, blockSize);

        int[] queries = {18, 21, 50, 100};
        for (int q : queries) {
            int result = searchSequential(filePath, q);
            if (result != -1) {
                System.out.println("search(" + q + ") = true (en bloque " + result + ")");
            } else {
                System.out.println("search(" + q + ") = false");
            }
        }
    }
