package com.wovenware.aee.breakdown.reporting.bean;

/**
 * <i>Main Bean.</i>
 * 
 * Wovenware, Inc 2013
 * Created on June 06, 2013
 * @author Alberto Aresti, Nelson Perez
 */

import java.sql.Connection;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.wovenware.aee.breakdown.reporting.Constants;
import com.wovenware.aee.breakdown.reporting.db.dao.BksReportedDAO;
import com.wovenware.aee.breakdown.reporting.db.dao.to.BksReportedTO;
import com.wovenware.aee.breakdown.reporting.util.ConnectionUtil;

@ManagedBean
@SessionScoped
public class Main extends GenericBean {
	private static final long serialVersionUID = 1L;
	
	private String _list = null;
	
	// List
	public String getList() {
		_list = loadList();
		
		return _list;
	}
	
	public String loadList() {
		StringBuilder list = new StringBuilder();
		
		List<BksReportedTO> results = getBreakdownsReported(_userEmail);
		
		if(results != null) {
			for(BksReportedTO bksReportedTO : results) {
				list.append("<tr><td>");
				list.append("<div class=\"row-fluid item\">");
				list.append("<div class=\"span12\">");
				list.append("<h4><i class=\"icon-user icon-large\"></i>");
				list.append(bksReportedTO.getArea() + ",&nbsp;" + bksReportedTO.getCity());
				list.append("</h4>");
				list.append("<div class=\"description\">");
				list.append("<span class=\"label label-important\">");
				list.append(bksReportedTO.getStatus());
				list.append("</span>");
				list.append("<br/>");
				list.append(bksReportedTO.getRptdLastUpdateTs());
				list.append("</div></div></div>");
				list.append("</td></tr>");
			}
		}
		
		return list.toString();
	}
 
    public List<BksReportedTO> getBreakdownsReported (String email) {
    	List<BksReportedTO> results = null;
    	
    	Connection connection = null;
    	
    	try {
    		connection = ConnectionUtil.createConnection(
    				Constants.Services.JNDI_JDBC_APP, false);
	    		
    		BksReportedDAO bksReportedDAO = new BksReportedDAO(connection);
	    	results = bksReportedDAO.find(email);
	    		
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
}