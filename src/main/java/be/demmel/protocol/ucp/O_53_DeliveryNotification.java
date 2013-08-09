package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_53_DeliveryNotification extends O_5x_AbstractDataType {

	public O_53_DeliveryNotification(String adc, String oadc, String rpid, String scts, char dst, String rsn, String dscts, char mt, String nb, String amsg, String tmsg, Character mms,
			String hplmn, String xser) {
		// All the "null" values passed are not applicable for this PDU
		super(Null.check(adc,"AdC"), Null.check(oadc,"OAdC"), null, null, null, null, null, null, null, null, null, null, null, rpid, Null.check(scts,"SCTS"), dst, Null.check(rsn,"Rsn"), Null.check(dscts,"DSCTS"), mt, nb, amsg, null, tmsg, mms, null, null, null, null, null, null, null, hplmn, xser, null, null);
	}
}
