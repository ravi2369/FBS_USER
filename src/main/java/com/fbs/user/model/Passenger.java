package com.fbs.user.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "passenger")
@Data
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long passengerId;
    private String passengerName;
    private int age;
    private String gender;
}
