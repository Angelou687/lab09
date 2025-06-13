package estructura;

public class ArrayList<T> {
    private Object[] elements;
    private int size;

    public ArrayList() {
        elements = new Object[10];
        size = 0;
    }

    public void add(T element) {
        if (size == elements.length) {
            resize();
        }
        elements[size++] = element;
    }

    public void add(int index, T element) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Índice fuera de rango: " + index);
        }

        if (size == elements.length) {
            resize();
        }

        // Mueve los elementos hacia la derecha desde el índice dado
        for (int i = size; i > index; i--) {
            elements[i] = elements[i - 1];
        }

        elements[index] = element;
        size++;
    }

    
    @SuppressWarnings("unchecked")
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Índice fuera de rango: " + index);
        }
        return (T) elements[index];
    }
    
    public void set(int index, T element) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Índice fuera de rango: " + index);
        }
        elements[index] = element;
    }

    public int size() {
        return size;
    }

    public void clear() {
        size = 0;
        elements = new Object[10];
    }

    private void resize() {
        Object[] newElements = new Object[elements.length * 2];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    public boolean contains(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i].equals(element)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Índice fuera de rango: " + index);
        }
        @SuppressWarnings("unchecked")
        T removedElement = (T) elements[index];

        // Desplazar elementos a la izquierda desde index + 1
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // ayuda GC
        return removedElement;
    }
}
