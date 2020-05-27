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
    @Column(name = "phone_number")
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private LineType lineType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Locality locality;

    @Enumerated(value = EnumType.STRING)
    @Column(columnDefinition = "varchar(50) default 'ENABLED'")
    private LineStatus status = ENABLED;
}
