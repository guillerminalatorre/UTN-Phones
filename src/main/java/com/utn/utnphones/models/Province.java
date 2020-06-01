package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "provinces")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Province {
    @Id
    @Column(name = "id_province")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProvince;

    @NotNull
    @Column(name = "name")
    private String name;

    @JsonManagedReference
    @OneToMany(mappedBy = "province",
            cascade = CascadeType.ALL)
    List<Locality> localities = new ArrayList<>();
}
