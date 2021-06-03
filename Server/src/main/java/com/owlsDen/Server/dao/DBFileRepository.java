package com.owlsden.Server.dao;

import com.owlsden.Server.model.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, String> {
    DBFile findByUsername(String username);
    DBFile findByFileURL(String url);
    DBFile deleteByFileURL(String url);
}
