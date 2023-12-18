<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${pageContext.request.contextPath}/styles/index.css" rel="stylesheet" >
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
            ><i class="fa-brands fa-bitcoin bitcoin-icon"></i>itVote</a>
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
        <h1>Today's Cryptocurrency Prices</h1>
        <h1></h1>
      </div>
      <div class="coin-table">
        <table class="table-align">
          <tr>
            <td class="table-title title-align">#</td>
            <td class="table-title title-align">Name</td>
            <td class="table-title">Price</td>
            <td class="table-title">24h%</td>
            <td class="table-title">MarketCap</td>
            <td class="table-title">Volume(Total)</td>
          </tr>
          <c:forEach var="coinList" items="${coinList}" varStatus="theCount">
          <tr>
            <td class="title-align"><c:out value="${theCount.count}"/></td>
            <td class="title-align" style="font-weight: bold;"><a href="details?coinId=${coinList.getId()}">
              <img src="${coinList.getImage().getThumb()}" style="vertical-align: middle;"> ${coinList.getName()} <span class="coin-sym">${coinList.getSymbol().toUpperCase()}</span></a>
            </td>
            <td>$${coinList.getMarket_data().getCurrent_price().getUsd() }</td>
            <c:choose>
            	<c:when test = "${Float.parseFloat(coinList.getMarket_data().getPrice_change_percentage_24h()) < 0 }"><td style="color: red">${coinList.getMarket_data().getPrice_change_percentage_24h() }</td></c:when>
            	<c:otherwise><td style="color: #00C537">${coinList.getMarket_data().getPrice_change_percentage_24h() }</td></c:otherwise>
            </c:choose>
            <td>$${coinList.getMarket_data().getMarket_cap().getUsd() }</td>
            <td>$${coinList.getMarket_data().getTotal_volume().getUsd() }</td>
          </tr>
          </c:forEach>
        </table>
      </div>
    </div>
  </body>
</html>
