CREATE OR REPLACE package body vbsp_qd16_proc_pkg
as
    PROCEDURE b02_vbsp 
        (p_pos_cd     in varchar2,
        p_date         in varchar2,
        p_posflag     in varchar2,
        p_termflag     in varchar2,
        aoutcursordata out sys_refcursor)
    AS
        v_date date;
        v_date_ago date;
        l_date varchar2 (12);
        l_date_ago varchar2 (12);
        l_date_ago_1 varchar2 (12);
        a_1 number (32, 4) := 0;
        a_2 number (32, 4) := 0;
        a_3 number (32, 4) := 0;
        a_4 number (32, 4) := 0;
        a_5 number (32, 4) := 0;
        a_6 number (32, 4) := 0;
        a_7 number (32, 4) := 0;
        a_8 number (32, 4) := 0;
        a_9 number (32, 4) := 0;
        a_10 number (32, 4) := 0;
        a_11 number (32, 4) := 0;
        a_12 number (32, 4) := 0;
        a_13 number (32, 4) := 0;
        a_14 number (32, 4) := 0;
        a_15 number (32, 4) := 0;
        a_16 number (32, 4) := 0;
        a_17 number (32, 4) := 0;
        a_18 number (32, 4) := 0;
        a_19 number (32, 4) := 0;
        a_20 number (32, 4) := 0;
        a_21 number (32, 4) := 0;
        a_22 number (32, 4) := 0;
        a_23 number (32, 4) := 0;
        a_24 number (32, 4) := 0;
        a_25 number (32, 4) := 0;
        a_26 number (32, 4) := 0;
        a_27 number (32, 4) := 0;
        a_28 number (32, 4) := 0;
        a_29 number (32, 4) := 0;
        a_30 number (32, 4) := 0;
        a_31 number (32, 4) := 0;
        a_32 number (32, 4) := 0;
        a_33 number (32, 4) := 0;
        a_14_1 number (32, 4) := 0;
        a1_15 number (32, 4) := 0;
        a2_15 number (32, 4) := 0;
        a3_15 number (32, 4) := 0;
        a4_15 number (32, 4) := 0;
        a1_23 number (32, 4) := 0;
        a2_23 number (32, 4) := 0;
        a3_23 number (32, 4) := 0;
        tg_a1 number (32, 4) := 0;
        tg_a2 number (32, 4) := 0;
        tg_a3 number (32, 4) := 0;
        tg_a4 number (32, 4) := 0;
        tg_a5 number (32, 4) := 0;
        tg_a6 number (32, 4) := 0;
        tg_a7 number (32, 4) := 0;
        tg_a8 number (32, 4) := 0;
        tg_a9 number (32, 4) := 0;
        tg_a91 number (32, 4) := 0;
        b_1 number (32, 4) := 0;
        b_2 number (32, 4) := 0;
        b_3 number (32, 4) := 0;
        b_4 number (32, 4) := 0;
        b_5 number (32, 4) := 0;
        b_6 number (32, 4) := 0;
        b_7 number (32, 4) := 0;
        b_8 number (32, 4) := 0;
        b_9 number (32, 4) := 0;
        b_10 number (32, 4) := 0;
        b_11 number (32, 4) := 0;
        b_12 number (32, 4) := 0;
        b_13 number (32, 4) := 0;
        b_14 number (32, 4) := 0;
        b_15 number (32, 4) := 0;
        b_16 number (32, 4) := 0;
        b_17 number (32, 4) := 0;
        b_18 number (32, 4) := 0;
        b_19 number (32, 4) := 0;
        b_20 number (32, 4) := 0;
        b_21 number (32, 4) := 0;
        b_22 number (32, 4) := 0;
        b_23 number (32, 4) := 0;
        b_24 number (32, 4) := 0;
        b_25 number (32, 4) := 0;
        b_26 number (32, 4) := 0;
        b_27 number (32, 4) := 0;
        b_28 number (32, 4) := 0;
        b_29 number (32, 4) := 0;
        b_30 number (32, 4) := 0;
        b_31 number (32, 4) := 0;
        b_32 number (32, 4) := 0;
        b_33 number (32, 4) := 0;
        B_14_1 number (32, 4) := 0;
        b1_15 number (32, 4) := 0;
        b2_15 number (32, 4) := 0;
        b3_15 number (32, 4) := 0;
        b4_15 number (32, 4) := 0;
        b1_23 number (32, 4) := 0;
        b2_23 number (32, 4) := 0;
        b3_23 number (32, 4) := 0;
        tg_b1 number (32, 4) := 0;
        tg_b2 number (32, 4) := 0;
        tg_b3 number (32, 4) := 0;
        tg_b4 number (32, 4) := 0;
        tg_b5 number (32, 4) := 0;
        tg_b6 number (32, 4) := 0;
        tg_b7 number (32, 4) := 0;
        tg_b8 number (32, 4) := 0;
        tg_b9 number (32, 4) := 0;
        tg_b91 number (32, 4) := 0;
        BEGIN
        IF TRIM (P_TERMFLAG) = 'Q'
        then
            v_date := vbsp_public_function.f_get_last_day_of_quater (p_date);
            l_date := to_char (v_date, 'dd-mon-yyyy');
            v_date_ago := vbsp_public_function.f_get_last_day_of_year (add_months (v_date, -12));
            l_date_ago := vbsp_public_function.f_get_last_day_of_year (to_char(add_months (v_date, -12), 'dd-mon-yyyy'));
            l_date_ago_1 := last_day(vbsp_public_function.f_get_first_day_of_year (v_date));
        else
            v_date := vbsp_public_function.f_get_last_day_of_year (p_date);
            l_date := to_char (v_date, 'dd-mon-yyyy');
            v_date_ago := add_months (l_date, -12);
            l_date_ago := vbsp_public_function.f_get_last_day_of_year (to_char(add_months (v_date, -12), 'dd-mon-yyyy'));
            l_date_ago_1 :=last_day(vbsp_public_function.f_get_first_day_of_year (v_date));
        END IF;
        A_1 := rpt_balance_ulib.f_get_sbv_ac_round(L_DATE,'Q',P_POS_CD,P_POSFLAG,'101','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round(L_DATE,'Q',P_POS_CD,P_POSFLAG,'103','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round(L_DATE,'Q',P_POS_CD,P_POSFLAG,'104','DCN');
        B_1 := rpt_balance_ulib.f_get_sbv_ac_round(L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'101','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round(L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'103','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round(L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'104','DCN');
        A_2 := rpt_balance_ulib.f_get_sbv_ac_round(L_DATE,'Q',P_POS_CD,P_POSFLAG,'111','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round(L_DATE,'Q',P_POS_CD,P_POSFLAG,'112','DCN');
        B_2 := rpt_balance_ulib.f_get_sbv_ac_round(L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'111','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round(L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'112','DCN');
        A_3 := rpt_balance_ulib.f_get_sbv_ac_round(L_DATE,'Q',P_POS_CD,P_POSFLAG,'131','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round(L_DATE,'Q',P_POS_CD,P_POSFLAG,'132','DCN');
        B_3 := rpt_balance_ulib.f_get_sbv_ac_round(L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'131','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round(L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'132','DCN');
        A_4 := rpt_balance_ulib.f_get_sbv_ac_round(L_DATE,'Q',P_POS_CD,P_POSFLAG,'201','DCN');
        B_4 :=rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'201','DCN');
        A_5 := - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'209','DCC');
        B_5 :=- rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'209','DCC');
        A_6 :=rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'211','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'212','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'213','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'251','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'252','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'253','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'271','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'291','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'292','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'293','DCN');
        B_6 :=rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'211','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'212','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'213','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'251','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'252','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'253','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'271','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'291','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'292','DCN')
            + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'293','DCN');
        A_7 := - ( rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'219','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'259','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'279','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'299','DCC') );
        B_7 := - ( rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'219','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'259','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'279','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'299','DCC') )  ;
        A_8 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'301','DCN');
        B_8 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'301','DCN');
        A_9 :=    - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'3051','DCC');
        B_9 :=    - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'3051','DCC');
        A_10 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'303','DCN');
        B_10 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'303','DCN');
        A_11 :=    - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'3053','DCC');
        B_11 :=    - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'3053','DCC');
        A_12 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'302','DCN');
        B_12 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'302','DCN');
        A_13 :=    - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'3052','DCC');
        B_13 :=    - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'3052','DCC');
        A_14 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'32','DCN')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'35','DCN')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'3535','DCN')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'36','DCN');
        B_14 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'32','DCN')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'35','DCN')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'3535','DCN')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'36','DCN');

        A_14_1 :=        rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'397','DCN');
        B_14_1 :=        rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'397','DCN');
        
        A1_15 :=rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'31','DCN')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'31','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'38','DCN');
        A2_15 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'50','DCN')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'50','DCC');
        A3_15 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'51','DCN')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'51','DCC');
        A4_15 := 0;
        IF A2_15 > 0
        THEN
            A4_15 := A4_15 + A2_15;
        END IF;
        IF A3_15 > 0
        THEN
            A4_15 := A4_15 + A3_15;
        END IF;
        A_15 := A1_15 + A4_15;
        B1_15 :=rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'31','DCN')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'31','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'38','DCN');
        B2_15 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'50','DCN')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'50','DCC');
        B3_15 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'51','DCN')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'51','DCC');
        B4_15 := 0;
        IF B2_15 > 0
        THEN
            B4_15 := B4_15 + B2_15;
        END IF;
        IF B3_15 > 0
        THEN
            B4_15 := B4_15 + B3_15;
        END IF;
        B_15 := B1_15 + B4_15;
        A_16 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'401','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'403','DCC');
        B_16 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'401','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'403','DCC');
        A_17 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'411','DCC');
        B_17 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'411','DCC');
        A_18 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'415','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'418','DCC');
        B_18 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'415','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'418','DCC');
        A_19 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'42','DCC');
        B_19 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'42','DCC');
        A_20 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'441','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'442','DCC');
        B_20 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'441','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'442','DCC');
        A_21 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'43','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'43','DCN');
        B_21 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'43','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'43','DCN');
        A_22 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'491','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'492','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'493','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'494','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'497','DCC');
        B_22 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'491','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'492','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'493','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'494','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'497','DCC');
        A1_23 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'45','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'46','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'484','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'485','DCC');
        A2_23 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'50','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'50','DCN');
        A3_23 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'51','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'51','DCN');
        IF A2_23 + A3_23 > 0
        THEN
            A_23 := A1_23 + A2_23 + A3_23;
        ELSE
            A_23 := A1_23;
        END IF;
        
        B1_23 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'45','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'46','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'484','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'485','DCC');
        B2_23 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'50','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'50','DCN');
        B3_23 := rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'51','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'51','DCN');
        IF B2_23 + B3_23 > 0
        THEN
            B_23 := B1_23 + B2_23 + B3_23;
        ELSE
            B_23 := B1_23;
        END IF;
        A_24 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'4891','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'4899','DCC');
        B_24 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'4891','DCC')
                + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'4899','DCC');
        A_25 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'601','DCC');
        B_25 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'601','DCC');
        A_26 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'602','DCC');
        B_26 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'602','DCC');

        A_27 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'609','DCC');
        B_27 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'609','DCC');
        A_28 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'61','DCC')
                    + rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'62','DCC');
        B_28 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'61','DCC')
                    +rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'62','DCC');
        A_29 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'63','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'63','DCN');
        B_29 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'63','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'63','DCN');
        A_30 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'64','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'64','DCN');
        B_30 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'64','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO,'Y',P_POS_CD,P_POSFLAG,'64','DCN');
        A_32 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'7','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'8','DCN');
        B_32 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO_1,'Y',P_POS_CD,P_POSFLAG,'7','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO_1,'Y',P_POS_CD,P_POSFLAG,'8','DCN');
        A_33 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'69','DCC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE,'Q',P_POS_CD,P_POSFLAG,'69','DCN');
        B_33 :=    rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO_1,'M',P_POS_CD,P_POSFLAG,'69','DDC')
                - rpt_balance_ulib.f_get_sbv_ac_round (L_DATE_AGO_1,'M',P_POS_CD,P_POSFLAG,'69','DDN');
        A_31 := A_32 + A_33;
        B_31 := B_32 + B_33;
        
        /* -- Don vi trieu dong
        a_1     := A_1/1000000;
        a_2     := A_2/1000000;
        a_3     := A_3/1000000;
        a_4     := A_4/1000000;
        a_5     := A_5/1000000;
        a_6     := A_6/1000000;
        a_7     := A_7/1000000;
        a_8     := A_8/1000000;
        a_9     := A_9/1000000;
        a_10     := A_10/1000000;
        a_11     := A_11/1000000;
        a_12     := A_12/1000000;
        a_13     := A_13/1000000;
        a_14     := A_14/1000000;
        a_14_1   := A_14_1/1000000;
        a_15     := A_15/1000000;
        a_16     := A_16/1000000;
        a_17     := A_17/1000000;
        a_18     := A_18/1000000;
        a_19     := A_19/1000000;
        a_20     := A_20/1000000;
        a_21     := A_21/1000000;
        a_22     := A_2/1000000;
        a_23     := A_23/1000000;
        a_24     := A_24/1000000;
        a_25     := A_25/1000000;
        a_26     := A_26/1000000;
        a_27     := A_27/1000000;
        a_28     := A_28/1000000;
        a_29     := A_29/1000000;
        a_30     := A_30/1000000;
        a_31     := A_31/1000000;
        a_32     := A_32/1000000;
        a_33     := A_33/1000000;

        b_1     := B_1/1000000;
        b_2     := B_2/1000000;
        b_3     := B_3/1000000;
        b_4     := B_4/1000000;
        b_5     := B_5/1000000;
        b_6     := B_6/1000000;
        b_7     := B_7/1000000;
        b_8     := B_8/1000000;
        b_9     := B_9/1000000;
        b_10     := B_10/1000000;
        b_11     := B_11/1000000;
        b_12     := B_12/1000000;
        b_13     := B_13/1000000;
        b_14     := B_14/1000000;
        b_14_1     := B_14_1/1000000;
        b_15     := B_15/1000000;
        b_16     := B_16/1000000;
        b_17     := B_17/1000000;
        b_18     := B_18/1000000;
        b_19     := B_19/1000000;
        b_20     := B_20/1000000;
        b_21     := B_21/1000000;
        b_22     := B_22/1000000;
        b_23     := B_23/1000000;
        b_24     := B_24/1000000;
        b_25     := B_25/1000000;
        b_26     := B_26/1000000;
        b_27     := B_27/1000000;
        b_28     := B_28/1000000;
        b_29     := B_29/1000000;
        b_30     := B_30/1000000;
        b_31     := B_31/1000000;
        b_32     := B_32/1000000;
        b_33     := B_33/1000000; */
        
        TG_A2 := A_3 + A_4 + A_5;
        TG_B2 := B_3 + B_4 + B_5;
        TG_A3 := A_6 + A_7;
        TG_B3 := B_6 + B_7;
        TG_A4 := A_8 + A_9 + A_10 + A_11 + A_12 + A_13;
        TG_B4 := B_8 + B_9 + B_10 + B_11 + B_12 + B_13;
        TG_A5 := A_14 + A_15;
        TG_B5 := B_14 + B_15;
        TG_A7 := A_17 + A_18;
        TG_B7 := B_17 + B_18;
        TG_A8 := A_22 + A_23 + A_24;
        TG_B8 := B_22 + B_23 + B_24;
        TG_A91 := A_25 + A_26 + A_27;
        TG_B91 := B_25 + B_26 + B_27;
        TG_A9 := TG_A91 + A_28 + A_29 + A_30 + A_31;
        TG_B9 := TG_B91 + B_28 + B_29 + B_30 + B_31;
        TG_A1 := TG_A2 + TG_A3 + TG_A4;
        TG_B1 := TG_B2 + TG_B3 + TG_B4;
        TG_A6 := A_16 + TG_A7 + A_19 + A_20 + A_21 + TG_A8 + TG_A9;
        TG_B6 := B_16 + TG_B7 + B_19 + B_20 + B_21 + TG_B8 + TG_B9;
        TG_A1 := A_1 + A_2 + TG_A2 + TG_A3 + TG_A4 + TG_A5;
        TG_B1 := B_1 + B_2 + TG_B2 + TG_B3 + TG_B4 + TG_B5;
        OPEN aoutcursordata FOR
        SELECT 
                v_date v_date,
                l_date l_date,
                v_date_ago v_date_ago,
                l_date_ago l_date_ago,
                l_date_ago_1 l_date_ago_1,
                A_1 A_1,
                A_2 A_2,
                A_3 A_3,
                A_4 A_4,
                A_5 A_5,
                A_6 A_6,
                A_7 A_7,
                A_8 A_8,
                A_9 A_9,
                A_10 A_10,
                A_11 A_11,
                A_12 A_12,
                A_13 A_13,
                A_14 A_14,
                A_14_1 A_14_1,
                B_14_1 B_14_1,
                A_15 A_15,
                A_16 A_16,
                A_17 A_17,
                A_18 A_18,
                A_19 A_19,
                A_20 A_20,
                A_21 A_21,
                A_22 A_22,
                A_23 A_23,
                A_24 A_24,
                A_25 A_25,
                A_26 A_26,
                A_27 A_27,
                A_28 A_28,
                A_29 A_29,
                A_30 A_30,
                A_31 A_31,
                A_32 A_32,
                A_33 A_33,
                B_1 B_1,
                B_2 B_2,
                B_3 B_3,
                B_4 B_4,
                B_5 B_5,
                B_6 B_6,
                B_7 B_7,
                B_8 B_8,
                B_9 B_9,
                B_10 B_10,
                B_11 B_11,
                B_12 B_12,
                B_13 B_13,
                B_14 B_14,
                B_15 B_15,
                B_16 B_16,
                B_17 B_17,
                B_18 B_18,
                B_19 B_19,
                B_20 B_20,
                B_21 B_21,
                B_22 B_22,
                B_23 B_23,
                B_24 B_24,
                B_25 B_25,
                B_26 B_26,
                B_27 B_27,
                B_28 B_28,
                B_29 B_29,
                B_30 B_30,
                B_31 B_31,
                B_32 B_32,
                B_33 B_33,
                TG_A1 TG_A1,
                TG_A2 TG_A2,
                TG_A3 TG_A3,
                TG_A4 TG_A4,
                TG_A5 TG_A5,
                TG_A6 TG_A6,
                TG_A7 TG_A7,
                TG_A8 TG_A8,
                TG_A9 TG_A9,
                TG_A91 TG_A91,
                TG_B1 TG_B1,
                TG_B2 TG_B2,
                TG_B3 TG_B3,
                TG_B4 TG_B4,
                TG_B5 TG_B5,
                TG_B6 TG_B6,
                TG_B7 TG_B7,
                TG_B8 TG_B8,
                TG_B9 TG_B9,
                TG_B91 TG_B91
        FROM DUAL;
    END ;
    
----------------------------------------------------------
end vbsp_qd16_proc_pkg;
/