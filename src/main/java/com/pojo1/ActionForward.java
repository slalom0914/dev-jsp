package com.pojo1;

public class ActionForward {
	private String path = null;//viewName -> ModelAndView
	//true이면 sendRedirect
	//false 이면 forward -> view.forward(req,res)
	private boolean isRedirect = false;
	/*
	 * 페이지 이동하는 방법
	 * 1) redirect -> insert, update, delete
	 * 2) forward -> select
	 */
	public String getPath() {//getter
		return path;
	}
	public void setPath(String path) {//setter
		this.path = path;
	}
	public boolean isRedirect() {//getter
		return isRedirect;
	}
	public void setRedirect(boolean isRedirect) {//setter
		this.isRedirect = isRedirect;
	}
}
