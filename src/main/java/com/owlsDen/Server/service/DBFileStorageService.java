package com.owlsden.Server.service;

import com.owlsden.Server.Exception.FileStorageException;
import com.owlsden.Server.Exception.MyFileNotFoundException;
import com.owlsden.Server.dao.DBFileRepository;
import com.owlsden.Server.model.DBFile;
import com.owlsden.Server.model.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@Service
public class DBFileStorageService  {

    @Autowired
    private DBFileRepository dbFileRepository;

    @Autowired
    private ProfileRepository profileRepository;

    public DBFile storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            //DBFile exists = dbFileRepository.findByUsername(dbfile.getUsername());
            DBFile dbFile = new DBFile(fileName, file.getContentType(), file.getBytes());
//            if(exists == null) {
//                dbFile.setUsername(dbfile.getUsername());
//            }

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public DBFile getAvatarByProfile(String name) {
//    	Profile profile = profileRepository.findByUsername(name);
        String URI = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/avatar/")
                .path(name)
                .toUriString();
        DBFile test = dbFileRepository.findByFileURL(URI);

        try {
            System.out.println("--------------------------------------" + test.getClass().getName());
    		return test;
    	}
    	catch(Exception e) {
            System.out.println("------------------------------------------");
            System.out.println();
            System.out.println(e.toString());
        }

        return test;

    }
    
    public DBFile getBackgroundByProfile(String name) {
//    	Profile profile = profileRepository.findByUsername(name);
        String URI = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/background/")
                .path(name)
                .toUriString();
        DBFile test = dbFileRepository.findByFileURL(URI);
    	try {
    		return test;
    	}
    	catch(Exception e) {
            System.out.println("------------------------------------------");
            System.out.println(e.toString());
    	}

    	return test;
    }
    
    public DBFile getFile(String fileId) {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }
}