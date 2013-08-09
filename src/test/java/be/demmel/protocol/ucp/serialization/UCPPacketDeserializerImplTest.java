package be.demmel.protocol.ucp.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.util.HashMap;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

import be.demmel.protocol.ucp.O_31_Alert;
import be.demmel.protocol.ucp.O_5x_AbstractDataType;
import be.demmel.protocol.ucp.O_6x_AbstractDataType;
import be.demmel.protocol.ucp.PositiveResponse;
import be.demmel.protocol.ucp.PositiveResponseWithMvp;
import be.demmel.protocol.ucp.UCPOperation;
import be.demmel.protocol.ucp.UCPOperationHeader;
import be.demmel.protocol.ucp.UCPOperationType;
import be.demmel.protocol.ucp.UCPPacket;

public class UCPPacketDeserializerImplTest {
	private static final UCPPacketDeserializerImpl PACKET_DESERIALIZER;

	static {
		O_31_AlertDeserializer o31Deserializer = new O_31_AlertDeserializer();
		O_5x_AbstractDataTypeDeserializer o5xDeserializer = new O_5x_AbstractDataTypeDeserializer();
		O_6x_AbstractDataTypeDeserializer o6xDeserializer = new O_6x_AbstractDataTypeDeserializer();
		O_01_CallInputDeserializer o01Deserializer = new O_01_CallInputDeserializer();
		O_02_MultipleAddressCallInputDeserializer o02Deserializer = new O_02_MultipleAddressCallInputDeserializer();
		O_03_CallInputWithMultipleSupplementaryServicesDeserializer o03Deserializer = new O_03_CallInputWithMultipleSupplementaryServicesDeserializer();
		O_30_SmsMessageTransferDeserializer o30Deserializer = new O_30_SmsMessageTransferDeserializer();
		Map<UCPOperationType, UCPOperationDeserializer> operationDeserializers = new HashMap<>();
		operationDeserializers.put(UCPOperationType.ALERT, o31Deserializer);
		operationDeserializers.put(UCPOperationType.SESSION_MANAGEMENT, o6xDeserializer);
		operationDeserializers.put(UCPOperationType.LIST_MANAGEMENT, o6xDeserializer);
		operationDeserializers.put(UCPOperationType.SUBMIT_SHORT_MESSAGE, o5xDeserializer);
		operationDeserializers.put(UCPOperationType.MODIFY_MESSAGE, o5xDeserializer);
		operationDeserializers.put(UCPOperationType.INQUIRY_MESSAGE, o5xDeserializer);
		operationDeserializers.put(UCPOperationType.DELETE_MESSAGE, o5xDeserializer);
		operationDeserializers.put(UCPOperationType.DELIVER_SHORT_MESSAGE, o5xDeserializer);
		operationDeserializers.put(UCPOperationType.DELIVER_NOTIFICATION, o5xDeserializer);
		operationDeserializers.put(UCPOperationType.RESPONSE_INQUIRY_MESSAGE, o5xDeserializer);
		operationDeserializers.put(UCPOperationType.RESPONSE_DELETE_MESSAGE, o5xDeserializer);
		operationDeserializers.put(UCPOperationType.CALL_INPUT, o01Deserializer);
		operationDeserializers.put(UCPOperationType.MULTIPLE_ADDRESS_CALL_INPUT, o02Deserializer);
		operationDeserializers.put(UCPOperationType.CALL_INPUT_WITH_MULTIPLE_SUPPLEMENTARY_SERVICES, o03Deserializer);
		operationDeserializers.put(UCPOperationType.SMS_MESSAGE_TRANSFER, o30Deserializer);

		PACKET_DESERIALIZER = new UCPPacketDeserializerImpl(operationDeserializers);
	}

