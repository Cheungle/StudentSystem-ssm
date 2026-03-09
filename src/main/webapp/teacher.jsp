<%@ page language="java" contentType="text/html; charset=GB2312"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="beans.student"%>
 <%@ page import="java.util.List" %>
<%@ page import="dao.StudentDao"%>
<%@ page import="dao.StudentDao"%>
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
                <!-- Navbar Brand --><a href="adminindex.html" class="navbar-brand d-none d-sm-inline-block">
                  <div class="brand-text d-none d-lg-inline-block"><span>Student System</span></div>
                  </a>
                <!-- Toggle Button--><a id="toggle-btn" href="#" class="menu-btn active"><span></span><span></span><span></span></a>
              </div>
              <!-- Navbar Menu -->
              <ul class="nav-menu list-unstyled d-flex flex-md-row align-items-md-center">
                <!-- Search-->
                <li class="nav-item d-flex align-items-center"><a id="search" href="#"><i class="icon-search"></i></a></li>
                <!-- Notifications-->
                <li class="nav-item dropdown"> <a id="notifications" rel="nofollow" data-target="#" href="#" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="nav-link"><i class="fa fa-bell-o"></i><span class="badge bg-red badge-corner">1</span></a>
                    <ul aria-labelledby="notifications" class="dropdown-menu">
                      <li><a rel="nofollow"  class="dropdown-item"> 
                          <div class="notification">
                            <div class="notification-content"><i class="fa fa-bullhorn bg-green"></i>Someone submitted a application. </div>
                            <div class="notification-time"><small>4 minutes ago</small></div>
                          </div></a></li>
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
                  <div class="avatar"><img src="img/avatar-1.jpg"  class="img-fluid rounded-circle"></div>
                  <div class="title">
                    <h1 class="h4">Admin</h1>
                    <p>take charge of all projects</p>
                  </div>
                </div>
                <!-- Sidebar Navidation Menus--><span class="heading">Main</span>
                <ul class="list-unstyled">
                          <li><a href="adminindex.jsp"> <i class="icon-home"></i>Home </a></li>
                          <li ><a href="student.jsp"> <i class="fa fa-address-card-o"></i>Student Information </a></li>
                          <li class="active"><a href="teacher.jsp"> <i class="fa fa-id-card-o"></i>Teacher Information</a></li>
                </ul><span class="heading">Extras</span>
          <ul class="list-unstyled">
            <li> <a href="GetNotices"> <i class="fa fa-bullhorn"></i>Notice </a></li>
            <li> <a > <i class="fa fa-comments"></i>Contact </a></li>
            <li> <a > <i class="fa fa-hashtag"></i>More </a></li>
          </ul>
        </nav>
        <div class="content-inner">
          <!-- Page Header-->
          <header class="page-header">
            <div class="container-fluid">
              <h2 class="no-margin-bottom">Teacher Information</h2>
            </div>
          </header>
          <!-- Breadcrumb-->
          <div class="breadcrumb-holder container-fluid">
            <ul class="breadcrumb">
              <li class="breadcrumb-item active">Teacher Information</li>
              <li class="breadcrumb-item dropdown term" ><a href="#" data-toggle="dropdown" class="dropdown-collapse">Academy of <span id="academyname">information engineering</span></a>
                  <ul class="dropdown-menu list-unstyled has-shadow">
                    <li onclick="changeacademy(this)"><a > Academy of <span class="academy 1">humanities</span></a></li>
                    <li onclick="changeacademy(this)"><a > Academy of <span class="academy 2">law</span></a></li>
                </ul>
              </li>
            </ul>
          </div>
          <section class="tables">   
            <div class="container-fluid">
              <div class="row"  id="crush">
                      <div class="col-lg-2"></div>
                      <div class="col-lg-8">
                            <div class="card">
                              <div class="card-close">
                                <div class="dropdown">
                                  <button type="button" id="closeCard2" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="dropdown-toggle"><i class="fa fa-ellipsis-v"></i></button>
                                  <div aria-labelledby="closeCard2" class="dropdown-menu dropdown-menu-right has-shadow"><a href="#" class="dropdown-item remove"> <i class="fa fa-times"></i>Close</a><a href="#" class="dropdown-item edit"  data-toggle="modal" data-target="#edit"> <i class="fa fa-gear"></i>Edit</a><a href="#" class="dropdown-item edit"  data-toggle="modal" data-target="#add"> <i class="fa fa-plus"></i>Add</a><a href="#" class="dropdown-item edit"  data-toggle="modal" data-target="#delete"> <i class="fa fa-minus"></i>Delete</a></div>
                                </div>
                              </div>
                              <div class="card-header d-flex align-items-center">
                                <h3 class="h4"></h3>
                              </div>
                              <div class="card-body">
                                <div class="table-responsive">  
                                  <table class="table table-striped">
                                    <thead>
                                      <tr>
                                        <th scope="row">id</th>
                                        <th class="include">Name</th>
                                        <th class="include">Office</th>
                                        <th class="include">Academy</th>
                                      </tr>
                                    </thead>
                                    <tbody class="crusha">       
                           
                                    </tbody>
                             <script type="text/html" id="tbody-script">
				           {{ each data value i }}
					    	<tr>
		                     <td class="include"> {{ value.idTeacher }} </td>
		                     <td class="include">{{ value.nameTeacher }}</td>
		                     <td class="include">{{ value.office }}</td>
		                     <td class="include">{{ value.academy }}</td>
		                    
		                    </tr>
				             {{ /each }}
			                    </script>
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
    <div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="opacity: 1;">
    <div class="modal-dialog" style="top: 100px;width: 1000px">
        <div class="modal-content">
            <div class="modal-header">
                
                <h4 class="modal-title">
                    edit teacher information
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body row">
                <br>
                <br>
                <div class="form-group container col-sm-12 col-md-12">
                    <label class="control-label">id</label>
                    <!-- 样式1 -->
                    <input type="text" class="form-control" style="width: auto; display: inline;" id="edit-id">
                </div>
                <div class="form-group container col-sm-12 col-md-12">
                    <label class="control-label">name</label>
                    <!-- 样式1 -->
                    <input type="text" class="form-control" style="width: auto; display: inline;" id="edit-name">
                </div>
                <div class="form-group container col-sm-12 col-md-12">
                    <label class="control-label">office</label>
                    <!-- 样式1 -->
                    <input type="text" class="form-control" style="width: auto; display: inline;" id="edit-class">
                </div>
                <div class="form-group container col-sm-12 col-md-12">
                    <label class="control-label">academy</label>
                    <!-- 样式1 -->
                    <input type="text" class="form-control" style="width: auto; display: inline;" id="edit-academy">
                </div>

            </div>
            <div class="modal-footer">
                <div>
                    <button type="button" class="btn btn-danger navbar-btn" data-dismiss="modal">close
                    </button>
                    <button type="button" class="btn btn-default navbar-btn" id="edit-submit" style="margin: 8px" data-dismiss="modal">
                        confirm
                    </button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
    </div>
    <div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="opacity: 1;">
    <div class="modal-dialog" style="top: 100px;width: 1000px">
        <div class="modal-content">
            <div class="modal-header">
                
                <h4 class="modal-title">
                    add teacher information
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body row">
                <br>
                <br>
                <div class="form-group container col-sm-12 col-md-12">
                    <label class="control-label">id</label>
                    <!-- 样式1 -->
                    <input type="text" class="form-control" style="width: auto; display: inline;" id="add-id">
                </div>
                <div class="form-group container col-sm-12 col-md-12">
                    <label class="control-label">name</label>
                    <!-- 样式1 -->
                    <input type="text" class="form-control" style="width: auto; display: inline;" id="add-name">
                </div>
                <div class="form-group container col-sm-12 col-md-12">
                    <label class="control-label">office</label>
                    <!-- 样式1 -->
                    <input type="text" class="form-control" style="width: auto; display: inline;" id="add-class">
                </div>
                <div class="form-group container col-sm-12 col-md-12">
                    <label class="control-label">academy</label>
                    <!-- 样式1 -->
                    <input type="text" class="form-control" style="width: auto; display: inline;" id="add-academy">
                </div>
                
            </div>
            <div class="modal-footer">
                <div>
                    <button type="button" class="btn btn-danger navbar-btn" data-dismiss="modal">close
                    </button>
                    <button type="button" class="btn btn-default navbar-btn" id="add-submit" style="margin: 8px" data-dismiss="modal">
                        confirm
                    </button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>
