<%@ page language="java" contentType="text/html; charset=GB2312"
         pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<%@ page import="org.entity.student" %>
<%@ page import="org.dao.StudentDao" %>
<%@ page import="org.dao.ScDao" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";

%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Student System</title>
    <meta name="description" content="Student System">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="robots" content="all,follow">
    <!-- Bootstrap CSS-->
    <link rel="stylesheet" href="vendor/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome CSS-->
    <link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.min.css">
    <!-- Fontastic Custom icon font-->
    <link rel="stylesheet" href="css/fontastic.css">
    <!-- Google fonts - Poppins -->
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Poppins:300,400,700">
    <!-- theme stylesheet-->
    <link rel="stylesheet" href="css/style.default.css" id="theme-stylesheet">
    <!-- Favicon-->
    <link rel="shortcut icon" href="img/favicon.ico">
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/ajax-setup.js"></script>
</head>
<body>
<div class="page">
    <!-- Main Navbar-->
    <header class="header">
        <nav class="navbar">
            <!-- Search Box-->
            <div class="search-box">
                <button class="dismiss"><i class="icon-close"></i></button>
                <form id="searchForm" action="#" role="search">
                    <input type="search" placeholder="What are you looking for..." class="form-control">
                </form>
            </div>
            <div class="container-fluid">
                <div class="navbar-holder d-flex align-items-center justify-content-between">
                    <!-- Navbar Header-->
                    <div class="navbar-header">
                        <!-- Navbar Brand --><a href="index.jsp" class="navbar-brand d-none d-sm-inline-block">
                        <div class="brand-text d-none d-lg-inline-block"><span>Student System</span></div>
                    </a>
                        <!-- Toggle Button--><a id="toggle-btn" href="#"
                                                class="menu-btn active"><span></span><span></span><span></span></a>
                    </div>
                    <!-- Navbar Menu -->
                    <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
                        <!-- Search-->
                        <li class="nav-item d-flex align-items-center"><a id="search" href="#"><i
                                class="icon-search"></i></a></li>
                        <!-- Notifications-->
                        <li class="nav-item dropdown"><a id="notifications" rel="nofollow" data-target="#" href="#"
                                                         data-toggle="dropdown" aria-haspopup="true"
                                                         aria-expanded="false" class="nav-link"><i
                                class="fa fa-bell-o"></i><span class="badge bg-red badge-corner">4</span></a>
                            <ul aria-labelledby="notifications" class="dropdown-menu">
                                <li><a rel="nofollow" class="dropdown-item">
                                    <div class="notification">
                                        <div class="notification-content"><i class="fa fa-bullhorn bg-green"></i>You
                                            have 3 new notices
                                        </div>
                                        <div class="notification-time"><small>4 minutes ago</small></div>
                                    </div>
                                </a></li>
                                <li><a rel="nofollow" class="dropdown-item">
                                    <div class="notification">
                                        <div class="notification-content"><i class="fa fa-tag bg-orange"></i>You have 1
                                            new item
                                        </div>
                                        <div class="notification-time"><small>4 minutes ago</small></div>
                                    </div>
                                </a></li>
                            </ul>
                        </li>
                        <!-- Messages                        -->
                        <li class="nav-item dropdown"><a id="messages" rel="nofollow" data-target="#" href="#"
                                                         data-toggle="dropdown" aria-haspopup="true"
                                                         aria-expanded="false" class="nav-link"><i
                                class="fa fa-envelope-o"></i><span class="badge bg-orange badge-corner">2</span></a>
                            <ul aria-labelledby="notifications" class="dropdown-menu">
                                <li><a rel="nofollow" class="dropdown-item d-flex">
                                    <div class="msg-profile"><img src="img/avatar-3.jpg" alt="..."
                                                                  class="img-fluid rounded-circle"></div>
                                    <div class="msg-body">
                                        <h3 class="h5">Teacher Chen</h3><span>Sent You Message</span>
                                    </div>
                                </a></li>
                                <li><a rel="nofollow" class="dropdown-item d-flex">
                                    <div class="msg-profile"><img src="img/avatar-2.jpg" alt="..."
                                                                  class="img-fluid rounded-circle"></div>
                                    <div class="msg-body">
                                        <h3 class="h5">Teacher Lu</h3><span>Sent You Message</span>
                                    </div>
                                </a></li>
                                <li><a rel="nofollow" class="dropdown-item all-notifications text-center"> <strong>Read
                                    all messages </strong></a></li>
                            </ul>
                        </li>
                        <!-- Languages dropdown    -->
                        <li class="nav-item dropdown"><a id="languages" rel="nofollow" data-target="#" href="#"
                                                         data-toggle="dropdown" aria-haspopup="true"
                                                         aria-expanded="false"
                                                         class="nav-link language dropdown-toggle"><img src="img/GB.png"
                                                                                                        alt="English"><span
                                class="d-none d-sm-inline-block">English</span></a>
                            <ul aria-labelledby="languages" class="dropdown-menu">
                                <li><a rel="nofollow" class="dropdown-item"> <img src="img/CN.png" alt="Chinese"
                                                                                  class="mr-2">Chinese</a></li>
                            </ul>
                        </li>
                        <!-- Logout    -->
                        <li class="nav-item"><a href="login.jsp" class="nav-link logout" onclick="logout()"> <span
                                class="d-none d-sm-inline">Logout</span><i class="fa fa-sign-out"></i></a></li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div class="page-content d-flex align-items-stretch">
        <!-- Side Navbar -->
        <nav class="side-navbar">
            <!-- Sidebar Header-->
            <div class="sidebar-header d-flex align-items-center">
                <div class="avatar"><img id="userPhoto" class="img-fluid rounded-circle"></div>
                <div class="title">
                    <h1 class="h4" id="userName"></h1>
                    <p>major in <span id="userMajor"></span></p>
                    <p class="nowid" style="display:none" id="userId"></p>
                    <p class="nowterm" id="nowTerm"></p>
                </div>
            </div>
            <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
            <ul class="list-unstyled">
                <li><a href="index.jsp"> <i class="icon-home"></i>Home </a></li>
                <li class="active"><a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i
                        class="icon-grid"></i>Timetable & Grades </a>
                    <ul id="exampledropdownDropdown" class="collapse.in list-unstyled ">
                        <li><a href="course.jsp">Course Schedule</a></li>
                        <li id="testItem"><a href="javascript:;" onclick="getTableTest()">Test Schedule</a></li>
                        <li id="gradeItem"><a href="javascript:;" onclick="getTableGrade()">Grade</a></li>
                    </ul>
                </li>
                <li><a href="charts.jsp"> <i class="fa fa-bar-chart"></i>Analyze</a></li>
            </ul>
            <span class="heading">Extras</span>
            <ul class="list-unstyled">
                <li><a href="GetNoticeByStu"> <i class="fa fa-bullhorn"></i>Notice </a></li>
                <li><a> <i class="fa fa-comments"></i>Contact </a></li>
                <li><a> <i class="fa fa-hashtag"></i>More </a></li>
            </ul>
        </nav>
        <div class="content-inner">
            <!-- Page Header-->
            <header class="page-header">
                <div class="container-fluid">
                    <h2 class="no-margin-bottom">Timetable & Grades</h2>
                </div>
            </header>
            <!-- Breadcrumb-->
            <div class="breadcrumb-holder container-fluid">
                <ul class="breadcrumb">
                    <li class="breadcrumb-item active">Timetable & Grades</li>
                    <li id="titleInfo" class="breadcrumb-item active"></li>
                    <li class="breadcrumb-item dropdown term">
                        <a id="testMenu" href="#" data-toggle="dropdown" class="dropdown-collapse"></a>
                        <ul class="dropdown-menu list-unstyled has-shadow">
                            <li class="testInfo"><a onclick="getPastTest()"> Past </a></li>
                            <li class="pastTestInfo"><a onclick="getTableTest()"> Now </a></li>
                            <li class="gradeInfo"><a onclick="getPastGrade()"> Past </a></li>
                            <li class="pastGradeInfo"><a onclick="getTableGrade()"> Now </a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <section class="tables">
                <div class="container-fluid">
                    <div class="row testInfo disappear">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-close">
                                    <div class="dropdown">
                                        <button type="button" id="closeCard1" data-toggle="dropdown"
                                                aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i
                                                class="fa fa-ellipsis-v"></i></button>
                                        <div aria-labelledby="closeCard1"
                                             class="dropdown-menu dropdown-menu-right has-shadow">
                                            <a href="#" class="dropdown-item remove">
                                                <i class="fa fa-times"></i>Close</a></div>
                                    </div>
                                </div>
                                <div class="card-header d-flex align-items-center">
                                    <h3 id="testTableTitle" class="h4">2019-2020 First term</h3>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th class="include">Course</th>
                                                <th class="include">Date&Time</th>
                                                <th class="include">Place</th>
                                                <th class="include">Duration</th>
                                            </tr>
                                            </thead>
                                            <tbody class="testtable">
                                            <tr>
                                                <th scope="row"></th>
                                                <td class="include"></td>
                                                <td class="include"></td>
                                                <td class="include"></td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row pastTestInfo disappear">
                        <div class="col-lg-6">
                            <div class="card">
                                <div class="card-close">
                                    <div class="dropdown">
                                        <button type="button" id="closeCard1" data-toggle="dropdown"
                                                aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i
                                                class="fa fa-ellipsis-v"></i></button>
                                        <div aria-labelledby="closeCard1"
                                             class="dropdown-menu dropdown-menu-right has-shadow">
                                            <a href="#" class="dropdown-item remove">
                                                <i class="fa fa-times"></i>Close</a></div>
                                    </div>
                                </div>
                                <div class="card-header d-flex align-items-center">
                                    <h3 class="h4">2017-2018 First term</h3>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped">
                                            <thead>
                                            <tr>
                                                <th scope="row">Course</th>
                                                <th class="include">Time</th>
                                                <th class="include">Place</th>
                                            </tr>
                                            </thead>
                                            <tbody class="17-18-1">

                                            <tr>
                                                <th scope="row"></th>
                                                <td class="include"></td>
                                                <td class="include"></td>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row gradeInfo disappear">
                        <div class="col-lg-12">
                            <div class="card">
                                <div class="card-close">
                                    <div class="dropdown">
                                        <button type="button" id="closeCard1" data-toggle="dropdown"
                                                aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i
                                                class="fa fa-ellipsis-v"></i></button>
                                        <div aria-labelledby="closeCard1"
                                             class="dropdown-menu dropdown-menu-right has-shadow">
                                            <a href="#" class="dropdown-item remove">
                                                <i class="fa fa-times"></i>Close</a></div>
                                    </div>
                                </div>
                                <div class="card-header d-flex align-items-center">
                                    <h3 id="gradeTableTitle" class="h4"></h3>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th class="include">Course</th>
                                                <th class="include">Credit</th>
                                                <th class="include">Grade</th>
                                                <th class="include">GPA</th>
                                            </tr>
                                            </thead>
                                            <tbody class="nowgrade">
                                            <tr>
                                                <th scope="row"></th>
                                                <td class="include"></td>
                                                <td class="include"></td>
                                                <td class="include"></td>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row pastGradeInfo disappear">
                        <div class="col-lg-6">
                            <div class="card">
                                <div class="card-close">
                                    <div class="dropdown">
                                        <button type="button" id="closeCard1" data-toggle="dropdown"
                                                aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i
                                                class="fa fa-ellipsis-v"></i></button>
                                        <div aria-labelledby="closeCard1"
                                             class="dropdown-menu dropdown-menu-right has-shadow">
                                            <a href="#" class="dropdown-item remove">
                                                <i class="fa fa-times"></i>Close</a></div>
                                    </div>
                                </div>
                                <div class="card-header d-flex align-items-center">
                                    <h3 class="h4">2018-2019 First term</h3>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th class="include">Course</th>
                                                <th class="include">Grade</th>
                                                <th class="include">Credit</th>
                                                <th class="include">GPA</th>
                                            </tr>
                                            </thead>
                                            <tbody class="18-19-1">

                                            <tr>
                                                <th scope="row"></th>
                                                <td class="include"></td>
                                                <td class="include"></td>
                                                <td class="include"></td>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="card">
                                <div class="card-close">
                                    <div class="dropdown">
                                        <button type="button" id="closeCard1" data-toggle="dropdown"
                                                aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i
                                                class="fa fa-ellipsis-v"></i></button>
                                        <div aria-labelledby="closeCard1"
                                             class="dropdown-menu dropdown-menu-right has-shadow">
                                            <a href="#"> class="dropdown-item remove">
                                                <i class="fa fa-times"></i>Close</a></div>
                                    </div>
                                </div>
                                <div class="card-header d-flex align-items-center">
                                    <h3 class="h4">2018-2019 Second term</h3>
                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table">
                                            <thead>
                                            <tr>
                                                <th class="include">Course</th>
                                                <th class="include">Grade</th>
                                                <th class="include">Credit</th>
                                                <th class="include">GPA</th>
                                            </tr>
                                            </thead>
                                            <tbody class="18-19-2">

                                            <tr>
                                                <th scope="row"></th>
                                                <td class="include"></td>
                                                <td class="include"></td>
                                                <td class="include"></td>
                                            </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </div>
            </section>

        </div>
    </div>
