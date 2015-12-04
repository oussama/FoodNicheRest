package com.foodniche.db.dao;

import com.foodniche.db.entities.Content;
import com.foodniche.db.entities.Contenttypes;
import com.foodniche.db.entities.Product;
import com.foodniche.db.repositories.ContentRepository;
import com.foodniche.db.repositories.ContentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alexey Dubrov
 */

@Repository
public class ContentDao extends BaseDao<Content, Integer> {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private ContentTypeRepository contentTypeRepository;

    @Override
    protected JpaRepository<Content, Integer> getRepository() {
        return contentRepository;
    }

    public List<Content> getProductContents(Product product, String typeName) {
        List<Content> contents = null;

        Contenttypes contenttypes = contentTypeRepository.findByName(typeName);

        if (contenttypes != null) {
            contents = contentRepository.findByProductAndContenttypeid(product, contenttypes.getContenttypeid());
        }

        return contents;
    }
}
