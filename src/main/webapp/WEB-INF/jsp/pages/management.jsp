<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/jsp/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html>
<t:head/>
<body style="background-color: #434a52;">
    <t:nav/>
    <div class="highlight-blue" style="padding-top: 25px;padding-bottom: 25px;background-color: #434a52;">
        <div class="container">
            <div class="intro">
                <h2 class="text-center">Management panel</h2>
                <p class="text-center">Ability to watch user's orders and add new hotels with rooms.</p>
            </div>
        </div>
    </div>
    <div style="margin-bottom: 200px">
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="list-group m-md-auto m-lg-auto m-xl-auto" style="margin-top: 20px;color: rgb(52,58,66);">
                        <c:forEach items="${users}" var="user">
                        <a class="list-group-item list-group-item-action list-group-item-dark text-light bg-dark border-dark">
                            <span style="font-size: 24px;margin-top: 0px;margin-bottom: 0px;padding-bottom: 0px;">
                                    ${user.username}  - <span style="text-transform: capitalize">${fn:toLowerCase(fn:replace(fn:replace(user.roles, "[" ,"" ),"]",""))}</span>
                            </span><ul class="list-group" style="margin-top: 12px;">
                            <c:forEach items="${user.orders}" var="order">
                            <li class="list-group-item" style="background-color: #1e2833;"><div class="row">
                                <div class="col"><span style="color: #f8f9fa;">${order.room.hotel.title}</span></div>
                                <div class="col"><span style="color: #f8f9fa;">
                                    <fmt:formatDate value="${order.fromDate}" pattern="MM/dd/yyyy"/> -
                                    <fmt:formatDate value="${order.toDate}" pattern="MM/dd/yyyy"/></span></div>
                                <div class="col"><span style="color: #f8f9fa;">Room: ${order.room.number}</span></div>
                            </div>
                            </li>
                            </c:forEach>
                        </li>
                        </ul>
                        </a>
                        </c:forEach>
                    </div>
            </div>
            <div class="col-md-6" style="padding-right: 15px;">
                <div class="row" style="padding-right: 21px;padding-left: 21px;margin-bottom: 12px;">
                    <div class="col"><span style="color: #f8f9fa;">Hotel name</span></div>
                    <div class="col"><span style="color: #f8f9fa;">Country</span></div>
                    <div class="col"><span style="color: #f8f9fa;">Capacity</span></div>
                </div>
                <div class="list-group m-md-auto m-lg-auto m-xl-auto" style="margin-top: 20px;">
                    <c:forEach items="${hotels}" var="hotel">
                    <a class="list-group-item list-group-item-action list-group-item-dark text-light bg-dark border-dark" style="padding-top: 0px;">
                        <div class="row" style="height: 65px;padding-top: 0px;">
                            <div class="col m-auto" style="width: auto;height: auto;max-width: 100%;"><span>${hotel.title}</span></div>
                            <div class="col m-auto" style="width: auto;"><span>${hotel.country}</span></div>
                            <div class="col" style="width: auto;max-width: 100%;margin-top: 15px;padding-right: 15px;">
                                <div class="table-responsive" style="height: 65px; overflow: hidden;">
                                   <table class="table">
                                        <thead>
                                            <tr></tr>
                                        </thead>
                                        <tbody>
                                        <tr>

                                            <form action="/management/decrement-room/${hotel.id}" method="post">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                <td class="d-inline-flex float-left justify-content-xl-center" style="width: 30px;color: #f8f9fa;height: 50px;">
                                                    <c:if test="${hotel.hasEmptyRoomWithoutOrders()}">
                                                    <button class="btn btn-primary text-center d-xl-flex justify-content-xl-center align-items-xl-center" type="submit" style="width: 100%;height: 35px;font-size: 30px;padding-top: 0px;padding-bottom: 9px;margin-bottom: 0px;">-</button>
                                                    </c:if>
                                                </td>
                                            </form>
                                            <td class="d-inline-flex justify-content-xl-center" style="width: 30px;color: #f8f9fa;margin-right: 18px;margin-left: 28px;">${hotel.capacity}</td>
                                            <form action="/management/increment-room/${hotel.id}" method="post">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <td class="d-inline-flex float-right justify-content-xl-center" style="width: 30px;color: #f8f9fa;height: 50px;"><button class="btn btn-primary text-center d-xl-flex justify-content-xl-center align-items-xl-center" type="submit" style="width: 100%;height: 35px;font-size: 20px;padding-top: 0px;padding-bottom: 7px;">+</button></td>
                                            </form>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </a>
                    </c:forEach>
                    <a class="list-group-item list-group-item-action list-group-item-dark text-light bg-dark border-dark" style="padding-top: 0px;">
                        <form method="post" action="${pageContext.request.contextPath}/management/addHotel">
                            <div class="form-row" style="height: 65px;padding-top: 0px;">
                                <div class="col d-xl-flex m-auto" style="width: 33%;height: auto;max-width: 100%;">
                                    <input class="form-control d-xl-flex justify-content-xl-center align-items-xl-center" type="text" style="width: 100%;" name="title"></div>
                                <div class="col d-xl-flex m-auto" style="width: 33%;height: auto;max-width: 100%;">
                                    <input class="form-control d-xl-flex justify-content-xl-center align-items-xl-center" type="text" style="width: 100%;" name="country"></div>
                                <div class="col m-auto" style="width: 33%;">
                                    <input class="form-control d-xl-flex justify-content-xl-center align-items-xl-center" type="number" style="width: 100%;" min="1" max="1000" name="capacity"></div>
                            </div>
                            <c:if test="${isError}">
                                <span class="text-danger">Some fields are empty!</span>
                            </c:if>
                            <div class="form-row">
                                <div class="col"><button class="btn btn-primary d-xl-flex m-auto" type="submit">Add</button></div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </a>
                </div>
            </div>
        </div>
    </div>
    </div>
    <t:footer/>
</body>

</html>