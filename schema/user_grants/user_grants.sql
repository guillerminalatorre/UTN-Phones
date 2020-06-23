CREATE USER 'backoffice'@'localhost';
SET PASSWORD FOR 'backoffice'@'localhost' = PASSWORD('12345');

/*PERMISOS DE ESCRITURA*/

GRANT INSERT on utn_phones.phone_lines to 'backoffice'@'localhost';
GRANT INSERT on utn_phones.users to 'backoffice'@'localhost';
GRANT INSERT on utn_phones.tariffs to 'backoffice'@'localhost';
GRANT INSERT on utn_phones.line_types to 'backoffice'@'localhost';

/*PERMISOS DE LECTURA*/

grant select on utn_phones.* to 'backoffice'@'localhost';

/*PERMISOS DE UPDATE*/

grant UPDATE(name, lastname, user_type, id_locality, active, username, password) on utn_phones.users to 'backoffice'@'localhost';
grant UPDATE(cost_price, price) on utn_phones.tariffs to 'backoffice'@'localhost';
grant UPDATE(status, active) on utn_phones.phone_lines to 'backoffice'@'localhost';
grant UPDATE(status) on utn_phones.bills to 'backoffice'@'localhost';

/*PERMISOS DE DELETE: No contiene, solo se realizan borrados lógicos.*/



CREATE USER 'client'@'localhost';
SET PASSWORD FOR 'client'@'localhost' = PASSWORD('12345');

/*PERMISOS DE ESCRITURA: No posee*/

/*PERMISOS DE LECTURA*/
/*SELECT privilege is used to select data in a database object, applies to the entire object, and cannot be granted to specific columns.*/

grant select on utn_phones.users to 'client'@'localhost';
grant select on utn_phones.bills to 'client'@'localhost';
grant select on utn_phones.calls to 'client'@'localhost';

/*PERMISOS DE UPDATE*/

grant update(username, password ) on utn_phones to 'client'@'localhost';

/*PERMISOS DE DELETE: No posee*/


CREATE USER 'antenna'@'localhost';
SET PASSWORD FOR 'antenna'@'localhost' = PASSWORD('12345');

/*PERMISOS DE ESCRITURA*/

grant insert on utn_phones.users to 'antenna'@'localhost';

