package com.examples.ezoo.test;

import java.util.List;

import com.examples.ezoo.dao.AnimalDAO;
import com.examples.ezoo.dao.AnimalDaoImpl;
import com.examples.ezoo.dao.FeedingScheduleDAO;
import com.examples.ezoo.dao.FeedingScheduleDAOImpl;
import com.examples.ezoo.model.Animal;
import com.examples.ezoo.model.FeedingSchedule;

public class Logger {
	public void fLog() {
		FeedingScheduleDAO fDao = new FeedingScheduleDAOImpl();
		List<FeedingSchedule> list = fDao.getFeedingSchedules();
		for(int i = 0; i < list.size(); i++) {
			FeedingSchedule f = list.get(i);
			System.out.println(f.toString());
		}
	}
	
	public void aLog() {
		AnimalDAO aDao = new AnimalDaoImpl();
		List<Animal> animals = aDao.getAllAnimals();
		for(int i = 0; i < animals.size(); i++) {
			Animal a = animals.get(i);
			System.out.println(a.toString());
		}
	}
}
