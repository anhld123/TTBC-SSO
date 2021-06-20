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

create or replace type PERSON_TAB is table of PERSON_T;

show errors;

drop table PersonObjTab;

create table PersonObjTab1 (id number,adtcol1 PERSON_TAB) nested table adtcol1 store as PERSON_TAB_1;

INSERT INTO PersonObjTab1
     VALUES (10,
             person_tab (
             person_t('Jack1',  25, Address_t (' 10 Embarcadero',' Ferry Plazza 1','San Francisco 1',93126)),
             person_t('Jack2',  26, Address_t (' 11 Embarcadero',' Ferry Plazza 2','San Francisco 2',93127)),
             person_t('Jack3',  27, Address_t (' 12 Embarcadero',' Ferry Plazza 3','San Francisco 3',93128)),
             person_t('Jack4',  28, Address_t (' 13 Embarcadero',' Ferry Plazza 4','San Francisco 4',93129))));

commit;

