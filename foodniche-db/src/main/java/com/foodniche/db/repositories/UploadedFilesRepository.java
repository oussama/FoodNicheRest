package com.foodniche.db.repositories;

import com.foodniche.db.entities.FileType;
import com.foodniche.db.entities.UploadedFiles;
import com.foodniche.db.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Alexey Dubrov
 *
 * Upload files repository.
 */

public interface UploadedFilesRepository extends JpaRepository<UploadedFiles, Integer> {

    List<UploadedFiles> findByUserAndFileType(Users user, FileType fileType);
}