<div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="opacity: 1;">
    <div class="modal-dialog" style="top: 100px;width: 1000px">
        <div class="modal-content">
            <div class="modal-header">
                
                <h4 class="modal-title">
                    delete teacher information
                </h4>
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    &times;
                </button>
            </div>
            <div class="modal-body row">
                <br>
                <br>
                <div class="form-group container col-sm-12 col-md-12">
                    <label class="control-label">id</label>
                    <!-- 样式1 -->
                    <input type="text" class="form-control" style="width: auto; display: inline;" id="delete-id">
                </div>        
            </div>
            <div class="modal-footer">
                <div>
                    <button type="button" class="btn btn-danger navbar-btn" data-dismiss="modal">close
                    </button>
                    <button type="button" class="btn btn-default navbar-btn" id="delete-submit" style="margin: 8px" data-dismiss="modal">
                        confirm
                    </button>
                </div>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
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
    <script src="js/template.js"></script>
    <script>
     $(function () { $('#edit').modal('hide')});
     $(function () { $('#add').modal('hide')});
     $(function () { $('#delete').modal('hide')});
     /* $('#delete').on('hidden.bs.modal', function (){
     	document.getElementsByClassName("form-control").value="";
     }); */
    /*  function changeacademy(obj){
     	var old = $("#academyname").innerHTML;
     	var now=$(obj).children("a").children("span");
     	alert(typeof(now));
     	alert(now.text);
     	 $("#academyname").innerHTML=now.innerHTML;
     	$(obj).children("a").children("span").innerHTML=old;
      }; */
     function updateData(){	
    	 $(".crusha").empty();
			$.ajax({
				url: "TTable",
				type: "post",
				async:false,
				 data:{
	                	academy:$("#academyname").text()
	                },
				success: function(data){
					console.log(data);
					$(".crusha").append(template("tbody-script",{data:data}));
				}, error: function(data){
					
				}
			});
			
	        $("#add-submit").click(function () {
	            var id = $("#add-id").val();
	            var name = $("#add-name").val();
	            var office = $("#add-class").val();
	            var academy = $("#add-academy").val();

	            $.ajax({
	                url:"AddT",
	                type: "post",
	                data: {
	                    id:id,
	                    name:name,
	                    office:office,
	                    academy:academy,
	                },
	                success: function (data) {
	                    console.log(data);
	                    $(".form-control").val("");
	                    updateData();
	                }, error: function (data) {
	                    console.log(data);
	                }
	            });
	         });
	        $("#edit-submit").click(function () {
	            $.ajax({
	                url: "UpdateT",
	                type: "post",
	                data:{
	                	id : $("#edit-id").val(),
	    	            name : $("#edit-name").val(),
	    	            office : $("#edit-class").val(),
	    	            academy : $("#edit-academy").val(),
	                },
	                success: function (data) {
	                    console.log(data);
	                    $(".form-control").val("");
	                    updateData();
	                }, error: function (data) {
	                    console.log(data);
	                }
	            });
	        });
	        $("#delete").click(function () {
	        	 
	            $.ajax({
	                url: "DeleteT",
	                type: "post",
	                data:{
	                    id:$("#delete-id").val()
	                },
	                success: function (data) {
	                  console.log(data);
	                  $(".form-control").val("");
	                  updateData();
	                    
	                }, error: function (data) {
	                    console.log(data);
	                }
	            });
	        });
		}
		
		updateData();
     </script>
  </body>
</html>