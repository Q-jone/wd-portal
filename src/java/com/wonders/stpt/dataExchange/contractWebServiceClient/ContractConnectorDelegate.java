package com.wonders.stpt.dataExchange.contractWebServiceClient;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * 
 */
@WebService(name = "ContractConnectorDelegate", targetNamespace = "http://contract.webservice.wonders.com/")
public interface ContractConnectorDelegate {

	/**
	 * 
	 * @param arg1
	 * @param arg0
	 * @return returns java.lang.String
	 */
	@WebMethod
	@WebResult(targetNamespace = "")
	@RequestWrapper(localName = "saveContract", targetNamespace = "http://contract.webservice.wonders.com/", className = "contract.SaveContract")
	@ResponseWrapper(localName = "saveContractResponse", targetNamespace = "http://contract.webservice.wonders.com/", className = "contract.SaveContractResponse")
	public String saveContract(
			@WebParam(name = "arg0", targetNamespace = "") String arg0,
			@WebParam(name = "arg1", targetNamespace = "") String arg1);

}
