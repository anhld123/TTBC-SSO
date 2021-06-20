<%-- 
    Document   : phanquyenchitieu_xa
    Created on : Oct 4, 2016, 2:25:50 PM
    Author     : BAOANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<!DOCTYPE html>
<style>
    #container{
        width: 100%;
        height: 100%;
        border: 0px solid;
        padding-left: 0px;        
        /*color: #FFE6B0*/
    }

    #containTree{
        width: 29%;
        border-left: 1px solid;
        border-right: 1px solid;
        height: 450px;
        float: right;
        overflow: scroll;
        border: 1px solid;
    }
    #navParam{
        height: 450px;
        width: 70%;
        padding:0px;
        padding-left: 5px;
        padding-bottom: 0px;
        padding-top: 0px;
        float: right;
        overflow: scroll;
        border: 1px solid;                
    }

    th{
        background-color: #DCDCDC;
        border-color: #999;
    }
    td{
        border-color: #999;
    }
    table.editDelete{
        border-collapse: collapse;
        width: 95%;
        border-color: #999;
    }
    table.editDelete tr:hover{
        background-color:#FFE47A;
        cursor: pointer;
    }

    table{
        border-style: solid;
        border-collapse: collapse;
        /*width: 100%;*/
        /*line-height: 19px;*/
    }
    .tbhead th{
        background-color: #5e5e55;
        font-weight: bold;
        color: #fff;
        text-align: center;
        padding: 5px;
    }
    .cscontent td{
        padding-left:5px;
        padding-top:5px;
        padding-bottom: 5px;
    }
    .tblmain tr td{
        font-weight: bold;
        color: #018c3b;
    }

    /*    input{
            border: 0px;
        }*/

    .BOLD 
    {
        font-weight: bold;
        font-size: 13px;
        /*width: 95%;*/
    }

    .ITALIC 
    {
        font-style: italic;
        font-size: 12px;
        /*width: 95%;*/
    }

    .BOLD a
    {
        font-weight: bold;
        font-size: 13px;
        width: 95%;
    }

    .ITALIC a
    {
        font-style: italic;
        font-size: 12px;
        width: 95%;
    }

    /*    input[type="text"]
        {
            width: 95%;
        }*/

    /*    input[type="button"]
        {
            margin-left: 3px;
        }*/
    /*
        .parameter{
            border: 1px solid black;
            width: 50%;
        }*/

    #posCD, #namBc, #maCn, #userId{
        width: 70px;
    }

    a.linkKh{
        color: #116600;
        text-decoration: none;            
    }
    a.linkKh:hover
    {
        color: #5494ea;
        text-decoration: underline;
    }
    a.linkKh:visited
    {
        color: #ab59a6;

    }
</style>
<script>
    $(document).ready(function () {
        $("#allCheck").change(function () {
            $(".checkbox1").prop('checked', $(this).prop("checked"));
        });
        $("#CheckChitieu").change(function () {
            $(".CheckChitieu1").prop('checked', $(this).prop("checked"));
        });
    });

    function LoadPGXa_Chitieu(ma_xa)
    {
        try
        {
//            alert(ma_xa);
            var ht1 = screen.availHeight - 100;
            var wt1 = 600;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 10;
            //tungnv sua phan nay de lau dong nam dua vao
            var namBc = $("#namBc").val();
            var loai_nv = $("#idloai_nv").val();
//                window.open("fullname", "IMS_REPORTS",);
//                alert(namBc);
            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
            var resize = window.open("PhanquyenXa_CT.action?donvi=" + ma_xa + "&loai_nv=" + loai_nv, "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

            var ht = screen.availHeight;
            var wt = screen.availWidth;
//            var resize = window.open("PhanquyenXa_CT.action?donvi=" + ma_xa + "&loai_nv=" + loai_nv + "&vbsprandom=" + Math.random(), "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                resize.resizeBy(wt, ht);
            } else {
                resize.resizeTo(wt, ht);
            }
            resize.focus();

            window.history.back();
        } catch (e) {
            alert(e.toString());
        }
    }

    function LoadPGChitieu_Xa(ma_chitieu)
    {
        try
        {
//            alert(ma_chitieu);
            var ht1 = screen.availHeight - 100;
            var wt1 = 600;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 10;
            //tungnv sua phan nay de lau dong nam dua vao
            var namBc = $("#namBc").val();
            var loai_nv = $("#idloai_nv").val();
//                window.open("fullname", "IMS_REPORTS",);
//                alert(namBc);
            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
            var resize = window.open("PhanquyenCT_xa.action?ma_chitieu=" + ma_chitieu + "&loai_nv=" + loai_nv, "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

            var ht = screen.availHeight;
            var wt = screen.availWidth;
//            var resize = window.open("PhanquyenCT_xa.action?ma_chitieu=" + ma_chitieu + "&loai_nv=" + loai_nv + "&vbsprandom=" + Math.random(), "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                resize.resizeBy(wt, ht);
            } else {
                resize.resizeTo(wt, ht);
            }
            resize.focus();

            window.history.back();

        } catch (e) {
            alert(e.toString());}
    }
