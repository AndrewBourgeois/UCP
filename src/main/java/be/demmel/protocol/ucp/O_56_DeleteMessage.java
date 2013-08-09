package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_56_DeleteMessage extends O_5x_AbstractDataType {

	public O_56_DeleteMessage(String adc, String oadc, String ac, char mt, String amsg, String otoa, String hplmn) {
		// All the "null" values passed are not applicable for this PDU
		super(Null.check(adc,"AdC"), Null.check(oadc,"OAdC"), ac, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, mt, null, amsg, null, null, null, null, null, null, null, null, null, otoa, hplmn, null, null, null);
	}
}
