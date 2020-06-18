package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity(name = "calls")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Call {
    @Id
    @Column(name = "id_call")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="id_tariff", referencedColumnName="id_tariff", foreignKey = @ForeignKey(name = "fk_id_tariff"))
    private Tariff tariff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="id_bill", referencedColumnName="id_bill", foreignKey = @ForeignKey(name = "fk_id_bill"))
    private Bill bill;

    @Column(name = "phone_number_from")
    @NotNull
    private String phoneNumberFrom;

    @Column(name = "phone_number_to")
    @NotNull
    private String phoneNumberTo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_locality_from", referencedColumnName="id_locality", foreignKey = @ForeignKey(name = "fk_id_locality_from"))
    private Locality localityFrom;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_locality_to", referencedColumnName="id_locality", foreignKey = @ForeignKey(name = "fk_id_locality_to"))
    private Locality localityTo;

    @NotNull
    @JoinColumn(name="datee")
    @Temporal(TemporalType.TIMESTAMP)/**datetime**/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Date datee;

    @NotNull
    @Temporal(TemporalType.TIME)/**date**/
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="HH:mm:ss")
    @DateTimeFormat(pattern = "HH:mm:ss")
    private Date duration;

    @Column(name = "cost_price")
    private Float costPrice;

    @Column(name = "total_price")
    private Float totalPrice;
}
