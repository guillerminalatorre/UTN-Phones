DELIMITER//
CREATE TRIGGER TBI_calls before insert on calls for each row
begin
	if new.datee > now() then
		begin
			signal sqlstate '10004'
			SET MESSAGE_TEXT = 'Date is not valid';
		end;
	end if;

	if EXISTS (select p.phone_number from phone_lines p where p.phone_number = new.phone_number_from) and
			(select p.phone_number from phone_lines p where p.phone_number = new.phone_number_to) then
		begin
			declare lty_from integer;

			declare lty_to integer;

			select p.id_locality into lty_from  from phone_lines p where p.phone_number = new.phone_number_from;

			select p.id_locality into lty_to from phone_lines p where p.phone_number = new.phone_number_to;

			call sp_tariff_from_call(lty_from, lty_to, @id_tariff);

			if @id_tariff is not null then
				begin

					declare total_cost float;

					declare total_price float;

					declare price float;

					declare costprice float;

					declare tariff integer;

					select @id_tariff into tariff;

					call sp_time_to_minutes(new.duration, @minu) ;

					select t.cost_price into costprice from tariffs t where t.id_tariff = tariff;

					select t.price into price from tariffs t where t.id_tariff = tariff;

					select truncate ((@minu * costprice),2) into total_cost;
					select truncate ((@minu * price),2) into total_price;

					set new.id_locality_from = lty_from;
					set new.id_locality_to = lty_to;
					set new.id_tariff = tariff;
					set new.cost_price = total_cost;
					set new.total_price = total_price;
					set new.id_bill = null;
				end;
			else
				begin
					signal sqlstate '10005'
					SET MESSAGE_TEXT = 'Tariff do not exists for this call';
				end;
			end if;
		end;
	else
		begin
			signal sqlstate '10006'
			SET MESSAGE_TEXT = 'One of the numbers inserted do not exists';
		end;
	end if;
end;

delimiter;