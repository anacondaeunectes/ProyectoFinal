/*Crea la tabla proyecto*/
DROP TABLE IF EXISTS proyecto;
CREATE TABLE proyecto(
	fecha_inicio DATE NOT NULL,
	fecha_fin DATE NOT NULL,
	titulo VARCHAR(60) NOT NULL,
	descripcion TEXT,
	id_proyecto INT NOT NULL AUTO_INCREMENT,
	CONSTRAINT pk_proyecto PRIMARY KEY (id_proyecto),
	CONSTRAINT unique_proyecto_titulo UNIQUE (titulo)
);

/*Crea la tabla empleado*/
DROP TABLE IF EXISTS empleado;
CREATE TABLE empleado (
	nombre VARCHAR(60) NOT NULL,
	apellido VARCHAR(150) NOT NULL,
	ano_nacimiento YEAR NOT NULL,
	NIF VARCHAR(9) NOT NULL,
	CONSTRAINT pk_empleado PRIMARY KEY (NIF)
);

/*Crea la tabla trabaja. Nace de la relación N:M entre la tabla proyecto y la tabla empleado. Almacena las claves primarias de cada tabla y ambas se convierte en clave primaria de esta. La constraint que une esta tabla con la otra se maneja con un “ON DELETE CASCADE” que elimina las relaciones que tuviese un empleado o un proyecto al eliminarlo*/
DROP TABLE IF EXISTS trabaja;
CREATE TABLE trabaja (
ref_empleado VARCHAR(9) NOT NULL,
	ref_proyecto INT NOT NULL,
	CONSTRAINT pk_trabaja PRIMARY KEY (ref_proyecto, ref_empleado),
	CONSTRAINT fk_empleado FOREIGN KEY (ref_empleado) REFERENCES empleado (NIF) ON DELETE CASCADE,
	CONSTRAINT fk_proyecto  FOREIGN KEY (ref_proyecto) REFERENCES proyecto (id_proyecto) ON DELETE CASCADE
);

/*Procedimiento que realiza un update sobre un registro de la tabla empleado a través de su NIF*/
DELIMITER $$
DROP PROCEDURE IF EXISTS doUpdateEmpleado;
CREATE PROCEDURE doUpdateEmpleado (IN nuevoNombre varchar(60), IN nuevoApellido VARCHAR(150), IN nuevoAno_nacimiento INT(4),  IN claveNif VARCHAR(9))
BEGIN
	UPDATE empleado SET nombre = nuevoNombre, apellido = nuevoApellido, ano_nacimiento = nuevoAno_nacimiento WHERE NIF = claveNif;
END;
$$

/*Procedimiento que  realiza un update sobre un registro de la tabla empleado a través de su id*/
DELIMITER $$
DROP PROCEDURE IF EXISTS doUpdateProyecto;
CREATE PROCEDURE doUpdateProyecto (IN nuevoTitulo VARCHAR(60), IN nuevaFecha_Inicio DATE,IN nuevaFecha_Fin DATE, IN id INT(11),  IN nuevaDescripcion MEDIUMTEXT)
BEGIN
	UPDATE proyecto SET titulo = nuevoTitulo, fecha_inicio = nuevaFecha_Inicio, fecha_fin = nuevaFecha_Fin, descripcion = nuevaDescripcion WHERE id_proyecto = id;
END;
$$

/*Procedimiento que realiza un insert sobre la tabla empleado*/
DELIMITER $$
DROP PROCEDURE IF EXISTS insertEmpleado;
CREATE PROCEDURE insertEmpleado (IN nombre VARCHAR(60), IN apellido VARCHAR(150), IN ano_nacimiento YEAR, IN nif VARCHAR(9))

BEGIN
	INSERT INTO empleado VALUES (nombre, apellido, ano_nacimiento, nif);
END;
$$


/*Procedimiento que realiza un insert sobre la tabla proyecto*/
DELIMITER $$
DROP PROCEDURE IF EXISTS insertProyecto ;
CREATE PROCEDURE insertProyecto (IN titulo VARCHAR(60), IN fechaInicio DATE, IN fechaFin DATE, IN descripcion MEDIUMTEXT)

BEGIN
	INSERT INTO proyecto VALUES (fechaInicio, fechaFin, titulo, descripcion, NULL);
END;
$$

