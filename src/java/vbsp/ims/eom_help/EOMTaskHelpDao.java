/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.eom_help;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.model.RefObject;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class EOMTaskHelpDao {

    private DaoConnect daoConnect;
    private Connection conn;

    public EOMTaskHelpDao() {
//        daoConnect = new DaoConnect();
    }

    public List<EOMTask> list_all_task(
            String module_id,
            String report_dt,
            String period,
            String reload_data_flg
    ) {
        String strStoreproce
                = "{call crd_sync_task.p_list_all_task(?, ? , ? ,? , ?)}";
        List<EOMTask> eom_tasks = new ArrayList<>();
        try {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            ResultSet rs;

            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, module_id);
                calstatement.setString(2, report_dt);
                calstatement.setString(3, period);
                calstatement.setString(4, reload_data_flg);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(5);
                int l_seq_no;
                String l_task_no, l_descript, l_comment, l_status;
                while (rs.next()) {
                    l_seq_no = rs.getInt("SEQ_NO");
                    l_task_no = rs.getString("TASK");
                    l_descript = rs.getString("DESCRIPT");
                    l_comment = rs.getString("COMMENT");
                    l_status = rs.getString("STATUS");
                    eom_tasks.add(new EOMTask(l_seq_no, l_task_no, l_descript, l_comment, l_status));
                }
                rs.close();
                calstatement.close();
            } catch (SQLException ex_1) {
                System.out.println("Error when execute procedure~" + ex_1.getMessage());
            } finally {
                conn.close();
                return eom_tasks;
            }
        } catch (Exception ex_2) {
            System.err.println("EOMTaskHelpDao.list_all_task-->" + ex_2.getMessage());
            return null;
        }
    }

    public List<EOMSubTask> list_all_subtask(String task,
            String report_dt,
            String period,
            String reload_flg) {
        String strStoreproce
                = "{call crd_sync_task.p_list_all_subtask(?,?,?,?,?)}";
        List<EOMSubTask> eom_subtasks = new ArrayList<>();
        try {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            ResultSet rs;

            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, task);
                calstatement.setString(2, report_dt);
                calstatement.setString(3, period);
                calstatement.setString(4, reload_flg);
                calstatement.registerOutParameter(5, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(5);
                int l_seq_no;
                String l_task_no, l_short_descript, l_server, l_execute_script, l_check_script, l_expected_time,
                        l_descript, l_comment, l_status, l_period, l_sub_task_no, l_attach_file;
                Link attach_file;
                int l_expected_row_total;
                String l_script_type;
                String l_remark;
                String l_execute_permission = "0";
                while (rs.next()) {
                    l_seq_no = rs.getInt("SEQ_NO");
                    l_task_no = rs.getString("TASK");
                    l_sub_task_no = rs.getString("SUBTASK");
                    l_short_descript = rs.getString("SHORT_DESCRIPT");
                    l_descript = rs.getString("DESCRIPT");
                    l_server = rs.getString("SERVER");
                    l_execute_script = rs.getString("EXECUTE_SCRIPT");
                    l_check_script = rs.getString("CHECK_SCRIPT");
                    l_expected_time = rs.getString("EXPECTED_TIME");
                    l_status = rs.getString("STATUS");
                    l_comment = rs.getString("COMMENT");
                    l_period = rs.getString("PERIOD");
                    l_attach_file = rs.getString("ATTACH_FILE");
                    l_script_type = rs.getString("SCRIPT_TYPE");
                    if (l_attach_file == null
                            || l_attach_file.isEmpty()) {
                        attach_file = new Link("", "");
                    } else {
                        attach_file = new Link("attach file", Define.M_ROOT + l_attach_file);
                    }

                    l_expected_row_total = rs.getInt("EXPECTED_ROW_TOTAL");
                    l_remark = rs.getString("REMARK");
                    l_execute_permission = rs.getString("EXECUTE_PERMISSION");
                    eom_subtasks.add(new EOMSubTask(l_seq_no, l_task_no, l_sub_task_no, l_short_descript,
                            l_descript, l_server, l_execute_script, l_check_script,
                            l_expected_time, l_status, l_comment, l_period,
                            attach_file, l_expected_row_total, l_script_type, l_remark,l_execute_permission));
                }
                rs.close();
                calstatement.close();
            } catch (SQLException ex_1) {
                System.out.println("Error when execute list_all_subtask~" + ex_1.getMessage());
            } finally {
                conn.close();
                return eom_subtasks;
            }
        } catch (Exception ex_2) {
            System.err.println("EOMTaskHelpDao.list_all_subtask-->" + ex_2.getMessage());
            return null;
        }
    }

    public List<ListValue> list_all_module() {
        List<ListValue> modules = new ArrayList<>();
//        modules.add(new ListValue("CREDIT", "Báo cáo tín dụng"));
//        modules.add(new ListValue("INDICATOR", "Chỉ tiêu đánh giá chất lượng tín dụng"));

        String strStoreproce
                = "{call crd_sync_task.p_list_module(?)}";
        try {
//            if (conn == null)
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
            ResultSet rs;
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.registerOutParameter(1, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(1);
                String l_mod_cd, l_description;
                while (rs.next()) {
                    l_mod_cd = rs.getString("MOD_CD");
                    l_description = rs.getString("DESCRIPTION");
                    modules.add(new ListValue(l_mod_cd, l_description));
                }
                rs.close();
                calstatement.close();
            } catch (SQLException ex_1) {
                System.out.println("Error when execute list_all_subtask~" + ex_1.getMessage());
            } finally {
                conn.close();
                return modules;
            }
        } catch (Exception ex_2) {
            System.err.println("EOMTaskHelpDao.list_all_module-->" + ex_2.getMessage());
            return null;
        }
    }

    public List<ListValue> list_all_period(String pv_mod_cd) {
        List<ListValue> periods = new ArrayList<>();
//        periods.add(new ListValue("W", "Kỳ tuần"));
//        periods.add(new ListValue("M", "Kỳ tháng"));
        String strStoreproce
                = "{call crd_sync_task.p_list_period(? , ?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            ResultSet rs;
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_mod_cd);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(2);
                String l_KEY, l_description;
                while (rs.next()) {
                    l_KEY = rs.getString("KEY");
                    l_description = rs.getString("DESCRIPT");
                    periods.add(new ListValue(l_KEY, l_description));
                }
                rs.close();
                calstatement.close();
            } catch (SQLException ex_1) {
                System.out.println("Error when execute list_all_subtask~" + ex_1.getMessage());
            } finally {
                conn.close();
                return periods;
            }
        } catch (Exception ex_2) {
            System.err.println("EOMTaskHelpDao.list_all_module-->" + ex_2.getMessage());
            return null;
        }
    }

    public EOMSubTask get_subtask_detail(String sub_task, String report_dt, String period) {
        String strStoreproce
                = "{call crd_sync_task.p_view_subtask_detail(?,?,?,?)}";
        EOMSubTask eom_subtask = new EOMSubTask();
        try {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            ResultSet rs;

            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, sub_task);
                calstatement.setString(2, report_dt);
                calstatement.setString(3, period);
                calstatement.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                rs = (ResultSet) calstatement.getObject(4);
                int l_seq_no;
                String l_task_no, l_short_descript, l_server, l_execute_script, l_check_script, l_expected_time,
                        l_descript, l_comment, l_status, l_period, l_sub_task_no, l_attach_file;
                Link attach_file;
                int l_expected_row_total;
                String l_script_type;
                String l_remark;
                String l_execute_permission;
                while (rs.next()) {
                    l_seq_no = rs.getInt("SEQ_NO");
                    l_task_no = rs.getString("TASK");
                    l_sub_task_no = rs.getString("SUBTASK");
                    l_short_descript = rs.getString("SHORT_DESCRIPT");
                    l_descript = rs.getString("DESCRIPT");
                    l_server = rs.getString("SERVER");
                    l_execute_script = rs.getString("EXECUTE_SCRIPT");
                    l_check_script = rs.getString("CHECK_SCRIPT");
                    l_expected_time = rs.getString("EXPECTED_TIME");
                    l_status = rs.getString("STATUS");
                    l_comment = rs.getString("COMMENT");
                    l_period = rs.getString("PERIOD");
                    l_attach_file = rs.getString("ATTACH_FILE");
                    if (l_attach_file == null
                            || l_attach_file.isEmpty()) {
                        attach_file = new Link("", "");
                    } else {
                        attach_file = new Link("attach file", Define.M_ROOT + l_attach_file);
                    }
                    l_expected_row_total = rs.getInt("EXPECTED_ROW_TOTAL");
                    l_script_type = rs.getString("SCRIPT_TYPE");
                    l_remark = rs.getString("REMARK");
                    l_execute_permission = rs.getString("EXECUTE_PERMISSION");
                    eom_subtask = new EOMSubTask(l_seq_no, l_task_no, l_sub_task_no, l_short_descript,
                            l_descript, l_server, l_execute_script, l_check_script,
                            l_expected_time, l_status, l_comment, l_period,
                            attach_file, l_expected_row_total, l_script_type, l_remark, l_execute_permission);
                }
                rs.close();
                calstatement.close();
            } catch (SQLException ex_1) {
                System.out.println("Error when execute get_subtask_detail~" + ex_1.getMessage());
            } finally {
                conn.close();
                return eom_subtask;
            }
        } catch (Exception ex_2) {
            System.err.println("EOMTaskHelpDao.get_subtask_detail-->" + ex_2.getMessage());
            return null;
        }
    }

    public String execute_script(String para_subtask, String para_report_date, String para_period,
            String para_script_type, String username, RefObject outmsg, List<EOMTaskLog> tasklogs) {
        String l_status = "E";
        String strStoreproce
                = "{call crd_sync_task.p_execute_subtask(?,?,?,?,?,?,?,?)}";
        try {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            String l_message;

            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, para_subtask);
                calstatement.setString(2, para_report_date);
                calstatement.setString(3, para_period);
                calstatement.setString(4, para_script_type);
                calstatement.setString(5, username);
                calstatement.registerOutParameter(6, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(7, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.registerOutParameter(8, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                l_status = (String) calstatement.getObject(6);
                l_message = (String) calstatement.getObject(7);

                outmsg.setString01(l_message);

                if (para_script_type.equals("2")) {
                    String l_pos_cd, l_error_msg, l_status_2, l_subtask_type, l_subtask_descript;
                    int l_record_total;
                    ResultSet rs;
                    rs = (ResultSet) calstatement.getObject(8);
                    try {
                        while (rs.next()) {
                            l_pos_cd = rs.getString("POS_CD");
                            l_subtask_type = rs.getString("SUBTASK_TYPE");
                            l_subtask_descript = rs.getString("SUBTASK_DESCRIPT");
                            l_error_msg = rs.getString("ERROR_DESC");
                            l_status_2 = rs.getString("T_STATUS");
                            l_record_total = rs.getInt("RECORD_TOTAL");
                            tasklogs.add(new EOMTaskLog(l_pos_cd, l_subtask_type, l_subtask_descript,
                                    l_record_total, l_error_msg, l_status_2));
                        }
                        rs.close();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                calstatement.close();
                return l_status;
            } catch (SQLException ex_1) {
                System.out.println("Error when execute execute_script~" + ex_1.getMessage());
                outmsg.setString01(outmsg.getString01() + "~" + ex_1.getMessage());
                return l_status;
            } finally {
                conn.close();
            }
        } catch (Exception ex_2) {
            System.err.println("EOMTaskHelpDao.execute_script-->" + ex_2.getMessage());
            outmsg.setString01(outmsg.getString01() + "~" + ex_2.getMessage());
            return l_status;
        }
    }

    public String update_subtask(
            String pv_maker_id,
            String pv_sub_task,
            String pv_report_dt,
            String pv_period,
            String pv_status,
            String pv_comment,
            String pv_expected_time,
            int pv_expected_row_total) {
        String lv_message = "";
        String strStoreproce
                = "{call crd_sync_task.p_EDIT_subtask_add_infor(?,?,?,?,?,? ,?,?,?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();
//            }
            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_sub_task);
                calstatement.setString(2, pv_report_dt);
                calstatement.setString(3, pv_period);
                calstatement.setString(4, pv_comment);
                calstatement.setString(5, pv_status);
                calstatement.setString(6, pv_maker_id);
                calstatement.setString(7, pv_expected_time);
                calstatement.setInt(8, pv_expected_row_total);
                calstatement.registerOutParameter(9, oracle.jdbc.OracleTypes.VARCHAR);
                calstatement.execute();
                lv_message = (String) calstatement.getObject(9);
                calstatement.close();
            } catch (Exception ex_1) {
                System.out.println("Error when execute edit_subtask~" + ex_1.getMessage());
            } finally {
                conn.close();
            }
        } catch (Exception ex_2) {
            System.err.println("EOMTaskHelpDao.edit_subtask-->" + ex_2.getMessage());
        }
        return lv_message;
    }

    public ServerInfor getServerInfor(String pv_subtask_ID, String pv_serverID) {
        ServerInfor serverInfor = new ServerInfor();
        String strStoreproce
                = "{call crd_sync_task.p_get_serverinfor(?,?)}";
        try {
//            if (conn == null) {
            daoConnect = new DaoConnect();
            conn = daoConnect.getConnect();

            try (CallableStatement calstatement = conn.prepareCall(strStoreproce,
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                calstatement.setString(1, pv_serverID);
                calstatement.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
                calstatement.execute();
                ResultSet rs;
                rs = (ResultSet) calstatement.getObject(2);
                try {
                    String lv_server_descript, lv_server_address, lv_job_detail;
                    int ln_job_cnt;
                    while (rs.next()) {
                        lv_server_descript = rs.getString("SERVERNAME");
                        lv_server_address = rs.getString("SERVERADDRESS");
                        ln_job_cnt = rs.getInt("NUMOFJOB");
                        lv_job_detail = rs.getString("JOBDETAIL");
                        serverInfor = new ServerInfor(
                                pv_serverID,
                                lv_server_descript,
                                lv_server_address,
                                ln_job_cnt, lv_job_detail);
                    }
                    rs.close();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
                calstatement.close();
            } catch (Exception ex_1) {
                System.out.println("Error when execute edit_subtask~" + ex_1.getMessage());
            } finally {
                conn.close();
            }
        } catch (Exception ex_2) {
            System.err.println("EOMTaskHelpDao.getServerInfor-->" + ex_2.getMessage());
        }
        return serverInfor;
    }
}
