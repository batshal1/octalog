package com.sept.rest.webservices.restfulwebservices.dao;

import com.sept.rest.webservices.restfulwebservices.model.DBFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBFileService {

    @Autowired
    private DBFileRepository dbFileRepository;

    public void deleteById(String id){
        dbFileRepository.deleteById(id);
    }
    public DBFile submitFileToDB(DBFile dbFile) {
        return dbFileRepository.save(dbFile);
    }

    public DBFile retrieveByFileUrl(String fileDownloadUri){
       return dbFileRepository.findByFileURL(fileDownloadUri);
    }
}
