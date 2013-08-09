package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import be.demmel.protocol.ucp.O_5x_AbstractDataType;
import be.demmel.protocol.ucp.util.IRA;
import be.demmel.protocol.ucp.util.Serialization;

public class O_5x_AbstractDataTypeSerializer implements UCPOperationSerializer<O_5x_AbstractDataType> {

	@Override
	public ByteBuf serialize(O_5x_AbstractDataType ucpOperation) {
		ByteBuf bb = Unpooled.buffer();
		
		Serialization.serializeString(bb, ucpOperation.getAdc());
		Serialization.serializeString(bb, ucpOperation.getOadc());
		Serialization.serializeString(bb, ucpOperation.getAc());
		Serialization.serializeCharacter(bb, ucpOperation.getNrq());
		Serialization.serializeString(bb, ucpOperation.getNadc());
		Serialization.serializeCharacter(bb, ucpOperation.getNt());
		Serialization.serializeString(bb, ucpOperation.getNpid());
		Serialization.serializeCharacter(bb, ucpOperation.getLrq());
		Serialization.serializeString(bb, ucpOperation.getLrad());
		Serialization.serializeString(bb, ucpOperation.getLpid());
		Serialization.serializeCharacter(bb, ucpOperation.getDd());
		Serialization.serializeString(bb, ucpOperation.getDdt());
		Serialization.serializeString(bb, ucpOperation.getVp());
		Serialization.serializeString(bb, ucpOperation.getRpid());
		Serialization.serializeString(bb, ucpOperation.getScts());
		Serialization.serializeCharacter(bb, ucpOperation.getDst());
		Serialization.serializeString(bb, ucpOperation.getRsn());
		Serialization.serializeString(bb, ucpOperation.getDscts());
		Character mt = ucpOperation.getMt();
		Serialization.serializeCharacter(bb, ucpOperation.getMt());

		if (mt != null) {			
			Serialization.serializeString(bb, ucpOperation.getNb());
			
			if('2' == mt) {
				Serialization.serializeString(bb, ucpOperation.getNmsg());
			} else if('3' == mt) {
				String amsgIra = IRA.unicodeToIra(ucpOperation.getAmsg());
				Serialization.serializeString(bb, amsgIra);
			} else if('4' == mt) {
				String tmsgIra = IRA.unicodeToIra(ucpOperation.getTmsg());
				Serialization.serializeString(bb, tmsgIra);
			} else {
				throw new IllegalArgumentException("Illegal \"mt\" value: "+ mt);
			}
		} else {
			bb.writeByte('/');// empty nb
			bb.writeByte('/');// empty msg
		}
		
		Serialization.serializeCharacter(bb, ucpOperation.getMms());
		Serialization.serializeCharacter(bb, ucpOperation.getPr());
		Serialization.serializeCharacter(bb, ucpOperation.getDc());
		Serialization.serializeCharacter(bb, ucpOperation.getMcl());
		Serialization.serializeCharacter(bb, ucpOperation.getRpi());
		Serialization.serializeString(bb, ucpOperation.getCpg());
		Serialization.serializeCharacter(bb, ucpOperation.getRply());
		Serialization.serializeString(bb, ucpOperation.getOtoa());
		Serialization.serializeString(bb, ucpOperation.getHplmn());
		Serialization.serializeString(bb, ucpOperation.getXser());
		Serialization.serializeString(bb, ucpOperation.getRes4());
		Serialization.serializeString(bb, ucpOperation.getRes5());
		
		return bb;
	}

}
