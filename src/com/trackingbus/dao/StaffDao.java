package com.trackingbus.dao;
import java.util.List;

import com.trackingbus.model.DepartmentModel;
import com.trackingbus.model.SchoolModel;
import com.trackingbus.model.StaffModel;

public interface StaffDao {
	public List<DepartmentModel> listDepartment();
	public void addStaff(StaffModel s);
	public StaffModel checkSchoolName(String email);
	public List<StaffModel> listStaff(int school_id);
	public StaffModel getStaffById(int staff_id);
	public void editStaffById(int staff_id, StaffModel staff);
	public void deleteStaff(int staff_id);
	public List<StaffModel> getAllStaff();
	

}
