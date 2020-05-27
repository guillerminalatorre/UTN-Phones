package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "localities")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Locality {
    @Id
    @Column(name = "id_locality")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLocality;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="id_province")
    private Province province;

    @NotNull
    private String name;

    @NotNull
    private String prefix;
}
