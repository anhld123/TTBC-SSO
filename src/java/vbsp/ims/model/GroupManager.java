/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.GroupDao;

/**
 *
 * @author Trung
 */
public class GroupManager {

    //--------------------------------------------------------------------------    
    private static int totalCount = 0;
    private static List<Group> groups;
    private static GroupDao groupDao;

    private final String user_name;
    private final int report_grade;

    public GroupManager(String user_name, int report_grade) {
        // Khoi tao ket noi moi
        groupDao = null;
        this.user_name = user_name;
        this.report_grade = report_grade;
        if (groupDao == null) {
            groupDao = new GroupDao();
        }
        System.err.println("Lay thong tin to nhom:  " + user_name + "~" + report_grade);
        groups = groupDao.getGroupList(user_name, report_grade);
        totalCount = groups.size();
    }

    public List<Group> find(int from, int to) {
        ArrayList<Group> findList = new ArrayList<>();
        to = (to > totalCount) ? totalCount : to;
        from = (from > to || from < 0) ? 0 : from;
        for (int i = from; i < to; i++) {
            findList.add(groups.get(i));
        }
        return findList;
    }

    public List<Group> search(String pv_searchOper, String pv_searchfield, String pv_key) {
        List<Group> search_group = new ArrayList<>();
        if (groups == null) {
            if (groupDao == null) {
                groupDao = new GroupDao();
            }
            groups = groupDao.getGroupList(user_name, report_grade);
        }
        if (pv_key == null || pv_key.isEmpty()) {
            groups = groupDao.getGroupList(user_name, report_grade);
            return groups;
        } else {
            switch (pv_searchfield) {
                case "group_id":
                    for (Group group : groups) {
                        if (group.getGroup_id().contains(pv_key)) {
                            search_group.add(group);
                        }
                    }
                    break;
                case "commune_id":
                    for (Group group : groups) {
                        if (group.getCommune_id().contains(pv_key)) {
                            search_group.add(group);
                        }
                    }
                    break;
                case "mass_org":
                    for (Group group : groups) {
                        if (group.getMass_org().contains(pv_key)) {
                            search_group.add(group);
                        }
                    }
                    break;
                case "leader_group_name":
                    for (Group group : groups) {
                        if (group.getLeader_group_name().toLowerCase().contains(pv_key.toLowerCase())) {
                            search_group.add(group);
                        }
                    }
                    break;
            }
            groups = search_group;
            return search_group;

        }
    }

    public boolean update_group(Group group) {
        if (groupDao == null)
            groupDao = new GroupDao();
        return groupDao.updateGroup_tmp(user_name, report_grade, group);
    }

    public int commit_session_edit(String pv_pos_cd) {
        if (groupDao == null)
            groupDao = new GroupDao();
        return groupDao.updateGroup_table(user_name, report_grade, pv_pos_cd);
    }

    public List<Group> getGroupList_sync(String pv_pos_cd){
         List<Group> sync_groups;
        if (groupDao == null)
            groupDao = new GroupDao();
        sync_groups = groupDao.getGroupList_sync(user_name,pv_pos_cd);
        return sync_groups;
    }
    
    public Group getGroup(int index) {
        return groups.get(index);
    }

    public int getTotalCount() {
        return totalCount;
    }
}
