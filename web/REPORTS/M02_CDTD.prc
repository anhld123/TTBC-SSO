CREATE OR REPLACE PROCEDURE IMS.M02_cdtd (p_pos_cd IN VARCHAR2,p_report_dt IN VARCHAR2,csrdata OUT sys_refcursor)        
    AS        

    BEGIN
            
            OPEN csrdata FOR
                    SELECT A.POS_CD,A.COMMUNE_ID,(select x.ten FROM dmxa x WHERE x.ma = a.COMMUNE_ID) tenxa ,  SUM(DECODE(CODE,'X01',MARK,0)) D1, SUM(DECODE(CODE,'X02',MARK,0)) D2, SUM(DECODE(CODE,'X03',MARK,0)) D3,
                   SUM(DECODE(CODE,'X04',MARK,0)) D4, SUM(DECODE(CODE,'X05',MARK,0)) D5, SUM(DECODE(CODE,'X06',MARK,0)) D6,
                   SUM(DECODE(CODE,'X07',MARK,0)) D7, SUM(DECODE(CODE,'X08',MARK,0)) D8, SUM(DECODE(CODE,'X09',MARK,0)) D9,
                   SUM(DECODE(CODE,'X10',MARK,0)) D10,
                   (select total_mark from dg_commune_mark X where x.pos_Cd = A.pos_cd and x.COMMUNE_ID = a.COMMUNE_ID and x.report_dt = p_report_dt) tongdiem,
                    (select CLASSIFICATION from dg_commune_mark X where x.pos_Cd = A.pos_cd and x.COMMUNE_ID = a.COMMUNE_ID and x.report_dt = p_report_dt) XEPLOAI
--                    (select case when CLASSIFICATION = 1 then 'X' else '' end  
--                    from dg_commune_mark X where x.pos_Cd = A.pos_cd and x.COMMUNE_ID = a.COMMUNE_ID and x.report_dt = p_report_dt) xeploai_t,
--                     (select case when CLASSIFICATION = 2 then 'X' else '' end  
--                    from dg_commune_mark X where x.pos_Cd = A.pos_cd and x.COMMUNE_ID = a.COMMUNE_ID and x.report_dt = p_report_dt) xeploai_K,
--                     (select case when CLASSIFICATION = 3 then 'X' else '' end  
--                    from dg_commune_mark X where x.pos_Cd = A.pos_cd and x.COMMUNE_ID = a.COMMUNE_ID and x.report_dt = p_report_dt) xeploai_tB,
--                     (select case when CLASSIFICATION = 4 then 'X' else '' end  
--                    from dg_commune_mark X where x.pos_Cd = A.pos_cd and x.COMMUNE_ID = a.COMMUNE_ID and x.report_dt = p_report_dt) xeploai_Y
                   from DG_COMMUNE_ASSESS_DTLS  A    
                   where
                  a.POS_CD =p_pos_cd
                   AND report_dt=p_report_dt
                   GROUP BY A.POS_CD,A.COMMUNE_ID
                   ORDER BY 1,2;

    END;
/
