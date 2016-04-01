package enginePS;

import eventPS.EventPS;
import eventPS.EventPSDealData;

public class EnginePSDealReader implements EnginePS {
    public static String engineName = "DealReader";
	@Override
	public String engineName(String engineName) {
		// TODO Auto-generated method stub
		return engineName;
	}

	@Override
	public boolean process(EventPS event) {
		// TODO Auto-generated method stub
		if(event  instanceof EventPSDealData) { 
			EventPSDealData dealData = (EventPSDealData) event;
			System.out.println(" From Deal Reader Engine "+ dealData.getId());
			System.out.println(" From Deal Reader Engine "+ dealData.getValue());
			System.out.println(dealData.getTrade().getId());
			return true;
		}
		return false;
	}

}
