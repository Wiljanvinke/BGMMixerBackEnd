package com.example.bgmmixer.service;

import com.example.bgmmixer.dtos.FileDto;
import com.example.bgmmixer.exceptions.FileStorageException;
import com.example.bgmmixer.exceptions.MyFileNotFoundException;
import com.example.bgmmixer.model.File;
import com.example.bgmmixer.repositories.FileRepository;
import com.fasterxml.jackson.databind.util.ArrayIterator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public File storeFile(MultipartFile file){
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            File dBfile = new File(fileName, file.getContentType(), file.getSize(), file.getBytes());

            return fileRepository.save(dBfile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public File getFile(long fileId) {
        return fileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

    public Iterable<FileDto> getAllFiles(){
        Iterable<File> files = fileRepository.findAll();
        List<FileDto> fileDtos = new ArrayList<>();
        for(File file: files){
            fileDtos.add(new FileDto(file));
        }
        return fileDtos;
    }

    public FileDto getFileDto(long fileId){
        return new FileDto(fileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId)));
    }
}
