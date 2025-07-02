package org.WalshareApi.dto.response.walrus;


public class NewlyCreated {
    private BlobObject blobObject;
    private int cost;

    public BlobObject getBlobObject() {
        return blobObject;
    }

    public void setBlobObject(BlobObject blobObject) {
        this.blobObject = blobObject;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
