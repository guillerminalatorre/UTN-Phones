package com.utn.utnphones;

import com.utn.utnphones.models.User;
import com.utn.utnphones.utils.Hash;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UtnPhonesApiApplication {

	public static void main(String[] args) {

		SpringApplication.run(UtnPhonesApiApplication.class, args);
	}


	/*todo
	* Base de Datos:
	* -indexes
	* -scheduler para ejecutar la carga de facturas(sp ya creado)
	* -NoSql
	* -Usuarios con permisos para cada tipo (no interviene el el proyecto, solo script ejecutado)
	*
	* Test:
	* -70% necesario.
	**/

}
