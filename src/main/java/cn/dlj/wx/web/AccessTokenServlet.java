package cn.dlj.wx.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "AccessTokenServlet")
public class AccessTokenServlet extends HttpServlet {

	//TODO 这个记得改定时器
	public void init() throws ServletException {
		new Thread(new TokenThread()).start(); //启动进程
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
}
