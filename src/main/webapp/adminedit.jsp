<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/adminedit.css" />
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
        <form id="searchForm">
          <div class="search">
            <input
              class="searchInput"
              type="text"
              name="search"
              placeholder="Search..."
            />
            <input type="submit" style="display: none" />
          </div>
        </form>
        <div>
          <div class="user">
            <div class="user-info">
              <form id="editForm" method="get" action="edit">
                <input
                  class="editinput"
                  type="text"
                  name="userName"
                  value="${user.getUserName() }"
                />
                <input
                  class="editinput"
                  type="text"
                  name="email"
                  value="${user.getEmail() }"
                />
                <input
                  class="editinput"
                  type="text"
                  name="phone"
                  value="${user.getPhone() }"
                />
                <input
                  class="editinput"
                  type="text"
                  name="role"
                  value="${user.getRole() }"
                />
                <input type ="hidden" name ="idUser" value ="${user.getIdUser()}" />
              </form>
            </div>
            <div class="user-buttons">
              <a onclick="submitEdit()"><i class="fa-solid fa-check"></i></a>
            </div>
          </div>
        </div>
      </div>
    </div>
  </body>
  <script src="${pageContext.request.contextPath}/JavaScript/script.js"></script>
</html>
