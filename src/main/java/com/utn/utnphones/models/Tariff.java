package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeId;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "tariffs")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class Tariff {
    @Id
    @Column(name = "id_tariff")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTariff;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_locality_from", referencedColumnName="id_locality", foreignKey = @ForeignKey(name = "fk_id_locality_from"))
    private Locality localityFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_locality_to", referencedColumnName="id_locality", foreignKey = @ForeignKey(name = "fk_id_locality_to"))
    private Locality localityTo;

    @Column(name = "cost_price")
    @NotNull
    private Float costPrice;

    @NotNull
    @Column(name = "price")
    private Float price;
}
