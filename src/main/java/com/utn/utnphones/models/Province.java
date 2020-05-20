package com.utn.utnphones.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer idProvince;

    @NotNull
    @Column(unique=true)
    private String name;
}
