insert into rooms values (1001, true,  false, 1006, true, 'Novi Sad', 3, 2, 'Cosic', true);
insert into rooms values (1002, true,  true, 1006, true, 'Novi Sad', 4, 3, 'Cosina', true);
insert into rooms values (1003, false,  false, 1010, false, 'Prague', 1, 1, 'Nemi', true);
insert into rooms values (1004, false,  true, 1010, false, 'Berlin', 4, 3, 'Nemo', true);

/* price type: 0 by guest, 1 by unit*/
insert into prices values (1005, '2023-05-15', '2023-06-15', 0, 25, 1001);
insert into prices values (1006, '2023-06-16', '2023-07-15', 0, 30, 1001);
insert into prices values (1007, '2023-07-16', '2023-08-15', 0, 35, 1001);

insert into prices values (1008, '2023-05-15', '2023-06-15', 0, 20, 1002);
insert into prices values (1009, '2023-06-16', '2023-07-15', 0, 28, 1002);
insert into prices values (1010, '2023-07-16', '2023-08-15', 0, 30, 1002);

insert into prices values (1011, '2023-05-15', '2023-06-15', 1, 35, 1003);
insert into prices values (1012, '2023-06-16', '2023-07-15', 1, 39, 1003);
insert into prices values (1013, '2023-07-16', '2023-08-15', 1, 45, 1003);

insert into prices values (1014, '2023-05-15', '2023-06-15', 1, 120, 1004);
insert into prices values (1015, '2023-06-16', '2023-07-15', 1, 100, 1004);
insert into prices values (1016, '2023-07-16', '2023-08-15', 1, 125, 1004);

insert into availabilities values (1017, '2023-05-15', '2023-08-15', 1001);
insert into availabilities values (1018, '2023-05-15', '2023-08-15', 1002);
insert into availabilities values (1019, '2023-05-15', '2023-08-15', 1003);
insert into availabilities values (1020, '2023-05-15', '2023-08-15', 1004);