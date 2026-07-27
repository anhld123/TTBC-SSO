<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Tiện ích hỗ trợ</title>
        <script type="text/javascript" src="js/jquery-2.1.26.js"></script>
        <script type="text/javascript" src="chatbox/jquery-23.7.26.js"></script>
        <link rel="stylesheet" type="text/css"  href="chatbox/css-23.7.26.css" />
    </head>
    <body>
        <div id="ai-chat-container">
            <div class="header">
                <span>Tiện ích hỗ trợ</span>
                <input type="hidden" id="response" value=""> 
            </div>

            <div class="header-options">
                <div class="chat-option-wrap">
                    <span class="chat-option active" data-type="BAOCAO" onclick="changeType('BAOCAO', this)"> &#128196; Báo cáo </span>
                    <span class="chat-option" data-type="MENU" onclick="changeType('MENU', this)"> &#128193; Menu </span>
                    <span class="chat-option" data-type="KHAC" onclick="changeType('KHAC', this)"> &#128172; Khác </span>
                </div>
            </div>

            <div id="chatBox">
                <div id="welcome-loading" class="msg-row">
                    <div class="msg-box ai loading-box">
                        <img src="img/icon.png" class="loading-icon" alt="Đang tải">

                        <span>Đang kiểm tra thông tin máy...</span>
                    </div>
                </div>
            </div>
            <div id="statusType" class="chat-status"> &#128196; Đang tra cứu: <b>Báo cáo</b>
            </div>
            <div class="bottom">
                <input type="hidden" id="chatType" value="BAOCAO">
                <input id="message" type="text" maxlength="200" placeholder="Nhập nội dung...">
                <button class="btn-icon" onclick="sendChat()">
                    <svg viewBox="0 0 24 24">
                    <path
                        d="M2.01 21L23 12 2.01 3 2 10l15 2-15 2z"/>
                    </svg>
                </button>
            </div>
        </div>
    </body>
</html>