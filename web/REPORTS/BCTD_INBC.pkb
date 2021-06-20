CREATE OR REPLACE PACKAGE BODY IMS.BCTD_INBC
AS
    /* ********************************************************************************************* 
    * Package: BCTD_INBC 
    * Module: In báo cáo tín dụng (VB308)
    * Created by: TrungNT88
    * Maker date: 19-apr-2016
    * Edited date: 
    * **********************************************************************************************/
    
    --- Thủ tục in báo cáo theo Quyet dinh 308/QĐ-NHCS. Mẫu TT45_05
    --- Người tạo: CHUDV
    --- Ngày tạo: 19/04/2016
    
    PROCEDURE INSL_BCTD_TT45_05(P_POS_CD IN VARCHAR2,P_POS_FLAG IN VARCHAR2,P_REPORT_DATE IN VARCHAR2,P_PRINCUR OUT SYS_REFCURSOR)
    AS
        LV_MAIN_POS     VARCHAR2(6);
        LV_KHOA         VARCHAR2(32);
    BEGIN
        SELECT PO_MACN INTO LV_MAIN_POS FROM DMPOS WHERE PO_MA = P_POS_CD;
        LV_KHOA := 'BCTD_TT45_05';
        
        OPEN P_PRINCUR FOR 
        SELECT 'I' TT_HIENTHI, 1 KIEUIN,BCTD_MACN,BCTD_MAPGD,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN,'' BCTD_XA,'' TENXA,'' BCTD_DTTH,'Người lao động' BCTD_D1,'' BCTD_D2,
               '' BCTD_D4,'' BCTD_MADP,'' TENTHON,'' DIADIEM,SUM(BCTD_D21) BCTD_D21,SUM(BCTD_D22) BCTD_D22,0 BCTD_COL8,0 BCTD_D23,0 BCTD_D24,
               SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28
               FROM
               (
                SELECT BCTD_MACN,BCTD_MAPGD,BCTD_TINH,E.TEN BCTD_TENTINH,BCTD_HUYEN,
                       REPLACE(REPLACE(NVL(D.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,
                       BCTD_XA,BCTD_DTTH,BCTD_D1,BCTD_D2,BCTD_D4,BCTD_MADP,BCTD_D21,BCTD_D22,
                       BCTD_D23,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28 
                       FROM BCTD_DULIEU A, DMHUYEN D, DMTINH E
                       WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                             AND BCTD_DTTH NOT IN ('11','13','14','17','20','27','28','29','30')
                             AND SUBSTR(A.BCTD_MADP,1,4)=D.MA(+) AND A.BCTD_TINH=E.MA(+) AND E.MA <>'00'
               ) GROUP BY BCTD_MACN,BCTD_MAPGD,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN
        UNION ALL
        SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY BCTD_D6)) TT_HIENTHI,3 KIEUIN,BCTD_MACN,BCTD_MAPGD,BCTD_TINH,E.TEN BCTD_TENTINH,BCTD_HUYEN,
                       REPLACE(REPLACE(NVL(D.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,
                       BCTD_XA,NVL(C.TEN,'') TENXA,BCTD_DTTH,BCTD_D1,BCTD_D2,BCTD_D4,BCTD_MADP,NVL(B.TEN,'') TENTHON,NVL(B.TEN,'') ||' - '|| NVL(C.TEN,'') DIADIEM,
                       BCTD_D21,BCTD_D22,(CASE WHEN BCTD_D21<>0 THEN ROUND((BCTD_D22/BCTD_D21)*100,2) ELSE 0 END) AS BCTD_COL8,
                       BCTD_D23,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28 
                       FROM BCTD_DULIEU A,DMTHON B, DMXA C, DMHUYEN D, DMTINH E
                       WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                             AND BCTD_DTTH NOT IN ('11','13','14','17','20','27','28','29','30')
                             AND A.BCTD_MADP=B.MA(+) AND SUBSTR(A.BCTD_MADP,1,6)=C.MA(+)
                             AND SUBSTR(A.BCTD_MADP,1,4)=D.MA(+) AND A.BCTD_TINH=E.MA(+) AND E.MA <>'00' 
        UNION ALL
        SELECT 'II' TT_HIENTHI,1 KIEUIN,BCTD_MACN,BCTD_MAPGD,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN,'' BCTD_XA,'' TENXA,'' BCTD_DTTH,'Cơ sở sản xuất kinh doanh' BCTD_D1,'' BCTD_D2,
               '' BCTD_D4,'' BCTD_MADP,'' TENTHON,'' DIADIEM,SUM(BCTD_D21) BCTD_D21,SUM(BCTD_D22) BCTD_D22,0 BCTD_COL8,0 BCTD_D23,0 BCTD_D24,
               SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28
               FROM
               (
                SELECT BCTD_MACN,BCTD_MAPGD,BCTD_TINH,E.TEN BCTD_TENTINH,BCTD_HUYEN,
                       REPLACE(REPLACE(NVL(D.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,
                       BCTD_XA,BCTD_DTTH,BCTD_D1,BCTD_D2,BCTD_D4,BCTD_MADP,BCTD_D21,BCTD_D22,
                       BCTD_D23,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28 
                       FROM BCTD_DULIEU A, DMHUYEN D, DMTINH E
                       WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                             AND BCTD_DTTH IN ('11','13','14','17','20','27','28','29','30')
                             AND SUBSTR(A.BCTD_MADP,1,4)=D.MA(+) AND A.BCTD_TINH=E.MA(+) AND E.MA <>'00'
               ) GROUP BY BCTD_MACN,BCTD_MAPGD,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN
        UNION ALL
        SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY BCTD_D6)) TT_HIENTHI,3 KIEUIN,BCTD_MACN,BCTD_MAPGD,BCTD_TINH,E.TEN BCTD_TENTINH,BCTD_HUYEN,
                       REPLACE(REPLACE(NVL(D.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,
                       BCTD_XA,NVL(C.TEN,'') TENXA,BCTD_DTTH,BCTD_D1,BCTD_D2,BCTD_D4,BCTD_MADP,NVL(B.TEN,'') TENTHON,NVL(B.TEN,'') ||' - '|| NVL(C.TEN,'') DIADIEM,
                       BCTD_D21,BCTD_D22,(CASE WHEN BCTD_D21<>0 THEN ROUND((BCTD_D22/BCTD_D21)*100,2) ELSE 0 END) AS BCTD_COL8,
                       BCTD_D23,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28 
                       FROM BCTD_DULIEU A,DMTHON B, DMXA C, DMHUYEN D, DMTINH E
                       WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                             AND BCTD_DTTH IN ('11','13','14','17','20','27','28','29','30')
                             AND A.BCTD_MADP=B.MA(+) AND SUBSTR(A.BCTD_MADP,1,6)=C.MA(+)
                             AND SUBSTR(A.BCTD_MADP,1,4)=D.MA(+) AND A.BCTD_TINH=E.MA(+) AND E.MA <>'00'
        UNION ALL
        SELECT '' TT_HIENTHI,1 KIEUIN,BCTD_MACN,BCTD_MAPGD,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN,'' BCTD_XA,'' TENXA,'' BCTD_DTTH,'Tổng cộng' BCTD_D1,'' BCTD_D2,
               '' BCTD_D4,'' BCTD_MADP,'' TENTHON,'' DIADIEM,SUM(BCTD_D21) BCTD_D21,SUM(BCTD_D22) BCTD_D22,0 BCTD_COL8,0 BCTD_D23,0 BCTD_D24,
               SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28
               FROM
               (
                SELECT BCTD_MACN,BCTD_MAPGD,BCTD_TINH,E.TEN BCTD_TENTINH,BCTD_HUYEN,
                       REPLACE(REPLACE(NVL(D.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,
                       BCTD_XA,BCTD_DTTH,BCTD_D1,BCTD_D2,BCTD_D4,BCTD_MADP,BCTD_D21,BCTD_D22,
                       BCTD_D23,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28 
                       FROM BCTD_DULIEU A, DMHUYEN D, DMTINH E
                       WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                             AND SUBSTR(A.BCTD_MADP,1,4)=D.MA(+) AND A.BCTD_TINH=E.MA(+) AND E.MA <>'00'
               ) GROUP BY BCTD_MACN,BCTD_MAPGD,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN;       
    END;
    
    PROCEDURE INSL_BCTD_TT45_07(P_POS_CD IN VARCHAR2,P_POS_FLAG IN VARCHAR2,P_REPORT_DATE IN VARCHAR2,P_PRINCUR OUT SYS_REFCURSOR)
    AS
        LV_MAIN_POS     VARCHAR2(6);
        LV_KHOA         VARCHAR2(32);
        LN_CAPBC        NUMBER;
    BEGIN
        SELECT PO_MACN INTO LV_MAIN_POS FROM DMPOS WHERE PO_MA = P_POS_CD;
        LV_KHOA := 'BCTD_TT45_07_09';
        LN_CAPBC := VBSP_PUBLIC_FUNCTION.F_GET_REPORT_GRADE(P_POS_CD,P_POS_FLAG);
        
        IF LN_CAPBC = 1 THEN
            OPEN P_PRINCUR FOR 
                SELECT TT_HIENTHI,KIEUIN,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN BCTD_TENHUYEN,TENHUYEN BCTD_D1,BCTD_D22,BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,
                       BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                    FROM
                    (
                        SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY BCTD_HUYEN)) TT_HIENTHI,KIEUIN,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN,
                               SUM(BCTD_D22) BCTD_D22,SUM(BCTD_COL4) BCTD_COL4,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,
                               SUM(BCTD_D26) BCTD_D26,SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                               SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,SUM(BCTD_D35) BCTD_D35,
                               SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                            FROM   
                            (    
                            SELECT 3 KIEUIN,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,
                               BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,
                               BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                               FROM BCTD_DULIEU A,DMHUYEN B, DMTINH C
                               WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                                     AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                     AND A.BCTD_HUYEN=SUBSTR(b.MA(+),3,2) and SUBSTR(b.MA,1,2)=A.BCTD_TINH
                                     AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00'
                            ) GROUP BY KIEUIN,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN
                        UNION ALL
                        SELECT '' TT_HIENTHI,1 KIEUIN,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,'' BCTD_HUYEN,'Tổng cộng' TENHUYEN,
                               SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D21-BCTD_D23) BCTD_COL4,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,
                               SUM(BCTD_D26) BCTD_D26,SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                               SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,SUM(BCTD_D35) BCTD_D35,
                               SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                               FROM BCTD_DULIEU A,DMTINH C
                               WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                                     AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                     AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00'
                                     GROUP BY BCTD_TINH,NVL(C.TEN,'')
                    );
        ELSIF LN_CAPBC = 2 THEN
            OPEN P_PRINCUR FOR 
                SELECT TT_HIENTHI,KIEUIN,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN BCTD_TENHUYEN,TENHUYEN BCTD_D1,BCTD_D22,BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,
                       BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                    FROM
                    (
                    SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY BCTD_HUYEN)) TT_HIENTHI,KIEUIN,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN,
                                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_COL4) BCTD_COL4,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,
                                       SUM(BCTD_D26) BCTD_D26,SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,SUM(BCTD_D35) BCTD_D35,
                                       SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                                    FROM   
                                    (    
                                    SELECT 3 KIEUIN,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,
                                       BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,
                                       BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                       FROM BCTD_DULIEU A,DMHUYEN B, DMTINH C
                                       WHERE BCTD_KHOA = LV_KHOA AND BCTD_MACN=P_POS_CD
                                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                             AND A.BCTD_HUYEN=SUBSTR(b.MA(+),3,2) and SUBSTR(b.MA,1,2)=A.BCTD_TINH
                                             AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00'
                                    ) GROUP BY KIEUIN,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN
                                UNION ALL
                                SELECT '' TT_HIENTHI,1 KIEUIN,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,'' BCTD_HUYEN,'Tổng cộng' TENHUYEN,
                                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D21-BCTD_D23) BCTD_COL4,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,
                                       SUM(BCTD_D26) BCTD_D26,SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,SUM(BCTD_D35) BCTD_D35,
                                       SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                                       FROM BCTD_DULIEU A,DMTINH C
                                       WHERE BCTD_KHOA = LV_KHOA AND BCTD_MACN=P_POS_CD
                                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                             AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00'
                                             GROUP BY BCTD_TINH,NVL(C.TEN,'')
                );
                             
        ELSIF LN_CAPBC = 3 THEN
            OPEN P_PRINCUR FOR
                SELECT TT_HIENTHI,KIEUIN,BCTD_TINH,BCTD_TENTINH,BCTD_TENTINH BCTD_D1,BCTD_HUYEN,TENHUYEN BCTD_TENHUYEN,BCTD_D22,BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,
                       BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                    FROM
                    (
                        SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY BCTD_TINH)) TT_HIENTHI,KIEUIN,BCTD_TINH,BCTD_TENTINH,'' BCTD_HUYEN,'' TENHUYEN,
                               SUM(BCTD_D22) BCTD_D22,SUM(BCTD_COL4) BCTD_COL4,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,
                               SUM(BCTD_D26) BCTD_D26,SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                               SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,SUM(BCTD_D35) BCTD_D35,
                               SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                            FROM   
                            (    
                            SELECT 3 KIEUIN,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,
                               BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,
                               BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                               FROM BCTD_DULIEU A,DMHUYEN B, DMTINH C
                               WHERE BCTD_KHOA = LV_KHOA
                                     AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                     AND A.BCTD_HUYEN=SUBSTR(b.MA(+),3,2) and SUBSTR(b.MA,1,2)=A.BCTD_TINH
                                     AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00'
                            ) GROUP BY KIEUIN,BCTD_TINH,BCTD_TENTINH
                        UNION ALL
                        SELECT '' TT_HIENTHI,1 KIEUIN,''BCTD_TINH,'Tổng cộng' BCTD_TENTINH,'' BCTD_HUYEN,'' TENHUYEN,
                               SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D21-BCTD_D23) BCTD_COL4,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,
                               SUM(BCTD_D26) BCTD_D26,SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                               SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,SUM(BCTD_D35) BCTD_D35,
                               SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                               FROM BCTD_DULIEU A,DMTINH C
                               WHERE BCTD_KHOA = LV_KHOA
                                     AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                     AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00'
                );
        END IF;
    END;


    PROCEDURE INSL_BCTD_TT45_09(P_POS_CD IN VARCHAR2,P_POS_FLAG IN VARCHAR2,P_REPORT_DATE IN VARCHAR2,P_PRINCUR OUT SYS_REFCURSOR)
    AS
        LV_MAIN_POS     VARCHAR2(6);
        LV_KHOA         VARCHAR2(32);
        LN_CAPBC        NUMBER;
    BEGIN
        SELECT PO_MACN INTO LV_MAIN_POS FROM DMPOS WHERE PO_MA = P_POS_CD;
        LV_KHOA := 'BCTD_TT45_07_09';
        LN_CAPBC := VBSP_PUBLIC_FUNCTION.F_GET_REPORT_GRADE(P_POS_CD,P_POS_FLAG);
        
        IF LN_CAPBC = 1 THEN
            OPEN P_PRINCUR FOR 
                SELECT 'I' TT_HIENTHI,1 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,'' BCTD_TINH,'' BCTD_TENTINH,'' BCTD_HUYEN,'' TENHUYEN,
                       'Tỉnh, thành phố (UBND)' BCTD_D1,0 BCTD_COL4,0 BCTD_D22,0 BCTD_D24,0 BCTD_D25,0 BCTD_D26,0 BCTD_D27,0 BCTD_D28,
                       0 BCTD_D29,0 BCTD_D30,0 BCTD_D31,0 BCTD_D32,0 BCTD_D33,0 BCTD_D34,0 BCTD_D35,0 BCTD_D36,0 BCTD_D37,0 BCTD_D38 FROM DUAL
                UNION ALL
                SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY BCTD_HUYEN)) TT_HIENTHI,KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,
                       BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN,TENHUYEN BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                       SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                       SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                       FROM
                        (
                            SELECT 3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,
                                   REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,
                                   BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                   FROM
                                   (
                                    SELECT B.KHOA_2 BCTD_CAPQLV,B.GIATRI BCTD_TENCQLV,NVL(BCTD_TINH,SUBSTR(P_POS_CD,3,2)) BCTD_TINH,
                                           NVL(BCTD_HUYEN,SUBSTR(P_POS_CD,5,2)) BCTD_HUYEN,NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                           NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                           NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                           NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                           NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                           FROM 
                                            (  
                                            SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                   BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                   FROM BCTD_DULIEU
                                                   WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                                                         AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                            ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                            WHERE A.BCTD_CAPQLV(+)= B.KHOA_2
                                   ) A, DMHUYEN B, DMTINH C
                                   WHERE A.BCTD_HUYEN=SUBSTR(B.MA(+),3,2) AND SUBSTR(B.MA,1,2)=A.BCTD_TINH
                                         AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN
                            ) WHERE BCTD_CAPQLV='20' GROUP BY KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN
                UNION ALL
                SELECT '' TT_HIENTHI,2 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,
                       '' BCTD_TINH,'' BCTD_TENTINH,'' BCTD_HUYEN,'Tổng' TENHUYEN,'Tổng' BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                       SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                       SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                       FROM
                        (
                            SELECT 3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,
                                   REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,
                                   BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                   FROM
                                   (
                                    SELECT B.KHOA_2 BCTD_CAPQLV,B.GIATRI BCTD_TENCQLV,NVL(BCTD_TINH,SUBSTR(P_POS_CD,3,2)) BCTD_TINH,
                                           NVL(BCTD_HUYEN,SUBSTR(P_POS_CD,5,2)) BCTD_HUYEN,NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                           NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                           NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                           NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                           NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                           FROM 
                                            (  
                                            SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                   BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                   FROM BCTD_DULIEU
                                                   WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                                                         AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                            ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                            WHERE A.BCTD_CAPQLV(+)= B.KHOA_2
                                   ) A, DMHUYEN B, DMTINH C
                                   WHERE A.BCTD_HUYEN=SUBSTR(B.MA(+),3,2) AND SUBSTR(B.MA,1,2)=A.BCTD_TINH
                                         AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN
                            ) WHERE BCTD_CAPQLV='20'
                UNION ALL
                SELECT 'II' TT_HIENTHI,1 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,'' BCTD_TINH,'' BCTD_TENTINH,'' BCTD_HUYEN,'' TENHUYEN,
                       'Tổ chức thực hiện chương trình' BCTD_D1,0 BCTD_COL4,0 BCTD_D22,0 BCTD_D24,0 BCTD_D25,0 BCTD_D26,0 BCTD_D27,0 BCTD_D28,
                       0 BCTD_D29,0 BCTD_D30,0 BCTD_D31,0 BCTD_D32,0 BCTD_D33,0 BCTD_D34,0 BCTD_D35,0 BCTD_D36,0 BCTD_D37,0 BCTD_D38 FROM DUAL
                UNION ALL
                SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY BCTD_CAPQLV)) TT_HIENTHI,KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,
                       BCTD_TINH,BCTD_TENTINH,'' BCTD_HUYEN,'' TENHUYEN,BCTD_TENCQLV BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                       SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                       SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                       FROM
                        (
                            SELECT 3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,
                                   REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,
                                   BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                   FROM
                                   (
                                    SELECT B.KHOA_2 BCTD_CAPQLV,B.GIATRI BCTD_TENCQLV,NVL(BCTD_TINH,SUBSTR(P_POS_CD,3,2)) BCTD_TINH,
                                           NVL(BCTD_HUYEN,SUBSTR(P_POS_CD,5,2)) BCTD_HUYEN,NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                           NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                           NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                           NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                           NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                           FROM 
                                            (  
                                            SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                   BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                   FROM BCTD_DULIEU
                                                   WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                                                         AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                            ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                            WHERE A.BCTD_CAPQLV(+)= B.KHOA_2
                                   ) A, DMHUYEN B, DMTINH C
                                   WHERE A.BCTD_HUYEN=SUBSTR(B.MA(+),3,2) AND SUBSTR(B.MA,1,2)=A.BCTD_TINH
                                         AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN
                            ) WHERE BCTD_CAPQLV IN ('13','11','17','15','14','12','16') GROUP BY KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,BCTD_TENTINH
                UNION ALL
                SELECT '' TT_HIENTHI,2 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,
                       '' BCTD_TINH,'' BCTD_TENTINH,'' BCTD_HUYEN,'Tổng' TENHUYEN,'Tổng' BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                       SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                       SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                       FROM
                        (
                            SELECT 3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,
                                   REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,
                                   BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                   FROM
                                   (
                                    SELECT B.KHOA_2 BCTD_CAPQLV,B.GIATRI BCTD_TENCQLV,NVL(BCTD_TINH,SUBSTR(P_POS_CD,3,2)) BCTD_TINH,
                                           NVL(BCTD_HUYEN,SUBSTR(P_POS_CD,5,2)) BCTD_HUYEN,NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                           NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                           NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                           NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                           NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                           FROM 
                                            (  
                                            SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                   BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                   FROM BCTD_DULIEU
                                                   WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                                                         AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                            ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                            WHERE A.BCTD_CAPQLV(+)= B.KHOA_2
                                   ) A, DMHUYEN B, DMTINH C
                                   WHERE A.BCTD_HUYEN=SUBSTR(B.MA(+),3,2) AND SUBSTR(B.MA,1,2)=A.BCTD_TINH
                                         AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN
                            ) WHERE BCTD_CAPQLV IN ('13','11','17','15','14','12','16')
                UNION ALL
                SELECT '' TT_HIENTHI,1 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,
                       '' BCTD_TINH,'' BCTD_TENTINH,'' BCTD_HUYEN,'TỔNG CỘNG' TENHUYEN,'TỔNG CỘNG' BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                       SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                       SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                       FROM
                        (
                            SELECT 3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,
                                   REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,
                                   BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                   FROM
                                   (
                                    SELECT B.KHOA_2 BCTD_CAPQLV,B.GIATRI BCTD_TENCQLV,NVL(BCTD_TINH,SUBSTR(P_POS_CD,3,2)) BCTD_TINH,
                                           NVL(BCTD_HUYEN,SUBSTR(P_POS_CD,5,2)) BCTD_HUYEN,NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                           NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                           NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                           NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                           NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                           FROM 
                                            (  
                                            SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                   BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                   FROM BCTD_DULIEU
                                                   WHERE BCTD_KHOA = LV_KHOA AND BCTD_MAPGD=P_POS_CD
                                                         AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                            ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                            WHERE A.BCTD_CAPQLV(+)= B.KHOA_2
                                   ) A, DMHUYEN B, DMTINH C
                                   WHERE A.BCTD_HUYEN=SUBSTR(B.MA(+),3,2) AND SUBSTR(B.MA,1,2)=A.BCTD_TINH
                                         AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN
                            ) WHERE BCTD_CAPQLV IN ('13','11','17','15','14','12','16','20');
                            
        ELSIF LN_CAPBC = 2 THEN
            OPEN P_PRINCUR FOR 
                SELECT 'I' TT_HIENTHI,1 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,'' BCTD_TINH,'' BCTD_TENTINH,'' BCTD_HUYEN,'' TENHUYEN,
                       'Tỉnh, thành phố (UBND)' BCTD_D1,0 BCTD_COL4,0 BCTD_D22,0 BCTD_D24,0 BCTD_D25,0 BCTD_D26,0 BCTD_D27,0 BCTD_D28,
                       0 BCTD_D29,0 BCTD_D30,0 BCTD_D31,0 BCTD_D32,0 BCTD_D33,0 BCTD_D34,0 BCTD_D35,0 BCTD_D36,0 BCTD_D37,0 BCTD_D38 FROM DUAL
                UNION ALL
                SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY BCTD_HUYEN)) TT_HIENTHI,KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,
                       BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN,TENHUYEN BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                       SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                       SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                       FROM
                        (
                            SELECT 3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,
                                   REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,
                                   BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                   FROM
                                   (
                                    SELECT B.KHOA_2 BCTD_CAPQLV,B.GIATRI BCTD_TENCQLV,NVL(BCTD_TINH,SUBSTR(P_POS_CD,3,2)) BCTD_TINH,
                                           NVL(BCTD_HUYEN,SUBSTR(P_POS_CD,5,2)) BCTD_HUYEN,NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                           NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                           NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                           NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                           NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                           FROM 
                                            (  
                                            SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                   BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                   FROM BCTD_DULIEU
                                                   WHERE BCTD_KHOA = LV_KHOA AND BCTD_MACN=P_POS_CD
                                                         AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                            ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                            WHERE A.BCTD_CAPQLV(+)= B.KHOA_2
                                   ) A, DMHUYEN B, DMTINH C
                                   WHERE A.BCTD_HUYEN=SUBSTR(B.MA(+),3,2) AND SUBSTR(B.MA,1,2)=A.BCTD_TINH
                                         AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN
                            ) WHERE BCTD_CAPQLV='20' GROUP BY KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,BCTD_TENTINH,BCTD_HUYEN,TENHUYEN
                UNION ALL
                SELECT '' TT_HIENTHI,2 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,
                       '' BCTD_TINH,'' BCTD_TENTINH,'' BCTD_HUYEN,'Tổng' TENHUYEN,'Tổng' BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                       SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                       SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                       FROM
                        (
                            SELECT 3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,
                                   REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,
                                   BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                   FROM
                                   (
                                    SELECT B.KHOA_2 BCTD_CAPQLV,B.GIATRI BCTD_TENCQLV,NVL(BCTD_TINH,SUBSTR(P_POS_CD,3,2)) BCTD_TINH,
                                           NVL(BCTD_HUYEN,SUBSTR(P_POS_CD,5,2)) BCTD_HUYEN,NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                           NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                           NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                           NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                           NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                           FROM 
                                            (  
                                            SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                   BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                   FROM BCTD_DULIEU
                                                   WHERE BCTD_KHOA = LV_KHOA AND BCTD_MACN=P_POS_CD
                                                         AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                            ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                            WHERE A.BCTD_CAPQLV(+)= B.KHOA_2
                                   ) A, DMHUYEN B, DMTINH C
                                   WHERE A.BCTD_HUYEN=SUBSTR(B.MA(+),3,2) AND SUBSTR(B.MA,1,2)=A.BCTD_TINH
                                         AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN
                            ) WHERE BCTD_CAPQLV='20'
                UNION ALL
                SELECT 'II' TT_HIENTHI,1 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,'' BCTD_TINH,'' BCTD_TENTINH,'' BCTD_HUYEN,'' TENHUYEN,
                       'Tổ chức thực hiện chương trình' BCTD_D1,0 BCTD_COL4,0 BCTD_D22,0 BCTD_D24,0 BCTD_D25,0 BCTD_D26,0 BCTD_D27,0 BCTD_D28,
                       0 BCTD_D29,0 BCTD_D30,0 BCTD_D31,0 BCTD_D32,0 BCTD_D33,0 BCTD_D34,0 BCTD_D35,0 BCTD_D36,0 BCTD_D37,0 BCTD_D38 FROM DUAL
                UNION ALL
                SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY BCTD_CAPQLV)) TT_HIENTHI,KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,
                       BCTD_TINH,BCTD_TENTINH,'' BCTD_HUYEN,'' TENHUYEN,BCTD_TENCQLV BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                       SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                       SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                       FROM
                        (
                            SELECT 3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,
                                   REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,
                                   BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                   FROM
                                   (
                                    SELECT B.KHOA_2 BCTD_CAPQLV,B.GIATRI BCTD_TENCQLV,NVL(BCTD_TINH,SUBSTR(P_POS_CD,3,2)) BCTD_TINH,
                                           NVL(BCTD_HUYEN,SUBSTR(P_POS_CD,5,2)) BCTD_HUYEN,NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                           NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                           NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                           NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                           NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                           FROM 
                                            (  
                                            SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                   BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                   FROM BCTD_DULIEU
                                                   WHERE BCTD_KHOA = LV_KHOA AND BCTD_MACN=P_POS_CD
                                                         AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                            ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                            WHERE A.BCTD_CAPQLV(+)= B.KHOA_2
                                   ) A, DMHUYEN B, DMTINH C
                                   WHERE A.BCTD_HUYEN=SUBSTR(B.MA(+),3,2) AND SUBSTR(B.MA,1,2)=A.BCTD_TINH
                                         AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN
                            ) WHERE BCTD_CAPQLV IN ('13','11','17','15','14','12','16') GROUP BY KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,BCTD_TENTINH
                UNION ALL
                SELECT '' TT_HIENTHI,2 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,
                       '' BCTD_TINH,'' BCTD_TENTINH,'' BCTD_HUYEN,'Tổng' TENHUYEN,'Tổng' BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                       SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                       SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                       FROM
                        (
                            SELECT 3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,
                                   REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,
                                   BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                   FROM
                                   (
                                    SELECT B.KHOA_2 BCTD_CAPQLV,B.GIATRI BCTD_TENCQLV,NVL(BCTD_TINH,SUBSTR(P_POS_CD,3,2)) BCTD_TINH,
                                           NVL(BCTD_HUYEN,SUBSTR(P_POS_CD,5,2)) BCTD_HUYEN,NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                           NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                           NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                           NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                           NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                           FROM 
                                            (  
                                            SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                   BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                   FROM BCTD_DULIEU
                                                   WHERE BCTD_KHOA = LV_KHOA AND BCTD_MACN=P_POS_CD
                                                         AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                            ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                            WHERE A.BCTD_CAPQLV(+)= B.KHOA_2
                                   ) A, DMHUYEN B, DMTINH C
                                   WHERE A.BCTD_HUYEN=SUBSTR(B.MA(+),3,2) AND SUBSTR(B.MA,1,2)=A.BCTD_TINH
                                         AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN
                            ) WHERE BCTD_CAPQLV IN ('13','11','17','15','14','12','16')
                UNION ALL
                SELECT '' TT_HIENTHI,1 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,
                       '' BCTD_TINH,'' BCTD_TENTINH,'' BCTD_HUYEN,'TỔNG CỘNG' TENHUYEN,'TỔNG CỘNG' BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                       SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                       SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                       SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                       SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                       FROM
                        (
                            SELECT 3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_HUYEN,
                                   REPLACE(REPLACE(NVL(B.TEN,''),'Thành phố ','TP. '),'Thành Phố ','TP. ') TENHUYEN,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,
                                   BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                   FROM
                                   (
                                    SELECT B.KHOA_2 BCTD_CAPQLV,B.GIATRI BCTD_TENCQLV,NVL(BCTD_TINH,SUBSTR(P_POS_CD,3,2)) BCTD_TINH,
                                           NVL(BCTD_HUYEN,SUBSTR(P_POS_CD,5,2)) BCTD_HUYEN,NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                           NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                           NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                           NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                           NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                           FROM 
                                            (  
                                            SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                   BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                   FROM BCTD_DULIEU
                                                   WHERE BCTD_KHOA = LV_KHOA AND BCTD_MACN=P_POS_CD
                                                         AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                            ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                            WHERE A.BCTD_CAPQLV(+)= B.KHOA_2
                                   ) A, DMHUYEN B, DMTINH C
                                   WHERE A.BCTD_HUYEN=SUBSTR(B.MA(+),3,2) AND SUBSTR(B.MA,1,2)=A.BCTD_TINH
                                         AND A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN
                            ) WHERE BCTD_CAPQLV IN ('13','11','17','15','14','12','16','20');
            
        ELSIF LN_CAPBC = 3 THEN
            OPEN P_PRINCUR FOR
                SELECT TT_HIENTHI,KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,BCTD_TENTINH,'' BCTD_HUYEN,'' TENHUYEN,BCTD_D1,BCTD_COL4,
                       BCTD_D22,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,
                       BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                       FROM
                       (
                        SELECT 'I' TT_HIENTHI,1 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,'' BCTD_TINH,'' BCTD_TENTINH,
                               'Tỉnh, thành phố (UBND)' BCTD_D1,0 BCTD_COL4,0 BCTD_D22,0 BCTD_D24,0 BCTD_D25,0 BCTD_D26,0 BCTD_D27,0 BCTD_D28,
                               0 BCTD_D29,0 BCTD_D30,0 BCTD_D31,0 BCTD_D32,0 BCTD_D33,0 BCTD_D34,0 BCTD_D35,0 BCTD_D36,0 BCTD_D37,0 BCTD_D38 FROM DUAL
                        UNION ALL
                        SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY BCTD_TINH)) TT_HIENTHI,3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,
                               BCTD_TINH,BCTD_TENTINH,BCTD_TENTINH BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                               SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                               SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                               SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                               SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                               FROM
                                (
                                SELECT BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,BCTD_D26,
                                       BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                       FROM
                                       (
                                        SELECT BCTD_CAPQLV,NVL(B.GIATRI,'Khác') BCTD_TENCQLV,BCTD_TINH,
                                               NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                               NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                               NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                               NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                               NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                               FROM 
                                                (  
                                                SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                       BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                       FROM BCTD_DULIEU WHERE BCTD_KHOA = LV_KHOA
                                                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                                ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                                WHERE A.BCTD_CAPQLV= B.KHOA_2(+)
                                       ) A, DMTINH C
                                       WHERE A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH
                                ) WHERE BCTD_CAPQLV='20' GROUP BY BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,BCTD_TENTINH
                        UNION ALL
                        SELECT '' TT_HIENTHI,2 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,
                               '' BCTD_TINH,'' BCTD_TENTINH,'Tổng' BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                               SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                               SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                               SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                               SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                               FROM
                                (
                                SELECT BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,BCTD_D26,
                                       BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                       FROM
                                       (
                                        SELECT BCTD_CAPQLV,NVL(B.GIATRI,'Khác') BCTD_TENCQLV,BCTD_TINH,
                                               NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                               NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                               NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                               NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                               NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                               FROM 
                                                (  
                                                SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                       BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                       FROM BCTD_DULIEU WHERE BCTD_KHOA = LV_KHOA
                                                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                                ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                                WHERE A.BCTD_CAPQLV= B.KHOA_2(+)
                                       ) A, DMTINH C
                                       WHERE A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH
                                ) WHERE BCTD_CAPQLV='20'
                        UNION ALL
                        SELECT 'II' TT_HIENTHI,1 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,'' BCTD_TINH,'' BCTD_TENTINH,
                               'Tổ chức thực hiện chương trình' BCTD_D1,0 BCTD_COL4,0 BCTD_D22,0 BCTD_D24,0 BCTD_D25,0 BCTD_D26,0 BCTD_D27,0 BCTD_D28,
                               0 BCTD_D29,0 BCTD_D30,0 BCTD_D31,0 BCTD_D32,0 BCTD_D33,0 BCTD_D34,0 BCTD_D35,0 BCTD_D36,0 BCTD_D37,0 BCTD_D38 FROM DUAL
                        UNION ALL
                        SELECT TO_CHAR(ROW_NUMBER() OVER (ORDER BY BCTD_CAPQLV)) TT_HIENTHI,3 KIEUIN,BCTD_CAPQLV,BCTD_TENCQLV,
                               '' BCTD_TINH,'' BCTD_TENTINH,BCTD_TENCQLV BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                               SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                               SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                               SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                               SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                               FROM
                                (
                                SELECT BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,BCTD_D26,
                                       BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                       FROM
                                       (
                                        SELECT BCTD_CAPQLV,NVL(B.GIATRI,'Khác') BCTD_TENCQLV,BCTD_TINH,
                                               NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                               NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                               NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                               NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                               NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                               FROM 
                                                (  
                                                SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                       BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                       FROM BCTD_DULIEU WHERE BCTD_KHOA = LV_KHOA
                                                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                                ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                                WHERE A.BCTD_CAPQLV= B.KHOA_2(+)
                                       ) A, DMTINH C
                                       WHERE A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH
                                ) WHERE BCTD_CAPQLV IN ('13','11','17','15','14','12','16') GROUP BY BCTD_CAPQLV,BCTD_TENCQLV
                        UNION ALL
                        SELECT '' TT_HIENTHI,2 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,
                               '' BCTD_TINH,'' BCTD_TENTINH,'Tổng' BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                               SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                               SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                               SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                               SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                               FROM
                                (
                                SELECT BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,BCTD_D26,
                                       BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                       FROM
                                       (
                                        SELECT BCTD_CAPQLV,NVL(B.GIATRI,'Khác') BCTD_TENCQLV,BCTD_TINH,
                                               NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                               NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                               NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                               NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                               NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                               FROM 
                                                (  
                                                SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                       BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                       FROM BCTD_DULIEU WHERE BCTD_KHOA = LV_KHOA
                                                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                                ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                                WHERE A.BCTD_CAPQLV= B.KHOA_2(+)
                                       ) A, DMTINH C
                                       WHERE A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH
                                ) WHERE BCTD_CAPQLV IN ('13','11','17','15','14','12','16')
                        UNION ALL
                        SELECT '' TT_HIENTHI,1 KIEUIN,'' BCTD_CAPQLV,'' BCTD_TENCQLV,
                               '' BCTD_TINH,'' BCTD_TENTINH,'TỔNG CỘNG' BCTD_D1,SUM(BCTD_COL4) BCTD_COL4,
                               SUM(BCTD_D22) BCTD_D22,SUM(BCTD_D24) BCTD_D24,SUM(BCTD_D25) BCTD_D25,SUM(BCTD_D26) BCTD_D26,
                               SUM(BCTD_D27) BCTD_D27,SUM(BCTD_D28) BCTD_D28,SUM(BCTD_D29) BCTD_D29,SUM(BCTD_D30) BCTD_D30,
                               SUM(BCTD_D31) BCTD_D31,SUM(BCTD_D32) BCTD_D32,SUM(BCTD_D33) BCTD_D33,SUM(BCTD_D34) BCTD_D34,
                               SUM(BCTD_D35) BCTD_D35,SUM(BCTD_D36) BCTD_D36,SUM(BCTD_D37) BCTD_D37,SUM(BCTD_D38) BCTD_D38
                               FROM
                                (
                                SELECT BCTD_CAPQLV,BCTD_TENCQLV,BCTD_TINH,NVL(C.TEN,'') BCTD_TENTINH,BCTD_COL4,BCTD_D22,BCTD_D24,BCTD_D25,BCTD_D26,
                                       BCTD_D27,BCTD_D28,BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                       FROM
                                       (
                                        SELECT BCTD_CAPQLV,NVL(B.GIATRI,'Khác') BCTD_TENCQLV,BCTD_TINH,
                                               NVL(BCTD_COL4,0) BCTD_COL4,NVL(BCTD_D22,0) BCTD_D22,
                                               NVL(BCTD_D24,0) BCTD_D24,NVL(BCTD_D25,0) BCTD_D25,NVL(BCTD_D26,0) BCTD_D26,NVL(BCTD_D27,0) BCTD_D27,
                                               NVL(BCTD_D28,0) BCTD_D28,NVL(BCTD_D29,0) BCTD_D29,NVL(BCTD_D30,0) BCTD_D30,NVL(BCTD_D31,0) BCTD_D31,
                                               NVL(BCTD_D32,0) BCTD_D32,NVL(BCTD_D33,0) BCTD_D33,NVL(BCTD_D34,0) BCTD_D34,NVL(BCTD_D35,0) BCTD_D35,
                                               NVL(BCTD_D36,0) BCTD_D36,NVL(BCTD_D37,0) BCTD_D37,NVL(BCTD_D38,0) BCTD_D38
                                               FROM 
                                                (  
                                                SELECT BCTD_CAPQLV,BCTD_TINH,BCTD_HUYEN,BCTD_D22,BCTD_D21-BCTD_D23 BCTD_COL4,BCTD_D24,BCTD_D25,BCTD_D26,BCTD_D27,BCTD_D28,
                                                       BCTD_D29,BCTD_D30,BCTD_D31,BCTD_D32,BCTD_D33,BCTD_D34,BCTD_D35,BCTD_D36,BCTD_D37,BCTD_D38
                                                       FROM BCTD_DULIEU WHERE BCTD_KHOA = LV_KHOA
                                                             AND BCTD_NGAYBC BETWEEN TO_DATE('01-01-'||TO_CHAR(TO_DATE(P_REPORT_DATE,'DD-MON-YYYY'),'YYYY'),'DD-MM-YYYY') AND TO_DATE(P_REPORT_DATE)
                                                ) A, (SELECT * FROM DMKHAC WHERE KHOA_1='19') B 
                                                WHERE A.BCTD_CAPQLV= B.KHOA_2(+)
                                       ) A, DMTINH C
                                       WHERE A.BCTD_TINH=C.MA(+) AND C.MA <>'00' ORDER BY BCTD_CAPQLV,BCTD_TINH
                                ) WHERE BCTD_CAPQLV IN ('13','11','17','15','14','12','16','20')
                       );
        END IF;
    END;

    
     --- Thủ tục in báo cáo theo Quyet dinh 308/QĐ-NHCS. Mẫu TT45_06
    --- Người tạo: NGUYETLM
    --- Ngày tạo: 19/04/2016
PROCEDURE INSL_BCTD_TT45_06 (
P_POS_CD IN VARCHAR2,
P_POS_FLAG IN VARCHAR2,
P_REPORT_DATE IN VARCHAR2,
P_PRINCUR OUT SYS_REFCURSOR)
    AS
        LV_KHOA         VARCHAR2(32);
        LV_FROM_DT  DATE;
        LV_TO_DT DATE;
        LV_DONVI NUMBER;
    BEGIN
        LV_KHOA := 'BCTD_TT45_06';
        LV_FROM_DT := VBSP_PUBLIC_FUNCTION.F_GET_FIRST_DAY_OF_YEAR(P_REPORT_DATE);
        LV_TO_DT :=  TO_DATE(P_REPORT_DATE);
        LV_DONVI := 1000000;
        OPEN P_PRINCUR FOR 
                 /* Formatted on 4/19/2016 3:18:26 PM (QP5 v5.252.13127.32847) */
                SELECT INITCAP(LOWER(BCTD_D3))  TEN, 
                                INITCAP(LOWER(BCTD_D4)) CUTRU, 
                                CASE WHEN BCTD_D5='01' THEN 'X' ELSE '' END GT_NAM, 
                                CASE WHEN BCTD_D5='02' THEN 'X' ELSE ''  END GT_NU, 
                                CASE WHEN BCTD_DTTH='02' THEN 'X' ELSE '' END DT_HCN,
                                CASE WHEN BCTD_DTTH='09' THEN 'X' ELSE '' END DT_TNCOCONGCM,
                                BCTD_D6 QUOCGIA,
                                BCTD_D7 THOIHAN_HD,
                                BCTD_D21/LV_DONVI SOTIEN,
                                BCTD_D8 THOIHAN_VAY,
                                BCTD_D22 LSUAT
                 FROM BCTD_DULIEU 
                 WHERE BCTD_KHOA= LV_KHOA AND BCTD_MAPGD = P_POS_CD AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT;
                                      
    END;
    
    
     --- Thủ tục in báo cáo theo Quyet dinh 308/QĐ-NHCS. Mẫu TT45_08, Mẫu TT45_10
    --- Người tạo: NGUYETLM
    --- Ngày tạo: 20/04/2016
    
PROCEDURE INSL_BCTD_TT45_08_10 (
P_POS_CD IN VARCHAR2,
P_POS_FLAG IN VARCHAR2,
P_REPORT_DATE IN VARCHAR2,
P_PRINCUR OUT SYS_REFCURSOR)
    AS
        LV_KHOA         VARCHAR2(32);
        LV_FROM_DT  DATE;
        LV_TO_DT DATE;
        LV_DONVI NUMBER;
        LV_CAPBC NUMBER;
        LV_POS_CD VARCHAR2 (8);
        LV_KH_GIAO NUMBER;       
    BEGIN
        LV_KHOA := 'BCTD_TT45_06';
        LV_FROM_DT := VBSP_PUBLIC_FUNCTION.F_GET_FIRST_DAY_OF_YEAR(P_REPORT_DATE);
        LV_TO_DT :=  TO_DATE(P_REPORT_DATE);
        LV_DONVI := 1000000;
        LV_CAPBC := VBSP_PUBLIC_FUNCTION.F_GET_REPORT_GRADE(P_POS_CD,P_POS_FLAG);
        
        CASE 
        WHEN LV_CAPBC = 1 THEN 
                        OPEN P_PRINCUR FOR 
                                 /* Formatted on 4/19/2016 3:18:26 PM (QP5 v5.252.13127.32847) */
                                SELECT A.BCTD_MACN, A.BCTD_MAPGD,  INITCAP(LOWER(B.TEN))  TEN, SUM(A.DSCHOVAY) DSCHOVAY, 
                                               ( (select    vbsp_rpt_khnv.sp_get_dc_value('zzz4',LV_TO_DT,P_POS_CD,'N')+
                                                                vbsp_rpt_khnv.sp_get_dc_value('zzz5',LV_TO_DT,P_POS_CD,'N')+
                                                                vbsp_rpt_khnv.sp_get_dc_value('zzz6',LV_TO_DT,P_POS_CD,'N')+
                                                                vbsp_rpt_khnv.sp_get_dc_value('zzz7',LV_TO_DT,P_POS_CD,'N')+
                                                                vbsp_rpt_khnv.sp_get_dc_value('zzz8',LV_TO_DT,P_POS_CD,'N')+
                                                                vbsp_rpt_khnv.sp_get_dc_value('zzz9',LV_TO_DT,P_POS_CD,'N')+
                                                                vbsp_rpt_khnv.sp_get_dc_value('zzz10',LV_TO_DT,P_POS_CD,'N')+
                                                                vbsp_rpt_khnv.sp_get_dc_value('zzz11',LV_TO_DT,P_POS_CD,'N')+
                                                                vbsp_rpt_khnv.sp_get_dc_value('zzz12',LV_TO_DT,P_POS_CD,'N')
                                                                giatri  from dual)  -  SUM(A.DSCHOVAY) ) SOVON_TD, 
                                                 SUM(A.TONGSOLD) TONGSOLD, SUM(A.LD_NU) LD_NU, SUM(A.LD_HCN) LD_HCN, SUM(A.LD_TNCOCONGCM) LD_TNCOCONGCM
                                FROM(
                                SELECT BCTD_MACN,                  
                                                BCTD_MAPGD, 
                                                SUM(BCTD_D21) DSCHOVAY,
                                                COUNT(DISTINCT BCTD_D1) TONGSOLD,
                                                0 LD_NU,
                                                0 LD_HCN,
                                                0 LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA AND BCTD_MAPGD = P_POS_CD AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT
                                 GROUP BY BCTD_MACN, BCTD_MAPGD
                                 UNION ALL
                                 SELECT BCTD_MACN,                  
                                                BCTD_MAPGD, 
                                                0 DSCHOVAY,
                                                0 TONGSOLD,
                                                COUNT (DISTINCT BCTD_D1)  LD_NU,
                                                0 LD_HCN,
                                                0 LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA AND BCTD_MAPGD = P_POS_CD AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT AND BCTD_D5 = '02'
                                 GROUP BY BCTD_MACN, BCTD_MAPGD
                                 UNION ALL
                                 SELECT BCTD_MACN,                  
                                                BCTD_MAPGD, 
                                                0 DSCHOVAY,
                                                0 TONGSOLD,
                                                0 LD_NU,
                                                COUNT (DISTINCT BCTD_D1)   LD_HCN,
                                                0 LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA AND BCTD_MAPGD = P_POS_CD AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT AND BCTD_DTTH = '02'
                                 GROUP BY BCTD_MACN, BCTD_MAPGD   
                                 UNION ALL
                                 SELECT BCTD_MACN,                  
                                                BCTD_MAPGD, 
                                                0 DSCHOVAY,
                                                0 TONGSOLD,
                                                0 LD_NU,
                                                0   LD_HCN,
                                                COUNT (DISTINCT BCTD_D1) LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA AND BCTD_MAPGD = P_POS_CD AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT AND BCTD_DTTH = '09'
                                 GROUP BY BCTD_MACN, BCTD_MAPGD) A, DMHUYEN B
                                 WHERE   SUBSTR(A.BCTD_MAPGD,3,4) = B.MA                
                                 GROUP BY BCTD_MACN, BCTD_MAPGD, TEN
                                 ORDER BY 1,2;
                 WHEN LV_CAPBC = 2 THEN 
                  --OPEN P_PRINCUR FOR 
                                 /* Formatted on 4/19/2016 3:18:26 PM (QP5 v5.252.13127.32847) */
                                DELETE FROM REP001TB_BCTD;
                                INSERT INTO REP001TB_BCTD (VDATA_1, VDATA_2, VDATA_3, NDATA_1, NDATA_2, NDATA_3, NDATA_4, NDATA_5, NDATA_6 ) 
                                SELECT A.BCTD_MACN, A.BCTD_MAPGD,  INITCAP(LOWER(B.TEN))  TEN, SUM(A.DSCHOVAY) DSCHOVAY, 0 SOVON_TD, SUM(A.TONGSOLD) TONGSOLD, SUM(A.LD_NU) LD_NU, SUM(A.LD_HCN) LD_HCN, SUM(A.LD_TNCOCONGCM) LD_TNCOCONGCM
                                FROM(
                                SELECT BCTD_MACN,                  
                                                BCTD_MAPGD, 
                                                SUM(BCTD_D21) DSCHOVAY,
                                                COUNT(DISTINCT BCTD_D1) TONGSOLD,
                                                0 LD_NU,
                                                0 LD_HCN,
                                                0 LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA AND BCTD_MACN = P_POS_CD AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT
                                 GROUP BY BCTD_MACN, BCTD_MAPGD
                                 UNION ALL
                                 SELECT BCTD_MACN,                  
                                                BCTD_MAPGD, 
                                                0 DSCHOVAY,
                                                0 TONGSOLD,
                                                COUNT (DISTINCT BCTD_D1)  LD_NU,
                                                0 LD_HCN,
                                                0 LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA AND BCTD_MACN = P_POS_CD AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT AND BCTD_D5 = '02'
                                 GROUP BY BCTD_MACN, BCTD_MAPGD
                                 UNION ALL
                                 SELECT BCTD_MACN,                  
                                                BCTD_MAPGD, 
                                                0 DSCHOVAY,
                                                0 TONGSOLD,
                                                0 LD_NU,
                                                COUNT (DISTINCT BCTD_D1)   LD_HCN,
                                                0 LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA AND BCTD_MACN = P_POS_CD AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT AND BCTD_DTTH = '02'
                                 GROUP BY BCTD_MACN, BCTD_MAPGD   
                                 UNION ALL
                                 SELECT BCTD_MACN,                  
                                                BCTD_MAPGD, 
                                                0 DSCHOVAY,
                                                0 TONGSOLD,
                                                0 LD_NU,
                                                0   LD_HCN,
                                                COUNT (DISTINCT BCTD_D1) LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA AND BCTD_MACN = P_POS_CD AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT AND BCTD_DTTH = '09'
                                 GROUP BY BCTD_MACN, BCTD_MAPGD) A, DMHUYEN B
                                 WHERE   SUBSTR(A.BCTD_MAPGD,3,4) = B.MA                
                                 GROUP BY BCTD_MACN, BCTD_MAPGD, TEN
                                 ORDER BY 1,2;
                                 
                                 BEGIN 
                                  FOR REC IN (SELECT DISTINCT VDATA_2  FROM REP001TB_BCTD )   
                                        LOOP 
                                            LV_POS_CD  := REC.VDATA_2;
                                            LV_KH_GIAO :=   vbsp_rpt_khnv.sp_get_dc_value('zzz4',LV_TO_DT,LV_POS_CD,'N')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz5',LV_TO_DT,LV_POS_CD,'N')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz6',LV_TO_DT,LV_POS_CD,'N')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz7',LV_TO_DT,LV_POS_CD,'N')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz8',LV_TO_DT,LV_POS_CD,'N')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz9',LV_TO_DT,LV_POS_CD,'N')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz10',LV_TO_DT,LV_POS_CD,'N')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz11',LV_TO_DT,LV_POS_CD,'N')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz12',LV_TO_DT,LV_POS_CD,'N') ;
                                           UPDATE REP001TB_BCTD SET  NDATA_2 =  LV_KH_GIAO  -   NDATA_1  WHERE  VDATA_2  =   LV_POS_CD;
                                    END LOOP;             
                               END;   
                              OPEN P_PRINCUR FOR 
                              SELECT VDATA_1  BCTD_MACN,
                                              VDATA_2  BCTD_MAPGD,
                                              VDATA_3 TEN,
                                              NDATA_1 DSCHOVAY,
                                              NDATA_2 SOVON_TD,
                                              NDATA_3 TONGSOLD,
                                              NDATA_4 LD_NU,
                                              NDATA_5 LD_HCN,
                                              NDATA_6 LD_TNCOCONGCM
                              FROM REP001TB_BCTD 
                              ORDER BY 1,2;
                   ELSE
                                 DELETE FROM REP001TB_BCTD;
                                INSERT INTO REP001TB_BCTD (VDATA_1, VDATA_2, NDATA_1, NDATA_2, NDATA_3, NDATA_4, NDATA_5, NDATA_6 ) 
                                 /* Formatted on 4/19/2016 3:18:26 PM (QP5 v5.252.13127.32847) */
                                SELECT A.BCTD_MACN,  INITCAP(LOWER(B.TEN))  TEN, SUM(A.DSCHOVAY) DSCHOVAY, 0 SOVON_TD, SUM(A.TONGSOLD) TONGSOLD, SUM(A.LD_NU) LD_NU, SUM(A.LD_HCN) LD_HCN, SUM(A.LD_TNCOCONGCM) LD_TNCOCONGCM
                                FROM(
                                SELECT BCTD_MACN,                  
                                                SUM(BCTD_D21) DSCHOVAY,
                                                COUNT(DISTINCT BCTD_D1) TONGSOLD,
                                                0 LD_NU,
                                                0 LD_HCN,
                                                0 LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA  AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT
                                 GROUP BY BCTD_MACN
                                 UNION ALL
                                 SELECT BCTD_MACN,                  
                                                0 DSCHOVAY,
                                                0 TONGSOLD,
                                                COUNT (DISTINCT BCTD_D1)  LD_NU,
                                                0 LD_HCN,
                                                0 LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT AND BCTD_D5 = '02'
                                 GROUP BY BCTD_MACN
                                 UNION ALL
                                 SELECT BCTD_MACN,                  
                                                0 DSCHOVAY,
                                                0 TONGSOLD,
                                                0 LD_NU,
                                                COUNT (DISTINCT BCTD_D1)   LD_HCN,
                                                0 LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT AND BCTD_DTTH = '02'
                                 GROUP BY BCTD_MACN
                                 UNION ALL
                                 SELECT BCTD_MACN,                  
                                                0 DSCHOVAY,
                                                0 TONGSOLD,
                                                0 LD_NU,
                                                0   LD_HCN,
                                                COUNT (DISTINCT BCTD_D1) LD_TNCOCONGCM
                                  FROM BCTD_DULIEU
                                 WHERE BCTD_KHOA= LV_KHOA  AND BCTD_NGAYBC BETWEEN LV_FROM_DT AND LV_TO_DT AND BCTD_DTTH = '09'
                                 GROUP BY BCTD_MACN) A, DMTINH B
                                 WHERE   SUBSTR(A.BCTD_MACN,3,2) = B.MA                
                                 GROUP BY BCTD_MACN, TEN
                                 ORDER BY 1,2;
                                 
                                  BEGIN 
                                  FOR REC IN (SELECT DISTINCT VDATA_1  FROM REP001TB_BCTD )   
                                        LOOP 
                                            LV_POS_CD  := REC.VDATA_1;
                                            LV_KH_GIAO :=   vbsp_rpt_khnv.sp_get_dc_value('zzz4',LV_TO_DT,LV_POS_CD,'Y')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz5',LV_TO_DT,LV_POS_CD,'Y')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz6',LV_TO_DT,LV_POS_CD,'Y')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz7',LV_TO_DT,LV_POS_CD,'Y')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz8',LV_TO_DT,LV_POS_CD,'Y')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz9',LV_TO_DT,LV_POS_CD,'Y')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz10',LV_TO_DT,LV_POS_CD,'Y')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz11',LV_TO_DT,LV_POS_CD,'Y')+
                                                                            vbsp_rpt_khnv.sp_get_dc_value('zzz12',LV_TO_DT,LV_POS_CD,'Y') ;
                                           UPDATE REP001TB_BCTD SET  NDATA_2 =  LV_KH_GIAO  -   NDATA_1  WHERE  VDATA_1  =   LV_POS_CD;
                                    END LOOP;             
                               END;   
                              OPEN P_PRINCUR FOR 
                              SELECT VDATA_1  BCTD_MACN,
                                              VDATA_2 TEN,
                                              NDATA_1 DSCHOVAY,
                                              NDATA_2 SOVON_TD,
                                              NDATA_3 TONGSOLD,
                                              NDATA_4 LD_NU,
                                              NDATA_5 LD_HCN,
                                              NDATA_6 LD_TNCOCONGCM
                              FROM REP001TB_BCTD 
                              ORDER BY 1,2;
                END CASE;
                
    END;

