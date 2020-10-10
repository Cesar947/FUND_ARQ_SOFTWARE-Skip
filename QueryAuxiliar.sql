SELECT * FROM skip_db.viaje;
select * from parada;
select * from usuario;
select * from cuenta;
select* from cuenta_rol;
select * from rol;
update cuenta_rparadaol set rol_id = 1 where cuenta_id=2;
insert into (estado_tabla,latitud,longitud,ubicacion) values (1,12.123,41.123,"Tu casa");
update usuario set imagen = 'https://i0.wp.com/marvin.com.mx/wp-content/uploads/2020/01/shrek-peliculas-gratis-canal-television-italia-2020-1.jpg?resize=800%2C450&ssl=1' where usuario_id=1;
ALTER TABLE parada MODIFY latitud Double;
ALTER TABLE parada MODIFY longitud Double;
insert into parada() values(true,15.123123,12,12351,"Mi casas");

delete from viaje where viaje_id >3

select * from ruta;
select * from itinerario;
update viaje set numero_pasajeros = 3 where viaje_id=1;