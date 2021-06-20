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
                width: 84%;
                height: 450px;
                padding-left: 5px;
                /*float: left;*/
                overflow: scroll;
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
    </head>
    <body>
        <label id="idtitle" style="font-size: 14px; color: #18ab29; font-weight: bold">
            Chỉnh sửa chỉ tiêu kế hoạch tín dụng xã
        </label>
        <hr>
        <div id="container" >
            <s:form id="idchitieuxa" action="chitieuxa" theme="simple">
                <div id="navParam" >
                    <div id="chitieu"  align="center">
                        <table border="0">
                            <tr>
                                <s:url id="load_nv_ct" action="load_nv_ct.action"/>
                                <td style="width: 100px">
                                    Loại chỉ tiêu:
                                </td>
                                <td style="width: 160px">
                                    <sj:select  
                                        href="%{load_nv_ct}"
                                        onChangeTopics="loadchitieucha"
                                        id="idloai_ct"
                                        name="loai_nv"
                                        list="lstLoainv" 
                                        listKey="sKey"
                                        listValue="sDesc" 
                                        cssStyle="font-weight: bold;width: 150px; vertical-align: middle;">                    
                                    </sj:select>
                                </td>
                                <td style="width: 100px">
                                    Chỉ tiêu cha:
                                </td>
                                <td style="width: 220px">
                                    <sj:select    href="%{load_nv_ct}"
                                        id="iddm_chitieu"
                                        reloadTopics="loadchitieucha"
                                        formIds="idchitieuxa"
                                        name="ma_chitieu_cha"
                                        list="lstDmChitieu" 
                                        listKey="sKey"
                                        listValue="sDesc" 
                                        headerKey="-1"
                                        headerValue="-- Chọn chỉ tiêu gốc --"
                                        cssStyle="font-weight: bold;width: 200px; vertical-align: middle;">                    
                                    </sj:select>
                                </td>
                                <s:url id="addChitieu" action="addChitieuxa" />
                                <td style="width: 100px">
                                    <!--<a href="#" >Thêm chỉ tiêu</a>-->
                                    <sj:submit id="idsubmitaddct" formIds="" value="Thêm chỉ tiêu"
                                               targets="navParam2" indicator="loadingImage_next" href="%{addChitieu}" onBeforeTopics="before-next" 
                                               onCompleteTopics="after-next"/>
                                </td>
                                <s:url id="loadEditChitieuxa" action="loadEditChitieuxa"/>
                                <td style="width: 120px">
                                    <!--<a href="#">Sửa/xóa chỉ tiêu</a>-->
                                    <sj:submit id="idsubmiteditct" formIds="" value="Sửa/xóa chỉ tiêu"
                                               targets="navParam2" indicator="loadingImage_next" href="%{loadEditChitieuxa}" onBeforeTopics="before-next" 
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
                <div id="containTree">
                    <sjt:tree
                        name="ma_donvi"
                        id="treeDynamicCheckboxes"
                        jstreetheme="apple"
                        rootNode="nodes_pos"
                        childCollectionProperty="children"
                        nodeTitleProperty="title"
                        nodeIdProperty="id"
                        openAllOnLoad="true"
                        checkbox="true"
                        showThemeDots="false"
                        showThemeIcons="true" 
                        />
                </div>
            </s:form>

        </div>
    </body>
</html>
