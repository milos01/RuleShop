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
    <div class="modal fade newCategoryModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <form method="POST" action="/addCategory">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">New user category</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="pwd">Category name:</label>
                            <input class="form-control" type="text" name="category_name" required>
                        </div>
                        <p>Category limits</p>
                        <hr/>

                        <div class="form-group">
                            <label for="pwd">Total price from:</label>
                            <input class="form-control" type="number" name="limit_from" required>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Total price to:</label>
                            <input class="form-control" type="number" name="limit_to" required>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Point percent:</label>
                            <input class="form-control" type="number" name="point_percent" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="modal fade newItemCategoryModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <form method="POST" action="/addItemCategory">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">New item category</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="pwd">Code:</label>
                            <input class="form-control" type="text" name="code" required>
                        </div>

                        <div class="form-group">
                            <label for="pwd">Name:</label>
                            <input class="form-control" type="text" name="name" required>
                        </div>

                        <div class="form-group">
                            <label for="pwd">Max discount percent:</label>
                            <input class="form-control" type="number" name="point_percent" required>
                        </div>
                        <hr/>
                        <input type="checkbox" name="consumer_goods"> Consumer goods
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="modal fade newSaleModal" role="dialog">
        <div class="modal-dialog">

            <!-- Modal content-->
            <div class="modal-content">
                <form method="POST" action="/addNewSale">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">New sale</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="pwd">Code:</label>
                            <input class="form-control" type="text" name="code" required>
                        </div>

                        <div class="form-group">
                            <label for="pwd">Name:</label>
                            <input class="form-control" type="text" name="name" required>
                        </div>

                        <div class="form-group">
                            <label for="pwd">Sale starts at:</label>
                            <input class="form-control" type="date" name="sale_from" required>
                        </div>

                        <div class="form-group">
                            <label for="pwd">Sale ends at:</label>
                            <input class="form-control" type="date" name="sale_to" required>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Sale discount:</label>
                            <input class="form-control" type="number" name="point_percent" required>
                        </div>
                        <div class="form-group">
                            <label for="pwd">Choose categories:</label>
                        </div>
                    <#list iCat as cat>
                        <#if cat.sale??>
                        <#else>
                            <input type="checkbox" class="coding" name="categories" value="${cat.id}">
                            <label for="coding">${cat.name}</label>
                        </#if>
                    </#list>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Add</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- End category modal -->
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Manage buyer categories
                        <button class="btn btn-primary btn-xs pull-right" data-toggle="modal"
                                data-target=".newCategoryModal">Add category
                        </button>
                    </div>

                    <div class="panel-body">
                        <ul class="list-group">
                        <#list bCat as cat>
                        <#--Add limit modal-->
                            <div class="modal fade newLimitModal${cat.id}" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <form method="POST" action="/addLimit">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;
                                                </button>
                                                <h4 class="modal-title">New limit for ${cat.category_name} category</h4>
                                            </div>
                                            <div class="modal-body">
                                                <input class="form-control" type="hidden" name="category_id"
                                                       value="${cat.id}" required>

                                                <div class="form-group">
                                                    <label for="pwd">Total price from:</label>
                                                    <input class="form-control" type="number" name="limit_from"
                                                           required>
                                                </div>
                                                <div class="form-group">
                                                    <label for="pwd">Total price to:</label>
                                                    <input class="form-control" type="number" name="limit_to" required>
                                                </div>
                                                <div class="form-group">
                                                    <label for="pwd">Point percent:</label>
                                                    <input class="form-control" type="number" name="point_percent"
                                                           required>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                    Close
                                                </button>
                                                <button type="submit" class="btn btn-primary">Add</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- End limit modal -->
                        <#--Rename category modal-->
                            <div class="modal fade renameCatetoryModal${cat.id}" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <form method="POST" action="/renameCategory">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;
                                                </button>
                                                <h4 class="modal-title">New limit for ${cat.category_name} category</h4>
                                            </div>
                                            <div class="modal-body">
                                                <input class="form-control" type="hidden" name="category_id"
                                                       value="${cat.id}" required>

                                                <div class="form-group">
                                                    <label for="pwd">Category name:</label>
                                                    <input class="form-control" type="text" name="category_name"
                                                           value="${cat.category_name}" required>
                                                </div>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                    Close
                                                </button>
                                                <button type="submit" class="btn btn-primary">Rename</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- End rename category modal -->
                            <li class="list-group-item">Category: <b style="font-size: 18px">${cat.category_name}</b>
                                <button class="btn btn-default btn-xs pull-right" data-toggle="modal"
                                        data-target=".newLimitModal${cat.id}">add limit
                                </button>
                                <button class="btn btn-default btn-xs pull-right" data-toggle="modal"
                                        data-target=".renameCatetoryModal${cat.id}" style="margin-right: 10px">rename
                                </button>
                            </li>
                            <#list cat.getLimits() as limit>
                                <!-- New category modal -->
                                <!-- Modal -->
                                <div class="modal fade newCategoryModal${limit.id}" role="dialog">
                                    <div class="modal-dialog">
                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <form method="POST" action="/updateLimit">
                                                <div class="modal-header">
                                                    <button type="button" class="close" data-dismiss="modal">&times;
                                                    </button>
                                                    <h4 class="modal-title">New user category</h4>
                                                </div>
                                                <div class="modal-body">
                                                    <input class="form-control" type="hidden" name="limit_id"
                                                           value="${limit.id}" required>
                                                    <div class="form-group">
                                                        <label for="pwd">Total price from:</label>

                                                        <input class="form-control" type="number" name="limit_from"
                                                               value="${limit.limit_from}" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="pwd">Total price to:</label>
                                                        <input class="form-control" type="number" name="limit_to"
                                                               value="${limit.limit_to}" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="pwd">Point percent:</label>
                                                        <input class="form-control" type="number" name="point_percent"
                                                               value="${limit.point_percent}" required>
                                                    </div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="button" class="btn btn-default" data-dismiss="modal">
                                                        Close
                                                    </button>
                                                    <button type="submit" class="btn btn-primary">Update</button>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                                <!-- End category modal -->
                                <div class="list-group">
                                    <a href="#" class="list-group-item list-group-item-action list-group-item-warning">
                                        From: ${limit.limit_from} / To: ${limit.limit_to}
                                        <span class="pull-right">${limit.point_percent}%
                                    <button class="btn btn-default btn-xs pull-right" style="margin-left: 10px"
                                            data-toggle="modal"
                                            data-target=".newCategoryModal${limit.id}"></span>edit</button>
                                        </span>
                                    </a>
                                </div>
                            </#list>
                        <#else>
                            No categories found!
                        </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    <#--Category manage-->
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Manage item categories
                        <button class="btn btn-primary btn-xs pull-right" data-toggle="modal"
                                data-target=".newItemCategoryModal">Add item category
                        </button>
                    </div>

                    <div class="panel-body">
                        <ul class="list-group">
                        <#list iCat as cat>
                        <#--Add limit modal-->
                            <div class="modal fade updateItemCategory${cat.id}" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <form method="POST" action="/updateItemCategory">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;
                                                </button>
                                                <h4 class="modal-title">Update "${cat.name}" category</h4>
                                            </div>
                                            <div class="modal-body">
                                                <input class="form-control" type="hidden" name="category_id"
                                                       value="${cat.id}" required>

                                                <div class="form-group">
                                                    <label for="pwd">Code:</label>
                                                    <input class="form-control" type="text" name="code"
                                                           value="${cat.code}" disabled>
                                                </div>

                                                <div class="form-group">
                                                    <label for="pwd">Name:</label>
                                                    <input class="form-control" type="text" name="name"
                                                           value="${cat.name}" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="pwd">Max discount percent:</label>
                                                    <input class="form-control" type="number" name="point_percent"
                                                           value="${cat.max_discount_percent}" required>
                                                </div>
                                                <hr/>
                                                <#if cat.hasGlobaItemCat == true>
                                                    <input type="checkbox" name="consumer_goods" checked> Consumer goods
                                                <#else>
                                                    <input type="checkbox" name="consumer_goods"> Consumer goods
                                                </#if>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                    Close
                                                </button>
                                                <button type="submit" class="btn btn-primary">Update</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- End limit modal -->
                            <!-- End rename category modal -->
                            <li class="list-group-item">Category: <b style="font-size: 18px">${cat.name}</b>
                                <span class="pull-right" style="margin-right: 10px;">${cat.max_discount_percent}%
                                <button class="btn btn-default btn-xs pull-right" data-toggle="modal"
                                        data-target=".updateItemCategory${cat.id}"
                                        style="margin-left: 10px">Update</button>
                            </li>
                        <#else>
                            No categories found!
                        </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    <#--Sales manage-->
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Manage sales
                        <button class="btn btn-primary btn-xs pull-right" data-toggle="modal"
                                data-target=".newSaleModal">Add new sale
                        </button>
                    </div>

                    <div class="panel-body">
                        <ul class="list-group">
                        <#list sCat as cat>
                        <#--Add limit modal-->
                            <div class="modal fade updateSale${cat.id}" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <form method="POST" action="/updateSale">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;
                                                </button>
                                                <h4 class="modal-title">Update "${cat.name}" sale</h4>
                                            </div>
                                            <div class="modal-body">
                                                <input class="form-control" type="hidden" name="category_id"
                                                       value="${cat.id}" required>

                                                <div class="form-group">
                                                    <label for="pwd">Code:</label>
                                                    <input class="form-control" type="text" name="code"
                                                           value="${cat.code}" disabled>
                                                </div>

                                                <div class="form-group">
                                                    <label for="pwd">Name:</label>
                                                    <input class="form-control" type="text" name="name"
                                                           value="${cat.name}" required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="pwd">Sale starts at:</label>
                                                    <input class="form-control" type="date" name="sale_from"
                                                           value="${cat.sale_from?datetime?string('yyyy-MM-dd')}"
                                                           required>
                                                </div>

                                                <div class="form-group">
                                                    <label for="pwd">Sale ends at:</label>
                                                    <input class="form-control" type="date" name="sale_to"
                                                           value="${cat.sale_to?datetime?string('yyyy-MM-dd')}"
                                                           required>
                                                </div>
                                                <div class="form-group">
                                                    <label for="pwd">Sale discount:</label>
                                                    <input class="form-control" type="number" name="point_percent"
                                                           value="${cat.discount_percent}" required>
                                                </div>
                                                <#list iCat as item>
                                                    <#if item.sale??>
                                                        <#if item.sale.id == cat.id>
                                                            <input type="checkbox" class="coding" name="categories"
                                                                   value="${item.id}" checked>
                                                            <label for="coding">${item.name}</label>
                                                        </#if>
                                                    <#else>

                                                        <input type="checkbox" class="coding" name="categories"
                                                               value="${item.id}">
                                                        <label for="coding">${item.name}</label>
                                                    </#if>
                                                </#list>
                                            </div>
                                            <div class="modal-footer">
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                    Close
                                                </button>
                                                <button type="submit" class="btn btn-primary">Update</button>
                                            </div>
                                        </form>
                                    </div>
                                </div>
                            </div>
                            <!-- End limit modal -->
                            <!-- End rename category modal -->
                            <li class="list-group-item">Sale: <b style="font-size: 18px">${cat.name}</b>
                                (${cat.discount_percent}%)
                                <button class="btn btn-default btn-xs pull-right" data-toggle="modal"
                                        data-target=".updateSale${cat.id}" style="margin-left: 10px">Update
                                </button>
                                <span class="pull-right">From: ${cat.sale_from} / To: ${cat.sale_to}</span>
                            <#--<button class="btn btn-default btn-xs pull-right" data-toggle="modal" data-target=".newLimitModal${cat.id}">add limit</button>-->

                            </li>
                        <#else>
                            No categories found!
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