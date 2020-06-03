create procedure sp_time_to_minutes (in tiime time, out minutes float)
begin

	select ((time_format(tiime, '%H') * 60) +
			time_format(tiime, '%i') + (time_format(tiime, '%s') /100)) into minutes;

end;