package com.plgr.techround.domain.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Suburb Entity Class
 */
@Entity
@Table(name = "TBL_SUBURBS")
@Data
public class Suburb {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="SUBURB_ID", nullable = false)
    private Integer suburbId;

    @Column(name="POSTCODE", nullable = false)
    private Integer postCode;

    @Column(name="SUBURB_NAME", nullable = false)
    private String name;
}
