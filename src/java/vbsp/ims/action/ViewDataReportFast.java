/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;

public class ViewDataReportFast extends ActionSupport {
     List lstcolumn= new ArrayList();
    List lstselectcolumn = new ArrayList();
    String column;
    String selectcolumn;
    String module_id;
    String message;
     
    private static final long serialVersionUID = -7895258309088641394L;

    //@Action(value = "/ajax1", results = { @Result(location = "ajax1.jsp", name = "success") })
    public String execute() throws Exception {
        //System.err.println("Vao execute trong ham view " +column +"   "+selectcolumn+"  module_id  "+module_id);
	return SUCCESS;
    }

    public List getLstcolumn() {
        return lstcolumn;
    }

    public void setLstcolumn(List lstcolumn) {
        this.lstcolumn = lstcolumn;
    }

    public List getLstselectcolumn() {
        return lstselectcolumn;
    }

    public void setLstselectcolumn(List lstselectcolumn) {
        this.lstselectcolumn = lstselectcolumn;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getSelectcolumn() {
        return selectcolumn;
    }

    public void setSelectcolumn(String selectcolumn) {
        this.selectcolumn = selectcolumn;
    }

    public String getModule_id() {
        return module_id;
    }

    public void setModule_id(String module_id) {
        this.module_id = module_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
