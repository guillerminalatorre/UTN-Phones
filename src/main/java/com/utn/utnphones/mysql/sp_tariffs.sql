create procedure sp_tariff_from_call (in id_localityfrom integer, in id_localityto integer, out idtariff integer)
begin
	select t.id_tariff into idtariff from tariffs t where t.id_locality_from = id_localityfrom  and t.id_locality_to  = id_localityto ;
end;