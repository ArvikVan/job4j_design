create table gps_device(
	id serial primary key,
	seria int
);

create table vehicle(
	id serial primary key,
	name varchar(255),
	gps_device_id int references gps(id) unique
);
