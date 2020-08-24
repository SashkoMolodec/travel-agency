<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<html>
<t:head/>
<body style="background-color: #434a52;">
    <t:nav/>
    <div class="highlight-blue" style="padding-top: 30px;padding-bottom: 5px;background-color: #434a52;">
        <div class="container">
            <div class="intro">
                <h2 class="text-center">Today is a best day to book!</h2>
                <p class="text-center">Don't miss your chance! Choose your favorite country and date that you won't forget!</p>
                <i class="icon ion-ios-arrow-down d-xl-flex justify-content-xl-center" style="font-size: 40px;margin-top: 0px;padding-top: 15px;"></i></div>
        </div>
    </div>
    <div class="border-dark" style="margin-top: 25px;">
        <div class="container border-white">
            <div class="row text-center">
                <div class="col-md-4 text-center" style="background-color: #343a42; padding-top: 18px;">
                    <div class="row" style="margin-top: 6px;">
                        <div class="col" style="color: #f8f9fa;"><span class="d-xl-flex justify-content-xl-start">Filters</span></div>
                        <div class="col"><i class="icon ion-funnel d-block d-md-flex justify-content-md-center align-items-md-center justify-content-xl-end" style="color: #f8f9fa;font-size: 21px;margin: 0px;padding: 0px;margin-top: 0px;margin-bottom: 0px;padding-top: 0px;margin-right: 6px;"></i></div>
                    </div>
                    <form>
                        <h6 class="text-light" style="padding-top: 0px;padding-bottom: 0px;margin-top: 6px;margin-bottom: 6px;">Choose country</h6>
                        <select class="border-dark custom-select" style="color: rgb(248,249,250);background-color: #465765;padding-bottom: 0px;padding-top: 0px;margin-top: 6px;margin-bottom: 6px;">
                            <optgroup label="Choose country">
                                <c:forEach items="${countries}" var="country">
                                <option value="" selected disabled hidden>World</option>
                                <option value="${country}">${country}</option>
                                </c:forEach>
                            </optgroup>
                        </select>
                        <h6 class="text-light" style="padding-top: 0px;padding-bottom: 0px;margin-top: 6px;margin-bottom: 6px;">Pick date</h6>
                        <input class="border-dark form-control d-xl-flex" type="date" id="datePicker" style="background-color: #465765;color: #f8f9fa;margin-top: 12px;margin-bottom: 12px;">
                            <h6 class="text-light" style="margin-top: 6px;margin-bottom: 12px;">How many days you wish to rest?</h6>
                            <input class="border-dark form-control d-xl-flex justify-content-center align-content-center align-self-center m-auto justify-content-xl-center align-items-xl-center" type="number" style="background-color: #465765;width: 20%;color: rgb(248,249,250);padding-top: 0px;padding-bottom: 0px;margin-top: 0px;margin-bottom: 0px;"
                                max="14" min="1">
                            <input class="border-dark custom-range" type="range" min="1" max="14" value="1" style="width: 80%;margin-top: 6px;"><button class="btn btn-primary btn-block d-xl-flex mx-sm-auto mx-md-auto mx-lg-auto justify-content-xl-center mx-xl-auto"
                                type="submit" style="margin-top: 12px;margin-bottom: 16px;width: 40%;margin-left: 0%;margin-right: 0%;padding-left: 12px;">Apply</button></form>
                </div>
                <div class="col-md-8 visible" style="padding-top: 0px;margin-top: 0px;">
                    <div class="list-group m-md-auto m-lg-auto m-xl-auto" style="margin-top: 20px;">

                        <a class="list-group-item list-group-item-action list-group-item-dark text-light bg-dark border-dark">
                            <div class="row" style="height: 60px;">
                                <div class="col m-auto" style="width: auto;height: auto;max-width: 20%;"><span>France</span></div>
                                <div class="col m-auto" style="width: 50%;"><span>Hotel la Fleur</span></div>
                                <div class="col m-auto" style="width: auto;max-width: 20%;"><span>6 rooms</span></div>
                            </div>
                        </a>
                        <a class="list-group-item list-group-item-action list-group-item-dark text-light bg-dark border-dark">
                            <div class="row" style="height: 60px;">
                                <div class="col m-auto" style="width: auto;height: auto;max-width: 20%;"><span>Ukraine</span></div>
                                <div class="col m-auto" style="width: 50%;"><span>Kozak</span></div>
                                <div class="col m-auto" style="width: auto;max-width: 20%;"><span>0 rooms</span></div>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-dark" style="margin-top: 100px;background-color: #343a40;">
        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-sm-6 col-md-3 item">
                        <h3>About</h3>
                        <ul>
                            <li><a href="#">Company</a></li>
                            <li><a href="#">Team</a></li>
                            <li><a href="#">Careers</a></li>
                        </ul>
                    </div>
                    <div class="col-md-6 item text">
                        <h3>Book!</h3>
                        <p>Best website to spend your money!</p>
                    </div>
                    <div class="col item social"><a href="#"><i class="icon ion-social-facebook"></i></a><a href="#"><i class="icon ion-social-twitter"></i></a><a href="#"><i class="icon ion-social-snapchat"></i></a><a href="#"><i class="icon ion-social-instagram"></i></a></div>
                </div>
                <p class="copyright">Company Name © 2017</p>
            </div>
        </footer>
    </div>
    <script>
        document.getElementById('datePicker').valueAsDate = new Date();
    </script>
</body>

</html>