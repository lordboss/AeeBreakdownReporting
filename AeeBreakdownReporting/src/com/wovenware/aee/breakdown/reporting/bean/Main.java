package com.wovenware.aee.breakdown.reporting.bean;

/**
 * <i>Main Bean.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti, Nelson Perez
 */

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import com.wovenware.aee.breakdown.reporting.Constants;
import com.wovenware.aee.breakdown.reporting.db.dao.Bk2UsersDAO;
import com.wovenware.aee.breakdown.reporting.db.dao.CityAreasDAO;
import com.wovenware.aee.breakdown.reporting.db.dao.UserAreasDAO;
import com.wovenware.aee.breakdown.reporting.db.dao.to.Bk2UsersTO;
import com.wovenware.aee.breakdown.reporting.db.dao.to.CityAreasTO;
import com.wovenware.aee.breakdown.reporting.db.dao.to.UserAreasTO;
import com.wovenware.aee.breakdown.reporting.util.ConnectionUtil;
import com.wovenware.aee.breakdown.reporting.util.FeedbackUtil;

@ManagedBean
@SessionScoped
public class Main extends GenericBean {
	private static final long serialVersionUID = 1L;
	
	private String _userAreas = null;
	private String _areaToUpdate = null;
	private String _newName = null;
	private String _areaToDelete = null;
	private String _city = null;
	private String _area = null;
	private String _name = null;
	
	private List<SelectItem> _cityList = null;
	private List<SelectItem> _areaList = null;
	
	// User Areas
	public String getUserAreas() {
		_userAreas = loadUserAreas();
		
		return _userAreas;
	}
	
	// Area to Update
	public String getAreaToUpdate() {
		return _areaToUpdate;
	}
	
	public void setAreaToUpdate(String areaToUpdate) {
		_areaToUpdate = areaToUpdate;
	}
	
	// New Name
	public String getNewName() {
		return _newName;
	}
	
	public void setNewName(String newName) {
		_newName = newName;
	}
	
	// Area to Delete
	public String getAreaToDelete() {
		return _areaToDelete;
	}
	
	public void setAreaToDelete(String areaToDelete) {
		_areaToDelete = areaToDelete;
	}
	
	// City
	public String getCity() {
		return _city;
	}
	
	public void setCity(String city) {
		_city = city;
	}
	
	public List<SelectItem> getCityList() {
		_cityList = loadCityList();
		
		return _cityList;
	}
	
	// Area
	public String getArea() {
		return _area;
	}
	
	public void setArea(String area) {
		_area = area;
	}
	
	public List<SelectItem> getAreaList() {
		_areaList = loadAreaList();
		
		return _areaList;
	}
	
	// Name
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public String loadUserAreas() {
		StringBuilder userAreas = new StringBuilder();
		
		List<UserAreasTO> results = getUserAreas(_userEmail);
		
		int i = 0;
		
		if(results != null) {
			for(UserAreasTO userAreasTO : results) {
				i++;
				
				boolean hasBreakdown = userAreasTO.getStatus() != null;
				
				userAreas.append("<div class=\"row-fluid item\">");
				userAreas.append("<div class=\"span11\">");
				userAreas.append("<h4 id=\"title" + i + "\">");
				
				if(hasBreakdown) {
					userAreas.append("<i class=\"icon-bell text-error\"></i>");
				} else {
					userAreas.append("<i class=\"icon-check text-info\"></i>");
				}
				
				userAreas.append("&nbsp;");
				userAreas.append("<a id=\"update" + i + "\"" +
						" href=\"javascript:;\"" +
						" data-toggle=\"tooltip\"" +
						" title=\"Actualizar\" " +
						" onmouseover=\"$('#update" + i + "').tooltip('show');\"" +
						" onclick=\"editArea('" + i + "','" + userAreasTO.getName() + "');\">");
				userAreas.append(userAreasTO.getName());
				userAreas.append("</a>");
				
				if(!userAreasTO.getName().equals(userAreasTO.getArea() + ", " + userAreasTO.getCity())) {
					userAreas.append("&nbsp;<small>");
					userAreas.append("(" + userAreasTO.getArea() + ",&nbsp;" + userAreasTO.getCity() + ")");
					userAreas.append("</small>");
				}
				
				userAreas.append("</h4>");
				userAreas.append("<div id=\"updateForm" + i + "\" class=\"update-form\" style=\"display: none;\">");
				userAreas.append("<input id=\"updateOriginalName" + i + "\" type=\"hidden\" />");
				userAreas.append("<input id=\"updateName" + i + "\" type=\"text\" />");
				userAreas.append("&nbsp;");
				userAreas.append("<a href=\"javascript:;\" class=\"btn btn-primary btn-small\" onclick=\"executeUpdate('" + i + "');\">Actualizar</a>");
				userAreas.append("&nbsp;");
				userAreas.append("<a href=\"javascript:;\" class=\"btn btn-small\" onclick=\"cancelUpdate('" + i + "');\">Cancelar</a></div>");
				userAreas.append("<div class=\"status\">");
				
				if(hasBreakdown) {
					userAreas.append("<span class=\"label label-important\">");
					userAreas.append(userAreasTO.getStatus());
					userAreas.append("</span>");
					userAreas.append("&nbsp;-&nbsp;");
					userAreas.append(userAreasTO.getRptdLastUpdateTs());
				} else {
					userAreas.append("<span class=\"label label-info\">");
					userAreas.append("No hay aver&iacute;as reportadas para esta &aacute;rea");
					userAreas.append("</span>");
				}
				
				userAreas.append("</div></div>");
				userAreas.append("<div class=\"span1 remove\">");
				userAreas.append("<a id=\"delete" + i + "\"" +
						" href=\"javascript:;\"" +
						" class=\"close\" " +
						" data-toggle=\"tooltip\"" +
						" title=\"Borrar\" " +
						" onmouseover=\"$('#delete" + i + "').tooltip('show');\"" +
						" onclick=\"removeArea('" + userAreasTO.getName() + "');\">");
				userAreas.append("<i class=\"icon-remove-circle\"></i>");
				userAreas.append("</a></div></div>");
			}
		}
		
		if(i == 0) {
			userAreas.append(FeedbackUtil.formatGeneralFeedback(
					Constants.AlertTypes.WARNING,
					"Atenci&oacute;n!",
					"Usted no tiene ning&uacute;n &aacute;rea relevante configurada en este momento. Por favor utilize la forma de la derecha para a&ntilde;adir las &aacute;reas relevantes para usted."));
	    	
		}
		
		return userAreas.toString();
	}
 
