drop table if exists user_role
drop table if exists role
drop table if exists user
drop table if exists rememberme_token
create table role (id bigint not null auto_increment, role_name varchar(255), primary key (id),unique key (role_name))
create table user (id bigint not null auto_increment, confirm_password varchar(255), date_of_birth datetime, email varchar(255), first_name varchar(255), gender varchar(255), image longblob, last_name varchar(255), password varchar(255), user_name varchar(255), primary key (id), unique key (user_name))
create table user_role (user_id bigint not null, role_id bigint not null, primary key (user_id, role_id))
create table rememberme_token (series varchar(255),user_name varchar(255),token_value varchar(255),date datetime, primary key (series))
alter table user_role add constraint FKa68196081fvovjhkek5m97n3y foreign key (role_id) references role (id)
alter table user_role add constraint FK859n2jvi8ivhui0rl0esws6o foreign key (user_id) references user (id)