package com.immo.entities;

import com.immo.entities.audit.UserDateAudit;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;

/**
 * Created by olivier on 02/10/2019.
 */
//#=======================
//table proprietaire bien
//#=======================
@Entity
@Table(name = "property_owner")
public class PropertyOwner extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
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

    @Column(name = "nber_piece")
    private String nberPiece;

    @Column(name = "nature_piece")
    private String naturePiece;

    @Column(name = "lieu_nais")
    private String lieuNais;

    @Column(name = "domicile")
    private String domicile;


    @Column(name = "account")
    private String account;

    @Column(name="birth_date")
    @Temporal(value = TemporalType.DATE)
    private Date birthDate;

    @ManyToOne
    @JoinColumn(name = "type_owner_id")
    private TypePropertyOwner typePropertyOwner;

    @ManyToOne
    @JoinColumn(name = "civility_id")
    private Civility civility;

    @Column(name="status_property_owner")
    private Short statusPropertyOwner;

    @Lob
    @Column(name="image", unique =true, nullable = true,length = 50000000)
    private byte[] image;

    @Column(name="image_name", nullable = true )
    private String imageName;

    @ManyToOne
    @JoinColumn(name="twon_id")
    private Twon twon;

    @Transient
    private String imageTransient;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
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

    public String getNberPiece() {
        return nberPiece;
    }

    public void setNberPiece(String nberPiece) {
        this.nberPiece = nberPiece;
    }

    public String getNaturePiece() {
        return naturePiece;
    }

    public void setNaturePiece(String naturePiece) {
        this.naturePiece = naturePiece;
    }

    public String getLieuNais() {
        return lieuNais;
    }

    public void setLieuNais(String lieuNais) {
        this.lieuNais = lieuNais;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public TypePropertyOwner getTypePropertyOwner() {
        return typePropertyOwner;
    }

    public void setTypePropertyOwner(TypePropertyOwner typePropertyOwner) {
        this.typePropertyOwner = typePropertyOwner;
    }

    public Civility getCivility() {
        return civility;
    }

    public void setCivility(Civility civility) {
        this.civility = civility;
    }

    public Short getStatusPropertyOwner() {
        return statusPropertyOwner;
    }

    public void setStatusPropertyOwner(Short statusPropertyOwner) {
        this.statusPropertyOwner = statusPropertyOwner;
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

    public Twon getTwon() {
        return twon;
    }

    public void setTwon(Twon twon) {
        this.twon = twon;
    }

    public String getImageTransient() {
        return imageTransient;
    }

    public void setImageTransient(String imageTransient) {
        this.imageTransient = imageTransient;
    }
}
