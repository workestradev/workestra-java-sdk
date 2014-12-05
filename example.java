

public class example {

    public static void main(String[] args) {

    	WorkestraSDK client=new WorkestraSDK();
		client.setApiKey("" /* Your key here */ );
		//or use basic authenticaiton
		client.setBasicAuth("","" /* Your email and password here */ );

		WorkestraHTTPResponse resp=client.listNotifications(new String[] {"count","20"} );
		System.out.println( resp.statusCode );
		System.out.println( resp.content );

    }

}