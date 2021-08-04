create table supernatural(
    id serial primary key,
    name varchar(255),
    age integer,
    weght real
    );
insert into supernatural(name, age, weght) values('Sam', '30', '78.5');
insert into supernatural(name, age, weght) values('Din', '37', '98.5');
select * from supernatural;
update supernatural set name = 'Sam Winchester' where id = 1;
select * from supernatural;
update supernatural set name = 'Dean Winchester' where id = 2;
select * from supernatural;
delete from supernatural;
select * from supernatural;