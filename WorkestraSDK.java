
/*

Copyright (c) 2014, Workestra LLC
All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted provided that the following conditions are met:

* Redistributions of source code must retain the above copyright notice, this
  list of conditions and the following disclaimer.

* Redistributions in binary form must reproduce the above copyright notice,
  this list of conditions and the following disclaimer in the documentation
  and/or other materials provided with the distribution.

* Neither the name of Workestra nor the names of its contributors may be used
  to endorse or promote products derived from this software without specific
  prior written permission.


THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

*/

import java.io.*;
import javax.net.ssl.*;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.security.NoSuchAlgorithmException;
import java.security.KeyManagementException;
import sun.misc.BASE64Encoder;


public class WorkestraSDK {
	protected WorkestraHTTPClient httpHandler;
	protected String baseUrl="https://www.workestra.co/api/v1";


	WorkestraSDK() {
		httpHandler=new WorkestraHTTPClient();
	}

	WorkestraSDK(WorkestraHTTPClient http) {
		httpHandler=http;
	}

	void setApiKey(String key){
		httpHandler.setBasicAuth(key, "w");
    	}

    void setBasicAuth(String username, String password) {
		httpHandler.setBasicAuth(username, password);
	}

	private static String implode(String glue, String[] arr) {
    		StringBuilder sb = new StringBuilder();
		if (arr.length > 0) {
			sb.append(arr[0]);
			for (int i=1; i<arr.length; i++) {
				sb.append(glue);
				sb.append(arr[i]);
    			}
		}
		return sb.toString();
	}

	private static String prepareKeyValues(HashMap<String,String> values) {
                StringBuilder xml=new StringBuilder();
		Set<String> keys=values.keySet();
		for(String str : keys) {
			xml.append("<field id=\"").append(str).append("\">").append(values.get(str)).append("</field>");
		}
                return xml.toString();
	}

	private static String encodeHTML(String s) {
    		StringBuffer out = new StringBuffer();
		for(int i=0; i<s.length(); i++) {           
			char c=s.charAt(i);
			switch(c) {
				case '"': out.append("&quot;"); break;
				case '<': out.append("&lt;");break;
				case '>': out.append("&gt;");break;
				default: out.append(c); break;
			}
		}
    		return out.toString();
	}

	WorkestraHTTPResponse listNotifications(String[] fields) {
		WorkestraHTTPRequest request=new WorkestraHTTPRequest();
		request.method="GET";
		request.url=baseUrl + "/notifications";
		return httpHandler.sendRequest( request );
	}




	public static void main(String arguments[]) {

		WorkestraSDK client=new WorkestraSDK();
		client.setApiKey("{Your-API-Key}");
		
		//or use basic authenticaiton
		client.setBasicAuth("{Email}","{Password}");

		WorkestraHTTPResponse resp=client.listNotifications(new String[] {"count","20"} );
		System.out.println( resp.statusCode );
		System.out.println( resp.content );


	}

}

