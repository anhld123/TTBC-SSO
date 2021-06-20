<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="javax.servlet.ServletConfig"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
 <SCRIPT language="javascript">
      function evaluateSum() {
            try {
//                alert('vao tinh tong');
                var temp = 0;
                var kh_capht = ".KH_CAPHT";
                var kh_congthuc = ".KH_CONGTHUC";
                var kh_ma_ct = ".KH_MA_CT";
                var kh_dc = ".KH_DC";
                //Thu tu cua i tinh tu 0
                var arrCapht = [4.0, 3.0, 2.0, 1.0]; //Luu cac cot cua du lieu can tinh toan
                var rowCount = $("#tableKhnv td").closest("tr").length;
                
                //duyệt cấp cộng tổng hợp
                for (k = 0; k < arrCapht.length; k++)
                {//duyệt số row của bảng để lấy ra công thức.
                   
                    for (i = 0; i < rowCount; i++)
                    {//nếu cấp báo cáo bằng với danh sách mảng của câp báo cáo ở trên và công thức khác null hoặc rỗng
                        if ($(kh_capht).eq(i).val() == arrCapht[k] && $(kh_congthuc).eq(i).val().length > 0)
                        {
                            //lấy ra công thức
                            var congthuc = $(kh_congthuc).eq(i).val();
//                             console.log('-------------- congthuc = '+congthuc);
                            //cắt công thức đưa về mảng
                            var valNew = congthuc.split('+');
                            var tong_dc=sum_mact(valNew,kh_dc);
                            if(tong_dc!=0)
                                $(kh_dc).eq(i).val(tong_dc);
                      
//                            console.log('-------------- tong = '+tong_dc)
//                            console.log('capht=' + $(kh_capht).eq(i).val().toString() + ' congthuc=' + $(kh_congthuc).eq(i).val() + ' chitieu=' + $(kh_ma_ct).eq(i).val() +
//                                    ' Tong=' + tong_dc);
                        }
                    }

                }
            } catch (e) {
                alert(e.toString());
            }

        }
        //ham nay tinh tong chi tieu truyen vao la 1 mang dang  [abc,hsjs,skjdsk..] và class de tinh
        function sum_mact(arrMact, name_class)
        {
            //lấy ra tổng số row có dữ liệu của bảng
            var rowCount = $("#tableKhnv td").closest("tr").length;
            //khởi tạo class mã chỉ tiêu
            var kh_ma_ct = ".KH_MA_CT";
            var tong = 0;
            //for cho các row de tinh
            for (var i = 0; i < rowCount; i++)
            {
                var ma_ct = $(kh_ma_ct).eq(i).val();
                if(isChitieu(arrMact,ma_ct))
                    tong += parseFloat($(name_class).eq(i).val());
            }
            return tong;
        }
        function isChitieu(arrMact, ma_ct)
        {
            try
            {
             for (j = 0; j < arrMact.length; j++)
                {
                    if (arrMact[j] == ma_ct)
                    //nếu tìm thấy mã chỉ tiêu ở trong mảng thì cộng vào tổng
                    {
                        return true;
                    }
                }
                return false;
            }
            catch(e){return false;}
        }
     </SCRIPT>
