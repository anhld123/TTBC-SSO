<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%--<sj:head jqueryui="false" jquerytheme="simple"/>
<s:head/>
<sj:head/>--%>
<!DOCTYPE html>

      
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
            
            .tdtest {
                width: 20%;
            }
        </style>
        
     <SCRIPT language="javascript">

//            function clk_glkhtd() {
//                var lstPos = "";
//                $('#treeView').jstree("get_checked", null, true).each(
//                        function() {
//                            lstPos = lstPos + this.id + ',';
//                        });
//                document.getElementById("selectedPos").value = lstPos;
//            }
            
            //Xu ly tinh tong cho tung dong
          
            
            //Check xem du lieu da ok chua
            //Neu ok roi thi goi su kien submit du lieu

           
            
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
        </SCRIPT>   
        
      
    <div id="TEST">
    <div id="valueview_div" class="BalanceSheetStyle" >                        
       <table border="1px" id="tableKtnb">
                            <tr class="tbhead">
                                <th rowspan="4">Đơn vị</th>
                                <th colspan="4">Đơn tố cáo thuộc thẩm quyền</th>
                                <th colspan="16">Kết quả giải quyết</th>
                                <th rowspan="2" colspan="2">Chấp hành thời gian giải quyết theo quy định</th>
                                <th colspan="10">Việc thi hành quyết định xử lý tố cáo</th>
                                <th rowspan="4">Ghi chú</th>
                                
                                
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
                                
                                
                            </tr>
                            
                            
                            <s:iterator value="ktnb11ModelList">
                                <tr class="cscontent">
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DV'/>" name="KT_DV" class="KT_DV" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DKN_TS'/>" name="KT_DKN_TS" class="KT_DKN_TS number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DKN_TD_TK'/>" name="KT_DKN_TD_TK" class="KT_DKN_TD_TK number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DKN_TD_KT'/>" name="KT_DKN_TD_KT" class="KT_DKN_TD_KT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DKN_TD_TS'/>" name="KT_DKN_TD_TS" class="KT_DKN_TD_TS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_DGQ_SD'/>" name="KT_KQ_DGQ_SD" class="KT_KQ_DGQ_SD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_DGQ_SVV'/>" name="KT_KQ_DGQ_SVV" class="KT_KQ_DGQ_SVV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_PT_TCD'/>" name="KT_KQ_PT_TCD" class="KT_KQ_PT_TCD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_PT_TCS'/>" name="KT_KQ_PT_TCS" class="KT_KQ_PT_TCS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_PT_TCD1'/>" name="KT_KQ_PT_TCD1" class="KT_KQ_PT_TCD1 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_KN_T'/>" name="KT_KQ_KN_T" class="KT_KQ_KN_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_KN_D'/>" name="KT_KQ_KN_D" class="KT_KQ_KN_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_TL_T'/>" name="KT_KQ_TL_T" class="KT_KQ_TL_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_TL_D'/>" name="KT_KQ_TL_D" class="KT_KQ_TL_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_SN'/>" name="KT_KQ_SN" class="KT_KQ_SN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_KN_TS'/>" name="KT_KQ_KN_TS" class="KT_KQ_KN_TS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_KN_SN'/>" name="KT_KQ_KN_SN" class="KT_KQ_KN_SN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_SV'/>" name="KT_KQ_CCQ_SV" class="KT_KQ_CCQ_SV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_SDT'/>" name="KT_KQ_CCQ_SDT" class="KT_KQ_CCQ_SDT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_KQ_SV'/>" name="KT_KQ_CCQ_KQ_SV" class="KT_KQ_CCQ_KQ_SV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_KQ_SDT'/>" name="KT_KQ_CCQ_KQ_SDT" class="KT_KQ_CCQ_KQ_SDT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_KQ_DTH'/>" name="KT_KQ_CCQ_KQ_DTH" class="KT_KQ_CCQ_KQ_DTH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CCQ_KQ_QTH'/>" name="KT_KQ_CCQ_KQ_QTH" class="KT_KQ_CCQ_KQ_QTH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_TS'/>" name="KT_TH_TS" class="KT_TH_TS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_DTH'/>" name="KT_TH_DTH" class="KT_TH_DTH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_THNN_PT_T'/>" name="KT_TH_THNN_PT_T" class="KT_TH_THNN_PT_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_THNN_PT_D'/>" name="KT_TH_THNN_PT_D" class="KT_TH_THNN_PT_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_THNN_DT_T'/>" name="KT_TH_THNN_DT_T" class="KT_TH_THNN_DT_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_THNN_DT_D'/>" name="KT_TH_THNN_DT_D" class="KT_TH_THNN_DT_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_TL_PT_T'/>" name="KT_TH_TL_PT_T" class="KT_TH_TL_PT_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_TL_PT_D'/>" name="KT_TH_TL_PT_D" class="KT_TH_TL_PT_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_TL_DT_T'/>" name="KT_TH_TL_DT_T" class="KT_TH_TL_DT_T number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TH_TL_DT_D'/>" name="KT_TH_TL_DT_D" class="KT_TH_TL_DT_D number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_GHICHU'/>" name="KT_GHICHU" class="KT_GHICHU" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>



                                </tr>
                            </s:iterator>
                        </table> 
    </div>      
</div>