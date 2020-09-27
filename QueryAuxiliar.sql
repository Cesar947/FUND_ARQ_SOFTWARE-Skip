SELECT * FROM skip_db.viaje;
select * from parada;
select * from usuario;
select * from cuenta;
select* from cuenta_rol;
select * from rol;
update cuenta_rol set rol_id = 1 where cuenta_id=2;
insert into parada(estado_tabla,latitud,longitud,ubicacion) values (1,12.123,41.123,"Tu casa");

insert into parada() values(true,15.123123,12,12351,"Mi casas");

select * from ruta;
select * from itinerario;
update viaje set numero_pasajeros = 3 where viaje_id=1;