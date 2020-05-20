package com.utn.utnphones.models;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity(name = "provinces")
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
    @Column(unique=true)
    private String name;

    @OneToMany(mappedBy = "province")
    List<Locality> localities;
}
