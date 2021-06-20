/* Formatted on 8/20/2015 8:51:42 AM (QP5 v5.252.13127.32867) */
CREATE OR REPLACE TYPE ADDRESS_T
   AS OBJECT 
(
   street1 VARCHAR2 (25) ,
   street2 VARCHAR2 (25),
   city VARCHAR2 (25) ,
   zip NUMBER 
);
/

show errors;
/* Formatted on 8/20/2015 8:51:49 AM (QP5 v5.252.13127.32867) */
CREATE OR REPLACE TYPE PERSON_T
   AS OBJECT 
(
   name VARCHAR2 (25),
   age NUMBER ,
   addrObj address_t
);
/

show errors;

drop table PersonObjTab;

create table PersonObjTab (id number,adtcol1 person_t);
/* Formatted on 8/20/2015 8:52:10 AM (QP5 v5.252.13127.32867) */
INSERT INTO PersonObjTab
     VALUES (10,
             person_t ('Jack',  25, Address_t (' 10 Embarcadero',' Ferry Plazza','San Francisco',93126)));

INSERT INTO PersonObjTab
     VALUES (11,person_t ('Bob',26,Address_t ('12 Jr MLK','Alley3','Chicago',1090)));



INSERT INTO PersonObjTab
     VALUES (12,person_t ('Doug',27,Address_t ('10 Alley1','Alley2','Denvers',1091)));
commit;


create or replace function getPersonObj (id IN number)
return person_t
as language java
name 'SQLJ_Object.getPersonObj(int) returns PersonObj';
/

show errors;

create or replace procedure insPersonObj(id IN number,
personin IN person_t,
personout IN OUT person_t) as language java
name 'SQLJ_Object.insPersonObj(int, PersonObj,
PersonObj [])';
/
show errors;


/* Formatted on 8/20/2015 8:52:55 AM (QP5 v5.252.13127.32867) */
DECLARE
   m3    person_t;
   m4    person_t;
   par   NUMBER := 25;
   cnt   NUMBER := 0;
BEGIN
   m3 :=
      person_t ('Jane',
                31,
                address_t ('Oracle Parkway',
                           'of 101',
                           'Redwood Shores',
                           94065));
   DBMS_OUTPUT.put_line ('*** Calling insPersonObj Procedure *** ');

   SELECT COUNT (*) INTO cnt FROM PersonObjtab;

   DBMS_OUTPUT.put_line (' Number of Records is ' || cnt);

   insPersonObj (par, m3, m4);
   DBMS_OUTPUT.put_line (' After calling the procedure ');

   SELECT COUNT (*) INTO cnt FROM PersonObjTab;

   DBMS_OUTPUT.put_line ('Number of Records is ' || cnt);
   DBMS_OUTPUT.put_line (
         ' *name is '
      || m4.name
      || '*age is'
      || m4.age
      || ' *street1 is '
      || m4.addrObj.street1
      || ' *street2 is '
      || m4.addrObj.street2
      || ' *city is '
      || m4.addrObj.city
      || '*zip is '
      || m4.addrObj.zip);
   DBMS_OUTPUT.put_line ('*** Calling getPersonObj Function*** ');
   m4 := getPersonObj (11);
   DBMS_OUTPUT.put_line (
         ' *name is '
      || m4.name
      || '*age is'
      || m4.age
      || ' *street1 is '
      || m4.addrObj.street1
      || ' *street2 is '
      || m4.addrObj.street2
      || ' *city is '
      || m4.addrObj.city
      || ' *zip is '
      || m4.addrObj.zip);
END;
/