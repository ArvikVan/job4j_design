create table owners(
	id serial primary key,
	name varchar(255)
);
insert into owners(name) values('John');
insert into owners(name) values('Trevor');
insert into owners(name) values('Kurt');
insert into owners(name) values('Samuel');
create table cars(
	id serial primary key,
	name varchar(255)
);
insert into cars(name) values('Chevrolet Impala');
insert into cars(name) values('Dodge');
insert into cars(name) values('Volvo');

create table cars_owners(
	id serial primary key,
	owners_id int references owners(id),
	cars_id int references cars(id)
);
insert into cars_owners(owners_id, cars_id) values (1, 1);
insert into cars_owners(owners_id, cars_id) values (1, 2);
insert into cars_owners(owners_id, cars_id) values (1, 3);
insert into cars_owners(owners_id, cars_id) values (2, 1);
insert into cars_owners(owners_id, cars_id) values (3, 1);
insert into cars_owners(owners_id, cars_id) values (4, 2);
select * from cars_owners;
