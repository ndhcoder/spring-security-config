package com.ndhcoder.demo.model.entity;

import com.ndhcoder.demo.utils.DateUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
@DynamicUpdate
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public UserEntity(UserEntity user) {
        this.id = user.getId();
        this.userName = user.getUserName();
        this.password = user.getPassword();
        this.status = user.getStatus();
        this.fullName = user.getFullName();
        this.updatedAt = user.getUpdatedAt();
        this.createdAt = user.getCreatedAt();
        this.roles = user.getRoles();
    }

    public UserEntity() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "username")
    private String userName;

    @JsonIgnore
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private Integer status;

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_user", joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "id")})

    private List<RoleEntity> roles;

    @PrePersist
    void preInsert() {
        this.createdAt = DateUtils.getCurrentTimestamp();
        this.updatedAt = DateUtils.getCurrentTimestamp();

        if (this.status == null) {
            this.status = 0;
        }
    }

    @PreUpdate
    void preUpdate() {
        this.updatedAt = DateUtils.getCurrentTimestamp();
    }
}