import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        List<String> names = readNames("data/female_names.txt");

        HashTable table1 = new HashTableHash1();
        HashTable table2 = new HashTableHash2();

        long timeInsert1 = measureTime(() -> {
            for (String name : names) table1.insert(name);
        });

        long timeInsert2 = measureTime(() -> {
            for (String name : names) table2.insert(name);
        });

        long timeSearch1 = measureTime(() -> {
            for (String name : names) table1.search(name);
        });

        long timeSearch2 = measureTime(() -> {
            for (String name : names) table2.search(name);
        });

        System.out.println("\n--- RELATÓRIO FINAL ---");

        printReport("Hash Function 1", table1, timeInsert1, timeSearch1);
        printReport("Hash Function 2", table2, timeInsert2, timeSearch2);
    }

    private static void printReport(String label, HashTable table, long timeInsert, long timeSearch) {
        System.out.println("\n" + label);
        System.out.println("Colisões totais: " + table.getCollisions());
        System.out.printf("Tempo de inserção: %.2f ms\n", timeInsert / 1_000_000.0);
        System.out.printf("Tempo de busca: %.2f ms\n", timeSearch / 1_000_000.0);

        System.out.println("Distribuição de chaves:");
        int[] dist = table.getDistribution();
        int[] colisoes = table.getCollisionsPerSlot();
        for (int i = 0; i < dist.length; i++) {
            System.out.printf("Posição %2d: %2d elementos (%d colisões nessa posição)\n", i, dist[i], colisoes[i]);
        }


    }

    public static List<String> readNames(String filepath) {
        List<String> names = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = br.readLine()) != null) {
                names.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return names;
    }

    public static long measureTime(Runnable task) {
        long start = System.nanoTime();
        task.run();
        return System.nanoTime() - start;
    }

}
