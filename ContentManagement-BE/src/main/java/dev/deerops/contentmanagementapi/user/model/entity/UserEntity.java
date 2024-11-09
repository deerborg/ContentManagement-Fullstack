package dev.deerops.contentmanagementapi.user.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.deerops.contentmanagementapi.content.model.entity.ContentEntity;
import dev.deerops.contentmanagementapi.user.model.entity.enums.Role;
import dev.deerops.contentmanagementapi.user.model.entity.enums.UserType;
import jakarta.persistence.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue
    @UuidGenerator
    private String userId;

    private String name;

    private String lastName;

    private String email;

    private String phone;

    private String address;

    private String username;

    private String password;

    private Integer contentMaxLimit;

    private LocalDate creationDate;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialsNonExpired;

    private boolean enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> role;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    private List<ContentEntity> contentEntityList;

    public UserEntity(String userId, String name, String lastName, String email, String phone, String address, String username, String password, Integer contentMaxLimit, LocalDate creationDate, boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled, List<Role> role, UserType userType, List<ContentEntity> contentEntityList) {
        this.userId = userId;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.username = username;
        this.password = password;
        this.contentMaxLimit = contentMaxLimit;
        this.creationDate = creationDate;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
        this.role = role;
        this.userType = userType;
        this.contentEntityList = contentEntityList;
    }

    public UserEntity() {

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Integer getContentMaxLimit() {
        return contentMaxLimit;
    }

    public void setContentMaxLimit(Integer contentMaxLimit) {
        this.contentMaxLimit = contentMaxLimit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public List<ContentEntity> getContentEntityList() {
        return contentEntityList;
    }

    public void setContentEntityList(List<ContentEntity> contentEntityList) {
        this.contentEntityList = contentEntityList;
    }
}
