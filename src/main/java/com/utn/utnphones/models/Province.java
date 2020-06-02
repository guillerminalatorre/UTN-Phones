package com.utn.utnphones.models;

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
    private Integer idProvince;

    @Column(unique=true)
    @NotNull
    private String name;

}
