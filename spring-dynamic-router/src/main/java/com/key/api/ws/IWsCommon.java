package com.key.api.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;


/**
 * 
 * @author DSF
 * 
 * @version v0.1
 * 
 * Created on 2014-04-17
 * 
 * Revision History:
 * Date   		Reviser		Description
 * ----   		-------   	----------------------------------------------------
 * 
 * Description: 该接口为一些不能改变得旧接口准备，可以直接定义方法
 * 
 */
@BindingType(value=SOAPBinding.SOAP12HTTP_BINDING)
@WebService(name = "API_WebService", targetNamespace = "http://tempuri.org/")
// @Path是restful的注解.作为拦截路径的.请求会先走CxfConfig的类address路径再走此处.
// 如果想要写多个接口服务类的话可以把CxfConfig中的address设置为'/',此处设置为@path('/url1')
// 另一个类设置为@path('/url2')等多个不同路径即可.
@Path("/")
public interface IWsCommon {
	
	
	@WebMethod(operationName="Add_DataSource",exclude=false,action="http://tempuri.org/Add_DataSource")
	// @POST,@Path是restful的注解.
	@POST
	@Path("Add_DataSource/")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public String Add_DataSource(@WebParam(name ="JsonString") String jsonStr);

	@WebMethod(operationName="Delete_DataSource",exclude=false,action="http://tempuri.org/Delete_DataSource")
	@POST
	@Path("/Delete_DataSource/")
	@Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public String Delete_DataSource(@WebParam(name ="JsonString") String jsonStr);
	
	
	
	
	
}