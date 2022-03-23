create database timesheet;
use timesheet;
create table status (status_id int primary key auto_increment ,status_name varchar(25) not null unique )ENGINE=InnoDB;
create table mode (mode_id int primary key auto_increment,mode_name varchar(25) not null unique  )ENGINE=InnoDB;
create table staff (staff_id int primary key auto_increment,first_name varchar(50) not null,last_name varchar(50) not null  )ENGINE=InnoDB;
create table transactor (transactor_id int primary key auto_increment,transactor_name varchar(25) not null)ENGINE=InnoDB;
create table category_activity (category_activity_id int primary key auto_increment,category_name varchar(15) not null unique )ENGINE=InnoDB;
create table timesheet (
                        timesheet_id bigint primary key auto_increment,
                        status_id int,
                        transactor_id int,
                        mode_id int,
                        staff_id int,
                        category_activity_id int,
                        FOREIGN KEY (transactor_id) REFERENCES transactor(transactor_id), 
                        FOREIGN KEY (staff_id) REFERENCES staff(staff_id), 
                        FOREIGN KEY (category_activity_id) REFERENCES category_activity(category_activity_id), 
                        FOREIGN KEY (mode_id) REFERENCES mode(mode_id), 
                        FOREIGN KEY (status_id) REFERENCES status(status_id), 
                        time_taken int,
                        activity varchar(255) not null,
                        activity_date datetime not null, 
                        unit_of_time varchar(255) not null
                        )ENGINE=InnoDB;


insert into status (status_id, status_name) values(null,"Ongoing"),(null,"Completed");
insert into mode (mode_id, mode_name) values(null,"Remote"),(null,"Physical");
insert into staff (staff_id, first_name,last_name) values(null,"Nasasira","Rachael"),(null,"Kyarikunda", "Florence"), (null,"Agaba", "Fred"), (null,"Ocheger" ,"Abraham") ,(null,"Selemba", "Faizo"), (null,"Nkwasibwe ", "Brian"), (null,"Atuhaire ", "Collins"), (null,"Mugisha ", "Best");
insert into transactor (transactor_id,transactor_name) values (null,"Wingersoft") ,(null,"Mara Agribusiness") ,(null,"Usave") ,(null,"new client"),(null,"savemore");
insert into category_activity (category_activity_id,category_name) values (null,"Support") , (null,"Administrative"),(null,"Training"),(null,"Development"),(null,"Learning"),(null,"Accounting");
insert into timesheet (timesheet_id,status_id,transactor_id,mode_id ,staff_id,category_activity_id, time_taken,activity,activity_date,unit_of_time) 
            values(null,2,1,2,1,1,2,"Stock-taking",now(),"hours") ,
                  (null,1,5,2,4,1,3,"Domain-registration",now(),"hours");
            

--time track view
CREATE VIEW timesheet_track AS 
SELECT t.activity_date,concat(s.first_name," ",s.last_name) AS staff_name, r.transactor_name,c.category_name,m.mode_name,t.time_taken,
t.unit_of_time, t.activity, cast(t.activity_date as time ) as 'time_of_submission' ,u.status_name FROM timesheet.timesheet t 
INNER JOIN staff s USING(staff_id)
INNER JOIN transactor r USING(transactor_id)
INNER JOIN category_activity c USING(category_activity_id)
INNER JOIN mode m USING(mode_id)
INNER JOIN status u USING(status_id)
;


SELECT activity_date,staff_name,transactor_name,category_name,mode_name,time_taken,unit_of_time,activity,time_of_submission,status_name FROM timesheet_track;