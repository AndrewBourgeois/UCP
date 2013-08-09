package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_57_ResponseInquiryMessage extends O_5x_AbstractDataType {

	public O_57_ResponseInquiryMessage(String adc, char mt, String amsg, String hplmn) {
		// All the "null" values passed are not applicable for this PDU
		super(Null.check(adc,"AdC"), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, mt, null, amsg, null, null, null, null, null, null, null, null, null, null, hplmn, null, null, null);
	}
}
