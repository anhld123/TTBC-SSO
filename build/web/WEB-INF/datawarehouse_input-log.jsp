<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head/>


<html>
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    </head>
    
    <s:form id="dtw_log_form_ID" action="#">
        <p style="color: red; font-family: Arial; font-size: 13px;"
           id="message_ID"
           ><s:property value="message" /> &nbsp;
            <s:url action="dtw_view_upload_log.action" id="view_url_0_ID" />            
            <sj:a id="view_upload_file_ID" 
                  href="%{view_url_0_ID}"
                      formIds="dtw_log_form_ID" 
                      targets="upload_detail_ID"                                             
                      cssClass="metroButtonStyle"
                      onBeforeTopics="before-next-0"                                                         
                      onCompleteTopics="after-next-0"
                      button="false"
                >
            <u><b>Chi tiết</b></u> 
            </sj:a>
        </p>
        <s:hidden name="fileName"/>            
    </s:form>
    
    <s:div id="upload_detail_ID"></s:div>
    <script>                     
        $.subscribe('before-next-0',
            function(event, data) {
                $("#upload_detail_ID").empty();
                $("#upload_detail_ID").hide();
            });



    $.subscribe('after-next-0', function(event, data) {
        com_2.loadpage.onTableLoad();
        $("#upload_detail_ID").show();        
    });
    
                            if (!com_2)
                                var com_2 = {};
                            com_2.loadpage = {
                                onTableLoad: function () {
                                    // Gets called when the data loads
                                    $("#search_table th.sortable").each(function () {
                                        $(this).click(function () {
                                            var link = $(this).find("a").attr("href");
                                            $("#upload_detail_ID").load(link, {},
                                                    com_2.loadpage.onTableLoad);
                                            return false;
                                        });
                                    });

                                    $("#upload_detail_ID .pagelinks a").each(function () {
                                        $(this).click(function () {
                                            var link = $(this).attr("href");
                                            var rplink = link.replace("gennew=Y", "gennew=N");
                                            $("#upload_detail_ID").load(rplink, {},
                                                    com_2.loadpage.onTableLoad);
                                            return false;
                                        });
                                    });

//                                    $("#divListDonator .pagelinks strong").each(function () {
//                                        var htmlString = $(this).html();
//                                        $(this).text("trang " + htmlString);
//                                    });
                                }
                            };
                        </script>
</html>