	@Test
	public void alertDeserializerTest() {
		ByteBuf inputBytes = toByteBuf("0230302f30303032362f4f2f33312f373130302f303533392f424503");
		UCPPacket deseralizedPacket = PACKET_DESERIALIZER.deserialize(inputBytes);
				
		UCPOperationHeader header = deseralizedPacket.getHeader();
		int trn = header.getTrn();
		Assert.assertEquals(0, trn);
		UCPOperationHeader.Type type = header.getType();
		Assert.assertEquals(UCPOperationHeader.Type.OPERATION, type);
		UCPOperationType operationType = header.getOperation();
		Assert.assertEquals(UCPOperationType.ALERT, operationType);
	
		UCPOperation operation = deseralizedPacket.getData();
		Assert.assertTrue(operation instanceof O_31_Alert);
		O_31_Alert alert = (O_31_Alert) operation;
		
		Assert.assertEquals("7100", alert.getAdc());
		Assert.assertEquals("0539", alert.getPid());
	}
	
	@Test
	public void sessionManagementDeserializerTest() {
		ByteBuf inputBytes = toByteBuf("0230302f30303035352f4f2f36302f353631312f362f352f312f333533363536343334333533333133312f2f303130302f2f2f2f2f2f363303");
		UCPPacket deseralizedPacket = PACKET_DESERIALIZER.deserialize(inputBytes);
						
		UCPOperationHeader header = deseralizedPacket.getHeader();
		int trn = header.getTrn();
		Assert.assertEquals(0, trn);
		UCPOperationHeader.Type type = header.getType();
		Assert.assertEquals(UCPOperationHeader.Type.OPERATION, type);
		UCPOperationType operationType = header.getOperation();
		Assert.assertEquals(UCPOperationType.SESSION_MANAGEMENT, operationType);
	
		UCPOperation operation = deseralizedPacket.getData();
		Assert.assertTrue(operation instanceof O_6x_AbstractDataType);
		O_6x_AbstractDataType sessionManagement = (O_6x_AbstractDataType) operation;
		
		Assert.assertEquals("5611", sessionManagement.getOadc());
		Assert.assertEquals((Character)'6', sessionManagement.getOton());
		Assert.assertEquals((Character)'5', sessionManagement.getOnpi());
		Assert.assertEquals((Character)'1', sessionManagement.getStyp());
		Assert.assertEquals("56VCCS11", sessionManagement.getPwd());
		Assert.assertEquals(null, sessionManagement.getNpwd());
		Assert.assertEquals("0100", sessionManagement.getVers());
		Assert.assertEquals(null, sessionManagement.getOpid());
	}
	
	@Test
	public void submitShortMessageDeserializerTest() {
		ByteBuf inputBytes = toByteBuf("0230302f30303134302f4f2f35312f303630333134303535312f313231322f2f312f2f332f2f2f2f2f2f2f2f2f2f2f2f2f332f2f34443635373337333631363736353230373436353733373432303734363537383734323037333734363136453634363137323634323033303331323033303336323033323330333133312f2f2f2f2f2f2f2f2f2f2f2f2f343603");
		UCPPacket deseralizedPacket = PACKET_DESERIALIZER.deserialize(inputBytes);
						
		UCPOperationHeader header = deseralizedPacket.getHeader();
		int trn = header.getTrn();
		Assert.assertEquals(0, trn);
		UCPOperationHeader.Type type = header.getType();
		Assert.assertEquals(UCPOperationHeader.Type.OPERATION, type);
		UCPOperationType operationType = header.getOperation();
		Assert.assertEquals(UCPOperationType.SUBMIT_SHORT_MESSAGE, operationType);
	
		UCPOperation operation = deseralizedPacket.getData();
		Assert.assertTrue(operation instanceof O_5x_AbstractDataType);
		O_5x_AbstractDataType sessionManagement = (O_5x_AbstractDataType) operation;
		
		Assert.assertEquals("0603140551", sessionManagement.getAdc());
		Assert.assertEquals("1212", sessionManagement.getOadc());
		Assert.assertEquals(null, sessionManagement.getAc());
		Assert.assertEquals((Character) '1', sessionManagement.getNrq());
		Assert.assertEquals(null, sessionManagement.getNadc());
		Assert.assertEquals((Character)'3', sessionManagement.getNt());
		Assert.assertEquals(null, sessionManagement.getNpid());
		Assert.assertEquals(null, sessionManagement.getLrq());
		Assert.assertEquals(null, sessionManagement.getLrad());
		Assert.assertEquals(null, sessionManagement.getLpid());
		Assert.assertEquals(null, sessionManagement.getDd());
		Assert.assertEquals(null, sessionManagement.getDdt());
		Assert.assertEquals(null, sessionManagement.getVp());
		Assert.assertEquals(null, sessionManagement.getRpid());
		Assert.assertEquals((Character) '3', sessionManagement.getMt());
		Assert.assertEquals(null, sessionManagement.getNmsg());
		Assert.assertEquals("Message test text standard 01 06 2011", sessionManagement.getAmsg());
		Assert.assertEquals(null, sessionManagement.getNb());
		Assert.assertEquals(null, sessionManagement.getTmsg());
		Assert.assertEquals(null, sessionManagement.getMms());
		Assert.assertEquals(null, sessionManagement.getPr());
		Assert.assertEquals(null, sessionManagement.getMcl());
		Assert.assertEquals(null, sessionManagement.getRpi());
		Assert.assertEquals(null, sessionManagement.getOtoa());
		Assert.assertEquals(null, sessionManagement.getXser());
	}
	
