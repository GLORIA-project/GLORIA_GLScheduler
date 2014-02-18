package eu.gloria.rtc.op;

public enum ExtExecInterruptionState {
	
	INTERRUPTED,	//RTS within GLORIA control
	WAIT_TURN,		//RTS waiting to telescope for finishing the previous action.
					//When finished, GLORIA will take the control
	RESUMED			//RTS without GLORIA control

}
