create table carbody(
	id serial primary key,
	carbody varchar(255)
);
create table engine(
	id serial primary key,
	engine varchar(255)
);
create table transmission(
	id serial primary key,
	transmission varchar(255)
);
create table vehicle(
	id serial primary key,
	brand varchar(255),
	carbody_id int references carbody(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);
insert into carbody(carbody) values ('minivan'), ('sedan'), ('hatchback'), ('jeep');
insert into engine(engine) values ('1.6l'), ('2.0l'), ('2.2l'), ('3.0l');
insert into transmission(transmission) values ('automata'), ('mechanical'), ('robot');
insert into vehicle(brand, carbody_id, engine_id, transmission_id) values('opel', 1, 2, 2), 
('audi', 2, 3, 1), ('chevrolet', 3, 1, 2), ('lada', 2, 1, 2);
--1) Вывести список всех машин и все привязанные к ним детали.
select v.brand, c.carbody, e.engine, t.transmission from vehicle v join carbody c on v.carbody_id = c.id
join engine e on v.engine_id = e.id join transmission t on v.transmission_id = t.id;
--2) Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине, 
--   кузова, двигатели, коробки передач.
select * from carbody c left join vehicle v on c.id = v.carbody_id where v.carbody_id is null;
select * from engine e left join vehicle v on e.id = v.engine_id where v.engine_id is null; 
select * from transmission t left join vehicle v on t.id = v.transmission_id where v.transmission_id is null;