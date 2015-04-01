/**
 * Copyright 2014 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package com.ibm.datapower.amt.soma;

import java.io.InputStream;

import org.apache.xmlbeans.XmlOptions;
import org.w3c.dom.Node;

import com.ibm.datapower.amt.Constants;
import com.ibm.datapower.amt.amp.DeviceContext;

/**
 * A list of high-level commands that a SOAPHelperImpl class should implement.
 * The purpose of this interface is to provide for the unique runtime platform
 * requirements of users of the IBM Appliance Management Toolkit. 
 */
public interface SOAPHelper {

	public static final String COPYRIGHT_2013 = Constants.COPYRIGHT_2013;
    /**
     * Creates an XmlOptions object that enables the XML outputted by XMLBeans
     * to be pretty-printed, and to have the correct namespace prefix for the
     * SOMA WSDL.
     * 
     * @return an XmlOptions object with pretty print enabled and the SOMA prefix
     *         defined.
     */
	public XmlOptions getOptions();
	
	/**
     * Given a requestDocument (as an StringBuffer), this method provides a
     * high-level API to wrap the SOAP envelope, create an SSL connection, send
     * the SOAP request, get the SOAP response, and return the response XML
     * (without the SOAP envelope)
     * 
     * @param device
     *            the remote device information contained in a DeviceContext
     *            object (username, password, hostname, and SOMA port number)
     * @param requestDocument
     *            a StringBuffer containing the XML request. It will be
     *            translated into an XML document for the SOAP request.
     * 
     * @return a String containing the XML document returned by the SOAP
     *         response.
     * @throws SOMAIOException
     *             an error occurred while communicating with the DataPower
     *             device.
     * @throws InvalidCredentialsException
     *             an invalid username/password pair was specified in the
     *             DeviceContext
     * @throws SOMAException
     *             an error occured while parsing the SOAP envelope returned
     *             from the device.
     */
	public Node call(DeviceContext device, StringBuffer requestDocument)
	throws SOMAIOException, SOMAException;
	
	/**
     * Given a requestDocument (as an combination of header bytes, an
     * InputStream containing the blob, and footer bytes), this method provides
     * a high-level API to wrap the SOAP envelope, create an SSL connection,
     * send the SOAP request, get the SOAP response, and return the response XML
     * (without the SOAP envelope).
     * <p>
     * The inputStream must represent base64 encoded bytes!!!
     * 
     * @param device
     *            the remote device information contained in a DeviceContext
     *            object (username, password, hostname, and SOMA port number)
     * @param header
     *            the bytes before the blob in the message
     * @param footer
     *            the bytes after the blob in the message
     * @param requestDocument
     *            an InputStream containing the XML request. It will be
     *            translated into an XML document for the SOAP request.
     *            This method will close the InputStream.
     * @return a Node containing the XML document returned by the SOAP response.
     * @throws SOMAIOException
     *             an error occurred while communicating with the DataPower
     *             device.
     * @throws InvalidCredentialsException
     *             an invalid username/password pair was specified in the
     *             DeviceContext
     * @throws SOMAException
     *             an error occured while parsing the SOAP envelope returned
     *             from the device.
     */
	public Node call(DeviceContext device, byte[] header, byte[] footer, 
			InputStream requestDocument) 
    throws SOMAIOException, SOMAException;
    
}
