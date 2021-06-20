<%-- 
    Document   : risk_detail_customer
    Created on : May 27, 2015, 8:50:46 AM
    Author     : BAOANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <%--<sx:head/>--%>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>

        <style>      
            *{
                font: 14px Arial, Helvetica, sans-serif;
            }

            #divChiTieu{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width:95%; 
                min-height: 100%; 
                margin: 0px auto 10px auto; 
                padding: 10px;
                background-color: #E2E8C9;
            }

            #idTitle{
                font-family: Verdana,Arial,Tahoma,Helvetica;
                font-size: 13pt;
                font-weight: bold;
                color: #116600;
            }

            #divSave{
                text-align: right;
            }

            table{
                border-collapse: collapse;
                width: 100%;
                line-height: 19px;
            }
            .tbhead th{
                background-color: #DCDCDC;
                text-align: center;
                font-weight: bold;
                padding: 2px;
            }
            .cscontent td{
                background-color: white;
                /*text-align: center;*/
                padding-left:1px;
                padding-top:1px;
                padding-bottom: 1px;
            }

            /*                        input{
                                        border: 0px;
                                    }
                        
                                    input[type="text"]
                                    {
                                        width: 95%;
                                    }*/

            .Commune{
                width: 30px;
            }
            .Code{
                border: 0px;
                width: 60px;
            }
             .Desc{
                border: 0px;
                /*width: 600px;*/
            }
            input[type="text"]
            {
                width: 100%;
                /*border: 0px;*/
                /*border: 1px solid;*/
                /*color: #000000*/
                /*border-color: #18ab29;*/
                /*background: #F9F9F9;*/
                /*color:#666666;*/
            }
            input[type=text]:focus, textarea:focus {
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                padding: 3px 0px 3px 3px;
                margin: 5px 1px 3px 0px;
                border: 1px solid rgba(81, 203, 238, 1);
            }
        </style>
        <script>
            $(document).ready(function () {
                //Format cac truong input.number can le phai
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});

                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);

                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 1);  //Phan thap phan
//                $('.maPGD').css({"text-align": "left"});
                $('.Commune').css({"text-align": "right"});
                $(".Commune").css({"width": "80px"});
                $('.Code').css({"text-align": "center"});
                $(".Code").css({"width": "60px"});
                $('.Desc').css({"text-align": "left"});
                $(".Desc").css({"width": "650px"});
//                $(".maPGD").css({"width": "140px"});
//                    var element;
//                    var count=1;
//                    for (var i = 0; i < document.forms["formInputCommune"].elements.length; i++)
//                    {
//                        element = document.forms["formInputCommune"].elements[i];
//                        if (element.value.length > 0 && element.type == 'text')
//                        {
//                            var nameinput = element.id;
//                            if(nameinput=='sSubcode')
//                                onclick_disable(element.value);
////                                alert('sCode='+element.value.substring(0,3)+' sSubcode='+element.value)
//                        }
//                    }
//                onclick_disable();
            });

            function ValidateNmber(obj, evt) {
            var charCode = (evt.which) ? evt.which : event.keyCode
            /*First decimal allowed*/
            if (charCode == 46 && isDecimal) {
                isDecimal = false;
                return true;
            }
            /*Dont allow more*/
            else if (charCode == 46 && !isDecimal) {
                return false;
            }
            if (charCode > 31 && (charCode < 48 || charCode > 57) || !isSubmit) {
                return false;
            }
            else {
                isDecimal = true;
                return true;
            }
        }
            
            //Disable enter key form submit
            function stopRKey(evt) {
                var evt = (evt) ? evt : ((event) ? event : null);
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
                if ((evt.keyCode == 13) && (node.type == "text")) {
                    return false;
                }
            }

            //Disable enter key form submit            
            document.onkeypress = stopRKey;

            function isNumber(value)
            {
                if (value == null)
                {
                    alert('Bạn phải nhập dữ liệu cho trường này');
//                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');

                    return false;
                }
                value = value.replace(/,/g, "");
//            alert(value.replace(/,/g, ""));
                var result = true; //Luu ket qua kiem tra kieu so co dung khong
                //Kiem tra xem co nhap kieu so khong
                if (isNaN(parseFloat(value))) {
                    result = false;
                    //Neu nguoi dung khong nhap dung kieu du lieu
                    //Dua ra canh bao
                    alert('Bạn nhập không đúng kiểu số xin nhập lại dữ liệu');
//                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                    focus();
                    return false;
                }
                else {
                    //Neu la kieu so --> Kiem tra xem kieu nhap co > 0 
//                if (parseFloat(value) < 0) {
//                    result = false;
//                    alert('Bạn không được nhập giá trị < 0!');
//                    //Dua ra canh bao
////                        $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn không được nhập giá trị < 0!');
//                    focus();
//                    return false;
//                }

                    //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                    if (parseFloat(value) > 9999999999) {
                        result = false;
                        alert('Giá trị bạn nhập vượt quá giới hạn!');
                        //Dua ra canh bao
//                        $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Giá trị bạn nhập vượt quá giới hạn!');
                        focus();
                        return false;
                    }
                }
            }
        </script>
        <script>

    function onclick_disable( subcode)
            {
//                alert('code='+code+' subcode='+subcode+' size='+document.forms["formInputCommune"].elements.length);
                try {
                    var element;
                    //duyet tung dieu khien tren form
                    for (var i = 0; i < document.forms["formInputCommune"].elements.length; i++)
                    {
                        element = document.forms["formInputCommune"].elements[i];
                        //neu la dang text input thi xu ly
                        if (element.value.length > 0 && element.type == 'text')
                        {
                            var nameinput = element.id;
                            
                            console.log('element.id='+element.id);
//                             alert(nameinput);
                            //neu code duoc tim thay trong id va gia tri khac khong thi de readonly cac dieu khien con lai co cung code
                            if(nameinput.toString().match(subcode.toString().substr(0,3))&& (nameinput!='value['+subcode+']' && nameinput!='mark['+subcode+']'))
                            {
//                               alert(document.getElementById('lstCommune['+subcode+']').value);
//                                alert('disable '+document.getElementById(nameinput));
                                if(document.getElementById('value['+subcode+']').value!=0 ||document.getElementById('mark['+subcode+']').value!='0.0')
                                {
//                                    element.style.backgroundColor = 'red';
                                    element.style.backgroundColor = 'yellow';
                                    element.setAttribute("readonly", true);
                                 }
                                 else
                                 {
                                     //truong hop ma dieu chinh ve 0 thi se remove het cac readonly
                                    element.style.backgroundColor = 'white';
                                    element.removeAttribute("readonly");
                                 }

                            }
                        }

                    }
                    return true;
                    //Console.log(params);
                } catch (e) {
                    alert('onclick_disable ' + e.description);
                    return false;
                }
            }
            
            
            
            function onload_disable( )
            {
                 try {
                     //khai bao mang cac code 
                   var arr_code = new Array("X01","X07","X06","X04","X10","X08","X09");
//                    var arr_code=["X01","X07","X06","X04","X10","X08","X09"];
                    for(var i=0;i<arr_code.length;i++)
                    {
                        //for cac code tu 1->10
                        for(var j=1;j<10;j++)
                        {
                            try
                            {
                                //neu gia tri cua code >0 thi se goi ham tren de khoi tao form
    //                        alert('value['+arr_code[i]+'0'+j.toString()+']='+document.getElementById('value['+arr_code[i]+'0'+j.toString()+']').value+' -> mark['+arr_code[i]+'0'+j.toString()+']');
                                if(document.getElementById('value['+arr_code[i]+'0'+j.toString()+']').value !=0 || document.getElementById('mark['+arr_code[i]+'0'+j.toString()+']').value!=0)
                                    onclick_disable(arr_code[i]+'0'+j.toString());
                            }
                            catch(e)
                            {
        //                        alert('onclick_disable ' + e.description);
                            }
                    }
                }
                    //Console.log(params);
                } catch (e) {
                    alert('onclick_disable ' + e.description);
                    return false;
                }
            }
