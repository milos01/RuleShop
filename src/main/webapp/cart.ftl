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
                    <div class="panel-heading">Bill
                    </div>

                    <div class="panel-body">

                        <ul class="list-group">
                            <ul class="list-group">
                            <#list billss as bill>
                                <ul class="list-group">
                                    <div class="container">Original price: ${bill.origina_price}</div>
                                    <div class="container">Discount: ${bill.discount_percent}%</div>
                                    <div class="container">Final price: ${bill.final_price}</div>
                                    <div class="container">Gained points: ${bill.ganed_points}</div>
                                </ul>
                                <hr>
                                <#list bill.discounts as bill_d>
                                    <ul class="list-group">
                                        <div class="container"><i>Bill discount: ${bill_d.discount_percent} / ${bill_d.dt}</i></div>
                                    </ul>
                                </#list>
                                <hr>
                                <#list bill.bill_items as items>
                                    <#list items.discounts as items_d>
                                        <ul class="list-group">
                                            <div class="container"><i>Item discount: ${items_d.discount_percent} / ${items_d.dt}</i></div>
                                        </ul>
                                    </#list>
                                </#list>
                            <#else>
                                No bill found!
                            </#list>
                            </ul>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">My cart
                    </div>

                    <div class="panel-body">

                        <ul class="list-group">
                            <ul class="list-group">
                                <#list cart_items as citem>
                                    <li class="list-group-item">${citem.item.name} <span class="badge">${citem.quantity} x $${citem.item.price}</span></li>
                                <#else>
                                    No items found!
                                </#list>
                            </ul>
                        </ul>
                        <form action="/orderCheckout" method="POST">
                            <button class="btn btn-primary pull-right" type="submit">Order & Checkout</button>
                        </form>
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