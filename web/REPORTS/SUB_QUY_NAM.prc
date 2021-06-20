CREATE OR REPLACE PROCEDURE INTELLECT.SUB_QUY_NAM (
    P_TERMFLAG IN VARCHAR2,
    P_DATE      IN VARCHAR2,
    OUTCURSOR    OUT  SYS_REFCURSOR)
AS
    L_LAST_Q DATE;
    L_LAST_Y DATE;
    P_NAM VARCHAR2(10000);
    P_QUY VARCHAR2(10000);
    P_DK VARCHAR2(10000);
    P_THANG VARCHAR2(10000);
BEGIN
        L_LAST_Q := VBSP_PUBLIC_FUNCTION.f_get_last_day_of_quater(P_DATE);
        L_LAST_Y := VBSP_PUBLIC_FUNCTION.f_get_last_day_of_year(P_DATE);
        P_NAM := 'NAM ' || TO_CHAR (L_LAST_Y, 'YYYY');
        P_DK  := 'Ð?nh k? t? ' || '01/01/' || TO_CHAR (L_LAST_Y, 'YYYY') || ' d?n ' || TO_CHAR(TO_DATE (P_DATE),'DD/MM/YYYY');
        P_THANG := 'Tháng ' || TO_CHAR(TO_DATE (P_DATE),'MM/YYYY');
    IF trim(P_TERMFLAG) = '01' THEN
       
        CASE 
        WHEN   TO_CHAR (L_LAST_Q, 'DDMM') = '3103' THEN
                    P_QUY:='QUÝ I ' || '/ ' || P_NAM;
        WHEN  TO_CHAR (L_LAST_Q, 'DDMM') = '3006' THEN
                    P_QUY:='QUÝ II' || '/ ' || P_NAM;
        WHEN  TO_CHAR (L_LAST_Q, 'DDMM') = '3009' THEN
                    P_QUY:='QUÝ III' || '/ ' || P_NAM;      
        WHEN  TO_CHAR (L_LAST_Q, 'DDMM') = '3112' THEN   
                    P_QUY:='QUÝ IV' || '/ ' || P_NAM;
        END CASE;
   ELSIF trim(P_TERMFLAG) = '02' THEN
        P_QUY := P_NAM;
   
   ELSIF trim(P_TERMFLAG) = '03' THEN
        P_QUY := P_THANG;
            
   ELSIF trim(P_TERMFLAG) = '04' THEN
        P_QUY := P_DK;
            
   END IF;
   OPEN OUTCURSOR FOR
         SELECT P_QUY HIENTHI FROM DUAL;
END;
/