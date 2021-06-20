<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 02/KNTC</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        
        <style type="text/css">
            *{
                font: 12px Arial, Helvetica, sans-serif;
            }
            table{
                border-style: solid;
                border-collapse: collapse;
                width: 100%;
                line-height: 19px;
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
            }
            .cscontent:hover{
                background-color: #ffff99;
            }
            
            .cscontent:hover input[type="text"]{
                background-color: #ffff99;
            }
            .tblmain tr td{
                font-weight: bold;
                color: #018c3b;
            }
            
            input{
                border: 0px;
            }
            
            .BOLD input[type="text"]
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
            
            .ITALIC input[type="text"]
            {
                font-style: italic;
                font-size: 12px;
                width: 95%;
            }
            
            input[type="text"]
            {
                width: 95%;
            }
            
            input[type="button"]
            {
                margin-left: 3px;
            }
            
            .parameter{
                border: 1px solid black;
                width: 50%;
            }
            
            #posCD, #quyBc, #namBc, #maCn, #userId{
                width: 70px;
            }
        </style>
        
        <script>
            $(document).ready(function(){
                $("#update").click(function () {
                document.getElementById("update").disabled = true;                
                sleep(1000);
                document.getElementById("update").disabled = false; 
            });
                //Format cac truong input.number can le phai
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                
                //CSS truong so thu tu (cot 1) cho width
//                $('#tableKtnb td:nth-child(1),#tableKtnb th:nth-child(1)').css({"width" : "35px"});
                
                //CSS truong so thu tu (cot 1) cho width
//                $('#tableKtnb td:nth-child(2),#tableKtnb th:nth-child(2)').css({"width" : "250px"});
                $(".KT_DV").css({"width" : "50px"});
//                $(".KT_TC_UT").css({"width" : "240px"});                
                
                //An di cac cot chuc nang
                $('.hideColumn').hide();
                
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true,0);  
                
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true,2);  
            });
            
            //Xu ly tinh tong cho tung dong
            function autoEvaluate(){
                //1=2+3+4+5
                $(".KT_TN_TS").eq(0).val(parseFloat($(".KT_TN_TK_NN").eq(0).val()) + 
                        parseFloat($(".KT_TN_TK_MN").eq(0).val()) + parseFloat($(".KT_TN_KT_NN").eq(0).val()) +
                        parseFloat($(".KT_TN_KT_MN").eq(0).val()));
                
                //7=8+9+10+11	
                $(".KT_PL_ND_KN_HC_T").eq(0).val(parseFloat($(".KT_PL_ND_KN_HC_DD").eq(0).val()) + 
                        parseFloat($(".KT_PL_ND_KN_HC_NTS").eq(0).val()) + parseFloat($(".KT_PL_ND_KN_HC_CS").eq(0).val()) +
                        parseFloat($(".KT_PL_ND_KN_HC_CT").eq(0).val()));
                
                //14=15+16+17+18+19
                $(".KT_PL_ND_TC_T").eq(0).val(parseFloat($(".KT_PL_ND_TC_HC").eq(0).val()) + 
                        parseFloat($(".KT_PL_ND_TC_TP").eq(0).val()) + parseFloat($(".KT_PL_ND_TC_TN").eq(0).val()) +
                        parseFloat($(".KT_PL_ND_TC_D").eq(0).val()) + parseFloat($(".KT_PL_ND_TC_K").eq(0).val()));
            }
            
             function doClick(id, e)
            {
//                alert('vao doClick');
                //the purpose of this function is to allow the enter key to 
                //point to the correct button to click.
                var key;

                if (window.event)
                    key = window.event.keyCode;     //IE
                else
                    key = e.which;     //firefox

                if (key == 13)
                {
                    //Get the button the user wants to have clicked
                    e.preventDefault();
                     e.stopPropagation();
                }
            }
            
            function fnResetVal(){
               $(".KT_TN_TS").val('0');
               $(".KT_TN_TK_NN").val('0');
               $(".KT_TN_TK_MN").val('0');
               $(".KT_TN_KT_NN").val('0');
               
               $(".KT_TN_KT_MN").val('0');
               $(".KT_TN_DDK").val('0');
               $(".KT_PL_ND_KN_HC_T").val('0');
               $(".KT_PL_ND_KN_HC_DD").val('');
               
               $(".KT_PL_ND_KN_HC_NTS").val('0');
               $(".KT_PL_ND_KN_HC_CS").val('0');
               $(".KT_PL_ND_KN_HC_CT").val('0');
               $(".KT_PL_ND_KN_TP").val('0');
               
               
                $(".KT_PL_ND_KN_D").val('0');
               $(".KT_PL_ND_TC_T").val('0');
               $(".KT_PL_ND_TC_HC").val('0');
               $(".KT_PL_ND_TC_TP").val('0');
               
               $(".KT_PL_ND_TC_TN").val('0');
               $(".KT_PL_ND_TC_D").val('0');
               $(".KT_PL_ND_TC_K").val('0');
               $(".KT_PL_TQ_HC").val('');
               
               $(".KT_PL_TQ_TP").val('0');
               $(".KT_PL_TQ_D").val('0');
               $(".KT_PL_TT_CGQ").val('0');
               $(".KT_PL_TT_DGQ1").val('0');
               
               
               $(".KT_PL_TT_GDQN").val('0');
               $(".KT_DK").val('0');
               $(".KT_KQ_SVB").val('0');
               $(".KT_KQ_CTQ").val('');
               
               $(".KT_KQ_SCV").val('0');
               $(".KT_KQ_TTQ_KN").val('0');
               $(".KT_KQ_TTQ_TC").val('0');
               $(".KT_GHICHU").val('');

            }
            
            //Check xem du lieu da ok chua
            //Neu ok roi thi goi su kien submit du lieu
            function fnCheckThenSubmit(){
                $("#update").click(function () {
                   sleep(1000);  
                });
                if(validateRequiredFields()){
                    $("#update").trigger('click');
                }
            }
            
            
            function sleep(milliSeconds){
                var startTime = new Date().getTime(); // get the current time
                while (new Date().getTime() < startTime + milliSeconds); // hog cpu
            }
            
            
            function validateRequiredFields(){
                var result = true; //Luu ket qua kiem tra kieu so co dung khong
                
                //Cac class number phai nhap kieu so
                $(".number").each(function(index){
                    //Kiem tra xem co nhap kieu so khong
                    if(isNaN(parseFloat($(this).val()))){
                        result = false; 
                        return false;
                    }
                });
                
                //Cac class nubmer2 phai nhap kieu so
                $(".number2").each(function(index){
                    //Kiem tra xem co nhap kieu so khong
                    if(isNaN(parseFloat($(this).val()))){
                        result = false;
                        return false;
                    }
                });
                
                if(result == false){
                    //Neu nguoi dung khong nhap dung kieu du lieu
                    //Dua ra canh bao
                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                }
                
                return result;
            }
        </script>
    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdata" id="frmdata" action="save_data_ktnb09.action" theme="simple">
            <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                <tr>
                    <td colspan="3" style="font-size: 14px;">02/KNTC: Tổng hợp kết quả xử lý đơn khiếu nại tố cáo<hr></td>                    
                </tr>
                <tr>
                    <td width="70%" >
                        <b>Phòng giao dịch: </b><input type="text" name="posCD" id="posCD" value="<s:property value="posCD"/>" readonly="readonly"/>
                        <b>Chi nhánh: </b><input type="text" name="maCn" id="maCn" value="<s:property value="maCn"/>" readonly="readonly"/>
                        <b>Quý báo cáo: </b><input type="text" name="quyBc" id="quyBc" value="<s:property value="quyBc"/>" readonly="readonly"/>
                        <b>Năm báo cáo: </b><input type="text" name="namBc" id="namBc" value="<s:property value="namBc"/>" readonly="readonly"/>
                        <b>Người dùng: </b><input type="text" name="userId" id="userId" value="<s:property value="userId"/>" readonly="readonly"/>
                    </td>
                    <td align="right">     
                       <div id="result" style="color: red">                            
                        </div>
                        <input type="button" id="checkThenSubmit" value="Cập nhật" onclick="fnCheckThenSubmit()" style="width:122px;height:25px;color: red;"/>
                        <sj:submit targets="result" value="Cập nhật" name="update" id="update"  cssStyle="display: none;"/>
                    </td>
                    
                     <td align="right" style="width:125px">    
                        <s:url id="idurlReset9" action="ResetDataInput9.action"></s:url>
                            <sj:submit id="idReset9" name="nameReset9" href="%{idurlReset9}" 
                                       value="Reset" style="width:122px;height:25px;color: red;"
                                       targets="result"
                                       formIds="frmdata"
                                       onclick="fnResetVal()"
                                       />
                    </td>
                </tr>
                <tr>
                    <td colspan="3">
                        <hr>
                        <table border="1px" id="tableKtnb">
                            <tr class="tbhead">
                                <th rowspan="5">Đơn vị</th>
                                <th rowspan="5">Tổng số đơn</th>
                                <th colspan="5">Tiếp nhận</th>
                                <th colspan="19">Phân loại đơn khiếu nại, tố cáo (số đơn)</th>
                                <th rowspan="5">Đơn khác (kiến nghị, phản ánh, đơn nặc danh)</th>
                                <th colspan="5">Kết quả xử lý đơn khiếu nại, tố cáo</th>
                                <th rowspan="5">Ghi chú</th>
                                
                                <th rowspan="5">Chức năng</th>   
                                <th rowspan="5" class="hideColumn">Được nhập</th>
                                <th rowspan="5" class="hideColumn">Cố định</th>
                                <th rowspan="5" class="hideColumn">Thêm</th>
                                <th rowspan="5" class="hideColumn">Xóa</th>
                                <th rowspan="5" class="hideColumn">Font</th>
                                <th rowspan="5" class="hideColumn">Cấp hiển thị</th>
                                <th rowspan="5" class="hideColumn">Số thứ tự</th>
                                <th rowspan="5" class="hideColumn">Ngày cập nhật</th>
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="2" colspan="2">Đơn tiếp nhận trong kỳ</th>
                                <th rowspan="2" colspan="2">Đơn kỳ trước chuyển sang</th>
                                <th rowspan="4">Đơn đủ điều kiện xử lý</th>
                                <th colspan="13">Theo nội dung</th>
                                <th colspan="3">Theo thẩm quyền giải quyết</th>
                                <th colspan="3">Theo trình tự giải quyết</th>
                                <th rowspan="4">Số văn bản hướng dẫn</th>
                                <th rowspan="4">Số đơn chuyển cơ quan có thẩm quyền</th>
                                <th rowspan="4">Số công văn đôn đốc việc giải quyết</th>
                                <th rowspan="2" colspan="2">Đơn thuộc thẩm quyền</th>
                            </tr>
                            <tr class="tbhead">
                                <th colspan="7">Khiếu nại</th>
                                <th colspan="6">Tố cáo</th>
                                <th rowspan="3">Của các cơ quan hành chính các cấp</th>
                                <th rowspan="3">Của các cơ quan tư pháp các cấp</th>
                                <th rowspan="3">Của cơ quan Đảng</th>
                                <th rowspan="3">Chưa được giải quyết</th>
                                <th rowspan="3">Đã được giải quyết lần đầu</th>
                                <th rowspan="3">Đã được giải quyết nhiều lần</th>
                            </tr>
                            <tr class="tbhead"> 
                               <th rowspan="2">Đơn có nhiều người đứng tên</th>
                                <th rowspan="2">Đơn một người đứng tên</th>
                                <th rowspan="2">Đơn có nhiều người đứng tên</th>
                                <th rowspan="2">Đơn có một người đứng tên</th>
                                <th colspan="5">Lĩnh vực hành chính</th>
                                <th rowspan="2">Lĩnh vực tư pháp</th>
                                <th rowspan="2">Về Đảng</th>
                                <th rowspan="2">Tổng</th>
                                <th rowspan="2">Lĩnh vực hành chính</th>
                                <th rowspan="2">Lĩnh vực tư pháp</th>
                                <th rowspan="2">Tham nhũng</th>
                                <th rowspan="2">Về Đảng</th>
                                <th rowspan="2">Lĩnh vực khác</th>
                                <th rowspan="2">Khiếu nại</th>
                                <th rowspan="2">Tố cáo</th>
                            </tr>
                            <tr class="tbhead">
                                <th>Tổng</th>
                                <th>Liên quan đến đất đai</th>
                                <th>Về nhà, tài sản</th>
                                <th>Về chính sách,chế độ CC,VC</th>
                                <th>Lĩnh vực CT, VH, XH</th>
                            </tr>
                            <tr class="tbhead">
                                <th>MS</th>
                                <th>1=2+3+4+5</th>
                                <th>2</th>
                                <th>3</th>
                                <th>4</th>
                                <th>5</th>
                                <th>6</th>
                                <th>7=8+9+10+11</th>                                
                                <th>8</th>
                                <th>9</th>
                                <th>10</th>
                                <th>11</th>
                                <th>12</th>                                
                                <th>13</th>
                                <th>14=15+16+17+18+19</th>
                                <th>15</th>
                                <th>16</th>
                                <th>17</th>
                                <th>18</th>
                                <th>19</th>
                                <th>20</th>
                                <th>21</th>
                                <th>22</th>
                                <th>23</th>
                                <th>24</th>
                                <th>25</th>
                                <th>26</th>
                                <th>27</th>
                                <th>28</th>
                                <th>29</th>
                                <th>30</th>
                                <th>31</th>                                
                                <th>32</th>
                                
                                <th>33</th>
                                <th class="hideColumn">34</th>
                                <th class="hideColumn">35</th>
                                <th class="hideColumn">36</th>
                                <th class="hideColumn">37</th>
                                <th class="hideColumn">38</th>
                                <th class="hideColumn">39</th>
                                <th class="hideColumn">40</th>
                                <th class="hideColumn">41</th>
                            </tr>
                            
                            
                            <s:iterator value="ktnb09ModelList">
                                <tr class="cscontent">
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DV'/>" name="KT_DV" class="KT_DV" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>

                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_TS'/>" name="KT_TN_TS" class="KT_TN_TS number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_TK_NN'/>" name="KT_TN_TK_NN" class="KT_TN_TK_NN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_TK_MN'/>" name="KT_TN_TK_MN" class="KT_TN_TK_MN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_KT_NN'/>" name="KT_TN_KT_NN" class="KT_TN_KT_NN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_KT_MN'/>" name="KT_TN_KT_MN" class="KT_TN_KT_MN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_DDK'/>" name="KT_TN_DDK" class="KT_TN_DDK number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_HC_T'/>" name="KT_PL_ND_KN_HC_T" class="KT_PL_ND_KN_HC_T number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_HC_DD'/>" name="KT_PL_ND_KN_HC_DD" class="KT_PL_ND_KN_HC_DD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_HC_NTS'/>" name="KT_PL_ND_KN_HC_NTS" class="KT_PL_ND_KN_HC_NTS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_HC_CS'/>" name="KT_PL_ND_KN_HC_CS" class="KT_PL_ND_KN_HC_CS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_HC_CT'/>" name="KT_PL_ND_KN_HC_CT" class="KT_PL_ND_KN_HC_CT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_TP'/>" name="KT_PL_ND_KN_TP" class="KT_PL_ND_KN_TP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_D'/>" name="KT_PL_ND_KN_D" class="KT_PL_ND_KN_D number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_T'/>" name="KT_PL_ND_TC_T" class="KT_PL_ND_TC_T number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_HC'/>" name="KT_PL_ND_TC_HC" class="KT_PL_ND_TC_HC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_TP'/>" name="KT_PL_ND_TC_TP" class="KT_PL_ND_TC_TP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_TN'/>" name="KT_PL_ND_TC_TN" class="KT_PL_ND_TC_TN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_D'/>" name="KT_PL_ND_TC_D" class="KT_PL_ND_TC_D number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_K'/>" name="KT_PL_ND_TC_K" class="KT_PL_ND_TC_K number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TQ_HC'/>" name="KT_PL_TQ_HC" class="KT_PL_TQ_HC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TQ_TP'/>" name="KT_PL_TQ_TP" class="KT_PL_TQ_TP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TQ_D'/>" name="KT_PL_TQ_D" class="KT_PL_TQ_D number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TT_CGQ'/>" name="KT_PL_TT_CGQ" class="KT_PL_TT_CGQ number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TT_DGQ1'/>" name="KT_PL_TT_DGQ1" class="KT_PL_TT_DGQ1 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TT_GDQN'/>" name="KT_PL_TT_GDQN" class="KT_PL_TT_GDQN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK'/>" name="KT_DK" class="KT_DK number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_SVB'/>" name="KT_KQ_SVB" class="KT_KQ_SVB number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CTQ'/>" name="KT_KQ_CTQ" class="KT_KQ_CTQ number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_SCV'/>" name="KT_KQ_SCV" class="KT_KQ_SCV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" /></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_TTQ_KN'/>" name="KT_KQ_TTQ_KN" class="KT_KQ_TTQ_KN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_TTQ_TC'/>" name="KT_KQ_TTQ_TC" class="KT_KQ_TTQ_TC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_GHICHU'/>" name="KT_GHICHU" class="KT_GHICHU" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    
                                     <!-- CuongBM: Xử lý thêm xóa dòng-->
                                    <td class="<s:property value='KT_FONTWEIGHT'/>">
                                        <!-- CuongBM: Nếu được thêm dòng-->
                                        <s:if test="KT_THEM.equalsIgnoreCase('Y')">                                     
                                            <input type="button" onclick="addRow(this.parentNode.parentNode.rowIndex)" value="Them"/>                                            
                                        </s:if>

                                        <!-- CuongBM: Nếu được xóa dòng-->
                                        <s:if test="KT_XOA.equalsIgnoreCase('Y')">                                     
                                            <input type="button" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" value="Xoa"/>
                                        </s:if>
                                    </td> 

                                    <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_DN'/>" name="KT_DN"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_CO_DINH'/>" name="KT_CO_DINH"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_THEM'/>" name="KT_THEM"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_XOA'/>" name="KT_XOA"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_FONTWEIGHT'/>" name="KT_FONTWEIGHT"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_CAPHT'/>" name="KT_CAPHT"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KT_STT'/>" name="KT_STT"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='NG_CAPNHAT'/>" name="NG_CAPNHAT"/></td>

                                </tr>
                            </s:iterator>
                        </table>
                </tr>
            </table>
                    
            </s:form>
        </div>
    </body>
</html>
