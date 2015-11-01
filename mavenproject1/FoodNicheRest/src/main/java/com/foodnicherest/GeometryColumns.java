/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "geometry_columns")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GeometryColumns.findAll", query = "SELECT g FROM GeometryColumns g"),
    @NamedQuery(name = "GeometryColumns.findByFTableCatalog", query = "SELECT g FROM GeometryColumns g WHERE g.fTableCatalog = :fTableCatalog"),
    @NamedQuery(name = "GeometryColumns.findByFTableSchema", query = "SELECT g FROM GeometryColumns g WHERE g.fTableSchema = :fTableSchema"),
    @NamedQuery(name = "GeometryColumns.findByFTableName", query = "SELECT g FROM GeometryColumns g WHERE g.fTableName = :fTableName"),
    @NamedQuery(name = "GeometryColumns.findByFGeometryColumn", query = "SELECT g FROM GeometryColumns g WHERE g.fGeometryColumn = :fGeometryColumn"),
    @NamedQuery(name = "GeometryColumns.findByCoordDimension", query = "SELECT g FROM GeometryColumns g WHERE g.coordDimension = :coordDimension"),
    @NamedQuery(name = "GeometryColumns.findBySrid", query = "SELECT g FROM GeometryColumns g WHERE g.srid = :srid"),
    @NamedQuery(name = "GeometryColumns.findByType", query = "SELECT g FROM GeometryColumns g WHERE g.type = :type")})
public class GeometryColumns implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 256)
    @Column(name = "f_table_catalog")
    private String fTableCatalog;
    @Size(max = 256)
    @Column(name = "f_table_schema")
    private String fTableSchema;
    @Size(max = 256)
    @Column(name = "f_table_name")
    private String fTableName;
    @Size(max = 256)
    @Column(name = "f_geometry_column")
    private String fGeometryColumn;
    @Column(name = "coord_dimension")
    private Integer coordDimension;
    @Column(name = "srid")
    private Integer srid;
    @Size(max = 30)
    @Column(name = "type")
    private String type;

    public GeometryColumns() {
    }

    public String getFTableCatalog() {
        return fTableCatalog;
    }

    public void setFTableCatalog(String fTableCatalog) {
        this.fTableCatalog = fTableCatalog;
    }

    public String getFTableSchema() {
        return fTableSchema;
    }

    public void setFTableSchema(String fTableSchema) {
        this.fTableSchema = fTableSchema;
    }

    public String getFTableName() {
        return fTableName;
    }

    public void setFTableName(String fTableName) {
        this.fTableName = fTableName;
    }

    public String getFGeometryColumn() {
        return fGeometryColumn;
    }

    public void setFGeometryColumn(String fGeometryColumn) {
        this.fGeometryColumn = fGeometryColumn;
    }

    public Integer getCoordDimension() {
        return coordDimension;
    }

    public void setCoordDimension(Integer coordDimension) {
        this.coordDimension = coordDimension;
    }

    public Integer getSrid() {
        return srid;
    }

    public void setSrid(Integer srid) {
        this.srid = srid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
