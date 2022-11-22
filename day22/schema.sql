create table user (
	-- primary key
    username varchar(128) not null,
    password varchar(256) not null,
    email varchar(128) not null,
    dob date not null,
    phone varchar(128),

    primary key(username)
);

create table task (
    task_id         int                         auto_increment,
    task_name       varchar(128)                not null,
    priority        enum('LOW', 'MED', 'HIGH')  default 'MED',
    assign_to       varchar(128),
    completion_date date                        not null,

    primary key(task_id)
);