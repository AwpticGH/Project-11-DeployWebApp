<%@ page import="ccit.g2airline.project11deployableweb.model.database.AuthModel" %>
<%@ page import="ccit.g2airline.project11deployableweb.model.database.FlightModel" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.ParseException" %>
<%@ page import="ccit.g2airline.project11deployableweb.helper.DateHelper" %>
<%@ page import="ccit.g2airline.project11deployableweb.helper.TimeHelper" %>
<%@ page import="ccit.g2airline.project11deployableweb.model.database.ReservationInfoModel" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/views/layouts/head.jsp">
        <jsp:param name="css-file" value="ticket.css"></jsp:param>
    </jsp:include>
</head>
<body>
    <jsp:include page="/views/layouts/navbar.jsp"></jsp:include>

    <!-- content -->
    <!-- if has ticket -->
    <%
        AuthModel authModel = (AuthModel) session.getAttribute("AuthModel");
        FlightModel flight = (FlightModel) request.getAttribute("flight");
        ArrayList<ReservationInfoModel> reservations = (ArrayList<ReservationInfoModel>)request.getAttribute("reservations");
        int pagination = 5;
        int pages = 1;

        if (authModel != null && reservations != null) {
    %>
    <div class="content">
        <div class="panel shadowed">
            <div class="flight-detail">
                <div class="col-6">
                    <div class="row content">
                        <div class="col-4 content">
                            <h1><%= "fromCity"%></h1>
                        </div>
                        <div class="col-4 content text-center">
                            <h1><i class='bx bx-right-arrow-alt'></i></h1>
                        </div>
                        <div class="col-4 content text-center">
                            <h1><%= "toCity"%></h1>
                        </div>
                    </div>
                    <div class="row content">
                        <div class="col-12 content">
                            <h3><%= "airlineName"%></h3>
                        </div>
                    </div>
                    <div class="row content">
                        <div class="col-12 content footer">
                            <p><%= DateHelper.dateFormat("dd MMM yy", flight.getTime_of_departure())%> &nbsp||&nbsp <%= reservations.size() %> <%= reservations.size() > 1 ? "Passengers" : "Passenger" %>> &nbsp||&nbsp <%= reservations.get(0).getSeat_class() %></p>
                        </div>
                    </div>
                </div>
                <div class="col-6">
                    <div class="header">
                        <strong><%= "airlineCode"%>-<%= "routeId"%></strong>
                        <hr>
                    </div>
                    <div class="from-to-time-airport">
                        <div class="col-4">
                            <div class="text from-to text-center">
                                <p><%= "timeOfDeparture"%></p>
                                <p><%= "fromApCode"%></p>
                                <p><%= "fromApName"%></p>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="text time-of-flight text-center">
                                <p><%= "timeOfFlight"%> M</p>
                                <hr>
                            </div>
                        </div>
                        <div class="col-4">
                            <div class="text from-to text-center">
                                <p><%= TimeHelper.addTime("timeOfDeparture", "timeOfFlight")%></p>
                                <p><%= "toApCode"%></p>
                                <p><%= "toApName"%></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <hr>
            <div class="passenger-detail">
                <div class="col-12">
                    <br>
                    <table>
                        <tr>
                            <th class="number">No</th>
                            <th class="name">Name</th>
                            <th>Age</th>
                            <th>Gender</th>
                            <th class="phone">Phone Number</th>
                        </tr>
                        <% for (int i = 0; i < reservations.size(); i++) { %>
                        <tr>
                            <td><%= i + 1 %>.</td>
                            <td><%= (reservations.get(i).getAge().equals("Male") ? "Mr" : (Integer.parseInt(reservations.get(i).getAge()) > 18 ? "Mrs" : "Miss")) %>. <%= reservations.get(i).getName() %></td>
                            <td><%= reservations.get(i).getAge() %></td>
                            <td><%= reservations.get(i).getGender() %></td>
                            <td>+62 <%= reservations.get(i).getPhone_number() %></td>
                        </tr>
                        <% } %>
                    </table>
                </div>
            </div>
            <div class="text-center">
                <form action="/reservation/delete" method="post">
                    <input type="hidden" name="flightId" value="<%= flight.getId() %>">
                    <input type="hidden" name="accountId" value="<%= authModel.getId() %>">
                    <input type="hidden" name="reservationId" value="<%= reservations.get(0).getReservation_id() %>">
                    <button type="submit" onclick="return confirm('Are You Sure?')">Cancel Flight</button>
                </form>
            </div>
        </div>
    </div>
    <%  } %>
    <!-- if reservation is not found -->
    <%  if (reservations == null) { %>
        <jsp:include page="/views/layouts/reservation/empty.jsp"></jsp:include>
    <%  } %>
    <!-- end of content -->
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
    <br>
    <br>
    <br>
    <br>
    <br>



    <jsp:include page="/views/layouts/footer.jsp"></jsp:include>
    <jsp:include page="/views/layouts/scripts.jsp">
        <jsp:param name="js-file" value=""></jsp:param>
    </jsp:include>
</body>
</html>