</script>
    </head>
    <body >
        <!--<div id="container" style="width: 70%;">-->
        <s:form name="formInputCommune" id="formInputCommune" action="saveInputCommune.action" theme="simple">
            <s:hidden name="report_dt" />
            <s:hidden name="poscd" />
            <div id="divChiTieu" style="">
                <span id="idTitle">Thông tin chi tiết các chỉ tiêu nhập tay</span>
                <hr/>
                <table border="1px" id="tableKhnv" class="tableKhnv">
                    <tr class="tbhead">
                        <th >Mã</th>
                        <th >Mã con</th>
                        <th >Mô tả</th>
                        <th >Điểm chuẩn</th>
                        <th >Giá trị</th>
                        <th >Điểm chấm</th>
                    </tr> 
                    <s:iterator value="lstCommune" var="modelcommune" status="rowstatus">
                        <tr class="cscontent">
                            <td align = "center" >
                                <input type="text" id="sCode"  value="<s:property value='sCode'/>"
                                       name="lstCommune[<s:property  value="%{#rowstatus.index}" />].sCode"   class="Code" 
                                       onfocus="this.select()" readonly="true">
                            </td>
                            <td align = "center">
                                <input type="text" id="sSubcode" value="<s:property value='sSubcode'/>"
                                       name="lstCommune[<s:property  value="%{#rowstatus.index}" />].sSubcode"   class="Code" 
                                       onfocus="this.select()" readonly="true">
                            </td>
                            <td align = "left">
                             <input type="text" value="<s:property value='sDescription'/>"
                                       name="lstCommune[<s:property  value="%{#rowstatus.index}" />].sDescription"   class="Desc" 
                                       onfocus="this.select()" readonly="true">
                            </td>
                            <td align = "center">
                                <input type="text" value="<s:property value='sMarkStandard'/>"
                               name="lstCommune[<s:property  value="%{#rowstatus.index}" />].sMarkStandard"   class="Code" 
                               onfocus="this.select()" readonly="true">
                            </td>
                            <td class="Commune">
                                <input type="text" value="<s:property value='bValue'/>"  id="value[<s:property  value="sSubcode" />]"
                                       onchange="onclick_disable('<s:property value='sSubcode'/>')"
                                       name="lstCommune[<s:property  value="%{#rowstatus.index}" />].bValue"  class="Commune number"  
                                       onfocus="this.select()" onblur="if (this.value == '') {this.value = 0};isNumber(this.value)" />
                            </td>
                            <td class="Commune"><input type="text" value="<s:property value='bMark'/>" class="Commune number" 
                                                       name="lstCommune[<s:property  value="%{#rowstatus.index}" />].bMark"  id="mark[<s:property  value="sSubcode" />]"
                                                       onkeyup="onclick_disable('<s:property value='sSubcode'/>')"
                                                       onfocus="this.select()" onblur="if (this.value == '') {this.value = 0};isNumber(this.value)"/>
                            </td>
                        </tr>

                    </s:iterator>
                        
                    <sj:submit id="idSave" name="nameSave" targets="divMessage" cssClass="metroButtonStyle" value="Lưu dữ liệu"  cssStyle="display: none"></sj:submit>
                </table>
            </div>
        </s:form>
        <!--</div>-->
    </body>
    <script>
        onload_disable();
    </script>
</html>

