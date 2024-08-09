function setTimeOut(){
	var timeout = 1000;		//Avoid attack by using hard coded timeout
	setTimeout(function(){Foo()},timeout);
}