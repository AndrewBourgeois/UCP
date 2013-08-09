package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import be.demmel.protocol.ucp.O_01_CallInput;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_01_CallInputSerializer implements UCPOperationSerializer<O_01_CallInput> {

	@Override
	public ByteBuf serialize(O_01_CallInput ucpOperation) {
		ByteBuf bb = Unpooled.buffer();

		Serialization.serializeString(bb, ucpOperation.getAdc());
		Serialization.serializeString(bb, ucpOperation.getOadc());
		Serialization.serializeString(bb, ucpOperation.getAc());

		char mt = ucpOperation.getMt();
		Serialization.serializeCharacter(bb, mt);

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
