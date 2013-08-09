package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import be.demmel.protocol.ucp.O_03_CallInputWithMultipleSupplementaryServices;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_03_CallInputWithMultipleSupplementaryServicesSerializer implements UCPOperationSerializer<O_03_CallInputWithMultipleSupplementaryServices> {

	@Override
	public ByteBuf serialize(O_03_CallInputWithMultipleSupplementaryServices ucpOperation) {
		ByteBuf bb = Unpooled.buffer();
		
		Serialization.serializeString(bb, ucpOperation.getRad());
		Serialization.serializeString(bb, ucpOperation.getOadc());
		Serialization.serializeString(bb, ucpOperation.getAc());
		Serialization.serializeString(bb, ucpOperation.getNpl());
		Serialization.serializeString(bb, ucpOperation.getGas());
		Serialization.serializeCharacter(bb, ucpOperation.getRp());
		Serialization.serializeCharacter(bb, ucpOperation.getPr());
		Serialization.serializeString(bb, ucpOperation.getLpr());
		Serialization.serializeCharacter(bb, ucpOperation.getUr());
		Serialization.serializeString(bb, ucpOperation.getLur());
		Serialization.serializeCharacter(bb, ucpOperation.getRc());
		Serialization.serializeString(bb, ucpOperation.getLrc());
		Serialization.serializeCharacter(bb, ucpOperation.getDd());
		Serialization.serializeString(bb, ucpOperation.getDdt());

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
