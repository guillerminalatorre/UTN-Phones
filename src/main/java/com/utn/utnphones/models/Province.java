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
    private Integer idProvince;

    @NotNull
    @Column(unique=true)
    private String name;

}
