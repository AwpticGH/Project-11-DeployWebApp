<%@ page import="ccit.g2airline.project11deployableweb.model.database.AuthModel" %>
<!DOCTYPE html>
<html>
<head>
    <jsp:include page="/views/layouts/head.jsp">
        <jsp:param name="css-file" value="account-profile.css"></jsp:param>
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
<body>
    <jsp:include page="/views/layouts/navbar.jsp"></jsp:include>

    <% AuthModel model = (AuthModel)session.getAttribute("AuthModel"); %>
    <!-- Begin page content -->
    <main class="flex-shrink-0">
        <div class="container">
            <h1 class="mt-5">Edit Account - <%= model.getUsername() %></h1>
            <p class="lead">Edit account <%= model.getUsername()  %> in G2Airline</p>
            <div class="row pt-5">
                <div class="col-12">
                    <form method="POST" action="Account">
                        <input type="hidden" name="accountId" value="<%= model.getId() %>" />
                        <div class="mb-3">
                            <label class="form-label">Name</label>
                            <input type="text" class="form-control"
                                   name="name" readonly
                                   value="<%= model.getName() %>"
                            >
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Email</label>
                            <input type="text" class="form-control"
                                   name="email" readonly
                                   value="<%= model.getEmail() %>"
                            >
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control"
                                   name="password"
                                   value="<%= model.getPassword() %>"
                            >
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Title</label>
                            <select class="form-select" aria-label="Default select example" name="title">
                                <option value="Mr" <%= (model.getTitle().equals("Mr") ? "selected" : "") %>>Mr</option>
                                <option value="Mrs" <%= (model.getTitle().equals("Mrs") ? "selected" : "") %>>Mrs</option>
                                <option value="Miss" <%= (model.getTitle().equals("Miss") ? "selected" : "") %>>Miss</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Gender</label>
                            <select class="form-select" aria-label="Default select example" name="gender">
                                <option value="Male" <%= (model.getGender().equals("Male") ? "selected" : "") %>>Male</option>
                                <option value="Female" <%= (model.getGender().equals("Female") ? "selected" : "") %>>Female</option>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Date of Birth</label>
                            <input type="date" class="form-control"
                                   placeholder="Date of Birth..." name="dob" required
                                   value="<%= model.getDate_of_birth() %>"
                            >
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Phone Number</label>
                            <input type="number" class="form-control"
                                   placeholder="Phone Number..." name="phoneNumber" required
                                   value="<%= model.getPhone_number() %>"
                            >
                        </div>
                        <div class="mb-3">
                            <button type="submit" class="btn btn-primary btn-small btn-rounded">Edit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </main>

    <jsp:include page="/views/layouts/footer.jsp"></jsp:include>
    <jsp:include page="/views/layouts/scripts.jsp"></jsp:include>

</body>
</html>