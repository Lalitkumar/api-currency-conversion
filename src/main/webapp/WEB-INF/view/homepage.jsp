<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="style.css">
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <script>
        $(document).ready(function(){
          $("#b1").click(function(){
            if($("#amount").val()==0 || $("#amount").val()==""){
                alert("Amount Value can not be blank or zero");
                return 0;
            }
            if($("#currency").val()==""){
                alert("Please select currency");
                return 0;
            }
            var trHTML = '';
            $.ajax({
                       type: 'POST',
                       dataType: 'json',
                       url: '/calculateNotes',
                       data: { amount:$("#amount").val(), currency:$("#currency").val()},
                       dataType:"json",
                       success: function (data) {
                            $("#curr_table").find("tr:gt(0)").remove();
                            $.each(data, function(index, currDtls) {
                                        trHTML +=
                                        '<tr align="center" width="100"><td style="text-align:center">' + currDtls.noteValue +
                                        '</td><td style="text-align:center">' + currDtls.noteCount +
                                        '</td></tr>';
                                     });
                        $('#curr_table').append(trHTML);
                        $("#curr_table").css("display", "block");
                       },
                       error: function (XMLHttpRequest, textStatus, errorThrown) {

                       }
                   });
          });
          $("#amount").inputFilter(function(value) {
              return /^\d*$/.test(value);
            });

        });


    </script>

</head>
    <body>
<form>
	<div class="mainbodydiv">
		<table>
			<tr>
			    <h3 style="color:red;">Currency Note/Coin Calculator</h3>
			</tr>
			<tr>
			<td>
		<table>
			<tr>
			    <td align="right" colspan="2">Amount</td>
			    <td colspan="2"><input type="text" name="amount" id="amount" style="width:220px;height:29px;padding:5px"/></td>
			    <td><form:errors path="amount" cssStyle="color: #ff0000;" /></td>
			 </tr>
			 <tr>
			    <td align="right" colspan="2">Currency</td>
			    <td colspan="2"><form:select path="currency" id="currency" style="width:220px;height:29px;padding:5px">
			                <form:option value="" label="Select Currency"/>
                            <form:options items="${currencyList}"/>
                            </form:select>
                </td>
              </tr>
              <tr>
                <td></td>
			    <td align="right"><input type="button" id="b1" value="Submit" class="btn"/> </td>
			  </tr>
		</table>
		</td>
		<td>
			<table id="curr_table" style="display:none" class="w3-table-all w3-hoverable">
	        <tr>
	            <th align="center" width="100">Note/Coin</th>
                <th align="center" width="100">Count</th>
	        </tr>
	    </table>
		</td>
		</tr>
		</table>
	</div>
<form>
</body>
</html>
