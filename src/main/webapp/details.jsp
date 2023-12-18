<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="${pageContext.request.contextPath}/styles/details.css" rel="stylesheet" >
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
    <%
    
    %>
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
    <div class="detail">
      <div class="coin-info">
        <div>
          <h1>
            <img
              src="${imageURL}"
              style="vertical-align: middle"
            />
            ${coinList.getCoinList().get(0).getName()} <span class="coin-sym">${coinList.getCoinList().get(0).getSymbol().toUpperCase() }</span>
          </h1>
          <table class="table-detail">
            <tr>
              <td class="table-title">MarketCap</td>
              <td class="right-text">$${coinList.getCoinList().get(0).getMarket_data().getMarket_cap().getUsd()}</td>
            </tr>
            <tr>
              <td class="table-title">Volume(Total)</td>
              <td class="right-text">$${coinList.getCoinList().get(0).getMarket_data().getTotal_volume().getUsd()}</td>
            </tr>
          </table>
        </div>
        <div>
          <h2 class="right-text">Price</h2>
          <h1 class="right-text">$${coinList.getCoinList().get(0).getMarket_data().getCurrent_price().getUsd()}<br /><span style="color: ${color};" class="right-text">${coinList.getCoinList().get(0).getMarket_data().getPrice_change_percentage_24h()}</span></h1>
        </div>
      </div>
      <div class="votes-container">
        <div>
            <h1>VOTES</h1>
            <h3>Vote count</h3>
            <div class="vote-count">
                <h1 style="color: #00C537;"><i class="fa-solid fa-up-long" style="color: black;"></i> ${voteCountUp}</h1>
                <h1 style="color: red;"><i class="fa-solid fa-down-long" style="color: black;"></i> ${voteCountDown}</h1>
            </div>
            <h3>Vote percentage</h3>
            <div class="vote-count">
                <h1><i class="fa-solid fa-up-long"></i> ${votePercentageUp}%</h1>
                <h1><i class="fa-solid fa-down-long"></i> ${votePercentageDown}%</h1>
            </div>
            <h3>Average up percentage (24h)</h3>
            <div class="vote-count">
                <h1>${voteAvgUp}%</h1>
            </div>
            <h3>Average down percentage (24h)</h3>
            <div class="vote-count">
                <h1>${voteAvgDown}%</h1>
            </div>
        </div>
        <div><h1 class="right-text">SUBMIT YOUR VOTE</h1>
          <div class="modal" id="voteUpModal">
            <form id="percentageFormUp" method="get" action="vote">
              <div class="modal-content">
                <p>UP POURCENTAGE (24)</p>
                <input
                  type="number"
                  name="percentageUp"
                  class="per-input"
                  min="0"
                  max="100"
                  onkeyup=enforceMinMax(this)
                />
                <input type ="hidden" name ="coinId" value ="${coinId}" />
                <p class="per-sym">%</p>
                <a href="#" onclick="submitUpVote()"
                  ><i class="fa-solid fa-circle-chevron-right"></i
                ></a>
              </div>
            </form>
          </div>
          <div class="modal" id="voteDownModal">
            <form id="percentageFormDown" method="get" action="vote">
              <div class="modal-content">
                <p>DOWN POURCENTAGE (24)</p>
                <input
                  type="number"
                  name="percentageDown"
                  class="per-input"
                  min="0"
                  max="100"
                  onkeyup=enforceMinMax(this)
                />
                <input type ="hidden" name ="coinId" value ="${coinId}" />
                <p class="per-sym">%</p>
                <a href="#" onclick="submitDownVote()"
                  ><i class="fa-solid fa-circle-chevron-right"></i
                ></a>
              </div>
            </form>
          </div>
         </div>
      </div>
    </div>
    <script src="${pageContext.request.contextPath}/JavaScript/script.js"></script>
  </body>
</html>