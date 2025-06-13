package estructura;

public class HashSet<T> {
    private ArrayList<T> elements;

    public HashSet() {
        elements = new ArrayList<>();
    }

    public void add(T element) {
        if (!contains(element)) {
            elements.add(element);
        }
    }

    public boolean contains(T element) {
        for (int i = 0; i < elements.size(); i++) {
            if (elements.get(i).equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        elements.clear();
    }
}
