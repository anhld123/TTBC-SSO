CREATE OR REPLACE PROCEDURE IMS.M01_cdtd (p_pos_cd IN VARCHAR2,p_maxa IN VARCHAR2,p_report_dt IN VARCHAR2,v_viewcur OUT sys_refcursor)        
AS
        D1 NUMBER (32, 4);
        D2  NUMBER (32, 4); 
        D3 NUMBER (32, 4);
        D4  NUMBER (32, 4); 
        D5 NUMBER (32, 4);
        D6  NUMBER (32, 4); 
        D7 NUMBER (32, 4);
        D8  NUMBER (32, 4); 
        D9 NUMBER (32, 4);
        D10  NUMBER (32, 4); 
begin
 FOR jj IN (
   select pos_cd,COMMUNE_ID,report_dt,code,value,mark
            from DG_COMMUNE_ASSESS_DTLS 
            where pos_cd = p_pos_cd and COMMUNE_ID=p_maxa and report_dt = p_report_dt
            order by 1,2,3,4)
                LOOP
            IF jj.code = 'X01' THEN
            D1:=jj.mark;
            ELSIF jj.code = 'X02' THEN   
            D2:=jj.mark;
            ELSIF jj.code = 'X03' THEN   
            D3:=jj.mark;
            ELSIF jj.code = 'X04' THEN   
            D4:=jj.mark;
            ELSIF jj.code = 'X05' THEN   
            D5:=jj.mark;
            ELSIF jj.code = 'X06' THEN   
            D6:=jj.mark;
            ELSIF jj.code = 'X07' THEN   
            D7:=jj.mark;
            ELSIF jj.code = 'X08' THEN   
            D8:=jj.mark;
            ELSIF jj.code = 'X09' THEN   
            D9:=jj.mark;
            ELSIF jj.code = 'X10' THEN   
            D10:=jj.mark;
            end if ;
            end loop;
                  OPEN v_viewcur FOR SELECT D1 D1, D2 D2, D3 D3, D4 D4, D5 D5, D6 D6, D7 D7, D8 D8, D9 D9, D10 D10, D1+D2+D3+D4+D5+D6+D7+D8+D9+D10 TONG, 'X' loai from dual;
    END;
/
