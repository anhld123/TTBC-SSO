<%-- 
    Document   : smsbankingcustomer-infor
    Created on : Jun 23, 2016, 3:23:47 PM
    Author     : Trung
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:head/>
<sj:head/>


<!DOCTYPE html>
<style>
    .defaultText {
        font-family:Tahoma;
        font-size: 10pt; 
        color: #009900; 
    }   
    .editableText {
        font-family:Tahoma;
        font-size: 10pt; 
        color: #000099;
    }   
    .readonlyText {
        font-family:Tahoma;
        font-size: 10pt; 
        color: #009900;
        font-weight: bold;
        background: #DDFFDD;
    }   
</style>

<s:form id="customer-infor-form" 
        theme="simple">
    <div class="defaultText">


        <h4>Thông tin khách hàng:</h4>
        <table class="defaultText" style="width: 100%;">
            <tr>
                <td>
                    Cif:
                </td>
                <td>
                    <s:textfield name="customer.cifNo"
                                 cssClass="readonlyText"
                                 cssStyle=""
                                 readonly="true"
                                 size="40"/>
                </td>
            </tr>            
            <tr>
                <td>
                    Tài khoản:
                </td>
                <td>
                    <s:textfield name="customer.bankAccount"
                                 cssClass="readonlyText"
                                 readonly="true"
                                 size="40"/>
                </td>
            </tr>
            <tr>
                <td>
                    Tên KH:
                </td>
                <td>
                    <s:textfield name="customer.name" id="customerNameId"
                                 cssClass="editableText"                                 
                                 size="40"/>
                </td>
            </tr>
            <tr>
                <td>
                    Số điện thoại:
                </td>
                <td>
                    <s:textfield name="customer.phoneNumber" id="phoneNumberId"
                                 cssClass="editableText"                                 
                                 />
                    <!--                    onkeydown="javascript:backspacerDOWN(this,event);" 
                                                     onkeyup="javascript:backspacerUP(this,event);"-->
                    (*) Dãy số viết liền, không có ký tự đặc biệt
                    <script>
                        var zChar = new Array(' ', '(', ')', '-', '.');
                        var maxphonelength = 13;
                        var phonevalue1;
                        var phonevalue2;
                        var cursorposition;

                        function ParseForNumber1(object) {
                            phonevalue1 = ParseChar(object.value, zChar);
                        }
                        function ParseForNumber2(object) {
                            phonevalue2 = ParseChar(object.value, zChar);
                        }

                        function backspacerUP(object, e) {
                            if (e) {
                                e = e
                            } else {
                                e = window.event
                            }
                            if (e.which) {
                                var keycode = e.which
                            } else {
                                var keycode = e.keyCode
                            }

                            ParseForNumber1(object)

                            if (keycode >= 48) {
                                ValidatePhone(object)
                            }
                        }

                        function backspacerDOWN(object, e) {
                            if (e) {
                                e = e
                            } else {
                                e = window.event
                            }
                            if (e.which) {
                                var keycode = e.which
                            } else {
                                var keycode = e.keyCode
                            }
                            ParseForNumber2(object)
                        }

                        function GetCursorPosition() {

                            var t1 = phonevalue1;
                            var t2 = phonevalue2;
                            var bool = false
                            for (i = 0; i < t1.length; i++)
                            {
                                if (t1.substring(i, 1) != t2.substring(i, 1)) {
                                    if (!bool) {
                                        cursorposition = i
                                        bool = true
                                    }
                                }
                            }
                        }

                        function ValidatePhone(object) {

                            var p = phonevalue1

                            p = p.replace(/[^\d]*/gi, "")

                            if (p.length < 3) {
                                object.value = p
                            } else if (p.length == 3) {
                                pp = p;
                                d4 = p.indexOf('(')
                                d5 = p.indexOf(')')
                                if (d4 == -1) {
                                    pp = "(" + pp;
                                }
                                if (d5 == -1) {
                                    pp = pp + ")";
                                }
                                object.value = pp;
                            } else if (p.length > 3 && p.length < 7) {
                                p = "(" + p;
                                l30 = p.length;
                                p30 = p.substring(0, 4);
                                p30 = p30 + ")";

                                p31 = p.substring(4, l30);
                                pp = p30 + p31;

                                object.value = pp;

                            } else if (p.length >= 7) {
                                p = "(" + p;
                                l30 = p.length;
                                p30 = p.substring(0, 4);
                                p30 = p30 + ")"

                                p31 = p.substring(4, l30);
                                pp = p30 + p31;

                                l40 = pp.length;
                                p40 = pp.substring(0, 8);
                                p40 = p40 + "-"

                                p41 = pp.substring(8, l40);
                                ppp = p40 + p41;

                                object.value = ppp.substring(0, maxphonelength);
                            }

                            GetCursorPosition()

                            if (cursorposition >= 0) {
                                if (cursorposition == 0) {
                                    cursorposition = 2
                                } else if (cursorposition <= 2) {
                                    cursorposition = cursorposition + 1
                                } else if (cursorposition <= 5) {
                                    cursorposition = cursorposition + 2
                                } else if (cursorposition == 6) {
                                    cursorposition = cursorposition + 2
                                } else if (cursorposition == 7) {
                                    cursorposition = cursorposition + 4
                                    e1 = object.value.indexOf(')')
                                    e2 = object.value.indexOf('-')
                                    if (e1 > -1 && e2 > -1) {
                                        if (e2 - e1 == 4) {
                                            cursorposition = cursorposition - 1
                                        }
                                    }
                                } else if (cursorposition < 11) {
                                    cursorposition = cursorposition + 3
                                } else if (cursorposition == 11) {
                                    cursorposition = cursorposition + 1
                                } else if (cursorposition >= 12) {
                                    cursorposition = cursorposition
                                }

                                var txtRange = object.createTextRange();
                                txtRange.moveStart("character", cursorposition);
                                txtRange.moveEnd("character", cursorposition - object.value.length);
                                txtRange.select();
                            }

                        }

                        function ParseChar(sStr, sChar)
                        {
                            if (sChar.length == null)
                            {
                                zChar = new Array(sChar);
                            }
                            else
                                zChar = sChar;

                            for (i = 0; i < zChar.length; i++)
                            {
                                sNewStr = "";

                                var iStart = 0;
                                var iEnd = sStr.indexOf(sChar[i]);

                                while (iEnd != -1)
                                {
                                    sNewStr += sStr.substring(iStart, iEnd);
                                    iStart = iEnd + 1;
                                    iEnd = sStr.indexOf(sChar[i], iStart);
                                }
                                sNewStr += sStr.substring(sStr.lastIndexOf(sChar[i]) + 1, sStr.length);

                                sStr = sNewStr;
                            }

                            return sNewStr;
                        }

                    </script>
                </td>
            </tr>            
            <tr>
                <td>
                    Trạng thái:
                </td>
                <td>
                    <s:select headerKey="-1"
                              list="#{'A':'A - Hoạt động','C':'C - Đóng'}"
                              name="customer.status" 
                              />

                </td>
            </tr>

            <s:hidden name="customer.posCode"/>

            <tr>
                <td colspan="2">
                    <hr/>
                </td>
            </tr>

            <tr>
                <s:url id="submitDataUrl" action="SMSUpdateCustomer.action">                                            
                </s:url>

                <sj:a id="submitData_id"  
                      href="%{submitDataUrl}"                                                                                                
                      formIds="customer-infor-form"                        
                      targets="messageDiv"
                      button="false"                                                                                 
                      theme="simple"></sj:a>


                    <td colspan="2" align="right" style="padding-right: 150px;">
                        <a href="#" onclick="submitClick();">Lưu DL</a> &nbsp; 

                        <script>
                            function submitClick() {
                                var customerName
                                        = $("#customerNameId").val();
                                var phoneNumber
                                        = $("#phoneNumberId").val();

                                if (customerName === '' ||
                                        phoneNumber === '') {
                                    alert('Bạn phải nhập tên KH hoặc SĐT.');
                                    return false;
                                }

                                if (!isNumeric(phoneNumber)
                                        || (phoneNumber.length !== 11
                                                && phoneNumber.length !== 10)) {
                                    alert('Bạn nhập SĐT chưa đúng. Hãy nhập lại.');
                                    $("#phoneNumberId").val('');
                                    $("#phoneNumberId").focus();
                                    return false;
                                }

                                $("#submitData_id").trigger("click");
                            }

                            function isNumeric(n) {
                                return !isNaN(parseFloat(n)) && isFinite(n);
                            }
                        </script>
                        <a href="javascript:closeWindow();">Đóng MH</a>
                    </td>                
                </tr>
            </table>

            <script language="javascript" type="text/javascript">
                function closeWindow() {
            <%
                session.removeAttribute("username");
                session.removeAttribute("reportGrade");
            %>
                    if (navigator.userAgent.indexOf('Chrome') !== -1
                            && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                        window.open('', '_self', '');
                        self.close();
                        return false;
                    } else {
                        window.open('', '_parent', '');
                        window.close();
                    }
                }
        </script>

    </div>
    <div id="messageDiv"></div>                  
</s:form>