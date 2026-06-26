
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<style>
    #subTable {
        font-size: 12px; /* 👈 chữ to hơn */
        font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
        border-collapse: collapse;
        width: 99%;
        margin: auto;
        background-color: #fff;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        border: #000;
        border-radius: 10px;
        overflow: hidden;
    }

    #subTable th {
        background-color: #f0f4f8;
        color: #2a3f54;
        padding: 14px;
        text-align: center;
        font-weight: bold;
        font-size: 10px;
    }

    #subTable td {
        padding: 12px;
        border-bottom: 1px solid #ccc;
        /*text-align: center;*/
        font-size: 10.5px;
    }
    #subTable tbody tr:nth-child(odd) {
        background-color: #ffffff; /* trắng */
    }

    #subTable tbody tr:nth-child(even) {
        background-color: #f3f8ff; /* xanh nhạt */
    }

    #subTable tbody tr:hover {
        background-color: #dbeafe;
        transition: background-color 0.2s ease;
    }


</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var popWindow;
            var max_row = 0;
            function initSubForm() {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".STT1").css({"width": "5%"});
                $(".STT2").css({"width": "15%"});
                $(".STT4").css({"width": "5%"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0).css({"text-align": "right"});
                //            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);

            }

            initSubForm();
        </script>      
    </head>
    <body>
        <div style="overflow:scroll; width: 98vw;">             
            <div id="divTitle"
                 style="margin: 10px 0; text-align: center; font-weight: bold; font-size: 16px; text-transform: uppercase;">
                NHU CẦU VỐN TÍN DỤNG CHÍNH SÁCH <s:property value="ten_thon" />
            </div>
            <!--<div style="height:10px"></div>-->  
            <div id="divDonvitinh">
                Đơn vị: triệu đồng.
            </div>
            <table border="1" class="editDelete" id="subTable" align="center" style="padding-top: 10px"> 

                <tr>
                    <th class="STT2" rowspan="3">CHỈ TIÊU</th>
                    <th class="STT4" rowspan="3">TỔNG NHU CẦU VỐN TÍN DỤNG</th>
                    <th class="STT4" colspan="17">Các chương trình tín dụng</th>
                </tr>

                <tr>
                    <th class="STT4" rowspan="2">Hộ nghèo</th>
                    <th class="STT4" rowspan="2">Hộ cận nghèo</th>
                    <th class="STT4" rowspan="2">Hộ mới thoát nghèo</th>
                    <th class="STT4" rowspan="2">Hỗ trợ tạo việc làm, duy trì và mở rộng việc làm</th>
                    <th class="STT4" rowspan="2">Người lao động đi làm việc ở nước ngoài</th>
                    <th class="STT4" rowspan="2">Hộ gia đình SXKD tại vùng khó khăn</th>
                    <th class="STT4" rowspan="2">Thương nhân tại vùng khó khăn</th>
                    <th class="STT4" rowspan="2">Phát triển kinh tế vùng DTTS&MN</th>
                    <th class="STT4" rowspan="2">Người chấp hành xong án phạt tù</th>
                    <th class="STT4" rowspan="2">Người sau cai nghiện ma túy</th>
                    <th class="STT4" colspan="2">Học sinh sinh viên có hoàn cảnh khó khăn</th>
                    <th class="STT4" rowspan="2">Nước sạch và vệ sinh môi trường nông thôn</th>
                    <th class="STT4" rowspan="2">Nhà ở xã hội</th>
                    <th class="STT4" rowspan="2">Vốn nước ngoài</th>
                    <th class="STT4" rowspan="2">Các chương trình đã hết thời hạn giải ngân và chương trình khác</th>
                    <th class="STT4" rowspan="2">Khác</th>
                </tr>
                <tr>
                    <th class="STT4">Tổng số</th>
                    <th class="STT4">Tr/đó: HSSV STEM</th>
                </tr>

                <tr>
                    <s:iterator begin="1" end="19" status="st">
                        <th style="color:#000;font-style:italic;font-size:xx-small;padding:2px 0;line-height:12px;">
                            (<s:property value="#st.count"/>)
                        </th>
                    </s:iterator>
                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr <s:if test="THUTU == 0">style="font-weight:bold"</s:if>>
                        <td><s:property  value="TEN" /></td>
                        <td class="number"><s:property  value="D19" /></td>
                        <td class="number"> <s:property value="D1"/></td>
                        <td class="number"> <s:property value="D2"/></td>
                        <td class="number"> <s:property value="D3"/></td>
                        <td class="number"> <s:property value="D4"/></td>
                        <td class="number"> <s:property value="D5"/></td>
                        <td class="number"> <s:property value="D6"/></td>
                        <td class="number"> <s:property value="D7"/></td>
                        <td class="number"> <s:property value="D8"/></td> 
                        <td class="number"> <s:property value="D9"/></td> 
                        <td class="number"> <s:property value="D10"/></td> 
                        <td class="number"> <s:property value="D11"/></td> 
                        <td class="number"> <s:property value="D12"/></td> 
                        <td class="number"> <s:property value="D13"/></td> 
                        <td class="number"> <s:property value="D14"/></td> 
                        <td class="number"> <s:property value="D15"/></td> 
                        <td class="number"> <s:property value="D16"/></td> 
                        <td class="number"> <s:property value="D17"/></td> 
                    </tr>
                </s:iterator>
            </table>
        </div>      
        <div id="luu_thanhcong"></div>
    </body>

</html>
