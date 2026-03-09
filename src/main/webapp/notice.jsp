<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="beans.student"%>
<%@ page import="dao.StudentDao"%>
<%@ page import="dao.ScDao"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
                    <li><a rel="nofollow" class="dropdown-item"> 
                        <div class="notification">
                          <div class="notification-content"><i class="fa fa-tag bg-orange"></i>You have 1 new item</div>
                          <div class="notification-time"><small>4 minutes ago</small></div>
                        </div></a></li>
                  </ul>
                </li>
                <!-- Messages                        -->
                <li class="nav-item dropdown"> <a id="messages" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-envelope-o"></i><span class="badge bg-orange badge-corner">2</span></a>
                  <ul aria-labelledby="notifications" class="dropdown-menu">
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
                    <li><a rel="nofollow" class="dropdown-item all-notifications text-center"> <strong>Read all messages   </strong></a></li>
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
      <div class="page-content d-flex align-items-stretch "> 
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
                    <li><a href="charts.jsp"> <i class="fa fa-bar-chart"></i>Analyze</a></li>
          </ul><span class="heading">Extras</span>
          <ul class="list-unstyled">
            <li class="active"> <a href="GetNoticeByStu"> <i class="fa fa-bullhorn"></i>Notice </a></li>
            <li> <a > <i class="fa fa-comments"></i>Contact </a></li>
            <li> <a > <i class="fa fa-hashtag"></i>More </a></li>
          </ul>
        </nav>
        <div class="content-inner">
          <!-- Page Header-->
          <header class="page-header">
                <div class="container-fluid">
                  <h2 class="no-margin-bottom">Notice</h2>
                </div>
          </header>          
          <!-- Breadcrumb-->
          <div class="breadcrumb-holder container-fluid noticeinfor" style="display:none">
                <ul class="breadcrumb">
                  <li class="breadcrumb-item"><a href="GetNoticeByStu">Notice</a></li>
                  <li class="breadcrumb-item active">Text            </li>
                    </ul>
                  </li>
                </ul>
              </div> 
              <!-- Updates Section                                                -->
              <section class="updates noticeintro">
                <div class="container-fluid">
                  <div class="row">
                    <!-- Recent Updates-->
                    <div class="col-lg-12">
                      <div class="recent-updates card">
                        <div class="card-close">
                          <div class="dropdown">
                            <button type="button" id="closeCard6" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                            <div aria-labelledby="closeCard6" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                          </div>
                        </div>
                        <div class="card-header">
                          <h3 class="h4">Publish Notices</h3>
                        </div>
                        <div class="card-body no-padding">
                          <!-- Item-->
                          <div class="noticeitem">
                          <div class="item d-flex justify-content-between">
                            <!-- <div class="info d-flex">
                            <a href="#">
                              <div class="title">
                                <h5>Notice of Shanghai Opera House modern dance drama,Early Spring February, entering the special performance of Shanghai University of Finance and Economics</h5>
                              </div>
                            </a>
                            </div>
                            <div class="date text-right"><strong>24</strong><span>December</span></div> -->
                          <table style="width:100%;">
                           <thead><tr><th style="width:70%">Title</th><th scope="row">Publisher</th><th scope="row">Publish Date</th></tr></thead>
                           <tbody class="nt-table">
                           <c:forEach items="${notices}" var="notice">
                             <tr onclick="information(this)" id="${notice.idNotice} ">
                             <td>${notice.title}</td>
                             <td class="include">${notice.pubperson}</td>
                             <td class="include">${notice.pubdate}</td>
                             </tr>
                           </c:forEach>
                           <tr><td>&nbsp;</td><td>&nbsp;</td><td>&nbsp;</td></tr>
                           <tr class="h6">   
                             <td colspan="3" align="right">
                             total ${totalCount } notice&nbsp;total ${totalPage} pages&nbsp;
                             ${pageCur} page
                             <c:url var="url_pre" value="GetNoticeByStu">
                               <c:param name="pageCur" value="${pageCur-1}" />
                             </c:url>
                             <c:url var="url_next" value="GetNoticeByStu">
                               <c:param name="pageCur" value="${pageCur+1}" />
                             </c:url>
                             <c:if test="${pageCur!=1}">
                                <a href="${url_pre}">previous page</a>
                             </c:if>
                              <c:if test="${pageCur!=totalPage && totalPage!=0}">
                                <a href="${url_next}">next page</a> 
                             </c:if>
                             </td>
                           </tr>
                           </tbody>
                           </table>
                          </div>
                          </div>
                          </div>
                           </div>
                          
                    <!-- Recent Activities -->
                    <div class="col-lg-12" style="padding-left:0;padding-right:0">
                      <div class="recent-activities card">
                        <div class="card-close">
                          <div class="dropdown">
                            <button type="button" id="closeCard8" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                            <div aria-labelledby="closeCard8" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                          </div>
                        </div>
                        <div class="card-header">
                          <h3 class="h4">Personal Notice</h3>
                        </div>
                        <div class="card-body no-padding">
                          <div class="item">
                            <div class="row">
                              <div class="col-4 date-holder text-right">
                                <div class="icon"><i class="icon-clock"></i></div>
                                <div class="date"> <span>6:00 am</span><br><span class="text-info">6 hours ago</span></div>
                              </div>
                              <div class="col-8 content">
                                <a href="grade.jsp">
                                <h5>You have a new grade published.</h5>
                                <p>Teacher Liu published your grade in Course c++</p></a>
                              </div>
                            </div>
                          </div>
                          <div class="item">
                            <div class="row">
                              <div class="col-4 date-holder text-right">
                                <div class="icon"><i class="icon-clock"></i></div>
                                <div class="date"> <span>5:00 pm</span><br><span class="text-info">10 hours ago</span></div>
                              </div>
                              <div class="col-8 content">
                                <a href="test.jsp">
                                <h5>You have a exam schedule published.</h5>
                                <p>The exam of course math is schedule on 15th,December.</p></a>
                              </div>
                            </div>
                          </div>
                        </div>
                      
                    </div>
                  </div>
               
              </section>
          <!-- Updates Section                                                -->
              <section class="updates noticeinfor" style="display:none">
                <div class="container-fluid">
                  <div class="row">
                    <!-- Recent Updates-->
                    <div class="col-lg-12">
                      <div class="recent-updates card">
                        <div class="card-close">
                          <div class="dropdown">
                            <button type="button" id="closeCard6" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                            <div aria-labelledby="closeCard6" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a></div>
                          </div>
                        </div>
                        <div class="card-header">
                          <h3 class="h4">  </h3>
                        </div>
                        <div class="card-body no-padding">
                          <!-- Item-->
                          <div class="item d-flex justify-content-between">
                            <div class="info d-flex">
                              <div class="title">
                               <p id="content">  </p>
                               <br />
                               <div class="date text-right" id="noticedate"><strong></strong><span></span></div>
                              </div>
                            </div>                  
                          </div>
                        </div>
                    </div>
                  </div>
                </div>
              </section>
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
     <script>
     function monthtran(num){
       	 switch(num) {
            case "01":
               return "January";
               break;
            case "02":
                return "February";
                break;
            case "03":
                return "March";
                break;
            case "04":
                return "April";
                break;
            case "05":
                return "May";
                break;
            case "06":
                return "June";
                break;
            case "07":
                return "July";
                break;
            case "08":
                return "August";
                break;
            case "09":
                return "September";
                break;
            case "10":
                return "October";
                break;
            case "11":
                return "November";
                break;
            case "12":
                return "December";
                break;
            default:
                return "";
       } 
        };
     function information(obj){
    	 var id = $(obj).attr("id");
     	 $.ajax({
               url: "SelectNotice",
               type: "post",
               data:{
                   id:id
               },
               success: function (data) {
                 console.log(data);
                 $(".noticeinfor").attr("style","display:flex!important");
             	 $(".noticeintro").attr("style","display:none!important");
             	 $(".card-header h3").html(data.title);
             	 $("p#content").html(data.content);
             	 $("#noticedate strong").html(data.pubdate.substring(8));
             	 $("#noticedate span").html(monthtran(data.pubdate.substring(5,7)));
             	 $("#noticedate").attr("style","padding-right:0;padding-bottom:0");
                // updateData();
                   
               }, error: function (data) {
                   console.log(data);
               }});
     	 
     };
     </script>
    
 
		
  </body>
</html>