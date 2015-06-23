package com.wonders.stpt.dataExchange.contractWebServiceClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the contract package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _SaveContractResponse_QNAME = new QName(
			"http://contract.webservice.wonders.com/", "saveContractResponse");
	private final static QName _SaveContract_QNAME = new QName(
			"http://contract.webservice.wonders.com/", "saveContract");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package: contract
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link SaveContract }
	 * 
	 */
	public SaveContract createSaveContract() {
		return new SaveContract();
	}

	/**
	 * Create an instance of {@link SaveContractResponse }
	 * 
	 */
	public SaveContractResponse createSaveContractResponse() {
		return new SaveContractResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link SaveContractResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://contract.webservice.wonders.com/", name = "saveContractResponse")
	public JAXBElement<SaveContractResponse> createSaveContractResponse(
			SaveContractResponse value) {
		return new JAXBElement<SaveContractResponse>(
				_SaveContractResponse_QNAME, SaveContractResponse.class, null,
				value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link SaveContract }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://contract.webservice.wonders.com/", name = "saveContract")
	public JAXBElement<SaveContract> createSaveContract(SaveContract value) {
		return new JAXBElement<SaveContract>(_SaveContract_QNAME,
				SaveContract.class, null, value);
	}

}