</div>
<!-- JavaScript files-->
<script src="js/jquery.session.js"></script>
<script src="vendor/popper.js/umd/popper.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<script src="vendor/jquery.cookie/jquery.cookie.js"></script>
<script src="vendor/chart.js/Chart.min.js"></script>
<script src="vendor/jquery-validation/jquery.validate.min.js"></script>
<!-- Main File-->
<script src="js/front.js"></script>
<script src="js/util.js"></script>
<script>
    var info = location.search;
    var id = "";
    var term = getCurrentSemester();
    var academicYear = term.split(" ")[0];
    var semester = term.split(" ")[1];
    $(function () {
        updateData();
        if (info.endsWith("test")) getTableTest();
        else getTableGrade();
    });

    function updateData() {
        var term = getCurrentSemester();
        $(".nowterm").text(term);
        $.ajax({
            url: "/getUserInfo",
            type: "post",
            async: false,
            data: {},
            success: function (data) {
                id = data.userId;
                $(".nowid").text(id);
                $("#userMajor").text(data.userMajor);
                $("#userName").text(data.userName);
                $("#userPhoto").attr("src", data.userPhoto);
            }, error: function (data) {
            }
        });
    }

    function getTableTest() {
        $("#titleInfo").text("Test")
        $("#testMenu").text("Now");
        /*左侧导航*/
        $("#testItem").addClass("active");
        $("#gradeItem").removeClass("active");
        /*table && 下拉菜单选项*/
        $(".gradeInfo").addClass("disappear");
        $(".testInfo").removeClass("disappear");
        $(".pastTestInfo").addClass("disappear");
        $(".pastGradeInfo").addClass("disappear");
        $("#testTableTitle").text(term);
        $.ajax({
            url: "/student/getCoursesTest",
            type: "post",
            async: false,
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                "id": id,
                "academicYear": academicYear,
                "semester": semester
            }),
            success: function (data) {
                var table = "";
                data.forEach(item => {
                    table += "<tr><th scope=\"row\">" + item.courseName + "</th><td class=\"include\">" +
                        item.testTime + "</td><td class=\"include\">" + item.testPlace + "</td><td class=\"include\">" +
                        item.testDuration + "分钟</td></tr>";
                })
                $(".testtable").html(table);
            }
        });
    }

    function getPastTest() {
        $("#testMenu").text("Past");
        /*table && 下拉菜单选项*/
        $(".testInfo").addClass("disappear");
        $(".gradeInfo").addClass("disappear");
        $(".pastTestInfo").removeClass("disappear");
        $(".pastGradeInfo").addClass("disappear");
        $.ajax({
            url: "/student/getCoursesPastTest",
            type: "post",
            async: false,
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                "id": id,
                "academicYear": academicYear,
                "semester": semester
            }),
            success: function (res) {
                var tables = "";
                let sortedKeys = Object.keys(res).sort();
                for (let key of sortedKeys) {
                    let list = res[key]; // 拿到对应 List
                    var title = "<div class=\"col-lg-6\"><div class=\"card\">\<div class=\"card-close\">\<div class=\"dropdown\">" +
                        "<button type=\"button\" id=\"closeCard1\" data-toggle=\"dropdown\" aria-haspopup=\"true\" " +
                        "aria-expanded=\"false\" class=\"dropdown-toggle\"><i class=\"fa fa-ellipsis-v\"></i></button>" +
                        "<div aria-labelledby=\"closeCard1\" class=\"dropdown-menu dropdown-menu-right has-shadow\">" +
                        "<a href=\"#\" class=\"dropdown-item remove\"> <i class=\"fa fa-times\"></i>Close</a></div></div></div>" +
                        "<div class=\"card-header d-flex align-items-center\"><h3 class=\"h4\">" + key + "</h3></div>" +
                        "<div class=\"card-body\"><div class=\"table-responsive\"><table class=\"table table-striped\">" +
                        "<thead><tr><th scope=\"row\">Course</th><th class=\"include\">Time</th><th class=\"include\">Place</th>" +
                        "<th class=\"include\">Duration</th></tr></thead><tbody>";
                    var body = "";
                    for (let obj of list) {
                        body += "<tr><th scope=\"row\">" + obj.courseName + "</th><td class=\"include\">" + obj.testTime +
                            "</td><td class=\"include\">" + obj.testPlace + "</td><td class=\"include\">" + obj.testDuration +
                            "分钟</td></tr>";
                    }
                    var end = "</tbody></table></div></div></div></div></div>";
                    tables += title + body + end;
                }
                $("div.pastTestInfo").html(tables);
            }
        });

    }

    function getTableGrade() {
        $("#titleInfo").text("Grade")
        $("#testMenu").text("Now");
        /*左侧导航*/
        $("#testItem").removeClass("active");
        $("#gradeItem").addClass("active");
        /*table && 下拉菜单选项*/
        $(".testInfo").addClass("disappear");
        $(".gradeInfo").removeClass("disappear");
        $(".pastTestInfo").addClass("disappear");
        $(".pastGradeInfo").addClass("disappear");
        $("#gradeTableTitle").text(term);
        $.ajax({
            url: "/student/getCoursesGrade",
            type: "post",
            async: false,
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                "id": id,
                "academicYear": academicYear,
                "semester": semester
            }),
            success: function (data) {
                var table = "";
                data.forEach(item => {
                    let grade = item.grade != null && item.grade != undefined ? item.grade : " ";
                    let gpa = item.gpa != null && item.gpa != undefined ? item.gpa : " ";
                    table += "<tr><th scope=\"row\">" + item.courseName + "</th><td class=\"include\">" +
                        item.credit + "</td><td class=\"include\">" + grade + "</td><td class=\"include\">" +
                        gpa + "</td></tr>";
                })
                $(".nowgrade").html(table);
            }
        });
    }

    function getPastGrade() {
        $("#testMenu").text("Past");
        /*table && 下拉菜单选项*/
        $(".testInfo").addClass("disappear");
        $(".gradeInfo").addClass("disappear");
        $(".pastTestInfo").addClass("disappear");
        $(".pastGradeInfo").removeClass("disappear");
        $(".nowgrade").empty();
        $.ajax({
            url: "/student/getCoursesPastGrade",
            type: "post",
            async: false,
            headers: {
                'Content-Type': 'application/json'
            },
            data: JSON.stringify({
                "id": id,
                "academicYear": academicYear,
                "semester": semester
            }),
            success: function (res) {
                var tables = "";
                let sortedKeys = Object.keys(res).sort();
                for (let key of sortedKeys) {
                    let list = res[key]; // 拿到对应 List
                    var title = "<div class=\"col-lg-6\"><div class=\"card\">\<div class=\"card-close\">\<div class=\"dropdown\">" +
                        "<button type=\"button\" id=\"closeCard1\" data-toggle=\"dropdown\" aria-haspopup=\"true\" " +
                        "aria-expanded=\"false\" class=\"dropdown-toggle\"><i class=\"fa fa-ellipsis-v\"></i></button>" +
                        "<div aria-labelledby=\"closeCard1\" class=\"dropdown-menu dropdown-menu-right has-shadow\">" +
                        "<a href=\"#\" class=\"dropdown-item remove\"> <i class=\"fa fa-times\"></i>Close</a></div></div></div>" +
                        "<div class=\"card-header d-flex align-items-center\"><h3 class=\"h4\">" + key + "</h3></div>" +
                        "<div class=\"card-body\"><div class=\"table-responsive\"><table class=\"table table-striped\">" +
                        "<thead><tr><th scope=\"row\">Course</th><th class=\"include\">Credit</th><th class=\"include\">Grade</th>" +
                        "<th class=\"include\">GPA</th></tr></thead><tbody>";
                    var body = "";
                    for (let obj of list) {
                        let grade = obj.grade != null && obj.grade != undefined ? obj.grade : " ";
                        let gpa = obj.gpa != null && obj.gpa != undefined ? obj.gpa : " ";
                        body += "<tr><th scope=\"row\">" + obj.courseName + "</th><td class=\"include\">" + obj.credit +
                            "</td><td class=\"include\">" + grade + "</td><td class=\"include\">" + gpa +
                            "</td></tr>";
                    }
                    var end = "</tbody></table></div></div></div></div></div>";
                    tables += title + body + end;
                }
                $("div.pastGradeInfo").html(tables);
            }
        });
    }

</script>
</body>
</html>