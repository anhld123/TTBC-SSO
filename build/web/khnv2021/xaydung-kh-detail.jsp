<%@page import="javax.servlet.ServletConfig"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>

    <SCRIPT language="javascript">
        $(document).ready(function () {
            //Format cac truong input.number can le phai
//            $('input.number').css({"text-align": "right"});
//            $('input.number2').css({"text-align": "right"});
//
//            //An di cac cot chuc nang
//            $('.hideColumn').hide();
//
//            //Cac truong bang so --> se co so truong = 0
//            $('.number').number(true, 0);
//
//            //Cac truong bang so --> se co so truong = 0
//            $('.number2').number(true, 2);
//
//            $(".KH_STT_HT").css({"width": "30px"});
//            $(".KH_CHI_TIEU").css({"width": "800px"});
//            $('.KH_STT_HT').css({"text-align": "center"});

            //CuongBM: Boi dam mot so dong cho de nhin
//            $("#tableKhnv tr").eq(2).css({"background-color": "#DCDCDC"});
//            $("#tableKhnv tr").eq(2).find("input").css({"background-color": "#DCDCDC"});
//
//            $("#tableKhnv tr").eq(10).css({"background-color": "#DCDCDC"});
//            $("#tableKhnv tr").eq(10).find("input").css({"background-color": "#DCDCDC"});
//
//            $("#tableKhnv tr").eq(61).css({"background-color": "#DCDCDC"});
//            $("#tableKhnv tr").eq(61).find("input").css({"background-color": "#DCDCDC"});
            var rowCount = $("#tableKhnv td").closest("tr").length;
             var kh_congthuc = ".KH_CHITIEU_CHAR";
            for (i = 0; i < rowCount; i++)
            {
                if($(kh_congthuc).eq(i).val()=='Y')
                {
//                    alert($(kh_congthuc).eq(i).val());
                     $("#tableKhnv tr").eq(i+2).css({"background-color": "#DCDCDC"});
                    $("#tableKhnv tr").eq(i+2).find("input").css({"background-color": "#DCDCDC"});
                }
            }
        });

        //Xu ly tinh tong cho tung dong
        function evaluateSum() {
            try {
                var temp = 0;
                var kh_capht = ".KH_CAPHT";
                var kh_congthuc = ".KH_CONGTHUC";
                var kh_ma_ct = ".KH_MA_CT";
                var kh_uoc = ".KH_UOC_TH";
                var kh_nam = ".KH_KH_NAM";
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
                            //cắt công thức đưa về mảng
                            var valNew = congthuc.split('+');
                            var tong_uoc=sum_mact(valNew,kh_uoc);
                            if(tong_uoc!=0)
                                $(kh_uoc).eq(i).val(tong_uoc);
                            var tong = sum_mact(valNew, kh_nam);
                            if(tong !=0)
                                $(kh_nam).eq(i).val(tong);
//                            console.log('-------------- tong = '+tong)
//                            console.log('capht=' + $(kh_capht).eq(i).val().toString() + ' congthuc=' + $(kh_congthuc).eq(i).val() + ' chitieu=' + $(kh_ma_ct).eq(i).val() +
//                                    ' Tong=' + tong);
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
//                if(arrMact.indexOf(ma_ct)>=0) 
                /*
                for (j = 0; j < arrMact.length; j++)
                {
                    if (arrMact[j] == ma_ct)
                    //nếu tìm thấy mã chỉ tiêu ở trong mảng thì cộng vào tổng
//                    if(arrMact.indexOf(ma_ct)>=0) 
                    {
                        tong += parseFloat($(name_class).eq(i).val());
//                        alert(' ma_ct=' + ma_ct + ' sodu=' + $(name_class).eq(i).val());
//                        console.log('arrMact='+arrMact.toString()+' tong=' + tong + ' ma_ct=' + ma_ct+' sodu=' + $(name_class).eq(i).val());
                    }
                }
                */
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
        //Check xem du lieu da ok chua
        //Neu ok roi thi goi su kien submit du lieu
        function fnCheckThenSubmit() {
            //alert("Chay thu nao");
            if (validateRequiredFields()) {
                $("#update").trigger('click');
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
                } else {
                    //Neu la kieu so --> Kiem tra xem kieu nhap co > 0 
                    if (parseFloat($(this).val()) < 0) {
                        result = false;

                        //Dua ra canh bao
                        $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn không được nhập giá trị < 0!');
                        return false;
                    }

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
        ;

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
    </SCRIPT>
    <body>
        <div style="margin: 7px 7px 7px 7px;">

            <table border="1px" id="tableKhnv" class="tableKhnv">
                <tr class="tbhead">
                    <th>STT</th>
                    <th class="hideColumn">Mã chỉ tiêu</th>
                    <th class="KH_CHI_TIEU">Chỉ tiêu</th>
                    <th>Ước thực hiện đến 31/12/<s:property value="namBc"/></th>
                    <th>Kế hoạch năm <s:property value="namSau"/></th>

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

                <s:iterator value="xdkhModelList">
                   
                        <tr class="cscontent" >
                    <%--</s:else>--%>
                        
                        <!-- CuongBM: Nếu KT_DN: Được nhập = N, người dùng không được phép nhập, thay đổi -->
                        <s:if test="KH_DN.equalsIgnoreCase('N')">                                     
                            <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_STT_HT'/>" name="KH_STT_HT" class="KH_STT_HT" onfocus="this.select()" readonly="readonly"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_MA_CT'/>" name="KH_MA_CT" class="KH_MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_CHI_TIEU'/>" name="KH_CHI_TIEU" onfocus="this.select()" readonly="readonly"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_UOC_TH'/>" name="KH_UOC_TH" class="KH_UOC_TH number2" onfocus="this.select()" readonly="readonly" 
                                                                                   onblur="if (this.value == '') {this.value = 0};evaluateSum(this)"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_KH_NAM'/>" name="KH_KH_NAM" class="KH_KH_NAM number2" onfocus="this.select()" readonly="readonly" 
                                                                                   onblur="if (this.value == '') {this.value = 0}; evaluateSum(this)"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_DN'/>" name="KH_DN"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_FONTWEIGHT'/>" name="KH_FONTWEIGHT"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CAPHT'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_STT'/>" name="KH_STT"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CONGTHUC'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CHITIEU_CHAR'/>" name="KH_CHITIEU_CHAR" class="KH_CHITIEU_CHAR"/></td>
                            </s:if>

                        <!-- CuongBM: Nếu KT_DN: Được nhập = Y -->
                        <s:if test="KH_DN.equalsIgnoreCase('Y')">                                     
                            <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_STT_HT'/>" name="KH_STT_HT" class="KH_STT_HT" onfocus="this.select()" readonly="readonly"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_MA_CT'/>" name="KH_MA_CT" class="KH_MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_CHI_TIEU'/>" name="KH_CHI_TIEU" onfocus="this.select()" readonly="readonly"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_UOC_TH'/>" name="KH_UOC_TH" class="KH_UOC_TH number2" onfocus="this.select()"  onblur="if (this.value == '') {
                                        this.value = 0}; evaluateSum(this)"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_KH_NAM'/>" name="KH_KH_NAM" class="KH_KH_NAM number2" onfocus="this.select()"  onblur="if (this.value == '') {
                                        this.value = 0}; evaluateSum(this)"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_DN'/>" name="KH_DN"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_FONTWEIGHT'/>" name="KH_FONTWEIGHT"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CAPHT'/>" name="KH_CAPHT" class="KH_CAPHT"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_STT'/>" name="KH_STT"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CONGTHUC'/>" name="KH_CONGTHUC" class="KH_CONGTHUC"/></td>
                            <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CHITIEU_CHAR'/>" name="KH_CHITIEU_CHAR" class="KH_CHITIEU_CHAR"/></td>
                            </s:if>
                    </tr>
                </s:iterator>
            </table>
        </div>
    </body>
</html>
