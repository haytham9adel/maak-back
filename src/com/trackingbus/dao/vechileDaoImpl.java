package com.trackingbus.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.trackingbus.model.DriverDocModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.VehicleDocModel;
import com.trackingbus.model.VehicleModel;

@Repository("vechileDao")
public class vechileDaoImpl implements VechileDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public long addVechile(VehicleModel s) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(s);
		return s.getVechile_id();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleModel> getAllVehicles() {
		// TODO Auto-generated method stub
		return (List<VehicleModel>) sessionFactory.getCurrentSession()
				.createCriteria(VehicleModel.class).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VehicleModel> getVechile(int school_id) {
		// TODO Auto-generated method stub
		return (List<VehicleModel>) sessionFactory.getCurrentSession()
				.createCriteria(VehicleModel.class)
				.add(Restrictions.eq("school_id", school_id)).list();
	}

	@Override
	public void deleteVechile(int vechile_id) {
		// TODO Auto-generated method stub
		sessionFactory
				.getCurrentSession()
				.createQuery(
						"DELETE FROM VehicleModel where vechile_id='"
								+ vechile_id + "'").executeUpdate();
	}

	@Override
	public void editVechileById(int vechile_id, VehicleModel vechile) {
		// TODO Auto-generated method stub
		if(vechile.getInsurance_card_copy()=="")
		{
			sessionFactory
			.getCurrentSession()
			.createQuery(
					"UPDATE VehicleModel set vehile_name='"
							+ vechile.getVehile_name() + "',manufacture='"
							+ vechile.getManufacture() + "',model='"
							+ vechile.getModel() + "',year='"
							+ vechile.getYear() + "',color='"
							+ vechile.getColor() + "',configurtion='"
							+ vechile.getConfigurtion() + "',engine='"
							+ vechile.getEngine() + "',bus_number='"
							+ vechile.getBus_number() + "',school_id='"
							+ vechile.getSchool_id()+ "',insurance_end_date='"
							+ vechile.getInsurance_end_date()
							+ "',driver_id="+vechile.getDriver_id()+" where vechile_id='" + vechile_id + "'")
			.executeUpdate();
		}
		else
		{
			sessionFactory
			.getCurrentSession()
			.createQuery(
					"UPDATE VehicleModel set vehile_name='"
							+ vechile.getVehile_name() + "',manufacture='"
							+ vechile.getManufacture() + "',model='"
							+ vechile.getModel() + "',year='"
							+ vechile.getYear() + "',color='"
							+ vechile.getColor() + "',configurtion='"
							+ vechile.getConfigurtion() + "',engine='"
							+ vechile.getEngine() + "',bus_number='"
							+ vechile.getBus_number() + "',school_id='"
							+ vechile.getSchool_id()+"',insurance_card_copy='"
							+ vechile.getInsurance_card_copy() + "',insurance_end_date='"
							+ vechile.getInsurance_end_date()
							+ "' ,driver_id="+vechile.getDriver_id()+" where vechile_id='" + vechile_id + "'")
			.executeUpdate();
		}
	
	}

	@Override
	public VehicleModel getVehicleById(int vechile_id) {
		// TODO Auto-generated method stub
		return (VehicleModel) sessionFactory
				.getCurrentSession()
				.createQuery(
						"from VehicleModel where vechile_id='" + vechile_id
								+ "'").uniqueResult();
	}

	@Override
	public void addVechileDoc(VehicleDocModel doc) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(doc);
	}

	@Override
	public void editVechileDocById(int vechile_id, VehicleDocModel vechile) {
		// TODO Auto-generated method stub
				if(vechile.getInsurance_card_copy()=="1" || vechile.getInsurance_card_copy().equals("1") || vechile.getInsurance_card_copy()=="" || vechile.getInsurance_card_copy().equals(""))
				{
					System.out.println("I am without image="+vechile.getInsurance_card_copy()+" did you see");
					sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE VehicleDocModel set insurance_document_name='"
									
									+ vechile.getInsurance_document_name()
									+ "',insurance_document_expiry='"
									+ vechile.getInsurance_document_expiry()
									+ "',remind_day='"
									+ vechile.getRemind_day()
									+ "' where v_doc_id='" + vechile_id + "'")
					.executeUpdate();
				}
				else
				{
					System.out.println("I am with image="+vechile.getInsurance_card_copy()+" did you see");
					sessionFactory
					.getCurrentSession()
					.createQuery(
							"UPDATE VehicleDocModel set insurance_document_name='"
									
									+ vechile.getInsurance_document_name()
									+ "',insurance_document_expiry='"
									+ vechile.getInsurance_document_expiry()
									+ "',remind_day='"
									+ vechile.getRemind_day()
									+ "',insurance_card_copy='"
									+ vechile.getInsurance_card_copy()
									+ "' where v_doc_id='" + vechile_id + "'")
					.executeUpdate();
				}
	}

	@Override
	public void addDriverDoc(DriverDocModel doc) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().saveOrUpdate(doc);
	}

	@Override
	public void editDriverDocById(int vechile_id, DriverDocModel vechile) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if(vechile.getInsurance_card_copy()=="1" || vechile.getInsurance_card_copy().equals("1") || vechile.getInsurance_card_copy()=="" || vechile.getInsurance_card_copy().equals(""))
		{
			System.out.println("I am without image="+vechile.getInsurance_card_copy()+" did you see");
			sessionFactory
			.getCurrentSession()
			.createQuery(
					"UPDATE DriverDocModel set insurance_document_name='"
							
							+ vechile.getInsurance_document_name()
							+ "',insurance_document_expiry='"
							+ vechile.getInsurance_document_expiry()
							+ "',insurance_end_date='"
							+ vechile.getInsurance_end_date()
							+ "',remind_day='"
							+ vechile.getRemind_day()
							+ "' where v_doc_id='" + vechile_id + "'")
			.executeUpdate();
		}
		else
		{
			System.out.println("I am with image="+vechile.getInsurance_card_copy()+" did you see");
			sessionFactory
			.getCurrentSession()
			.createQuery(
					"UPDATE DriverDocModel set insurance_document_name='"
							
							+ vechile.getInsurance_document_name()
							+ "',insurance_document_expiry='"
							+ vechile.getInsurance_document_expiry()
							+ "',insurance_end_date='"
							+ vechile.getInsurance_end_date()
							+ "',remind_day='"
							+ vechile.getRemind_day()
							+ "',insurance_card_copy='"
							+ vechile.getInsurance_card_copy()
							+ "' where v_doc_id='" + vechile_id + "'")
			.executeUpdate();
		}
	}

}
