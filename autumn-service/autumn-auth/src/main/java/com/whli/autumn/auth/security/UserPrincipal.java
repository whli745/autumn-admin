package com.whli.autumn.auth.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.whli.autumn.model.system.Role;
import com.whli.autumn.model.system.User;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private String id;

    private String name;

    private String username;

    @JsonIgnore
    private String superAdmin;

    @JsonIgnore
    private String enabled;

    @JsonIgnore
    private Integer deleted;

    @JsonIgnore
    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrincipal(String id, String name, String username, String email, String password,String superAdmin, String enabled,Integer deleted, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.superAdmin = superAdmin;
        this.authorities = authorities;
        this.enabled = enabled;
        this.deleted = deleted;
    }

    public static UserPrincipal create(User user, List<Role> roles) {
        List<GrantedAuthority> authorities = CollectionUtils.isEmpty(roles) ? null : roles.stream().map(role ->
                new SimpleGrantedAuthority(role.getName())
        ).collect(Collectors.toList());

        return new UserPrincipal(
                user.getId(),
                user.getLoginName(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword(),
                user.getSuperAdmin(),
                user.getEnabled(),
                user.getDeleted(),
                authorities
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getSuperAdmin() {
        return superAdmin;
    }

    public String getEnabled() {
        return enabled;
    }

    public Integer getDeleted() {
        return deleted;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserPrincipal that = (UserPrincipal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}
