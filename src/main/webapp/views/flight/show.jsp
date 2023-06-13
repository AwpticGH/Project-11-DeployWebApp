<%@ page import="ccit.g2airline.project11deployableweb.model.database.AuthModel" %>
<%@ page import="java.text.ParseException" %><%--
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
        AuthModel authModel = (AuthModel)session.getAttribute("AuthModel");

        Boolean resultFound = (Boolean)request.getAttribute("resultFound");

        String fromCity = Helper.StringHelper.castCity(request.getParameter("from-city"));
        String toCity = Helper.StringHelper.castCity(request.getParameter("to-city"));
        String departDate = null;
        try {
            departDate = Helper.DateHelper.dateFormat(request.getParameter("departure-date"));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        String seatClass = Helper.StringHelper.getSeatClass(request.getParameter("passenger-seat-class"));
        String passCount = Helper.StringHelper.getPassCount(request.getParameter("passenger-seat-class"));

    %>
    <!-- panel - searched -->
    <div class="panel searched shadowed">
        <div class="col-6">
            <div class="row content">
                <div class="col-4 content">
                    <h1><%= fromCity%></h1>
                </div>
                <div class="col-4 content text-center">
                    <h1><i class='bx bx-right-arrow-alt'></i></h1>
                </div>
                <div class="col-4 content text-center">
                    <h1><%= toCity%></h1>
                </div>
            </div>
            <div class="row content">
                <div class="col-12 content">
                    <% if (Integer.parseInt(passCount) == 1) {%>
                    <p class="searched-footer"><%= departDate%> &nbsp||&nbsp <%= passCount%> Passenger &nbsp||&nbsp <%= seatClass%></p>
                    <% } %>
                    <% if (Integer.parseInt(passCount) > 1) {%>
                    <p class="searched-footer"><%= departDate%> &nbsp||&nbsp <%= passCount%> Passengers &nbsp||&nbsp <%= seatClass%></p>
                    <% } %>
                </div>
            </div>
        </div>
        <div class="col-6 content">
            <button class="btn-panel-searched shadowed" onclick="history.back()">Change Search</button>
        </div>
    </div>


    <% if(resultFound) {
        Integer resultCounter = (Integer)request.getAttribute("resultCounter");
        for (int i = 0; i < resultCounter; i++) { %>
    <!-- panel - result -->
    <form action="Reservation">
        <input name="accountId" type="hidden" value="<%= accountRs.getString("id")%>">
        <input name="flightId" type="hidden" value="<%= request.getAttribute("flightId" + i)%>">
        <input name="passengerCount" type="hidden" value="<%= passCount%>">
        <input name="seatClass" type="hidden" value="<%= seatClass%>">
        <div id="result">
            <div class="panel result shadowed" onclick="clickedResult1(), extendResult1()">
                <div class="col-4">
                    <div class="col-12 content">
                        <h2><img class="result-airline-logo" src="public/frontend/images/garuda_indonesia.png"> <%= request.getAttribute("airlineName" + i)%></h2>
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
                            <p class="result-text-time"><%= request.getAttribute("departTime" + i)%></p>
                            <p><%= request.getAttribute("fromApCode" + i)%></p>
                        </div>
                        <div class="col-4 content text-center">
                            <p class="result-text-time-duration"><%= request.getAttribute("timeOfFlight" + i)%> M</p>
                            <hr>
                            <!-- <div>
                                <i class='bx bx-chevron-left'></i><hr class="result-text-time-separator"><i class='bx bx-chevron-right' ></i>
                            </div> -->
                        </div>
                        <div class="col-4 content text-center">
                            <p class="result-text-time"><%= request.getAttribute("arrivalTime" + i)%></p>
                            <p><%= request.getAttribute("toApCode" + i)%></p>
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
                        <p class="no-margin-bottom no-margin-top"><%= request.getAttribute("departTime" + i)%></p>
                        <p class="text-gray no-margin-top"><%= departDate%>(need convertion)</p>
                        <br>
                        <br>
                        <br>
                        <p><i class='bx bx-right-arrow-alt'></i> <%= request.getAttribute("timeOfFlight" + i)%> M</p>
                        <br>
                        <br>
                        <br>
                        <p class="no-margin-bottom"><%= request.getAttribute("arrivalTime" + i)%></p>
                        <p class="text-gray no-margin-top no-margin-bottom"><%= departDate%>(need convertion)</p>
                    </div>
                    <div class="col-10 content" style="padding-top: 0; padding-bottom: 0;">
                        <p class="no-margin-bottom no-margin-top"><%= fromCity%> (<%= request.getAttribute("fromApCode" + i)%>)</p>
                        <p class="no-margin-top text-gray"><%= request.getAttribute("fromApName" + i)%></p>
                        <div class="row">
                            <div class="col-10 panel info-panel">
                                <div class="col-4 content">
                                    <strong><img src="public/frontend/images/garuda_indonesia.png" alt="Logo" class="info-airline-logo"> <%= request.getAttribute("airlineName" + i)%></strong>
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
                        <p class="no-margin-bottom"><%= toCity%> (<%= request.getAttribute("toApCode" + i)%>)</p>
                        <p class="no-margin-top text-gray"><%= request.getAttribute("toApName" + i)%></p>
                    </div>
                </div>
            </div>
            <!-- price -->
            <div class="panel result-extended" id="price-1">
                <div class="col-6 content">
                    <h3 class="price-text-title">Conditions</h3>
                    <p><img src="public/frontend/images/garuda_indonesia.png" alt="Logo" class="info-airline-logo"> <span class="price-text-title"><%= request.getAttribute("airlineName" + i)%></span></p>
                    <p><%= fromCity%> <i class='bx bx-right-arrow-alt'></i> <%= toCity%></p>
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
                            <img src="public/frontend/images/garuda_indonesia.png" alt="Logo" class="price-panel-airline-logo">
                        </div>
                        <div class="col-11">
                            <p class="price-text-title no-margin-top" style="margin-bottom: 0;">&nbsp<%= request.getAttribute("airlineName" + i)%></p>
                            <p>&nbsp<%= fromCity%> &nbsp&nbsp<i class='bx bx-right-arrow-alt' ></i>&nbsp&nbsp <%= toCity%> - <span class="text-bold text-italic"><%= seatClass%></span></p>
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