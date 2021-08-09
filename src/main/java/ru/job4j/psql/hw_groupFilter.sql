create table devices(
    id serial primary key,
    name varchar(255),
    price float
);
create table people(
    id serial primary key,
    name varchar(255)
);
create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);
insert into devices(name, price) values ('nokia', 5000), ('motorola', 4000), ('honor', 20000);
insert into devices(name, price) values ('akira', 15000), ('samsung', 18000);
insert into devices(name, price) values ('note', 30000), ('PC', 60000), ('tablet', 25000);
insert into people(name) values ('alex'), ('samuel'), ('amiran');
insert into devices_people(people_id, device_id) values (1, 3), (1, 5), (1, 8);
insert into devices_people(people_id, device_id) values (2, 2), (2, 4), (2, 7);
insert into devices_people(people_id, device_id) values (3,1), (3, 4), (3, 6), (3, 8);

select avg(price) from devices;
select p.name, avg(d.price) from devices_people as dp join devices as d on dp.device_id = d.id
join people as p on dp.people_id = p.id group by p.name;
select p.name, avg(d.price) from devices_people as dp join devices as d on dp.device_id = d.id
join people as p on dp.people_id = p.id group by p.name having avg(d.price) > 5000;