    public List<UserAreasTO> getUserAreas(String email) {
    	List<UserAreasTO> results = null;
    	
    	Connection connection = null;
    	
    	try {
    		connection = ConnectionUtil.createConnection(
    				Constants.Services.JNDI_JDBC_APP, false);
	    		
    		UserAreasDAO userAreasDAO = new UserAreasDAO(connection);
	    	results = userAreasDAO.find(email);
	    		
	    	connection.commit();
    	} catch(Exception e) {
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.rollback();
    			}
    		} catch (Exception e1) {
    			// Do nothing...
    		}
    	} finally {
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.close();
    			}
    		} catch (Exception e) {
    			// Do nothing...
    		} finally {
    			connection = null;
    		}
    	}
    	
    	return results;
    }
    
    public List<SelectItem> loadCityList() {
    	List<SelectItem> cityList = new ArrayList<SelectItem>();
    	
    	List<CityAreasTO> results = getCities(_userEmail);
    	
    	boolean isFirst = true;
    	
    	for(CityAreasTO cityAreasTO : results) {
    		if(isFirst){
    			isFirst = false;
    			
    			_city = cityAreasTO.getCity();
    		}
    		
    		cityList.add(new SelectItem(cityAreasTO.getCity()));
    	}
        
		return cityList;
	}
    
    public List<CityAreasTO> getCities(String email) {
    	List<CityAreasTO> results = null;
    	
    	Connection connection = null;
    	
    	try {
    		connection = ConnectionUtil.createConnection(
    				Constants.Services.JNDI_JDBC_APP, false);
	    		
    		CityAreasDAO cityAreasDAO = new CityAreasDAO(connection);
	    	results = cityAreasDAO.findCities(email);
	    		
	    	connection.commit();
    	} catch(Exception e) {
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.rollback();
    			}
    		} catch (Exception e1) {
    			// Do nothing...
    		}
    	} finally {
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.close();
    			}
    		} catch (Exception e) {
    			// Do nothing...
    		} finally {
    			connection = null;
    		}
    	}
    	
    	return results;
    }
    
    public List<SelectItem> loadAreaList() {
    	List<SelectItem> areaList = new ArrayList<SelectItem>();
    	
    	List<CityAreasTO> results = getAreas(_city, _userEmail);
    	
    	for(CityAreasTO cityAreasTO : results) {
    		areaList.add(new SelectItem(cityAreasTO.getArea()));
    	}
        
		return areaList;
	}
    
    public List<CityAreasTO> getAreas(String city, String email) {
    	List<CityAreasTO> results = null;
    	
    	Connection connection = null;
    	
    	try {
    		connection = ConnectionUtil.createConnection(
    				Constants.Services.JNDI_JDBC_APP, false);
	    		
    		CityAreasDAO cityAreasDAO = new CityAreasDAO(connection);
	    	results = cityAreasDAO.findAreas(city, email);
	    		
	    	connection.commit();
    	} catch(Exception e) {
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.rollback();
    			}
    		} catch (Exception e1) {
    			// Do nothing...
    		}
    	} finally {
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.close();
    			}
    		} catch (Exception e) {
    			// Do nothing...
    		} finally {
    			connection = null;
    		}
    	}
    	
    	return results;
    }
    
    public void updateAreaOptions() {
    	_areaList = loadAreaList();
	}
    
    public void updateArea() {
    	_feedback = null;
    	
    	Connection connection = null;
    	
    	try {
    		connection = ConnectionUtil.createConnection(
    				Constants.Services.JNDI_JDBC_APP, false);
    		
    		Bk2UsersDAO bk2UsersDAO = new Bk2UsersDAO(connection);
	    	bk2UsersDAO.update(_userEmail, _areaToUpdate, _newName);
	    		
	    	connection.commit();
	    	
	    	_feedback = FeedbackUtil.formatGeneralFeedback(
					Constants.AlertTypes.SUCCESS,
					"Confirmaci&oacute;n!",
					"El &aacute;rea <i><strong>" + _newName + "</strong></i> fue actualizada exitosamente.");
	    	
	    	_areaToDelete = null;
	    	_cityList = loadCityList();
	    	_areaList = loadAreaList();
	    	_name = null;
    	} catch(Exception e) {
    		_feedback = FeedbackUtil.formatGeneralFeedback(
					Constants.AlertTypes.ERROR,
					"Error!",
					"El &aacute;rea <i><strong>" + _areaToUpdate + "</strong></i> no pudo ser actualizada en este momento. Por favor intente mas tarde.");
    		
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.rollback();
    			}
    		} catch (Exception e1) {
    			// Do nothing...
    		}
    	} finally {
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.close();
    			}
    		} catch (Exception e) {
    			// Do nothing...
    		} finally {
    			connection = null;
    		}
    	}
    }
    
    public void deleteArea() {
    	_feedback = null;
    	
    	Connection connection = null;
    	
    	try {
    		connection = ConnectionUtil.createConnection(
    				Constants.Services.JNDI_JDBC_APP, false);
    		
    		Bk2UsersDAO bk2UsersDAO = new Bk2UsersDAO(connection);
	    	bk2UsersDAO.delete(_userEmail, _areaToDelete);
	    		
	    	connection.commit();
	    	
	    	_feedback = FeedbackUtil.formatGeneralFeedback(
					Constants.AlertTypes.SUCCESS,
					"Confirmaci&oacute;n!",
					"El &aacute;rea <i><strong>" + _areaToDelete + "</strong></i> fue removida exitosamente.");
	    	
	    	_areaToDelete = null;
	    	_cityList = loadCityList();
	    	_areaList = loadAreaList();
	    	_name = null;
    	} catch(Exception e) {
    		_feedback = FeedbackUtil.formatGeneralFeedback(
					Constants.AlertTypes.ERROR,
					"Error!",
					"El &aacute;rea <i><strong>" + _name + "</strong></i> no pudo ser removida en este momento. Por favor intente mas tarde.");
    		
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.rollback();
    			}
    		} catch (Exception e1) {
    			// Do nothing...
    		}
    	} finally {
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.close();
    			}
    		} catch (Exception e) {
    			// Do nothing...
    		} finally {
    			connection = null;
    		}
    	}
    }
    
    public void addArea() {
    	_feedback = null;
    	
    	Connection connection = null;
    	
    	try {
    		connection = ConnectionUtil.createConnection(
    				Constants.Services.JNDI_JDBC_APP, false);
    		
    		Bk2UsersTO bk2UsersTO = new Bk2UsersTO();
    		bk2UsersTO.setCity(_city);
    		bk2UsersTO.setArea(_area);
    		bk2UsersTO.setFkUserId(_userEmail);
    		
    		if(_name == null || _name.trim().isEmpty()) {
    			_name = _area + ", " + _city;
    		}
    		
    		bk2UsersTO.setName(_name);
	    		
    		Bk2UsersDAO bk2UsersDAO = new Bk2UsersDAO(connection);
	    	bk2UsersDAO.create(bk2UsersTO);
	    		
	    	connection.commit();
	    	
	    	_feedback = FeedbackUtil.formatGeneralFeedback(
					Constants.AlertTypes.SUCCESS,
					"Confirmaci&oacute;n!",
					"El &aacute;rea <i><strong>" + _name + "</strong></i> fue a&ntilde;adida exitosamente.");
	    	
	    	_cityList = loadCityList();
	    	_areaList = loadAreaList();
	    	_name = null;
    	} catch(Exception e) {
    		_feedback = FeedbackUtil.formatGeneralFeedback(
					Constants.AlertTypes.ERROR,
					"Error!",
					"El &aacute;rea <i><strong>" + _name + "</strong></i> no pudo ser a&ntilde;adida en este momento. Por favor intente mas tarde.");
    		
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.rollback();
    			}
    		} catch (Exception e1) {
    			// Do nothing...
    		}
    	} finally {
    		try {
    			if(connection != null && !connection.isClosed()) {
    				connection.close();
    			}
    		} catch (Exception e) {
    			// Do nothing...
    		} finally {
    			connection = null;
    		}
    	}
	}
}