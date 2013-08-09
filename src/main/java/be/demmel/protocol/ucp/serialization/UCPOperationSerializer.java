package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import be.demmel.protocol.ucp.UCPOperation;

public interface UCPOperationSerializer<E extends UCPOperation> {
	ByteBuf serialize(E ucpOperation);
}
