workestra-java-sdk
============

A Java SDK library for the [Workestra API](https://www.workestra.co/developers/docs)

Quick Start
===========
You will need an API key to get started. You can find instructions on obtaining an API key [here](https://www.workestra.co/developers/docs#authentication)

Once you have that, the following code will get the latest notifications (as long as your user is able to access those notifications)

````


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


````
After that, you may want to explore the stream [api](https://www.workestra.co/developers/docs#sream), or just look through the wrapper code.
