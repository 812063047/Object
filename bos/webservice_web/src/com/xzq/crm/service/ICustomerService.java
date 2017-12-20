
package com.xzq.crm.service;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ICustomerService", targetNamespace = "http://service.crm.xzq.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ICustomerService {


    /**
     * 
     * @return
     *     returns java.util.List<com.xzq.crm.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.crm.xzq.com/", className = "com.xzq.crm.service.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.crm.xzq.com/", className = "com.xzq.crm.service.FindAllResponse")
    public List<Customer> findAll();

}
