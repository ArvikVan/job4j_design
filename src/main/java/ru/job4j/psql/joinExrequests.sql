select * from devieces d left join owners o on d.owners_id = o.id;
select * from devieces d left join owners o on d.owners_id = o.id where o.id is null;
select * from owners o left join devieces d on o.id = d.owners_id;
select * from devieces d left join owners o on d.owners_id = o.id;
select * from owners o right join devieces d on d.owners_id = o.id;
select * from owners o left join devieces d on o.id = d.owners_id;
select * from devieces d right join owners o on d.owners_id = o.id;
select * from devieces d full join owners o on d.owners_id = o.id;