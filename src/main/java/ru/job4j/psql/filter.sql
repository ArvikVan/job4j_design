--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product p join type t on p.id = t.id where t.name = 'сыр';
--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select * from product where name like '%мороженное%';
--3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from product where current_date > expired_date;
--4. Написать запрос, который выводит самый дорогой продукт.
select * from product;
select * from product where price = (select max(price) from product);
--5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select * from product;
select t.name as "Имя_типа", count(p.name) as "Количество" from product as p join type as t on p.type_id = t.id group by t.name;
--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product;
select * from product p join type t on p.id = t.id where t.name in ('сыр', 'молоко');
--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. 
--Под количеством подразумевается количество продуктов определенного типа. Например, 
--если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат,
--то количество продуктов типа "СЫР" будет 2. 
select * from product;
select t.name as "Имя_типа", count(p.name) as "Количество" 
from product as p join type as t on p.type_id = t.id group by t.name having count(p.name) < 10;
--8. Вывести все продукты и их тип.
select * from product p join type t on p.id = t.id;


