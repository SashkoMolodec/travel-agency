<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag description="Navbar" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<nav class="navbar navbar-dark navbar-expand-md bg-dark navigation-clean-button">
    <div class="container"><a class="navbar-brand" href="#">Book!</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse"
             id="navcol-1">
            <ul class="nav navbar-nav mr-auto">
                <sec:authorize access="isAuthenticated()">
                <li class="nav-item" role="presentation">
                    <a class="nav-link" href="${pageContext.request.contextPath}/home">Home</a></li>
                    <sec:authorize access="hasRole('MANAGER')">
                        <li class="nav-item" role="presentation">
                            <a class="nav-link" href="${pageContext.request.contextPath}/management">Management</a></li>
                    </sec:authorize>

                </ul><span class="navbar-text actions"> <a class="login" href="${pageContext.request.contextPath}/logout">Log out</a></span>
                </sec:authorize>

            <sec:authorize access="!isAuthenticated()">
            </ul><span class="navbar-text actions"> <a class="login" href="${pageContext.request.contextPath}/login">Log In</a>
            <a class="btn btn-dark action-button" role="button" href="${pageContext.request.contextPath}/register">Sign Up</a></span></div>
            </sec:authorize>

    </div>
</nav>