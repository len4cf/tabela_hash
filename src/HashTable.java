public abstract class HashTable {
    protected int size = 32;
    protected MyLinkedList<String>[] table;
    protected int collisions = 0;
    protected int[] collisionsPerSlot;

    public HashTable() {
        table = new MyLinkedList[size];
        collisionsPerSlot = new int[size];
        for (int i = 0; i < size; i++) {
            table[i] = new MyLinkedList<>();
            collisionsPerSlot[i] = 0;
        }
    }

    protected abstract int hash(String key);

    public void insert(String key) {
        int index = hash(key);
        if (!table[index].isEmpty()) {
            collisions++;
            collisionsPerSlot[index]++;
        }
        table[index].add(key);
    }

    public boolean search(String key) {
        int index = hash(key);
        return table[index].contains(key);
    }

    public void printTable() {
        for (int i = 0; i < size; i++) {
            System.out.print("Slot " + i + ": ");
            table[i].printList();
        }
    }

    public int getCollisions() {
        return collisions;
    }

    public int[] getDistribution() {
        int[] distribution = new int[size];
        for (int i = 0; i < size; i++) {
            distribution[i] = table[i].size();
        }
        return distribution;
    }

    public int[] getCollisionsPerSlot() {
        return collisionsPerSlot;
    }
}
