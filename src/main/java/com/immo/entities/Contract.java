package com.immo.entities;

import com.immo.entities.audit.UserDateAudit;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by olivier on 02/10/2019.
 */
//#=======================
 //table contract
//#=======================
@Entity
@Table(name = "contract")
public class Contract extends UserDateAudit{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "month_nber", nullable = false)
    private int monthNber;

    @Column(name = "amount")
    private double amount;

    @Column(name = "first_quittance")
    private double firstQuittance;

    @Column(name = "advance_nber_month")
    private int advanceNberMonth;

    @Column(name = "agence_nber_month")
    private int agenceNberMonth;

    @Column(name="start_bail_date")
    @Temporal(value = TemporalType.DATE)
    private Date startBailDate;


    @Column(name = "remaining_bail", nullable = false)
    private double remainingBail;


    @ManyToOne
    @JoinColumn(name = "locatif_id", nullable = false)
    private Locatif locatif;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Lob
    @Column(name="image", unique =true, nullable = true,length = 80000000)
    private byte[] image;

    @Column(name="image_name", nullable = true)
    private String imageName;

    @Column(name = "status_contract")
    private Short statusContract;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMonthNber() {
        return monthNber;
    }

    public void setMonthNber(int monthNber) {
        this.monthNber = monthNber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getFirstQuittance() {
        return firstQuittance;
    }

    public void setFirstQuittance(double firstQuittance) {
        this.firstQuittance = firstQuittance;
    }

    public int getAdvanceNberMonth() {
        return advanceNberMonth;
    }

    public void setAdvanceNberMonth(int advanceNberMonth) {
        this.advanceNberMonth = advanceNberMonth;
    }

    public Date getStartBailDate() {
        return startBailDate;
    }

    public void setStartBailDate(Date startBailDate) {
        this.startBailDate = startBailDate;
    }

    public int getAgenceNberMonth() {
        return agenceNberMonth;
    }

    public void setAgenceNberMonth(int agenceNberMonth) {
        this.agenceNberMonth = agenceNberMonth;
    }

    public double getRemainingBail() {
        return remainingBail;
    }

    public void setRemainingBail(double remainingBail) {
        this.remainingBail = remainingBail;
    }

    public Locatif getLocatif() {
        return locatif;
    }

    public void setLocatif(Locatif locatif) {
        this.locatif = locatif;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
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

    public Short getStatusContract() {
        return statusContract;
    }

    public void setStatusContract(Short statusContract) {
        this.statusContract = statusContract;
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
