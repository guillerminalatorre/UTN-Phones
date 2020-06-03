package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.utn.utnphones.models.enums.LineStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static com.utn.utnphones.models.enums.LineStatus.ENABLED;

@Entity(name = "phone_lines")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PhoneLine {
    @Id
    @Column(name = "id_phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value="id_user")
    @JoinColumn(name="id_user")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value="id_line_type")
    @JoinColumn(name="id_line_type")
    private LineType lineType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value="id_locality")
    @JoinColumn(name="id_locality")
    private Locality locality;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status" ,columnDefinition = "varchar(50) default 'enabled'")
    private LineStatus status = ENABLED;
}
