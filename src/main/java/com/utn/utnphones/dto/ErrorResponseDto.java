package com.utn.utnphones.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseDto {
    @JsonProperty
    int code;
    @JsonProperty
    String description;

    public ErrorResponseDto(int code, String description) {
        this.code = code;
        this.description = description;
    }


}
