package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import be.demmel.protocol.ucp.O_30_SmsMessageTransfer;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_30_SmsMessageTransferDeserializer implements UCPOperationDeserializer<O_30_SmsMessageTransfer> {

	@Override
	public O_30_SmsMessageTransfer deserialize(ByteBuf packetPDU) {
		String adc = Serialization.getString(packetPDU);
		String oadc = Serialization.getString(packetPDU);
		String ac = Serialization.getString(packetPDU);
		Character nrq = Serialization.getCharacter(packetPDU);
		String nad = Serialization.getString(packetPDU);
		String npid = Serialization.getString(packetPDU);
		Character dd = Serialization.getCharacter(packetPDU);
		String ddt = Serialization.getString(packetPDU);
		String vp = Serialization.getString(packetPDU);
		String amsgIra = Serialization.getString(packetPDU);
		String amsg = IRA.iraToUnicode(amsgIra);
		
		return new O_30_SmsMessageTransfer(adc, oadc, ac, nrq, nad, npid, dd, ddt, vp, amsg);
	}

}
