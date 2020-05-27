package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import com.utn.utnphones.models.enums.BillStatus;
import com.utn.utnphones.models.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

import static com.utn.utnphones.models.enums.BillStatus.UNPAID;

@Entity(name = "bills")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Bill {
    @Id
    @Column(name = "id_bill")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name="id_phone_line")
    private PhoneLine phoneLine;

    @Column(name = "calls_qty", columnDefinition = "integer default 0")
    private Integer callsQty = 0;

    @Column(name = "cost_price")
    @NotNull
    private Float costPrice;

    @Column(name = "total_price")
    @NotNull
    private Float totalPrice;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)/**datetime**/
    private Date date;

    @Column(name = "due_date")
    @NotNull
    @Temporal(TemporalType.DATE)/**date**/
    private Date dueDate;

    @NotNull
    @Column(columnDefinition = "varchar(50) default 'UNPAID'")
    @Enumerated(value = EnumType.STRING)
    private BillStatus status = UNPAID;

}
