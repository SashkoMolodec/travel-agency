<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
    <div class="border-dark" style="margin-top: 25px; margin-bottom: 100px">
        <div class="container border-white">
            <div class="row text-center">
                <div class="col-md-4 text-center" style="background-color: #343a42; padding-top: 18px;">
                    <div class="row" style="margin-top: 6px;">
                        <div class="col" style="color: #f8f9fa;"><span class="d-xl-flex justify-content-xl-start">Filters</span></div>
                        <div class="col"><i class="icon ion-funnel d-block d-md-flex justify-content-md-center align-items-md-center justify-content-xl-end" style="color: #f8f9fa;font-size: 21px;margin: 0px;padding: 0px;margin-top: 0px;margin-bottom: 0px;padding-top: 0px;margin-right: 6px;"></i></div>
                    </div>
                    <form action="${pageContext.request.contextPath}/home/filter" class="hide">
                        <h6 class="text-light" style="padding-top: 0px;padding-bottom: 0px;margin-top: 6px;margin-bottom: 6px;">Choose country</h6>
                        <select id="slctCountry" class="border-dark custom-select" style="color: rgb(248,249,250);background-color: #465765;padding-bottom: 0px;padding-top: 0px;margin-top: 6px;margin-bottom: 6px;" name="country">
                            <optgroup label="Choose country">
                                <option value="world" selected>World</option>
                                <c:forEach items="${countries}" var="country">
                                <option value="${country}">${country}</option>
                                </c:forEach>
                            </optgroup>
                        </select>
                        <h6 class="text-light" style="padding-top: 0px;padding-bottom: 0px;margin-top: 6px;margin-bottom: 6px;">Pick date</h6>
                        <input class="border-dark form-control d-xl-flex" type="date" name="date" id="datePicker" style="background-color: #465765;color: #f8f9fa;margin-top: 12px;margin-bottom: 12px;">
                            <h6 class="text-light" style="margin-top: 6px;margin-bottom: 12px;">How many days you wish to rest?</h6>
                            <input id="daysInputPicker" class="border-dark form-control d-xl-flex justify-content-center align-content-center align-self-center m-auto justify-content-xl-center align-items-xl-center" type="number" style="background-color: #465765;width: 20%;color: rgb(248,249,250);padding-top: 0px;padding-bottom: 0px;margin-top: 0px;margin-bottom: 0px;"
                                max="14" min="1">
                            <input id="daysRangePicker" name="daysPeriod" class="border-dark custom-range" type="range" min="1" max="14" value="1" style="width: 80%;margin-top: 6px;"><button class="btn btn-primary btn-block d-xl-flex mx-sm-auto mx-md-auto mx-lg-auto justify-content-xl-center mx-xl-auto"
                                type="submit" style="margin-top: 12px;margin-bottom: 16px;width: 40%;margin-left: 0%;margin-right: 0%;padding-left: 12px;" onclick="reload()">Apply</button>
                    </form>
                </div>
                <div class="col-md-8 visible" style="padding-top: 0px;margin-top: 0px;">
                    <p class="text-left" style="font-size: 12px;color: #f8f9fa;">Free rooms from
                        <fmt:formatDate value="${fromDate}" pattern="MM/dd/yyyy"/>
                        to <fmt:formatDate value="${toDate}" pattern="MM/dd/yyyy"/></p>
                    <div class="list-group m-md-auto m-lg-auto m-xl-auto" style="margin-top: 20px;">

                        <c:forEach items="${hotelList}" var="hotel">
                        <a class="list-group-item list-group-item-action list-group-item-dark text-light bg-dark border-dark">
                            <div class="row" style="height: 60px;">
                                <div class="col m-auto" style="width: auto;height: auto;max-width: 20%;"><span>${hotel.key.country}</span></div>
                                <div class="col m-auto" style="width: 50%;"><span id="hotelTitle">${hotel.key.title}</span></div>
                                <div class="col m-auto" style="width: auto;max-width: 20%;">
                                    <div class="row">
                                        <div class="col"><span style="font-size: 12px">${hotel.value} rooms available</span></div>
                                    </div>
                                    <div class="row">
                                        <div class="col" style="margin-top: 6px;"><button onclick="submitOrder(${hotel.key.id})" class="btn btn-primary" type="submit" style="font-size: 12px;">Book!</button></div>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </a>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <t:footer/>
    <script>
        var token =  $('input[name="${_csrf.parameterName}"]').attr('value');
        function submitOrder(hotelId){
            var date = $('#datePicker').val();
            var daysPeriod = $('#daysInputPicker').val();
            $.ajax({
                method:'POST',
                data:{
                    date:date,
                    daysPeriod:daysPeriod,
                    hotelId:hotelId
                },
                headers: {
                    'X-CSRF-Token': token
                },
                url:"/home/bookHotel",
                success: funcSuccess
            });
        }
            function funcSuccess() {
                location.reload()
            }


        function reload() {
            localStorage.setItem('selectedCountry',$('#slctCountry').val());
            localStorage.setItem('selectedDate',$('#datePicker').val());
            localStorage.setItem('selectedDays',$('#daysInputPicker').val());
            location.reload(true);
        }
        $(document).ready(function() {
            var selectedCountry = localStorage.getItem('selectedCountry');
            var selectedDate = localStorage.getItem('selectedDate');
            var selectedDays = localStorage.getItem('selectedDays');
            if (selectedCountry){
                $('#slctCountry').val(selectedCountry)
                $('#datePicker').val(selectedDate)

                if(selectedDate === '')
                    document.getElementById('datePicker').valueAsDate = new Date()

                $('#daysInputPicker').val(selectedDays)
                $('#daysRangePicker').val(selectedDays)

            }
        });

        $('#daysRangePicker, #daysInputPicker').on('input', function(){
            $(this).siblings('#daysRangePicker, #daysInputPicker').val(this.value);
        });

    </script>
</body>

</html>