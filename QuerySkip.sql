/*Insertando roles*/
INSERT INTO rol(nombre,estado_tabla) VALUES('ROL_CONDUCTOR', 1);
INSERT INTO rol(nombre,estado_tabla) VALUES('ROL_PASAJERO', 1);
SELECT * FROM rol;

/*Insertando cuenta de prueba*/
INSERT INTO cuenta(codigo_upc,contrasena,correo_upc,estado_tabla) VALUES('U201710947', '1234567','U201710947@upc.edu.pe', 1);
SELECT * FROM cuenta;

/*Insertando cuenta rol*/
INSERT INTO cuenta_rol(cuenta_id,rol_id) VALUES(1, 1);
SELECT * FROM cuenta_rol;

/*Insertando en usuario*/
INSERT INTO usuario(apellidos,dni,estado_tabla,nombres,sede,cuenta_id) 
VALUES('Pizarro Llanos', '74036222', 1, 'César Alejandro', 'San Miguel', 1);
SELECT * FROM usuario;

/*Insertando en info conductor*/
INSERT INTO informacion_conductor(direccion,facebook_id,latitud,licencia_conducir,longitud,telefono,usuario_id)
VALUES('Av. Juan Pardo de Zela 166 Cercado de Lima', 'www.facebook.com/Ce', -12.0834862, '99999999', -77.0350564,
'998362542', 1);
SELECT * FROM informacion_conductor;

/*Insertando en auto*/
INSERT INTO auto(anhos_uso,estado_tabla,limite_personas,marca,modelo,placa,poliza_soat,info_conductor_id)
VALUES(5,1,3,'Nissan','Sentra','XH3-332', 53421162, 1);
SELECT * FROM auto;

/*Insertando en ruta ---- 1 a la universidad - 0 de regreso*/
/*Recordar que se debe mostrar la sede del conductor para que los estudiantes sepan que el viaje es hacia su sede*/
INSERT INTO Ruta(estado_tabla,sentido,tiempo_estimado) VALUES(1, 1, '1h 00m 00s');
SELECT * FROM ruta;
update ruta set distancia = 1000 where id=1;

/*Insertando en viaje*/
INSERT INTO viaje(descripcion,estado_tabla,estado_viaje,fecha_publicacion,
fecha_viaje,hora_inicio,hora_llegada,hora_publicacion,visualizacion_habilitada,
conductor_id,ruta_id)
VALUES('Ùnete al lado oscuro', 1, 'Publicado', '2020-06-16', '2020-06-20', '09:00:00', '10:00:00', 
'18:54:00', 1, 1, 1);

select * from viaje