	@Test
	public void deliveryNotificationDeserializerTest() {
		ByteBuf inputBytes = toByteBuf("0230302f30303139382f4f2f35332f31303731333438373335332f303630333134303535312f2f2f2f2f2f2f2f2f2f2f2f2f3237303731313134353134302f302f3030302f3237303731313134353134352f332f2f344436353733373336313637363532303636364637323230333033303333333333363330333333313334333033353335333132303638363137333230363236353635364532303634363536433639373636353732363536342f2f2f2f2f2f2f2f2f33333630393030343738302f2f2f2f324203");
		UCPPacket deseralizedPacket = PACKET_DESERIALIZER.deserialize(inputBytes);
		
		UCPOperationHeader header = deseralizedPacket.getHeader();
		int trn = header.getTrn();
		Assert.assertEquals(0, trn);
		UCPOperationHeader.Type type = header.getType();
		Assert.assertEquals(UCPOperationHeader.Type.OPERATION, type);
		UCPOperationType operationType = header.getOperation();
		Assert.assertEquals(UCPOperationType.DELIVER_NOTIFICATION, operationType);
	
		UCPOperation operation = deseralizedPacket.getData();
		Assert.assertTrue(operation instanceof O_5x_AbstractDataType);
		O_5x_AbstractDataType sessionManagement = (O_5x_AbstractDataType) operation;
		
		Assert.assertEquals("10713487353", sessionManagement.getAdc());
		Assert.assertEquals("0603140551", sessionManagement.getOadc());
		Assert.assertEquals(null, sessionManagement.getRpid());
		Assert.assertEquals("270711145140", sessionManagement.getScts());
		Assert.assertEquals((Character) '0', sessionManagement.getDst());
		Assert.assertEquals("000", sessionManagement.getRsn());
		Assert.assertEquals("270711145145", sessionManagement.getDscts());
		Assert.assertEquals((Character) '3', sessionManagement.getMt());
		Assert.assertEquals(null, sessionManagement.getNb());
		Assert.assertEquals("Message for 0033603140551 has been delivered", sessionManagement.getAmsg());
		Assert.assertEquals(null, sessionManagement.getTmsg());
		Assert.assertEquals(null, sessionManagement.getMms());
		Assert.assertEquals("33609004780", sessionManagement.getHplmn());
		Assert.assertEquals(null, sessionManagement.getXser());
	}
	
