package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_58_ResponseDeleteMessage extends O_5x_AbstractDataType {

	public O_58_ResponseDeleteMessage(String adc, char mt, String amsg, Character mms, String hplmn) {
		// All the "null" values passed are not applicable for this PDU
		super(Null.check(adc,"AdC"), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, mt, null, amsg, null, null, mms, null, null, null, null, null, null, null, hplmn, null, null, null);
	}
}
