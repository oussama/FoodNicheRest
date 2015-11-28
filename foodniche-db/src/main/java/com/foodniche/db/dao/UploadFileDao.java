package com.foodniche.db.dao;

import com.foodniche.db.entities.FileType;
import com.foodniche.db.entities.UploadedFiles;
import com.foodniche.db.entities.Users;
import com.foodniche.db.repositories.UploadedFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by agdubrov on 11/27/15.
 */
public class UploadFileDao extends BaseDao<UploadedFiles, Integer> {

    @Autowired
    private UploadedFilesRepository filesRepository;

    @Override
    protected JpaRepository<UploadedFiles, Integer> getRepository() {
        return filesRepository;
    }

    public List<UploadedFiles> getUserImages(Users user) {
        return filesRepository.findByUserAndFileType(user, FileType.IMAGE);
    }
}
