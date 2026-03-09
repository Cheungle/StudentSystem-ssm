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
              <li><a href="#exampledropdownDropdown" aria-expanded="false" data-toggle="collapse"> <i class="icon-grid"></i>Timetable & Grades </a>
                <ul id="exampledropdownDropdown" class="collapse list-unstyled ">
                  <li><a href="course.jsp">Course Schedule</a></li>
                  <li><a href="test.jsp">Test Schedule</a></li>
                  <li><a href="grade.jsp">Grade</a></li>
                </ul>
              </li>
              <li class="active"><a href="charts.jsp"> <i class="fa fa-bar-chart"></i>Analyze</a></li>
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
              <h2 class="no-margin-bottom">Analyze</h2>
            </div>
          </header>
          <!-- Charts Section-->
          <section class="charts">
            <div class="container-fluid">
              <div class="row">
                <!-- Bar Charts-->
                <div class="col-lg-8">
                    <div class="bar-chart-example card">
                      <div class="card-close">
                        <div class="dropdown">
                          <button type="button" id="closeCard6" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                          <div aria-labelledby="closeCard6" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                        </div>
                      </div>
                      <div class="card-header d-flex align-items-center">
                        <h3 class="h4">Compare Average GPA with Credit </h3>
                      </div>
                      <div class="card-body">
                        <canvas id="barChartExample"></canvas>
                      </div>
                    </div>
                  </div>
                <!-- Line Charts-->  
                <div class="col-lg-4">
                  <div class="line-chart-example card no-margin-bottom">
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard2" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">Average GPA and Credits <br/>in all kinds of terms</h3>
                    </div>
                    <div class="card-body">
                      <canvas id="lineChartExample1"></canvas>
                    </div>
                  </div>
                  <div class="line-chart-example card">
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard3" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard3" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                      </div>
                    </div>
                    <div class="card-body">
                      <canvas id="lineChartExample2"></canvas>
                    </div>
                  </div>
                </div>
             
                <!-- Pie Chart -->
                <div class="col-lg-6">
                  <div class="pie-chart-example card">
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard8" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard8" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">Credits in all kinds of courses</h3>
                    </div>
                    <div class="card-body">
                      <canvas id="pieChartExample"></canvas>
                    </div>
                  </div>
                </div>
                <!-- Radar Chart-->
                <div class="col-lg-6">
                  <div class="radar-chart-example card">
                    <div class="card-close">
                      <div class="dropdown">
                        <button type="button" id="closeCard10" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                        <div aria-labelledby="closeCard10" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                      </div>
                    </div>
                    <div class="card-header d-flex align-items-center">
                      <h3 class="h4">Average grade in all kinds of courses</h3>
                    </div>
                    <div class="card-body">
                      <canvas id="radarChartExample"></canvas>
                    </div>
                  </div>
                </div>
                <div class="data" style="display:none"></div>
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
    <!-- Main File-->
    <script src="js/front.js"></script>
    <script>
    $(document).ready(function () {
    	'use strict';
    	$.ajax({
			url: "Charts",
			type: "post",
			async:false,
			 data:{
                 id:$(".nowid").text(),
                 
                },
			success: function(data){
				 console.log(data);
				    // ------------------------------------------------------- //
				    // Charts Gradients
				    // ------------------------------------------------------ //
				    var ctx1 = $("canvas").get(0).getContext("2d");
				    var gradient1 = ctx1.createLinearGradient(150, 0, 150, 10);
				    gradient1.addColorStop(0, 'rgba(133, 180, 242, 0.91)');
				    gradient1.addColorStop(1, 'rgba(255, 119, 119, 0.94)');

				    var gradient2 = ctx1.createLinearGradient(146, 0, 154, 300);
				    gradient2.addColorStop(0, 'rgba(104, 179, 112, 0.85)');
				    gradient2.addColorStop(1, 'rgba(76, 162, 205, 0.85)');

				    // ------------------------------------------------------- //
				    // Line Chart 1
				    // ------------------------------------------------------ //
				    var LINECHART1 = $('#lineChartExample1');
				    var myLineChart = new Chart(LINECHART1, {
				        type: 'line',
				        options: {
				            scales: {
				                xAxes: [{
				                    display: true,
				                    gridLines: {
				                        display: false
				                    }
				                }],
				                yAxes: [{
				                    ticks: {
				                        max: 4.3,
				                        min: 0,
				                        stepSize: 0.5
				                    },
				                    display: false,
				                    gridLines: {
				                        display: false
				                    }
				                }]
				            },
				            legend: {
				                display: false
				            }
				        },
				        data: {
				            labels: ["1-1", "1-2", "2-1", "2-2", "3-1", "3-2", "4-1","4-2"],
				            datasets: [
				                {
				                    label: "Total Overdue",
				                    fill: true,
				                    lineTension: 0,
				                    backgroundColor: "transparent",
				                    borderColor: '#ff7676 ',
				                    pointBorderColor: '#ff7676',
				                    pointHoverBackgroundColor: '#ff7676',
				                    borderCapStyle: 'butt',
				                    borderDash: [],
				                    borderDashOffset: 0.0,
				                    borderJoinStyle: 'miter',
				                    borderWidth: 3,
				                    pointBackgroundColor: "#ff7676",
				                    pointBorderWidth: 0,
				                    pointHoverRadius: 4,
				                    pointHoverBorderColor: "#fff",
				                    pointHoverBorderWidth: 0,
				                    pointRadius: 4,
				                    pointHitRadius: 0,
				                    data:  [data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7]],
				                    spanGaps: false
				                    //aveGPA
				                }
				            ]
				        }
				    });


				    // ------------------------------------------------------- //
				    // Line Chart 2
				    // ------------------------------------------------------ //
				    var LINECHART1 = $('#lineChartExample2');
				    var myLineChart = new Chart(LINECHART1, {
				        type: 'line',
				        options: {
				            scales: {
				                xAxes: [{
				                    display: true,
				                    gridLines: {
				                        display: false,
				                        color: '#eee'
				                    }
				                }],
				                yAxes: [{
				                    ticks: {
				                        max: 30,
				                        min: 0,
				                        stepSize: 0.5
				                    },
				                    display: false,
				                    gridLines: {
				                        display: false
				                    }
				                }]
				            },
				            legend: {
				                display: false
				            }
				        },
				        data: {
				            labels:["1-1", "1-2", "2-1", "2-2", "3-1", "3-2", "4-1","4-2"],
				            datasets: [
				                {
				                    label: "Total Overdue",
				                    fill: true,
				                    lineTension: 0,
				                    backgroundColor: "transparent",
				                    borderColor: '#6ccef0',
				                    pointBorderColor: '',
				                    pointHoverBackgroundColor: '#59c2e6',
				                    borderCapStyle: 'butt',
				                    borderDash: [],
				                    borderDashOffset: 0.0,
				                    borderJoinStyle: 'miter',
				                    borderWidth: 3,
				                    pointBackgroundColor: "#59c2e6",
				                    pointBorderWidth: 0,
				                    pointHoverRadius: 4,
				                    pointHoverBorderColor: "#fff",
				                    pointHoverBorderWidth: 0,
				                    pointRadius: 4,
				                    pointHitRadius: 0,
				                    data: [data[8],data[9],data[10],data[11],data[12],data[13],data[14],data[15]],
				                    spanGaps: false
				                }//allcre
				            ]
				        }
				    });
				 // ------------------------------------------------------- //
				    // Bar Chart
				    // ------------------------------------------------------ //
				    var BARCHARTEXMPLE    = $('#barChartExample');
				    var barChartExample = new Chart(BARCHARTEXMPLE, {
				        type: 'bar',
				        options: {
				            scales: {
				                xAxes: [{
				                    display: true,
				                    gridLines: {
				                        color: '#eee'
				                    }
				                }],
				                yAxes:[{
				                    display: true,
				                    gridLines: {
				                        color: '#eee'}
				                }],
				            },
				            legend:{
				                backgroundColor:gradient1,
				            }
				        },
				        data: {
				            labels: ["1-1", "1-2", "2-1", "2-2", "3-1", "3-2", "4-1","4-2"],
				            datasets: [
				                {
				                    label: "Average GPA",
				                    backgroundColor: [
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1
				                    ],
				                    hoverBackgroundColor: [
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1
				                    ],
				                    borderColor: [
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1,
				                        gradient1
				                    ],
				                    borderWidth: 1,
				                    data:  [data[0],data[1],data[2],data[3],data[4],data[5],data[6],data[7]]
				                },//aveGPA
				                {
				                    label: "Credit",
				                    backgroundColor: [
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2
				                    ],
				                    hoverBackgroundColor: [
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2
				                    ],
				                    borderColor: [
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2,
				                        gradient2
				                    ],
				                    borderWidth: 1,
				                    data: [data[8],data[9],data[10],data[11],data[12],data[13],data[14],data[15]]
				                }//allcre
				            ]
				        }
				    });
			}
       }) ;
    	$.ajax({
			url: "KindChart",
			type: "post",
			async:false,
			 data:{
                 id:$(".nowid").text(),
                 kind1:"general elective",
                 kind2:"general required",
                 kind3:"professional",
                 kind4:"personalized",
                 kind5:"subject common"
                },
			success: function(data){
				 console.log(data);

				    // ------------------------------------------------------- //
				    // Pie Chart
				    // ------------------------------------------------------ //
				    var PIECHARTEXMPLE    = $('#pieChartExample');
				    var pieChartExample = new Chart(PIECHARTEXMPLE, {
				        type: 'pie',
				        data: {
				            labels: [
				                "general elective",
				                "general required",
				                "professional",
				                "personalized",
				                "subject common"
				            ],
				            datasets: [
				                {//crekind
				                    data:[data[10],data[11],data[12],data[13],data[14]],
				                    borderWidth: 0,
				                    backgroundColor: [
				                        '#2c94b9',
				                        '#44b2d7',
				                        "#59c2e6",
				                        "#71d1f2",
				                        "#96e5ff"

				                    ],
				                    hoverBackgroundColor: [
				                        '#2c94b9',
				                        '#44b2d7',
				                        "#59c2e6",
				                        "#71d1f2",
				                        "#96e5ff"
				                    ]
				                }]

				            },
				        options:{
				            legend:{
				                display:true,
				                position:'left',
				            }
				        }
				    });

				    var pieChartExample = {
				        responsive: true
				    };
				    // ------------------------------------------------------- //
				    // Radar Chart
				    // ------------------------------------------------------ //
				    var RADARCHARTEXMPLE  = $('#radarChartExample');
				    var radarChartExample = new Chart(RADARCHARTEXMPLE, {
				        type: 'radar',
				        data: {
				            labels: ["general elective", "general required", "professional", "personalized", "subject common"],
				            datasets: [
				                {
				                    label: "2017-2018",
				                    backgroundColor: "rgba(84, 230, 157, 0.4)",
				                    borderWidth: 2,
				                    borderColor: "rgba(75, 204, 140, 1)",
				                    pointBackgroundColor: "rgba(75, 204, 140, 1)",
				                    pointBorderColor: "#fff",
				                    pointHoverBackgroundColor: "#fff",
				                    pointHoverBorderColor: "rgba(75, 204, 140, 1)",
				                    data: [data[0],data[1],data[2],data[3],data[4]]
				                },
				                {
				                    label: "2018-2019",
				                    backgroundColor: "rgba(255, 119, 119, 0.4)",
				                    borderWidth: 2,
				                    borderColor: "rgba(255, 119, 119, 1)",
				                    pointBackgroundColor: "rgba(255, 119, 119, 1)",
				                    pointBorderColor: "#fff",
				                    pointHoverBackgroundColor: "#fff",
				                    pointHoverBorderColor: "rgba(255, 119, 119, 1)",
				                    data: [data[5],data[6],data[7],data[8],data[9]]
				                }//kindyear
				            ]
				        }
				    });
				    var radarChartExample = {
				        responsive: true
				    };
			}
       }) ;
     






    

  



});

    </script>
  </body>
</html>