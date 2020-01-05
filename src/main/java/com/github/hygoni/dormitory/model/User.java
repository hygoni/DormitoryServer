package com.github.hygoni.dormitory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    int identity;

    @Column(name = "id")
    @JsonProperty("id")
    String id;

    @Column(name = "password")
    @JsonProperty("password")
    String password;

    @Column(name = "nickname")
    @JsonProperty("nickname")
    String nickname;

    @Column(name = "building_number")
    @JsonProperty("building_number")
    int buildingNumber;
}
