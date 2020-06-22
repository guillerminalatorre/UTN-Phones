package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.List;

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
    @JoinColumn(name = "id_province", referencedColumnName="id_province", foreignKey = @ForeignKey(name = "fk_id_province"))
    private Province province;

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "prefix")
    private String prefix;

    @OneToMany(mappedBy = "localityFrom")
    @JsonBackReference
    private List<Tariff> tarrifsFrom;

    @OneToMany(mappedBy = "localityTo")
    @JsonBackReference
    private List<Tariff> tarrifsTo;

    @OneToMany(mappedBy = "localityFrom")
    @JsonBackReference
    private List<Call> callsFrom;

    @OneToMany(mappedBy = "localityTo")
    @JsonBackReference
    private List<Call> callsTo;

}
