package com.example.clientsmanagement.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "user_group")

public class Client {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name;
    private String firstname;
    private String lastname;
    private String address;
    private String email;
    private String country;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private User user;
}
