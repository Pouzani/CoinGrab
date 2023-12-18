<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/admin.css" />
    <link rel="preconnect" href="https://fonts.googleapis.com" />
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin />
    <link
      href="https://fonts.googleapis.com/css2?family=Rubik:ital,wght@0,300;0,400;0,500;0,600;0,700;0,800;0,900;1,300;1,400;1,500;1,600;1,700;1,800;1,900&display=swap"
      rel="stylesheet"
    />
    <script
      src="https://kit.fontawesome.com/87c05a788c.js"
      crossorigin="anonymous"
    ></script>
    <title>BitVote</title>
  </head>
  <body>
    <nav class="navbar">
      <div></div>
      <div>
        <h1 class="title">Admin Dashboard</h1>
      </div>
      <div>
        <a href="logout" class="logout"><i class="fa-solid fa-xmark"></i></a>
      </div>
    </nav>
    <div class="container">
      <div class="container-items">
        <form id="searchForm" method="get" action="search">
          <div class="search">
            <input
              class="searchInput"
              type="text"
              name="search"
              placeholder="Search..."
              value="${search}"
            />
            <input type="submit" style="display: none" />
          </div>
        </form>
        <div>
        <c:forEach items="${Users}" var="a">
          <div class="user">
            <div class="user-info">
              <h3>#${a.getIdUser() } ${a.getUserName()}</h3>
              <h3 class="sub">${a.getEmail() }</h3>
              <h3 class="sub">${a.getPhone() }</h3>
              <h3 class="sub">${a.getRole() }</h3>
            </div>
            <div class="user-buttons">
              <a href="delete?idUser=${a.getIdUser()}"><i class="fa-solid fa-trash"></i></a>
              <a href="adminedit?idUser=${a.getIdUser()}"><i class="fa-solid fa-pen-to-square"></i></a>
            </div>
          </div>
          </c:forEach>
        </div>
      </div>
    </div>
  </body>
</html>
