<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="beans.student"%>
<%@ page import="dao.StudentDao"%>
<%@ page import="dao.ScDao"%>

<% 
   String path = request.getContextPath();
   String basePath = request.getScheme() + "://" + request.getServerName() + ":" +request.getServerPort() + path + "/";
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
                <!-- Toggle Button--><a id="toggle-btn" href="#" class="menu-btn active"><span></span><span></span><span></span></a>
              </div>
              <!-- Navbar Menu -->
              <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
                <!-- Search-->
                <li class="nav-item d-flex align-items-center"><a id="search" href="#"><i class="icon-search"></i></a></li>
                <!-- Notifications-->
                <li class="nav-item dropdown"> <a id="notifications" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-bell-o"></i><span class="badge bg-red badge-corner">4</span></a>
                  <ul aria-labelledby="notifications" class="dropdown-menu">
                    <li><a rel="nofollow"  class="dropdown-item"> 
                        <div class="notification">
                          <div class="notification-content"><i class="fa fa-bullhorn bg-green"></i>You have 3 new notices </div>
                          <div class="notification-time"><small>4 minutes ago</small></div>
                        </div></a></li>
                    <li><a rel="nofollow"  class="dropdown-item"> 
                        <div class="notification">
                          <div class="notification-content"><i class="fa fa-tag bg-orange"></i>You have 1 new item</div>
                          <div class="notification-time"><small>4 minutes ago</small></div>
                        </div></a></li>
                  </ul>
                </li>
                <!-- Messages                        -->
                <li class="nav-item dropdown"> <a id="messages" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-envelope-o"></i><span class="badge bg-orange badge-corner">2</span></a>
                  <ul aria-labelledby="notifications" class="dropdown-menu">
                    <li><a rel="nofollow"  class="dropdown-item d-flex"> 
                        <div class="msg-profile"> <img src="img/avatar-3.jpg" alt="..." class="img-fluid rounded-circle"></div>
                        <div class="msg-body">
                          <h3 class="h5">Teacher Chen</h3><span>Sent You Message</span>
                        </div></a></li>
                    <li><a rel="nofollow"  class="dropdown-item d-flex"> 
                        <div class="msg-profile"> <img src="img/avatar-2.jpg" alt="..." class="img-fluid rounded-circle"></div>
                        <div class="msg-body">
                          <h3 class="h5">Teacher Lu</h3><span>Sent You Message</span>
                        </div></a></li>
                    <li><a rel="nofollow"  class="dropdown-item all-notifications text-center"> <strong>Read all messages   </strong></a></li>
                  </ul>
                </li>
                <!-- Languages dropdown    -->
                <li class="nav-item dropdown"><a id="languages" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link language dropdown-toggle"><img src="img/GB.png" alt="English"><span class="d-none d-sm-inline-block">English</span></a>
                  <ul aria-labelledby="languages" class="dropdown-menu">
                    <li><a rel="nofollow"  class="dropdown-item"> <img src="img/CN.png" alt="Chinese" class="mr-2">Chinese</a></li>
                  </ul>
                </li>
                <!-- Logout    -->
                <li class="nav-item"><a href="login.jsp" class="nav-link logout" onclick="logout()"> <span class="d-none d-sm-inline">Logout</span><i class="fa fa-sign-out"></i></a></li>
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
            <div class="avatar"><img src="${nowuser.photo}"  class="img-fluid rounded-circle"></div>
            <div class="title">
              <h1 class="h4">${nowuser.nameStudent}</h1>
              <p>major in ${nowuser.major} </p>
              <p class="nowid" style="display:none">${nowuser.idStudent}</p>
              <p class="nowterm" >19-20 1</p>
            </div>
          </div>
          <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
          <ul class="list-unstyled">
              <li><a href="index.jsp"> <i class="icon-home"></i>Home </a></li>
              <li class="active" ><a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-grid"></i>Timetable & Grades </a>
                <ul id="exampledropdownDropdown" class="collapse.in list-unstyled ">
                  <li class="active"><a href="course.jsp">Course Schedule</a></li>
                  <li><a href="test.jsp">Test Schedule</a></li>
                  <li><a href="grade.jsp">Grade</a></li>
                </ul>
              </li>
              <li><a href="charts.jsp"> <i class="fa fa-bar-chart"></i>Analyze</a></li>
          </ul><span class="heading">Extras</span>
          <ul class="list-unstyled">
            <li> <a href="GetNoticeByStu"> <i class="fa fa-bullhorn"></i>Notice </a></li>
            <li> <a > <i class="fa fa-comments"></i>Contact </a></li>
            <li> <a > <i class="fa fa-hashtag"></i>More </a></li>
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
              <li class="breadcrumb-item active">Course Schedule            </li>
              <li class="breadcrumb-item dropdown term" ><a href="#" data-toggle="dropdown" class="dropdown-collapse">2019-2020</a>
                  <ul class="dropdown-menu list-unstyled has-shadow">
                    <li><a > 2018-2019 </a></li>
                    <li><a > 2017-2018 </a></li>
                </ul>
              </li>
            </ul>
          </div>
          <section class="tables">   
            <div class="container-fluid">
              <div class="row">
                <div class="col-lg-12">
                  <div class="card">
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard1" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">2019-2020 First term</h3>
                    </div>
                    <div class="card-body">
                      <div class="table-responsive">
                        <table class="table"  align="center">
                          <thead>
                            <tr>
                              <th >#</th>
                              <th class="include">MON</th>
                              <th class="include">TUES</th>
                              <th class="include">WED</th>
                              <th class="include">THU</th>
                              <th class="include">FRI</th>
                              <th class="include">SAT</th>
                              <th class="include">SUN</th>
                            </tr>
                          </thead>
                         
                         
                         
                          <tbody>
                            <tr id="8:00"  align="center">
                              <th scope="row">8:00-8:45</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="8:55" align="center">
                              <th scope="row">8:55-9:40</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="10:05" align="center">
                              <th scope="row">10:05-10:50</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="11:00" align="center">
                              <th scope="row" >11:00-11:45</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="11:55" align="center">
                              <th scope="row" >11:55-12:40</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="13:20" align="center">
                              <th scope="row" class="afternoon">13:20-14:05</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="14:15" align="center">
                              <th scope="row" class="afternoon">14:15-15:00</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="15:25" align="center">
                              <th scope="row" class="afternoon">15:25-16:10</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="16:20" align="center">
                              <th scope="row" class="afternoon">16:20-17:05</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="17:15" align="center">
                              <th scope="row" class="afternoon">17:15-18:00</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="18:00" align="center">
                              <th scope="row" class="evening">18:00-18:45</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="18:55" align="center">
                              <th scope="row" class="evening">18:55-19:40</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="19:50" align="center">
                              <th scope="row" class="evening">19:50-20:35</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
                            </tr>
                            <tr id="20:45" align="center">
                              <th scope="row" class="evening">20:45-21:30</th>
                              <td class="Monday"></td>
                              <td class="Tuesday"></td>
                              <td class="Wednesday"></td>
                              <td class="Thursday"></td>
                              <td class="Friday"></td>
                              <td class="Saturday"></td>
                              <td class="Sunday"></td>
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
                        <button type="button" id="closeCard2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard2" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">2019-2020 Second term</h3>
                    </div>
                    <div class="card-body">
                      <div class="table-responsive">  
                        <table class="table table-striped">
                          <thead>
                            <tr>
                              <th scope="row">List</th>
                              <th>Time</th>
                              <th>Course</th>
                              <th>Place</th>
                              <th>Teacher</th>
                              <th>credit</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <th scope="row">1</th>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                            </tr>
                            <tr>
                              <th scope="row">2</th>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                            </tr>
                            <tr>
                              <th scope="row">3</th>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
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
                        <button type="button" id="closeCard3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard3" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">Summer Course</h3>
                    </div>
                    <div class="card-body">
                      <div class="table-responsive">                       
                        <table class="table table-striped table-hover">
                          <thead>
                            <tr>
                              <th scope="row">List</th>
                              <th>Time</th>
                              <th>Course</th>
                              <th>Place</th>
                              <th>Teacher</th>
                              <th>credit</th>
                            </tr>
                          </thead>
                          <tbody>
                            <tr>
                              <th scope="row">1</th>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                            </tr>
                            <tr>
                              <th scope="row">2</th>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                              <td></td>
                            </tr>
                            <tr>
                              <th scope="row">3</th>
                              <td></td>
                              <td></td>         
                              <td></td>   
                              <td></td>
                              <td></td>        
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

    <script src="vendor/popper.js/umd/popper.min.js"> </script>
    <script src="js/jquery.session.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/jquery-validation/jquery.validate.min.js"></script>
    <!-- Main File-->
    <script src="js/front.js"></script>
    <script>
    function weektrans(week){
    	switch(week){
    	case "Monday":
    		return 0;
    		break;
    	case "Tuesday":
    		return 1;
    		break;
    	case "Wednesday":
    		return 2;
    		break;
    	case "Thursday":
    		return 3;
    		break;
    	case "Friday":
    		return 4;
    		break;
    	case "Saturday":
    		return 5;
    		break;
    	case "Sunday":
    		return 6;
    		break;
    	}
    }
    
    function updateData(){	
   $.ajax({
		url: "GetCourses",
		type: "post",
		async:false,
		data:{
			id:$(".nowid").text(),
		    term:$(".nowterm").text()
		},
		success: function(data){
			console.log(data);
		    for(var i = 0;i<eval(data).length;i++){
		    	var element = document.getElementById(data[i].startTime);
		    	var count = weektrans(data[i].date);
		  		var currelement = element.getElementsByTagName("td")[count];
		  		currelement.setAttribute("rowspan",data[i].sumClass);
		  		currelement.innerHTML=data[i].nameCourse+"<br />("+data[i].nameTeacher+",<br />"+data[i].credit+",<br />"+data[i].classroom+")";
		  		currelement.setAttribute("class","include bg-orange");
                var nextelement = element.nextElementSibling;
                for( var j = 1;j<parseInt(i.sumClass);j++)
        		  {
        			  var child = nextelement.getElementsByTagName("td")[count];                 			  
        		      nextelement.removeChild(child);
        		      nextelement = nextelement.nextSibling;
        		  }
		    }
		}, error: function(data){
			
		}
	});
       
    }
   updateData();
    </script>
  </body>
</html>