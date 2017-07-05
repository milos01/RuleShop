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
                <form method="POST" action="{!! route('addCategory') !!}">
                    {{ csrf_field() }}
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">New user category</h4>
                    </div>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="pwd">Category name:</label>
                            <input class="form-control" type="text" name="category_name">
                        </div>
                        <p>Category limits</p>
                        <hr/>

                        <div class="form-group">
                            <label for="pwd">Total price from:</label>
                            <input class="form-control" type="number" name="limit_from">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Total price to:</label>
                            <input class="form-control" type="number" name="limit_to">
                        </div>
                        <div class="form-group">
                            <label for="pwd">Point percent:</label>
                            <input class="form-control" type="number" name="point_percent">
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
    <!-- End category modal -->
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-md-offset-2">
                <div class="panel panel-default">
                    <div class="panel-heading">Manage buyer categories
                        <button class="btn btn-primary btn-xs pull-right" data-toggle="modal" data-target=".newCategoryModal">Add category</button>
                    </div>

                    <div class="panel-body">
                        <ul class="list-group">
                            @foreach($bcats as $bcat)
                            <li class="list-group-item">Category: <b style="font-size: 18px">{{$bcat->category_name}}</b>
                                <button class="btn btn-default btn-xs pull-right">add limit</button>
                            </li>
                            @foreach($bcat->limits as $limit)
                            <!-- New category modal -->
                            <!-- Modal -->
                            <div class="modal fade newCategoryModal{{$limit->id}}" role="dialog">
                                <div class="modal-dialog">
                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <form method="POST" action="{!! route('addCategory') !!}">
                                            {{ csrf_field() }}
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">New user category</h4>
                                            </div>
                                            <div class="modal-body">
                                                <div class="form-group">
                                                    <label for="pwd">Category name:</label>
                                                    <input class="form-control" type="text" name="category_name">
                                                </div>
                                                <p>{{$limit->id}}</p>
                                                <hr/>

                                                <div class="form-group">
                                                    <label for="pwd">Total price from:</label>
                                                    <input class="form-control" type="number" name="limit_from">
                                                </div>
                                                <div class="form-group">
                                                    <label for="pwd">Total price to:</label>
                                                    <input class="form-control" type="number" name="limit_to">
                                                </div>
                                                <div class="form-group">
                                                    <label for="pwd">Point percent:</label>
                                                    <input class="form-control" type="number" name="point_percent">
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
                            <!-- End category modal -->
                            <div class="list-group">
                                <a href="#" class="list-group-item list-group-item-action list-group-item-warning">
                                    <#--From: ${{$limit->limit_from}} / To: ${{$limit->limit_to}}-->
                                    <span class="pull-right">{{$limit->point_percent}}%
                                    <button class="btn btn-default btn-xs pull-right" style="margin-left: 10px" data-toggle="modal" data-target=".newCategoryModal{{$limit->id}}"></span>edit</button>
                                    </span>
                                </a>
                            </div>
                            @endforeach
                            @endforeach
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