insert into rooms values (1001, true,  false, 1006, true, 'Novi Sad', 3, 2, 'Cosic', true);
insert into rooms values (1002, true,  true, 1006, true, 'Novi Sad', 4, 3, 'Cosina', true);
insert into rooms values (1003, false,  false, 1010, false, 'Prague', 1, 1, 'Nemi', true);
insert into rooms values (1004, false,  true, 1010, false, 'Berlin', 4, 3, 'Nemo', true);

insert into rooms_images values(2000, 'https://firebasestorage.googleapis.com/v0/b/xws-rooms.appspot.com/o/fa1e6343-111f-4d84-9858-831a9c2928ba0?generation=1683555322095137&alt=media', 1001);
insert into rooms_images values(2001, 'https://firebasestorage.googleapis.com/v0/b/xws-rooms.appspot.com/o/72252f68-1d10-4bc5-a3f8-02df3132cf2f1?generation=1683555322442835&alt=media', 1001);
insert into rooms_images values(2001, 'https://firebasestorage.googleapis.com/v0/b/xws-rooms.appspot.com/o/a3cf814d-2d73-44e2-a368-643cdef1d65b7?generation=1683555324786047&alt=media', 1001);
insert into rooms_images values(2002, 'https://firebasestorage.googleapis.com/v0/b/xws-rooms.appspot.com/o/e269ce32-0ab8-4cff-ba93-4733893d609b2?generation=1683555322753409&alt=media', 1002);
insert into rooms_images values(2003, 'https://firebasestorage.googleapis.com/v0/b/xws-rooms.appspot.com/o/f718e872-f389-4c3f-9225-2e1b1fa1599e3?generation=1683555323192614&alt=media', 1002);
insert into rooms_images values(2004, 'https://firebasestorage.googleapis.com/v0/b/xws-rooms.appspot.com/o/a12175bd-077e-4725-90db-538a51ea11824?generation=1683555323648912&alt=media', 1003);
insert into rooms_images values(2005, 'https://firebasestorage.googleapis.com/v0/b/xws-rooms.appspot.com/o/15a2fc95-f60a-48ff-b453-3632589ecf0f5?generation=1683555323958643&alt=media', 1003);
insert into rooms_images values(2006, 'https://firebasestorage.googleapis.com/v0/b/xws-rooms.appspot.com/o/d5e4df60-f204-468e-82d3-56e25dda76186?generation=1683555324363931&alt=media', 1004);
insert into rooms_images values(2007, 'https://firebasestorage.googleapis.com/v0/b/xws-rooms.appspot.com/o/a3cf814d-2d73-44e2-a368-643cdef1d65b7?generation=1683555324786047&alt=media', 1004);

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