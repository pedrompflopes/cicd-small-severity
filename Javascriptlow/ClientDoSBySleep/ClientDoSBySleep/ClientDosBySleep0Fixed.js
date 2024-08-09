function setTimer(){
	var chosenInterval = Request.Form("interval");
	var interval = chosenInterval == 0 ? 0 : 1000;		//Avoid attack by using hard coded timeout
	setInterval(function () {myTimer()}, interval);
}