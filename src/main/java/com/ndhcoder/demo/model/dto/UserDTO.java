package com.ndhcoder.demo.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

    public UserDTO(UserDTO userDTO) {
        id = userDTO.id;
        fullName = userDTO.fullName;
        userName = userDTO.userName;
        password = userDTO.password;
        status = userDTO.status;
        createdAt = userDTO.createdAt;
        updatedAt = userDTO.updatedAt;
        roles = userDTO.roles;
    }

    private Long id;

    private String fullName;

    private String userName;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private Integer status;

    private Date createdAt;

    private Date updatedAt;

    private List<RoleDTO> roles;

}
