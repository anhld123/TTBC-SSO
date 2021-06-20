<%-- 
    Document   : add_excel
    Created on : Jan 22, 2016, 8:35:20 AM
    Author     : BAOANH
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <sj:head/>
        <%--<sx:head/>--%>
        <style type="text/css">
        .ui-state-default  .ui-icon {
               background-image: url('img/variable_in.PNG');
               background-size: 16px 16px;
               color: red;
           }
                        
            #containTree{
                width: 18%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
                /*background: window;*/
            }
            #containParm{
                width: 81%;
                height: 450px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
                /*background: yellow;*/
            }
        </style>
        <script>
            $(document).ready(function (obj) {
//                $(".NGAY_SL").css({"width": "80px"});
//                $('#A_anchor').css("background-image", "url(img/parameter_in.PNG)"); 
//                $('#A').css("background-size", "16px 16px"); 
//alert(obj.text())
//deleteTreeNode('%{echo}', obj);
    
            });
            function createTreeNode(obj)
            {
                var action ;
                var fileTemplate=$('#fileTemplate').val();
                var type_id=obj[0].id;
                //neu la tham so
                 if(type_id=='parameter')
                 {
                     action ='configEditParameter.action';
                    $("#result2").load(action +"?parameter=new&fileTemplate="+fileTemplate);
                 }
                 else if(type_id=='query') //neu la truy van
                 {
                     action ='configEditQuery.action';
                    $("#result2").load(action +"?id=new&fileTemplate="+fileTemplate);
                 }
                 else //truong hop khac
                 {
                     $('#Message').html("<h2 style='color: red'>Bạn không thể thêm vào treeitem này được  ! </h2>");
                 }
//                window.location.href=action+"?Message=TUNGNV";
                
            }
            
            function deleteTreeNode(obj)
            {
                var action ='deleteParameter.action';
                var fileTemplate=$('#fileTemplate').val();
                var Message=obj[0].id;
                
                var r = confirm("Bạn có chắc chắn muốn xóa tham số này không, Khi xóa bạn sẽ không thể lấy lại được ? OK : Đồng ý, Cancel : Hủy bỏ");
                if (r != true) {
                   // $("#divParams").load(action);
                    return false;
                }
                
                 $("#result2").load(action +"?Message="+Message+"&fileTemplate="+fileTemplate);
                
                return true;
            }
            function modifyTreeNode(obj)
            {
                var action ='deleteParameter.action';
                var fileTemplate=$('#fileTemplate').val();
                var Message=obj[0].id;
                alert(Message);
//                var r = confirm("Bạn có chắc chắn muốn xóa tham số này không, Khi xóa bạn sẽ không thể lấy lại được ? OK : Đồng ý, Cancel : Hủy bỏ");
//                if (r != true) {
//                   // $("#divParams").load(action);
//                    return false;
//                }
                
//                 $("#result2").load(action +"?Message="+Message+"&save_id="+save_id);
                
                return true;
            }
            $(function(){
                $.subscribe('treeBefore', function (event, data){
                    $("#loadingImageDiv_rpt").show();
//                alert("treeClicked");// check if script called on page load
                //Get the next item, this is the tree node object is selected, many operations need it
//                var item = event.originalEvent.data.rslt.obj;
                //For example, we want to select the node id suggest, you can write like this
//                alert ('Clicked ID : ' + item.attr ("id"));
                });
            });
             $(function(){
                $.subscribe('treeComplete', function (event, data){
                    $("#loadingImageDiv_rpt").hide();
                });
            });
        </script>
