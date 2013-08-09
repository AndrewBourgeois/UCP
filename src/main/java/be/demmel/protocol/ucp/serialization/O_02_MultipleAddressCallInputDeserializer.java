package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import be.demmel.protocol.ucp.O_02_MultipleAddressCallInput;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_02_MultipleAddressCallInputDeserializer implements UCPOperationDeserializer<O_02_MultipleAddressCallInput> {

	@Override
	public O_02_MultipleAddressCallInput deserialize(ByteBuf packetPDU) {
		String npl = Serialization.getString(packetPDU);
		String rads = Serialization.getString(packetPDU);
		String oadc = Serialization.getString(packetPDU);
		String ac = Serialization.getString(packetPDU);
		
		char mt = Serialization.getCharacter(packetPDU);
		String nMsg = null;
		String aMsg = null;
		
		if ('2' == mt) {
			nMsg = Serialization.getString(packetPDU);
		} else if ('3' == mt) {
			String amsgIra = Serialization.getString(packetPDU);
			aMsg = IRA.iraToUnicode(amsgIra);
		} else {
			throw new IllegalArgumentException("Illegal \"mt\" value: " + mt);
		}

		return new O_02_MultipleAddressCallInput(npl, rads, oadc, ac, mt, nMsg, aMsg);
	}

}
