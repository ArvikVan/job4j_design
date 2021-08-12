create table owners(
	id serial primary key,
	name varchar(255)
);
create table devieces(
	id serial primary key,
	name varchar(255),
	owners_id int references owners(id)
);
insert into owners(name) values ('Owner 1');
insert into owners(name) values ('Owner 2');
insert into owners(name) values ('Owner 3');
insert into devieces (name, owners_id) values ('Deviece 1', 1);
insert into devieces (name, owners_id) values ('Deviece 2', 2);
insert into devieces (name, owners_id) values ('Deviece 3', 3);
insert into devieces (name, owners_id) values ('Deviece 4', null);
insert into devieces (name, owners_id) values ('Deviece 5', null);
insert into devieces (name, owners_id) values ('Deviece 6', 1);