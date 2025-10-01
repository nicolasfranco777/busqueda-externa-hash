import java.io.*;
public class busquedaexterna {
    public static void main(String[] args) throws Exception {
        String filePath = "bloques.txt";
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

    static void saveDataInBlocks(String path, int[] data, int blockSize) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            int count = 0;
            for (int num : data) {
                writer.write(num + " ");
                count++;
                if (count == blockSize) {
                    writer.newLine(); 
                    count = 0;
                }
            }
            if (count != 0) writer.newLine(); 
        }
    }

    static int searchSequential(String path, int key) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String block;
            int blockNumber = 0;
            while ((block = reader.readLine()) != null) {
                String[] values = block.trim().split(" ");
                for (String v : values) {
                    if (!v.isEmpty() && Integer.parseInt(v) == key) {
                        return blockNumber; 
                    }
                }
                blockNumber++;
            }
        }
        return -1; 
    }
}

