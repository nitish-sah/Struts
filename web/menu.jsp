
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">

    <title>Employee Management</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <meta name="theme-color" content="#712cf9">

    <!-- Custom styles for this template -->
    <link href="css/header.css" rel="stylesheet">
    <!--<link href="css/carousel.css" rel="stylesheet">-->
    <style>
        header{
            width: 100%;
        }
        ul{
            font-size:25px;
        }
        li{
            padding:0px 8px;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.3.js" 
                        integrity="sha256-nQLuAZGRRcILA+6dMBOvcRh5Pe310sBpanc6+QBmyVM=" 
                crossorigin="anonymous"></script>
    <script>

        function valFunction() {
            var x = document.getElementById("countryId");

            document.getElementById("myForm").submit();
        }

    </script>

    <script type="text/javascript">
        function fetchContent(selected, target) {
            //alert("HI");
            $.ajax({
                url: 'navBarPage',
                data: {
                    ['work']: (selected)
                },
                success: function (responseText) {
                    $("#" + target).html(responseText);
                }
            });
        }

    </script>


    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.13.1/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.13.1/js/dataTables.bootstrap5.min.js"></script>
    <script>
        $(document).ready(function () {
            $('#example').DataTable();
        });
    </script>


    <header class="p-3 text-bg-dark">
        <div class="container">
            <div class="d-flex flex-wrap align-items-center justify-content-center justify-content-lg-start">
                <a href="/" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                    <img src="images/flower-logo.jpg" width="75" height="75"></img>
                </a>
                <c:set var="login_check" value="${LoggedIn}"/>
                <c:if test="${login_check!=null}">
                    <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                        
                        <li><a href="#" class="nav-link px-2 text-white" onclick="fetchContent('add', 'target')">Add</a></li>
                        <li><a href="#" class="nav-link px-2 text-white" onclick="fetchContent('search', 'target')">Search</a></li>
                        <li><a href="#" class="nav-link px-2 text-white" onclick="fetchContent('show', 'target')">Show</a></li>
                        <li><a href="ApiFetch"" class="nav-link px-2 text-white">Api Call</a></li>

                        <a class="nav-link px-2 text-bg-light">
                            WELCOME :
                            <c:set var="user" value="${USER}"/>
                            <c:out value="${user.getFirstName()} ${user.getLastName()}" />!!!!
                        </a>
                    </c:if>
                    <c:if test="${login_check==null}">
                        <ul class="nav col-12 col-lg-auto me-lg-auto mb-2 justify-content-center mb-md-0">
                           
                            <li><a href="login.jsp" class="nav-link px-2 text-white">ADD</a></li>
                            <li><a href="login.jsp" class="nav-link px-2 text-white">Search</a></li>
                            <li><a href="login.jsp"  class="nav-link px-2 text-white">Show</a></li>
                            <li><a href="login.jsp" class="nav-link px-2 text-white">Api Call</a></li>                             
                            </c:if>
                    </ul>

                    <form class="col-12 col-lg-auto mb-3 mb-lg-0 me-lg-3" role="search">
                        <input type="search" class="form-control form-control-dark text-bg-dark" placeholder="Search..." aria-label="Search">
                    </form>

                    <div class="text-end">
                        <c:set var="login_check" value="${LoggedIn}"/>
                        <c:if test="${login_check==null}">
                            <a href="login.jsp">
                                <button type="button" class="btn btn-outline-light me-2" >Login</button>
                            </a>
                            <a href="PreSignUp">
                                <button type="button" class="btn btn-warning">Sign-up</button>
                            </a>
                        </c:if>
                        <c:if test="${login_check!=null}">
                            <a href="Logout">
                                <button type="button" class="btn btn-outline-light me-2" >Log Out</button>
                            </a>
                        </c:if>

                    </div>
            </div>
        </div>
    </header>
    <div id="target">

    </div>
   