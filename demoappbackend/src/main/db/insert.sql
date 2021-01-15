insert into persons (username,displayname,email,password) values ('admin','admin','admin@yopmail.com','998ed4d621742d0c2d85ed84173db569afa194d4597686cae947324aa58ab4bb');
insert into persons (username,displayname,email,password) values ('guest','guest','guest@yopmail.com','1c74c75d5672278aea9c610c98864674f1d8293b85e4081987e4ae15397342bc');

insert into roles (id,name) values (0,'Admin');
insert into roles (id,name) values (1,'Guest');

insert into user_roles (usr_id,role_id) values (1,0);
insert into user_roles (usr_id,role_id) values (2,1);


insert into cities (id) values (2633352);
insert into cities (id) values (2643741);
insert into cities (id) values (2644688);
insert into cities (id) values (2654675);
insert into cities (id) values (2911298);
insert into cities (id) values (2925535);
insert into cities (id) values (2988507);
insert into cities (id) values (2990969);
insert into cities (id) values (3120501);
insert into cities (id) values (3128760);
insert into cities (id) values (4099974);
insert into cities (id) values (4140963);
insert into cities (id) values (4440906);