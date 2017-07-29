package com.trackingbus.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.trackingbus.model.DriverDocModel;
import com.trackingbus.model.VehicleDocModel;
import com.trackingbus.model.VehicleModel;
import com.trackingbus.dao.StudentDao;
import com.trackingbus.dao.VechileDao;

import java.util.List;

@Service("vechileService")  
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true) 
public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VechileDao vechiledao;

	@Override
	public long addVechile(VehicleModel vechile) {
		// TODO Auto-generated method stub
		return vechiledao.addVechile(vechile);
	
	}

	@Override
	public List<VehicleModel> getVechile(int school_id) {
		// TODO Auto-generated method stub
		return vechiledao.getVechile(school_id);
	}

	@Override
	public void deleteVechile(int vechile_id) {
		// TODO Auto-generated method stub
		vechiledao.deleteVechile(vechile_id);
	}

	@Override
	public void editVechileById(int vechile_id, VehicleModel vechile) {
		// TODO Auto-generated method stub
		vechiledao.editVechileById(vechile_id, vechile);
	}

	@Override
	public VehicleModel getVehicleById(int vechile_id) {
		// TODO Auto-generated method stub
		return vechiledao.getVehicleById(vechile_id);
	}

	@Override
	public List<VehicleModel> getAllVehicles() {
		// TODO Auto-generated method stub
		return vechiledao.getAllVehicles();
	}

	@Override
	public void addVechileDoc(VehicleDocModel doc) {
		// TODO Auto-generated method stub
		vechiledao.addVechileDoc(doc);
	}

	@Override
	public void editVechileDocById(int vechile_id, VehicleDocModel vechile) {
		// TODO Auto-generated method stub
		vechiledao.editVechileDocById(vechile_id, vechile);
	}

	@Override
	public void addDriverDoc(DriverDocModel doc) {
		// TODO Auto-generated method stub
		vechiledao.addDriverDoc(doc);
	}

	@Override
	public void editDriverDocById(int vechile_id, DriverDocModel vechile) {
		// TODO Auto-generated method stub
		vechiledao.editDriverDocById(vechile_id, vechile);
	}

}
