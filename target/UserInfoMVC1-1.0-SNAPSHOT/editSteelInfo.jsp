<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>编辑钢材信息</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>编辑钢材信息</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        
        <form action="${pageContext.request.contextPath}/steelInfo" method="post">
            <input type="hidden" name="action" value="edit">
            <input type="hidden" name="id" value="${steelInfo.id}">
            
            <div class="mb-3">
                <label for="steelType" class="form-label">钢材类型</label>
                <input type="text" class="form-control" id="steelType" name="steelType" value="${steelInfo.steelType}" required>
            </div>
            
            <div class="mb-3">
                <label for="steelOrigin" class="form-label">钢材产地</label>
                <input type="text" class="form-control" id="steelOrigin" name="steelOrigin" value="${steelInfo.steelOrigin}" required>
            </div>
            
            <div class="mb-3">
                <label for="productionDate" class="form-label">生产日期</label>
                <input type="date" class="form-control" id="productionDate" name="productionDate" 
                       value="<fmt:formatDate value='${steelInfo.productionDate}' pattern='yyyy-MM-dd'/>" required>
            </div>
            
            <div class="mb-3">
                <label for="manufacturer" class="form-label">产商</label>
                <input type="text" class="form-control" id="manufacturer" name="manufacturer" value="${steelInfo.manufacturer}" required>
            </div>
            
            <div class="mb-3">
                <label for="price" class="form-label">价格</label>
                <input type="number" step="0.01" class="form-control" id="price" name="price" value="${steelInfo.price}" required>
            </div>
            
            <div class="mb-3">
                <label for="volume" class="form-label">体积</label>
                <input type="number" step="0.01" class="form-control" id="volume" name="volume" value="${steelInfo.volume}" required>
            </div>
            
            <div class="mb-3">
                <button type="submit" class="btn btn-primary">保存修改</button>
                <a href="${pageContext.request.contextPath}/steelInfo" class="btn btn-secondary">返回</a>
            </div>
        </form>
    </div>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html> 