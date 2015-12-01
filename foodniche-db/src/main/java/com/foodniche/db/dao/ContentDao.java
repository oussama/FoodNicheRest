package com.foodniche.db.dao;

import com.foodniche.db.entities.Content;
import com.foodniche.db.repositories.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alexey Dubrov
 */

@Repository
public class ContentDao extends BaseDao<Content, Integer> {

    @Autowired
    private ContentRepository contentRepository;

    @Override
    protected JpaRepository<Content, Integer> getRepository() {
        return contentRepository;
    }
}
