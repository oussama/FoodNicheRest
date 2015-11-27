var m = jQuery.noConflict();
m(document).ready(function(){
	
	//Check to see if the window is top if not then display button
	m(window).scroll(function(){
		if (m(this).scrollTop() > 100) {
			m('.scrollToTop').fadeIn();
		} else {
			m('.scrollToTop').fadeOut();
		}
	});
	
	//Click event to scroll to top
	m('.scrollToTop').click(function(){
		m('html, body').animate({scrollTop : 0},800);
		return false;
	});
	
});
