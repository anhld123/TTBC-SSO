var isProcessing = false;

function formatAIMessage(message) {
    if (!message)
        return "";

    message = message.replace(/\\u([0-9a-fA-F]{4})/g, function (match, hex) {
        return String.fromCharCode(parseInt(hex, 16));
    });

    message = message.replace(/u([0-9a-fA-F]{4})/g, function (match, hex) {
        return String.fromCharCode(parseInt(hex, 16));
    });

    message = message
            .replace(/&nbsp;/gi, " ")
            .replace(/&gt;/gi, ">")
            .replace(/&lt;/gi, "<")
            .replace(/&amp;/gi, "&")
            .replace(/&quot;/gi, '"');

    message = message.replace(/\*\*(.*?)\*\*/g, "<strong>$1</strong>");
    message = message.replace(/\*(.*?)\*/g, "<em>$1</em>");

    message = message.replace(/\r\n/g, "\n");
    message = message.replace(/\n/g, "<br>");

    return message.trim();
}

function createAIMessage(message) {
    var row = document.createElement("div");
    row.className = "msg-row";

    var box = document.createElement("div");
    box.className = "msg-box ai ai-message";

    var icon = document.createElement("img");
    icon.src = "img/icon.png";
    icon.className = "ai-icon";
    icon.alt = "AI";

    var content = document.createElement("div");
    content.className = "ai-message-content";
    content.innerHTML = formatAIMessage(message);

    box.appendChild(icon);
    box.appendChild(content);
    row.appendChild(box);

    return row;
}

function createUserMessage(message) {
    var row = document.createElement("div");
    row.className = "msg-row user-row";

    var box = document.createElement("div");
    box.className = "msg-box user";
    box.textContent = message;

    row.appendChild(box);

    return row;
}

function createLoadingMessage(tempId) {
    var row = document.createElement("div");
    row.className = "msg-row";
    row.id = tempId;

    var box = document.createElement("div");
    box.className = "msg-box ai loading-box";

    var icon = document.createElement("img");
    icon.src = "img/icon.png";
    icon.className = "loading-icon";
    icon.alt = "Đang xử lý";

    var text = document.createElement("span");
    text.textContent = "Đang xử lý...";

    box.appendChild(icon);
    box.appendChild(text);
    row.appendChild(box);

    return row;
}

function sendChat() {
    if (isProcessing)
        return;

    var inputField = document.getElementById("message");
    var typeField = document.getElementById("chatType");
    var chatBox = document.getElementById("chatBox");
    var sendBtn = document.querySelector(".btn-icon");
    var msg = inputField.value.trim();

    if (msg === "")
        return;

    isProcessing = true;
    inputField.disabled = true;
    sendBtn.disabled = true;
    sendBtn.style.opacity = "0.3";

    chatBox.appendChild(createUserMessage(msg));
    inputField.value = "";
    chatBox.scrollTop = chatBox.scrollHeight;

    var tempId = "ai-loading-" + Date.now();
    chatBox.appendChild(createLoadingMessage(tempId));
    chatBox.scrollTop = chatBox.scrollHeight;

    fetch("askAIAction.action?question=" + encodeURIComponent(msg) + "&type=" + encodeURIComponent(typeField.value))
            .then(function (response) {
                if (!response.ok)
                    throw new Error("Lỗi HTTP");
                return response.text();
            })
            .then(function (aiReply) {
                var loading = document.getElementById(tempId);

                if (loading)
                    loading.remove();

                chatBox.appendChild(createAIMessage(aiReply));
                chatBox.scrollTop = chatBox.scrollHeight;
            })
            .catch(function () {
                var loading = document.getElementById(tempId);

                if (loading)
                    loading.remove();

                chatBox.appendChild(createAIMessage("Lỗi kết nối! Không thể kết nối đến máy chủ."));
                chatBox.scrollTop = chatBox.scrollHeight;
            })
            .finally(function () {
                isProcessing = false;
                inputField.disabled = false;
                sendBtn.disabled = false;
                sendBtn.style.opacity = "1";
                inputField.focus();
            });
}

function changeType(type, obj) {
    if (isProcessing)
        return;

    document.getElementById("chatType").value = type;
    var response = document.getElementById("response").value;
    var chatBox = document.getElementById("chatBox");
    chatBox.innerHTML = "";
    chatBox.appendChild(createAIMessage(response + "<br>Chào bạn, tôi có thể hỗ trợ gì?"));

    document.querySelectorAll(".chat-option").forEach(function (el) {
        el.classList.remove("active");
    });

    obj.classList.add("active");

    document.getElementById("statusType").innerHTML =
            type === "MENU"
            ? "&#128193; Đang tra cứu: <b>Menu</b>"
            : type === "BAOCAO"
            ? "&#128196; Đang tra cứu: <b>Báo cáo</b>"
            : "&#128172; Đang hỏi AI: <b>Khác</b>";

    document.getElementById("message").focus();
}

document.addEventListener("DOMContentLoaded", function () {
    var inputField = document.getElementById("message");

    inputField.addEventListener("keydown", function (event) {
        if (event.key === "Enter") {
            event.preventDefault();
            sendChat();
        }
    });

    inputField.focus();
});

function clearChat() {
    var chatBox = document.getElementById("chatBox");
    var inputField = document.getElementById("message");
    var sendBtn = document.querySelector(".btn-icon");
    var response = document.getElementById("response").value;
    chatBox.innerHTML = "";
    chatBox.appendChild(createAIMessage(response + "<br>Chào bạn, tôi có thể hỗ trợ gì?"));

    inputField.value = "";
    inputField.disabled = false;
    sendBtn.disabled = false;
    sendBtn.style.opacity = "1";
    isProcessing = false;

    document.getElementById("chatType").value = "BAOCAO";

    document.querySelectorAll(".chat-option").forEach(function (el) {
        el.classList.remove("active");
    });

    var baoCaoTab = document.querySelector(".chat-option[data-type='BAOCAO']");

    if (baoCaoTab) {
        baoCaoTab.classList.add("active");
    }

    document.getElementById("statusType").innerHTML =
            "&#128196; Đang tra cứu: <b>Báo cáo</b>";

    inputField.focus();
}
function loadMachineInfo() {
    var chatBox = document.getElementById("chatBox");

    fetch("checkMachineAction.action")
            .then(function (response) {
                if (!response.ok) {
                    throw new Error("Lỗi HTTP");
                }

                return response.text();
            })
            .then(function (data) {

                var loading = document.getElementById("welcome-loading");

                if (loading) {
                    loading.remove();
                }

                document.getElementById("response").value = data;

                chatBox.appendChild(
                        createAIMessage(
                                data + "<br>Chào bạn, tôi có thể hỗ trợ gì?"
                                )
                        );

                chatBox.scrollTop = chatBox.scrollHeight;
            })
            .catch(function () {

                var loading = document.getElementById("welcome-loading");

                if (loading) {
                    loading.remove();
                }

                chatBox.appendChild(
                        createAIMessage(
                                "Không thể kiểm tra thông tin máy."
                                )
                        );
            });
}
document.addEventListener("DOMContentLoaded", function () {

    var inputField = document.getElementById("message");

    inputField.addEventListener("keydown", function (event) {
        if (event.key === "Enter") {
            event.preventDefault();
            sendChat();
        }
    });

    inputField.focus();

    // Chatbox đã hiển thị rồi mới gọi BE
    setTimeout(function () {
        loadMachineInfo();
    }, 100);
});