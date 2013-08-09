package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import be.demmel.protocol.ucp.UCPOperation;

public interface UCPOperationDeserializer<E extends UCPOperation> {
	E deserialize(ByteBuf bb);
}
