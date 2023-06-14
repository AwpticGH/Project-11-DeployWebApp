<%@ page import="ccit.g2airline.project11deployableweb.model.database.AuthModel" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="ccit.g2airline.project11deployableweb.helper.StringHelper" %>
<%@ page import="ccit.g2airline.project11deployableweb.helper.DateHelper" %>
<%@ page import="ccit.g2airline.project11deployableweb.model.web.FlightSearchedModel" %>
<%@ page import="ccit.g2airline.project11deployableweb.dictionary.WebRoute" %>
<%@ page import="ccit.g2airline.project11deployableweb.helper.DatetimeHelper" %>
<%@ page import="ccit.g2airline.project11deployableweb.dictionary.WebVariable" %>
<%@ page import="ccit.g2airline.project11deployableweb.helper.TimeHelper" %><%--
    Document   : search
    Created on : Jul 4, 2022, 8:29:56 PM
    Author     : rafih
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/views/layouts/head.jsp">
        <jsp:param name="css-file" value="search.css"></jsp:param>
    </jsp:include>
</head>
<body>
    <jsp:include page="/views/layouts/navbar.jsp"></jsp:include>

    <!-- content -->
    <%
        FlightSearchedModel model;
        String passCount;
        String seatClass;
        String datetime;
        String date;
        String departDate;
        try {
            model = (FlightSearchedModel) request.getAttribute("FlightSearchedModel");
        }
        catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
        boolean resultFound = (model.getFlightModels() != null || model.getFlightModels().size() != 0);
        if (resultFound) {
            passCount = StringHelper.getPassCount(model.getPassengerSeatClass());
            seatClass = StringHelper.getSeatClass(model.getPassengerSeatClass());
            datetime = model.getFlightModels().get(0).getTime_of_departure();
            try {
                date = DatetimeHelper.parseDatetimeToDate(datetime);
                departDate = DateHelper.dateFormat(date, "dd MMMM");
            }
            catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }

    %>
    <!-- panel - searched -->
    <div class="panel searched shadowed">
        <div class="col-6">
            <div class="row content">
                <div class="col-4 content">
                    <h1><%= model.getAirportDestinationModel().getCity() %></h1>
                </div>
                <div class="col-4 content text-center">
                    <h1><i class='bx bx-right-arrow-alt'></i></h1>
                </div>
                <div class="col-4 content text-center">
                    <h1><%= model.getAirportDestinationModel().getCity() %></h1>
                </div>
            </div>
            <div class="row content">
                <div class="col-12 content">
                    <p class="searched-footer"><%= departDate%> &nbsp||&nbsp <%= passCount %> <%= (Integer.parseInt(passCount) == 1) ? "Passenger" : "Passengers" %> &nbsp||&nbsp <%= seatClass%></p>
                </div>
            </div>
        </div>
        <div class="col-6 content">
            <button class="btn-panel-searched shadowed" onclick="history.back()">Change Search</button>
        </div>
    </div>


    <% if(resultFound) {
        for (int i = 0; i < model.getFlightModels().size(); i++) {
            String time = null;
            String departTime = null;
            String arrivalTime = null;
            String timeOfFlight = null;
            try {
                time = DatetimeHelper.parseDatetimeToTime(model.getFlightModels().get(i).getTime_of_departure());
                departTime = TimeHelper.removeSecondsFromTime(time);
                timeOfFlight = model.getRouteModel().getTime_of_flight();
                arrivalTime = TimeHelper.removeSecondsFromTime(TimeHelper.addTime(departTime, timeOfFlight));
            }
            catch (ParseException e) {
                throw new RuntimeException(e);
            }

    %>
    <!-- panel - result -->
    <form action="<%= WebRoute.RESERVATION_CREATE %>">
        <input name="<%= WebVariable.FLIGHT_ID %>" type="hidden" value="<%= model.getFlightModels().get(i).getId() %>">
        <input name="<%= WebVariable.PASSENGER_COUNT %>" type="hidden" value="<%= passCount%> ">
        <input name="<%= WebVariable.SEAT_CLASS %>" type="hidden" value="<%= seatClass%> ">
        <div id="result">
            <div class="panel result shadowed" onclick="clickedResult1(), extendResult1()">
                <div class="col-4">
                    <div class="col-12 content">
                        <h2><img class="result-airline-logo" src="${pageContext.request.contextPath}/public/assets/images/garuda_indonesia.png"> <%= model.getAirlineModels().get(i).getName() %></h2>
                    </div>
                    <div class="col-12 content">
                        <br>
                    </div>
                    <div class="col-12">
                        <div class="col-6 content text-center">
                            <a href="#result" class="info-extender">Flight Info</a>
                        </div>
                        <div class="col-6 content text-center">
                            <a href="#result" class="price-extender">Fare Info</a>
                        </div>
                    </div>
                </div>
                <div class="col-8">
                    <div class="col-8">
                        <div class="col-4 content text-center">
                            <p class="result-text-time"><%= departTime %></p>
                            <p><%= model.getAirportDepartureModel().getCode() %></p>
                        </div>
                        <div class="col-4 content text-center">
                            <p class="result-text-time-duration"><%= timeOfFlight %> M</p>
                            <hr>
                            <!-- <div>
                                <i class='bx bx-chevron-left'></i><hr class="result-text-time-separator"><i class='bx bx-chevron-right' ></i>
                            </div> -->
                        </div>
                        <div class="col-4 content text-center">
                            <p class="result-text-time"><%= arrivalTime %></p>
                            <p><%= model.getAirportDestinationModel().getCode() %></p>
                        </div>
                    </div>
                    <div class="col-4 content">
                        <button type="submit" class="btn-panel-result shadowed">Choose</button>
                    </div>
                </div>
            </div>
            <!-- panel - result - 1 extension -->
            <div class="panel result-extended" id="info-1">
                <!-- below is blank  -->
                <div class="row">
                    <div class="col-12 content"></div>
                </div>
                <!-- above is blank -->
                <div class="row">
                    <div class="col-2 content text-center info-vr" style="padding-top: 0; padding-bottom: 0;">
                        <p class="no-margin-bottom no-margin-top"><%= departTime %></p>
                        <p class="text-gray no-margin-top"><%= departDate %></p>
                        <br>
                        <br>
                        <br>
                        <p><i class='bx bx-right-arrow-alt'></i> <%= timeOfFlight %> M</p>
                        <br>
                        <br>
                        <br>
                        <p class="no-margin-bottom"><%= arrivalTime %></p>
                        <p class="text-gray no-margin-top no-margin-bottom"><%= departDate %></p>
                    </div>
                    <div class="col-10 content" style="padding-top: 0; padding-bottom: 0;">
                        <p class="no-margin-bottom no-margin-top"><%= model.getAirportDepartureModel().getCity() %> (<%= model.getAirportDepartureModel().getCode() %>)</p>
                        <p class="no-margin-top text-gray"><%= request.getAttribute("fromApName" + i)%></p>
                        <div class="row">
                            <div class="col-10 panel info-panel">
                                <div class="col-4 content">
                                    <strong><img src="${pageContext.request.contextPath}/public/assets/images/garuda_indonesia.png" alt="Logo" class="info-airline-logo"> <%= model.getAirlineModels().get(i).getName() %></strong>
                                    <p><%= request.getAttribute("airlineCode" + i)%>-<%= request.getAttribute("routeId" + i)%> - <span class="info-text-seat-class"><%= seatClass%></span></p>
                                </div>
                                <div class="col-4 content">
                                    <p class="text-gray no-margin-top">Baggage 20 kg</p>
                                    <p class="text-gray">Cabin Baggage 7 kg</p>
                                </div>
                                <div class="col-4 content">
                                    <p class="no-margin-top"><span class="text-bold">Aircraft</span> Boeing 737</p>
                                    <p><span class="text-bold">Seat layout</span> 3-3</p>
                                    <p><span class="text-bold">Seat pitch</span> 29 inches (standard)</p>
                                </div>
                            </div>
                        </div>
                        <p class="no-margin-bottom"><%= model.getAirportDestinationModel().getCity() %> (<%= model.getAirportDestinationModel().getCode() %>)</p>
                        <p class="no-margin-top text-gray"><%= model.getAirportDestinationModel().getName() %></p>
                    </div>
                </div>
            </div>
            <!-- price -->
            <div class="panel result-extended" id="price-1">
                <div class="col-6 content">
                    <h3 class="price-text-title">Conditions</h3>
                    <p><img src="${pageContext.request.contextPath}/public/assets/images/garuda_indonesia.png" alt="Logo" class="info-airline-logo"> <span class="price-text-title"><%= model.getAirlineModels().get(i).getName() %></span></p>
                    <p><%= model.getAirportDepartureModel().getCity() %> <i class='bx bx-right-arrow-alt'></i> <%= model.getAirportDestinationModel().getCity() %></p>
                    <p class="text-gray"><%= seatClass%></p>
                    <hr>
                    <h3 class="price-text-title">Included Benefits</h3>
                    <div class="col-1 text-center">
                        <i class='bx bxs-check-circle' style="color: blue;"></i>
                    </div>
                    <div class="col-11">
                        <p class="price-text-title no-margin-top">Free Protection</p>
                        <p>This free insurance will help cover you in case you get infected with COVID-19 during travel.</p>
                    </div>
                    <hr>
                    <div class="panel price-panel col-12">
                        <h3 class="no-margin-bottom">Baggage Prices</h3>
                        <p>Extra baggage purchase is not available for your flight</p>
                        <div class="col-1">
                            <img src="${pageContext.request.contextPath}/public/assets/images/garuda_indonesia.png" alt="Logo" class="price-panel-airline-logo">
                        </div>
                        <div class="col-11">
                            <p class="price-text-title no-margin-top" style="margin-bottom: 0;">&nbsp<%= model.getAirlineModels().get(i).getName() %></p>
                            <p>&nbsp<%= model.getAirportDepartureModel().getCity() %> &nbsp&nbsp<i class='bx bx-right-arrow-alt' ></i>&nbsp&nbsp <%= model.getAirportDestinationModel().getCity()%> - <span class="text-bold text-italic"><%= seatClass%></span></p>
                        </div>
                    </div>
                </div>
                <div class="col-6 content">
                    <h3 class="price-text-title">Price Details</h3>
                    <div class="col-6 text-left">
                        <p>Adult Basic Fare (x1)</p>
                        <p>Tax</p>
                        <p>Regular Total Price</p>
                        <p class="text-discount">Save</p>
                        <p class="text-discount">Free Protection</p>
                    </div>
                    <div class="col-6 text-right">
                        <p>Rp 1.277.300</p>
                        <p>Included</p>
                        <p>Rp 1.277.300</p>
                        <p class="text-discount">Rp 40.000</p>
                        <p class="text-discount">FREE</p>
                    </div>
                    <hr>
                    <div class="col-6 text-bold text-gray text-left">
                        <p class="no-margin-top">You Pay</p>
                    </div>
                    <div class="col-6 text-bold text-gray text-right">
                        <p class="no-margin-top">Rp 1.237.000</p>
                    </div>
                </div>
            </div>
        </div>
    </form>
        <% } %>
    <% } %>
    <% if (!resultFound) { %>
    <div class="row col-12" style="text-align: center">
        <h1>YOUR TICKET IS NOT FOUND</h1>
    </div>

    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>
    <br>

    <jsp:include page="/views/layouts/footer.jsp"></jsp:include>
    <jsp:include page="/views/layouts/scripts.jsp">
        <jsp:param name="js-file" value="search.js"></jsp:param>
    </jsp:include>
</body>
</html>