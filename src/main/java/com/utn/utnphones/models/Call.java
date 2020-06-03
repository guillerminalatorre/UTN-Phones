package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    @JoinColumn(name="id_tariff")
    private Tariff tariff;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="id_bill")
    private Bill bill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="id_phone_line_from")/**re funcion, re lindo*/
    private PhoneLine phoneLineFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_phone_line_to")
    @JsonBackReference
    private PhoneLine phoneLineTo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="id_locality_from")/**re funcion, re lindo*/
    private Locality localityFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_locality_to")
    @JsonBackReference
    private Locality localityTo;

    @NotNull
    @JoinColumn(name="datee")
    @Temporal(TemporalType.TIMESTAMP)/**datetime**/
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date date;

    @NotNull
    @Temporal(TemporalType.TIME)/**date**/
    private Date duration;

    @Column(name = "cost_price")
    @NotNull
    private Float costPrice;

    @Column(name = "total_price")
    @NotNull
    private Float totalPrice;
}
