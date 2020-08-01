CREATE DATABASE FAMS

GO

USE FAMS
GO
CREATE SCHEMA FAMS_G2

GO

insert into [FAMS_G2].channel(channel_name, remarks) values
('channel1', 'none'),
('channel2', 'none'),
('channel3', 'none')
insert into [FAMS_G2].location(location_name, remarks) values
('location1', 'none'),
('location2', 'none'),
('location3', 'none')

insert into [FAMS_G2].university(remarks, university_name) values
('none', 'Hanoi University'),
('none', 'Hanoi Medical University'),
('none', 'ThuyLoi University'),
('none', 'Electric Power University')
insert into [FAMS_G2].faculty(faculty_name, remarks) values
('faculty_name1', 'none'),
('faculty_name2', 'none'),
('faculty_name3', 'none')
insert into [FAMS_G2].class_admin(remarks, class_id) values
('none',1),
('none',2)
insert into [FAMS_G2].class_admin_profile(full_name, gender, class_adminid) values
('class admin 1',1, 1),
('class admin 1',0, 2)

SELECT * FROM FAMS_G2.[user]
SELECT * FROM FAMS_G2.[role]
SELECT * FROM FAMS_G2.user_role

GO
INSERT INTO [FAMS_G2].[channel] ([channel_name],[remarks])
     VALUES ('ThanhChu','Nice')
GO
INSERT INTO [FAMS_G2].[location] ([location_name],[remarks])
     VALUES('Nam Dinh','Fun')
GO
INSERT INTO [FAMS_G2].[location] ([location_name],[remarks])
VALUES('Ha Noi','Fun'),
      ('Hai Phong','Fun')
INSERT INTO [FAMS_G2].[candidate]([application_date],[history]
           ,[remarks],[status],[channel_id],[location_id])
     VALUES
           ('10-02-2020', '', 'Good', 'New', 1, 1),
		   ('10-02-2020', '', 'Good', 'New', 1, 1),
		   ('10-02-2020', '', 'Good', 'New', 1, 1),
		   ('10-02-2020', '', 'Good', 'New', 1, 3),
           ('10-01-2020', '', 'Good', 'Interview - Pass', 1, 1),
		   ('10-01-2020', '', 'Good', 'Interview - Pass', 1, 1),
		   ('10-01-2020', '', 'Good', 'Interview - Pass', 1, 1),
		   ('10-01-2020', '', 'Good', 'Interview - Pass', 1, 1),
		   ('10-01-2020', '', 'Good', 'Interview - Pass', 1, 2),
		   ('10-01-2020', '', 'Good', 'Interview - Pass', 1, 2),
		   ('10-01-2020', '', 'Good', 'Interview - Pass', 1, 2),
		   ('10-01-2020', '', 'Good', 'Interview - Pass', 1, 2),
		    ('10-01-2020', '', 'Good', 'Interview-Fail', 1, 3),
		   ('10-01-2020', '', 'Good', 'Interview-Fail', 1, 3),
		   ('10-01-2020', '', 'Good', 'Interview-Fail', 1, 1),
		   ('10-01-2020', '', 'Good', 'Interview-Fail', 1, 1),

		   ('10-01-2020', '', 'Good', 'Test-Pass', 1, 1),
		   ('10-01-2020', '', 'Good', 'Test-Pass', 1, 2),
		   ('10-01-2020', '', 'Good', 'Test-Pass', 1, 2),
		   ('10-01-2020', '', 'Good', 'Test-Pass', 1, 2),
		    ('10-01-2020', '', 'Good', 'Test-Fail', 1,2),
		   ('10-01-2020', '', 'Good', 'Test-Fail', 1, 1),
		   ('10-01-2020', '', 'Good', 'Test-Fail', 1, 2),
		   ('10-01-2020', '', 'Good', 'Test-Fail', 1, 2)
GO
INSERT INTO [FAMS_G2].[university]([remarks],[university_name])
     VALUES(1,'HaUI'),
			(1,'Utehy'),
			(1,'HaUI2'),
			(1,'HaUI3')
GO
INSERT INTO [FAMS_G2].[entry_test]
           ([date],[language_result],[language_valuator],[remarks],[result],[technical_result] ,[technical_valuator],[time],[candidate_id]) 
		   VALUES ('10-10-1998', 1, 'English', 'Nice', 'Good', 1, 'Java', 1, 1)
GO
INSERT INTO [FAMS_G2].[entry_test]
           ([date],[language_result],[language_valuator],[remarks],[result],[technical_result] ,[technical_valuator],[time],[candidate_id]) 
		   VALUES ('1/10/1998', 1, 'English', 'Nice', 'Good', 1, 'Java', 1, 1)

