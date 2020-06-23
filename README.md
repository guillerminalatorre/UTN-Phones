# UTN-Phones
https://www.getpostman.com/collections/00666e7d8e17abe80342 //IMPORTAR EN POSTAM PARA ACCEDER A LOS REQUESTS

PROGRAMACIÓN AVANZADA 1:
-Acceso de tres tipos de usuario con login compartido y filtrado interno.
  -Backoffice:
    -Manejo de clientes: insercion, borrado lógica, actualización(idLocality, name, lastname, usertype), consulta(idUser, activados, desactivados)
    -Alta, baja y Suspencion de lineas: inserción, actualización (estado (ENABLED, DISABLED, SUSPENDED) ), borrado lógico, consulta(numero de telefono, todas)
    -Consulta de tarifas: all, o por localidad de origen && destino.
    -Consulta de llamadas por Usuario: por idUser, entre fechas, y todas entre fechas.
    -Consulta de facturas: por idUser, entre fechas, y todas entre fechas.

Características:
-SpringBoot
-jdk1.8.0_201
-MySql
-WampServer
-DBeaver/MySqlWorkbrench
-Intellij IDEA

#Swagger 2 ui Documentation(first run the project):
http://localhost:8080/swagger-ui.html#/

Swagger 2 setting up Documentation:
https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
