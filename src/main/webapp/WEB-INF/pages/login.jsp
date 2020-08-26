<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<t:head/>
<body>
    <div class="login-dark" style="background-color: #434a52;">
        <t:nav/>
        <form method="post" style="background-color: #343a42;" action="${pageContext.request.contextPath}/login">
            <h2 class="sr-only">Login Form</h2>
            <h1 class="text-center" style="font-size: 23px;"><strong>Greetings!</strong></h1>
            <div class="illustration"><i class="icon ion-ios-locked-outline"></i></div>
            <div class="form-group"><input class="form-control" type="text" name="username" placeholder="Username"></div>
            <div class="form-group"><input class="form-control" type="password" name="password" placeholder="Password"></div>
            <div class="form-group"><button class="btn btn-primary btn-block" type="submit">Log In</button></div><a class="forgot" href="${pageContext.request.contextPath}/register">Don't have an account? Sign up!</a>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>

    </div>
    <t:footer/>
</body>

</html>