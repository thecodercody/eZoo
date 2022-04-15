package com.examples.ezoo.servlets;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/addFeedingSchedule")
public class AddFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int schedule_id = Integer.parseInt(request.getParameter("schedule_id"));
		String feeding_time = request.getParameter("feeding_time");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		long animalid = Long.parseLong(request.getParameter("animalid"));
		
		FeedingSchedule f = new FeedingSchedule(
				schedule_id,
				feeding_time,
				recurrence,
				food,
				notes,
				animalid);
	
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.addFeedingSchedule(f);
			request.getSession().setAttribute("message", "Feeding Schedule added to the database successfully");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingScheduleCare");
		} catch(SQLIntegrityConstraintViolationException e) {
			e.printStackTrace();
			
			request.getSession().setAttribute("message", "Id of " + f.getSchedule_id() + " is already in use");
			request.getSession().setAttribute("messageClass", "alert-danger");
			
			request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			
			request.getSession().setAttribute("message", "There was a problem adding the feeding schedule");
			request.getSession().setAttribute("messageClass", "alter-danger");
			
			request.getRequestDispatcher("addFeedingSchedule.jsp").forward(request, response);
		}
	}
}


