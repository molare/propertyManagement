package com.immo.entities;

import com.immo.entities.audit.UserDateAudit;

import javax.persistence.*;
import java.time.Instant;


/**
 * Created by olivier on 02/10/2019.
 */
//#=======================
//table commune
//#=======================
@Entity
@Table(name = "city")
public class City extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name="twon_id")
    private Twon twon;

    @Transient
    private String action;


    public City() {
    }

    public City(String name, String description, Twon twon) {
        this.name = name;
        this.description = description;
        this.twon = twon;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Twon getTwon() {
        return twon;
    }

    public void setTwon(Twon twon) {
        this.twon = twon;
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


    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", twon=" + twon +
                ", action='" + action + '\'' +
                '}';
    }
}
