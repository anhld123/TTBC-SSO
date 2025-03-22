<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        
        <script>            
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
                        
            function UnlockLock(branchCode, status) {
                let _confirmStatus = confirm("Bạn chắc chắn muốn mở khóa/khóa chi nhánh " + branchCode + "?");
                let _reportDate = $("#ngay_bc_DATE").val();
                if (_confirmStatus) {
                    $.ajax({
                        type: "GET",
                        url: "CDTT_Lock_Change_Status?" + "branchCode=" + branchCode + "&status=" + status+ "&reportDate=" + _reportDate,
                        success: function (res) {
                            alert('Cập nhật thành công!');
                            $("#loadDatatmp").trigger( "click" );
                        },
                        error: function (res) {
                            alert("Có lỗi xảy ra!");
                        }
                    });
                }
            }
        </script>  
        
        <style>                        
           
            /* CSS */
            .unlockBtnStyle {
              background-color: initial;
              background-image: linear-gradient(-180deg, #00D775, #00BD68);
              border-radius: 5px;
              box-shadow: rgba(0, 0, 0, 0.1) 0 2px 4px;
              color: #FFFFFF;
              cursor: pointer;
              display: inline-block;
              font-family: Inter,-apple-system,system-ui,Roboto,"Helvetica Neue",Arial,sans-serif;
              height: 30px;
              width: 80px;
              line-height: 30px;
              outline: 0;
              overflow: hidden;
              padding: 0 5px;
              pointer-events: auto;
              position: relative;
              text-align: center;
              touch-action: manipulation;
              user-select: none;
              -webkit-user-select: none;
              vertical-align: top;
              white-space: nowrap;
              width: 100%;
              z-index: 9;
              border: 0;
            }

            .unlockBtnStyle:hover {
              background: #00bd68;
            }
            
            .lockBtnStyle {
              background-color: initial;
              background-image: linear-gradient(-180deg, #F07A0F, #FF0000);
              border-radius: 5px;
              box-shadow: rgba(0, 0, 0, 0.1) 0 2px 4px;
              color: #FFFFFF;
              cursor: pointer;
              display: inline-block;
              font-family: Inter,-apple-system,system-ui,Roboto,"Helvetica Neue",Arial,sans-serif;
              height: 30px;
              width: 80px;
              line-height: 30px;
              outline: 0;
              overflow: hidden;
              padding: 0 5px;
              pointer-events: auto;
              position: relative;
              text-align: center;
              touch-action: manipulation;
              user-select: none;
              -webkit-user-select: none;
              vertical-align: top;
              white-space: nowrap;
              width: 100%;
              z-index: 9;
              border: 0;
            }

            .lockBtnStyle:hover {
              background: #FF5B5B;
            }

        </style>
    </head>
    <body>
        <s:form id="id_sv_%{khoa_cdtt}" action="SAVE_%{khoa_cdtt}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />"  
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                MỞ KHÓA/KHÓA GỬI DỮ LIỆU
            </div>                                  
                <br/>
                <table border="1" align="center" style="width: 80%;" class="editDelete" id="tablecdtt99">
                    <tr>
                        <th style="width: 100px;" class="TD_5">TT</th>
                        <th class="TD_5">Chi nhánh</th>                          
                        <th style="width: 200px;" class="TD_5">Trạng thái</th>   
                        <th></th>
                    </tr>                      
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                            
                            <tr>  
                                <td>
                                    <input type="text" value="<s:property  value="%{#rowstatus.index+1}" />" class="TEN_KH number" style="text-align: center;" readonly="true"/>
                                </td>
                                <td>
                                    <input type="text"  value="<s:property  value="TEN" />" class="TEN_KH" readonly="true"/>                                    
                                </td>
                                <td>
                                    <input type="text" value="<s:property  value="D2" />" class="TEN_KH" style="text-align: center;" readonly="true"/>
                                </td>    
                                <td style="text-align: center;">
                                    <s:if test="D1.equalsIgnoreCase('0')">
                                        <input type="button" value="Đóng" onclick="UnlockLock('<s:property  value="MA" />','1');" width="100px;" class="lockBtnStyle"/>
                                    </s:if>
                                    <s:else>
                                        <input type="button" value="Mở" onclick="UnlockLock('<s:property  value="MA" />','0');" width="100px;" class="unlockBtnStyle"/>
                                    </s:else>
                                    
                                </td>
                            </tr>                        
                    </s:iterator>
                </table> 
                     
            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