GO
INSERT INTO [FAMS_G2].[entry_test]
           ([date],[language_result],[language_valuator],[remarks],[result],[technical_result],[technical_valuator],[time],[candidate_id])
     VALUES ('10-01-2020', 40, 'English', '', 'Good', 80, 'Java', 3, 1)
GO
INSERT INTO [FAMS_G2].[offer]
	([contract_type],[job_rank],[offer_salary],[remarks],[technology])
     VALUES('All', 1, 1000,'Great','Java')
GO
INSERT INTO [FAMS_G2].[offer]
	([contract_type],[job_rank],[offer_salary],[remarks],[technology])
     VALUES('All', 1, 1000,'Great','C++')
GO
INSERT INTO [FAMS_G2].[offer]
	([contract_type],[job_rank],[offer_salary],[remarks],[technology])
     VALUES('All', 1, 1500,'Great','.Net')
GO
	INSERT INTO [FAMS_G2].[inter_view]([comments],[interview_time],[interviewer],[remarks],[result],[candidate_id],[offer_id])
	VALUES (N'Khá tốt', 1, 'DieuNT1', 'Nice', 'Good' , 2, 3)
GO

INSERT INTO [FAMS_G2].[faculty]
           ([faculty_name],[remarks])
     VALUES
           ('SE', 'Good')
GO
INSERT INTO [FAMS_G2].[candidate_profile]([account],[allocation_status],[cv],[date_of_birth],[email],[foreign_language]
           ,[full_name],[gender],[graduation_year],[level],[phone],[remarks],[skill],[type],[candidate_id]
           ,[faculty_id],[university_id])
     VALUES
           ( 'ThanhChu','FA','ThanhChu'
           , '10-19-1998'
           , 'chuthanh98nd@gmail.com', 
		   'English', 'Chu Tuan Thanh', 1, '01-06-2020', 'Fresher', '0941146156', 'Nice', 'Spring', 'Exelent', 1, 1 , 1);
GO

SELECT * FROM [FAMS].[FAMS_G2].[entry_test]

GO

select c.* from [FAMS_G2].class_bath c where Upper(class_name) like Upper('%net%')


insert into [FAMS_G2].candidate_profile(full_name, date_of_birth, gender, graduation_year, phone, email, type, skill, university_id, faculty_id, candidate_id, account)
values('le van minh',getdate(), 1, getdate(), 1234567, 'prifce@fam1.com','candidate', '.Net', 1,1,2, 'minhlv1'),
('le van minh',getdate(), 1, getdate(), 1234568, 'prifce@fam2.com','candidate', 'Android', 1,1,3, 'minhlv2'),
('le van minh',getdate(), 1, getdate(), 1234569, 'prifce@fam3.com','candidate', 'Front-End', 1,1,4, 'minhlv3'),
('le van minh',getdate(), 1, getdate(), 1234570, 'prifce@fam4.com','trainee', 'Front-End', 1,1,5, 'minhlv4'),
('le van minh',getdate(), 1, getdate(), 1234571, 'prifce@fam5.com','trainee', 'Front-End', 1,1,6, 'minhlv5'),
('le van minh',getdate(), 1, getdate(), 1234572, 'prifce6@fam.com','trainee', 'java', 1,1,7, 'minhlv6'),
('le van minh',getdate(), 1, getdate(), 1234573, 'prifce61@fam.com','trainee', 'C/C++', 1,1,8, 'minhlv7'),
('le van minh',getdate(), 1, getdate(), 1234574, 'prifce62@fam.com','trainee', 'C/C++', 1,1,9, 'minhlv8'),
('le van minh',getdate(), 1, getdate(), 1234575, 'prifce63@fam.com','trainee', 'C/C++', 1,1,10, 'minhlv9');

GO
insert into [FAMS_G2].university(university_name)
values('utehy1');
insert into [FAMS_G2].university(university_name)
values('utehy2');
insert into [FAMS_G2].university(university_name)
values('utehy3');

insert into [FAMS_G2].faculty(faculty_name)
values('cntt');


