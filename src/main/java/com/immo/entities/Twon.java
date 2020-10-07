package com.immo.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.immo.entities.audit.UserDateAudit;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.Date;

/**
 * Created by olivier on 02/10/2019.
 */
//#=======================
//table ville
//#=======================
@Entity
@Table(name = "twon")
public class Twon extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Transient
    private String action;


    public Twon() {
    }

    public Twon(String name, String description) {
        this.name = name;
        this.description = description;
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
        return "Twon{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", action='" + action + '\'' +
                '}';
    }
}
