package com.pojo1;

public class ActionForward {
	private String path = null;
	//true이면 sendRedirect
	//false 이면 forward -> view.forward(req,res)
	private boolean isRedirect = false;
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
