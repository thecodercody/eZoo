package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deleteFeedingSchedule")
public class DeleteFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("deleteFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
	
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.deleteFeedingSchedule(schedule_id);
			request.getSession().setAttribute("message", "Feeding Schedule deleted from the database successfully");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingScheduleCare");
		} catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			
			request.getSession().setAttribute("message", "Feeding Schedule with Id of " + schedule_id + " doesn't exist");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("deleteFeedingSchedule.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			request.getSession().setAttribute("message", "There was a problem deleting the feeding schedule");
			request.getSession().setAttribute("messageClass", "alter-danger");
			
			request.getRequestDispatcher("deleteFeedingSchedule.jsp").forward(request, response);
		}
	}
}
