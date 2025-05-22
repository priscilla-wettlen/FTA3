package model;

public class Producer {
    private int productId;
    private SharedBuffer sharedBuffer;

    public Producer(int productId, SharedBuffer sharedBuffer) {
        this.productId = productId;
        this.sharedBuffer = sharedBuffer;
    }

    public int getId() {
        return productId;
    }

    public void deliverProducts(){
        for(int i = 0; i < sharedBuffer.getBufferSize(); i++ ){

        }
    }
}
