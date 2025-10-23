
package cinema;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cinema package. 
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

    private final static QName _GetNominations_QNAME = new QName("http://cinema/", "getNominations");
    private final static QName _GetNominationsResponse_QNAME = new QName("http://cinema/", "getNominationsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cinema
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetNominations }
     * 
     */
    public GetNominations createGetNominations() {
        return new GetNominations();
    }

    /**
     * Create an instance of {@link GetNominationsResponse }
     * 
     */
    public GetNominationsResponse createGetNominationsResponse() {
        return new GetNominationsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNominations }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetNominations }{@code >}
     */
    @XmlElementDecl(namespace = "http://cinema/", name = "getNominations")
    public JAXBElement<GetNominations> createGetNominations(GetNominations value) {
        return new JAXBElement<GetNominations>(_GetNominations_QNAME, GetNominations.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNominationsResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link GetNominationsResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://cinema/", name = "getNominationsResponse")
    public JAXBElement<GetNominationsResponse> createGetNominationsResponse(GetNominationsResponse value) {
        return new JAXBElement<GetNominationsResponse>(_GetNominationsResponse_QNAME, GetNominationsResponse.class, null, value);
    }

}
