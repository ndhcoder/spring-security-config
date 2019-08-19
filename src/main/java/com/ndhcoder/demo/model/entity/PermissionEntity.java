package com.ndhcoder.demo.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "permissions")
@Data
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;
}