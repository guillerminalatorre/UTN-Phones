package com.utn.utnphones.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.utn.utnphones.models.enums.Status;
import com.utn.utnphones.models.enums.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private Status status;
}
