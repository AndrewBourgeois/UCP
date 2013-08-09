package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_52_DeliveryShortMessage extends O_5x_AbstractDataType {

	public O_52_DeliveryShortMessage(String adc, String oadc, String rpid, String scts, char mt, String nmsg, String amsg, String tmsg, Character mms,
			Character dc, Character mcl, Character rpi, String hplmn, String xser) {
		// All the "null" values passed are not applicable for this PDU
		super(Null.check(adc,"AdC"), Null.check(oadc,"OAdC"), null, null, null, null, null, null, null, null, null, null, null, rpid, Null.check(scts,"SCTS"), null, null, null, mt, nmsg, amsg, null, tmsg, mms, null, dc, mcl, rpi, null, null, null, hplmn, xser, null, null);
	}
}
