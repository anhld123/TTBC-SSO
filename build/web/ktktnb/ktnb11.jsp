<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biểu số 04/KNTC</title>
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
            
            function fnResetVal(){
               $(".KT_DKN_TS").val('0');
               $(".KT_DKN_TD_TK").val('0');
               $(".KT_DKN_TD_KT").val('0');
               $(".KT_DKN_TD_TS").val('0');
               
               $(".KT_KQ_DGQ_SD").val('0');
               $(".KT_KQ_DGQ_SVV").val('0');
               $(".KT_KQ_PT_TCD").val('0');
               $(".KT_KQ_PT_TCS").val('');
               
               $(".KT_KQ_PT_TCD1").val('0');
               $(".KT_KQ_KN_T").val('0');
               $(".KT_KQ_KN_D").val('0');
               $(".KT_KQ_TL_T").val('0');
               
               
                $(".KT_KQ_TL_D").val('0');
               $(".KT_KQ_SN").val('0');
               $(".KT_KQ_KN_TS").val('0');
               $(".KT_KQ_KN_SN").val('0');
               
               $(".KT_KQ_CCQ_SV").val('0');
               $(".KT_KQ_CCQ_SDT").val('0');
               $(".KT_KQ_CCQ_KQ_SV").val('0');
               $(".KT_KQ_CCQ_KQ_SDT").val('');
               
               $(".KT_KQ_CCQ_KQ_DTH").val('0');
               $(".KT_KQ_CCQ_KQ_QTH").val('0');
               $(".KT_TH_TS").val('0');
               $(".KT_TH_DTH").val('0');
               
               
               $(".KT_TH_THNN_PT_T").val('0');
               $(".KT_TH_THNN_PT_D").val('0');
               $(".KT_TH_THNN_DT_T").val('0');
               $(".KT_TH_THNN_DT_D").val('');
               
               $(".KT_TH_TL_PT_T").val('0');
               $(".KT_TH_TL_PT_D").val('0');
               $(".KT_TH_TL_DT_T").val('0');
               
               $(".KT_TH_TL_DT_D").val('0');
               
               $(".KT_GHICHU").val('');

            }
            
            //Xu ly tinh tong cho tung dong
            function autoEvaluate(){
                //1=2+3
                $(".KT_DKN_TS").eq(0).val(parseFloat($(".KT_DKN_TD_TK").eq(0).val()) + 
                        parseFloat($(".KT_DKN_TD_KT").eq(0).val()));
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
            <s:form name="frmdata" id="frmdata" action="save_data_ktnb11.action" theme="simple">
            <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                <tr>
                    <td colspan="3" style="font-size: 14px;">04/KNTC: Tổng hợp kết quả giải quyết đơn tố cáo<hr></td>                    
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
                        <s:url id="idurlReset11" action="ResetDataInput11.action"></s:url>
                            <sj:submit id="idReset11" name="nameReset11" href="%{idurlReset11}" 
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
                                <th rowspan="4">Đơn vị</th>
                                <th colspan="4">Đơn tố cáo thuộc thẩm quyền</th>
                                <th colspan="16">Kết quả giải quyết</th>
                                <th rowspan="2" colspan="2">Chấp hành thời gian giải quyết theo quy định</th>
                                <th colspan="10">Việc thi hành quyết định xử lý tố cáo</th>
                                <th rowspan="4">Ghi chú</th>
                                
                                <th rowspan="4">Chức năng</th>   
                                <th rowspan="4" class="hideColumn">Được nhập</th>
                                <th rowspan="4" class="hideColumn">Cố định</th>
                                <th rowspan="4" class="hideColumn">Thêm</th>
                                <th rowspan="4" class="hideColumn">Xóa</th>
                                <th rowspan="4" class="hideColumn">Font</th>
                                <th rowspan="4" class="hideColumn">Cấp hiển thị</th>
                                <th rowspan="4" class="hideColumn">Số thứ tự</th>
                                <th rowspan="4" class="hideColumn">Ngày cập nhật</th>
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="3">Tổng số đơn tố cáo</th>
                                <th colspan="3">Trong đó</th>
                                <th colspan="2">Đã giải quyết</th>
                                <th colspan="3">Phân tích kết quả (vụ việc)</th>
                                <th rowspan="2" colspan="2">Kiến nghị thu hồi cho nhà nước</th>
                                <th rowspan="2" colspan="2">Trả lại cho công dân/khách hàng</th>
                                <th rowspan="3">Số người được bảo vệ quyền lợi</th>
                                <th rowspan="2" colspan="2">Kiến nghị xử lý hành chính</th>
                                <th colspan="4">Chuyển cơ quan điều tra, khởi tố</th>
                                
                                <th rowspan="3">Tổng số quyết định phải tổ chức thực hiện trong kỳ báo cáo</th>
                                <th rowspan="3">Đã thực hiện</th>
                                <th colspan="4">Thu hồi cho nhà nước</th>
                                <th colspan="4">Trả lại cho công dân/khách hàng</th>
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="2">Đơn nhận được trong kỳ báo cáo</th>
                                <th rowspan="2">Đơn tồn kỳ trước chuyển sang</th>
                                <th rowspan="2">Tổng số vụ việc</th>
                                <th rowspan="2">Số đơn thuộc thẩm quyền</th>
                                <th rowspan="2">Số vụ việc thuộc thẩm quyền</th>
                                <th rowspan="2">Tố cáo đúng</th>
                                <th rowspan="2">Tố cáo sai</th>
                                <th rowspan="2">Tố cáo đúng một phần</th>
                                <th rowspan="2">Số vụ</th>
                                <th rowspan="2">Số đối tượng</th>
                                <th colspan="2">Kết quả</th>
                                <th rowspan="2">Số vụ việc giải quyết đúng thời hạn</th>
                                <th rowspan="2">Số vụ việc giải quyết quá thời hạn</th>
                                <th colspan="2">Phải thu</th>
                                <th colspan="2">Đã thu</th>
                                <th colspan="2">Phải thu</th>
                                <th colspan="2">Đã thu</th>
                            </tr>
                            <tr class="tbhead"> 
                                <th>Tiền (trđ)</th>
                                <th>Đất (m2)</th>
                                <th>Tiền (trđ)</th>
                                <th>Đất (m2)</th>
                                <th>Tổng số người</th>
                                <th>Số người đã bị xử lý</th>
                                <th>Số vụ đã khởi tố</th>                                
                                <th>Số đối tượng đã khởi tố</th>
                                <th>Tiền (trđ)</th>
                                <th>Đất (m2)</th>
                                <th>Tiền (trđ)</th>
                                <th>Đất (m2)</th>
                                <th>Tiền (trđ)</th>
                                <th>Đất (m2)</th>
                                <th>Tiền (trđ)</th>
                                <th>Đất (m2)</th>
                            </tr>
                            <tr class="tbhead">
                                <th>MS</th>
                                <th>1=2+3</th>
                                <th>2</th>
                                <th>3</th>
                                <th>4</th>
                                <th>5</th>
                                <th>6</th>
                                <th>7</th>                                
                                <th>8</th>
                                <th>9</th>
                                <th>10</th>
                                <th>11</th>
                                <th>12</th>                                
                                <th>13</th>
                                <th>14</th>
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
                                
                                <th>34</th>
                                <th class="hideColumn">35</th>
                                <th class="hideColumn">36</th>
                                <th class="hideColumn">37</th>
                                <th class="hideColumn">38</th>
                                <th class="hideColumn">39</th>
                                <th class="hideColumn">40</th>
                                <th class="hideColumn">41</th>
                                <th class="hideColumn">42</th>
                            </tr>
                            
                            
                            <s:iterator value="ktnb11ModelList">
                                <tr class="cscontent">
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DV'/>" name="KT_DV" class="KT_DV" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DKN_TS'/>" name="KT_DKN_TS" class="KT_DKN_TS number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DKN_TD_TK'/>" name="KT_DKN_TD_TK" class="KT_DKN_TD_TK number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DKN_TD_KT'/>" name="KT_DKN_TD_KT" class="KT_DKN_TD_KT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DKN_TD_TS'/>" name="KT_DKN_TD_TS" class="KT_DKN_TD_TS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_DGQ_SD'/>" name="KT_KQ_DGQ_SD" class="KT_KQ_DGQ_SD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_DGQ_SVV'/>" name="KT_KQ_DGQ_SVV" class="KT_KQ_DGQ_SVV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_PT_TCD'/>" name="KT_KQ_PT_TCD" class="KT_KQ_PT_TCD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_PT_TCS'/>" name="KT_KQ_PT_TCS" class="KT_KQ_PT_TCS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_PT_TCD1'/>" name="KT_KQ_PT_TCD1" class="KT_KQ_PT_TCD1 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_KN_T'/>" name="KT_KQ_KN_T" class="KT_KQ_KN_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_KN_D'/>" name="KT_KQ_KN_D" class="KT_KQ_KN_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_TL_T'/>" name="KT_KQ_TL_T" class="KT_KQ_TL_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_TL_D'/>" name="KT_KQ_TL_D" class="KT_KQ_TL_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_SN'/>" name="KT_KQ_SN" class="KT_KQ_SN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_KN_TS'/>" name="KT_KQ_KN_TS" class="KT_KQ_KN_TS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_KN_SN'/>" name="KT_KQ_KN_SN" class="KT_KQ_KN_SN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_SV'/>" name="KT_KQ_CCQ_SV" class="KT_KQ_CCQ_SV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_SDT'/>" name="KT_KQ_CCQ_SDT" class="KT_KQ_CCQ_SDT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_KQ_SV'/>" name="KT_KQ_CCQ_KQ_SV" class="KT_KQ_CCQ_KQ_SV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_KQ_SDT'/>" name="KT_KQ_CCQ_KQ_SDT" class="KT_KQ_CCQ_KQ_SDT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_KQ_DTH'/>" name="KT_KQ_CCQ_KQ_DTH" class="KT_KQ_CCQ_KQ_DTH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_KQ_QTH'/>" name="KT_KQ_CCQ_KQ_QTH" class="KT_KQ_CCQ_KQ_QTH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_TS'/>" name="KT_TH_TS" class="KT_TH_TS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_DTH'/>" name="KT_TH_DTH" class="KT_TH_DTH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_THNN_PT_T'/>" name="KT_TH_THNN_PT_T" class="KT_TH_THNN_PT_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_THNN_PT_D'/>" name="KT_TH_THNN_PT_D" class="KT_TH_THNN_PT_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_THNN_DT_T'/>" name="KT_TH_THNN_DT_T" class="KT_TH_THNN_DT_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_THNN_DT_D'/>" name="KT_TH_THNN_DT_D" class="KT_TH_THNN_DT_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_TL_PT_T'/>" name="KT_TH_TL_PT_T" class="KT_TH_TL_PT_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_TL_PT_D'/>" name="KT_TH_TL_PT_D" class="KT_TH_TL_PT_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_TL_DT_T'/>" name="KT_TH_TL_DT_T" class="KT_TH_TL_DT_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_TL_DT_D'/>" name="KT_TH_TL_DT_D" class="KT_TH_TL_DT_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" onkeypress="doClick('checkThenSubmit',event)"/></td>
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
