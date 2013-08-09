package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import be.demmel.protocol.ucp.O_6x_AbstractDataType;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_6x_AbstractDataTypeSerializer implements UCPOperationSerializer<O_6x_AbstractDataType> {

	@Override
	public ByteBuf serialize(O_6x_AbstractDataType ucpOperation) {
		ByteBuf bb = Unpooled.buffer();

		Serialization.serializeString(bb, ucpOperation.getOadc());
		Serialization.serializeCharacter(bb, ucpOperation.getOton());
		Serialization.serializeCharacter(bb, ucpOperation.getOnpi());
		Serialization.serializeCharacter(bb, ucpOperation.getStyp());
		String pwdIra = IRA.unicodeToIra(ucpOperation.getPwd());
		Serialization.serializeString(bb, pwdIra);
		String npwdIra = IRA.unicodeToIra(ucpOperation.getNpwd());
		Serialization.serializeString(bb, npwdIra);
		Serialization.serializeString(bb, ucpOperation.getVers());
		Serialization.serializeString(bb, ucpOperation.getLadc());
		Serialization.serializeCharacter(bb, ucpOperation.getLton());
		Serialization.serializeCharacter(bb, ucpOperation.getLnpi());
		Serialization.serializeString(bb, ucpOperation.getOpid());
		Serialization.serializeString(bb, ucpOperation.getRes1());

		return bb;
	}

}
