package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import be.demmel.protocol.ucp.O_6x_AbstractDataType;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_6x_AbstractDataTypeDeserializer implements UCPOperationDeserializer<O_6x_AbstractDataType> {

	@Override
	public O_6x_AbstractDataType deserialize(ByteBuf bb) {
		String oadc = Serialization.getString(bb);
		Character oton = Serialization.getCharacter(bb);
		Character onpi = Serialization.getCharacter(bb);
		Character styp = Serialization.getCharacter(bb);
		String pwdIra = Serialization.getString(bb);
		String pwd = IRA.iraToUnicode(pwdIra);
		String npwdIra = Serialization.getString(bb);
		String npwd = IRA.iraToUnicode(npwdIra);
		String vers = Serialization.getString(bb);
		String ladc = Serialization.getString(bb);
		Character lton = Serialization.getCharacter(bb);
		Character lnpi = Serialization.getCharacter(bb);
		String opid = Serialization.getString(bb);
		String res1 = Serialization.getString(bb);
		
		return new O_6x_AbstractDataType(oadc, oton, onpi, styp, pwd, npwd, vers, ladc, lton, lnpi, opid, res1);
	}
}
