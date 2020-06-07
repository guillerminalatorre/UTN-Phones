drop procedure sp_due_date;
create procedure sp_due_date (out datee datetime, out due_date date)
begin
	declare date_now datetime;

	declare due date;

	set date_now = now();

	set datee = date_now;

	select DATE_ADD(cast(date_now as date), interval 15 day) into due;

	set due_date = due;

end;