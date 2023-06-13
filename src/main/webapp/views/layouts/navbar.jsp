<%@ page import="ccit.g2airline.project11deployableweb.model.database.AuthModel" %>
<%
    boolean isLogged = false;
    session = request.getSession();
    AuthModel model = (AuthModel) session.getAttribute("AuthModel");
    if (!session.isNew()) {
        isLogged = (Boolean) session.getAttribute("isLogged");
    }
%>
<div class="wrapper" id="wrapper">
    <nav>
        <input type="hidden" name="status" id="status" value="">
        <div class="container-flex">
            <div class="brand">
                <a href="${pageContext.request.contextPath}">G2 Airline</a>
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
                            <form action="/auth/profile" method="GET">
                                <input type="hidden" name="user-id" value="<%= model.getId() %>">
                                <button type="Submit">Account</button>
                            </form>
                            <form action="/flight/reserved" method="GET">
                                <input type="hidden" name="user-id" value="<%= model.getId() %>">
                                <button type="Submit">Ticket</button>
                            </form>
                            <form action="/auth/logout" method="POST">
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
                            <form action="${pageContext.request.contextPath}/auth/login" method="POST" class="login-form">
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
                                        <a href="${pageContext.request.contextPath}/auth/create">Register</a>
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
