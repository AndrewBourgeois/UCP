package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import be.demmel.protocol.ucp.O_31_Alert;
import be.demmel.protocol.ucp.util.Serialization;

public class O_31_AlertSerializer implements UCPOperationSerializer<O_31_Alert> {

	@Override
	public ByteBuf serialize(O_31_Alert ucpOperation) {
		ByteBuf bb = Unpooled.buffer();
		
		Serialization.serializeString(bb, ucpOperation.getAdc());
		Serialization.serializeString(bb, ucpOperation.getPid());
		
		return bb;
	}

}
