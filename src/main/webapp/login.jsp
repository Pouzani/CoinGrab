<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
.<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/login.css" />
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
    <title>Login</title>
  </head>
  <body>
    <nav class="navbar">
      <div class="brand">
        <h1><a href="home"><i class="fa-brands fa-bitcoin bitcoin-icon"></i>itVote</a></h1>
      </div>
      <div class="navbar-item">
        <a href="#"><h3>CRYPTOCURRENCIES</h3></a>
        <a href="#"><h3>FORUM</h3></a>
        <a href="#"><h3>ABOUT US</h3></a>
        <a href="loginView"><i class="fa-solid fa-right-to-bracket login-icon"></i></a>
      </div>
    </nav>
    <div class="login">
      <h1>Log in</h1>
      <form action="login" method="post">
        <div class="login-container">
          <div class="email-input">
            <label for="email">Email adresse</label>
            <input
              type="email"
              id="email"
              name="email"
              placeholder="Enter your email adresse"
              required
            />
          </div>
          <div class="email-input">
            <div class="pass-label">
              <label for="password">Password</label>
              <a href="#">Forgot password?</a>
            </div>
            <input
              type="password"
              id="password"
              name="password"
              placeholder="Enter your password"
              required
            />
          </div>
          <div class="login-btn-container">
            <button href="#" type="submit" class="login-button">LOGIN</button>
            <br>
            <a href="signupView">Not a member? Sign up here!</a>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>
