DROP DATABASE TEST;

CREATE DATABASE TEST;


use test;

CREATE TABLE DEPARTMENT (ID INTEGER NOT NULL, NAME VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE PROJECT (ID INTEGER NOT NULL, DTYPE VARCHAR(31), NAME VARCHAR(255), PRIMARY KEY (ID));
CREATE TABLE PROJECT_EMPLOYEE (EMPLOYEES_ID INTEGER, PROJECTS_ID INTEGER);
CREATE TABLE EMPLOYEE (ID INTEGER NOT NULL, NAME VARCHAR(255), SALARY BIGINT, STARTDATE DATE, ADDRESS_ID INTEGER, DEPT_ID INTEGER, MANAGER_ID INTEGER, PRIMARY KEY (ID));
CREATE TABLE PHONE (ID BIGINT NOT NULL, NUMBER VARCHAR(255), TYPE VARCHAR(255), EMPLOYEE_ID INTEGER, PRIMARY KEY (ID));
CREATE TABLE ADDRESS (ID INTEGER NOT NULL, CITY VARCHAR(255), STATE VARCHAR(255), STREET VARCHAR(255), ZIP VARCHAR(255), PRIMARY KEY (ID));


INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP) VALUES (1, 'New York', 'NY', '123 Apple Tree Cr.', '10001');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP) VALUES (2, 'Manhattan', 'NY', '654 Stanton Way.', '10003');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP) VALUES (3, 'New York', 'NY', '99 Queen St.', '10001');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP) VALUES (4, 'Redwood Shores', 'CA', '445 McDonell Cr.', '90123');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP) VALUES (5, 'San Jose', 'CA', '624 Hamilton Dr.', '90123');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP) VALUES (6, 'San Jose', 'CA', '724 Coventry Rd.', '90123');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP) VALUES (7, 'San Francisco', 'CA', '77 Manchester Blvd.', '90123');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP) VALUES (8, 'Moorestown', 'NJ', '53 King St.', '08057');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP) VALUES (9, 'New York', 'NY', '14 Industrial Ave.', '10001');
INSERT INTO ADDRESS (ID, CITY, STATE, STREET, ZIP) VALUES (10, 'Redwood Shores', 'CA', '777 High Tech Ln.', '90123');

INSERT INTO DEPARTMENT (ID, NAME) VALUES (1, 'Engineering');
INSERT INTO DEPARTMENT (ID, NAME) VALUES (2, 'QA');
INSERT INTO DEPARTMENT (ID, NAME) VALUES (3, 'Accounting');
INSERT INTO DEPARTMENT (ID, NAME) VALUES (4, 'CAEngOtt');
INSERT INTO DEPARTMENT (ID, NAME) VALUES (5, 'USEngCal');
INSERT INTO DEPARTMENT (ID, NAME) VALUES (6, 'CADocOtt');
INSERT INTO DEPARTMENT (ID, NAME) VALUES (7, 'QA_East');
INSERT INTO DEPARTMENT (ID, NAME) VALUES (8, 'QANorth');

INSERT INTO PROJECT (ID, NAME, DTYPE) VALUES (1, 'Design Release2', 'DP');
INSERT INTO PROJECT (ID, NAME, DTYPE) VALUES (2, 'Release1','DP');
INSERT INTO PROJECT (ID, NAME, DTYPE) VALUES (3, 'Test Release2','QP');
INSERT INTO PROJECT (ID, NAME, DTYPE) VALUES (4, 'Implement Release3','QP');


INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (10, 'anil', 59000, {d '2003-04-16'}, 10, 1, NULL);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (9, 'Sarah', 52000, {d '2002-04-26'}, 9, 2, 10);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (1, 'anirudha', 55000, {d '2001-01-01'}, 1, 2, 9);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (2, 'Rob', 53000, {d '2001-05-23'}, 2, 2, 9);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (3, 'Peter', 40000, {d '2002-08-06'}, 3, 2, 9);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (4, 'Frank', 41000, {d '2003-02-17'}, 4, 1, 10);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (5, 'rani', 60000, {d '2004-11-14'}, 5, 1, 10);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (6, 'Sue', 62000, {d '2005-08-18'}, 6, 1, 10);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (7, 'Stephanie', 54000, {d '2006-06-07'}, 7, 1, 10);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (8, 'Jennifer', 45000, {d '1999-08-11'}, 8, 1, NULL);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (11, 'Marcus', 35000, {d '1995-07-22'}, NULL, NULL, NULL);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (12, 'Joe', 36000, {d '1995-07-22'}, NULL, 3, 11);
INSERT INTO EMPLOYEE (ID, NAME, SALARY, STARTDATE, ADDRESS_ID, DEPT_ID, MANAGER_ID) VALUES (13, 'Jack', 43000, {d '1995-07-22'}, NULL, 3, NULL);

INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (1, '(212)555-1234', 'Office', 1);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (2, '(212)555-9843', 'Home', 1);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (3, '(315)555-6253', 'Office', 2);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (4, '(516)555-9837', 'Office', 3);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (5, '(516)555-2034', 'Cell', 3);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (6, '(650)555-7583', 'Office', 4);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (7, '(650)555-5345', 'Home', 4);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (8, '(650)555-9386', 'Office', 5);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (9, '(650)555-4885', 'Cell', 5);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (10, '(650)555-3836', 'Office', 6);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (11, '(650)555-0985', 'Home', 6);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (12, '(650)555-1946', 'Cell', 6);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (13, '(650)555-4759', 'Office', 7);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (14, '(650)555-4757', 'Home', 7);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (15, '(650)555-6753', 'Office', 8);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (16, '(585)555-0693', 'Office', 9);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (17, '(585)555-3098', 'Home', 9);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (18, '(585)555-1457', 'Cell', 9);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (19, '(650)555-9838', 'Office', 10);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (20, '(650)555-2346', 'Home', 10);
INSERT INTO PHONE (ID, NUMBER, TYPE, EMPLOYEE_ID) VALUES (21, '(650)555-9874', 'Cell', 10);

INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (1, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (2, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (2, 3);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (3, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (3, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (3, 3);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (4, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (5, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (5, 3);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (6, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (6, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (7, 3);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (8, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (8, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (9, 3);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (9, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (10, 1);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (10, 2);
INSERT INTO PROJECT_EMPLOYEE (EMPLOYEES_ID, PROJECTS_ID) VALUES (10, 3);