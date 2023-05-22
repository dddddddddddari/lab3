
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Map<String, Integer> freqDict = new HashMap<>();
        File folder = new File("C://Users//User//IdeaProjects");
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (File file : folder.listFiles()) {
            if (file.getName().toLowerCase().endsWith(".txt")) {
                executor.execute(() -> processFile(file, freqDict));
            }
        }
        executor.shutdown();
        while (!executor.isTerminated()) {
        }
        List<Map.Entry<String, Integer>> sortedList = new ArrayList<>(freqDict.entrySet());
        sortedList.sort(Collections.reverseOrder(Map.Entry.comparingByValue()));
        for (int i = 0; i < sortedList.size() && i < sortedList.size(); i++) {
            System.out.println(sortedList.get(i).getKey() + " : " + sortedList.get(i).getValue());
        }
    }
    private static void processFile(File file, Map<String, Integer> freqDict) {
        try (Scanner input = new Scanner(file)) {
            while (input.hasNext()) {
                String word = input.next();
                word = word.replaceAll("[^A-Za-zА-Яа-я]", "").toLowerCase();
                freqDict.put(file.getPath() + ' ' + word, freqDict.getOrDefault(word, 0) + 1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}