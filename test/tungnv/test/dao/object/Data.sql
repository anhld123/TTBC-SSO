

create type KH_TYPE as object
(
  KH_MAKH      VARCHAR2(10 BYTE),
  KH_TENKH     VARCHAR2(100 BYTE),
  KH_NGAYSINH  DATE,
  KH_CMT       VARCHAR2(20 BYTE),
  KH_DIACHI    VARCHAR2(250 BYTE),
  KH_MADP      VARCHAR2(8 BYTE)
);

create type KH_TAB is table of KH_TYPE;

drop table KH_OBJECT_TAB

create table KH_OBJECT_TAB
(
    KH_MAPGD varchar2(6),
    KH_TENPGD varchar2(50),
    KH_MAXA varchar2(10),
    KH_TENXA varchar2(50),
    KH_MATHON varchar2(10),
    KH_TENTHON varchar2(50),
    KH_LIST KH_TAB
) nested table KH_LIST store as KH_LIST_KH;

select * from dmthon

select * from hssv

select KH_MAKH,KH_TENKH,KH_NGAYSINH,KH_CMT,KH_DIACHI,KH_MADP from hskh, hsku where KH_MADP = '10031501' and kh_makh=ku_makh and ku_ngaybc='28-feb-2015'

declare 
--type ARR_KH_TAB is table of KH_TYPE;
PV_KH_TAB KH_TAB;
begin
    for rec_pos in (select pos_cd, pos_desc from po850mb where main_pos='001011' order by pos_cd)
    loop
        for rec_thon in (select a.ma maxa, a.ten tenxa, b.ma mathon, b.ten tenthon from dmxa a, dmthon b 
        where PGD_QL=rec_pos.pos_cd and a.ma=b.xa order by a.ma,b.ma)
        loop
            select KH_TYPE(KH_MAKH,KH_TENKH,KH_NGAYSINH,KH_CMT,KH_DIACHI,KH_MADP) bulk collect into PV_KH_TAB from hskh where kh_madp=rec_thon.mathon order by kh_makh;
--            dbms_output.put_line(rec_pos.pos_desc);
            insert into KH_OBJECT_TAB(KH_MAPGD,KH_TENPGD,KH_MAXA,KH_TENXA,KH_MATHON,KH_TENTHON,KH_LIST)
            values(rec_pos.pos_cd,rec_pos.pos_desc,rec_thon.maxa,rec_thon.tenxa,rec_thon.mathon,rec_thon.tenthon,PV_KH_TAB);
        end loop;
        commit;
    end loop;
    exception when others then
    rollback;
    dbms_output.put_line(sqlerrm);
end;


select * from dmxa where pgd_ql='001003'

select KH_TYPE(KH_MAKH,KH_TENKH,KH_NGAYSINH,KH_CMT,KH_DIACHI,KH_MADP) from hskh where kh_madp='10031501'

select a.ma maxa, a.ten tenxa, b.ma mathon, b.ten tenthon from dmxa a, dmthon b where PGD_QL='001003' and a.ma=b.xa order by a.ma,b.ma

select * from dmthon

select pos_cd, pos_desc, add_street1,add_street2, add_city from po850mb where main_pos='001011'


SELECT * FROM KH_OBJECT_TAB WHERE KH_MAPGD=? ORDER BY KH_MAXA, KH_MATHON
 

create or replace procedure sp_tab_by_tab(pv_pos_cd in varchar2, pv_err_cd out number, pv_err_txt out varchar2, csrdata out sys_refcursor)
as
begin

    open csrdata for SELECT * FROM KH_OBJECT_TAB WHERE KH_MAPGD=pv_pos_cd ORDER BY KH_MAXA, KH_MATHON;
    exception when others then
    pv_err_cd:=sqlcode;
    pv_err_txt:='exception -> '||sqlerrm;
    raise;
end;


var b refcursor;
declare err_cd number;
err_txt varchar2(2000);
begin
    sp_tab_by_tab('001003',err_cd,err_txt,:b);
end;
print b;

-----------------------------------------


CREATE TYPE dnames_tab AS TABLE OF VARCHAR2(30);
/
CREATE TABLE depts (region VARCHAR2(25), dept_names dnames_tab) 
   NESTED TABLE dept_names STORE AS dnames_nt;
BEGIN
   INSERT INTO depts VALUES('Europe', dnames_tab('Shipping','Sales','Finance'));
   INSERT INTO depts VALUES('Americas', dnames_tab('Sales','Finance','Shipping'));
   INSERT INTO depts VALUES('Asia', dnames_tab('Finance','Payroll'));
   COMMIT;
END;
/




create type tungnv_type_object as table of varchar2(6);

create table Tungnv_tab_object
(
    so_pgd int,
    city varchar2(100),
    ma_pgd tungnv_type_object
) nested table ma_pgd store as ma_pgd_nt;


select * from Tungnv_tab_object order by so_pgd


insert into Tungnv_tab_object (so_pgd,city,ma_pgd)
values(5,'Hải phòng',tungnv_type_object('000301',
'000302',
'000303',
'000304',
'000305'));


select ''''||pos_cd||''',' pos, pos_desc from po850mb where main_pos ='000314' and rownum<6


CREATE OR REPLACE procedure IMS.sp_tab_by_tab(pv_pos_cd in varchar2, pv_err_cd out number, pv_err_txt out varchar2, csrdata out sys_refcursor)
as
begin

    open csrdata for SELECT * FROM KH_OBJECT_TAB WHERE KH_MAPGD=pv_pos_cd ORDER BY KH_MAXA, KH_MATHON;
    exception when others then
    pv_err_cd:=sqlcode;
    pv_err_txt:='exception -> '||sqlerrm;
    raise;
end;
/

CREATE OR REPLACE procedure sp_ins_tab_by_tab(pv_mapgd in varchar2, pv_tenpgd in varchar2,pv_maxa in varchar2,pv_tenxa in varchar2,
pv_mathon in varchar2,pv_tenthon in varchar2,arr_kh_list in KH_TAB,
pv_err_cd out number, pv_err_txt out varchar2)
as
begin

    insert into KH_OBJECT_TAB (kh_mapgd, kh_tenpgd, kh_maxa,kh_tenxa, kh_mathon,kh_tenthon,kh_list)
values(pv_mapgd, pv_tenpgd, pv_maxa, pv_tenxa, pv_mathon, pv_tenthon, arr_kh_list);
commit;
    exception when others then
    rollback;
    pv_err_cd:=sqlcode;
    pv_err_txt:='exception -> '||sqlerrm;
    raise;
end;
/