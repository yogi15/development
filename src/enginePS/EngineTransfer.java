package enginePS;

import eventPS.EventPS;
import eventPS.EventPSDealData;

public class EngineTransfer implements  EnginePS {

	@Override
	public String engineName(String engineName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean process(EventPS event) {
		if(event  instanceof EventPSDealData) { 
			EventPSDealData dealData = (EventPSDealData) event;
			
			System.out.println("EngineTransfer "+ dealData.getTrade().getId() + " :: " + dealData.getTrade().getType()  + " :: " + dealData.getTrade().getQuantity());
			return true;
		}
		return false;
	}

}
