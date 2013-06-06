/**
 * BreakdownSummary.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.prepa.breakdown.persistence.xsd;

public class BreakdownSummary  implements java.io.Serializable {
    private java.lang.String r1TownOrCity;

    private java.lang.Integer r2TotalBreakdowns;

    public BreakdownSummary() {
    }

    public BreakdownSummary(
           java.lang.String r1TownOrCity,
           java.lang.Integer r2TotalBreakdowns) {
           this.r1TownOrCity = r1TownOrCity;
           this.r2TotalBreakdowns = r2TotalBreakdowns;
    }


    /**
     * Gets the r1TownOrCity value for this BreakdownSummary.
     * 
     * @return r1TownOrCity
     */
    public java.lang.String getR1TownOrCity() {
        return r1TownOrCity;
    }


    /**
     * Sets the r1TownOrCity value for this BreakdownSummary.
     * 
     * @param r1TownOrCity
     */
    public void setR1TownOrCity(java.lang.String r1TownOrCity) {
        this.r1TownOrCity = r1TownOrCity;
    }


    /**
     * Gets the r2TotalBreakdowns value for this BreakdownSummary.
     * 
     * @return r2TotalBreakdowns
     */
    public java.lang.Integer getR2TotalBreakdowns() {
        return r2TotalBreakdowns;
    }


    /**
     * Sets the r2TotalBreakdowns value for this BreakdownSummary.
     * 
     * @param r2TotalBreakdowns
     */
    public void setR2TotalBreakdowns(java.lang.Integer r2TotalBreakdowns) {
        this.r2TotalBreakdowns = r2TotalBreakdowns;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BreakdownSummary)) return false;
        BreakdownSummary other = (BreakdownSummary) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.r1TownOrCity==null && other.getR1TownOrCity()==null) || 
             (this.r1TownOrCity!=null &&
              this.r1TownOrCity.equals(other.getR1TownOrCity()))) &&
            ((this.r2TotalBreakdowns==null && other.getR2TotalBreakdowns()==null) || 
             (this.r2TotalBreakdowns!=null &&
              this.r2TotalBreakdowns.equals(other.getR2TotalBreakdowns())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getR1TownOrCity() != null) {
            _hashCode += getR1TownOrCity().hashCode();
        }
        if (getR2TotalBreakdowns() != null) {
            _hashCode += getR2TotalBreakdowns().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BreakdownSummary.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://persistence.breakdown.prepa.com/xsd", "BreakdownSummary"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r1TownOrCity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://persistence.breakdown.prepa.com/xsd", "r1TownOrCity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r2TotalBreakdowns");
        elemField.setXmlName(new javax.xml.namespace.QName("http://persistence.breakdown.prepa.com/xsd", "r2TotalBreakdowns"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
