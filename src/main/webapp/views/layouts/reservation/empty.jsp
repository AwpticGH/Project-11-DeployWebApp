<% boolean isLogged = (Boolean) session.getAttribute("isLogged") %>
<div class="search-group">
    <div class="content">
        <div class="panel searched shadowed">
            <div class="col-6">
                <div class="row content">
                    <div class="col-6 content">
                        <h1><i class='bx bx-left-arrow-alt'></i>Go Back</h1>
                    </div>
                </div>
                <div class="col-12 content text-center">
<!--                    <h2>Nothing Here</h2>-->
                </div>
            </div>
            <div class="row content">
                <div class="col-12 content">
                    <h1>
                        <%= isLogged ? "Reserve Ticket First !" : "Login First !"%>
                    </h1>
                </div>
            </div>
        </div>
    </div>
</div>
