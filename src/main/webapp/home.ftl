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
            <div class="col-md-10 col-md-offset-1">
                <div class="panel panel-default">
                    <div class="panel-heading">Items
                        <span class="pull-right">
                                        <form action="/searchItems" method="POST">
                                            <input type="text" name="searchCode" style="width: 100px;"
                                                   placeholder="Search code..." required>
                                            <input type="text" name="searchName" style="width: 100px;"
                                                   placeholder="Search name..." required>
                                            <input type="text" name="category" style="width: 120px;"
                                                   placeholder="Search category..." required>
                                            <input type="number" name="price_from" style="width: 140px;"
                                                   placeholder="Search price from..." required>
                                            <input type="number" name="price_to" style="width: 130px;"
                                                   placeholder="Search price to..." required>
                                            <button type="submit" class="btn btn-default btn-xs">Filter</button>
                                        </form>
                                    </span>
                        </span>
                    </div>
                    <div class="panel-body">
                        <ul class="list-group">
                        <#list items as item>
                            <!-- End limit modal -->

                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <b>${item.name}</b> / <i>(code: ${item.code})</i>
                                        <u> ${item.getItemCategory().name} </u>| $${item.price}

                                        <form action="/addItemToCart" method="POST" class="pull-right">
                                            <input type="hidden" name="item_id" value="${item.id}">
                                            <input type="number" name="cartNnum" style="width: 50px" required>
                                            <button type="submit" class="btn btn-default btn-xs"
                                                    style="margin-top:-3px;">add
                                            </button>
                                        </form>

                                        <span class="pull-right" style="margin-right: 5px;">
                                            <#if item.getItemCategory().sale??>
                                                Sale:
                                           <i> ${item.getItemCategory().sale.name}
                                               (-${item.getItemCategory().sale.discount_percent}%)</i>
                                            <#else >

                                            </#if>
                                        </span>

                                    </h4>
                                </div>
                            </div>
                        <#else>
                            No items found!
                        </#list>
                        </ul>
                    </div>
                </div>
            </div>
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