CREATE OR REPLACE PROCEDURE IMS.M03_CDTD (p_pos_cd IN VARCHAR2,p_pos_flg IN VARCHAR2,p_report_dt IN VARCHAR2,csrdata OUT sys_refcursor)

    AS        
        main_pos  varchar2(6);
        v_cap_bc number := 0; -- Cap bao cao, 3: tw, 2: cn, 1:pgd
    BEGIN
    select PO_MACN   into main_pos from dmpos where po_ma=p_pos_cd ;
    v_cap_bc:=VBSP_PUBLIC_FUNCTION.F_GET_REPORT_GRADE(main_pos, 'Y') ;
       
        IF (v_cap_bc = 2) THEN 
          
             OPEN csrdata FOR  
             select   substr(pos_cd,3,2) macn, (select ten from dmtinh where ma=substr(pos_cd,3,2))tentinh, (select ten  from dmhuyen where ma=substr(pos_cd,3,4))tenhuyen,
             SUM(DECODE(CLASSIFICATION,1,1,0)) Tot, SUM(DECODE(CLASSIFICATION,2,1,0)) kha ,SUM(DECODE(CLASSIFICATION,3,1,0)) TB, SUM(DECODE(CLASSIFICATION,4,1,0)) Yeu
             from dg_commune_mark 
             where
             substr(pos_cd,3,2) =substr(p_pos_cd,3,2) and report_dt=p_report_dt
             GROUP BY POS_CD;
           
        ELSIF (v_cap_bc = 3) THEN
             OPEN csrdata FOR  
             select   substr(pos_cd,3,2) macn, (select ten from dmtinh where ma=substr(pos_cd,3,2))tentinh,(select ten  from dmhuyen where ma=substr(pos_cd,3,4))tenhuyen,
              SUM(DECODE(CLASSIFICATION,1,1,0)) Tot, SUM(DECODE(CLASSIFICATION,2,1,0)) kha ,SUM(DECODE(CLASSIFICATION,3,1,0)) TB, SUM(DECODE(CLASSIFICATION,4,1,0)) Yeu
             from dg_commune_mark 
             where
            report_dt=p_report_dt
             GROUP BY POS_CD;
            END IF;
    END;
/
