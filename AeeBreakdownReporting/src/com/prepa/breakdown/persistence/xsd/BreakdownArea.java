/**
 * BreakdownArea.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.prepa.breakdown.persistence.xsd;

public class BreakdownArea  implements java.io.Serializable {
    private java.lang.String r2Area;

    private java.lang.String r3Status;

    private java.lang.String r1TownOrCity;

    private java.lang.String r4LastUpdate;

    public BreakdownArea() {
    }

    public BreakdownArea(
           java.lang.String r2Area,
           java.lang.String r3Status,
           java.lang.String r1TownOrCity,
           java.lang.String r4LastUpdate) {
           this.r2Area = r2Area;
           this.r3Status = r3Status;
           this.r1TownOrCity = r1TownOrCity;
           this.r4LastUpdate = r4LastUpdate;
    }


    /**
     * Gets the r2Area value for this BreakdownArea.
     * 
     * @return r2Area
     */
    public java.lang.String getR2Area() {
        return r2Area;
    }


    /**
     * Sets the r2Area value for this BreakdownArea.
     * 
     * @param r2Area
     */
    public void setR2Area(java.lang.String r2Area) {
        this.r2Area = r2Area;
    }


    /**
     * Gets the r3Status value for this BreakdownArea.
     * 
     * @return r3Status
     */
    public java.lang.String getR3Status() {
        return r3Status;
    }


    /**
     * Sets the r3Status value for this BreakdownArea.
     * 
     * @param r3Status
     */
    public void setR3Status(java.lang.String r3Status) {
        this.r3Status = r3Status;
    }


    /**
     * Gets the r1TownOrCity value for this BreakdownArea.
     * 
     * @return r1TownOrCity
     */
    public java.lang.String getR1TownOrCity() {
        return r1TownOrCity;
    }


    /**
     * Sets the r1TownOrCity value for this BreakdownArea.
     * 
     * @param r1TownOrCity
     */
    public void setR1TownOrCity(java.lang.String r1TownOrCity) {
        this.r1TownOrCity = r1TownOrCity;
    }


    /**
     * Gets the r4LastUpdate value for this BreakdownArea.
     * 
     * @return r4LastUpdate
     */
    public java.lang.String getR4LastUpdate() {
        return r4LastUpdate;
    }


    /**
     * Sets the r4LastUpdate value for this BreakdownArea.
     * 
     * @param r4LastUpdate
     */
    public void setR4LastUpdate(java.lang.String r4LastUpdate) {
        this.r4LastUpdate = r4LastUpdate;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof BreakdownArea)) return false;
        BreakdownArea other = (BreakdownArea) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.r2Area==null && other.getR2Area()==null) || 
             (this.r2Area!=null &&
              this.r2Area.equals(other.getR2Area()))) &&
            ((this.r3Status==null && other.getR3Status()==null) || 
             (this.r3Status!=null &&
              this.r3Status.equals(other.getR3Status()))) &&
            ((this.r1TownOrCity==null && other.getR1TownOrCity()==null) || 
             (this.r1TownOrCity!=null &&
              this.r1TownOrCity.equals(other.getR1TownOrCity()))) &&
            ((this.r4LastUpdate==null && other.getR4LastUpdate()==null) || 
             (this.r4LastUpdate!=null &&
              this.r4LastUpdate.equals(other.getR4LastUpdate())));
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
        if (getR2Area() != null) {
            _hashCode += getR2Area().hashCode();
        }
        if (getR3Status() != null) {
            _hashCode += getR3Status().hashCode();
        }
        if (getR1TownOrCity() != null) {
            _hashCode += getR1TownOrCity().hashCode();
        }
        if (getR4LastUpdate() != null) {
            _hashCode += getR4LastUpdate().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(BreakdownArea.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://persistence.breakdown.prepa.com/xsd", "BreakdownArea"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r2Area");
        elemField.setXmlName(new javax.xml.namespace.QName("http://persistence.breakdown.prepa.com/xsd", "r2Area"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r3Status");
        elemField.setXmlName(new javax.xml.namespace.QName("http://persistence.breakdown.prepa.com/xsd", "r3Status"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r1TownOrCity");
        elemField.setXmlName(new javax.xml.namespace.QName("http://persistence.breakdown.prepa.com/xsd", "r1TownOrCity"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("r4LastUpdate");
        elemField.setXmlName(new javax.xml.namespace.QName("http://persistence.breakdown.prepa.com/xsd", "r4LastUpdate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(true);
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
