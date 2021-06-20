<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Giao chỉ tiêu kế hoạch</title>
        <sx:head/>
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
                width:500px; 
                min-height: 580px; 
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
                font-weight: normal;
                padding: 2px;
            }
            .cscontent td{
                background-color: white;
                text-align: center;
                padding-left:5px;
                padding-top:5px;
                padding-bottom: 5px;
            }
            
            input{
                border: 0px;
            }
            
            input[type="text"]
            {
                width: 95%;
            }
            
            .maPgd{
                width: 80px;
            }
            
            .tenPgd{
                width: 300px;
            }
        </style>
        
        <script>
            $(document).ready(function(){
                //Format cac truong input.number can le phai
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true,0);  
                
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true,2);  
                
                $('.maPGD').css({"text-align": "center"});
                $('#khDuocGiao').css({"text-align": "left"});
                
                
                if(<s:property value="reportGrade"/> == 3){
                    //Neu la cap tw thi an di phan ke hoach giao
                    $("#divKhGiao").hide();
                    $("#posCda").text("Mã CN");
                    $("#posName").text("Tên CN");
                    $("#posKh").text("Kế hoạch năm");                    
                }
                
                if(<s:property value="reportGrade"/> == 2){
                    //Neu la cap cn
                    $("#posCda").text("Mã PGD");
                    $("#posName").text("Tên PGD");
                    $("#posKh").text("Giao kế hoạch");                    
                    
                    //Neu la chi tieu di phuong thi khong hien truong ke hoach giao
                    var ctDP = "<s:property value="ctDP"/>";
                    if(ctDP == "Y") $("#divKhGiao").hide();
                }
                var khDuocGiao = parseFloat($("#khDuocGiao").val());
                //alert(khDuocGiao);
                if(khDuocGiao==999999)
                {
                       $("#khDuocGiao").hide();
                }
                else   $("#khChuaDuocGiao").hide();
                    
            });
            $.subscribe('beforeClick', function(event, data) {
                $("#result").empty();
            });
            //Check xem du lieu da ok chua
            //Neu ok roi thi goi su kien submit du lieu
            function fnCheckThenSubmit(){
                $("#result").text('');
                if(validateRequiredFields()){
                    $("#update").trigger('click');
                }
            }
            
            function validateRequiredFields(){
                var result = true; //Luu ket qua kiem tra kieu so co dung khong
                
                //Cac class nubmer2 phai nhap kieu so
                $(".number2").each(function(index){                    
                    //Kiem tra xem co nhap kieu so khong
                    if(isNaN(parseFloat($(this).val()))){                        
                        result = false;
                        //Neu nguoi dung khong nhap dung kieu du lieu
                        //Dua ra canh bao
                        $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                        return false;
                    }
                    else{
                        //Neu la kieu so --> Kiem tra xem kieu nhap co > 0 
                        if(parseFloat($(this).val()) < 0){
                            result = false;
                            
                            //Dua ra canh bao
                            $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn không được nhập giá trị < 0!');
                            return false;
                        }
                        
                        //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                        if(parseFloat($(this).val()) > 9999999999){
                            result = false;
                            
                            //Dua ra canh bao
                            $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Giá trị bạn nhập vượt quá giới hạn!');
                            return false;
                        }
                    }
                });
                
                //Phan tich
                //Neu la cap tw thi ko can kiem tra dieu kien: TW giao cho chi nhanh = tong cac phong giao dich
                //Neu la cap chi nhanh thi kiem tra
                if(<s:property value="reportGrade"/> == 2){
                    //NEU LA CAP CHI NHANH
                    //Neu khong phai la chi tieu dia phuong thi kiem tra
                    //Tong ke hoach giao cho pgd = ke hoach tw giao cho chi nhanh
                    
                    var ctDP = "<s:property value="ctDP"/>";
                    
                    if(ctDP == "N"){                        
                        //NEU KHONG PHAI CHI TIEU DIA PHUONG
                        //Neu tat ca cac gia tri nhap dung la kieu so thi moi can kiem tra dieu kien "TW giao cho chi nhanh = tong cac phong giao dich"
                        /*
                        if(result == true){
                            var sumGiaoKH = 0;  //Tong cua cac gia tri giao ke hoach
                            $(".giaoKh").each(function(index){
                                sumGiaoKH += parseFloat($(this).val());
                            });

                            var khDuocGiao = parseFloat($("#khDuocGiao").val());
                            if(sumGiaoKH != khDuocGiao && khDuocGiao!=999999){
                                result = false;

                                //Dua ra canh bao
                                $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Tổng KH giao khác KH được giao!');
                            }   
                        }  
                        */
                    }
                }
                
                return result;
            }

            //Disable enter key form submit
            function stopRKey(evt) { 
              var evt = (evt) ? evt : ((event) ? event : null); 
              var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null); 
              if ((evt.keyCode == 13) && (node.type=="text"))  {return false;} 
            } 
            
            //Disable enter key form submit            
            document.onkeypress = stopRKey; 

            
        </script>
    </head>
    <body>
        <div id="container" style="width: 100%;">
            <s:form name="frmdata" id="frmdata" action="save_data_giaokh.action" theme="simple">
                <s:hidden name="namBc" id="namBc"/>
            <div id="divChiTieu" style="">
                <span id="idTitle">Giao kế hoạch</span>
                <hr/>
                
                <table>
                    <tr>
                        <td>
                        Mã chỉ tiêu:
                        <input type="text" name="maCt" id="maCt" value="<s:property value="maCt"/>" readonly="readonly" style="width: 350px; background-color: #E2E8C9; border: 0; "/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        Tên chỉ tiêu:
                        <input type="text" name="tenCt" id="tenCt" value="<s:property value="tenCt"/>" readonly="readonly" style="width: 400px; background-color: #E2E8C9; border: 0"/>
                        </td>
                    </tr>
                    <tr id="rowKhDcGiao">
                        <td>
                            <div id="divKhGiao">
                                Kế hoạch được giao:
                                <input type="text" name="khDuocGiao" id="khDuocGiao" value="<s:property value="khDuocGiao"/>" readonly="readonly" class="number2" style="width: 350px; background-color: #E2E8C9; border: 0; text-align: left"/>
                                <input type="text" name="khChuaDuocGiao" id="khChuaDuocGiao" value="Chưa được giao kế hoạch" readonly="readonly" style="width: 350px; background-color: #E2E8C9; border: 0; text-align: left; color: red"/>
                            </div>
                        </td>
                    </tr>
                </table>
                <hr/>
                
                <div id="divSave">
                    <span id="result" style="color: red">                            
                    </span>
                    <img id="loadingImage" src="img/loading.gif"  style="display:none"/>
                    <input type="button" id="checkThenSubmit" value="Cập nhật" onclick="fnCheckThenSubmit()" style="width:122px;height:25px;color: #116600"/>
                    <sj:submit targets="result" value="Cập nhật" name="update" id="update" indicator="loadingImage" onBeforeTopics="beforeClick"  cssStyle="display: none;"/>
                </div>
                <hr/>
                <div id="divSave" style="color: red; font-weight: initial" >
                    Đơn vị tính: Triệu đồng
                </div>
         
                <table border="1px" id="tableKhnv" class="tableKhnv">
                    <tr class="tbhead">
                        <th class="maPgd"><span id="posCda"></span></th>
                        <th class="tenPgd"><span id="posName"></span></th>
                        <th><span id="posKh"></span></th>
                    </tr>
                    <tr class="tbhead">
                        <th class="maPgd">(1)</th>
                        <th class="tenPgd">(2)</th>
                        <th>(3)</th>                 
                    </tr>    

                    <s:iterator value="cTieuKHoachModelList">
                        <tr class="cscontent">
                            <td><input type="text" value="<s:property value='maPGD'/>" name="maPGD" class="maPGD" onfocus="this.select()" readonly="readonly"/></td>
                            <td><input type="text" value="<s:property value='tenPGD'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            <td> <input type="text" value="<s:property value='giaoKh'/>" name="giaoKh" class="giaoKh number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0};"/></td>
                        </tr>
                    </s:iterator>
                </table>
            </div>
            </s:form>
        </div>
    </body>
</html>
