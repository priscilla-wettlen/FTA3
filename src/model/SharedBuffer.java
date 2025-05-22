package model;

public class SharedBuffer {
    private Producer product;
    private final int bufferSize = 30;
    private Producer[] productArray = new Producer[bufferSize];

    public int getBufferSize() {
        return bufferSize;
    }
}