</script>
<table border="0">
    <tr >
        <td style="width: 300px; font-size: 14px; color: #18ab29; font-weight: bold">
            <!--<label id="idtitle" style="font-size: 14px; color: #18ab29; font-weight: bold">-->
            Phân quyền chỉ tiêu kế hoạch tín dụng xã
            <!--</label>-->
        </td>
        <td style="width: 300px">
            <div id="message_suc_err"></div>
        </td>
        <td style="width: 100px">
            <s:url id="savePQChitieu" action="savePQChitieu.action"/>
            <sj:submit id="idsaveAdd" formIds="idchitieuxa" value="Lưu phân quyền"
                       targets="message_suc_err" indicator="loadingImage_next" href="%{savePQChitieu}" onBeforeTopics="before-next" 
                       onCompleteTopics="after-next" cssStyle="float: right;"/>
        </td>
    </tr></table>


<p></p>
<div id="container">
    <div id="navParam">
        <table border="1px" id="tableKhnv" class="tableKhnv">
            <tr class="tbhead">
                <th><s:checkbox id ="CheckChitieu" name="CheckChitieu" theme="simple"/></th>
                <th>Hiển thị</th>
                <th>Tên chỉ tiêu</th>
                <th style="width: 45px">PQ Xã</th>
            </tr>

            <s:iterator value="lstChitieu" var="modelView" status="rowstatus">
                <tr class="cscontent">
                    <td class="<s:property value='KH_FONTWEIGHT'/>">
                        <s:checkbox id ="%{#rowstatus.index}" theme="simple" cssClass="CheckChitieu1" name="ma_ct" value="true" fieldValue="%{KH_MA_CT}"/>
                    </td>
                    <td  class="<s:property value='KH_FONTWEIGHT'/>">
                        <s:property  value="KH_STT_HT" />
                    </td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>">
                        <s:property  value="KH_CHI_TIEU" />
                    </td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>">
                        <a href="javascript:LoadPGChitieu_Xa('<s:property value="KH_MA_CT"/>')" class="linkKh">PQ Xã</a>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </div>
    <div id="containTree">
        <table border="1" class="editDelete">
            <tr>
                <th><s:checkbox id ="allCheck" name="allCheck" theme="simple"/></th>
                <th>Mã xã</th>
                <th>Tên xã</th>
                <th>PQ Chỉ tiêu</th>
            </tr>
            <s:iterator value="lstDSDonvi" var="modelView" status="rowstatus">
                <tr>
                    <td>
                        <s:checkbox id ="%{#rowstatus.index}" theme="simple" cssClass="checkbox1" name="ma_donvi" fieldValue="%{strChildCd}"/>
                    </td>
                    <td>
                        <s:property  value="strChildCd" />
                    </td>
                    <td>
                        <s:property  value="strChildDesc" />
                    </td>
                    <td>

                        <a href="javascript:LoadPGXa_Chitieu('<s:property value="strChildCd"/>')" class="linkKh">Chỉ tiêu</a>

                    </td>
                    </td>
                </tr>
            </s:iterator>
        </table>
    </div>
</div>