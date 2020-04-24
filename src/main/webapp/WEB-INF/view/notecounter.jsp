<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<body>
	<div>
        <h1>Currency Note Calculator</h1>
        <table>
                <tr><td>Details of Notes or Coins for amount <b>${currency} ${amount}</b></td></tr>
               <tr>
                    <td><b>Note/Coin</td><td><b>Count</td>
               </tr>
           <c:forEach var="noteList" items="${noteList}">
              <tr>
                  <td>${noteList.noteValue}</td><td>${noteList.noteCount}</td>
              </tr>
           </c:forEach>
        </table>
    </div>
</body>
</html>
