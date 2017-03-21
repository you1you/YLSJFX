/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileUploadServer;

import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.OnError;
import javax.websocket.server.ServerEndpoint;
import org.apache.log4j.Logger;
import javax.websocket.server.ServerEndpoint;
@ServerEndpoint("/websocket")
public class ChatServer {

	protected Logger logger = Logger.getLogger(this.getClass());
	private Session session;
	String fileId = "";
	String fileName = "";

	@OnOpen
	public void open(Session session) {
		this.session = session;
		logger.error("MaxBinaryMessageBufferSize:" + session.getMaxBinaryMessageBufferSize());
		session.setMaxBinaryMessageBufferSize(1024 * 1024);
		logger.error("打开websocket123！");
	}
	
	@OnMessage
	public byte[] receiveMessage(byte[] b) {

		byte[] msg = null;

//		logger.error("getMessage！");
		try {
			logger.error("接收到客户端发送来的信息！" + "长度为：" + b.length);
			if (fileId.equals("")) {
//				System.out.println("收到的字节长为："+getHeadByteLength(b));
				System.out.println("收到的字节长为：" + unsigned4Bytes2Int(b, 0));
				System.out.println("包头识别码为：" + byte2int(b, 4));
				System.out.println("包编号：" + b[6]);
				System.out.println("包信息类别：" + b[7]);
				int fileLen = byte2int(b, 8);
				System.out.println("文件名长度:" + fileLen);
				fileName = getMyFileName(b, 10, 10 + fileLen);
				System.out.println("文件名为：" + fileName);
				int fileOffset = 2 * fileLen + 10;
				int fileNameEnd = fileOffset + 18;
				System.out.println("文件长度为:" + getFileSize(b, fileOffset, fileNameEnd));
				msg = getSendMsg();
//				sendMessage();
			} else {
				System.out.println("文件id已经非空，接收文件");
				try {
					String filePath = "D://upFileStorage/" + fileName;
					System.out.println("收到的字节长为：" + unsigned4Bytes2Int(b, 0));
					System.out.println("包头识别码为：" + byte2int(b, 4));
					System.out.println("包编号：" + b[6]);
					System.out.println("包信息类别：" + b[7]);

					byte[] b1 = new byte[b.length - 8];
					System.arraycopy(b, 8, b1, 0, b1.length);
					FileOutputStream out = new FileOutputStream(filePath, true);
					out.write(b1);
					out.close();
					msg = getFileRespose();
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error("接收到客户端发送来的信息出错啦！");

		}
		return msg;
//		return "很好很好很好";
	}

	@OnClose
	public void close() {
		logger.error("关闭websocket！");
	}

	@OnError
	public void error(Throwable t) {
		logger.error("websocket出错啦！！！");
		logger.error(t.getMessage());
		logger.error(t.getCause());
	}

	//发送数据到前端,这个是通过session发送的，不是websocket
	public void sendMessage() throws Exception {
		byte sendMsg[] = getSendMsg();
		ByteBuffer sendMsgBuf = ByteBuffer.wrap(sendMsg);
		this.session.getBasicRemote().sendText("123");
	}

//	public long getHeadByteLength(){
//		
//		return 0;
//	}
	//将一个4byte的数组转换成32位的int 
	public long unsigned4Bytes2Int(byte[] buf, int pos) {
		int firstByte = 0;
		int secondByte = 0;
		int thirdByte = 0;
		int fourthByte = 0;
		int index = pos;
		firstByte = (0x000000FF & ((int) buf[index]));  //等价于：256+(int) buf[index]
		secondByte = (0x000000FF & ((int) buf[index + 1]));
		thirdByte = (0x000000FF & ((int) buf[index + 2]));
		fourthByte = (0x000000FF & ((int) buf[index + 3]));
		return ((long) (firstByte << 24 | secondByte << 16 | thirdByte << 8 | fourthByte)) & 0xFFFFFFFFL;
		//强制转换为long类型，防止高32位丢失
	}

	//将byte转换为int16
	public int byte2int(byte[] res, int offset) {
		int targets = (res[offset] & 0xff) | ((res[offset + 1] << 8) & 0xff00);
		return targets;
	}

	//获取中文名字
	public String getMyFileName(byte[] b, int offset, int end) {
		int len = end - offset;
		char[] a = new char[len];
		String fileName = "";
		for (int i = 0, j = 0; i < len * 2; i++, j++) {
			a[j] = (char) byte2int(b, offset + i);
			i++;
		}
		fileName = String.valueOf(a);
		return fileName;
	}

	//获取文件大小
	public long getFileSize(byte[] b, int offset, int end) {
		int len = end - offset;
		String s = "";
		long l = 0L;
		for (int i = 0; i < len; i++) {
			s = s + b[i + offset];
		}
		l = Long.parseLong(s);
		return l;
	}

	public byte[] chars2Bytes(char[] c) {
		byte[] bs = new byte[(c.length) * 2];
		for (int i = 0; i < c.length; i++) {
			bs[2 * i + 1] = (byte) ((c[i] & 0xFF00) >> 8);
			bs[2 * i] = (byte) (c[i] & 0xFF);
		}
		return bs;
	}
	
	public static byte[] int2Bytes(int i) {
//		int i = 65535;
		byte[] a = new byte[4];
		a[0] = (byte) (0xff & i);
		a[1] = (byte) ((0xff00 & i) >> 8);
		a[2] = (byte) ((0xff0000 & i) >> 16);
		a[3] = (byte) ((0xff000000 & i) >> 24);
		return a;
	}

	public byte[] getSendMsg() {

		String news = "一二三.jpg";
		String s = "abcdefghijklmnopqrstuvwsyz123456";//32个字符
		fileId = s;
		char[] cs = news.toCharArray();
		byte[] bc = null;
		byte[] b = null;
		bc = chars2Bytes(cs);
		try {
			b = s.getBytes("utf-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		byte[] sendMsg = new byte[b.length + 1 + bc.length];
		sendMsg[0] = 0;//表示成功
		System.arraycopy(b, 0, sendMsg, 1, b.length);//将b数组的数据拷贝到sendMsg数组中去
		System.arraycopy(bc, 0, sendMsg, b.length + 1, bc.length);

		return sendMsg;
	}
	
	public byte[] getFileRespose(){
		String news="哈哈哈.xml";
		String s = "abcdefghijklmnopqrstuvwsyz123456";
		int pyl=65535;
		byte []bpyl=new byte[4];
		byte[] b = null;
		byte[] bc=null;
		char[] cs = news.toCharArray();
		bc=chars2Bytes(cs);
		try {
			b=s.getBytes("utf-8");
		} catch (UnsupportedEncodingException ex) {
			ex.printStackTrace();
		}
		byte[] sendMsg = new byte[b.length + 6 + bc.length];
		sendMsg[0]=0;
		System.arraycopy(b, 0, sendMsg, 1, b.length);
//		sendMsg[33]=5;//这是偏移量
		bpyl=int2Bytes(pyl);
		sendMsg[33]=bpyl[0];
		sendMsg[34]=bpyl[1];
		sendMsg[35]=bpyl[2];
		sendMsg[36]=bpyl[3];
		sendMsg[37]=(byte) bc.length;//信息长度
		System.arraycopy(bc, 0, sendMsg, b.length+6, bc.length);
		return sendMsg;
	}
	
}