	@Test
	public void deleteMessageDeserializerTest() {
		ByteBuf inputBytes = toByteBuf("0230312f30303038382f4f2f35362f303630333134303535312f313133332f2f2f2f2f2f2f2f2f2f2f2f2f2f2f2f2f332f2f3332333733303337333133313331333433353331333433302f2f2f2f2f2f2f2f2f2f2f2f2f394603");
		UCPPacket deseralizedPacket = PACKET_DESERIALIZER.deserialize(inputBytes);
				
		UCPOperationHeader header = deseralizedPacket.getHeader();
		int trn = header.getTrn();
		Assert.assertEquals(1, trn);
		UCPOperationHeader.Type type = header.getType();
		Assert.assertEquals(UCPOperationHeader.Type.OPERATION, type);
		UCPOperationType operationType = header.getOperation();
		Assert.assertEquals(UCPOperationType.DELETE_MESSAGE, operationType);
	
		UCPOperation operation = deseralizedPacket.getData();
		Assert.assertTrue(operation instanceof O_5x_AbstractDataType);
		O_5x_AbstractDataType sessionManagement = (O_5x_AbstractDataType) operation;
		
		Assert.assertEquals("0603140551", sessionManagement.getAdc());
		Assert.assertEquals("1133", sessionManagement.getOadc());
		Assert.assertEquals(null, sessionManagement.getAc());
		Assert.assertEquals((Character) '3', sessionManagement.getMt());
		Assert.assertEquals("270711145140", sessionManagement.getAmsg());
		Assert.assertEquals(null, sessionManagement.getOtoa());
		Assert.assertEquals(null, sessionManagement.getHplmn());
	}
	
	@Test
	public void positiveResponseDeserializerTest() {
		ByteBuf inputBytes = toByteBuf("0230302f30303032332f522f33312f412f303030302f323603");
		UCPPacket deseralizedPacket = PACKET_DESERIALIZER.deserialize(inputBytes);
				
		UCPOperationHeader header = deseralizedPacket.getHeader();
		int trn = header.getTrn();
		Assert.assertEquals(0, trn);
		UCPOperationHeader.Type type = header.getType();
		Assert.assertEquals(UCPOperationHeader.Type.RESULT, type);
		UCPOperationType operationType = header.getOperation();
		Assert.assertEquals(UCPOperationType.ALERT, operationType);
	
		UCPOperation operation = deseralizedPacket.getData();
		Assert.assertTrue(operation instanceof PositiveResponse);
		PositiveResponse sessionManagement = (PositiveResponse) operation;
		
		Assert.assertEquals("0000", sessionManagement.getSm());
	}
	
	@Test
	public void positiveResponseWithMvpDeserializerTest() {
		ByteBuf inputBytes = toByteBuf("0230302f30303035362f522f35312f412f323730373131313530362f303033333630333134303535313a3237303731313134353134302f433503");
		UCPPacket deseralizedPacket = PACKET_DESERIALIZER.deserialize(inputBytes);
				
		UCPOperationHeader header = deseralizedPacket.getHeader();
		int trn = header.getTrn();
		Assert.assertEquals(0, trn);
		UCPOperationHeader.Type type = header.getType();
		Assert.assertEquals(UCPOperationHeader.Type.RESULT, type);
		UCPOperationType operationType = header.getOperation();
		Assert.assertEquals(UCPOperationType.SUBMIT_SHORT_MESSAGE, operationType);
	
		UCPOperation operation = deseralizedPacket.getData();
		Assert.assertTrue(operation instanceof PositiveResponseWithMvp);
		PositiveResponseWithMvp sessionManagement = (PositiveResponseWithMvp) operation;
		
		Assert.assertEquals("0033603140551:270711145140", sessionManagement.getSm());
		Assert.assertEquals("2707111506", sessionManagement.getMvp());
	}

	// Converts the copied "Hex Stream" from Wireshark to a ByteBuf
	private static ByteBuf toByteBuf(String wiresharkCopy) {
		ByteBuf bb = Unpooled.buffer();

		char[] charArray = wiresharkCopy.toCharArray();

		for (int i = 0; i < charArray.length; i += 2) {
			char one = charArray[i];
			char two = charArray[i + 1];
			bb.writeByte(Byte.parseByte("" + one + two, 16));
		}
		return bb;
	}
}
