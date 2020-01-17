package com.github.hygoni.dormitory.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name="washers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Washer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @JsonProperty("building_number")
    int buildingNumber;

    @JsonProperty("sub_id")
    int subId;

    @JsonProperty("display_name")
    String displayName;

    //timestamp
    @JsonProperty("last_started")
    long lastStarted;

    @JsonProperty("working_time")
    //in minutes
    int workingTime;

    @JsonProperty("is_working")
    public boolean isWorking() {
        long now = System.currentTimeMillis() / 1000;
        long expiration = lastStarted + workingTime * 60;
        return now < expiration;
    }
}
