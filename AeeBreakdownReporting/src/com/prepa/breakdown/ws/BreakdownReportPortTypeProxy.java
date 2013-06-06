package com.prepa.breakdown.ws;

public class BreakdownReportPortTypeProxy implements com.prepa.breakdown.ws.BreakdownReportPortType {
  private String _endpoint = null;
  private com.prepa.breakdown.ws.BreakdownReportPortType breakdownReportPortType = null;
  
  public BreakdownReportPortTypeProxy() {
    _initBreakdownReportPortTypeProxy();
  }
  
  public BreakdownReportPortTypeProxy(String endpoint) {
    _endpoint = endpoint;
    _initBreakdownReportPortTypeProxy();
  }
  
  private void _initBreakdownReportPortTypeProxy() {
    try {
      breakdownReportPortType = (new com.prepa.breakdown.ws.BreakdownReportLocator()).getBreakdownReportHttpSoap11Endpoint();
      if (breakdownReportPortType != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)breakdownReportPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)breakdownReportPortType)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (breakdownReportPortType != null)
      ((javax.xml.rpc.Stub)breakdownReportPortType)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.prepa.breakdown.ws.BreakdownReportPortType getBreakdownReportPortType() {
    if (breakdownReportPortType == null)
      _initBreakdownReportPortTypeProxy();
    return breakdownReportPortType;
  }
  
  public com.prepa.breakdown.persistence.xsd.BreakdownArea[] getBreakdownsByTownOrCity(java.lang.String townOrCity) throws java.rmi.RemoteException{
    if (breakdownReportPortType == null)
      _initBreakdownReportPortTypeProxy();
    return breakdownReportPortType.getBreakdownsByTownOrCity(townOrCity);
  }
  
  public com.prepa.breakdown.persistence.xsd.BreakdownSummary[] getBreakdownsSummary() throws java.rmi.RemoteException{
    if (breakdownReportPortType == null)
      _initBreakdownReportPortTypeProxy();
    return breakdownReportPortType.getBreakdownsSummary();
  }
  
  
}