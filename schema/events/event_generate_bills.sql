SET GLOBAL event_scheduler = ON;

CREATE EVENT event_generate_bills
ON SCHEDULE EVERY "1" MONTH
Starts "2020-07-01 00:00:00"
DO
begin
	call sp_facturation();
end;