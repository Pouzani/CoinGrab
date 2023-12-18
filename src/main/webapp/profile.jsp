<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/profile.css" />
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
    <title>Profile</title>
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
    <div class="signup">
      <form action="updateUser" method="post" enctype="multipart/form-data">
        <div class="signup-layout">
          <div class="signup-btn-container">
            <label><img src="${pageContext.request.contextPath}/images/riyadcv.jpeg" alt="profile-pic">
              <input type="file" id="img" name="profileImg" accept="image/*" style="display:none"></label>
          </div>
          <div class="signup-container">
            <div class="signup-essentails">
              <div class="item-input">
                <label for="username">Username</label>
                <input
                  type="text"
                  id="username"
                  name="username"
                  placeholder="Enter a username"
                  value="${user.getUserName()}"
                  disabled
                />
              </div>
              <div class="item-input">
                <label for="email">Email adresse</label>
                <input
                  type="email"
                  id="email"
                  name="email"
                  placeholder="Change your email adresse"
                  value="${user.getEmail()}"
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
                  placeholder="Change your password"
                  value="${user.getPassword()}"
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
                      placeholder="Change your first..."
                      value="${user.getFirstName()}"
                    />
                  </div>
                  <div class="two-inputs-item">
                    <label for="lName">Last Name</label>
                    <input
                      type="text"
                      id="lName"
                      name="lName"
                      placeholder="Change your Last..."
                      value="${user.getLastName()}"
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
                      placeholder="Change your age"
                      value="${user.getAge()}"
                    />
                  </div>
                  <div class="two-inputs-item">
                    <label for="nation">Nationality</label>
                    <input
                      type="text"
                      id="nation"
                      name="nation"
                      placeholder="Change your natio..."
                      value="${user.getNationality()}"
                    />
                  </div>
                </div>
              </div>
              <div class="item-input">
                <label for="phone">Phone Number</label>
                <input
                  type="number"
                  id="phone"
                  name="phone"
                  placeholder="Change your phone number"
                  value="${user.getPhone()}"
                />
              </div>
            </div>
          </div>
          <div class="signup-btn-container">
            <div class="signup-btn">
              <a href="logout" class="signup-button"><span class="logout">LOGOUT</span></a>
              <br />
              <a href="delete" class="delete">Delete your account</a>
            </div>
            <div class="signup-btn">
              <button type="submit" class="save-button">SAVE</button>
              <br />
              <br />
            </div>
          </div>
        </div>
      </form>
    </div>
  </body>
</html>
