<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/forum1.css" />
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
      <div class="brand">
        <h1>
          <a href="home"
            ><i class="fa-brands fa-bitcoin bitcoin-icon"></i>itVote</a
          >
        </h1>
      </div>
      <div class="navbar-item">
        <a href="home"><h3>CRYPTOCURRENCIES</h3></a>
        <a href="forum"><h3>FORUM</h3></a>
        <a href="#"><h3>ABOUT US</h3></a>
        <c:choose>
        <c:when test="${user!=null}">
        	<a href="profile"><h3>${user.getUserName()}</h3></a>
        </c:when>
        <c:otherwise>
        <a href="loginView"><i class="fa-solid fa-right-to-bracket login-icon"></i></a>
        </c:otherwise>
        </c:choose>
      </div>
    </nav>
    <div class="main">
      <div class="title">
        <h1>Welcome to BitVote forum</h1>
        <h1></h1>
      </div>
      <div class="layout">
        <div class="post">
        <form method="post" action="createArticle" >
            <div class="article-input">
              <h2 class="write">Write your article</h2>
              <input type="text" name="title" placeholder="Title" required/>
              <textarea maxlenght="200" name="content" placeholder="Content" required></textarea>
              <input type="submit" style="display: none" />
            </div>
          </form>
          <div class="profile">
            <table>
            <c:forEach items="${articles}" var="a">
              <tr>
                <td>
                  <img src="${pageContext.request.contextPath}/images/profile.png" alt="profile" />
                </td>
                <td>
                  <h3>${a.getUser()}</h3>
                </td>
              </tr>
              <tr>
                <td><div class=""></div></td>
                <td class="para">
                  <h4>${a.getTitle()}</h4>
                  <p>
                    ${a.getContent()}
                  </p>
                </td>
              </tr>
              </c:forEach>
            </table>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>