<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <body>
        <div style="margin: 7px 7px 7px 7px; width: 1300px">            
            <div id="divChuaXD" style="">
                <span class="cTitle">Danh sách PGD chưa xây dựng kế hoạch</span>
                <hr/>
                
                <table border="1px" id="tableKhnv" class="tableKhnv">
                    <tr class="tbhead">
                        <th style="width: 90px;">Mã PGD</th>
                        <th >Tên PGD</th>
                    </tr>
                    
                    <s:iterator value="posModelList">
                        <tr class="cscontent">
                            <td ><input type="text" value="<s:property value='id'/>" name="id" class="id" onfocus="this.select()" readonly="readonly" style="width: 90px; background-color: #E2E8C9; border: 0; text-align: center"/></td>
                            <td><input type="text" value="<s:property value='desc'/>" name="desc" class="desc" onfocus="this.select()" readonly="readonly" style="background-color: #E2E8C9; border: 0; "/></td>                            
                        </tr>
                    </s:iterator>
                </table>
            </div>
            
            <div id="divChiTieu" style="">
                <span class="cTitle">Danh sách chỉ tiêu chưa khớp</span>
                <hr/>
                
                <table border="1px" id="tableKhnv" class="tableKhnv">
                    <tr class="tbhead">
                        <th class="maPOS">Mã CN</th>
                        <th class="tenPOS">Tên CN</th>
                        <th >Mã Chỉ Tiêu</th>
                        <th>Tên Chỉ Tiêu</th>
                        <th>KH TW giao</th>
                        <th>KH CN giao</th>
                    </tr>

                    <s:iterator value="cTChuaKhopModelList">
                        <tr class="cscontent">
                            <td class="maPOS"><input type="text" value="<s:property value='maPOS'/>" name="maPOS" class="maPOS" onfocus="this.select()" readonly="readonly" style="width: 50px; background-color: #E2E8C9; border: 0; text-align: center"/></td>
                            <td class="tenPOS"><input type="text" value="<s:property value='tenPOS'/>" name="tenPOS" class="tenPOS" onfocus="this.select()" readonly="readonly" style="width: 140px; background-color: #E2E8C9; border: 0; text-align: left"/></td>                            
                            <td><input type="text" value="<s:property value='maCT'/>" name="maCT" class="maCT" onfocus="this.select()" readonly="readonly" style="width: 90px; background-color: #E2E8C9; border: 0; text-align: center"/></td>                            
                            <td><input type="text" value="<s:property value='tenCT'/>" name="tenCT" class="tenCT" onfocus="this.select()" readonly="readonly" style="width: 350px; background-color: #E2E8C9; border: 0;"/></td>                            
                            <td><input type="text" value="<s:property value='khGiaoTW'/>" name="khGiaoTW" class="khGiaoTW number2" onfocus="this.select()" readonly="readonly" style="background-color: #E2E8C9; border: 0;"/></td>                            
                            <td><input type="text" value="<s:property value='khGiaoCN'/>" name="khGiaoCN" class="khGiaoCN number2" onfocus="this.select()" readonly="readonly" style="background-color: #E2E8C9; border: 0;"/></td>                            
                        </tr>
                    </s:iterator>
                </table>
            </div>
        </div>
                    
        <script>
            //Format cac truong input.number can le phai
            $('input.number').css({"text-align": "right"});
            $('input.number2').css({"text-align": "right"});

            //Cac truong bang so --> se co so truong = 0
            $('.number').number(true,0);  

            //Cac truong bang so --> se co so truong = 0
            $('.number2').number(true,2);  

            $('.maPGD').css({"text-align": "center"});
            
            if(<s:property value="reportGrade"/> == "3"){
                //Neu la cap trung uong 
            }else if(<s:property value="reportGrade"/> == "2"){
                //Neu la cap CN 
                $(".maPOS").hide();
                $(".tenPOS").hide();
            }
        </script>
    </body>
</html>
