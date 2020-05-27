package com.utn.utnphones.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "line_types")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class LineType {
    @Id
    @Column(name = "id_line_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLineType;

    @NotNull
    private String name;

    @NotNull
    @Column(name = "digits_qty")
    private Integer digitsQty;
}
