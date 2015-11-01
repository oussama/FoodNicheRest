/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.foodnicherest;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
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
@Table(name = "raster_columns")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RasterColumns.findAll", query = "SELECT r FROM RasterColumns r"),
    @NamedQuery(name = "RasterColumns.findByRTableCatalog", query = "SELECT r FROM RasterColumns r WHERE r.rTableCatalog = :rTableCatalog"),
    @NamedQuery(name = "RasterColumns.findByRTableSchema", query = "SELECT r FROM RasterColumns r WHERE r.rTableSchema = :rTableSchema"),
    @NamedQuery(name = "RasterColumns.findByRTableName", query = "SELECT r FROM RasterColumns r WHERE r.rTableName = :rTableName"),
    @NamedQuery(name = "RasterColumns.findByRRasterColumn", query = "SELECT r FROM RasterColumns r WHERE r.rRasterColumn = :rRasterColumn"),
    @NamedQuery(name = "RasterColumns.findBySrid", query = "SELECT r FROM RasterColumns r WHERE r.srid = :srid"),
    @NamedQuery(name = "RasterColumns.findByScaleX", query = "SELECT r FROM RasterColumns r WHERE r.scaleX = :scaleX"),
    @NamedQuery(name = "RasterColumns.findByScaleY", query = "SELECT r FROM RasterColumns r WHERE r.scaleY = :scaleY"),
    @NamedQuery(name = "RasterColumns.findByBlocksizeX", query = "SELECT r FROM RasterColumns r WHERE r.blocksizeX = :blocksizeX"),
    @NamedQuery(name = "RasterColumns.findByBlocksizeY", query = "SELECT r FROM RasterColumns r WHERE r.blocksizeY = :blocksizeY"),
    @NamedQuery(name = "RasterColumns.findBySameAlignment", query = "SELECT r FROM RasterColumns r WHERE r.sameAlignment = :sameAlignment"),
    @NamedQuery(name = "RasterColumns.findByRegularBlocking", query = "SELECT r FROM RasterColumns r WHERE r.regularBlocking = :regularBlocking"),
    @NamedQuery(name = "RasterColumns.findByNumBands", query = "SELECT r FROM RasterColumns r WHERE r.numBands = :numBands"),
    @NamedQuery(name = "RasterColumns.findByPixelTypes", query = "SELECT r FROM RasterColumns r WHERE r.pixelTypes = :pixelTypes"),
    @NamedQuery(name = "RasterColumns.findByNodataValues", query = "SELECT r FROM RasterColumns r WHERE r.nodataValues = :nodataValues"),
    @NamedQuery(name = "RasterColumns.findByOutDb", query = "SELECT r FROM RasterColumns r WHERE r.outDb = :outDb")})
public class RasterColumns implements Serializable {
    private static final long serialVersionUID = 1L;
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
    @Column(name = "srid")
    private Integer srid;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "scale_x")
    private Double scaleX;
    @Column(name = "scale_y")
    private Double scaleY;
    @Column(name = "blocksize_x")
    private Integer blocksizeX;
    @Column(name = "blocksize_y")
    private Integer blocksizeY;
    @Column(name = "same_alignment")
    private Boolean sameAlignment;
    @Column(name = "regular_blocking")
    private Boolean regularBlocking;
    @Column(name = "num_bands")
    private Integer numBands;
    @Column(name = "pixel_types")
    private Serializable pixelTypes;
    @Column(name = "nodata_values")
    private Serializable nodataValues;
    @Column(name = "out_db")
    private Serializable outDb;
    @Lob
    @Column(name = "extent")
    private Object extent;

    public RasterColumns() {
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

    public Integer getSrid() {
        return srid;
    }

    public void setSrid(Integer srid) {
        this.srid = srid;
    }

    public Double getScaleX() {
        return scaleX;
    }

    public void setScaleX(Double scaleX) {
        this.scaleX = scaleX;
    }

    public Double getScaleY() {
        return scaleY;
    }

    public void setScaleY(Double scaleY) {
        this.scaleY = scaleY;
    }

    public Integer getBlocksizeX() {
        return blocksizeX;
    }

    public void setBlocksizeX(Integer blocksizeX) {
        this.blocksizeX = blocksizeX;
    }

    public Integer getBlocksizeY() {
        return blocksizeY;
    }

    public void setBlocksizeY(Integer blocksizeY) {
        this.blocksizeY = blocksizeY;
    }

    public Boolean getSameAlignment() {
        return sameAlignment;
    }

    public void setSameAlignment(Boolean sameAlignment) {
        this.sameAlignment = sameAlignment;
    }

    public Boolean getRegularBlocking() {
        return regularBlocking;
    }

    public void setRegularBlocking(Boolean regularBlocking) {
        this.regularBlocking = regularBlocking;
    }

    public Integer getNumBands() {
        return numBands;
    }

    public void setNumBands(Integer numBands) {
        this.numBands = numBands;
    }

    public Serializable getPixelTypes() {
        return pixelTypes;
    }

    public void setPixelTypes(Serializable pixelTypes) {
        this.pixelTypes = pixelTypes;
    }

    public Serializable getNodataValues() {
        return nodataValues;
    }

    public void setNodataValues(Serializable nodataValues) {
        this.nodataValues = nodataValues;
    }

    public Serializable getOutDb() {
        return outDb;
    }

    public void setOutDb(Serializable outDb) {
        this.outDb = outDb;
    }

    public Object getExtent() {
        return extent;
    }

    public void setExtent(Object extent) {
        this.extent = extent;
    }
    
}
