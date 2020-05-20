package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import com.utn.utnphones.models.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class User {
    @Id
    @Column(name = "id_user")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Locality locality;

    @NotNull
    @Column( length = 150)
    private String name;

    @NotNull
    @Column( length = 150)
    private String lastname;

    @Column( unique = true, name = "id_number")
    private String idNumber;

    @NotNull
    private String password;

    @Column(name = "user_type")
    @Enumerated(value = EnumType.STRING)
    private UserType userType;
}
