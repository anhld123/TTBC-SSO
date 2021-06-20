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
        <script type="text/javascript" src="js/excel_template.js"></script>
        <link rel="stylesheet" type="text/css"  href="css/excel_template.css" />
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
                    <sjt:treeItem id="rootTree" title="%{fileName}" type="root">
                    <!----------------- cho phần tham số ------------------>
                    <sjt:treeItem id="parameter" title="parameter" type="parameter" cssClass="abcdef" >
                        <s:iterator value="#attr.parameter" var="parameterView" status="rowstatus">
                            <sjt:treeItem id="parameter_%{key}"  title="%{key}" type="parameter" name="parameter" 
                                          ondblclick="insertParameterToQuery(this)" cssClass="abc123"/>
                        </s:iterator>   
                    </sjt:treeItem>
                    <!----------------- cho phần truy vấn -------------------->
                    <sjt:treeItem id="query" title="query" type="query" onclick="onclick()">
                        <s:iterator value="#attr.lstQuery" var="queryView" status="rowstatus">
                            <sjt:treeItem id="query_%{id}"  title="%{id} -> %{desCription}" type="query" name="id" />
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
            </div> 
        </s:form>
    </body>
</html>
