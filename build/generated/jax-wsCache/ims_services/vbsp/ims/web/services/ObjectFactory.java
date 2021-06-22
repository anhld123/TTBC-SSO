
package vbsp.ims.web.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the vbsp.ims.web.services package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ReceivesXmlFile_QNAME = new QName("http://services.web.ims.vbsp/", "ReceivesXmlFile");
    private final static QName _ReceivesXmlFileResponse_QNAME = new QName("http://services.web.ims.vbsp/", "ReceivesXmlFileResponse");
    private final static QName _ReceivesXmlFileInbyte_QNAME = new QName("", "inbyte");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: vbsp.ims.web.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReceivesXmlFile }
     * 
     */
    public ReceivesXmlFile createReceivesXmlFile() {
        return new ReceivesXmlFile();
    }

    /**
     * Create an instance of {@link ReceivesXmlFileResponse }
     * 
     */
    public ReceivesXmlFileResponse createReceivesXmlFileResponse() {
        return new ReceivesXmlFileResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceivesXmlFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.ims.vbsp/", name = "ReceivesXmlFile")
    public JAXBElement<ReceivesXmlFile> createReceivesXmlFile(ReceivesXmlFile value) {
        return new JAXBElement<ReceivesXmlFile>(_ReceivesXmlFile_QNAME, ReceivesXmlFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReceivesXmlFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.web.ims.vbsp/", name = "ReceivesXmlFileResponse")
    public JAXBElement<ReceivesXmlFileResponse> createReceivesXmlFileResponse(ReceivesXmlFileResponse value) {
        return new JAXBElement<ReceivesXmlFileResponse>(_ReceivesXmlFileResponse_QNAME, ReceivesXmlFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "inbyte", scope = ReceivesXmlFile.class)
    public JAXBElement<byte[]> createReceivesXmlFileInbyte(byte[] value) {
        return new JAXBElement<byte[]>(_ReceivesXmlFileInbyte_QNAME, byte[].class, ReceivesXmlFile.class, ((byte[]) value));
    }

}
