<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>钢材信息详情</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>钢材信息详情</h2>
        
        <div class="card">
            <div class="card-body">
                <div class="row mb-3">
                    <div class="col-md-3 fw-bold">ID：</div>
                    <div class="col-md-9">${steelInfo.id}</div>
                </div>
                
                <div class="row mb-3">
                    <div class="col-md-3 fw-bold">钢材类型：</div>
                    <div class="col-md-9">${steelInfo.steelType}</div>
                </div>
                
                <div class="row mb-3">
                    <div class="col-md-3 fw-bold">钢材产地：</div>
                    <div class="col-md-9">${steelInfo.steelOrigin}</div>
                </div>
                
                <div class="row mb-3">
                    <div class="col-md-3 fw-bold">生产日期：</div>
                    <div class="col-md-9"><fmt:formatDate value="${steelInfo.productionDate}" pattern="yyyy-MM-dd"/></div>
                </div>
                
                <div class="row mb-3">
                    <div class="col-md-3 fw-bold">产商：</div>
                    <div class="col-md-9">${steelInfo.manufacturer}</div>
                </div>
                
                <div class="row mb-3">
                    <div class="col-md-3 fw-bold">价格：</div>
                    <div class="col-md-9">${steelInfo.price}</div>
                </div>
                
                <div class="row mb-3">
                    <div class="col-md-3 fw-bold">体积：</div>
                    <div class="col-md-9">${steelInfo.volume}</div>
                </div>
            </div>
        </div>
        
        <div class="mt-3">
            <a href="${pageContext.request.contextPath}/steelInfo?action=edit&id=${steelInfo.id}" class="btn btn-warning">编辑</a>
            <a href="${pageContext.request.contextPath}/steelInfo" class="btn btn-secondary">返回列表</a>
        </div>
    </div>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
</body>
</html> 