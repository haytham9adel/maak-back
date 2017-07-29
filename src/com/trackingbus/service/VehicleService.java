package com.trackingbus.service;
import java.util.List;

import com.trackingbus.model.DriverDocModel;
import com.trackingbus.model.LoginModel;
import com.trackingbus.model.VehicleDocModel;
import com.trackingbus.model.VehicleModel;

public interface VehicleService {
	public long addVechile(VehicleModel vechile);
	public List<VehicleModel> getVechile(int school_id);
	public List<VehicleModel> getAllVehicles();
	public void deleteVechile(int vechile_id);
	public void editVechileById(int vechile_id,VehicleModel vechile);
	public VehicleModel getVehicleById(int vechile_id) ;
	public void addVechileDoc(VehicleDocModel doc);
	public void editVechileDocById(int vechile_id,VehicleDocModel vechile);
	public void addDriverDoc(DriverDocModel doc);
	public void editDriverDocById(int vechile_id,DriverDocModel vechile);
	
}
