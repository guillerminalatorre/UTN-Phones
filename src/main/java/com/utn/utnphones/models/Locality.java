package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table (name = "localities")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Locality {
    @Id
    @Column(name = "id_locality")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLocality;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_province")
    @JsonBackReference
    private Province province;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "prefix")
    private String prefix;
}
