package org.WalshareApi.dto.response.walrus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public class WalrusUploadResponse {

    private NewlyCreated newlyCreated;
    private AlreadyCertified alreadyCertified;

    public NewlyCreated getNewlyCreated() {
        return newlyCreated;
    }

    public void setNewlyCreated(NewlyCreated newlyCreated) {
        this.newlyCreated = newlyCreated;
    }

    public AlreadyCertified getAlreadyCertified() {
        return alreadyCertified;
    }

    public void setAlreadyCertified(AlreadyCertified alreadyCertified) {
        this.alreadyCertified = alreadyCertified;
    }
}