<table border="1px" id="tableKhnv" class="tableKhnv">
    <tr class="tbhead">
        <th>STT</th>
        <th class="hideColumn">Mã chỉ tiêu</th>
        <th class="KH_CHI_TIEU">Chỉ tiêu</th>
        <th><span id="khTitle" style="font-weight: bold"></span></th>
        <th>Điều chỉnh kế hoạch</th>

        <th class="hideColumn">Được nhập</th>
        <th class="hideColumn">Font</th>
        <th class="hideColumn">Cấp hiển thị</th>
        <th class="hideColumn">Số thứ tự</th>
    </tr>
    <tr class="tbhead">
        <th class='locked_class_name'>(1)</th>
        <th class="hideColumn">(2)</th>
        <th class="KH_CHI_TIEU">(2)</th>
        <th>(3)</th>
        <th>(4)</th>
        <th class="hideColumn">(6)</th>
        <th class="hideColumn">(7)</th>
        <th class="hideColumn">(8)</th>
        <th class="hideColumn">(9)</th>                       
    </tr>    

    <s:iterator value="dieuchinhkhModelList">
        <tr class="cscontent">
            <s:if test="posCdLoadData.equalsIgnoreCase('000101')">
                <!-- CuongBM: Neu la SGD thi cho nhap tat ca cac chi tieu -->

                <!-- CuongBM: Nếu KT_DN: Được nhập = N, người dùng không được phép nhập, thay đổi -->
                <s:if test="KH_DN.equalsIgnoreCase('N')">                                     
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_STT_HT'/>" name="KH_STT_HT" class="KH_STT_HT" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_MA_CT'/>" name="KH_MA_CT" class="KH_MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_CHI_TIEU'/>" name="KH_CHI_TIEU" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_GIAO_DC'/>" name="KH_GIAO_DC" class="KH_GIAO_DC number2" onfocus="this.select()" readonly="readonly" onblur="if (this.value == '') {this.value = 0};evaluateSum(this)"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_DC'/>" name="KH_DC" class="KH_DC number2" onfocus="this.select()" readonly="readonly" onblur="if (this.value == '') {this.value = 0};evaluateSum(this)"/></td>

                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_DN'/>" name="KH_DN"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_FONTWEIGHT'/>" name="KH_FONTWEIGHT"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CAPHT'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_STT'/>" name="KH_STT"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CONGTHUC'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                    </s:if>

                <!-- CuongBM: Nếu KT_DN: Được nhập = Y -->
                <s:if test="KH_DN.equalsIgnoreCase('Y')">                                     
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_STT_HT'/>" name="KH_STT_HT" class="KH_STT_HT" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_MA_CT'/>" name="KH_MA_CT" class="KH_MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_CHI_TIEU'/>" name="KH_CHI_TIEU" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_GIAO_DC'/>" name="KH_GIAO_DC" class="KH_GIAO_DC number2" onfocus="this.select()"  readonly="readonly" onblur="if (this.value == '') {this.value = 0};evaluateSum(this)"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_DC'/>" name="KH_DC" class="KH_DC number2" onfocus="this.select()"  onblur="if (this.value == '') {this.value = 0};evaluateSum(this)"/></td>

                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_DN'/>" name="KH_DN"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_FONTWEIGHT'/>" name="KH_FONTWEIGHT"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CAPHT'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_STT'/>" name="KH_STT"/></td>
                     <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CONGTHUC'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                    </s:if>     
                </s:if>
                <s:else>
                <!-- CuongBM: Neu khong phai SGD thi cac chi tieu cua SGD se ko cho phep nhap -->

                <!-- CuongBM: Nếu KT_DN: Được nhập = N, người dùng không được phép nhập, thay đổi -->
                <s:if test="KH_DN.equalsIgnoreCase('N') || KH_CT_SGD.equalsIgnoreCase('Y')">                                     
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_STT_HT'/>" name="KH_STT_HT" class="KH_STT_HT" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_MA_CT'/>" name="KH_MA_CT" class="KH_MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_CHI_TIEU'/>" name="KH_CHI_TIEU" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_GIAO_DC'/>" name="KH_GIAO_DC" class="KH_GIAO_DC number2" onfocus="this.select()" readonly="readonly" onblur="if (this.value == '') {this.value = 0};evaluateSum(this)"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_DC'/>" name="KH_DC" class="KH_DC number2" onfocus="this.select()" readonly="readonly" onblur="if (this.value == '') {this.value = 0};evaluateSum(this)"/></td>

                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_DN'/>" name="KH_DN"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_FONTWEIGHT'/>" name="KH_FONTWEIGHT"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CAPHT'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_STT'/>" name="KH_STT"/></td>
                     <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CONGTHUC'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                </s:if>
                <s:else>                        
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_STT_HT'/>" name="KH_STT_HT" class="KH_STT_HT" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_MA_CT'/>" name="KH_MA_CT" class="KH_MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_CHI_TIEU'/>" name="KH_CHI_TIEU" onfocus="this.select()" readonly="readonly"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_GIAO_DC'/>" name="KH_GIAO_DC" class="KH_GIAO_DC number2" onfocus="this.select()"  readonly="readonly" onblur="if (this.value == '') {this.value = 0};evaluateSum(this)"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_DC'/>" name="KH_DC" class="KH_DC number2" onfocus="this.select()"  onblur="if (this.value == '') {this.value = 0};evaluateSum(this)"/></td>

                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_DN'/>" name="KH_DN"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_FONTWEIGHT'/>" name="KH_FONTWEIGHT"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CAPHT'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_STT'/>" name="KH_STT"/></td>
                    <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CONGTHUC'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                </s:else>                        
                </s:else>
        </tr>
    </s:iterator>
</table>


