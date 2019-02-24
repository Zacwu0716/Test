package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CustomerBean;
import model.CustomerService;

@WebServlet(
		urlPatterns={"/secure/login.controller"}
)
public class LoginServlet extends HttpServlet {
	private CustomerService customerService;
	@Override
	public void init() throws ServletException {
		customerService = new CustomerService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
//接收資料
		String username = request.getParameter("username");
		String password = request.getParameter("password");

//驗證資料：準備好錯誤訊息之後、將錯誤訊息傳送給View元件
		Map<String, String> errors = new HashMap<>();
		request.setAttribute("errorMsgs", errors);
		
		if(username==null || username.length()==0) {
			errors.put("xxx1", "請輸入帳號");
		}
		if(password==null || password.length()==0) {
			errors.put("xxx2", "請輸入密碼");
		}
		if(errors!=null && !errors.isEmpty()) {
			request.getRequestDispatcher(
					"/secure/login.jsp").forward(request, response);
			return;
		}
//呼叫model
		CustomerBean bean = customerService.login(username, password);
		
//根據model執行結果呼叫View
		if(bean==null) {
			errors.put("xxx2", "登入失敗");
			request.getRequestDispatcher(
					"/secure/login.jsp").forward(request, response);
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("user", bean);

			String path = request.getContextPath();
			response.sendRedirect(path+"/index.jsp");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
