insert into user_details(id, birth_date, name)
values (1001, current_date(), 'Vineet');

insert into user_details(id, birth_date, name)
values (1002, current_date(), 'Aniket');

insert into user_details(id, birth_date, name)
values (1003, current_date(), 'Pratik');

insert into Post(id, description, user_id)
values (101, 'I Won Cricket Competition Today', 1001);

insert into Post(id, description, user_id)
values (102, 'I Won football Competition Today', 1001);

insert into Post(id, description, user_id)
values (103, 'I Won Dance Competition Today', 1002);

insert into Post(id, description, user_id)
values (104, 'I Won Singing Competition Today', 1002);

insert into Post(id, description, user_id)
values (105, 'I Won Basketball Competition Today', 1003);