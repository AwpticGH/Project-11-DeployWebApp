<%@ page import="ccit.g2airline.project11deployableweb.model.database.AuthModel" %>
<!DOCTYPE html>
<html>

<head>
    <jsp:include page="/views/layouts/head.jsp">
        <jsp:param name="css-file" value=""></jsp:param>
    </jsp:include>
    <style>
        .input-group-append {
            cursor: pointer;
        }
    </style>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
    <link href='https://unpkg.com/boxicons@2.1.2/css/boxicons.min.css' rel='stylesheet'>
    <link rel="stylesheet" href="https://unpkg.com/js-datepicker/dist/datepicker.min.css">
</head>

<body class="d-flex flex-column h-100">
<jsp:include page="/views/layouts/navbar.jsp"></jsp:include>


<!-- Begin page content -->
<%
    int passCount = Integer.parseInt(request.getParameter("passengerCount"));
    String flightId = request.getParameter("flightId");
    String seatClass = request.getParameter("seatClass");

    AuthModel model = (AuthModel)session.getAttribute("AuthModel");
%>
<main class="flex-shrink-0">
    <div class="container">
        <h1 class="mt-5">Reserve Flight -
            <%= model.getUsername() %>
        </h1>
        <p class="lead">Fill In Ticket Detail</p>
        <div class="row pt-5">
            <div class="col-12">
                <form method="POST" action="Reservation">
                    <input type="hidden" name="flightId" value="<%= flightId%>" />
                    <input type="hidden" name="accountId" value="<%= model.getId()%>" />
                    <input type="hidden" name="passengerCount" value="<%= passCount%>" />
                    <input type="hidden" name="seatClass" value="<%= seatClass%>">
                    <% for (int i = 0; i < passCount; i++) { %>
                    <p class="lead">Person - <%= (Integer)i+1%></p>
                    <div class="mb-3">
                        <label class="form-label">Name</label>
                        <input type="text" class="form-control" placeholder="Enter your name" name="name<%= i%>" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Gender</label>
                        <select class="form-select" aria-label="Default select example" name="gender<%= i%>" required>
                            <option value="Male">Male</option>
                            <option value="Female">Female</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Date Of Birth</label>
                        <input type="date" class="form-control" placeholder="Enter Date Of Birth" name="dob<%= i%>" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Phone Number</label>
                        <input type="number" class="form-control" placeholder="Enter Your Phone number" name="phoneNumber<%= i%>">
                    </div>
                    <% } %>
                    <div class="mb-3">
                        <button type="submit" class="btn btn-primary btn-small btn-rounded">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</main>

<jsp:include page="/views/layouts/footer.jsp"></jsp:include>
<jsp:include page="/views/layouts/scripts.jsp">
    <jsp:param name="js-file" value=""></jsp:param>
</jsp:include>


</body>
</html>