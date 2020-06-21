package com.utn.utnphones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
public class AntennaCall {
    @JsonProperty
    String datee;

    @JsonProperty
    String duration;

    @JsonProperty
    String phoneNumberFrom;

    @JsonProperty
    String phoneNumberTo;

}