--------------------------------------------------------------------------------
    --- Thủ tục in báo cáo theo Quyet dinh 308/QĐ-NHCS. Mẫu 10/QĐ_176_QĐ_33
    --- Người tạo: LUANND
    --- Ngày tạo: 21/04/2016    
PROCEDURE INSL_BCTD_QD167_QD33 (
P_POS_CD IN VARCHAR2,
P_POS_FLAG IN VARCHAR2,
P_REPORT_DATE IN VARCHAR2,
P_PRINCUR OUT SYS_REFCURSOR)
    AS
        LV_DONVI NUMBER;
        LV_CAPBC NUMBER;
   
    BEGIN
        LV_DONVI := 1000000;
        LV_CAPBC := VBSP_PUBLIC_FUNCTION.F_GET_REPORT_GRADE(P_POS_CD,P_POS_FLAG);
        
        IF LV_CAPBC IN  ('1','2') THEN 
            IF LV_CAPBC = '1' THEN 
                        OPEN P_PRINCUR FOR 
                          SELECT B.MA MA,
                                 B.TEN TEN,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D4 END), 0)
                                 / LV_DONVI
                                    LKTHUNO_NAM_07,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D7 END), 0)
                                 / LV_DONVI
                                    TONGDNO_07,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D9 END), 0)
                                 / LV_DONVI
                                    DNOQHAN_07,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D10 END), 0)
                                 / LV_DONVI
                                    DNOKHOANH_07,
                                 NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D14 END), 0)
                                    SO_KH_DUNO_07,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D2 END), 0)
                                 / LV_DONVI
                                    DSCV_23,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D15 END), 0)
                                 / LV_DONVI
                                    SOKHVV_NAM_23,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D14 END), 0)
                                 / LV_DONVI
                                    LKTHUNO_NAM_23,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D7 END), 0)
                                 / LV_DONVI
                                    TONGDNO_23,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D9 END), 0)
                                 / LV_DONVI
                                    DNOQHAN_23,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D10 END), 0)
                                 / LV_DONVI
                                    DNOKHOANH_23,
                                 NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D14 END), 0)
                                    SO_KH_DUNO_23,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D7 END),
                                        0)
                                 / LV_DONVI
                                    TOTAL_TONGDNO,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D9 END),
                                        0)
                                 / LV_DONVI
                                    TOTAL_QHAN,
                                   NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D10 END),
                                        0)
                                 / LV_DONVI
                                    TOTAL_KHOANH,
                                 NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D14 END),
                                      0)
                                    TOTAL_SO_KH,
                                    LV_CAPBC CAPBC                                
                            FROM BCTD_DTTH A,
                                 (SELECT *
                                    FROM DMXA
                                   WHERE PGD_QL = P_POS_CD) B
                           WHERE     A.DTTH_NGAYBC = P_REPORT_DATE
                                 AND A.DTTH_MAPGD = P_POS_CD
                                 AND A.DTTH_XA = SUBSTR (B.MA, 5, 2)
                        GROUP BY B.MA, B.TEN
                        ORDER BY B.MA, B.TEN;
              ELSE         
                   OPEN P_PRINCUR FOR 
                      SELECT DTTH_MAPGD MA,
                             B.PO_TEN TEN,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D4 END), 0)
                             / LV_DONVI
                                LKTHUNO_NAM_07,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D7 END), 0)
                             / LV_DONVI
                                TONGDNO_07,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D9 END), 0)
                             / LV_DONVI
                                DNOQHAN_07,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D10 END), 0)
                             / LV_DONVI
                                DNOKHOANH_07,
                             NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D14 END), 0)
                                SO_KH_DUNO_07,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D2 END), 0)
                             / LV_DONVI
                                DSCV_23,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D15 END), 0)
                             / LV_DONVI
                                SOKHVV_NAM_23,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D14 END), 0)
                             / LV_DONVI
                                LKTHUNO_NAM_23,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D7 END), 0)
                             / LV_DONVI
                                TONGDNO_23,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D9 END), 0)
                             / LV_DONVI
                                DNOQHAN_23,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D10 END), 0)
                             / LV_DONVI
                                DNOKHOANH_23,
                             NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D14 END), 0)
                                SO_KH_DUNO_23,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D7 END),
                                    0)
                             / LV_DONVI
                                TOTAL_TONGDNO,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D9 END),
                                    0)
                             / LV_DONVI
                                TOTAL_QHAN,
                               NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D10 END),
                                    0)
                             / LV_DONVI
                                TOTAL_KHOANH,
                             NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D14 END),
                                  0)
                                TOTAL_SO_KH,
                               LV_CAPBC CAPBC   
                        FROM BCTD_DTTH A,
                             (SELECT *
                                FROM DMPOS
                               WHERE PO_MACN = P_POS_CD) B
                       WHERE     A.DTTH_NGAYBC = P_REPORT_DATE
                             AND A.DTTH_MACN = P_POS_CD
                             AND A.DTTH_MAPGD = B.PO_MA
                    GROUP BY DTTH_MAPGD, B.PO_TEN
                    ORDER BY DTTH_MAPGD;
                 END IF;
        ELSE 
                OPEN P_PRINCUR FOR                     
                SELECT DTTH_MACN MA,
                     B.TEN TEN,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D4 END), 0)
                     / LV_DONVI
                        LKTHUNO_NAM_07,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D7 END), 0)
                     / LV_DONVI
                        TONGDNO_07,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D9 END), 0)
                     / LV_DONVI
                        DNOQHAN_07,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D10 END), 0)
                     / LV_DONVI
                        DNOKHOANH_07,
                     NVL (SUM (CASE WHEN DTTH_CHTRINH = '07' THEN DTTH_D14 END), 0)
                        SO_KH_DUNO_07,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D2 END), 0)
                     / LV_DONVI
                        DSCV_23,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D15 END), 0)
                     / LV_DONVI
                        SOKHVV_NAM_23,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D14 END), 0)
                     / LV_DONVI
                        LKTHUNO_NAM_23,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D7 END), 0)
                     / LV_DONVI
                        TONGDNO_23,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D9 END), 0)
                     / LV_DONVI
                        DNOQHAN_23,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D10 END), 0)
                     / LV_DONVI
                        DNOKHOANH_23,
                     NVL (SUM (CASE WHEN DTTH_CHTRINH = '23' THEN DTTH_D14 END), 0)
                        SO_KH_DUNO_23,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D7 END),
                            0)
                     / LV_DONVI
                        TOTAL_TONGDNO,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D9 END),
                            0)
                     / LV_DONVI
                        TOTAL_QHAN,
                       NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D10 END),
                            0)
                     / LV_DONVI
                        TOTAL_KHOANH,
                     NVL (SUM (CASE WHEN DTTH_CHTRINH IN ('07', '23') THEN DTTH_D14 END),
                          0)
                        TOTAL_SO_KH,
                        LV_CAPBC CAPBC   
                FROM BCTD_DTTH A, DMTINH B
               WHERE A.DTTH_NGAYBC = P_REPORT_DATE AND SUBSTR (A.DTTH_MACN, 3, 2) = B.MA
            GROUP BY DTTH_MACN, B.TEN
            ORDER BY DTTH_MACN;
        END IF;                
 END;
     --- Thủ tục in báo cáo theo Quyet dinh 308/QĐ-NHCS. Mẫu BCTD_0703
    --- Người tạo: HAILT
    --- Ngày tạo: 22/04/2016
 PROCEDURE INSL_BCTD_0703 ( p_pos_cd in varchar2,p_pos_flag in varchar2,p_report_date IN varchar2,  p_princur OUT sys_refcursor)
        AS        
    begin
        OPEN p_princur FOR
        SELECT CHITIEU,SUB_CHITIEU,
        NVL(sum(CTDT_D1)/1000000,0) DS_NODH,
        NVL(sum(CTDT_D2)/1000000,0) TNODH_THANG,
        NVL(sum(CTDT_D3),0) SOHSSV_GN,
        NVL(sum(CTDT_D4),0) SOHSSV_GNLD,
        NVL(sum(CTDT_D5),0) SOHSSV_TT,
        NVL(sum(CTDT_D6),0) SOHSSV_DNO
        FROM (
        ---lay theo doi tuong thu huong KHOA_1=16
        SELECT
        KHOA_1 CHITIEU, 
        GIATRI SUB_CHITIEU, 
        CTDT_D1 ,
        CTDT_D2 ,
        CTDT_D3 ,
        CTDT_D4 ,
        CTDT_D5 ,
        CTDT_D6 
        FROM DMKHAC A, BCTD_CTDT, DMPOS Y,(SELECT PO_POSFLG, PO_MACN, PO_MA FROM DMPOS WHERE PO_MA = p_pos_cd) Z
        WHERE  ctdt_code like 'HQ00%' AND  SUBSTR(CTDT_CODE,4,2) = A.KHOA_2 AND
        A.khoa_1 = '16' AND A.trangthai = 'O' AND A.khoa_2 IN ('01','02','03','06','07','08','19')
        AND CTDT_MAPGD = Y.PO_MA
        AND DECODE(Z.PO_POSFLG,'H',1,'S',Y.PO_MA , Y.PO_MACN) = DECODE(Z.PO_POSFLG,'H',1,Z.PO_MA)
        AND DECODE('Y',p_pos_flag,1,Y.PO_MA) = DECODE('Y',p_pos_flag,1,p_pos_cd)
        AND  CTDT_NGAYBC BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date))
        UNION ALL
        ----lay theo phuong thuc cho vay KHOA_1=15
        SELECT
        KHOA_1 CHITIEU, 
        GIATRI SUB_CHITIEU, 
        CTDT_D1 ,
        CTDT_D2 ,
        CTDT_D3 ,
        CTDT_D4 ,
        CTDT_D5 ,
        CTDT_D6 
        FROM DMKHAC A, BCTD_CTDT, DMPOS Y,(SELECT PO_POSFLG, PO_MACN, PO_MA FROM DMPOS WHERE PO_MA = p_pos_cd) Z
        WHERE  ctdt_code like 'HQ01%' AND  SUBSTR(CTDT_CODE,5,1) = A.KHOA_2 AND
        A.khoa_1 = '15' AND A.trangthai = 'O'
        AND CTDT_MAPGD = Y.PO_MA
        AND DECODE(Z.PO_POSFLG,'H',1,'S',Y.PO_MA , Y.PO_MACN) = DECODE(Z.PO_POSFLG,'H',1,Z.PO_MA)
        AND DECODE('Y',p_pos_flag,1,Y.PO_MA) = DECODE('Y',p_pos_flag,1,p_pos_cd)
        AND  CTDT_NGAYBC BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date)))A
        group by CHITIEU,SUB_CHITIEU
        ORDER BY CHITIEU desc;
    end;
    --- Thủ tục in báo cáo theo Quyet dinh 308/QĐ-NHCS. Mẫu BCTD_0704
    --- Người tạo: HAILT
    --- Ngày tạo: 22/04/2016
    PROCEDURE INSL_BCTD_0704 (p_pos_cd in varchar2,p_pos_flag in varchar2,p_report_date IN varchar2,  p_princur OUT sys_refcursor)
        AS     
        I_UNTIL    NUMBER := 1000000;   
    begin
        OPEN p_princur FOR
        SELECT GQVL_NGUONVON_BS,GQVL_CAPQLV,TEN_CAPQL,
        ROUND(D1/I_UNTIL,2) D1,ROUND(D2/I_UNTIL,2) D2,ROUND(D3/I_UNTIL,2) D3,
        ROUND(D4/I_UNTIL,2) D4,ROUND(D5/I_UNTIL,2) D5,ROUND(D6/I_UNTIL,2) D6,
        ROUND(D7/I_UNTIL,2) D7,ROUND(D8/I_UNTIL,2) D8,ROUND(D9/I_UNTIL,2) D9,
        ROUND(D10/I_UNTIL,2) D10,ROUND(D11/I_UNTIL,2)D11,ROUND(D12/I_UNTIL,2)D12,
        ROUND(D13/I_UNTIL,2) D13,D14,D15,D19
        FROM 
        ( 
        --- GQVL_NGUONVON_BS='01' quy quoc gia ve viec lam trung uong
        SELECT '01'GQVL_NGUONVON_BS, ''GQVL_CAPQLV,'' TEN_CAPQL, 0 D1, 0 D2, 0 D3, 0 D4, 0 D5, 0 D6, 0 D7, 0 D8, 0 D9, 0 D10, 0 D11, 0 D12, 0 D13, 0 D14, 0 D15, 0 D19 FROM DUAL
        UNION ALL
        select a.* from (SELECT GQVL_NGUONVON_BS,
        GQVL_CAPQLV,GIATRI AS TEN_CAPQL,
        NVL(SUM (GQVL_D1),0) D1,
        NVL(SUM (GQVL_D2),0) D2,
        NVL(SUM (GQVL_D3),0) D3,
        NVL(SUM (GQVL_D4),0) D4,
        NVL(SUM (GQVL_D5),0) D5,
        NVL(SUM (GQVL_D6),0) D6,
        NVL(SUM (GQVL_D7),0) D7,
        NVL(SUM (GQVL_D8),0) D8,
        NVL(SUM (GQVL_D9),0) D9,
        NVL(SUM (GQVL_D10),0) D10,
        NVL(SUM (GQVL_D11),0) D11,
        NVL(SUM (GQVL_D12),0) D12,
        NVL(SUM (GQVL_D13),0) D13,
        NVL(SUM (GQVL_D14),0) D14,
        NVL(SUM (GQVL_D15),0) D15,
        NVL(SUM (GQVL_D19),0) D19
        FROM BCTD_GQVL,DMKHAC A,DMPOS Y,(SELECT PO_POSFLG, PO_MACN, PO_MA FROM DMPOS WHERE PO_MA = p_pos_cd) Z
        WHERE GQVL_NGAYBC  BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date))
        AND GQVL_CAPQLV = KHOA_2 AND KHOA_1 = '19' AND GQVL_NGUONVON_BS='01'
        AND GQVL_MAPGD = Y.PO_MA
        AND DECODE(Z.PO_POSFLG,'H',1,'S',Y.PO_MA , Y.PO_MACN) = DECODE(Z.PO_POSFLG,'H',1,Z.PO_MA)
        AND DECODE('Y',p_pos_flag,1,Y.PO_MA) = DECODE('Y',p_pos_flag,1,p_pos_cd)
        GROUP BY GQVL_NGUONVON_BS, GQVL_CAPQLV,GIATRI HAVING SUM (GQVL_D7) > 0
        ORDER BY GQVL_NGUONVON_BS, GQVL_CAPQLV,GIATRI)A
        UNION ALL
        ----------GQVL_NGUONVON_BS='02' NHCHSH huy dong
        SELECT '02'GQVL_NGUONVON_BS, ''GQVL_CAPQLV,'' TEN_CAPQL, 0 D1, 0 D2, 0 D3, 0 D4, 0 D5, 0 D6, 0 D7, 0 D8, 0 D9, 0 D10, 0 D11, 0 D12, 0 D13, 0 D14, 0 D15, 0 D19 FROM DUAL
        UNION ALL 
        SELECT B.* FROM (SELECT GQVL_NGUONVON_BS,
        GQVL_CAPQLV,GIATRI AS TEN_CAPQL,
        NVL(SUM (GQVL_D1),0) D1,
        NVL(SUM (GQVL_D2),0) D2,
        NVL(SUM (GQVL_D3),0) D3,
        NVL(SUM (GQVL_D4),0) D4,
        NVL(SUM (GQVL_D5),0) D5,
        NVL(SUM (GQVL_D6),0) D6,
        NVL(SUM (GQVL_D7),0) D7,
        NVL(SUM (GQVL_D8),0) D8,
        NVL(SUM (GQVL_D9),0) D9,
        NVL(SUM (GQVL_D10),0) D10,
        NVL(SUM (GQVL_D11),0) D11,
        NVL(SUM (GQVL_D12),0) D12,
        NVL(SUM (GQVL_D13),0) D13,
        NVL(SUM (GQVL_D14),0) D14,
        NVL(SUM (GQVL_D15),0) D15,
        NVL(SUM (GQVL_D19),0) D19
        FROM BCTD_GQVL,DMKHAC A,DMPOS Y,(SELECT PO_POSFLG, PO_MACN, PO_MA FROM DMPOS WHERE PO_MA = p_pos_cd) Z
        WHERE GQVL_NGAYBC  BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date))
        AND GQVL_CAPQLV = KHOA_2 AND KHOA_1 = '19' AND GQVL_NGUONVON_BS='02'
        AND GQVL_MAPGD = Y.PO_MA
        AND DECODE(Z.PO_POSFLG,'H',1,'S',Y.PO_MA , Y.PO_MACN) = DECODE(Z.PO_POSFLG,'H',1,Z.PO_MA)
        AND DECODE('Y',p_pos_flag,1,Y.PO_MA) = DECODE('Y',p_pos_flag,1,p_pos_cd)
        GROUP BY GQVL_NGUONVON_BS, GQVL_CAPQLV,GIATRI HAVING SUM (GQVL_D7) > 0
        ORDER BY GQVL_NGUONVON_BS, GQVL_CAPQLV,GIATRI)B
        UNION ALL
        ------------GQVL_NGUONVON_BS='03' von giai quyet viec lam dia phuong
        SELECT '03'GQVL_NGUONVON_BS, ''GQVL_CAPQLV,'' TEN_CAPQL, 0 D1, 0 D2, 0 D3, 0 D4, 0 D5, 0 D6, 0 D7, 0 D8, 0 D9, 0 D10, 0 D11, 0 D12, 0 D13, 0 D14, 0 D15, 0 D19 FROM DUAL
        UNION ALL 
        SELECT B.* FROM (SELECT GQVL_NGUONVON_BS,
        GQVL_CAPQLV,GIATRI AS TEN_CAPQL,
        NVL(SUM (GQVL_D1),0) D1,
        NVL(SUM (GQVL_D2),0) D2,
        NVL(SUM (GQVL_D3),0) D3,
        NVL(SUM (GQVL_D4),0) D4,
        NVL(SUM (GQVL_D5),0) D5,
        NVL(SUM (GQVL_D6),0) D6,
        NVL(SUM (GQVL_D7),0) D7,
        NVL(SUM (GQVL_D8),0) D8,
        NVL(SUM (GQVL_D9),0) D9,
        NVL(SUM (GQVL_D10),0) D10,
        NVL(SUM (GQVL_D11),0) D11,
        NVL(SUM (GQVL_D12),0) D12,
        NVL(SUM (GQVL_D13),0) D13,
        NVL(SUM (GQVL_D14),0) D14,
        NVL(SUM (GQVL_D15),0) D15,
        NVL(SUM (GQVL_D19),0) D19
        FROM BCTD_GQVL,DMKHAC A,DMPOS Y,(SELECT PO_POSFLG, PO_MACN, PO_MA FROM DMPOS WHERE PO_MA = p_pos_cd) Z
        WHERE GQVL_NGAYBC  BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date))
        AND GQVL_CAPQLV = KHOA_2 AND KHOA_1 = '19' AND GQVL_NGUONVON_BS='03'
        AND GQVL_MAPGD = Y.PO_MA
        AND DECODE(Z.PO_POSFLG,'H',1,'S',Y.PO_MA , Y.PO_MACN) = DECODE(Z.PO_POSFLG,'H',1,Z.PO_MA)
        AND DECODE('Y',p_pos_flag,1,Y.PO_MA) = DECODE('Y',p_pos_flag,1,p_pos_cd)
        GROUP BY GQVL_NGUONVON_BS, GQVL_CAPQLV,GIATRI HAVING SUM (GQVL_D7) > 0
        ORDER BY GQVL_NGUONVON_BS, GQVL_CAPQLV,GIATRI)B);
    end;
    --- Thủ tục in báo cáo theo Quyet dinh 308/QĐ-NHCS. Mẫu BCTD_0705
    --- Người tạo: HAILT
    --- Ngày tạo: 22/04/2016
    PROCEDURE INSL_BCTD_0705 (p_pos_cd in varchar2,p_pos_flag in varchar2,p_report_date IN varchar2,  p_princur OUT sys_refcursor)
       AS
       I_UNTIL    NUMBER := 1000000;  
          V_CAP_BC   NUMBER := 0;              -- Cap bao cao, 3: tw, 2: cn, 1:pgd
       BEGIN
          SELECT VBSP_PUBLIC_FUNCTION.F_GET_REPORT_GRADE (p_pos_cd, p_pos_flag)
            INTO V_CAP_BC
            FROM DUAL;

          IF (V_CAP_BC = 3)
          THEN
             -- Cap tw
             OPEN p_princur FOR
                SELECT GQVL_NGUONVON_BS, GQVL_TINH as donvi,(select ten from dmtinh x where x.ma=A.GQVL_TINH) tendonvi,
                ROUND(NVL(SUM (GQVL_D1),0)/I_UNTIL,2) D1,
                ROUND(NVL(SUM (GQVL_D2),0)/I_UNTIL,2) D2,
                ROUND(NVL(SUM (GQVL_D3),0)/I_UNTIL,2) D3,
                ROUND(NVL(SUM (GQVL_D4),0)/I_UNTIL,2) D4,
                ROUND(NVL(SUM (GQVL_D5),0)/I_UNTIL,2) D5,
                ROUND(NVL(SUM (GQVL_D6),0)/I_UNTIL,2) D6,
                ROUND(NVL(SUM (GQVL_D7),0)/I_UNTIL,2) D7,
                ROUND(NVL(SUM (GQVL_D8),0)/I_UNTIL,2) D8,
                ROUND(NVL(SUM (GQVL_D9),0)/I_UNTIL,2) D9,
                ROUND(NVL(SUM (GQVL_D10),0)/I_UNTIL,2) D10,
                ROUND(NVL(SUM (GQVL_D11),0)/I_UNTIL,2) D11,
                ROUND(NVL(SUM (GQVL_D12),0)/I_UNTIL,2) D12,
                ROUND(NVL(SUM (GQVL_D13),0)/I_UNTIL,2) D13,
                NVL(SUM (GQVL_D14),0) D14,
                NVL(SUM (GQVL_D15),0) D15,
                NVL(SUM (GQVL_D19),0) D19
                FROM BCTD_GQVL A
                WHERE GQVL_NGAYBC  BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date))
                GROUP BY GQVL_NGUONVON_BS,GQVL_TINH HAVING SUM (GQVL_D7) > 0
                ORDER BY GQVL_NGUONVON_BS, GQVL_TINH;
          ELSIF (V_CAP_BC = 2)
          THEN
             -- Cap cn
             OPEN p_princur FOR
                SELECT GQVL_NGUONVON_BS,GQVL_TINH,GQVL_HUYEN as donvi, (select ten from DMHUYEN x where x.ma=A.GQVL_TINH||A.GQVL_HUYEN) tendonvi ,
                ROUND(NVL(SUM (GQVL_D1),0)/I_UNTIL,2) D1,
                ROUND(NVL(SUM (GQVL_D2),0)/I_UNTIL,2) D2,
                ROUND(NVL(SUM (GQVL_D3),0)/I_UNTIL,2) D3,
                ROUND(NVL(SUM (GQVL_D4),0)/I_UNTIL,2) D4,
                ROUND(NVL(SUM (GQVL_D5),0)/I_UNTIL,2) D5,
                ROUND(NVL(SUM (GQVL_D6),0)/I_UNTIL,2) D6,
                ROUND(NVL(SUM (GQVL_D7),0)/I_UNTIL,2) D7,
                ROUND(NVL(SUM (GQVL_D8),0)/I_UNTIL,2) D8,
                ROUND(NVL(SUM (GQVL_D9),0)/I_UNTIL,2) D9,
                ROUND(NVL(SUM (GQVL_D10),0)/I_UNTIL,2) D10,
                ROUND(NVL(SUM (GQVL_D11),0)/I_UNTIL,2) D11,
                ROUND(NVL(SUM (GQVL_D12),0)/I_UNTIL,2) D12,
                ROUND(NVL(SUM (GQVL_D13),0)/I_UNTIL,2) D13,
                NVL(SUM (GQVL_D14),0) D14,
                NVL(SUM (GQVL_D15),0) D15,
                NVL(SUM (GQVL_D19),0) D19
                FROM BCTD_GQVL A
                WHERE GQVL_NGAYBC  BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date))
                AND GQVL_MACN=p_pos_cd
                GROUP BY GQVL_NGUONVON_BS,GQVL_TINH,GQVL_HUYEN HAVING SUM (GQVL_D7) > 0
                ORDER BY GQVL_NGUONVON_BS,GQVL_TINH,GQVL_HUYEN;
                  
          ELSE
             -- Cap pgd
             OPEN p_princur FOR
                SELECT GQVL_NGUONVON_BS, GQVL_TINH,GQVL_HUYEN, GQVL_XA as donvi, (select ten from DMXA x where x.ma=A.GQVL_TINH||A.GQVL_HUYEN||A.GQVL_XA) tendonvi,
                ROUND(NVL(SUM (GQVL_D1),0)/I_UNTIL,2) D1,
                ROUND(NVL(SUM (GQVL_D2),0)/I_UNTIL,2) D2,
                ROUND(NVL(SUM (GQVL_D3),0)/I_UNTIL,2) D3,
                ROUND(NVL(SUM (GQVL_D4),0)/I_UNTIL,2) D4,
                ROUND(NVL(SUM (GQVL_D5),0)/I_UNTIL,2) D5,
                ROUND(NVL(SUM (GQVL_D6),0)/I_UNTIL,2) D6,
                ROUND(NVL(SUM (GQVL_D7),0)/I_UNTIL,2) D7,
                ROUND(NVL(SUM (GQVL_D8),0)/I_UNTIL,2) D8,
                ROUND(NVL(SUM (GQVL_D9),0)/I_UNTIL,2) D9,
                ROUND(NVL(SUM (GQVL_D10),0)/I_UNTIL,2) D10,
                ROUND(NVL(SUM (GQVL_D11),0)/I_UNTIL,2) D11,
                ROUND(NVL(SUM (GQVL_D12),0)/I_UNTIL,2) D12,
                ROUND(NVL(SUM (GQVL_D13),0)/I_UNTIL,2) D13,
                NVL(SUM (GQVL_D14),0) D14,
                NVL(SUM (GQVL_D15),0) D15,
                NVL(SUM (GQVL_D19),0) D19
                FROM BCTD_GQVL A
                WHERE GQVL_NGAYBC  BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date))
                AND GQVL_MAPGD=p_pos_cd
                GROUP BY GQVL_NGUONVON_BS,GQVL_TINH,GQVL_HUYEN,GQVL_XA HAVING SUM (GQVL_D7) > 0
                ORDER BY GQVL_NGUONVON_BS,GQVL_TINH,GQVL_HUYEN,GQVL_XA;   
          END IF;
       END;
    --- Thủ tục in báo cáo theo Quyet dinh 308/QĐ-NHCS. Mẫu BCTD_0708
    --- Người tạo: HAILT
    --- Ngày tạo: 22/04/2016
    PROCEDURE INSL_BCTD_0708 (p_pos_cd in varchar2,p_pos_flag in varchar2,p_report_date IN varchar2,  p_princur OUT sys_refcursor)
       AS
       I_UNTIL    NUMBER := 1000000;  
          V_CAP_BC   NUMBER := 0;              -- Cap bao cao, 3: tw, 2: cn, 1:pgd
       BEGIN
          SELECT VBSP_PUBLIC_FUNCTION.F_GET_REPORT_GRADE (p_pos_cd, p_pos_flag)
            INTO V_CAP_BC
            FROM DUAL;

          IF (V_CAP_BC = 3)
          THEN
             -- Cap tw
             OPEN p_princur FOR
                SELECT KYQUY_TINH as donvi,(select ten from dmtinh x where x.ma=A.KYQUY_TINH) tendonvi,
                ROUND(NVL(SUM (KYQUY_D1),0)/I_UNTIL,2) D1,
                ROUND(NVL(SUM (KYQUY_D2),0)/I_UNTIL,2) D2,
                ROUND(NVL(SUM (KYQUY_D3),0)/I_UNTIL,2) D3,
                ROUND(NVL(SUM (KYQUY_D4),0)/I_UNTIL,2) D4,
                ROUND(NVL(SUM (KYQUY_D5),0)/I_UNTIL,2) D5,
                ROUND(NVL(SUM (KYQUY_D6),0)/I_UNTIL,2) D6,
                ROUND(NVL(SUM (KYQUY_D7),0)/I_UNTIL,2) D7,
                ROUND(NVL(SUM (KYQUY_D8),0)/I_UNTIL,2) D8,
                ROUND(NVL(SUM (KYQUY_D9),0)/I_UNTIL,2) D9,
                ROUND(NVL(SUM (KYQUY_D10),0)/I_UNTIL,2) D10,
                ROUND(NVL(SUM (KYQUY_D11),0)/I_UNTIL,2) D11,
                ROUND(NVL(SUM (KYQUY_D12),0)/I_UNTIL,2) D12,
                ROUND(NVL(SUM (KYQUY_D13),0)/I_UNTIL,2) D13,
                NVL(SUM (KYQUY_D14),0) D14,
                NVL(SUM (KYQUY_D15),0) D15
                FROM BCTD_KYQUY A
                WHERE KYQUY_NGAYBC  BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date))
                GROUP BY KYQUY_TINH HAVING SUM (KYQUY_D7) > 0
                ORDER BY KYQUY_TINH;
          ELSIF (V_CAP_BC = 2)
          THEN
             -- Cap cn
             OPEN p_princur FOR
                SELECT KYQUY_TINH, KYQUY_HUYEN as donvi,(select ten from DMHUYEN x where x.ma=A.KYQUY_TINH||A.KYQUY_HUYEN) tendonvi,
                ROUND(NVL(SUM (KYQUY_D1),0)/I_UNTIL,2) D1,
                ROUND(NVL(SUM (KYQUY_D2),0)/I_UNTIL,2) D2,
                ROUND(NVL(SUM (KYQUY_D3),0)/I_UNTIL,2) D3,
                ROUND(NVL(SUM (KYQUY_D4),0)/I_UNTIL,2) D4,
                ROUND(NVL(SUM (KYQUY_D5),0)/I_UNTIL,2) D5,
                ROUND(NVL(SUM (KYQUY_D6),0)/I_UNTIL,2) D6,
                ROUND(NVL(SUM (KYQUY_D7),0)/I_UNTIL,2) D7,
                ROUND(NVL(SUM (KYQUY_D8),0)/I_UNTIL,2) D8,
                ROUND(NVL(SUM (KYQUY_D9),0)/I_UNTIL,2) D9,
                ROUND(NVL(SUM (KYQUY_D10),0)/I_UNTIL,2) D10,
                ROUND(NVL(SUM (KYQUY_D11),0)/I_UNTIL,2) D11,
                ROUND(NVL(SUM (KYQUY_D12),0)/I_UNTIL,2) D12,
                ROUND(NVL(SUM (KYQUY_D13),0)/I_UNTIL,2) D13,
                NVL(SUM (KYQUY_D14),0) D14,
                NVL(SUM (KYQUY_D15),0) D15
                FROM BCTD_KYQUY A
                WHERE KYQUY_NGAYBC  BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date))
                AND KYQUY_MACN=p_pos_cd
                GROUP BY KYQUY_TINH,KYQUY_HUYEN HAVING SUM (KYQUY_D7) > 0
                ORDER BY KYQUY_TINH,KYQUY_HUYEN;
                  
          ELSE
             -- Cap pgd
             OPEN p_princur FOR
                SELECT KYQUY_TINH, KYQUY_HUYEN,KYQUY_XA as donvi,(select ten from DMXA x where x.ma=A.KYQUY_TINH||A.KYQUY_HUYEN||A.KYQUY_XA) tendonvi,
                ROUND(NVL(SUM (KYQUY_D1),0)/I_UNTIL,2) D1,
                ROUND(NVL(SUM (KYQUY_D2),0)/I_UNTIL,2) D2,
                ROUND(NVL(SUM (KYQUY_D3),0)/I_UNTIL,2) D3,
                ROUND(NVL(SUM (KYQUY_D4),0)/I_UNTIL,2) D4,
                ROUND(NVL(SUM (KYQUY_D5),0)/I_UNTIL,2) D5,
                ROUND(NVL(SUM (KYQUY_D6),0)/I_UNTIL,2) D6,
                ROUND(NVL(SUM (KYQUY_D7),0)/I_UNTIL,2) D7,
                ROUND(NVL(SUM (KYQUY_D8),0)/I_UNTIL,2) D8,
                ROUND(NVL(SUM (KYQUY_D9),0)/I_UNTIL,2) D9,
                ROUND(NVL(SUM (KYQUY_D10),0)/I_UNTIL,2) D10,
                ROUND(NVL(SUM (KYQUY_D11),0)/I_UNTIL,2) D11,
                ROUND(NVL(SUM (KYQUY_D12),0)/I_UNTIL,2) D12,
                ROUND(NVL(SUM (KYQUY_D13),0)/I_UNTIL,2) D13,
                NVL(SUM (KYQUY_D14),0) D14,
                NVL(SUM (KYQUY_D15),0) D15
                FROM BCTD_KYQUY A
                WHERE KYQUY_NGAYBC  BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date))
                AND KYQUY_MAPGD=p_pos_cd
                GROUP BY KYQUY_TINH,KYQUY_HUYEN,KYQUY_XA HAVING SUM (KYQUY_D7) > 0
                ORDER BY KYQUY_TINH,KYQUY_HUYEN,KYQUY_XA;
          END IF;
       END;   
    --- Thủ tục in báo cáo theo Quyet dinh 308/QĐ-NHCS. Mẫu BCTD_0709
    --- Người tạo: HAILT
    --- Ngày tạo: 22/04/2016
    PROCEDURE INSL_BCTD_0709 ( p_pos_cd in varchar2,p_pos_flag in varchar2,p_report_date IN varchar2,  p_princur OUT sys_refcursor)
        AS        
           I_UNTIL    NUMBER := 1000000;  
        BEGIN
            OPEN p_princur FOR
            SELECT KYQUY_MAQD,GIATRI AS TENQD,
            ROUND(NVL(SUM (KYQUY_D1),0)/I_UNTIL,2) D1,
            ROUND(NVL(SUM (KYQUY_D2),0)/I_UNTIL,2) D2,
            ROUND(NVL(SUM (KYQUY_D3),0)/I_UNTIL,2) D3,
            ROUND(NVL(SUM (KYQUY_D4),0)/I_UNTIL,2) D4,
            ROUND(NVL(SUM (KYQUY_D5),0)/I_UNTIL,2) D5,
            ROUND(NVL(SUM (KYQUY_D6),0)/I_UNTIL,2) D6,
            ROUND(NVL(SUM (KYQUY_D7),0)/I_UNTIL,2) D7,
            ROUND(NVL(SUM (KYQUY_D8),0)/I_UNTIL,2) D8,
            ROUND(NVL(SUM (KYQUY_D9),0)/I_UNTIL,2) D9,
            ROUND(NVL(SUM (KYQUY_D10),0)/I_UNTIL,2) D10,
            ROUND(NVL(SUM (KYQUY_D11),0)/I_UNTIL,2) D11,
            ROUND(NVL(SUM (KYQUY_D12),0)/I_UNTIL,2) D12,
            ROUND(NVL(SUM (KYQUY_D13),0)/I_UNTIL,2) D13,
            NVL(SUM (KYQUY_D14),0) D14,
            NVL(SUM (KYQUY_D15),0) D15
            FROM BCTD_KYQUY,DMKHAC A,DMPOS Y,(SELECT PO_POSFLG, PO_MACN, PO_MA FROM DMPOS WHERE PO_MA = p_pos_cd) Z
            WHERE KYQUY_NGAYBC  BETWEEN LAST_DAY(ADD_MONTHS(TO_DATE(p_report_date), -1)) + 1 AND LAST_DAY(TO_DATE(p_report_date))
            AND KYQUY_MAQD = KHOA_2 AND KHOA_1 = '07' 
            AND KYQUY_MAPGD = Y.PO_MA
            AND DECODE(Z.PO_POSFLG,'H',1,'S',Y.PO_MA , Y.PO_MACN) = DECODE(Z.PO_POSFLG,'H',1,Z.PO_MA)
            AND DECODE('Y',p_pos_flag,1,Y.PO_MA) = DECODE('Y',p_pos_flag,1,p_pos_cd)
            GROUP BY KYQUY_MAQD,GIATRI HAVING SUM (KYQUY_D7) > 0
            ORDER BY KYQUY_MAQD,GIATRI;
        end;   
 
END BCTD_INBC;
/