/*Este trigger se lanza antes de cada insert en la tabla proyecyo. Verifica que la fecha de inicio no sea posterior a la fecha de fin. En caso de ser posterior, lanza un error*/
DELIMITER $$
CREATE TRIGGER triggerFechasProyectoInsert BEFORE INSERT ON proyecto
FOR EACH ROW 

BEGIN 
	IF( NEW.fecha_inicio > NEW.fecha_fin ) THEN 
		SIGNAL SQLSTATE “45000” SET MESSAGE_TEXT = “Fechas no validas. La fecha de inicio no puede ser superior a la de fin”, MYSQL_ERRNO = 7800;	
	END IF;
END;
$$


/*Este trigger se lanza antes de cada iupdate en la tabla proyecyo. Verifica que la fecha de inicio no sea posterior a la fecha de fin. En caso de ser posterior, lanza un error*/
DELIMITER $$
CREATE TRIGGER triggerFechasProyecto BEFORE UPDATE ON proyecto
FOR EACH ROW 

BEGIN 
	IF( NEW.fecha_inicio > NEW.fecha_fin ) THEN 
		SIGNAL SQLSTATE “45000” SET MESSAGE_TEXT = “Fechas no validas. La fecha de inicio no puede ser superior a la de fin”, MYSQL_ERRNO = 7800;	
	END IF;
END;
$$

/*Vista de los títulos de la tabla proyecto*/
DELIMITER $$
DROP VIEW IF EXISTS comprobarProyectoVacio;
CREATE VIEW comprobarProyectoVacio AS
 SELECT titulo FROM proyecto;
$$

/*Procedimiento con dos cursores, uno recorre cada proyecto y otro cada empleado asociado a cada proyecto, que devuelve el título del proyecto con una mayor media de edad y el título del proyecto con menor media de edad*/
DELIMITER $$
DROP PROCEDURE IF EXISTS edad;
CREATE PROCEDURE edad (OUT proy_edadMayor VARCHAR(60), OUT proy_edadMenor VARCHAR(60))
BEGIN
	DECLARE tmp_idProyecto INT;
    	DECLARE tmp_tituloProyecto VARCHAR(60);
   	 DECLARE tmp_idEmpleado VARCHAR(10);
   	 DECLARE lrf BOOLEAN;

DECLARE suma INT;
   	 DECLARE contEmp INT;
    	DECLARE media DOUBLE(8,5);
    	DECLARE mediaMax DOUBLE(8,5) DEFAULT 0;
	DECLARE mediaMin DOUBLE(8,5) DEFAULT 100;

	DECLARE cursorProyecto CURSOR FOR SELECT id_proyecto, titulo FROM proyecto;
	DECLARE cursorTrabaja CURSOR FOR SELECT ref_empleado FROM trabaja WHERE ref_proyecto = tmp_idProyecto;
    
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET lrf = 1;
    
    SET lrf = 0;
    
    OPEN cursorProyecto;
    /*+*/proy_loop: LOOP
		FETCH cursorProyecto INTO tmp_idProyecto, tmp_tituloProyecto;
		IF lrf = 1 THEN LEAVE proy_loop;
		END IF;
		SELECT tmp_tituloProyecto;
        		OPEN  cursorTrabaja;
        
        		SET suma = 0;
        		SET contEmp = 0;
        
		/*-*/trabaja_loop: LOOP
			FETCH cursorTrabaja INTO tmp_idEmpleado;
			IF lrf = 1 THEN LEAVE trabaja_loop;
			END IF;

SET suma = suma + (SELECT (YEAR(NOW()) - ano_nacimiento) FROM empleado WHERE NIF = tmp_idEmpleado);
                
                		SET contEmp = contEmp + 1;

           		/*-*/END LOOP trabaja_loop;

		SELECT contEmp;
            
            CLOSE cursorTrabaja;
            
            SET media = (suma/contEmp);

	SELECT media;
	SELECT mediaMax;
            
/*No se puede poner una clausula if-then-else ya que si solo hubiese un proyecto con empleados asociados, unicamente se guardaría como mayor o menor pero no como los dos*/
            IF (media > mediaMax) THEN
		SET mediaMax = media;
                	SET proy_edadMayor = tmp_tituloProyecto;
	END IF;
	IF (media < mediaMin) THEN
		SET mediaMin = media;
		SET proy_edadMenor = tmp_tituloProyecto;
            END IF;

	SELECT mediaMax;
            
            SET lrf = 0;
        
	/*+*/END LOOP proy_loop;
    
    CLOSE cursorProyecto;
END;
$$
