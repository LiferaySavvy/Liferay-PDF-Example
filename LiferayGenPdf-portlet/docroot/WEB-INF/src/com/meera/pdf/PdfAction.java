package com.meera.pdf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

import javax.portlet.PortletException;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.liferay.portal.kernel.servlet.HttpHeaders;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class PdfAction
 */
public class PdfAction extends MVCPortlet {
 
	
	public void serveResource(ResourceRequest request, ResourceResponse response)
			throws PortletException, IOException {
		response.setContentType("application/pdf");
		response.setProperty(HttpHeaders.CONTENT_DISPOSITION,"attachement;filename=test");
		try {
			StringBuffer buf = new StringBuffer();
			buf.append("<html>");
			buf.append("<body>");
			buf.append("<table width='720px' border='0' ><tr><td align='center'><b>This is Test</b></td></tr></table>");
			buf.append("<table  width='720px' border='0'><tr><td><b>Organization Name:Test</b></td></tr></table>");
			buf.append("<table align='center' border='1'>");
			buf.append("<tr><td width='400px'>Name:</td><td width='400px'>Meera Prince</td></tr>");   
			buf.append("<tr><td width='400px'>Location:</td><td width='400px'>Hong Kong</td></tr>");  
			buf.append("<tr><td width='400px'>Email:</td><td width='400px'>meera.success@gmail.com</td></tr>");  
			buf.append("<tr><td width='400px'>Availeble Time:</td><td width='400px'>You can knock door any time.....</td></tr>");  
			buf.append("</table>");
			buf.append("</body>");
			buf.append("</html>");
			byte[] bytes = buf.toString().getBytes();
		   InputStream inputStream = new ByteArrayInputStream(bytes);
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(inputStream);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(doc, null);
			renderer.layout();
			OutputStream os = response.getPortletOutputStream();
			renderer.createPDF(os);
			
			//renderer.createPDF(os);
			os.close();
			} catch (Exception ex) {
			ex.printStackTrace();
			}

	}
}
