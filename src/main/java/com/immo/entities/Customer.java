package com.immo.entities;

import com.immo.entities.audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * Created by olivier on 02/10/2019.
 */
//#=======================
//table client
//#=======================
@Entity
@Table(name = "customer")
public class Customer extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "code", unique = true, nullable = false)
    private String code;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", unique = true)
    @Email
    private String email;
    @Column(name = "phone", unique = true)
    private String phone;

    @Column(name = "profession")
    private String profession;

    @Column(name = "nationality")
    private String nationality;

    @ManyToOne
    @JoinColumn(name = "civility_id")
    private Civility civility;
    @Lob
    @Column(name="image", unique =true, nullable = true,length = 80000000)
    private byte[] image;

    @Column(name="image_name", nullable = true )
    private String imageName;

    @Column(name="status_customer")
    private Short statusCustomer;

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Civility getCivility() {
        return civility;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
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

    public Short getStatusCustomer() {
        return statusCustomer;
    }

    public void setStatusCustomer(Short statusCustomer) {
        this.statusCustomer = statusCustomer;
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
