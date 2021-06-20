/* **************************************************************************
* Script: scr_check_logic_step.v01
* Module: Tạo số liệu báo cáo cuối tháng
* Mục đích: Hướng dẫn kiểm tra logic sau khi đã hoàn thành cập nhật
* Môi trường: SRV59
* ****************************************************************************/
/*Kiểm tra với cân đối trước khi thực hiện */
TRUNCATE TABLE chk_hsku_integrity;

DECLARE
    p_numofjob NUMBER DEFAULT 5;
    p_procedure VARCHAR2(500);
    p_pos_flg VARCHAR2(1);
    p_report_dt VARCHAR2(12);
    p_add_param VARCHAR2(100);
BEGIN    
    p_procedure := 'Vbsp_chk_integrity.p_chk_loan_os_with_bank_ac';
    p_pos_flg := 'N';
    p_report_dt := '31-AUG-2015';
    vbsp_exe_injob.p_job_execute(p_numofjob,p_procedure,p_pos_flg,p_report_dt,p_add_param,'');    
END;

SELECT COUNT(POS_CD) FROM VBSP_JOB_LOG WHERE PROCEDURE_NAME LIKE '%Vbsp_chk_integrity.p_chk_loan_os_with_bank_ac%';


/* Bước 1 - tạo số liệu backup */
CREATE TABLE hscv_30042015 TABLESPACE oidata AS SELECT * /*+  PARALLEL(hscv_temp,4) */ FROM hscv_temp ;  --WHERE ku_ngaybc = '10-jan-2015';

--TRUNCATE TABLE hscv_temp;

-- BEGIN    
    -- INSERT INTO hscv_temp /*+ append nologging */ SELECT * /*+  PARALLEL(hscv,4) */ FROM hscv WHERE ku_ngaybc = '10-jan-2015';
    -- COMMIT; 
-- END;

-- select  count(*) from hscv_temp;

/* Bước 2 - thực hiện kiểm tra dữ liệu 
* thời gian: 25 phút 
* thời gian: 5 tiếng*/
truncate table chk_hsku_integrity;

DECLARE
    p_numofjob NUMBER DEFAULT 5;
    p_procedure VARCHAR2(500);
    p_pos_flg VARCHAR2(1);
    p_report_dt VARCHAR2(12);
    p_add_param VARCHAR2(100);
BEGIN    
    p_procedure := 'Vbsp_chk_integrity.p_chk_loan_os_logic_dtls';
    p_pos_flg := 'N';
    p_report_dt := '31-AUG-2015';
    vbsp_exe_injob.p_job_execute(p_numofjob,p_procedure,p_pos_flg,p_report_dt,p_add_param,'');    
END;

SELECT COUNT(POS_CD) FROM VBSP_JOB_LOG WHERE PROCEDURE_NAME LIKE '%Vbsp_chk_integrity.p_chk_loan_os_logic_dtls%';


/*Kiểm tra trường hợp lỗi chưa xác định 
* trường hợp có phát sinh gửi lại địa chỉ nttrungyb@gmail.com
* */

select * from chk_hsku_integrity where txt_1 like '[Món vay trường hợp khác]';

/* Bước 3 - thực hiện cập nhật lại doanh số cho khoản vay 
* thời gian: dưới 5 phút*/
CREATE TABLE hsku_integrity_31082015_f01 TABLESPACE oidata AS select * from chk_hsku_integrity where txt_1 not like '[Món vay đúng logic]' order by 1;

DECLARE
    p_numofjob NUMBER DEFAULT 5;
    p_procedure VARCHAR2(500);
    p_pos_flg VARCHAR2(1);
    p_report_dt VARCHAR2(12);
    p_add_param VARCHAR2(100);
BEGIN    
    p_procedure := 'Vbsp_chk_integrity.p_up_loan_os_logic_dtls';
    p_pos_flg := 'N';
    p_report_dt := '31-AUG-2015';
    vbsp_exe_injob.p_job_execute(p_numofjob,p_procedure,p_pos_flg,p_report_dt,p_add_param,'');    
END;

SELECT COUNT(POS_CD) FROM VBSP_JOB_LOG WHERE PROCEDURE_NAME LIKE '%Vbsp_chk_integrity.p_up_loan_os_logic_dtls%' ; --- 697

/*Cập nhật thông tin thu nợ*/
DECLARE
	v_chlech NUMBER;
	v_sobanghi number := 0;
BEGIN
	FOR jj IN (select ku_soku,ku_m_tnthan,ku_m_tnqhan,ku_m_tnkhoanh from hscv_temp where KU_M_TNTHAN < 0)
	LOOP
		v_chlech := nvl(jj.ku_m_tnqhan,0) + nvl(jj.ku_m_tnthan,0) ;
		IF v_chlech >= 0 THEN
			UPDATE hscv_temp
			SET ku_m_tnqhan = v_chlech,ku_m_tnthan = 0 WHERE ku_soku = jj.ku_soku;
		ELSE
			v_chlech := v_chlech + jj.ku_m_tnkhoanh ;
			IF v_chlech >= 0 THEN
				UPDATE hscv_temp
				SET ku_m_tnqhan = 0,ku_m_tnthan = 0,ku_m_tnkhoanh = v_chlech WHERE ku_soku = jj.ku_soku; 
			END IF;
		END IF;
		v_sobanghi := v_sobanghi + 1;
	END LOOP;
	dbms_output.put_line('Số bản ghi được cập nhật: ' || v_sobanghi);
	COMMIT;
EXCEPTION WHEN OTHERS THEN 
	ROLLBACK;
	dbms_output.put_line(SQLERRM);
END;

/* Bước 4 - kiểm tra với cân đối sau khi cập nhật */

CREATE TABLE hsku_integrity_19062015_f02 TABLESPACE oidata AS select * from chk_hsku_integrity where txt_1 not like '[Món vay đúng logic]' order by 1;

TRUNCATE TABLE chk_hsku_integrity;

DECLARE
    p_numofjob NUMBER DEFAULT 5;
    p_procedure VARCHAR2(500);
    p_pos_flg VARCHAR2(1);
    p_report_dt VARCHAR2(12);
    p_add_param VARCHAR2(100);
BEGIN    
    p_procedure := 'Vbsp_chk_integrity.p_chk_loan_os_with_bank_ac';
    p_pos_flg := 'N';
    p_report_dt := '19-JUN-2015';
    vbsp_exe_injob.p_job_execute(p_numofjob,p_procedure,p_pos_flg,p_report_dt,p_add_param,'');    
END;

SELECT COUNT(POS_CD) FROM VBSP_JOB_LOG WHERE PROCEDURE_NAME LIKE 'Vbsp_chk_integrity.p_chk_loan_os_with_bank_ac%'  --- 697;

select * from chk_hsku_integrity where col_3 <> 0 and descript like '[KIEM TRA TONG DU NO]-MONVAY-TAIKHOAN-CHLECH';