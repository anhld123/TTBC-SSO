<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Danh mục Kểm tra kiểm toán nội bộ</title>
        <sj:head/>
    </head>
    <script>
        $('.textlink').click(function(){
            alert( $('.textlink').index(this) );
        });
        function Callbaocao(fullname,linktext) {
            var thamso = "?quyBc=" + document.getElementById("cboquybc").value + "&namBc=" + document.getElementById("cbonam").value+ "&textlink=" + linktext + "&action=" + fullname;
            var ht = screen.availHeight;
            var wt = screen.availWidth;
            var resize = window.open(fullname + thamso+"&vbsprandom="+Math.random(), "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                resize.resizeBy(wt, ht);
            } else {
                resize.resizeTo(wt, ht);
            }
            resize.focus();
        }
    </script>
    <style type="text/css">
        *{
            margin: 0px;
        }
        table{
            border-style: solid;
            border-collapse: collapse;
            width: 100%;
            font: 12px Arial, Helvetica, sans-serif; 
            line-height: 19px;
        }
        .tbhead{
            background-color: #5e5e55;
            font-weight: bold;
            color: #fff;
            text-align: center;
        }
        .cscontent td{
            padding-left:5px;
        }
        .cscontent:hover{
            background-color: #ffff99;
        }
    </style>
    <body>
        <form name="parentForm">
            <div style="margin: 7px 7px 7px 10px;">
                <table border="0" cellspacing="0" cellpading="0" height="100%" width="100%">
                    <tr>
                        <td>
                            <b>Quý báo cáo:</b>
                            <select name="cboquybc" id="cboquybc">
                                <option value="1">Quý I</option>
                                <option value="2">Quý II</option>
                                <option value="3">Quý III</option>
                                <option value="4">Quý IV</option>
                            </select>
                            &nbsp;&nbsp;
                            <b>Năm báo cáo:</b>
                            <!--Tungnv sua: 26-03-2015 <option duoc can trinh trong procedure-->
                           <select name="cbonam" id="cbonam">
                                <s:iterator value="lstYearReport" >
                                    <s:property escape="false"></s:property> 
                                </s:iterator>   
                                
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table border="1px" id="tblHead">
                                <tr class="tbhead">
                                    <td>Mã KTNB</td>
                                    <td>Tên mẫu - Nhấn vào tên mẫu để nhập liệu cho biểu KTNB</td>
                                    <td>Kỳ báo cáo</td>
                                </tr>
                                <s:iterator value="DSKTNB">
                                    <tr class="cscontent">
                                        <td><s:property value="TENVT"/></td>
                                        <td>
                                            <a href="javascript:Callbaocao('<s:property value="LINKBC"/>','<s:property value="MOTA"/>')" style="text-decoration:none;" class="textlink"><s:property value="MOTA"/></a>
                                        </td>
                                        <td><s:property value="KYBC"/><input type="hidden" value="<s:property value="KYBC"/>" id="txtkybaocao"/></td>
                                    </tr>
                                </s:iterator>
                            </table>
                    </tr>
                </table>
            </div>
        </form>

        <script>
            //CuongBM: 05Oct14
            //Desc: Xu truong hop dat gia tri mac dich cho combox Quy (Quater), la quy hien tai
            //      Cac bao cao Quy phai co id la PARA_QUY           
            var date = new Date();
            var year = date.getFullYear(); //nam
            var quarter = Math.floor(date.getMonth() / 3) + 1; //quy

            //Gan quy mac dinh
            $("#cboquybc").val(quarter);
        </script>
    </body>
</html>
