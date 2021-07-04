create table Users(
id int primary key auto_increment,
login varchar(30) unique not null,
user_name varchar(30) not null,
surname varchar(30) not null,
city varchar(50),
email varchar(50) not null unique,
user_password varchar(30) not null,
date_of_birth date,
token varchar (255)
);

create table Shared_routes(
id int primary key auto_increment,
user_id int not null,
is_danger bool,
start_date datetime,
foreign key(user_id) references users(id)
);

create table Messages(
id int primary key auto_increment,
sender_id int not null,
receiver_id int not null,
message varchar(255) not null,
date_send datetime not null,
foreign key(sender_id) references users(id),
foreign key(receiver_id) references users(id)
);

create table Users_routes(
user_id int not null,
route_id int not null,
primary key(user_id,route_id),
foreign key(user_id) references users(id),
foreign key(route_id) references shared_routes(id)
);

create table Points(
id int primary key auto_increment,
route_id int not null,
lat float not null,
lng float not null,
point_date datetime,
foreign key(route_id) references shared_routes(id)
);

create table Friends(
user1_id int not null,
user2_id int not null,
primary key(user1_id,user2_id),
foreign key(user1_id) references users(id),
foreign key(user2_id) references users(id)
);

create table Invitations(
inviting_id int not null,
invited_id int not null,
primary key(inviting_id,invited_id),
foreign key(inviting_id) references users(id),
foreign key(invited_id) references users(id)
);