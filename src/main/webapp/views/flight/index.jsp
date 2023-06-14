<%@ page import="ccit.g2airline.project11deployableweb.dictionary.WebRoute" %>
<%@ page import="ccit.g2airline.project11deployableweb.dictionary.WebVariable" %>
<%@page contentType="text/html; ISO-8859-1" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="${pageContext.request.contextPath}/views/layouts/head.jsp">
        <jsp:param name="page-title" value="Home"/>
        <jsp:param name="css-file" value="home.css"/>
    </jsp:include>
</head>
<body>
    <%--  Navbar  --%>
    <jsp:include page="${pageContext.request.contextPath}/views/layouts/navbar.jsp"></jsp:include>

    <!-- Slideshow -->
    <div class="slideshow-background">
        <div class="container slideshow">
            <img src="${pageContext.request.contextPath}/resources/images/Airline_ads(1).jpg" alt="Airline Ad(1)" class="slideshow-img" id="img-1">
            <img src="${pageContext.request.contextPath}/resources/images/Airline_ads(2).jpg" alt="Airline Ad(2)" class="slideshow-img" id="img-2">
            <img src="${pageContext.request.contextPath}/resources/images/Airline_ads(3).jpg" alt="Airline Ad(3)" class="slideshow-img" id="img-3">
        </div>
    </div>

    <!-- Search Box -->
    <div class="container search-box focusable" id="search-box" style="z-index: unset !important;">
        <div class="header">
            <h2 class="text">Hello there!</h2>
            <h1 class="text">Are you looking to travel somewhere?</h1>
        </div>
        <div class="promo-group">
            <div class="row">
                <div class="col-1">
                    <h1 class="text"><i class='bx bxs-plane-alt' style="color: blue;"></i></h1>
                </div>
                <div class="col-11">
                    <h2 class="text">Look for promos & cheap ticket prices <a href="">here</a>!!!</h2>
                </div>
            </div>
            <br>
            <div class="row">
                <label for="trip-choice">
                    <input type="radio" id="trip-choice"  name="trip-choice" checked> Single-Trip
                </label>
            </div>
        </div>
        <form action="<%= WebRoute.FLIGHT_SHOW %>" method="GET">
            <div class="search-group">
                <div class="col-2_5 from-to" id="from">
                    <div class="select-box">
                        <div class="options-container options-container-from">
                            <div class="option option-from">
                                <input type="radio" class="radio" value="Tanggerang, Indonesia" name="<%= WebVariable.DEPARTURE_CITY %>" id="from-1" />
                                <label for="from-1">Tanggerang, Indonesia</label>
                            </div>

                            <div class="option option-from">
                                <input type="radio" class="radio" value="Surabaya, Indonesia" name="<%= WebVariable.DEPARTURE_CITY %>" id="from-2"/>
                                <label for="from-2">Surabaya, Indonesia</label>
                            </div>

                            <div class="option option-from">
                                <input type="radio" class="radio" value="Medan, Indonesia" name="<%= WebVariable.DEPARTURE_CITY %>" id="from-3"/>
                                <label for="from-3">Medan, Indonesia</label>
                            </div>

                            <div class="option option-from">
                                <input type="radio" class="radio" value="Makassar, Indonesia" name="<%= WebVariable.DEPARTURE_CITY %>" id="from-4"/>
                                <label for="from-4">Makassar, Indonesia</label>
                            </div>

                            <div class="option option-from">
                                <input type="radio" class="radio" value="Yogyakarta, Indonesia" name="<%= WebVariable.DEPARTURE_CITY %>" id="from-5"/>
                                <label for="from-5">Yogyakarta, Indonesia</label>
                            </div>

                            <div class="option option-from">
                                <input type="radio" class="radio" value="Denpasar-Bali, Indonesia" name="<%= WebVariable.DEPARTURE_CITY %>" id="from-6"/>
                                <label for="from-6">Denpasar-Bali, Indonesia</label>
                            </div>

                            <div class="option option-from">
                                <input type="radio" class="radio" value="Padang, Indonesia" name="<%= WebVariable.DEPARTURE_CITY %>" id="from-7"/>
                                <label for="from-7">Padang, Indonesia</label>
                            </div>

                            <div class="option option-from">
                                <input type="radio" class="radio" value="Palembang, Indonesia" name="<%= WebVariable.DEPARTURE_CITY %>" id="from-8"/>
                                <label for="from-8">Palembang, Indonesia</label>
                            </div>

                            <div class="option option-from">
                                <input type="radio" class="radio" value="Banjarmasin, Indonesia" name="<%= WebVariable.DEPARTURE_CITY %>" id="from-9"/>
                                <label for="from-9">Banjarmasin, Indonesia</label>
                            </div>
                        </div>
                        <div class="selected selected-from">
                            Select City From
                        </div>
                    </div>
                </div>
                <div class="col-2_5 from-to">
                    <div class="select-box">
                        <div class="options-container options-container-to">
                            <div class="option option-to">
                                <input type="radio" class="radio" value="Tanggerang, Indonesia" name="<%= WebVariable.DESTINATION_CITY %>" id="to-1" />
                                <label for="to-1">Tanggerang, Indonesia</label>
                            </div>

                            <div class="option option-to">
                                <input type="radio" class="radio" value="Surabaya, Indonesia" name="<%= WebVariable.DESTINATION_CITY %>" id="to-2" />
                                <label for="to-2">Surabaya, Indonesia</label>
                            </div>

                            <div class="option option-to">
                                <input type="radio" class="radio" value="Medan, Indonesia" name="<%= WebVariable.DESTINATION_CITY %>" id="to-3" />
                                <label for="to-3">Medan, Indonesia</label>
                            </div>

                            <div class="option option-to">
                                <input type="radio" class="radio" value="Makassar, Indonesia" name="<%= WebVariable.DESTINATION_CITY %>" id="to-4" />
                                <label for="to-4">Makassar, Indonesia</label>
                            </div>

                            <div class="option option-to">
                                <input type="radio" class="radio" value="Yogyakarta, Indonesia" name="<%= WebVariable.DESTINATION_CITY %>" id="to-5" />
                                <label for="to-5">Yogyakarta, Indonesia</label>
                            </div>
                            <div class="option option-to">
                                <input type="radio" class="radio" value="Denpasar-Bali, Indonesia" name="<%= WebVariable.DESTINATION_CITY %>" id="to-6" />
                                <label for="to-6">Denpasar-Bali, Indonesia</label>
                            </div>
                            <div class="option option-to">
                                <input type="radio" class="radio" value="Padang, Indonesia" name="<%= WebVariable.DESTINATION_CITY %>" id="to-7" />
                                <label for="to-7">Padang, Indonesia</label>
                            </div>
                            <div class="option option-to">
                                <input type="radio" class="radio" value="Palembang, Indonesia" name="<%= WebVariable.DESTINATION_CITY %>" id="to-8" />
                                <label for="to-8">Palembang, Indonesia</label>
                            </div>
                            <div class="option option-to">
                                <input type="radio" class="radio" value="Banjarmasin, indonesia" name="<%= WebVariable.DESTINATION_CITY %>" id="to-9" />
                                <label for="to-9">Banjarmasin, Indonesia</label>
                            </div>
                        </div>
                        <div class="selected selected-to">
                            Select City To
                        </div>
                    </div>
                </div>

                <div class="col-2_25 departure-arrival">
                    <label for="<%= WebVariable.DEPARTURE_DATE %>" class="text-gray label-form">Depart Date</label><br>
                    <input type="date" name="<%= WebVariable.DEPARTURE_DATE %>" id="<%= WebVariable.DEPARTURE_DATE %>">
                </div>
                <div class="col-2_25 departure-arrival">
                    <input type="checkbox" id="return-date-check" name="return-date-check" class="checkbox" onclick="returnFlight()">
                    <label for="return-date-check" class="text-gray label-form"> Return Date</label><br>
                    <input type="date" name="<%= WebVariable.RETURN_DATE %>" id="<%= WebVariable.RETURN_DATE %>" disabled>
                </div>
                <div class="col-2_5 passenger-class" onclick="displayListsPass()">
                    <label for="<%= WebVariable.PASSENGER_SEAT_CLASS %>" class="text-gray label-form">Passenger, Seat Class</label><br>
                    <input type="text" name="<%= WebVariable.PASSENGER_SEAT_CLASS %>" id="<%= WebVariable.PASSENGER_SEAT_CLASS %>" value="1 Passenger, Economy" onclick="displayListsPass()" readonly>
                    <div class="container-lists" id="passenger-list">
                        <input type="text" placeholder="Passenger and Seat Class" disabled>
                        <div class="col-6" id="passenger-count">
                            <div class="row">
                                <div class="col-2 text-center">
                                    <button type="button" class="btn-decrease">-</button>
                                </div>
                                <div class="col-8 text-center">
                                    <p>Adult</p>
                                </div>
                                <div class="col-2 text-center">
                                    <button type="button" class="btn-increase">+</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-2 text-center">
                                    <button type="button" class="btn-decrease">-</button>
                                </div>
                                <div class="col-8 text-center">
                                    <p>Child</p>
                                </div>
                                <div class="col-2 text-center">
                                    <button type="button" class="btn-increase">+</button>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-2 text-center">
                                    <button type="button" class="btn-decrease">-</button>
                                </div>
                                <div class="col-8 text-center">
                                    <p>Infant</p>
                                </div>
                                <div class="col-2 text-center">
                                    <button type="button" class="btn-increase">+</button>
                                </div>
                            </div>
                        </div>
                        <div class="col-6" id="seat-class">
                            <button type="button" class="seat-class-list">Economy</button>
                            <button type="button" class="seat-class-list">Premium Economy</button>
                            <button type="button" class="seat-class-list">Business</button>
                            <button type="button" class="seat-class-list">First Class</button>
                            <div class="col-6 text-center">
                                <button type="button" class="btn-reset" onclick="resetPassSeatClass()">Reset</button>
                            </div>
                            <div class="col-6 text-center">
                                <button type="button" class="btn-done" onclick="submitPassSeatClass()">Done</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer">
                <button type="submit" class="btn-submit">Search</button>
            </div>
        </form>
    </div>

    <!-- Main Background -->
    <div class="main-background"></div>

    <!-- Footer -->
    <jsp:include page="${pageContext.request.contextPath}/views/layouts/footer.jsp"></jsp:include>

    <%-- Scripts --%>
    <jsp:include page="${pageContext.request.contextPath}/views/layouts/scripts.jsp">
        <jsp:param name="js-file" value="home.js"/>
    </jsp:include>
</body>
</html>
