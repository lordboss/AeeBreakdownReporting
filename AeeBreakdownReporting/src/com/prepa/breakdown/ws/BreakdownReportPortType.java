/**
 * BreakdownReportPortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.prepa.breakdown.ws;

public interface BreakdownReportPortType extends java.rmi.Remote {
    public com.prepa.breakdown.persistence.xsd.BreakdownArea[] getBreakdownsByTownOrCity(java.lang.String townOrCity) throws java.rmi.RemoteException;
    public com.prepa.breakdown.persistence.xsd.BreakdownSummary[] getBreakdownsSummary() throws java.rmi.RemoteException;
}
