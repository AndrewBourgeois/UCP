package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_51_SubmitShortMessage extends O_5x_AbstractDataType {

	public O_51_SubmitShortMessage(String adc, String oadc, String ac, Character nrq, String nadc, Character nt, String npid, Character lrq, String lrad, String lpid, Character dd, String ddt,
			String vp, String rpid, char mt, String nmsg, String amsg, String nb, String tmsg, Character mms,
			Character pr, Character mcl, Character rpi, String otoa, String xser) {
		// All the "null" values passed are not applicable for this PDU
		super(Null.check(adc,"AdC"), Null.check(oadc,"OAdC"), ac, nrq, nadc, nt, npid, lrq, lrad, lpid, dd, ddt, vp, rpid, null, null, null, null, mt, nmsg, amsg, nb, tmsg, mms, pr, null, mcl, rpi, null, null,
				otoa, null, xser, null, null);
	}
}
