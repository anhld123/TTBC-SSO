declare
    p_error_code  number;
    p_error_msg VARCHAR2(1000);
    p_record NUMBER;
begin
    delete dummy;
    commit;
    for ii in (select distinct po_ma from dmpos@cn44)
loop
    rpt_gstx_tsl.taosolieu(ii.po_ma,'N','31-dec-2015', p_error_code , p_error_msg,p_record );
    dbms_output.put_line('rpt_gstx_tsl.taosolieu -->' || p_error_code || p_error_msg || p_record);
end loop;
end;
