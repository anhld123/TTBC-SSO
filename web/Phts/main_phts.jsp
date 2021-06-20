<%-- 
    Document   : exp_excel
    Created on : Jul 14, 2014, 4:17:12 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <sj:head/>
        <script>
//            alert('<s:property value="Grade"/>')
            var change_color = '#FFB951';
            function mover(aa) {
                bgcolor = aa.style.backgroundColor;
                aa.style.backgroundColor = change_color;
            }
            function mout(aa) {
                aa.style.backgroundColor = bgcolor;
            }
            
        function mathongke(macn,ten,ngay_bc,tonghop) {
//            var ht = screen.availHeight / 2;
//            var wt = screen.availWidth / 3 + 50;
            var ht1 = screen.availHeight *0.85;
            var wt1 = screen.availWidth *0.8;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 30;
                                
            var url = "mathongke.action?macn=" + macn + "&tencn=" + ten + "&ngay_bc=" + ngay_bc  + "&tonghop=" + tonghop;
            
            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
            var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        }
        
        function maubieutt35(macn,ten,ngay_bc,tonghop) {
            var ht1 = screen.availHeight *0.6;
            var wt1 = screen.availWidth *0.8;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 10;
                     
            
            var url = "maubieutt35.action?macn=" + macn + "&ten=" + ten + "&ngay_bc=" + ngay_bc + "&tonghop=" + tonghop;
            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
            var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        }
        
        function nhapthucong(macn,ten,ngay_bc,tonghop) {
            var ht1 = screen.availHeight *0.8;
            var wt1 = screen.availWidth *0.8;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 10;
                     
            
            var url = "nhapthucong.action?macn=" + macn + "&ten=" + ten + "&ngay_bc=" + ngay_bc + "&tonghop=" + tonghop;
            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
            var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        }      
        
        function decodeParameter(param) {
            return decodeURIComponent(param.replace(/\+/g, ' '));
         }
        
        </script>      
        <style>
            #container{
                width: 100%;
                height: 460px;
                border: 0px solid;
                padding-left: 0px;        
                /*background: #FFE6B0*/
                overflow: scroll;
            }
            .underline {
                text-decoration: underline;
            }
            
            h3 {
                text-align: center;
            }
            
            #divTitlePhts{
                color: blue; 
                font-weight: bolder; 
                font-size: x-large;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div id="divTitlePhts">&nbsp;&nbsp; KẾT QUẢ PHẢN HỒI TRA SOÁT</div>
        <hr/>    
        <div id="container"  align="center">
        <s:form id="main_phts" action="main_report_phts" theme="simple">

            <table border="1" class="editDelete" style="width: 80%" id="tablepl01" align="center">
                        <tr height="30">

                            <th align = "center"  style="width: 50px;">Mã đơn vị</th>
                            <th style="width: 100px;">Tên đơn vị</th>
                            <th style="width: 90px;">Khai báo mã thống kê</th>
                            <th style="width: 90px;">Mẫu biểu TT 35</th>
                            <th style="width: 90px;">Khác</th>
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                           
                            <s:if test="D4.equalsIgnoreCase('Chưa phản hồi')">
                                <tr style="text-align: center; color:black; font-weight: bold;" onmouseover="mover(this);"  onmouseout="mout(this);">
                                    <td align = "center"  style="width: 30px;"><s:property  value="MA" /></td>
                                    <td align = "left" style="width: 100px;"><s:property  value="TEN" /></td>                                    
                                    <td align = "center"  style="width: 60px;">                                         
                                            <s:property value='D1'/>
                                        </a>
                                    </td>    
                                    <td align = "center"  style="width: 60px;">                                         
                                            <s:property value='D2'/>
                                        </a>
                                    </td> 
                                    <s:if test="D3.equalsIgnoreCase('0')">
                                        <td align = "center"  style="width: 60px;"> 
                                            <!--<a href="javascript:nhapthucong('<s:property value="MA"/>','<s:property value="TEN"/>','<s:property value="ngay_bc"/>')">-->
                                                <s:property value='D3'/>
                                            </a>
                                        </td>
                                    </s:if>
                                    <s:else>
                                        <td align = "center" class="underline" style="width: 60px;"> 
                                            <a href="javascript:nhapthucong('<s:property value="MA"/>','<s:property value="TEN"/>','<s:property value="ngay_bc"/>')">
                                                <s:property value='D3'/>
                                            </a>
                                        </td>
                                    </s:else>
                                </tr>
                            </s:if>
                            <s:else>
                                <s:if test="D5.equalsIgnoreCase('0')" >
                                    <tr style="text-align: center; color: red; font-weight: bold;" onmouseover="mover(this);"  onmouseout="mout(this);">
                                        <td align = "center"  style="width: 30px;"><s:property  value="MA" /></td>
                                        <td align = "left" style="width: 100px;"><s:property  value="TEN" /></td>                                    
                                        <s:if test="D1.equalsIgnoreCase('0')">
                                            <td align = "center"  style="width: 60px;">                                             
                                                    <s:property value='D1'/>                                            
                                            </td>
                                        </s:if>
                                        <s:else>
                                            <td align = "center" class="underline" style="width: 60px;"> 
                                                <a href="javascript:mathongke('<s:property value="MA"/>','<s:property value="TEN"/>','<s:property value="ngay_bc"/>','<s:property value="D15"/>')" style="color: red">
                                                    <s:property value='D1'/>
                                                </a>
                                            </td>
                                        </s:else>
                                        <s:if test="D2.equalsIgnoreCase('0')">
                                            <td align = "center"  style="width: 60px;">                                             
                                                    <s:property value='D2'/>                                           
                                            </td>
                                        </s:if>
                                        <s:else>
                                            <td align = "center" class="underline" style="width: 60px;"> 
                                                <a href="javascript:maubieutt35('<s:property value="MA"/>','<s:property value="TEN"/>','<s:property value="ngay_bc"/>','<s:property value="D15"/>')" style="color: red">
                                                    <s:property value='D2'/>
                                                </a>
                                            </td>
                                        </s:else>
                                        <s:if test="D3.equalsIgnoreCase('0')">
                                            <td align = "center"  style="width: 60px;">                                             
                                                    <s:property value='D3'/>                                            
                                            </td>
                                        </s:if>
                                        <s:else>
                                            <td align = "center" class="underline" style="width: 60px;"> 
                                                <a href="javascript:nhapthucong('<s:property value="MA"/>','<s:property value="TEN"/>','<s:property value="ngay_bc"/>','<s:property value="D15"/>')" style="color: red">
                                                    <s:property value='D3'/>
                                                </a>
                                            </td>
                                        </s:else>                                                                      
                                    </tr>
                                </s:if>
                                <s:else>
                                    <tr style="text-align: center; color: black; font-weight: bold;" onmouseover="mover(this);"  onmouseout="mout(this);">
                                        <td align = "center"  style="width: 30px;"><s:property  value="MA" /></td>
                                        <td align = "left" style="width: 100px;"><s:property  value="TEN" /></td>                                    
                                        <s:if test="D1.equalsIgnoreCase('0')">
                                            <td align = "center"  style="width: 60px;">                                             
                                                    <s:property value='D1'/>                                            
                                            </td>
                                        </s:if>
                                        <s:else>
                                            <td align = "center" class="underline" style="width: 60px;"> 
                                                <a href="javascript:mathongke('<s:property value="MA"/>','<s:property value="TEN"/>','<s:property value="ngay_bc"/>','<s:property value="D15"/>')" style="color: black">
                                                    <s:property value='D1'/>
                                                </a>
                                            </td>
                                        </s:else>
                                        <s:if test="D2.equalsIgnoreCase('0')">
                                            <td align = "center"  style="width: 60px;">                                             
                                                    <s:property value='D2'/>                                           
                                            </td>
                                        </s:if>
                                        <s:else>
                                            <td align = "center" class="underline" style="width: 60px;"> 
                                                <a href="javascript:maubieutt35('<s:property value="MA"/>','<s:property value="TEN"/>','<s:property value="ngay_bc"/>','<s:property value="D15"/>')" style="color: black">
                                                    <s:property value='D2'/>
                                                </a>
                                            </td>
                                        </s:else>
                                        <s:if test="D3.equalsIgnoreCase('0')">
                                            <td align = "center"  style="width: 60px;">                                             
                                                    <s:property value='D3'/>                                            
                                            </td>
                                        </s:if>
                                        <s:else>
                                            <td align = "center" class="underline" style="width: 60px;"> 
                                                <a href="javascript:nhapthucong('<s:property value="MA"/>','<s:property value="TEN"/>','<s:property value="ngay_bc"/>','<s:property value="D15"/>')" style="color: black">
                                                    <s:property value='D3'/>
                                                </a>
                                            </td>
                                        </s:else>                                                                      
                                    </tr>
                                </s:else>                                                                    
                            </s:else>    
                                                            
                        </s:iterator>
                    </table>  
            <p></p>  
        </s:form>  
        </div>
    </body>
</html>