<script>
    //Format cac truong input.number can le phai
    $('input.number').css({"text-align": "right"});
    $('input.number2').css({"text-align": "right"});

    //An di cac cot chuc nang
    $('.hideColumn').hide();

    //Cac truong bang so --> se co so truong = 0
    $('.number').number(true, 0);

    //Cac truong bang so --> se co so truong = 0
    $('.number2').number(true, 2);

    $(".KH_STT_HT").css({"width": "30px"});
    $(".KH_STT_HT").css({"text-align": "center"});
    $(".ZZZ").css({"text-align": "center"});
    $(".KH_CHI_TIEU").css({"width": "800px"});

    if (<s:property value="reportGrade"/> == "3") {
        //Neu la cap trung uong boi dam 2 dong nay   
        $("#tableKhnv tr").eq(2).css({"background-color": "#DCDCDC"});
        $("#tableKhnv tr").eq(2).find("input").css({"background-color": "#DCDCDC"});

        $("#tableKhnv tr").eq(29).css({"background-color": "#DCDCDC"});
        $("#tableKhnv tr").eq(29).find("input").css({"background-color": "#DCDCDC"});

        //Tieu de bao cao cap tw
        $("#idTitle").text("Điều hành chỉ tiêu kế hoạch tín dụng ");



        //Tieu de phong giao dich
        $("#phongGd").text("Mã CN: ");
        $("#khTitle").text("Kế hoạch năm");


    } else if (<s:property value="reportGrade"/> == "2") {
        //Neu la cap CN boi dam 2 dong nay
        $("#tableKhnv tr").eq(2).css({"background-color": "#DCDCDC"});
        $("#tableKhnv tr").eq(2).find("input").css({"background-color": "#DCDCDC"});

        $("#tableKhnv tr").eq(5).css({"background-color": "#DCDCDC"});
        $("#tableKhnv tr").eq(5).find("input").css({"background-color": "#DCDCDC"});

        //Tieu de phong giao dich
        $("#phongGd").text("Mã PGD: ");
        $("#khTitle").text("Giao kế hoạch");
    }
    
     
    //Xu ly tinh tong cho tung dong
    function evaluateSum1() {
        var arrCot = [".KH_DC"]; //Luu cac cot cua du lieu can tinh toan

        if (<s:property value="reportGrade"/> == "3") {
            //Neu la cap trung uong 
            for (k = 0; k < arrCot.length; k++) {
                var temp = 0;

                //Tinh toan "Vốn do Ngân sách Trung ương cấp"
                for (i = 2; i <= 12; i++) {
                    temp += parseFloat($(arrCot[k]).eq(i).val());
                }
                $(arrCot[k]).eq(1).val(temp);

                //Tinh toan "Vốn vay theo chỉ đạo của Thủ tướng Chính phủ"
                temp = 0;
                for (i = 14; i <= 17; i++) {
                    temp += parseFloat($(arrCot[k]).eq(i).val());
                }
                $(arrCot[k]).eq(13).val(temp);

                //Tinh toan "Vốn được giao huy động"
                temp = 0;
                for (i = 19; i <= 23; i++) {
                    temp += parseFloat($(arrCot[k]).eq(i).val());
                }
                $(arrCot[k]).eq(18).val(temp);

                //Tinh toan "NGUỒN VỐN"
                $(arrCot[k]).eq(0).val(parseFloat($(arrCot[k]).eq(1).val()) + parseFloat($(arrCot[k]).eq(13).val())
                        + parseFloat($(arrCot[k]).eq(18).val()) + parseFloat($(arrCot[k]).eq(24).val()));

                //Tinh toan "Cho vay vốn Quỹ quốc gia về việc làm (QĐ 71/2005/QĐ-TTg)"
                temp = 0;
                for (i = 34; i <= 42; i++) {
                    temp += parseFloat($(arrCot[k]).eq(i).val());
                }
                $(arrCot[k]).eq(33).val(temp);

                //Tinh toan "Cho vay một số dự án vốn nước ngoài khác"
                temp = 0;
                for (i = 58; i <= 64; i++) {
                    temp += parseFloat($(arrCot[k]).eq(i).val());
                }
                $(arrCot[k]).eq(57).val(temp);

                //Tinh toan "DƯ NỢ NGUỒN VỐN TRUNG ƯƠNG"
                temp = 0;
                for (i = 29; i <= 65; i++) {
//                    if (i == 30)
//                        continue;
                    //Cho vay vốn Quỹ quốc gia về việc làm (Nghị định 61/2015/NĐ-CP)
                    if ((i >= 34) && (i <= 42))
                        continue;
                    //
//                    if (i == 44)
//                        continue;
                    //Cho vay một số dự án vốn nước ngoài khác
                    if ((i >= 58) && (i <= 64))
                        continue;

                    temp += parseFloat($(arrCot[k]).eq(i).val());
                }
                $(arrCot[k]).eq(28).val(temp);

                //Tinh toan "QUỸ AN TOÀN CHI TRẢ"                
//                $(arrCot[k]).eq(66).val(parseFloat($(arrCot[k]).eq(66).val()) + parseFloat($(arrCot[k]).eq(67).val()));

                //Tinh toan "SỬ DỤNG VỐN"
                $(arrCot[k]).eq(27).val(parseFloat($(arrCot[k]).eq(28).val()) + parseFloat($(arrCot[k]).eq(66).val()));
            }
        } else if (<s:property value="reportGrade"/> == "2") {
            //Neu la cap CN 
            for (k = 0; k < arrCot.length; k++) {
                var temp = 0;

                //Tinh toan "NGUỒN VỐN"
                $(arrCot[k]).eq(0).val(parseFloat($(arrCot[k]).eq(1).val()) + parseFloat($(arrCot[k]).eq(2).val()));

                //Tinh toan "Cho vay vốn Quỹ quốc gia về việc làm (QĐ 71/2005/QĐ-TTg)"
                temp = 0;
                for (i = 10; i <= 18; i++) {
                    temp += parseFloat($(arrCot[k]).eq(i).val());
                }
                $(arrCot[k]).eq(9).val(temp);

                //Tinh toan "Cho vay một số dự án vốn nước ngoài khác"
                temp = 0;
                for (i = 34; i <= 40; i++) {
                    temp += parseFloat($(arrCot[k]).eq(i).val());
                }
                $(arrCot[k]).eq(33).val(temp);

                //Tinh toan "DƯ NỢ NGUỒN VỐN TRUNG ƯƠNG"
                temp = 0;
                for (i = 5; i <= 41; i++) {
//                    if (i == 6)
//                        continue;
                    //Cho vay vốn Quỹ quốc gia về việc làm (NĐ 61/2015/NĐ-CP)
                    if ((i >= 10) && (i <= 18))
                        continue;
//                    if (i == 20)
//                        continue;
                    //Cho vay một số dự án vốn nước ngoài khác
                    if ((i >= 34) && (i <= 40))
                        continue;

                    temp += parseFloat($(arrCot[k]).eq(i).val());
                }
                $(arrCot[k]).eq(4).val(temp);

                //Tinh toan "DƯ NỢ NGUỒN VỐN ĐỊA PHƯƠNG"
                temp = 0;
                for (i = 43; i <= 62; i++) {
                    temp += parseFloat($(arrCot[k]).eq(i).val());
                }
                $(arrCot[k]).eq(41).val(temp);

                //Tinh toan "QUỸ AN TOÀN CHI TRẢ"                
//                $(arrCot[k]).eq(63).val(parseFloat($(arrCot[k]).eq(63).val()) + parseFloat($(arrCot[k]).eq(62).val()));

                //Tinh toan "SỬ DỤNG VỐN"
                $(arrCot[k]).eq(3).val(parseFloat($(arrCot[k]).eq(4).val()) + parseFloat($(arrCot[k]).eq(42).val())
                        + parseFloat($(arrCot[k]).eq(63).val()));
            }
        }
    }

    //Check xem du lieu da ok chua
    //Neu ok roi thi goi su kien submit du lieu
    function fnCheckThenSubmit() {
//    alert('Bat dau cap nhat du lieu');
//        msls.showMessageBox("Please choose the appropriate button", {
//            title: "This is a message box",
//            buttons: msls.MessageBoxButtons.yesNoCancel
//
//        }).then(function (result) {
//
//            if (result === msls.MessageBoxResult.yes) {
//
//                alert("You have selected Morning");
//
//            } else if (result === msls.MessageBoxResult.no) {
//
//                alert("You Have Selected Afternoon");
//
//            } else if (result === msls.MessageBoxResult.cancel) {
//
//                alert("You have closed this message box");
//
//            }
//
//        });
        var r = confirm("Bạn có thật sự muốn lưu điều chỉnh này không ? OK : Đồng ý, Cancel : Hủy bỏ");
        if (r == true) {
            if (validateRequiredFields()) {
                $("#update").trigger('click');
            }
        } else {
            return;
        }

    }

    function validateRequiredFields() {
        var result = true; //Luu ket qua kiem tra kieu so co dung khong

        //Cac class nubmer2 phai nhap kieu so
        $(".number2").each(function (index) {
            //Kiem tra xem co nhap kieu so khong
            if (isNaN(parseFloat($(this).val()))) {
                result = false;
                //Neu nguoi dung khong nhap dung kieu du lieu
                //Dua ra canh bao
                $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                return false;
            }
            else {
                //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                if (parseFloat($(this).val()) > 9999999999) {
                    result = false;

                    //Dua ra canh bao
                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Giá trị bạn nhập vượt quá giới hạn!');
                    return false;
                }
            }
        });

        return result;
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
</script>
