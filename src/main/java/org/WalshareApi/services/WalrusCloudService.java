package org.WalshareApi.services;

import org.WalshareApi.dto.response.walrus.WalrusUploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpMethod.PUT;

@Service
public class WalrusCloudService implements CloudService {

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public String upload(MultipartFile file) {
        String walrusUrl = "https://publisher.walrus-testnet.walrus.space/v1/blobs";
        return extractBlobIdFrom(restTemplate.exchange(walrusUrl, PUT,
                buildUploadRequest(file), WalrusUploadResponse.class,
                createQueryParams()));
    }


    @Override
    public byte[] getFileBy(String blobId) {
        String walrusAggregator = "https://aggregator.walrus-testnet.walrus.space/v1/blobs/";
        ResponseEntity<byte[]> response = restTemplate.getForEntity(walrusAggregator.concat(blobId),
                byte[].class);
        return response.getBody();
    }


    private static String extractBlobIdFrom(ResponseEntity<WalrusUploadResponse> response) {
        WalrusUploadResponse walrusUploadResponse = response.getBody();
        boolean isFileAlreadyExists = walrusUploadResponse != null
                && walrusUploadResponse.getNewlyCreated() == null;
        if (isFileAlreadyExists) return walrusUploadResponse.getAlreadyCertified().getBlobId();
        assert walrusUploadResponse != null;
        return walrusUploadResponse.getNewlyCreated().getBlobObject().getBlobId();
    }


    public HttpEntity<?> buildUploadRequest(MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        Resource resource = file.getResource();
        return new HttpEntity<>(resource, headers);
    }


    private Map<String, ?> createQueryParams() {
        Map<String, Object> params = new HashMap<>();
        String epoch = "5";
        params.put("epoch", Integer.parseInt(epoch));
        String walrusUploadAddress = "0x202f0872cf991ed2091c1a949891bd2b45cdc2a687343a54064244d3066b43b6";
        params.put("send_object_to", walrusUploadAddress);
        return params;
    }
}
