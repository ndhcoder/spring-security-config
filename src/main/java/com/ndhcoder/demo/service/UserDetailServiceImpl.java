package com.ndhcoder.demo.service;

import com.ndhcoder.demo.model.dto.AuthUserDTO;
import com.ndhcoder.demo.model.dto.PermissionDTO;
import com.ndhcoder.demo.model.dto.RoleDTO;
import com.ndhcoder.demo.model.dto.UserDTO;
import com.ndhcoder.demo.model.entity.UserEntity;
import com.ndhcoder.demo.model.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("UserDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository mUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = mUserRepository.getByUserName(username);
        if (user == null) throw new UsernameNotFoundException("Username or password wrong");

        UserDetails userDetails = new AuthUserDTO(convertToUserDTO(user));

        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }

    private UserDTO convertToUserDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setFullName(userEntity.getFullName());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setStatus(userEntity.getStatus());
        userDTO.setUpdatedAt(userEntity.getUpdatedAt());
        userDTO.setCreatedAt(userEntity.getCreatedAt());

        List<RoleDTO> roleDTOList = new ArrayList<>();

        if (userEntity.getRoles() != null) {
            userEntity.getRoles().forEach(roleEntity -> {
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setId(roleEntity.getId());
                roleDTO.setName(roleEntity.getName());
                roleDTO.setDescription(roleEntity.getDescription());
                List<PermissionDTO> permissionDTOList = new ArrayList<>();

                if (roleEntity.getPermissions() != null) {
                    roleEntity.getPermissions().forEach(permissionEntity -> {
                        PermissionDTO permissionDTO = new PermissionDTO();
                        permissionDTO.setId(permissionEntity.getId());
                        permissionDTO.setName(permissionEntity.getName());
                        permissionDTO.setDescription(permissionEntity.getDescription());

                        permissionDTOList.add(permissionDTO);
                    });
                }
                roleDTO.setPermissions(permissionDTOList);

                roleDTOList.add(roleDTO);
            });
        }

        userDTO.setRoles(roleDTOList);

        return userDTO;
    }
}
