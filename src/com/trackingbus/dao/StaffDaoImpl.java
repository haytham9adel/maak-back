package com.trackingbus.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trackingbus.model.DepartmentModel;
import com.trackingbus.model.StaffModel;
import com.trackingbus.model.StudentModel;

@Repository("staffDao")
public class StaffDaoImpl implements StaffDao{

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<DepartmentModel> listDepartment() {
		// TODO Auto-generated method stub
		return (List<DepartmentModel>) sessionFactory.getCurrentSession().createCriteria(DepartmentModel.class).list();
	}

	@Override
	public void addStaff(StaffModel s) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(s);
	}

	@Override
	public StaffModel checkSchoolName(String email) {
		// TODO Auto-generated method stub
		return (StaffModel) sessionFactory.getCurrentSession().createQuery("from StaffModel where staff_email='"+email+"'").uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StaffModel> listStaff(int school_id) {
		// TODO Auto-generated method stub
		return (List<StaffModel>) sessionFactory.getCurrentSession().createCriteria(StaffModel.class).add(Restrictions.eq("school_id", school_id)).list();
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public List<StaffModel> getAllStaff() {
		// TODO Auto-generated method stub
		return (List<StaffModel>) sessionFactory.getCurrentSession().createCriteria(StaffModel.class).list();
	}
	
	

	@Override
	public StaffModel getStaffById(int staff_id) {
		// TODO Auto-generated method stub
		return (StaffModel) sessionFactory.getCurrentSession().createQuery("from StaffModel where staff_id='"+staff_id+"'").uniqueResult();
	}

	@Override
	public void editStaffById(int staff_id, StaffModel staff) {
		// TODO Auto-generated method stub
		if(staff.getImage_path()!="")
		{
			sessionFactory.getCurrentSession().createQuery("UPDATE StaffModel set staff_name='"+staff.getStaff_name()+"',staff_lname='"+staff.getStaff_lname()+"',staff_contact='"+staff.getStaff_contact()+"',address='"+staff.getAddress()+"',dept_id='"+staff.getDept_id()+"',image_path='"+staff.getImage_path()+"',education='"+staff.getEducation()+"' where staff_id='"+staff_id+"'").executeUpdate();
		}
		else
		{
			sessionFactory.getCurrentSession().createQuery("UPDATE StaffModel set staff_name='"+staff.getStaff_name()+"',staff_lname='"+staff.getStaff_lname()+"',staff_contact='"+staff.getStaff_contact()+"',address='"+staff.getAddress()+"',dept_id='"+staff.getDept_id()+"',education='"+staff.getEducation()+"' where staff_id='"+staff_id+"'").executeUpdate();
		}
		
	}

	@Override
	public void deleteStaff(int staff_id) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().createQuery("DELETE FROM StaffModel where staff_id='"+staff_id+"'").executeUpdate();
	}
	
}
