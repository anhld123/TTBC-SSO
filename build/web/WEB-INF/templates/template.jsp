<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<body topmargin="0" leftmargin="0" >
<jsp:include page="baseLayout.jsp" flush="true">
	<jsp:param name="css" value="css/main.css"/>
        <jsp:param name="title" value="Ngân hàng chính sách xã hội VN - ${unitDescript} "/>
	<jsp:param name="header" value="header.jsp"/>
	<jsp:param name="menu" value="menu.jsp"/>
	<jsp:param name="footer" value="footer.jsp"/>
</jsp:include>
</body>