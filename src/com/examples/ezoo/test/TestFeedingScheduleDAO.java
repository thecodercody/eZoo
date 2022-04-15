package com.examples.ezoo.test;
import java.util.ArrayList;
import java.util.List;

import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.dao.FeedingScheduleDAOImpl;
import com.examples.ezoo.model.*;
import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.AnimalDaoImpl;
import com.examples.ezoo.dao.DAOUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TestFeedingScheduleDAO {
	
	public static void main(String[] args) throws Exception {
		FeedingScheduleDAO fDao = new FeedingScheduleDAOImpl();
		Logger logger = new Logger();
		
		//get all feeding schedules
		System.out.println("Get all feeding schedules");
		logger.fLog();
		
		//get a specific animal's feeding schedule
		System.out.println("Get the first animal's feeding schedule");
		FeedingSchedule fs = fDao.getFeedingSchedule(1);
		System.out.println(fs.toString());
		
		
		//add a feeding schedule to the database
		System.out.println("Add a new feeding schedule to the database");
		FeedingSchedule fs1 = new FeedingSchedule();
		fs1.setSchedule_id(2);
		fs1.setFeeding_time("evening");
		fs1.setRecurrence("monthly");
		fs1.setFood("omnivorous diet");
		fs1.setNotes("doohicky mcwickenfudd");
		fDao.addFeedingSchedule(fs1);
		logger.fLog();
		
		//delete feeding schedule from database
		System.out.println("Delete feeding schedule from database");
		fDao.deleteFeedingSchedule(fs1.getSchedule_id());
		logger.fLog();
	
		//assign a feeding schedule to an animal
		System.out.println("Assign a feeding schedule to an animal");
			//add new animal to the database 
			/*
			AnimalDAO aDao = new AnimalDaoImpl();
			Animal a = new Animal(3L, "big bird", "animalia", "chordata", "aves", "passeriformes", "parulidae", "setophaga", "yellow warbler", 3.5D, 0.7D, "bird", "healthy");
			aDao.saveAnimal(a); 
		
			//add new feeding scheduled to be assigned to the database
			*/
		FeedingSchedule fs3 = new FeedingSchedule(2, "morning and evening", "every other day", "meat", "more notes");

		//fDao.addFeedingSchedule(fs3);
			
		fDao.assignFeedingSchedule(2, 3L);
		logger.aLog();
		/*
		//remove a feeding schedule from an animal
		fDao.removeFeedingSchedule(1, 1);
		logger.aLog();
		*/
	}
}
