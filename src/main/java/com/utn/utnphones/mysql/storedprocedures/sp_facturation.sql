create procedure sp_facturation()
begin
	#para recorrer cada línea
	DECLARE id_phone_line integer;
	DECLARE finished int default 0;

	#declaro el cursor
	DECLARE facturation CURSOR FOR select pl.id_phone_line as 'id_phone_line'
   	from phone_lines pl group by pl.id_phone_line asc;

   	#declaro el finalizador del cursor
   	DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = 1;

   	#abro el cursor
   	open facturation;
    FETCH facturation INTO id_phone_line;
    WHILE (finished=0) DO
	    START transaction;

			call sp_phone_line_facturation(id_phone_line);

	        FETCH facturation INTO id_phone_line;

	     COMMIT;

    END while;

    close facturation;
end;

#PARA REVISAR QUE SEAN CORRECTOS LOS DATOS
select count(c.id_call) as calls_qty, sum(c.total_price) as total_price, sum(c.cost_price) as cost_price
from calls c
inner join phone_lines pl on pl.phone_number = c.phone_number_from
group by pl.id_phone_line asc