<!--this.create(obj);-->
    </head>
    <body>
        <s:form id="tree" action="loaddata" theme="simple">
            <div id="containTree" class="result ui-widget-content ui-corner-all">
                <s:hidden id="fileTemplate" name="fileTemplate"/>
            <sjt:tree
                id="treeTypes"
                jstreetheme="apple"
                openAllOnLoad="true"
                onBeforeTopics="treeBefore"
                onCompleteTopics="treeComplete"
                 contextmenu="{
                                    items: { 
                                    'create': {
                                         'label' : 'Thêm mới',
                                         'action' : function(obj) {this.create(obj);createTreeNode(obj);}
                                    },
                                    'rename' :  {
                                         'label' : 'Sửa',
                                         'action' : function(obj) {modifyTreeNode(obj);}
                                    },
                                    'ccp' : false,
                                    'remove' : { 
                                    'label': 'Xóa', 
                                    'action':  function (obj) { if(deleteTreeNode(obj)) this.remove(obj);  }
                                    } 
                                    } 
                                }"
                types="{
                            'valid_children' : [ 'root' ],
                            'types' : {
				'root' : {
					'icon' : { 
						'image' : 'img/report_icon.png' 
					},
					'valid_children' : [  'parameter', 'query','variable' ]
				},
				'parameter' : {
					'icon' : { 
						'image' : 'img/parameter_in.PNG' 
					},
					'valid_children' : [ 'parameter', 'query','variable' ]
				},
				'query' : {
					'icon' : { 
						'image' : 'img/query_in.PNG' 
					},
					'valid_children' : [  'parameter', 'query','variable' ]
				},
				'variable' : {
					'icon' : { 
						'image' : 'img/variable_in.PNG' 
					},
					'valid_children' : [ 'none' ]
				}
                            }
                        }">
                <s:iterator value="#attr.objExcelTemplate" var="modelView" status="rowstatus">
                <sjt:treeItem title="%{fileName}" type="root">
                    <!----------------- cho phần tham số ------------------>
                    <sjt:treeItem id="parameter" title="parameter" type="parameter">
                        <s:iterator value="#attr.parameter" var="parameterView" status="rowstatus">
                            <s:url var="edit_parameter" value="/configEditParameter.action" escapeAmp="false">
                                <s:param name="parameter" value="key"/>   
                                 <s:param name="fileTemplate" value="fileTemplate"/>                                                            
                            </s:url>
                            <sjt:treeItem id="parameter_%{key}"  title="%{key}" type="parameter" targets="result2"
                                          href="%{edit_parameter}" name="parameter"/>
                        </s:iterator>   
                    </sjt:treeItem>
                    <!----------------- cho phần truy vấn -------------------->
                     <sjt:treeItem id="query" title="query" type="query">
                        <s:iterator value="#attr.lstQuery" var="queryView" status="rowstatus">
                            <s:url var="edit_query" value="/configEditQuery.action" escapeAmp="false">
                                <s:param name="id" value="%{id}"/>
                                <s:param name="fileTemplate" value="%{fileTemplate}"/>
                            </s:url>
                            <sjt:treeItem id="query_%{id}"  title="%{desCription}" type="query" targets="result2"
                                          href="%{edit_query}" name="id" />
                        </s:iterator>   
                    </sjt:treeItem>
                    <!---------------- cho phần biến --------------------->
                    <!--<sjt:treeItem id="variable" title="variable" type="variable" targets="result2">
                        <s:iterator value="#attr.lstObjGroup" var="modelView" status="rowstatus">
                            <s:url var="echo" value="/configTemplate.action">
                                <s:param name="Message" value="%{sKey}"/>
                            </s:url>
                            <sjt:treeItem id="%{sKey}"  title="%{sDesc}" type="variable" targets="result2"
                                          href="%{echo}" name="Message" />
                        </s:iterator>   
                    </sjt:treeItem>-->
                </sjt:treeItem>
                </s:iterator>
            </sjt:tree>
                </div>
            <div id="containParm" class="result ui-widget-content ui-corner-all">
            <strong></strong>
            <img id="loadingImage-next" src="img/loaderB32.gif" style="display:none"/>
            <div id="loadingImageDiv_rpt" style="display: none;">
                <img id="loadingImage_rpt" src='img/loading.gif' border='0'>
            </div>
            <div id="result2"></div>
            
            </div>            
        </s:form>
    </body>
</html>
