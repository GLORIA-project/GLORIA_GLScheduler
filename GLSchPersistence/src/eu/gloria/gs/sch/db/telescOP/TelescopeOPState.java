package eu.gloria.gs.sch.db.telescOP;

public enum TelescopeOPState {

	ADVERTISED,
	OFFERED,
	CONFIRMED,
	CANCELLED,
	REALLOCATING,
	DONE,
	IDLE;	
	
	
	public static TelescopeOPState valueOf (int value){
		
		switch (value){
		case 0: return ADVERTISED;
		case 1: return OFFERED;
		case 2: return CONFIRMED;
		case 3: return CANCELLED;
		case 4: return REALLOCATING;
		case 5: return DONE;
		case 6: return IDLE;
		}
		
		return null;


	}
		
}

