package com.immo.entities;

import com.immo.entities.audit.UserDateAudit;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by olivier on 02/10/2019.
 */
//#=======================
//table bien immobiler
//#=======================
@Entity
@Table(name = "property")
public class Property extends UserDateAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "designation")
    private String designation;

    @Column(name = "nber_foncier")
    private String nberFoncier;

    @Column(name = "nber_ilot")
    private String nberIlot;

    @Column(name = "nber_lot")
    private String nberlot;

    @Column(name = "superficy")
    private double superficy;

    @Column(name = "acquisition_cost")
    private double acquisitionCost;

    @ManyToOne
    @JoinColumn(name = "property_owner_id")
    private PropertyOwner propertyOwner;

    @ManyToOne
    @JoinColumn(name = "sector_id")
    private Sector sector;

    @ManyToOne
    @JoinColumn(name = "type_property_id")
    private TypeProperty typeProperty;

    @Lob
    @Column(name="image", unique =true, nullable = true,length = 80000000)
    private byte[] image;

    @Column(name="image_name", nullable = true)
    private String imageName;

    @Column(name = "commentary")
    private String commentary;

    @Transient
    private String imageTransient;

    @Transient
    private String action;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getNberFoncier() {
        return nberFoncier;
    }

    public void setNberFoncier(String nberFoncier) {
        this.nberFoncier = nberFoncier;
    }

    public String getNberIlot() {
        return nberIlot;
    }

    public void setNberIlot(String nberIlot) {
        this.nberIlot = nberIlot;
    }

    public String getNberlot() {
        return nberlot;
    }

    public void setNberlot(String nberlot) {
        this.nberlot = nberlot;
    }

    public double getSuperficy() {
        return superficy;
    }

    public void setSuperficy(double superficy) {
        this.superficy = superficy;
    }

    public double getAcquisitionCost() {
        return acquisitionCost;
    }

    public void setAcquisitionCost(double acquisitionCost) {
        this.acquisitionCost = acquisitionCost;
    }
    
    public PropertyOwner getPropertyOwner() {
        return propertyOwner;
    }

    public void setPropertyOwner(PropertyOwner propertyOwner) {
        this.propertyOwner = propertyOwner;
    }

    public Sector getSector() {
        return sector;
    }

    public void setSector(Sector sector) {
        this.sector = sector;
    }

    public TypeProperty getTypeProperty() {
        return typeProperty;
    }

    public void setTypeProperty(TypeProperty typeProperty) {
        this.typeProperty = typeProperty;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getCommentary() {
        return commentary;
    }

    public void setCommentary(String commentary) {
        this.commentary = commentary;
    }

    public String getImageTransient() {
        return imageTransient;
    }

    public void setImageTransient(String imageTransient) {
        this.imageTransient = imageTransient;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
