package com.foodniche.db.dao;

import com.foodniche.db.entities.Businesses;
import com.foodniche.db.entities.Content;
import com.foodniche.db.entities.Contenttypes;
import com.foodniche.db.entities.Product;
import com.foodniche.db.repositories.ContentTypeRepository;
import com.foodniche.db.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Alexey Dubrov
 */

@Repository
public class ProductDao extends BaseDao<Product, Integer> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ContentTypeRepository contentTypeRepository;

    @Override
    protected JpaRepository<Product, Integer> getRepository() {
        return productRepository;
    }

    public List<Product> getBusinessProducts(Integer businessesId) {
        Businesses businesses = new Businesses();
        businesses.setBusinessid(businessesId);

        return productRepository.findByBusinesses(businesses);
    }

    public Content addContent(String name, String content, String type, Integer productId) {
        Content cn = null;
        Contenttypes contenttypes = contentTypeRepository.findByName(type);
        Product product = productRepository.getOne(productId);

        if (contenttypes != null && product != null) {
            cn = new Content();
            cn.setProduct(product);
            cn.setContenttypeid(contenttypes.getContenttypeid());
            cn.setName(name);
            cn.setContent(content);
            getEntityManager().persist(cn);
        }

        return cn;
    }
}
