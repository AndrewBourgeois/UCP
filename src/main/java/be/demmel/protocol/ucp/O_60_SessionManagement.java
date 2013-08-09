package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_60_SessionManagement extends O_6x_AbstractDataType {

	public O_60_SessionManagement(String oadc, Character oton, Character onpi, char styp, String pwd, String npwd, String vers, String opid) {
		// All the "null" values passed are not applicable for this PDU
		super(Null.check(oadc,"OAdC"), oton, onpi, styp, Null.check(pwd,"PWD"), npwd, Null.check(vers,"VERS"), null, null, null, opid, null);
	}
}
