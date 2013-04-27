package com.nwc.gcm;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.nwc.gcm.S.Parameter;


/**
 * Servlet implementation class SendMessageServlet
 */
@WebServlet("/send")
public class SendMessageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
    
    
    protected Sender getSender() {
    	return new Sender(S.API_KEY);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Sending msg");
		String regId = request.getParameter(Parameter.PARAMETER_REG_ID);
		String str="";
		//System.out.println("regID : "+regId);
		Message message = new Message.Builder().addData(S.Parameter.MESSAGE_DATA_KEY,"").build();
		Sender sender = getSender();
		Result result = sender.send(message, regId, 5);
		
		System.out.println("Canoninal registration id : "+result.getCanonicalRegistrationId());
		System.out.println("Sending Msg to "+regId);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
