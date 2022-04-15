package com.examples.ezoo.dao;

import java.util.List;
import com.examples.ezoo.model.FeedingSchedule;


public interface FeedingScheduleDAO {
	//methods to get all feeding schedules or a particular animal's feeding schedule
	List<FeedingSchedule> getFeedingSchedules();
	FeedingSchedule getFeedingSchedule(long animalid);
	
	//methods to add, delete, and update feeding schedules to the database
	void addFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception;
	void deleteFeedingSchedule(int schedule_id) throws Exception;
	
	//methods to assign and remove feeding schedules to animals
	void assignFeedingSchedule(int schedule_id, long animalid) throws Exception;
	void removeFeedingSchedule(int schedule_id, long animalid) throws Exception;
	
}
