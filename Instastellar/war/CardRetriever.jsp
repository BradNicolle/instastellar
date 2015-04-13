<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.spaceappschallenge.adelaide.server.EMFService" %>
<%@ page import="com.spaceappschallenge.adelaide.shared.Card" %>
<%@ page import="javax.persistence.EntityManager" %>

<!DOCTYPE html>

<html>
<head>
<title>Your Instastellar Card!</title>
<style>
	body{
		background-color: #333333;
		color: white;
		text-align: center;
	}
</style>
<script>
var toggle = false;
function cardClicked() {
	if (toggle) {
		document.getElementById("back_img").style.visibility="hidden";
		document.getElementById("front_img").style.visibility="visible";
		document.getElementById("front_overlay").style.visibility="visible";
		document.getElementById("back_text").style.visibility="hidden";
		document.getElementById("front_text").style.visibility="visible";
	}
	else {
		document.getElementById("back_img").style.visibility="visible";
		document.getElementById("front_img").style.visibility="hidden";
		document.getElementById("front_overlay").style.visibility="hidden";
		document.getElementById("back_text").style.visibility="visible";
		document.getElementById("front_text").style.visibility="hidden";
	}
	toggle = !toggle;
}
</script>
<link href='http://fonts.googleapis.com/css?family=Lora:400italic' rel='stylesheet' type='text/css'>
</head>

<body onClick="cardClicked()">
<%
	Card c = null;
	try {
		Long cardId = Long.parseLong(request.getPathInfo().substring(1));
	//	out.println("YOUR CARD NAME: " + cardId + "<br />");
		
		EntityManager em = EMFService.get().createEntityManager();
		c = em.find(Card.class, cardId);
		if (c == null) {
			out.println("No card found for name: " + cardId);
		}
	//	else {
	//		out.println(c.getId() + " " + c.getUrl() + " " + c.getMessage());
	//	}
		
		List<Card> cards = em.createQuery("select c from Card c").getResultList();
	//	out.println("<br />ALL CARDS:<br />");
	//	for (Card card : cards) {
	//		out.println(card.getId() + "<br />");
	//	}
		
	} catch(NumberFormatException nfe) {
		out.println("Invalid card id!");
	}
%>

<div id="front_img" style="position: absolute; width: 95%; padding: 0; margin: 0;">
<img src="<%= c.getUrl() %>" style="width: 1200px; height:800px; padding: 0; margin: 0;" />
</div>

<div id="front_overlay" style="position: absolute; width: 95%; padding: 0; margin: 0;">
<img src="../Instastellar_Front.png" style="width: 1200px; height:800px; opacity: 0.3; padding: 0; margin: 0;" />
</div>

<div id="front_text" style="position: absolute; width: 95%; padding-top: 600px; font-family: 'Lora', serif; font-size: 18pt;">
Lorem ipsum dolor sit amet, consectetur adipiscing elit.
</div>

<div id="back_img" style="width: 95%; visibility: hidden;">
<img src="../Instastellar_CardBack.png" style="width: 1200px; 800px;" />
</div>

<div id="back_text" style="position: absolute; visibility: hidden; width: 95%; top: 100px; font-family: 'Lora', serif; font-size: 18pt; color: black;">
<%= c.getMessage() %>
</div>
</body>

</html>