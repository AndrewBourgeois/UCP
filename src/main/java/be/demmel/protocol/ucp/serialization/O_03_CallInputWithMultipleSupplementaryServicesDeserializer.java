package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import be.demmel.protocol.ucp.O_03_CallInputWithMultipleSupplementaryServices;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_03_CallInputWithMultipleSupplementaryServicesDeserializer implements UCPOperationDeserializer<O_03_CallInputWithMultipleSupplementaryServices> {

	@Override
	public O_03_CallInputWithMultipleSupplementaryServices deserialize(ByteBuf packetPDU) {
		String rad = Serialization.getString(packetPDU);
		String oadc = Serialization.getString(packetPDU);
		String ac = Serialization.getString(packetPDU);
		String npl = Serialization.getString(packetPDU);
		String gas = Serialization.getString(packetPDU);
		Character rp = Serialization.getCharacter(packetPDU);
		Character pr = Serialization.getCharacter(packetPDU);
		String lpr = Serialization.getString(packetPDU);
		Character ur = Serialization.getCharacter(packetPDU);
		String lur = Serialization.getString(packetPDU);
		Character rc = Serialization.getCharacter(packetPDU);
		String lrc = Serialization.getString(packetPDU);
		Character dd = Serialization.getCharacter(packetPDU);
		String ddt = Serialization.getString(packetPDU);
		
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

		return new O_03_CallInputWithMultipleSupplementaryServices(rad, oadc, ac, npl, gas, rp, pr, lpr, ur, lur, rc, lrc, dd, ddt, mt, nMsg, aMsg);
	}

}
