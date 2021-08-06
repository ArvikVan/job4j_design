create table amplua(
	id serial primary key,
	name varchar(255)
);

create table soccer_player(
	id serial primary key,
	name varchar(255),
	amplua_id int references amplua(id)
);

insert into amplua(name) values ('forward');
insert into amplua(name) values ('halfback');
insert into soccer_player (name, amplua_id) values ('Rooney', 1);
insert into soccer_player (name, amplua_id) values ('Torres', 1);
insert into soccer_player (name, amplua_id) values ('Gigs', 2);

select * from soccer_player;

select * from amplua where id in (select id from soccer_player);
