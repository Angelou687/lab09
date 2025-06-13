package estructura;

public class HashMap<K, V> {
    private ArrayList<K> keys;
    private ArrayList<V> values;

    public HashMap() {
        keys = new ArrayList<>();
        values = new ArrayList<>();
    }

    public void put(K key, V value) {
        int idx = indexOfKey(key);
        if (idx >= 0) {
        	values.set(idx, value);
        } else {
            keys.add(key);
            values.add(value);
        }
    }

    public V get(K key) {
        int idx = indexOfKey(key);
        if (idx >= 0) {
            return values.get(idx);
        }
        return null;
    }

    public boolean containsKey(K key) {
        return indexOfKey(key) >= 0;
    }

    private int indexOfKey(K key) {
        for (int i = 0; i < keys.size(); i++) {
            if (keys.get(i).equals(key)) {
                return i;
            }
        }
        return -1;
    }

    public void clear() {
        keys.clear();
        values.clear();
    }
}
