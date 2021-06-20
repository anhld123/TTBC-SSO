<%-- 
Document   : Treeview
Created on : Apr 27, 2014, 10:12:23 AM
Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style type="text/css">
            .css-treeview ul,
            .css-treeview li
            {
                padding: 0;
                margin: 0;
                list-style: none;
            }

            .css-treeview input
            {
                position: absolute;
                opacity: 0;
            }

            .css-treeview
            {
                font: normal 11px "Segoe UI", Arial, Sans-serif;
                -moz-user-select: none;
                -webkit-user-select: none;
                user-select: none;
            }

            .css-treeview a
            {
                color: #00f;
                text-decoration: none;
            }

            .css-treeview a:hover
            {
                text-decoration: underline;
            }

            .css-treeview input + label + ul
            {
                margin: 0 0 0 22px;
            }

            .css-treeview input ~ ul
            {
                display: none;
            }

            .css-treeview label,
            .css-treeview label::before
            {
                cursor: pointer;
            }

            .css-treeview input:disabled + label
            {
                cursor: default;
                opacity: .6;
            }

            .css-treeview input:checked:not(:disabled) ~ ul
            {
                display: block;
            }

            .css-treeview label,
            .css-treeview label::before
            {
                background: url("img/icons_treeview.png") no-repeat;
            }

            .css-treeview label,
            .css-treeview a,
            .css-treeview label::before
            {
                display: inline-block;
                height: 16px;
                line-height: 16px;
                vertical-align: middle;
            }

            .css-treeview label
            {
                background-position: 18px 0;
            }

            .css-treeview label::before
            {
                content: "";
                width: 16px;
                margin: 0 22px 0 0;
                vertical-align: middle;
                background-position: 0 -32px;
            }

            .css-treeview input:checked + label::before
            {
                background-position: 0 -16px;
            }

            /* webkit adjacent element selector bugfix */
            @media screen and (-webkit-min-device-pixel-ratio:0)
            {
                .css-treeview 
                {
                    -webkit-animation: webkit-adjacent-element-selector-bugfix infinite 1s;
                }

                @-webkit-keyframes webkit-adjacent-element-selector-bugfix 
                {
                    from 
                    { 
                        padding: 0;
                    } 
                    to 
                    { 
                        padding: 0;
                    }
                }
            }  
        </style>
    </head>
    <body>
        <div class="css-treeview">
            <ul>
                <s:iterator value="dmctieu" var="level1">
                    <s:if test="#level1.parent == 0">
                        <li><input type="checkbox" id='<s:property value="id"/>' checked /><label for='<s:property value="id"/>'><s:property value="chiTieu"/>&nbsp;<s:property value="name"/></label>
                            <ul>
                                <s:iterator value="dmctieu" var="level2">
                                    <s:if test="#level2.parent == #level1.id && #level1.id == #level1.id">
                                        <li><input type="checkbox" id='<s:property value="id"/>' /><label for='<s:property value="id"/>'><s:property value="chiTieu"/>&nbsp;<s:property value="name"/></label>
                                            <ul>
                                                <s:iterator value="dmctieu" var="level3">
                                                    <s:if test="#level3.parent == #level2.id">
                                                        <li><a href="javascript:getchitieu('<s:property value="chiTieu"/>');"><s:property value="chiTieu"/>&nbsp;<s:property value="name"/></a></li>
                                                    </s:if>
                                                </s:iterator>
                                            </ul>
                                        </li>
                                    </s:if>
                                </s:iterator>
                            </ul>
                        </li>
                    </s:if>
                </s:iterator>
            </ul>
        </div>
    </body>
</html>
