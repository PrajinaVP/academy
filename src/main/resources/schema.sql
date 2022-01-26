DROP TABLE course IF EXISTS;
CREATE TABLE course (id bigint auto_increment primary key, contact varchar(255) unique, desc varchar(255), name varchar(255), status varchar(255), primary key (id));
 
DROP TABLE login IF EXISTS;
CREATE TABLE login (userName varchar(255) not null unique, password varchar(255) not null, primary key (userName));
  
DROP TABLE module IF EXISTS;
CREATE TABLE module (id bigint auto_increment primary key, courseId bigint, desc varchar(255), name varchar(255), primary key (id) );

ALTER TABLE module ADD CONSTRAINT UQ_module_name UNIQUE(name);