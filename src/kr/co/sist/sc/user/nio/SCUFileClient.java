package kr.co.sist.sc.user.nio;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * ���� ���� ��û�� ������ Ŭ���̾�Ʈ�� �����ϰ� �ִ� �̹��� ����� Helper Class�� �����ϰ�, 
 * ���� �� ���� �̹����� ���Ͽ� �����ϰ� ���� ���� �̹����� ���۹޴� ��
 * @author owner
 */
public class SCUFileClient {
	private static SCUFileClient scu_fc;
	
	private SCUFileClient() { } // SCUFileClient
	
	public static SCUFileClient getInstance() {
		if (scu_fc == null) {
			scu_fc = new SCUFileClient();
		} // end if
		
		return scu_fc;
	} // getInstance
	
	/**
	 * user.images ��Ű�� ���� ������ �ִ� �̹����� Helper Class�� �����ϴ� ��
	 * @param revMsg
	 * @return
	 */
	public String[] sendImageList(String revMsg) {
		String[] fileList = null;
		
		String imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/" + revMsg + "/";
		File file = new File(imgPath);
		
		List<String> list = new ArrayList<String>();
		
		for (String fileName : file.list()) {
			if ((fileName.startsWith("l_" + revMsg + "_") && fileName.endsWith(".png")) 
					|| (fileName.startsWith("s_" + revMsg + "_") && fileName.endsWith(".png"))) {
				list.add(fileName);
			} // end if	
		} // end for
		
		fileList = new String[list.size()];
		
		list.toArray(fileList);
		
		return fileList;
	} // sendImageList
	
	/**
	 * Helper Class�κ��� �̹��� ��� ���� ��û�� ������, 
	 * �̹��� ����� Helper�� �����ϰ� �������� �ʴ� �̹����� �ٿ�ε��Ѵ�.
	 * 1) Ŭ���̾�Ʈ �α��ο� �������� �� (���� ����)
	 * 2) ��ȭ/������ �߰��Ǿ��� �� (���� ��)
	 * @throws IOException
	 */
	public void connectToServer(int init) throws IOException {
		Socket scClient = null;
		
		DataInputStream dis = null;
		DataOutputStream dos = null;
		
		FileOutputStream fos = null;
		
		int fileCnt = 0;
		int fileSize = 0;
		int fileLen = 0;
		
		byte[] readData = null;
		
		String fileName = "";
		String imgPath = "";
		String revMsg = "";
		
		try {
			String address = "211.63.89.132";
			int port = 3333;
			scClient = new Socket(address, port);
			
			// Ŭ���̾�Ʈ���� �̹��� ��û ��
			// 0 : not found, 1 : movie, 2 : snack
			if (init != 0) {
				dos = new DataOutputStream(scClient.getOutputStream());
				
				dos.writeInt(init);
				dos.flush();
			} // end if
			
			// Helper�κ��� �������� ��ȭ/������ �߰��Ǿ��ٴ� �޽����� �ް�, 
			dis = new DataInputStream(scClient.getInputStream());
			
			revMsg = dis.readUTF();
			if (!revMsg.equals("")) {
				// �̹��� ����� �˻��Ͽ� 
				String[] fileNames = SCUFileClient.getInstance().sendImageList(revMsg);
				
				// Helper�� ������ �� 
				dos = new DataOutputStream(scClient.getOutputStream());
				
				dos.writeInt(fileNames.length);
				dos.flush();
				
				for (int i = 0; i < fileNames.length; i++) {
					dos.writeUTF(fileNames[i]);
					dos.flush();
				} // end for
				
				// Helper���� �������� �̹��� ����� �����Ѵ�.
				dis = new DataInputStream(scClient.getInputStream());
				
				// ������ ����
				fileCnt = dis.readInt();
				
				for (int i = 0; i < fileCnt; i++) {
					// ���� ũ��
					fileSize = dis.readInt();
					
					
					// ���� �̸�
					fileName = dis.readUTF();
					
					//�������� ������ �����ϴ� �Ͱ� Ŭ���̾�Ʈ�� ������ �޴� �ӵ��� ���߱� ���� �극��ŷ �ڵ�.
					
					imgPath = "C:/dev/workspace/sc_prj/src/kr/co/sist/sc/user/images/" + fileName.split("_")[1] + "/";
					
					fos = new FileOutputStream(imgPath + fileName);
					
					readData = new byte[512];
					
					while (fileSize > 0) {
						fileLen = dis.read(readData);
						
						fos.write(readData, 0, fileLen);
						fos.flush();
						
						fileSize--;
						dos.writeUTF("");
					} // end while
				} // end for
			} // end if
		} finally {
			if (fos != null) { fos.close(); } // end if
			if (dis != null) { dis.close(); } // end if
			if (dos != null) { dos.close(); } // end if
			if (scClient != null) { scClient.close(); } // end if
		} // end finally
	} // connectToServer
	
} // class
