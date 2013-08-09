package be.demmel.protocol.ucp;

import be.demmel.protocol.ucp.util.Null;

public class O_61_ProvisioningActions extends O_6x_AbstractDataType {

	public O_61_ProvisioningActions(String oadc, Character oton, Character onpi, char styp, String vers, String ladc,
			Character lton, Character lnpi, String opid) {
		// All the "null" values passed are not applicable for this PDU
		super(Null.check(oadc,"OAdC"), oton, onpi, styp, null, null, Null.check(vers,"VERS"), Null.check(ladc,"LAdC"), lton, lnpi, opid, null);
	}
}
