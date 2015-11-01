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
@Table(name = "raster_overviews")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RasterOverviews.findAll", query = "SELECT r FROM RasterOverviews r"),
    @NamedQuery(name = "RasterOverviews.findByOTableCatalog", query = "SELECT r FROM RasterOverviews r WHERE r.oTableCatalog = :oTableCatalog"),
    @NamedQuery(name = "RasterOverviews.findByOTableSchema", query = "SELECT r FROM RasterOverviews r WHERE r.oTableSchema = :oTableSchema"),
    @NamedQuery(name = "RasterOverviews.findByOTableName", query = "SELECT r FROM RasterOverviews r WHERE r.oTableName = :oTableName"),
    @NamedQuery(name = "RasterOverviews.findByORasterColumn", query = "SELECT r FROM RasterOverviews r WHERE r.oRasterColumn = :oRasterColumn"),
    @NamedQuery(name = "RasterOverviews.findByRTableCatalog", query = "SELECT r FROM RasterOverviews r WHERE r.rTableCatalog = :rTableCatalog"),
    @NamedQuery(name = "RasterOverviews.findByRTableSchema", query = "SELECT r FROM RasterOverviews r WHERE r.rTableSchema = :rTableSchema"),
    @NamedQuery(name = "RasterOverviews.findByRTableName", query = "SELECT r FROM RasterOverviews r WHERE r.rTableName = :rTableName"),
    @NamedQuery(name = "RasterOverviews.findByRRasterColumn", query = "SELECT r FROM RasterOverviews r WHERE r.rRasterColumn = :rRasterColumn"),
    @NamedQuery(name = "RasterOverviews.findByOverviewFactor", query = "SELECT r FROM RasterOverviews r WHERE r.overviewFactor = :overviewFactor")})
public class RasterOverviews implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 2147483647)
    @Column(name = "o_table_catalog")
    private String oTableCatalog;
    @Size(max = 2147483647)
    @Column(name = "o_table_schema")
    private String oTableSchema;
    @Size(max = 2147483647)
    @Column(name = "o_table_name")
    private String oTableName;
    @Size(max = 2147483647)
    @Column(name = "o_raster_column")
    private String oRasterColumn;
    @Size(max = 2147483647)
    @Column(name = "r_table_catalog")
    private String rTableCatalog;
    @Size(max = 2147483647)
    @Column(name = "r_table_schema")
    private String rTableSchema;
    @Size(max = 2147483647)
    @Column(name = "r_table_name")
    private String rTableName;
    @Size(max = 2147483647)
    @Column(name = "r_raster_column")
    private String rRasterColumn;
    @Column(name = "overview_factor")
    private Integer overviewFactor;

    public RasterOverviews() {
    }

    public String getOTableCatalog() {
        return oTableCatalog;
    }

    public void setOTableCatalog(String oTableCatalog) {
        this.oTableCatalog = oTableCatalog;
    }

    public String getOTableSchema() {
        return oTableSchema;
    }

    public void setOTableSchema(String oTableSchema) {
        this.oTableSchema = oTableSchema;
    }

    public String getOTableName() {
        return oTableName;
    }

    public void setOTableName(String oTableName) {
        this.oTableName = oTableName;
    }

    public String getORasterColumn() {
        return oRasterColumn;
    }

    public void setORasterColumn(String oRasterColumn) {
        this.oRasterColumn = oRasterColumn;
    }

    public String getRTableCatalog() {
        return rTableCatalog;
    }

    public void setRTableCatalog(String rTableCatalog) {
        this.rTableCatalog = rTableCatalog;
    }

    public String getRTableSchema() {
        return rTableSchema;
    }

    public void setRTableSchema(String rTableSchema) {
        this.rTableSchema = rTableSchema;
    }

    public String getRTableName() {
        return rTableName;
    }

    public void setRTableName(String rTableName) {
        this.rTableName = rTableName;
    }

    public String getRRasterColumn() {
        return rRasterColumn;
    }

    public void setRRasterColumn(String rRasterColumn) {
        this.rRasterColumn = rRasterColumn;
    }

    public Integer getOverviewFactor() {
        return overviewFactor;
    }

    public void setOverviewFactor(Integer overviewFactor) {
        this.overviewFactor = overviewFactor;
    }
    
}
