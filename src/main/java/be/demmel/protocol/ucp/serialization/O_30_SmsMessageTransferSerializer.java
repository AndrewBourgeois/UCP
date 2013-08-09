package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import be.demmel.protocol.ucp.O_30_SmsMessageTransfer;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_30_SmsMessageTransferSerializer implements UCPOperationSerializer<O_30_SmsMessageTransfer> {

	@Override
	public ByteBuf serialize(O_30_SmsMessageTransfer ucpOperation) {
		ByteBuf bb = Unpooled.buffer();
		
		Serialization.serializeString(bb, ucpOperation.getAdc());
		Serialization.serializeString(bb, ucpOperation.getOadc());
		Serialization.serializeString(bb, ucpOperation.getAc());
		Serialization.serializeCharacter(bb, ucpOperation.getNrq());
		Serialization.serializeString(bb, ucpOperation.getNad());
		Serialization.serializeString(bb, ucpOperation.getNpid());
		Serialization.serializeCharacter(bb, ucpOperation.getDd());
		Serialization.serializeString(bb, ucpOperation.getDdt());
		Serialization.serializeString(bb, ucpOperation.getVp());
		
		String amsgIra = IRA.unicodeToIra(ucpOperation.getAmsg());
		Serialization.serializeString(bb, amsgIra);
		
		return bb;
	}

}
