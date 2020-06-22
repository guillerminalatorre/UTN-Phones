package com.utn.utnphones.models;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@Entity(name = "provinces")
@AllArgsConstructor
@NoArgsConstructor
public class Province {
    @Id
    @Column(name = "id_province")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProvince;

    @Column(unique=true)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "province",
            cascade = CascadeType.ALL)
    List<Locality> localities = new ArrayList<>();
}
