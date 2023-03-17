package com.eazybytes.eazyschool.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.EnumType.STRING;

@Data
@Entity
@Table( name = "holidays" )
public class Holiday extends BaseEntity {

    @Id
    private String day;
    private String reason;

    @Enumerated( STRING )
    private Type type;

    public enum Type {
        FESTIVAL, FEDERAL
    }
}
