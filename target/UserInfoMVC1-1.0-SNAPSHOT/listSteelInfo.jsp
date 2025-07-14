<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>钢材信息列表</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>钢材信息列表</h2>
        
        <div class="mb-3">
            <a href="${pageContext.request.contextPath}/steelInfo?action=add" class="btn btn-primary">添加新钢材</a>
            <a href="${pageContext.request.contextPath}/steelInfo?action=searchPage" class="btn btn-info">查询钢材</a>
        </div>
        
        <table class="table table-striped table-bordered">
            <thead class="table-dark">
                <tr>
                    <th>ID</th>
                    <th>钢材类型</th>
                    <th>钢材产地</th>
                    <th>生产日期</th>
                    <th>产商</th>
                    <th>价格</th>
                    <th>体积</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${steelInfoList}" var="steel">
                    <tr>
                        <td>${steel.id}</td>
                        <td>${steel.steelType}</td>
                        <td>${steel.steelOrigin}</td>
                        <td><fmt:formatDate value="${steel.productionDate}" pattern="yyyy-MM-dd"/></td>
                        <td>${steel.manufacturer}</td>
                        <td>${steel.price}</td>
                        <td>${steel.volume}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/steelInfo?action=edit&id=${steel.id}" class="btn btn-sm btn-warning">编辑</a>
                            <a href="${pageContext.request.contextPath}/steelInfo?action=find&id=${steel.id}" class="btn btn-sm btn-info">查看</a>
                            <a href="${pageContext.request.contextPath}/steelInfo?action=delete&id=${steel.id}" class="btn btn-sm btn-danger" onclick="return confirm('确定要删除吗？')">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html> 