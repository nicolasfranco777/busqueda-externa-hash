import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class busquedarexterna {

    public static void main(String[] args) throws Exception {
        ExternalHash index = new ExternalHash("buckets", 4); 

        
        int[] keys = {10, 14, 18, 22, 30, 34, 42, 50, 66, 82};
        for (int key : keys) {
            index.insert(key);
        }

     
        int[] queries = {10, 11, 42, 50, 99};
        for (int q : queries) {
            System.out.printf("contains(%d) = %s%n", q, index.contains(q));
        }
    }
}

class ExternalHash {
    private final String folderPath;
    private final int bucketCount;

    public ExternalHash(String folderPath, int bucketCount) {
        this.folderPath = folderPath;
        this.bucketCount = bucketCount;

        
        File folder = new File(folderPath);
        if (!folder.exists()) folder.mkdir();
    }

    private int hash(int key) {
        return Math.floorMod(key, bucketCount);
    }

    public void insert(int key) throws IOException, ClassNotFoundException {
        int bucket = hash(key);
        Map<Integer, Boolean> map = loadBucket(bucket);
        map.put(key, true);
        saveBucket(bucket, map);
    }

    public boolean contains(int key) throws IOException, ClassNotFoundException {
        int bucket = hash(key);
        Map<Integer, Boolean> map = loadBucket(bucket);
        return map.containsKey(key);
    }

    @SuppressWarnings("unchecked")
    private Map<Integer, Boolean> loadBucket(int bucket) throws IOException, ClassNotFoundException {
        File file = new File(folderPath + "/bucket_" + bucket + ".ser");
        if (!file.exists()) return new HashMap<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Map<Integer, Boolean>) ois.readObject();
        }
    }

    private void saveBucket(int bucket, Map<Integer, Boolean> map) throws IOException {
        File file = new File(folderPath + "/bucket_" + bucket + ".ser");
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(map);
        }
    }
}
