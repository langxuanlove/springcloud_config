package com.key.api.util;
/*package com.gnet.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;

*//**
* 远程Shell脚本执行工具
*
* @author 
*//*
public class RemoteShellTool {

private Connection conn;
private String ipAddr;
private String charset = Charset.defaultCharset().toString();
private String userName;
private String password;

public RemoteShellTool(String ipAddr, String userName, String password, String charset) {
   this.ipAddr = ipAddr;
   this.userName = userName;
   this.password = password;
   if(charset != null) {
    this.charset = charset;
   }
}
*//**
* 登录远程Linux主机
*
* @return
* @throws IOException
*//*
public boolean login() throws IOException {
   conn = new Connection(ipAddr);
   conn.connect(); // 连接
   return conn.authenticateWithPassword(userName, password); // 认证
}


*//**
* 执行Shell脚本或命令
*
* @param cmds 命令行序列
* @return
*//*
public String exec(String cmds) {
   InputStream in = null;
   String result = "";
   try {
    if (this.login()) {
     Session session = conn.openSession(); // 打开一个会话
     session.execCommand(cmds);
     in = session.getStdout();
     result = this.processStdout(in, this.charset);
     conn.close();
    }
   } catch (IOException e1) {
	   e1.printStackTrace();
   }
   return result;
}

*//**
* 解析流获取字符串信息
*
* @param in 输入流对象
* @param charset 字符集
* @return
*//*
public String processStdout(InputStream in, String charset) {
   byte[] buf = new byte[1024];
   StringBuffer sb = new StringBuffer();
   try {
    while (in.read(buf) != -1) {
     sb.append(new String(buf, charset));
    }
   } catch (IOException e) {
    e.printStackTrace();
   }
   return sb.toString();
}

*//**
 * 
 * 【作者】 zhouaiping
 * 
 * @Description：从Linux远程下载文件
 * 
 * @param remoteUrl
 * @param localDir
 *//*
public static void smbGet(String remoteUrl, String localDir) {    
    InputStream in = null;    
    OutputStream out = null;    
      
    try {    
        SmbFile remoteFile = new SmbFile(remoteUrl);    
        if (remoteFile != null && remoteFile.exists()) {    
            String fileName = remoteFile.getName();    
            File localFile = new File(localDir + File.separator + fileName);    
            in = new BufferedInputStream(new SmbFileInputStream(remoteFile));    
            out = new BufferedOutputStream(new FileOutputStream(localFile));    
            byte[] buffer = new byte[1024];    
            while (in.read(buffer) != -1) {    
                out.write(buffer);    
                buffer = new byte[1024];    
            }    
        } else {  
            // 文件不存在  
            System.out.println(remoteUrl + "    文件不存在！");  
        }  
          
    } catch (Exception e) {    
        e.printStackTrace();    
    } finally {    
        try {  
            out.close();  
            out = null;  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                in.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }    
}    

*//**
 * 向共享目录上传文件   
 * @param remoteUrl 
 * @param localFilePath 
 *//*
public static void smbPut(String remoteUrl, String localFilePath) {    
    InputStream in = null;    
    OutputStream out = null;   
    try {    
          
        File localFile = new File(localFilePath);    
        String fileName = localFile.getName();    
        SmbFile remoteFile = new SmbFile(remoteUrl + "/" + fileName);    
        in = new BufferedInputStream(new FileInputStream(localFile));    
        out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));    
        byte[] buffer = new byte[1024];    
        while (in.read(buffer) != -1) {    
            out.write(buffer);    
            buffer = new byte[1024];    
        }    
    } catch (Exception e) {    
        e.printStackTrace();    
    } finally {    
        try {  
            out.close();  
            out = null;
        } catch (IOException e) {
            e.printStackTrace();  
        } finally {  
            try {  
                in.close();  
            } catch (IOException e) {
                e.printStackTrace();  
            }  
        }  
    }    
}    

public static void main(String[] args) {
	RemoteShellTool tool = new RemoteShellTool("192.168.4.80", "root", "simple", null);
	try {
		tool.login();
	} catch (IOException e) {
		e.printStackTrace();
	}
	//String _sResult = tool.exec("cd /usr/local/apache-tomcat-8.0.14/bin/;./shutdown.sh;sleep 10;./startup.sh;");
	//String _sResult = tool.exec("cd /usr/local/apache-tomcat-8.0.14/conf/; sed -i 's/port=\"0926\"/port=\"8080\"/g' server.xml");
	//System.out.println(_sResult);
}

}
*/