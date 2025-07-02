package org.WalshareApi.controller;

import org.WalshareApi.services.CloudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("api/v1/walrus")
public class WalrusController {

    @Autowired
    private CloudService cloudService;

    @PostMapping("/hello")
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("Hello, Walrus!");
    }


    @PostMapping("/uploadImage")
    public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile image) {
        try {
            String blobId = cloudService.upload(image);
            return ResponseEntity.ok(blobId);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Upload failed: " + e.getMessage());
        }
    }

//    YIdgnGMPJ-AvL_BBIjVTP15R9hDEi2lKFe57_BBHElY
    @GetMapping("/getImage/{blobId}")
    public ResponseEntity<byte[]> getImage(@PathVariable String blobId) {
        try {
            byte[] imageData = cloudService.getFileBy(blobId);
            return ResponseEntity.ok(imageData);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
