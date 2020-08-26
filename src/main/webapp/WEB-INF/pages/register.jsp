<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<t:head/>
<body>
    <div class="login-dark" style="background-color: #434a52;">
        <t:nav/>
        <form action="${pageContext.request.contextPath}/register" name="registerForm" method="post" style="background-color: rgb(52,58,66);">
            <h2 class="sr-only">Registration Form</h2>
            <h1 class="text-center" style="font-size: 23px;"><strong>Create new account</strong></h1>
            <div class="illustration"></div>
            <i class="fas fa-users d-xl-flex justify-content-xl-center" style="font-size: 110px;margin-bottom: 18px;"></i>
                <c:if test="${isError}">
                    <span class="text-center text-danger d-xl-flex justify-content-xl-center" style="height: 100%;width: 100%;margin-bottom: 8px;">Please input valid fields!</span>
                </c:if>
            <div class="form-group"><input class="form-control" type="text" name="username" placeholder="Username"></div>
            <div class="form-group"><input class="form-control" type="password" name="password" placeholder="Password"></div>
            <div class="form-group"><button class="btn btn-primary btn-block" type="submit">Sign Up</button></div>
            <a class="forgot" href="${pageContext.request.contextPath}/login">Already have an account? Log in!</a>

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
    <t:footer/>

</body>
</html>