/**
 * BreakdownReportLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.prepa.breakdown.ws;

public class BreakdownReportLocator extends org.apache.axis.client.Service implements com.prepa.breakdown.ws.BreakdownReport {

    public BreakdownReportLocator() {
    }


    public BreakdownReportLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public BreakdownReportLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for BreakdownReportHttpSoap11Endpoint
    private java.lang.String BreakdownReportHttpSoap11Endpoint_address = "http://wss.prepa.com/services/BreakdownReport.BreakdownReportHttpSoap11Endpoint/";

    public java.lang.String getBreakdownReportHttpSoap11EndpointAddress() {
        return BreakdownReportHttpSoap11Endpoint_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String BreakdownReportHttpSoap11EndpointWSDDServiceName = "BreakdownReportHttpSoap11Endpoint";

    public java.lang.String getBreakdownReportHttpSoap11EndpointWSDDServiceName() {
        return BreakdownReportHttpSoap11EndpointWSDDServiceName;
    }

    public void setBreakdownReportHttpSoap11EndpointWSDDServiceName(java.lang.String name) {
        BreakdownReportHttpSoap11EndpointWSDDServiceName = name;
    }

    public com.prepa.breakdown.ws.BreakdownReportPortType getBreakdownReportHttpSoap11Endpoint() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(BreakdownReportHttpSoap11Endpoint_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getBreakdownReportHttpSoap11Endpoint(endpoint);
    }

    public com.prepa.breakdown.ws.BreakdownReportPortType getBreakdownReportHttpSoap11Endpoint(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.prepa.breakdown.ws.BreakdownReportSoap11BindingStub _stub = new com.prepa.breakdown.ws.BreakdownReportSoap11BindingStub(portAddress, this);
            _stub.setPortName(getBreakdownReportHttpSoap11EndpointWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setBreakdownReportHttpSoap11EndpointEndpointAddress(java.lang.String address) {
        BreakdownReportHttpSoap11Endpoint_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.prepa.breakdown.ws.BreakdownReportPortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.prepa.breakdown.ws.BreakdownReportSoap11BindingStub _stub = new com.prepa.breakdown.ws.BreakdownReportSoap11BindingStub(new java.net.URL(BreakdownReportHttpSoap11Endpoint_address), this);
                _stub.setPortName(getBreakdownReportHttpSoap11EndpointWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("BreakdownReportHttpSoap11Endpoint".equals(inputPortName)) {
            return getBreakdownReportHttpSoap11Endpoint();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.breakdown.prepa.com", "BreakdownReport");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.breakdown.prepa.com", "BreakdownReportHttpSoap11Endpoint"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("BreakdownReportHttpSoap11Endpoint".equals(portName)) {
            setBreakdownReportHttpSoap11EndpointEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
