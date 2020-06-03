create procedure sp_get_locality_from_phone_number (in phone_number varchar(50), out id Integer)
begin
	declare reply integer;
	declare four varchar(50);
	declare two varchar(50);
	declare three varchar(50);

	select SUBSTRING(phone_number,1,4) into four;

	select SUBSTRING(phone_number,1,3) into three;

	select SUBSTRING(phone_number,1,2) into two;

	select l.id_locality into reply from localities l where l.prefix = four or l.prefix = three or l.prefix = two
	group by l.prefix desc limit 1;

	set id = reply;
end;

call sp_get_locality_from_phone_number('11115678', @id_locality);

select @id_locality;
