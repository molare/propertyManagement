package com.immo.entities;

import com.immo.entities.audit.UserDateAudit;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by olivier on 02/10/2019.
 */
//#=======================
//table type de locative
//#=======================
@Entity
@Table(name = "locatif")
public class Locatif extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "designation", unique = true)
    private String designation;

    @Column(name = "using_locatif")
    private String usageLocatif;

    @Column(name = "nber_room")
    private int nberRoom;

    @Column(name = "superficy")
    private double superficy;

    @Column(name = "amount")
    private double amount;

    @Column(name = "charge")
    private double charge;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "type_locatif_id")
    private TypeLocatif typeLocative;

    @Column(name="status_locatif")
    private Short statusLocatif;

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getUsageLocatif() {
        return usageLocatif;
    }

    public void setUsageLocatif(String usageLocatif) {
        this.usageLocatif = usageLocatif;
    }

    public int getNberRoom() {
        return nberRoom;
    }

    public void setNberRoom(int nberRoom) {
        this.nberRoom = nberRoom;
    }

    public double getSuperficy() {
        return superficy;
    }

    public void setSuperficy(double superficy) {
        this.superficy = superficy;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getCharge() {
        return charge;
    }

    public void setCharge(double charge) {
        this.charge = charge;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public Short getStatusLocatif() {
        return statusLocatif;
    }

    public void setStatusLocatif(Short statusLocatif) {
        this.statusLocatif = statusLocatif;
    }

    public TypeLocatif getTypeLocative() {
        return typeLocative;
    }

    public void setTypeLocative(TypeLocatif typeLocative) {
        this.typeLocative = typeLocative;
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
        return "<td>\n" +
                "	<a href=\"javascript: void(0);\" style=\"text-decorations:none; color:inherit;\" class=\"link-underlined margin-right-50\" onclick=\"edit("+getId()+")\"><i class=\"fa fa-edit font-14\"><!-- --></i></a>\n" +
                "	<a href=\"javascript: void(0);\" style=\"text-decorations:none; color:inherit;\" class=\"link-underlined\" onclick=\"deletes("+getId()+")\"><i class=\"fa fa-trash font-14\"><!-- --></i></a>\n" +
                "</td>";
    }

    public void setAction(String action) {
        this.action = action;
    }
}
