drop procedure sp_phone_line_facturation;
create procedure sp_phone_line_facturation (in id_phone_line integer)
begin

	#variables para generar la factura
	DECLARE id_bill integer;
	DECLARE total_price float default 0;
    DECLARE cost_price float default 0;
	DECLARE calls_qty integer default 0;
	DECLARE datee datetime;
	DECLARE due_date date;

	#variables axiliares
	DECLARE price_sum float default 0;
	DECLARE cost_sum float default 0;
	DECLARE id_call integer;
	DECLARE finished int default 0;

	#declaro el cursor
	DECLARE cur_facturation CURSOR FOR select c.id_call, c.total_price as total_price, c.cost_price as cost_price
   	from calls c
   	inner join phone_lines pl on c.phone_number_from = pl.phone_number
   	where c.id_bill is null and pl.id_phone_line = id_phone_line
   	group by c.id_call;

   	#declaro el finalizador del cursor
   	DECLARE CONTINUE HANDLER FOR NOT FOUND SET finished = 1;

    #insertamos una nueva factura, solo con el dato del numero de telefono, fecha, y fecha de vencimiento.
   	call sp_due_date(@datee, @due_date) ;

    insert into bills (id_phone_line, datee , due_date) values (id_phone_line, @datee, @due_date);

   	#tomo el id de la factura
    set id_bill = last_insert_id();

   	#abro el cursor
   	open cur_facturation;
    FETCH cur_facturation INTO id_call, total_price, cost_price;
    WHILE (finished=0) DO

	        #se suman los datos de las llamadas
	        SET price_sum = (total_price + price_sum);
	        SET cost_sum = (cost_price + cost_sum);
			set calls_qty = (calls_qty + 1);

	        #Se updatea la llamada asignandole el id de la factura
	        UPDATE calls c set c.id_bill = id_bill
	       	where c.id_call = id_call;

	        FETCH cur_facturation INTO id_call, total_price, cost_price;

    END while;

    #asigno el total_price, cost_price, calls_qty a la factura
    update bills b set b.calls_qty = calls_qty , b.cost_price = cost_sum , b.total_price = price_sum
   	where b.id_bill =  id_bill;

   	#si la linea nunca realizo llamadas, pongo el estado de la factura en PAGO.
    if exists(select b.id_bill from bills b where b.id_bill = id_bill and b.calls_qty = 0) then
    	begin
	    	update bills b set b.status = "PAID" where b.id_bill = id_bill;
	  	end;
	end if;

    close cur_facturation;

end;