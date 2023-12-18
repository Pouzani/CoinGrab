<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/signuperror.css" />
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
    <title>Sign up</title>
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
        <a href="loginView"
          ><i class="fa-solid fa-right-to-bracket login-icon"></i
        ></a>
      </div>
    </nav>
    <div class="signup">
      <h1>Sign up</h1>
      <form action="signup" method="post">
        <div class="signup-layout">
          <div class="signup-container">
            <div class="signup-essentails">
              <div class="item-input">
                <label for="username">Username</label>
                <input
                  type="text"
                  id="username"
                  name="username"
                  placeholder="Enter a username"
                  value="${user.getUserName() }"
                />
              </div>
              <div class="item-input">
                <label for="email">Email adresse</label>
                <input
                  class="error"
                  type="email"
                  id="email"
                  name="email"
                  placeholder="Email already exists"
                />
              </div>
              <div class="item-input">
                <div class="pass-label">
                  <label for="password">Password</label>
                </div>
                <input
                  type="password"
                  id="password"
                  name="password"
                  placeholder="Choose a password"
                  value="${user.getPassword() }"
                />
              </div>
            </div>
            <div class="signup-essentails">
              <div class="item-input">
                <div class="two-inputs-layout">
                  <div class="two-inputs-item">
                    <label for="fName">First name</label>
                    <input
                      type="text"
                      id="fName"
                      name="fName"
                      placeholder="Enter your first..."
                      value="${user.getFirstName() }"
                    />
                  </div>
                  <div class="two-inputs-item">
                    <label for="lName">Last Name</label>
                    <input
                      type="text"
                      id="lName"
                      name="lName"
                      placeholder="Enter your Last..."
                      value="${user.getLastName() }"
                    />
                  </div>
                </div>
                <div class="two-inputs-layout">
                  <div class="two-inputs-item">
                    <label for="age">Age</label>
                    <input
                      type="number"
                      id="age"
                      name="age"
                      placeholder="Enter your age"
                      value="${user.getAge() }"
                    />
                  </div>
                  <div class="two-inputs-item">
                    <label for="nation">Nationality</label>
                    <input
                      type="text"
                      id="nation"
                      name="nation"
                      placeholder="Enter your natio..."
                      value="${user.getNationality() }"
                    />
                  </div>
                </div>
              </div>
              <div class="item-input">
                <label for="phone">Phone Number</label>
                <input
                  type="text"
                  id="phone"
                  name="phone"
                  placeholder="Enter your phone number"
                  value="${user.getPhone() }"
                />
              </div>
            </div>
          </div>
          <div class="signup-btn-container">
            <button href="#" type="submit" class="signup-button">
              SIGN UP
            </button>
            <br />
            <a href="loginView">Already member? Login here!</a>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>
