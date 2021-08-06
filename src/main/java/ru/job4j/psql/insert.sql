insert into role (role) values ('root');
insert into role (role) values ('sys_user');
insert into role (role) values ('user');
insert into role (role) values ('guest');

insert into rules (rule) values ('rwx');
insert into rules (rule) values ('rw-');
insert into rules (rule) values ('r--');
insert into rules (rule) values ('---');

insert into role_rules (role_id, rule_id) values (1, 1);
insert into role_rules (role_id, rule_id) values (2, 2);
insert into role_rules (role_id, rule_id) values (3, 3);
insert into role_rules (role_id, rule_id) values (4, 4);

insert into users (username, role_id) values ('Ivan', 1);
insert into users (username, role_id) values ('Idraque', 2);
insert into users (username, role_id) values ('Vlad', 3);
insert into users (username, role_id) values ('Igor', 3);
insert into users (username, role_id) values ('NoName', 4);

insert into category (category) values ('firstable');
insert into category (category) values ('secondary');
insert into category (category) values ('non');

insert into state (state) values ('complete');
insert into state (state) values ('inProcess');

insert into item (item, user_id, category_id,state_id) values ('create user', 1, 1, 2);
insert into item (item, user_id, category_id,state_id) values ('delete user', 1, 1, 1);

insert into comments (comment, item_id) values ('firstcomment', 1);
insert into comments (comment, item_id) values ('secondcomment', 2);

insert into attachs (attach, item_id) values ('access.log', 1);
insert into attachs (attach, item_id) values ('access.log', 2);


