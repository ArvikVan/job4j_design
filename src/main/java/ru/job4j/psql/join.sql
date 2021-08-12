--1. Создать таблицы и заполнить их начальными данными
create table departments(
	id serial primary key,
	name varchar(255)
);
create table emploees(
	id serial primary key,
	name varchar(255),
	department_id int references departments(id)
);
insert into departments(name) values ('dep 1'), ('dep 2'), ('dep 3');
insert into emploees(name, department_id) values ('Vlad', 1), ('Alex', 1);
insert into emploees(name, department_id) values ('Gosha', null), ('Rozha', null);
insert into emploees(name, department_id) values ('Petr', 2), ('Sergo', 2);
--2. Выполнить запросы с left, rigth, full, cross соединениями
select*from emploees e left join departments d on e.department_id = d.id; 
select*from emploees e right join departments d on e.department_id = d.id;
select * from emploees e full join departments d on e.department_id = d.id;
select * from emploees cross join departments;
--3. Используя left join найти департаменты, у которых нет работников
select * from departments d left join emploees e on e.department_id = d.id where e.id is null;
--4. Используя left и right join написать запросы, которые давали бы одинаковый результат. 
--следующие пары запросов будут работать одинаково, отличаться будет возможно только порядок столбцов в результирующей выборке.
select * from emploees e left join departments d  on  e.department_id = d.id;
select * from departments d right join emploees e on d.id = e.department_id;
--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join составить все возможные разнополые пары
create table teens(
	id serial primary key,
	name varchar(255),
	gender varchar(255)
);
insert into teens(name, gender) values ('Vlas', 'm'), ('Petr', 'm');
insert into teens(name, gender) values ('Oks', 'w'), ('Nadya', 'w');
select t1.name, t1.gender, t2.name, t2.gender from teens t1 cross join teens t2 where not t1.gender = t2.gender;
