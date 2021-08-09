create table cars_vin_code(
	id serial primary key,
	vin_code varchar(255),
	table_name varchar(255)
);
create table cars(
	id serial primary key,
	brand varchar(255),
	release timestamp,
	table_name varchar(255),
	vin_code_id int references cars_vin_code(id)
);
insert into cars_vin_code (vin_code, table_name) values ('W0L0TGFZ78545877', 'cars_vin_code');
insert into cars_vin_code (vin_code, table_name) values ('W0L0TGFZ78467454', 'cars_vin_code');
insert into cars_vin_code (vin_code, table_name) values ('W0L0TGFZ74257874', 'cars_vin_code');
insert into cars_vin_code (vin_code, table_name) values ('W0L0TGFZ00012415', 'cars_vin_code');
insert into cars (brand, release, table_name, vin_code_id) values ('Chevrolet', date '1979-09-01', 'cars', 1);
insert into cars (brand, release, table_name, vin_code_id) values ('Crysler', date '1961-09-01', 'cars', 2);
insert into cars (brand, release, table_name, vin_code_id) values ('Dodge', date '1998-09-01', 'cars', 3);
insert into cars (brand, release, table_name, vin_code_id) values ('Opel', date '2003-09-01', 'cars', 4);
select * from cars_vin_code v join cars c on c.vin_code_id=v.id;
select v.vin_code, v.table_name, c.brand, c.release, c.table_name, c.vin_code_id from cars c join cars_vin_code v on c.vin_code_id= v.id;
select v.vin_code as ВинКодИзВИНКОД, v.table_name as ИмяТаблицыИзВИНКОД, 
c.brand as БрендИЗМашины, c.release as ДатаВыпускаИзМашины, c.table_name as ИмяТаблицыИзМашины,
c.vin_code_id as ВинКодИзМашины from cars_vin_code v join cars c on c.vin_code_id= v.id;