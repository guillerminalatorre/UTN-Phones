package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
    @JoinColumn(name="id_locality")
    private Locality locality;

    @NotNull
    @Column( length = 150)
    private String name;

    @NotNull
    @Column( length = 150)
    private String lastname;

    @NotNull
    @Column(name = "user_name", unique = true)
    private String userName;

    @Column( unique = true, name = "id_number")
    private String idNumber;

    @NotNull
    @JsonIgnore
    private String password;

    @Column(name = "user_type", columnDefinition = "varchar(50) default 'CLIENT'")
    @Enumerated(value = EnumType.STRING)
    private UserType userType;

    @Column(name = "active", columnDefinition = "bool default true")
    private Boolean active;
}