insert into [FAMS_G2].candidate_profile(full_name, date_of_birth, gender, graduation_year, phone, email, type, skill, university_id, faculty_id, candidate_id)
values('le van minh',getdate(), 1, getdate(), 123456, 'prifce@fam.com','candidate', 'java', 1,1,1);
insert into [FAMS_G2].candidate_profile(full_name, date_of_birth, gender, graduation_year, phone, email, type, skill, university_id, faculty_id, candidate_id)
values('le van minh',getdate(), 1, getdate(), 1234567, 'prifce@fam1.com','candidate', '.Net', 1,1,2);
insert into [FAMS_G2].candidate_profile(full_name, date_of_birth, gender, graduation_year, phone, email, type, skill, university_id, faculty_id, candidate_id)
values('le van minh',getdate(), 1, getdate(), 1234568, 'prifce@fam2.com','candidate', 'Android', 1,1,3);
insert into [FAMS_G2].candidate_profile(full_name, date_of_birth, gender, graduation_year, phone, email, type, skill, university_id, faculty_id, candidate_id)
values('le van minh',getdate(), 1, getdate(), 1234569, 'prifce@fam3.com','candidate', 'Font-End', 1,1,4);
insert into [FAMS_G2].candidate_profile(full_name, date_of_birth, gender, graduation_year, phone, email, type, skill, university_id, faculty_id, candidate_id)
values('le van minh',getdate(), 1, getdate(), 1234570, 'prifce@fam4.com','candidate', 'Font-End', 1,1,5);
insert into [FAMS_G2].candidate_profile(full_name, date_of_birth, gender, graduation_year, phone, email, type, skill, university_id, faculty_id, candidate_id)
values('le van minh',getdate(), 1, getdate(), 1234571, 'prifce@fam5.com','candidate', 'Font-End', 1,1,6);
insert into [FAMS_G2].candidate_profile(full_name, date_of_birth, gender, graduation_year, phone, email, type, skill, university_id, faculty_id, candidate_id)
values('le van minh',getdate(), 1, getdate(), 1234572, 'prifce6@fam.com','trainee', 'java', 1,1,7);
select c.skill, COUNT(c.candidate_profile_id) as value  from [FAMS_G2].candidate_profile c group by c.skill, c.type having c.type= 'candidate'
GO
INSERT INTO [FAMS_G2].[candidate_profile]([account],[allocation_status],[cv],[date_of_birth],[email],[foreign_language]
           ,[full_name],[gender],[graduation_year],[level],[phone],[remarks],[skill],[type],[candidate_id]
           ,[faculty_id],[university_id])
     VALUES
           ('TuanNguyen', 'FA','TuanNguyen'
           , '10-19-1998'
           , 'tuannguyen98@gmail.com', 
		   'English', 'Nguyen Tuan', 1, '01-06-2020', 'Fresher', '0987654321', 'Nice', 'Spring', 'Exelent', 1, 1 , 1)
GO

INSERT INTO [FAMS_G2].[inter_view]
           ([comments],[interview_time],[interviewer],[remarks],[result],[candidate_id],[offer_id],[interview_date])
     VALUES ( 'Fine', 2, 'DieuNT1', 'Good', '', 1, 1, '1-1-1998')
GO
INSERT INTO [FAMS_G2].[inter_view]
           ([comments],[interview_time],[interviewer],[remarks],[result],[candidate_id],[offer_id],[interview_date])
     VALUES ( 'Fine', 2, 'HoaBT', 'Good', '', 2, 2, '1-1-1998')
GO
SELECT * FROM [FAMS].[FAMS_G2].[candidate]
GO
SELECT * FROM [FAMS].[FAMS_G2].[candidate_profile]
GO
SELECT * FROM [FAMS].[FAMS_G2].[channel]
GO
SELECT * FROM [FAMS].[FAMS_G2].[class_admin]
GO
SELECT * FROM [FAMS].[FAMS_G2].[class_bath]
GO
SELECT * FROM [FAMS].[FAMS_G2].[entry_test]
GO
SELECT * FROM [FAMS_G2].[inter_view]
GO
SELECT * FROM [FAMS_G2].[location]
GO
SELECT * FROM [FAMS_G2].[offer]
GO
SELECT * FROM [FAMS_G2].[trainee]
GO
SELECT * FROM [FAMS_G2].[university]
GO
SELECT TOP (1000) [trainee_id],[remarks],[candidate_profile_id],[class_id],[status_id]
  FROM [FAMS].[FAMS_G2].[trainee]

