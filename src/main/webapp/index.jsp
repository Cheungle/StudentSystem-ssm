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
<html lang="en">
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
                <!-- Navbar Brand --><a href="#" class="navbar-brand d-none d-sm-inline-block">
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
                  <ul aria-labelledby="notifications" href="#"class="dropdown-menu">
                    <li><a rel="nofollow" class="dropdown-item d-flex"> 
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
                <li class="nav-item dropdown"><a id="languages" rel="nofollow" data-target="#"  href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link language dropdown-toggle"><img src="img/GB.png" alt="English"><span class="d-none d-sm-inline-block">English</span></a>
                  <ul aria-labelledby="languages" class="dropdown-menu">
                    <li><a rel="nofollow"  class="dropdown-item"> <img src="img/CN.png" alt="Chinese" class="mr-2">Chinese</a></li>
                  </ul>
                </li>
                <!-- Logout    -->
                <li class="nav-item"><a href="login.jsp" class="nav-link logout" > <span class="d-none d-sm-inline">Logout</span><i class="fa fa-sign-out"></i></a></li>
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
              <h1 class="h4">${nowuser.nameStudent} </h1>
              <p>major in ${nowuser.major} </p>
              <p class="nowid" style="display:none">${nowuser.idStudent}</p>
              <p class="nowterm" >19-20 1</p>
            </div>
          </div>
          <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
          <ul class="list-unstyled">
                    <li class="active"><a href="index.jsp"> <i class="icon-home"></i>Home </a></li>
                    <li><a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-grid"></i>Timetable & Grades </a>
                      <ul id="exampledropdownDropdown" class="collapse list-unstyled ">
                        <li><a href="course.jsp">Course Schedule</a></li>
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
              <h2 class="no-margin-bottom">Home</h2>
            </div>
          </header>
          <!-- Dashboard Counts Section-->
          <section class="dashboard-counts no-padding-bottom">
            <div class="container-fluid">
              <div class="row bg-white has-shadow">
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center">
                    <div class="icon bg-violet"><i class="icon-padnote"></i></div>
                    <div class="title"><span>New<br>courses</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 15%; height: 4px;" aria-valuenow="15" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-violet"></div>
                      </div>
                    </div>
                    
                    <div class="number"><strong class="onecourse"></strong></div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center">
                    <div class="icon bg-red"><i class="icon-bill "></i></div>
                    <div class="title"><span>New<br>credits</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 13%; height: 4px;" aria-valuenow="13" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-red"></div>
                      </div>
                    </div>
                 
                    <div class="number"><strong class="allcreditsofterm"></strong></div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center">
                    <div class="icon bg-green"><i class="fa fa-graduation-cap"></i></div>
                    <div class="title"><span>Credits<br>gained</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 77%; height: 4px;" aria-valuenow="77" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-green"></div>
                      </div>
                    </div>
                    <div class="number"><strong class="beforecredits"></strong></div>
                  </div>
                </div>
                <!-- Item -->
                <div class="col-xl-3 col-sm-6">
                  <div class="item d-flex align-items-center">
                    <div class="icon bg-orange"><i class="icon-list-1"></i></div>
                    <div class="title"><span>Second<br>Credits</span>
                      <div class="progress">
                        <div role="progressbar" style="width: 60%; height: 4px;" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-orange"></div>
                      </div>
                    </div>
                    <div class="number"><strong>14</strong></div>
                  </div>
                </div>
              </div>
            </div>
          </section>

          <section class="projects">
            <div class="container-fluid" id="projects">
            
              <!-- <div class="project">
                <div class="row bg-white has-shadow">
                  <div class="left-col col-lg-6 d-flex align-items-center justify-content-between">
                    <div class="project-title d-flex align-items-center">
                      <div class="text">
                        <h3 class="h4">Course Java</h3><small> At cloud computer center 520</small>
                      </div>
                    </div>
                    <div class="project-date"><span class="hidden-sm-down">Today at 1:20 PM</span></div>
                  </div>
                  <div class="right-col col-lg-6 d-flex align-items-center">
                    <div class="time"><i class="fa fa-clock-o"></i>3:00 PM </div>
                    <div class="time">Now</div>
                    <div class="project-progress">
                      <div class="progress">
                        <div role="progressbar" style="width: 80%; height: 6px;" aria-valuenow="80" aria-valuemin="0" aria-valuemax="100" class="progress-bar bg-orange"></div>
                      </div>
                    </div>
                  </div>
                </div>
              </div> -->
            </div>
          </section>

          <!-- Feeds Section-->
          <section class="feeds no-padding-top">
            <div class="container-fluid">
              <div class="row">
                <!-- Trending Articles-->
                <div class="col-lg-6">
                    <div class="articles card">
                      <div class="card-close">
                        <div class="dropdown">
                          <button type="button" id="closeCard4" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                          <div aria-labelledby="closeCard4" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                        </div>
                      </div>
                      <div class="card-header d-flex align-items-center">
                        <a href="#"><h2 class="h3">Notices   </h2></a>
                        <div class="badge badge-rounded bg-green">3 New       </div>
                      </div>
                      <div class="card-body no-padding">
                        <div class="item d-flex align-items-center">
                          <div class="text"><a href="#">
                              <h3 class="h5">You have a new grade published.</h3></a><small>Posted on 5th December 2019 by Teacher Chen.   </small></div>
                        </div>
                        <div class="item d-flex align-items-center">
                          <div class="text"><a href="#">
                              <h3 class="h5">You have a test date changed.</h3></a><small>Posted on 5th December 2019 by admin.   </small></div>
                        </div>
                        <div class="item d-flex align-items-center">
                          <div class="text"><a href="#">
                              <h3 class="h5">School notice.</h3></a><small>Posted on 5th December 2019 by admin.   </small></div>
                        </div>
                      </div>
                    </div>
                  </div>
                <!-- Check List -->
                <div class="col-lg-6">
                  <div class="checklist card">
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard5" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard5" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a><a href="#" class="dropdown-item edit"> <i class="fa fa-gear"></i>Edit</a></div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">           
                      <h2 class="h3">To Do List </h2>
                    </div>
                    <div class="card-body no-padding">
                      <div class="item d-flex">
                        <input type="checkbox" id="input-1" name="input-1" class="checkbox-template">
                        <label for="input-1">Reciting words and practicing in order to have CET-6.</label>
                      </div>
                      <div class="item d-flex">
                        <input type="checkbox" id="input-2" name="input-2" class="checkbox-template">
                        <label for="input-2">Be ready to internship.</label>
                      </div>
                      <div class="item d-flex">
                        <input type="checkbox" id="input-3" name="input-3" class="checkbox-template">
                        <label for="input-3">Be ready to take part in the postgraduate entrance exams.</label>
                      </div>
                      <div class="item d-flex">
                        <input type="checkbox" id="input-4" name="input-4" class="checkbox-template">
                        <label for="input-4">Be prepare to the final exams.</label>
                      </div>
                      <div class="item d-flex">
                        <input type="checkbox" id="input-5" name="input-5" class="checkbox-template">
                        <label for="input-5">Machine learing homework.</label>
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
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/jquery.session.js"></script>
    <script src="vendor/popper.js/umd/popper.min.js"> </script>
    <script src="vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="vendor/chart.js/Chart.min.js"></script>
    <script src="vendor/jquery-validation/jquery.validate.min.js"></script>
    <script src="js/charts-home.js"></script>
    <!-- Main File-->
    <script src="js/front.js"></script>
   <script type="text/javascript">
            function getWeekDate() {
                var now = new Date();
                var day = now.getDay();
                var weeks = new Array("Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday");
                var week = weeks[day];
                return week;
              }

              function updateData(){
    	     $.ajax({
				url: "CountCourseOfOne",
				type: "post",
				async:false,
				data:{
					id:$(".nowid").text(),
				    term:$(".nowterm").text()
				},
				success: function(data){
					console.log(data);					
				    $(".onecourse").html(data);
				}, error: function(data){
					
				}
			});
    	     $.ajax({
 				url: "AllCreditsOfOne",
 				type: "post",
 				async:false,
 				data:{
 					id:$(".nowid").text(),
 					 term:$(".nowterm").text()
 				},
 				success: function(data){
 					console.log(data);					
 				    $(".allcreditsofterm").html(data);
 				}, error: function(data){
 					
 				}
 			});
    	     $.ajax({
  				url: "BeforeCredits",
  				type: "post",
  				async:false,
  				data:{
  					id:$(".nowid").text(),
  					 term:$(".nowterm").text()
  				},
  				success: function(data){
  					console.log(data);					
  				    $(".beforecredits").html(data);
  				}, error: function(data){
  					
  				}
  			});
    	  
    	     var today = getWeekDate();
    	     $.ajax({
 				url: "ShowCourseDay",
 				type: "post",
 				async:false,
 				data:{
 					id:$(".nowid").text(),
 				    term:$(".nowterm").text(),
 				    today:today
 				},
 				success: function(data){
 					console.log(data);					
 				   for(var i =0;i<eval(data).length;i++) $("#projects").append("<div class=\"project\"><div class=\"row bg-white has-shadow\"><div class=\"left-col col-lg-6 d-flex align-items-center justify-content-between\"><div class=\"project-title d-flex align-items-center\"><div class=\"text\"><h3 class=\"h4\">"+data[i].nameCourse+"</h3><small>At "+data[i].classroom+"</small></div></div><div class=\"project-date\"><span class=\"hidden-sm-down\">Today at "+data[i].startTime+"</span></div></div><div class=\"right-col col-lg-6 d-flex align-items-center\"><div class=\"time\"><i class=\"fa fa-clock-o\"></i>"+data[i].sumClass+" </div><div class=\"time\">Now</div><div class=\"project-progress\"><div class=\"progress\"><div role=\"progressbar\" style=\"width: 75%; height: 6px;\" aria-valuenow=\"75\" aria-valuemin=\"0\" aria-valuemax=\"100\" class=\"progress-bar bg-red\"></div></div></div></div></div></div>");
 				}, error: function(data){
 					
 				}
 			});
              }
		updateData();
		</script>
  </body>
</html>