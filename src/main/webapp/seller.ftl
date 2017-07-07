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
                <a class="navbar-brand" href="#">
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
                        ${user.first_name} <span class="caret"></span>
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
                    <div class="panel-heading">Items to order
                        <#--<button class="btn btn-primary btn-xs pull-right" data-toggle="modal" data-target=".newCategoryModal"></button>-->
                    </div>

                    <div class="panel-body">
                        <ul class="list-group">
                        <#list orderItems as item>
                        <#--Add limit modal-->
                            <div class="modal fade orderItem${item.id}" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <form method="POST" action="/orderItem">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Order for "${item.name}" item</h4>
                                            </div>
                                            <div class="modal-body">
                                                <input class="form-control" type="hidden" name="item_id" value="${item.id}">

                                                <div class="form-group">
                                                    <label for="pwd">Quantity</label>
                                                    <input class="form-control" type="number" name="item_quantity">
                                                </div>

                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                                <button type="submit" class="btn btn-primary">Order</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- End limit modal -->

                            <li class="list-group-item">Category: <b style="font-size: 18px">${item.name}</b>
                                <button class="btn btn-default btn-xs pull-right" data-toggle="modal" data-target=".orderItem${item.id}" style="margin-left: 10px">order this item</button>
                                <span class="pull-right">Items left in shop: <b>${item.number_left}</b>/ Minimum number on lager: <b>${item.lager_min_state}</b></span>
                                <#--<button class="btn btn-default btn-xs pull-right" data-toggle="modal" data-target=".renameCatetoryModal${cat.id}" style="margin-right: 10px">rename</button>-->
                            </li>

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