package com.trackingbus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.trackingbus.dao.StaffDao;
import com.trackingbus.dao.VechileDao;
import com.trackingbus.model.DepartmentModel;
import com.trackingbus.model.StaffModel;

import java.util.List;

@Service("staffservice")  
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 

public class StaffServiceImpl implements StaffService{

	@Autowired
	private StaffDao staffdao;
	
	@Override
	public List<DepartmentModel> listDepartment() {
		// TODO Auto-generated method stub
		return staffdao.listDepartment();
	}

	@Override
	public void addStaff(StaffModel staffmodel) {
		// TODO Auto-generated method stub
		staffdao.addStaff(staffmodel);
	}

	@Override
	public StaffModel checkSchoolName(String email) {
		// TODO Auto-generated method stub
		return staffdao.checkSchoolName(email);
	}

	@Override
	public List<StaffModel> listStaff(int school_id) {
		// TODO Auto-generated method stub
		return staffdao.listStaff(school_id);
	}

	@Override
	public StaffModel getStaffById(int staff_id) {
		// TODO Auto-generated method stub
		return staffdao.getStaffById(staff_id);
	}

	@Override
	public void editStaffById(int staff_id, StaffModel staff) {
		// TODO Auto-generated method stub
		staffdao.editStaffById(staff_id, staff);
	}

	@Override
	public void deleteStaff(int staff_id) {
		// TODO Auto-generated method stub
		staffdao.deleteStaff(staff_id);
	}

	@Override
	public List<StaffModel> getAllStaff() {
		// TODO Auto-generated method stub
		return staffdao.getAllStaff();
	}

}
