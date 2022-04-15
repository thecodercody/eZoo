package com.examples.ezoo.servlets;

import java.io.IOException;
import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/assignFeedingSchedule")
public class AssignFeedingScheduleServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("schedule_id"));
		String time = request.getParameter("feeding_time");
		String recurrence = request.getParameter("recurrence");
		String food = request.getParameter("food");
		String notes = request.getParameter("notes");
		long animalid = Long.parseLong(request.getParameter("animalid"));
		
		FeedingSchedule f = new FeedingSchedule(
				id,
				time,
				recurrence,
				food,
				notes,
				animalid);
	
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		try {
			dao.assignFeedingSchedule(f.getSchedule_id(), animalid);
			request.getSession().setAttribute("message", "Feeding Schedule assigned to the animal successfully");
			request.getSession().setAttribute("messageClass", "alert-success");
			response.sendRedirect("feedingScheduleCare");
		} catch (Exception e) {
			e.printStackTrace();
			
			request.getSession().setAttribute("message", "There was a problem assigning the feeding schedule");
			request.getSession().setAttribute("messageClass", "alter-danger");
			
			request.getRequestDispatcher("assignFeedingSchedule.jsp").forward(request, response);
		}
	}
}
