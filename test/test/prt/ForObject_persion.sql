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

