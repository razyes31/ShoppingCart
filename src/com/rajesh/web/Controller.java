package com.rajesh.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rajesh.domain.Cart;
import com.rajesh.domain.Item;
import com.rajesh.service.ItemGenerator;
import com.rajesh.service.LoginService;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginservice;
	private ItemGenerator itemservice;
	private HttpSession session = null;

	public void init(ServletConfig config) throws ServletException {
		loginservice = new LoginService();
		itemservice = new ItemGenerator();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String page = request.getParameter("page");
		String action = request.getParameter("action");

		String nextPage = "/login.jsp";
		if (page != null) {
			if (page.equalsIgnoreCase("login")) {

				String username = request.getParameter("uname");
				String password = request.getParameter("psw");
				boolean isValidUser = loginservice.validate(username, password);
				if (isValidUser) {
					nextPage = "/itemlist.jsp";
					request.setAttribute("items", itemservice.updator(null));
					session = request.getSession(true);

				} else {
					nextPage = "/login.jsp";
					request.setAttribute("loginError", "Invalid username or password");
				}

			} else if (page.equalsIgnoreCase("itemslist")) {
				List<Cart> cart = new ArrayList<>();
				String[] ids = request.getParameterValues("chkItem");
				if (ids != null) {
					for (String id : ids) {

						Map<Integer, Item> itemDB = itemservice.getItemDB();

						Integer iID = new Integer(id);
						// id_temp.add(iID);
						String name = itemDB.get(iID).getName();
						Float price = itemDB.get(iID).getPrice();
						Integer quantity = new Integer(request.getParameter("quantity" + id));

						cart.add(new Cart(iID, name, price, quantity));
					}

					
				} 

				if (action.equalsIgnoreCase("Add to Cart")) {

					session.setAttribute("cart", cart);

					request.setAttribute("items", itemservice.updator(cart));
					request.setAttribute("cartSuccess", "Items successfully added to cart");

					// Forward control to 'items.jsp'
					nextPage = "/itemlist.jsp";

				} else if (action.equalsIgnoreCase("Checkout")) {
					nextPage = "/summary.jsp";

				}
			} else if (page.equalsIgnoreCase("summary")) {
				if (action.equalsIgnoreCase("Back to Cart")) {
					nextPage = "/itemlist.jsp";
					request.setAttribute("items", itemservice.updator((List<Cart>) session.getAttribute("cart")));
				} else if (action.equalsIgnoreCase("Checkout")) {
					nextPage = "/thankyou.jsp";
				}
			} else if (page.equalsIgnoreCase("thankyou")) {
				if (action.equalsIgnoreCase("LogOut")) {
					nextPage = "/login.jsp";
					session.removeAttribute("cart");
				}
			}

		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);

	}

}