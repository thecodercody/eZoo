package com.examples.ezoo.servlets;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.examples.ezoo.dao.DAOUtilities;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.model.FeedingSchedule;


@WebServlet(description = "This servlet is the main interface into the Feeding Schedule System", urlPatterns = { "/feedingSchedules" })
public class FeedingSchedulesServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Grab a list of Feeding Schedules from the Database
		FeedingScheduleDAO dao = DAOUtilities.getFeedingScheduleDao();
		List<FeedingSchedule> feedingSchedules = dao.getFeedingSchedules();

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("feedingSchedules", feedingSchedules);
		
		request.getRequestDispatcher("feedingSchedules.jsp").forward(request, response);
	}


}
