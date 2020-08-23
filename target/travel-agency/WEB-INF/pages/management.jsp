<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
    <div>
        <div class="container">
            <div class="row">
                <div class="col-md-6">
                    <div class="list-group m-md-auto m-lg-auto m-xl-auto" style="margin-top: 20px;color: rgb(52,58,66);">
                        <c:forEach items="${users}" var="user">
                        <a class="list-group-item list-group-item-action list-group-item-dark text-light bg-dark border-dark">
                            <span style="font-size: 24px;margin-top: 0px;margin-bottom: 0px;padding-bottom: 0px; text-transform: capitalize;">${user.username} ${" - "} ${fn:replace(fn:replace(user.roles, "[" ,"" ),"]","")}</span><ul class="list-group" style="margin-top: 12px;">
                            <li class="list-group-item" style="background-color: #1e2833;"><div class="row">
                                <div class="col"><span style="color: #f8f9fa;">Hotel la Fleur</span></div>
                                <div class="col"><span style="color: #f8f9fa;">20.05.2020 - 21.05.2020</span></div>
                                <div class="col"><span style="color: #f8f9fa;">Room: 5</span></div>
                            </div>
                            </li>
                            <li class="list-group-item" style="background-color: #1e2833;"><div class="row"><div class="col"><span style="color: #f8f9fa;">Hotel la Fleur</span></div>
                                <div class="col"><span style="color: #f8f9fa;">20.05.2020 - 21.05.2020</span></div>
                                <div class="col"><span style="color: #f8f9fa;">Room: 5</span></div>
                            </div>
                            </li>
                                <li class="list-group-item" style="background-color: #1e2833;">
                            <div class="row"><div class="col"><span style="color: #f8f9fa;">Hotel la Fleur</span></div>
                                <div class="col"><span style="color: #f8f9fa;">20.05.2020 - 21.05.2020</span></div>
                                <div class="col"><span style="color: #f8f9fa;">Room: 5</span></div>
                            </div>
                        </li>
                        </ul>
                        </a>
                        </c:forEach>
                    </div>
                    <%--<div
                        class="list-group m-md-auto m-lg-auto m-xl-auto" style="margin-top: 20px;color: rgb(52,58,66);">
                        <a class="list-group-item list-group-item-action list-group-item-dark text-light bg-dark border-dark"><span style="font-size: 24px;margin-top: 0px;margin-bottom: 0px;padding-bottom: 0px;">Danya</span><ul class="list-group" style="margin-top: 12px;"><li class="list-group-item" style="background-color: #1e2833;"><div class="row"><div class="col"><span style="color: #f8f9fa;">Hotel Kozak</span></div><div class="col"><span style="color: #f8f9fa;">20.05.2020 - 21.05.2020</span></div><div class="col"><span style="color: #f8f9fa;">Room: 1</span></div></div></li><li class="list-group-item" style="background-color: #1e2833;"><div class="row"><div class="col"><span style="color: #f8f9fa;">Hotel la Fleur</span></div><div class="col"><span style="color: #f8f9fa;">20.05.2020 - 21.05.2020</span></div><div class="col"><span style="color: #f8f9fa;">Room: 2</span></div></div></li></ul></a></div>--%>
            </div>
            <div class="col-md-6" style="padding-right: 15px;">
                <div class="row" style="padding-right: 21px;padding-left: 21px;margin-bottom: 12px;">
                    <div class="col"><span style="color: #f8f9fa;">Hotel name</span></div>
                    <div class="col"><span style="color: #f8f9fa;">Country</span></div>
                    <div class="col"><span style="color: #f8f9fa;">Capacity</span></div>
                </div>
                <div class="list-group m-md-auto m-lg-auto m-xl-auto" style="margin-top: 20px;">
                    <a class="list-group-item list-group-item-action list-group-item-dark text-light bg-dark border-dark" style="padding-top: 0px;">
                        <div class="row" style="height: 65px;padding-top: 0px;">
                            <div class="col m-auto" style="width: auto;height: auto;max-width: 100%;"><span>Hotel la Fleur</span></div>
                            <div class="col m-auto" style="width: auto;"><span>France</span></div>
                            <div class="col" style="width: auto;max-width: 100%;margin-top: 15px;padding-right: 15px;">
                                <div class="table-responsive" style="height: 65px;">
                                    <table class="table">
                                        <thead>
                                            <tr></tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td class="d-inline-flex float-left justify-content-xl-center" style="width: 30px;color: #f8f9fa;">-</td>
                                                <td class="d-inline-flex justify-content-xl-center" style="width: 50px;color: #f8f9fa;margin-right: 18px;margin-left: 17px;">50</td>
                                                <td class="d-inline-flex float-right justify-content-xl-center" style="width: 30px;color: #f8f9fa;">+</td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </a>
                    <a class="list-group-item list-group-item-action list-group-item-dark text-light bg-dark border-dark" style="padding-top: 0px;">
                        <form>
                            <div class="form-row" style="height: 65px;padding-top: 0px;">
                                <div class="col d-xl-flex m-auto" style="width: 33%;height: auto;max-width: 100%;"><input class="form-control d-xl-flex justify-content-xl-center align-items-xl-center" type="text" style="width: 100%;"></div>
                                <div class="col d-xl-flex m-auto" style="width: 33%;height: auto;max-width: 100%;"><input class="form-control d-xl-flex justify-content-xl-center align-items-xl-center" type="text" style="width: 100%;"></div>
                                <div class="col m-auto" style="width: 33%;"><input class="form-control d-xl-flex justify-content-xl-center align-items-xl-center" type="number" style="width: 100%;" min="1" max="1000"></div>
                            </div>
                            <div class="form-row">
                                <div class="col"><button class="btn btn-primary d-xl-flex m-auto" type="button">Add</button></div>
                            </div>
                        </form>
                    </a>
                </div>
            </div>
        </div>
    </div>
    </div>
</body>

</html>