insert into address
values(1001,'New York', 'USA','Saint Johns', 8);
insert into address
values(1002, 'Novi Sad', 'Serbia','Bulevar Oslobodjenja', 18);
insert into address
values(1003,'Novi Sad', 'Serbia','Bulevar Jase Tomica', 24);
insert into address
values(1004, 'Novi Sad', 'Serbia','Zeleznicka', 2);
insert into address
values(1005,'Novi Sad', 'Serbia','Bulevar Kralja Petra', 1);

-- host je 0, guest je 1
insert into users
values(1006, 'mina@gmail.com', 'Mina', 'Minic', '$2a$10$jTIg6RxbEo3qVBkfHXWnmuEjznBxpBQqjAQ91bzPVt8mAnTzQf9ti', 0, 'mina', 1001);
insert into users
values(1007, 'ogi@gmail.com', 'Ognjen', 'Svraka', '$2a$10$jTIg6RxbEo3qVBkfHXWnmuEjznBxpBQqjAQ91bzPVt8mAnTzQf9ti', 1, 'ogi', 1002);
insert into users
values(1008, 'saska@gmail.com', 'Saska', 'Vujovic', '$2a$10$jTIg6RxbEo3qVBkfHXWnmuEjznBxpBQqjAQ91bzPVt8mAnTzQf9ti', 1, 'saska', 1003);
insert into users
values(1009, 'cos@gmail.com', 'Stefan', 'Tosic', '$2a$10$jTIg6RxbEo3qVBkfHXWnmuEjznBxpBQqjAQ91bzPVt8mAnTzQf9ti', 0, 'cos', 1004);
insert into users
values(1010, 'nemanja@gmail.com', 'Nemanja', 'Andric', '$2a$10$jTIg6RxbEo3qVBkfHXWnmuEjznBxpBQqjAQ91bzPVt8mAnTzQf9ti', 0, 'nemanja', 1005);
