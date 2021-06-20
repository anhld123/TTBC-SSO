<%-- 
    Document   : chinhsuachitieu_xa
    Created on : Sep 22, 2016, 8:32:45 AM
    Author     : BAOANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thêm, chỉnh sửa chỉ tiêu tín dụng xã</title>
        <sj:head/>
        <style>
            *{
                font: 14px Arial, Helvetica, sans-serif;
            }
            #menuBcttv_para{
                width: 100%;
                height: 25px;                
                border: 1px solid; 
                padding-bottom: 0px;
                padding-top: 0px;
            }

            #containBcttv_para{
                width: 100%;
                min-height:390px;
                border: 1px solid;
                margin-top: 2px;
            }

            .metroButtonStyle {
                font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
                display: block;
                color: rgb(255, 255, 255);
                text-decoration: none;
                text-align: center;
                width: 90px;
                height: 26px;
                padding: 5px;
                margin: 5px 0px 0px 5px;
                font-size: 12px;
                background: none repeat scroll 0 0 #808080;
                color: #FFF;
                border: 0px none;
                border-radius: 1px 1px 1px 1px;
                outline: 0px none;
            }
            .metroButtonStyle:hover {
                background: #018c3b;
            }
            .metroButtonStyle:active {
                background: #DCDCDC;
            }

            #container{
                width: 100%;
                height: 100%;
                border: 0px solid;
                padding-left: 0px;        
                /*color: #FFE6B0*/
            }

            #containTree{
                width: 19%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: right;
                overflow: scroll;
            }
            #navParam{
                height: 450px;
                width: 80%;
                padding:0px;
                padding-left: 5px;
                padding-bottom: 0px;
                padding-top: 0px;
                float: right;
                /*margin:5px;*/
                /*border-radius: 10px; //bo tron goc*/
                border: 1px solid;                
                /*                height: 50px;
                                border: 1px solid;  
                                border-radius: 10px; //bo tron goc
                                -moz-border-radius: 10px;
                                margin:5px;
                                padding:5px;*/
            }
            #containParm{
                width: 100%;
                height: 100%;
                padding-left: 5px;
                float: bottom;
                /*overflow: scroll;*/
            }
            #chitieu
            {
                height: 30px;
                /*width: 80%;*/
                padding:0px;
                padding-left: 5px;
                /*padding-bottom: 5px;*/
                padding-top: 5px;
                align-content: center;
                border: 0px solid; 
            }
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 12px;
            }

            .report_group_form{
                width: 100%;
            }

            #message_suc_err
            {
                height: 30px;
                border: 0px solid;
                padding-bottom: 0px;
                padding-top: 0px;
            }
        </style>
        <script language="javascript">
            $(document).ready(function () {
//                  alert('goi ham clear');
                $("#navParam2").empty();
            });
            function onclear()
            {
//                alert('goi ham clear');
                $("#navParam2").empty();
                $("#navParam2").text('');
            }
                $.subscribe('onClickDel', function () {
        var r = confirm("Bạn có thật sự muốn xóa chỉ tiêu này không ? OK : Đồng ý, Cancel : Hủy bỏ");
        if (r == true) {
            return true;
        } else
            return false;
    });
        </script>
    </head>
    <body>
        <label id="idtitle" style="font-size: 14px; color: #18ab29; font-weight: bold">
            Chỉnh sửa chỉ tiêu kế hoạch tín dụng xã
        </label>
        <hr>
        <div id="container" >
            <s:form id="idchitieuxa" action="chitieuxa" theme="simple">
                
                <div id="containParm" >
                    <div id="chitieu"  align="center">
                        <table border="0">
                            <tr>
                                <td style="width: 100px">
                                    Loại chỉ tiêu:
                                </td>
                                <td style="width: 160px">
                                    <s:select  
                                        id="idloai_nv"
                                        name="loai_nv"
                                        list="#{'XDKH_XA':'Xây dựng kế hoạch','GIAO_DC_PGD':'Giao, điều chỉnh kế hoạch'}" 
                                        value="GIAO_DC_PGD"
                                        cssStyle="font-weight: bold;width: 150px; vertical-align: middle;">                    
                                    </s:select>
                                </td>
                                <s:url id="addChitieu" action="addChitieuxa" />
                                <td style="width: 100px">
                                    <!--<a href="#" >Thêm chỉ tiêu</a>-->
                                    <sj:submit id="idsubmitaddct" formIds="idchitieuxa" value="Thêm chỉ tiêu" onclick="onclear()"
                                               targets="navParam2" indicator="loadingImage_next" href="%{addChitieu}" onBeforeTopics="before-next" 
                                               onCompleteTopics="after-next"/>
                                </td>
                                <s:url id="loadEditChitieuxa" action="loadEditChitieuxa"/>
                                <td style="width: 120px">
                                    <!--<a href="#">Sửa/xóa chỉ tiêu</a>-->
                                    <sj:submit id="idsubmiteditct" formIds="idchitieuxa" value="Sửa/xóa chỉ tiêu"  onclick="onclear()"
                                               targets="navParam2" indicator="loadingImage_next" href="%{loadEditChitieuxa}" onBeforeTopics="before-next" 
                                               onCompleteTopics="after-next"/>
                                </td>
                                <s:url id="idphanquyenChitieu" action="phanquyenChitieu"/>
                                <td style="width: 120px">
                                    <!--<a href="#">Sửa/xóa chỉ tiêu</a>-->
                                    <sj:submit id="idpqChitieu" formIds="idchitieuxa" value="Phân quyền chỉ tiêu"  onclick="onclear()"
                                               targets="navParam2" indicator="loadingImage_next" href="%{idphanquyenChitieu}" onBeforeTopics="before-next" 
                                               onCompleteTopics="after-next"/>
                                </td>
                                <td style="width: 150px"> <img id="loadingImage_next" src="img/loading.gif" style="display:none"/></td>
                            </tr>
                        </table>
                    </div>
                    <hr>
                    <div id="navParam2" align="center">
                    </div>
                </div>
            </s:form>

        </div>
    </body>
</html>
