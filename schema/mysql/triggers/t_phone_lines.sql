DELIMITER//
CREATE TRIGGER TBI_phone_line before insert on phone_lines for each row
begin
        declare cant integer;

        declare number_length integer;

        select length(new.phone_number) into number_length;

        select lt.digits_qty into cant from line_types lt where lt.id_line_type = new.id_line_type;


        if cant = number_length then
            begin
                call sp_get_locality_from_phone_number(new.phone_number, @id_locality) ;

                if @id_locality is not null then
                    begin
                        set new.id_locality = @id_locality;
                    end;
                else
                    begin
                        signal sqlstate '10001'
                        SET MESSAGE_TEXT = 'Phone number do not contains a prefix valid';
                    end;
                end if;
            end;
        else
            begin
                signal sqlstate '10002'
                SET MESSAGE_TEXT = 'Digits quantity is not the same as the Line Type';
            end;
        end if;
end;
delimiter;