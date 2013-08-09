package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import be.demmel.protocol.ucp.O_02_MultipleAddressCallInput;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_02_MultipleAddressCallInputSerializer implements UCPOperationSerializer<O_02_MultipleAddressCallInput> {

	@Override
	public ByteBuf serialize(O_02_MultipleAddressCallInput ucpOperation) {
		ByteBuf bb = Unpooled.buffer();
		
		Serialization.serializeString(bb, ucpOperation.getNpl());
		Serialization.serializeString(bb, ucpOperation.getRads());
		Serialization.serializeString(bb, ucpOperation.getOadc());
		Serialization.serializeString(bb, ucpOperation.getAc());

		char mt = ucpOperation.getMt();
		Serialization.serializeCharacter(bb, ucpOperation.getMt());

		if ('2' == mt) {
			Serialization.serializeString(bb, ucpOperation.getnMsg());
		} else if ('3' == mt) {
			String amsgIra = IRA.unicodeToIra(ucpOperation.getaMsg());
			Serialization.serializeString(bb, amsgIra);
		} else {
			throw new IllegalArgumentException("Illegal \"mt\" value: " + mt);
		}

		return bb;
	}

}
