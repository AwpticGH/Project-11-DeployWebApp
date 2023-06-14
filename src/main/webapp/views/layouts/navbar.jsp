<%@ page import="ccit.g2airline.project11deployableweb.model.database.AuthModel" %>
<%@ page import="ccit.g2airline.project11deployableweb.dictionary.WebRoute" %>
<%@ page import="ccit.g2airline.project11deployableweb.dictionary.WebVariable" %>
<%
    boolean isLogged = false;
    AuthModel model = null;
    if (!session.isNew()) {
        model = (AuthModel) session.getAttribute("AuthModel");
        isLogged = model != null;
    }

    String alert;
    boolean isAlerted;
    try {
        alert = (String) request.getAttribute(WebVariable.ALERT);
        isAlerted = alert != null;
    }
    catch (NullPointerException e) {
        throw new RuntimeException(e);
    }
%>
<div class="wrapper" id="wrapper">
    <nav>
        <input type="hidden" name="status" id="status" value="">
        <div class="container-flex">
            <div class="brand">
                <a href="<%= WebRoute.FLIGHT_INDEX %>">G2 Airline</a>
            </div>
            <div class="burger">
                <div class="bar1"></div>
                <div class="bar2"></div>
                <div class="bar3"></div>
            </div>
            <div class="bg-sidebar"></div>
            <ul class="sidebar">
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
                <li><a href="#blog">Blog</a></li>
                <% if (isLogged) { %>
                    <!-- logged in -->
                    <li id="trigger-user">
                        <a href="#wrapper" id="nav-user" name="username"><%= model.getUsername() %></a>
                        <div id="user-panel">
                            <form action="<%= WebRoute.AUTH_SHOW %>" method="GET">
                                <input type="hidden" name="user-id" value="<%= model.getId() %>">
                                <button type="Submit">Account</button>
                            </form>
                            <form action="<%= WebRoute.RESERVATION_SHOW %>" method="GET">
                                <input type="hidden" name="user-id" value="<%= model.getId() %>">
                                <button type="Submit">Ticket</button>
                            </form>
                            <form action="<%= WebRoute.AUTH_LOGOUT %>" method="GET">
                                <button type="Submit">Logout</button>
                            </form>
                        </div>
                    </li>
                <% } %>
                <% if (!isLogged) { %>
                    <!-- logged out -->
                    <li id="trigger-login">
                        <a href="#wrapper" id="nav-login">Login</a>
                        <div id="login-panel">
                            <form action="<%= WebRoute.AUTH_LOGIN %>" method="POST" class="login-form">
                                <label for="email">Email</label><br>
                                <input type="email" name="email" id="email" placeholder="example@gmail.com">
                                <label for="password">Password</label><br>
                                <input type="password" name="password" id="password" placeholder="Password"><br>
                                <div class="btn-group">
                                    <div class="col-4">
                                        <button type="Submit">Login</button>
                                    </div>
                                    <div class="col-4">
                                        <p>Don't have an account?</p>
                                    </div>
                                    <div class="col-4 text-right">
                                        <a href="<%= WebRoute.AUTH_CREATE %>">Register</a>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>
                <% } %>
            </ul>
        </div>
    </nav>
</div>
<% if (isAlerted) { %>
    <script>
        alert("<%= alert %>");
        console.log("jancoook");
    </script>
<%
    request.removeAttribute(WebVariable.ALERT);
}
%>