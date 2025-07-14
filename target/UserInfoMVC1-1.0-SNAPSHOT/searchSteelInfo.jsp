<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>查询钢材信息</title>
    <link href="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.3.0/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-4">
        <h2>查询钢材信息</h2>
        
        <div class="card mb-4">
            <div class="card-body">
                <form action="${pageContext.request.contextPath}/steelInfo" method="get" class="row g-3">
                    <input type="hidden" name="action" value="search">
                    
                    <div class="col-md-4">
                        <label for="searchId" class="form-label">钢材ID</label>
                        <input type="text" class="form-control" id="searchId" name="searchId" 
                               value="${param.searchSteelId}" placeholder="请输入钢材ID">
                    </div>
                    
                    <div class="col-md-4">
                        <label for="searchSteelType" class="form-label">钢材类型</label>
                        <input type="text" class="form-control" id="searchSteelType" name="searchSteelType" 
                               value="${param.searchSteelType}" placeholder="请输入钢材类型（支持模糊查询）">
                    </div>
                    
                    <div class="col-md-4">
                        <label for="searchSteelOrigin" class="form-label">钢材产地</label>
                        <input type="text" class="form-control" id="searchSteelOrigin" name="searchSteelOrigin" 
                               value="${param.searchSteelOrigin}" placeholder="请输入钢材产地（支持模糊查询）">
                    </div>
                    
                    <div class="col-12">
                        <button type="submit" class="btn btn-primary">查询</button>
                        <a href="${pageContext.request.contextPath}/steelInfo" class="btn btn-secondary">返回列表</a>
                        <button type="button" class="btn btn-outline-secondary" onclick="clearForm()">清空表单</button>
                    </div>
                </form>
            </div>
        </div>

<%--        <c:if test="${not empty steelInfo}">--%>
<%--            <div class="card">--%>
<%--                <div class="card-body">--%>
<%--                    <h3 class="card-title">查询结果</h3>--%>
<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-3 fw-bold">ID：</div>--%>
<%--                        <div class="col-md-9">${steelInfo.id}</div>--%>
<%--                    </div>--%>
<%--                    --%>
<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-3 fw-bold">钢材类型：</div>--%>
<%--                        <div class="col-md-9">${steelInfo.steelType}</div>--%>
<%--                    </div>--%>
<%--                    --%>
<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-3 fw-bold">钢材产地：</div>--%>
<%--                        <div class="col-md-9">${steelInfo.steelOrigin}</div>--%>
<%--                    </div>--%>
<%--                    --%>
<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-3 fw-bold">生产日期：</div>--%>
<%--                        <div class="col-md-9"><fmt:formatDate value="${steelInfo.productionDate}" pattern="yyyy-MM-dd"/></div>--%>
<%--                    </div>--%>
<%--                    --%>
<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-3 fw-bold">产商：</div>--%>
<%--                        <div class="col-md-9">${steelInfo.manufacturer}</div>--%>
<%--                    </div>--%>
<%--                    --%>
<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-3 fw-bold">价格：</div>--%>
<%--                        <div class="col-md-9">${steelInfo.price}</div>--%>
<%--                    </div>--%>
<%--                    --%>
<%--                    <div class="row mb-3">--%>
<%--                        <div class="col-md-3 fw-bold">体积：</div>--%>
<%--                        <div class="col-md-9">${steelInfo.volume}</div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:if>--%>

        <c:if test="${not empty steelInfoList}">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title">查询结果 (共找到 ${steelInfoList.size()} 条记录)</h3>
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
            </div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="alert alert-danger mt-3">
                ${error}
            </div>
        </c:if>
    </div>
    <script src="https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
    <script>
        function clearForm() {
            document.getElementById('searchId').value = '';
            document.getElementById('searchSteelType').value = '';
            document.getElementById('searchSteelOrigin').value = '';
        }
        
        // 表单提交验证
        document.querySelector('form').addEventListener('submit', function(e) {
            const searchId = document.getElementById('searchId').value.trim();
            const searchSteelType = document.getElementById('searchSteelType').value.trim();
            const searchSteelOrigin = document.getElementById('searchSteelOrigin').value.trim();
            
            // 允许单独查询：只要有一个条件不为空就可以查询
            if (!searchId && !searchSteelType && !searchSteelOrigin) {
                e.preventDefault();
                alert('请至少输入一个查询条件！');
                return false;
            }
            
            // 如果用户输入了ID，验证是否为数字
            if (searchId && isNaN(searchId)) {
                e.preventDefault();
                alert('钢材ID必须是数字！');
                return false;
            }
        });
    </script>
</body>
</html> 