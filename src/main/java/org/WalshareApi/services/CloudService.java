package org.WalshareApi.services;

import org.springframework.web.multipart.MultipartFile;

public interface CloudService {
    String upload(MultipartFile file);
    byte[] getFileBy(String blobId);
}
