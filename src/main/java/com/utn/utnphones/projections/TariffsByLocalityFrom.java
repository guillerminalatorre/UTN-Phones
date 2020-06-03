package com.utn.utnphones.projections;

import com.utn.utnphones.models.Locality;
import com.utn.utnphones.models.Tariff;

public interface TariffsByLocalityFrom {
    public Integer getIdLocalityFrom();
    public String getNameLtyFrom();
    public Integer getIdTariff();
    public Float getCostPrice();
    public Float getPrice();
    public Integer getIdLocalityTo();
    public String getNameLtyTo();/*
    public Locality getLocalityTo();
    public Locality getLocalityFrom();
    public Tariff getTariff();*/
}
