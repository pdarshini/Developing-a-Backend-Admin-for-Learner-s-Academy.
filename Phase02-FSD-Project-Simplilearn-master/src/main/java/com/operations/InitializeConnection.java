package com.operations;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.util.Util;

@WebServlet("/init")
public class InitializeConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		request.getRequestDispatcher("index.jsp").include(request, response);
		
		try {
			SessionFactory factory = Util.buildConnection();
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			
			if(session != null) {
				out.println("<div align='center'><h2 style='color:green'>CONNECTED SUCCESSFULLY</h2></div>");
			}
			t.commit();
			session.close();
		} catch (Exception e) {
				out.println("<div align='center'><h2 style='color:red'>Failed</h2></div>");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
