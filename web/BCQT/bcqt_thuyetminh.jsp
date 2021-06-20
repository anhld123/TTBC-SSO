<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />

<s:head/>
<sj:head/>

<!DOCTYPE html>
<html>
    <head>
        <style type="text/css">
            .auto-style1 {
                text-align: right;
                font-size: 12pt;
            }
            .auto-style2 {
                text-align: left;
                padding-bottom: 0;
                padding-left: 0;
                padding-right: 0;
                padding-top: 0;
                margin-bottom: 0;
                margin-left: 0;
                margin-right: 0;
                margin-top: 0;
                font-size: 12pt;
            }
            .auto-style3 {
                text-align: center;
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12pt;
            }
            .auto-style4 {
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12pt;
            }
            .auto-style5 {
                margin: 0;
                padding: 0;
                text-align: left;
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12pt;
            }
            .auto-style6 {
                margin: 0;
                padding: 0;
                text-align: left;
                font-family: Arial, Helvetica, sans-serif;
                font-size: 12pt;
            }
            .auto-style7 {
                font-size: 12pt;
            }
            input[readonly] {
                /*styling info here*/
                background-color: #99ffff;
            }

            input[type="radio"] {
                -webkit-appearance: checkbox; /* Chrome, Safari, Opera */
                -moz-appearance: checkbox;    /* Firefox */
                -ms-appearance: checkbox;     /* not currently supported */
            }
        </style>
        <script>
            $(document).ready(function() {
//                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $('#ui-datepicker-div').css('clip', 'auto');
                $(".TD_HEADER").css({"width": "30px"});
                $(".TEXT_COL").css({"width": "100%"});
                $('#divExportReport').css({
                    'overflow': 'auto',
                    'height': 'auto'
                });
            });
            $('.TEXT_COL').focus(function() {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEXT_COL').blur(function() {
                $(this).closest('tr').removeClass('highlight_row');
            });


            // PHAN XU LY KHI CHON
            function load(obj, index) {
                if (obj.checked === true) {
//                    alert('aa');
                    var content = $('#D1_' + index).val();
                    var title = $('#TEN_' + index).val();
                    var code = $('#MA_' + index).val();
                    var pos_cd = $('#MAPGD_' + index).val();
                    var ngaybc = $('#NGAYBC_' + index).val();
                    $("#detail_div").val(content);
                    $("#title_Txt").val(title);
                    $("#code_Txt").val(code);
                    $("#pos_cd_ID").val(pos_cd);
                    $("#input_date").val(ngaybc);
                } else {
                    $("#detail_div").val('');
                    $("#title_Txt").val('');
                    $("#code_Txt").val('');
                    $("#pos_cd_ID").val('');
                    var ngaybc = $("[name='ngay_bc_DATE']").val();
//                    alert('aa');
                    $("#input_date").val(ngaybc);
                }
            }
        </script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
    </head>

    <body>
        <s:form id="id_sv_%{khoa_bcqt}"  theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                THUYẾT MINH BÁO CÁO QUYẾT TOÁN
            </div>
            <s:hidden name="khoa_bcqt"/>        
            <s:hidden name="UserName"/>        
            <s:hidden name="Grade"/>        

            <div class="auto-style1" style="overflow: scroll; height: 160px;">

                <table style="width: 100%"  border="1" class="editDelete" id="tablepl01" align="center">
                    <tr>		
                        <th class="auto-style3"><strong>Chọn</strong></th>
                        <th class="auto-style3"><strong>Mã đơn vị</strong></th>
                        <th class="auto-style3"><strong>Mã TM</strong></th>
                        <th class="auto-style3"><strong>Tiêu đề</strong></th>
                        <th class="auto-style3"><strong>Ngày thuyết minh</strong></th>
                        <th class="auto-style3"><strong>Trạng thái</strong></th>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <tr>   
                            <td align="center" bgcolor="#99ffff"
                                style="width: 50px;">
                                <input type="radio" name="chon" id="chonID" onclick="load(this, '<s:property  value="%{#rowstatus.index}" />');"/>
                            </td>
                            <td style="width: 100px;">
                                <input type="text" value="<s:property  value="MAPGD" />" 
                                       id="MAPGD_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" 
                                       class="TEXT_COL" onfocus="this.select()"           
                                       readonly="true"/>                         
                            </td>
                            <td>
                                <input type="text" value="<s:property  value="MA" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" 
                                       id="MA_<s:property  value="%{#rowstatus.index}" />"
                                       class="TEXT_COL" onfocus="this.select()"          
                                       readonly="true"/>                         
                            </td>
                            <td style="width: 350px;">
                                <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"
                                       id="TEN_<s:property  value="%{#rowstatus.index}" />"
                                       class="TEXT_COL" onfocus="this.select()"           
                                       readonly="true"/>                         
                            </td>
                            <td style="width: 50px;">
                                <input type="text" value="<s:property  value="NGAYBC" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NGAYBC" 
                                       class="TEXT_COL" onfocus="this.select()"       
                                       id="NGAYBC_<s:property  value="%{#rowstatus.index}" />"
                                       readonly="true"
                                       style="text-align: right;width: 100%;"/>                         
                            </td>

                            <td style="width: 50px;">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="%{getStatusDescript(D3)}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3"  
                                       readonly="true"
                                       style="text-align: center;"/>                                                                  

                            </td> 

                        <input type="hidden" id="D1_<s:property  value="%{#rowstatus.index}" />" 
                               value="<s:property  value="D1" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                               />                
                        </tr>
                        <s:set var="st_total" value = "lstDulieuNt.size()" />

                    </s:iterator>
                </table>
            </div>
            <hr />
            <div class="auto-style1" >
                <table style="width: 100%">
                    <tr>
                        <td style="width: 15%;">
                            <p class="auto-style2">Mã Thuyết minh:</p>
                        </td>
                        <td style="width: 340px;">
                            <div id="code_div">
                                <input type="text" name="tm.code" 
                                       placeholder="Chọn kiểm tra để tạo mã TM mới..." 
                                       style="width: 320px;" id="code_Txt"
                                       readonly="true"
                                       value="<s:property value='tm.code'/>"/>                                                                                              
                                &nbsp;
                            </div>

                            <s:url id="checkcode_Url" action="CHECK_BCQT_THUYETMINH"></s:url>                                    
                            <sj:a href="%{checkcode_Url}"
                                  formIds="id_sv_%{khoa_bcqt}"
                                  targets="code_div"     
                                  id="checkcodeBtn_Id"
                                  onBeforeTopics="before-next"
                                  onCompleteTopics="after-next"
                                  >                    
                            </sj:a>                            
                        </td>
                        <td align="left">
                            <a href="#" onclick="onCheck();"><u>Kiểm tra</u></a>                             
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="auto-style2">Tiêu đề: 

                        </td>
                        <td colspan="2">
                            <input type="text" name="tm.title" placeholder="Thuyết minh cho báo cáo..." 
                                   style="width: 320px;font-weight: bold;" id="title_Txt"                                   
                                   />
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p class="auto-style2">Thuyết minh cho ngày: </p>
                        </td>
                        <td colspan="2">
                            <input type="text" value="<s:property value='ngay_nhaplieu'/>" style="width: 100px;"
                                   readonly="true"
                                   id="input_date"
                                   name="ngay_nhaplieu"/>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="3">
                            <p class="auto-style2">Nội dung: </p>
                            <textarea name="tm.content" id="detail_div" rows="5" style="width: 100%"></textarea>
                        </td>                
                    </tr>
                </table>
                <!--                PHAN THUOC TINH HIDDEN PHUC VU CAP NHAT-->
                <input type="hidden" name="tm.pos_cd" id="pos_cd_ID"/>
                <input type="hidden" name="action_type" id="action_type_ID" value="UPDATE"/>
                <hr />	
                <s:url id="save_Url" action="SAVE_BCQT_THUYETMINH"></s:url>
                <sj:a href="%{save_Url}"
                      formIds="id_sv_%{khoa_bcqt}"
                      targets="contentDiv"     
                      id="saveBtn_Id"
                      >                    
                </sj:a>
                <input name="SaveBtn" type="button" value="Lưu trữ" onclick="onSaveData('UPDATE');" />
                <input name="DeleteBtn" type="button" value="Xoá TM" onclick="onSaveData('DELETE');"/>
                <script>
                    $.subscribe('before-next',
                            function(event, data) {
                                $("#contentDiv").empty();
                                $("#contentDiv").hide();
                            });

                    $.subscribe('after-next',
                            function(event, data) {
                                $("#contentDiv").show();
//                                $('#code_div').load(
//                                        location.href + ' #code_div'
//                                        );
                            });

                    function onCheck() {
                        // DAT LAI GIA TRI NULL
                        $('#title_Txt').val('');
                        $('#detail_div').val('');
                        var ngaybc = $("[name='ngay_bc_DATE']").val();
                        //alert('aa');
                        $("#input_date").val(ngaybc);

                        // CAP NHAT THONG TIN
                        $('#action_type_ID').val('CREATE');
                        $('#checkcodeBtn_Id').click();
                    }

                    function onSaveData(action) {
                        var code = $('#code_Txt').val();
                        var title = $('#title_Txt').val();
                        var content = $('#detail_div').val();
                        if (code === null || code.length === 0
                                || title === null || title.length === 0
                                || content === null || content.length === 0) {
                            alert('Bạn phải nhập đầy đủ thông tin thuyết minh trước khi lưu trữ hoặc tạo mới.');
                            return;
                        } else {
                            if (action === 'DELETE') {
                                var answer = confirm('Bạn có chắc chắn muốn xoá thuyết minh?');
                                if (answer)
                                    $('#action_type_ID').val('DELETE');
                                else
                                    return;
                            }
                            $('#saveBtn_Id').click();
//                            $('#idTMtmp').click();
                        }
                    }
                </script>
            </div>

            <div style="float: left;" id="contentDiv"></div>
        </s:form>
    </body>
</html>