INSERT INTO [FAMS_G2].class_bath(class_name, status, skill, planned_trainee_number, accepted_trainee_number,actual_trainee_number, location_id)
VALUES('Java 08', 'In process', 'Java', 20,19,17,1),
	  ('Java 07', 'In process', 'Java', 20,19,17,1),
	  ('Java 06', 'In process', 'Java', 20,19,17,1),
	  ('Java 05', 'Planned', 'Java', 20,19,17,1),
	  ('Java 04', 'Planned', 'Java', 20,19,17,1),
	  ('Java 03', 'Planning', 'Java', 20,19,17,1),
	  ('Java 02', 'Planning', 'Java', 20,19,17,1),
	  ('Java 01', 'Planning', 'Java', 20,19,17,2),

	  ('Android 08', 'In process', 'Android', 20,19,17,2),
	  ('Android 07', 'In process', 'Android', 20,19,17,2),
	  ('Android 06', 'In process', 'Android', 20,19,17,1),
	  ('Android 05', 'Planned', 'Android', 20,19,17,1),
	  ('Android 04', 'Planned', 'Android', 20,19,17,1),
	  ('Android 03', 'Planning', 'Android', 20,19,17,1),
	  ('Android 02', 'Planning', 'Android', 20,19,17,1),
	  ('Android 01', 'Planning', 'Android', 20,19,17,2),

	  ('.Net 08', 'In process', '.Net', 20,19,17,3),
	  ('.Net 07', 'In process', '.Net', 20,19,17,3),
	  ('.Net 06', 'In process', '.Net', 20,19,17,3),
	  ('.Net 05', 'Planned', '.Net', 20,19,17,3),
	  ('.Net 04', 'Planned', '.Net', 20,19,17,3),
	  ('.Net 03', 'Planning', '.Net', 20,19,17,3),
	  ('.Net 02', 'Planning', '.Net', 20,19,17,3),
	  ('.Net 01', 'Planning', '.Net', 20,19,17,3),

	  ('Front-end 08', 'In process', 'Front-end', 20,19,17,1),
	  ('Front-end 07', 'In process', 'Front-end', 20,19,17,1),
	  ('Front-end 06', 'In process', 'Front-end', 20,19,17,1),
	  ('Front-end 05', 'Planned', 'Front-end', 20,19,17,1),
	  ('Front-end 04', 'Planned', 'Front-end', 20,19,17,1),
	  ('Front-end 03', 'Planning', 'Front-end', 20,19,17,1),
	  ('Front-end 02', 'Planning', 'Front-end', 20,19,17,1),
	  ('Front-end 01', 'Planning', 'Front-end', 20,19,17,2),

	  ('C/C++ 08', 'In process', 'C/C++', 20,19,17,3),
	  ('C/C++ 07', 'In process', 'C/C++', 20,19,17,3),
	  ('C/C++ 06', 'In process', 'C/C++', 20,19,17,3),
	  ('C/C++ 05', 'Planned', 'C/C++', 20,19,17,1),
	  ('C/C++ 04', 'Planned', 'C/C++', 20,19,17,1),
	  ('C/C++ 03', 'Planning', 'C/C++', 20,19,17,1),
	  ('C/C++ 02', 'Planning', 'C/C++', 20,19,17,2),
	  ('C/C++ 01', 'Planning', 'C/C++', 20,19,17,2)
GO

INSERT INTO [FAMS_G2].status(status_name)
VALUES ('Enrolled'),
		('Waiting for Class'),
		('Waiting for Allocation'),
		('Deferred'),
		('Drop-out')
GO
INSERT INTO [FAMS_G2].trainee(status_id, class_id, candidate_profile_id)
VALUES (1,null,5),
	   (1,null,6),
	   (1,null,7),
	   (1,null,8),
	   (1,null,9)
GO

select  c.skill, COUNT(c.candidate_profile_id) as value
from [FAMS_G2].candidate_profile c join [FAMS_G2].candidate ca on ca.candidate_id= c.candidate_id join [FAMS_G2].location l on ca.location_id= l.location_id
where Upper(ca.status) like Upper('Interview - Pass') and Upper(l.location_name) like Upper('%%') 
group by c.skill
GO

SELECT c.skill, COUNT(c.class_id) as value FROM [FAMS_G2].class_bath c join [FAMS_G2].location l on c.location_id=l.location_id
WHERE Upper(c.status) like Upper('Planning') and Upper(l.location_name) like Upper('Ha Noi') 
GROUP BY c.skill
GO

SELECT  c.skill, COUNT(c.candidate_profile_id) as value
FROM [FAMS_G2].candidate_profile c JOIN [FAMS_G2].trainee t on t.candidate_profile_id= c.candidate_profile_id JOIN [FAMS_G2].candidate ca on ca.candidate_id= c.candidate_id 
JOIN [FAMS_G2].location l on ca.location_id= l.location_id JOIN [FAMS_G2].status s ON t.status_id= s.status_id
WHERE Upper(s.status_name) like Upper('Enrolled') and Upper(l.location_name) like Upper('Location1')  
GROUP BY c.skill
GO
SELECT * FROM FAMS_G2.[user]
SELECT * FROM FAMS_G2.[role]
SELECT * FROM dbo.user_role

