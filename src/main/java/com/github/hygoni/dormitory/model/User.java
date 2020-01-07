package com.github.hygoni.dormitory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long privateKey;

    @Column(name = "uid")
    @JsonProperty("uid")
    String uid;

    @Column(name = "password")
    @JsonProperty("password")
    String password;

    @Column(name = "nickname")
    @JsonProperty("nickname")
    String nickname;

    @Column(name = "gender")
    @JsonProperty("gender")
    String gender;

    @Column(name = "building_number")
    @JsonProperty("building_number")
    int buildingNumber;

    @Builder.Default
    @ElementCollection
    private List<String> roles = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return uid;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
