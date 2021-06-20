
package vbsp.ims.web.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ReceivesXmlFile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReceivesXmlFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="inbyte" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *         &lt;element name="strFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReceivesXmlFile", propOrder = {
    "inbyte",
    "strFileName"
})
public class ReceivesXmlFile {

    @XmlElementRef(name = "inbyte", type = JAXBElement.class, required = false)
    protected JAXBElement<byte[]> inbyte;
    protected String strFileName;

    /**
     * Gets the value of the inbyte property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public JAXBElement<byte[]> getInbyte() {
        return inbyte;
    }

    /**
     * Sets the value of the inbyte property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link byte[]}{@code >}
     *     
     */
    public void setInbyte(JAXBElement<byte[]> value) {
        this.inbyte = value;
    }

    /**
     * Gets the value of the strFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrFileName() {
        return strFileName;
    }

    /**
     * Sets the value of the strFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrFileName(String value) {
        this.strFileName = value;
    }

}
