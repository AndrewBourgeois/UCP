package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import be.demmel.protocol.ucp.O_01_CallInput;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_01_CallInputDeserializer implements UCPOperationDeserializer<O_01_CallInput> {

	@Override
	public O_01_CallInput deserialize(ByteBuf packetPDU) {
		String adc = Serialization.getString(packetPDU);
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

		return new O_01_CallInput(adc, oadc, ac, mt, nMsg, aMsg);
	}

}
