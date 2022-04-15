package com.examples.ezoo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.examples.ezoo.model.FeedingSchedule;


public class FeedingScheduleDAOImpl implements FeedingScheduleDAO {
	
	@Override
	public List<FeedingSchedule> getFeedingSchedules() {
		List<FeedingSchedule> feedingSchedules = new ArrayList<>();
		Connection connection = null;
		Statement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			
			stmt = connection.createStatement();
			
			String sql = "SELECT * FROM FEEDING_SCHEDULES";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				FeedingSchedule s = new FeedingSchedule();
				
				s.setSchedule_id(rs.getInt("schedule_id"));
				s.setFeeding_time(rs.getString("feeding_time"));
				s.setRecurrence(rs.getString("recurrence"));
				s.setFood(rs.getString("food"));
				s.setNotes(rs.getString("notes"));
				s.setAnimalid(rs.getLong("animal"));
				
				feedingSchedules.add(s);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return feedingSchedules;
	}
	
	@Override
	public FeedingSchedule getFeedingSchedule(long animalid) {
		FeedingSchedule s = new FeedingSchedule();
		Connection connection = null;
		PreparedStatement stmt = null;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT schedule_id, feeding_time, recurrence, food, notes, animal FROM FEEDING_SCHEDULES JOIN animals ON feeding_schedule = schedule_id WHERE animalid = (?)";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setLong(1, animalid);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				s.setSchedule_id(rs.getInt("schedule_id"));
				s.setFeeding_time(rs.getString("feeding_time"));
				s.setRecurrence(rs.getString("recurrence"));
				s.setFood(rs.getString("food"));
				s.setNotes(rs.getString("notes"));
				s.setAnimalid(rs.getLong("animal"));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return s;
	}
	
	@Override
	public void addFeedingSchedule(FeedingSchedule feedingSchedule) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "INSERT INTO FEEDING_SCHEDULES VALUES (?,?,?,?,?)";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, feedingSchedule.getSchedule_id());
			stmt.setString(2, feedingSchedule.getFeeding_time());
			stmt.setString(3, feedingSchedule.getRecurrence());
			stmt.setString(4, feedingSchedule.getFood());
			stmt.setString(5, feedingSchedule.getNotes());
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Insert feeding schedule failed: " + feedingSchedule);
		}
	}
	
	@Override
	public void deleteFeedingSchedule(int schedule_id) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "DELETE FROM FEEDING_SCHEDULES WHERE schedule_id = (?)";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, schedule_id);
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Delete feeding schedule failed!");
		}
	}
	
	@Override
	public void assignFeedingSchedule(int schedule_id, long animalid) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			
			//update the database
			connection = DAOUtilities.getConnection();
			
			String sql = "UPDATE ANIMALS SET feeding_schedule = ? WHERE animalid = ?";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setInt(1, schedule_id);
			stmt.setLong(2, animalid);
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Assign feeding schedule to animal failed!");
		}
	}
	
	@Override
	public void removeFeedingSchedule(int schedule_id, long animalid) throws Exception {
		Connection connection = null;
		PreparedStatement stmt = null;
		int success = 0;
		
		try {
			connection = DAOUtilities.getConnection();
			String sql = "UPDATE ANIMALS SET feeding_schedule = ? WHERE animalid = ?";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setNull(1, java.sql.Types.INTEGER);
			stmt.setLong(2, animalid);
			
			success = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		if (success == 0) {
			throw new Exception("Remove feeding schedule from animal failed!");
		}
	}
}
