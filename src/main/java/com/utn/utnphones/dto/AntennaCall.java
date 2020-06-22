package com.utn.utnphones.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class AntennaCall {
    @JsonProperty
    String datee;

    @JsonProperty
    String duration;

    @JsonProperty
    String phoneNumberFrom;

    @JsonProperty
    String phoneNumberTo;

    public String getDatee() {
        return datee;
    }

    public void setDatee(String datee) {
        this.datee = datee;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPhoneNumberFrom() {
        return phoneNumberFrom;
    }

    public void setPhoneNumberFrom(String phoneNumberFrom) {
        this.phoneNumberFrom = phoneNumberFrom;
    }

    public String getPhoneNumberTo() {
        return phoneNumberTo;
    }

    public void setPhoneNumberTo(String phoneNumberTo) {
        this.phoneNumberTo = phoneNumberTo;
    }
}
