<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag description="Navbar" pageEncoding="UTF-8"%>

<nav class="navbar navbar-dark navbar-expand-md bg-dark navigation-clean-button">
    <div class="container"><a class="navbar-brand" href="#">Book!</a><button data-toggle="collapse" class="navbar-toggler" data-target="#navcol-1"><span class="sr-only">Toggle navigation</span><span class="navbar-toggler-icon"></span></button>
        <div class="collapse navbar-collapse"
             id="navcol-1">
            <ul class="nav navbar-nav mr-auto">
                <li class="nav-item" role="presentation"><a class="nav-link active" href="/home">Home</a></li>
            </ul><span class="navbar-text actions"> <a class="login" href="${pageContext.request.contextPath}/login">Log In</a><a class="btn btn-dark action-button" role="button" href="#">Sign Up</a></span></div>
    </div>
</nav>