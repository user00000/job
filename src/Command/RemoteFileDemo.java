package Command;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.vfs.FileObject;
import org.apache.commons.vfs.FileSystemException;
import org.apache.commons.vfs.FileSystemOptions;
import org.apache.commons.vfs.Selectors;
import org.apache.commons.vfs.UserAuthenticator;
import org.apache.commons.vfs.VFS;
import org.apache.commons.vfs.auth.StaticUserAuthenticator;
import org.apache.commons.vfs.impl.DefaultFileSystemConfigBuilder;


public class RemoteFileDemo {
	
	private String domain="data-integration.ru";
    private String userName="niceuser";
    private String password="aAd7eiFf";
    private String remoteFilePath="\\\\192.168.2.118\\D$\\test";
    private String remotrFileName = "restore.bat";   
    private String localPath = "D:\\Nice_Storage\\List_to_pkg_Restore";
    private String loacalFileName = "restore.bat";
	
    private FileObject destn;
    private FileObject fo;
  

    


    
public void copyToRemote() throws IOException {
    	
	
	    File f = new File(localPath + "\\" + loacalFileName);
	    /*
	    if(f.exists())
	    {
	        f.delete();
	    }
	    f.createNewFile(); 
	    */
	    
	    
	    UserAuthenticator auth = new StaticUserAuthenticator(domain, userName, password);
	    	
	    
	    destn=VFS.getManager().resolveFile(f.getAbsolutePath());
	    FileSystemOptions opts=new FileSystemOptions();
	    DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
	    fo=VFS.getManager().resolveFile(remoteFilePath + "\\" + remotrFileName,opts);

	    
	    //System.out.println(fo.exists());


	    //fo.copyFrom(destn, Selectors.SELECT_SELF); // to server
	  
	    //if(fo.exists()){
	    //	fo.delete();
	    //}
	    
	    destn.moveTo(fo);
    	
    	destn.close();
    }


public void copyToLocal() throws IOException {
	
	 File f = new File(localPath + "\\" + loacalFileName);
    
    UserAuthenticator auth = new StaticUserAuthenticator(domain, userName, password);
    	
    
    destn=VFS.getManager().resolveFile(f.getAbsolutePath());
    FileSystemOptions opts=new FileSystemOptions();
    DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
    fo=VFS.getManager().resolveFile(remoteFilePath + "\\" + remotrFileName,opts);


    //destn.copyFrom(fo,Selectors.SELECT_SELF);
    fo.moveTo(destn);
    
   	
   	destn.close();
   }








    


public void setDomain(String domain) {
	this.domain = domain;
}


public void setUserName(String userName) {
	this.userName = userName;
}


public void setPassword(String password) {
	this.password = password;
}


public void setRemoteFilePath(String remoteFilePath) {
	this.remoteFilePath = remoteFilePath;
}


public void setRemotrFileName(String remotrFileName) {
	this.remotrFileName = remotrFileName;
}


public void setLocalPath(String localPath) {
	this.localPath = localPath;
}


public void setLoacalFileName(String loacalFileName) {
	this.loacalFileName = loacalFileName;
}


}