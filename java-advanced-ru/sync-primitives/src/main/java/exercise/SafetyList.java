package exercise;

import java.util.Arrays;

class SafetyList {
    // BEGIN
    private int[] array;
    private int size;

    public SafetyList(){
        this.array = new int[10];
        size = 0;
    }


    public int getSize() {
        return this.size;
    }

    public synchronized void add( int number) {
        if (array.length  == size) {
            array = Arrays.copyOf(array, size * 3 / 2);
        }
        array[size] = number;
        size++;
    }
    // END
}
