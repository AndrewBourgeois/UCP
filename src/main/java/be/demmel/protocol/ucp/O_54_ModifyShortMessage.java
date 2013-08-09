package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_54_ModifyShortMessage extends O_5x_AbstractDataType {

	public O_54_ModifyShortMessage(String adc, String oadc, String ac, Character nrq, String nadc, Character nt, String npid, Character lrq, String lrad, String lpid, Character dd, String ddt,
			String vp, String rpid, String scts, Character mt, String nmsg, String amsg, String nb, String tmsg, Character mcl, Character rpi, String otoa, String hplmn, String xser) {
		// All the "null" values passed are not applicable for this PDU
		super(Null.check(adc,"AdC"), Null.check(oadc,"OAdC"), ac, nrq, nadc, nt, npid, lrq, lrad, lpid, dd, ddt, vp, rpid, Null.check(scts,"SCTS"), null, null, null, mt, nmsg, amsg, nb, tmsg, null, null, null, mcl, rpi, null, null, otoa, hplmn, xser, null, null);
	}
}
