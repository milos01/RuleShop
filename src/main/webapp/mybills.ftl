<!DOCTYPE html>
<html>

<head>
    <title>Rule Shop</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <link href="http://cdn.jsdelivr.net/webjars/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" media="screen"/>

</head>
<body>
<div>
    <nav class="navbar navbar-default navbar-static-top">
        <div class="container">
            <div class="navbar-header">

                <!-- Collapsed Hamburger -->
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                        data-target="#app-navbar-collapse">
                    <span class="sr-only">Toggle Navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>

                <!-- Branding Image -->
                <a class="navbar-brand" href="/">
                    Rule Shop
                </a>
            </div>

            <div class="collapse navbar-collapse" id="app-navbar-collapse">
                <!-- Left Side Of Navbar -->
                <ul class="nav navbar-nav">
                    &nbsp;
                </ul>

                <!-- Right Side Of Navbar -->
                <ul class="nav navbar-nav navbar-right">
                    <!-- Authentication Links -->

                <#if user??>

                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
                        ${user.first_name} (${user.role.role_name}) <span class="caret"></span>
                        </a>

                        <ul class="dropdown-menu" role="menu">
                            <#if user.hasRole('manager')>
                                <li>
                                    <a href="/manage">
                                        Manage settings
                                    </a>
                                </li>
                            </#if>
                            <#if user.hasRole('seller')>
                                <li>
                                    <a href="/sellsettings">
                                        Seller page
                                    </a>
                                    <a href="/billingsettings?filter=all">
                                        Billing page
                                    </a>
                                </li>
                            </#if>
                            <#if user.hasRole('buyer')>
                                <li>
                                    <a href="/profile">
                                        Profile page
                                    </a>
                                    <a href="/mybills">
                                        My orders/bills
                                    </a>
                                    <a href="/cart">
                                        My cart
                                    </a>
                                </li>
                            </#if>
                            <li><a href="/logout">Log out</a></li>
                        </ul>
                    </li>
                <#else>
                    <li><a href="#">Login</a></li>
                    <li><a href="#">Register</a></li>
                </#if>

                </ul>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">My orders
                    </div>

                    <div class="panel-body">
                        <ul class="list-group">
                        <#list bills as bill>
                            <#if bill.buyer.id == user.buyer.id>
                                <!-- End limit modal -->
                                <div class="panel panel-default">
                                    <div class="panel-heading">
                                        <h4 class="panel-title">
                                            <a data-toggle="collapse" data-parent="#accordion"
                                               href="#collapse${bill.id}">
                                                Bill: <b style="font-size: 18px">${bill.id}</b>
                                                <span class="pull-right">Successfully receved</span>
                                            </a>
                                            <#if bill.state == "porucen">
                                                <form action="/submitOrder" method="POST">
                                                    <input type="hidden" name="bill_id" style="margin-top: -15px"
                                                           value="${bill.id}">
                                                    <button class="btn btn-danger btn-xs pull-right" type="submit"
                                                            style="margin-top: -20px; margin-left: 10px">reject
                                                    </button>

                                                </form>
                                                <form action="/submitOrder" method="POST">
                                                    <input type="hidden" name="bill_id" style="margin-top: -15px"
                                                           value="${bill.id}">
                                                    <button class="btn btn-default btn-xs pull-right" type="submit"
                                                            style="margin-top: -20px">submit order
                                                    </button>

                                                </form>

                                            </#if>
                                        </h4>
                                    </div>
                                    <div id="collapse${bill.id}" class="panel-collapse collapse">
                                        <div class="panel-body">
                                            <#list bill.getBill_items() as item>
                                                <li class="list-group-item">Item: <b
                                                        style="font-size: 18px">${item.item_name}</b>
                                                    <span class="pull-right">
                                                    <span>(${item.item_quantity}) $${item.item_price}</span>
                                                </span>
                                                </li>
                                            </#list>
                                        </div>
                                    </div>
                                </div>
                            </#if>
                        <#else>
                            No items for order found!
                        </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

<!-- Scripts -->
<script src="/js/app.js"></script>
</body>
</html>