package com.tinqin.cms.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

//@Entity
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String filename;
    private String fileType;
    private LocalDateTime uploadTime;
    private byte[] data;

    // Getters and setters
}

//    public FileEntity storeFile(MultipartFile file) throws IOException {
//        FileEntity fileEntity = new FileEntity();
//        fileEntity.setFilename(file.getOriginalFilename());
//        fileEntity.setFileType(file.getContentType());
//        fileEntity.setData(file.getBytes());
//        fileEntity.setUploadTime(LocalDateTime.now());
//        return fileRepository.save(fileEntity);
//    }
//
//    @GetMapping("/download/{filename}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) {
//        Optional<FileEntity> fileEntityOptional = fileService.getFile(filename);
//
//        if (fileEntityOptional.isPresent()) {
//            FileEntity fileEntity = fileEntityOptional.get();
//            ByteArrayResource resource = new ByteArrayResource(fileEntity.getData());
//
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileEntity.getFilename())
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .contentLength(fileEntity.getData().length)
//                    .body(resource);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }