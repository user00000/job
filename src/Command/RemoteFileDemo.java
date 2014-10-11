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
    private String remoteFilePath="\\\\192.168.2.118\\C$\\test\\restore.bat";
    private String remotrFileName ;   
    private String localPath = "";
    private String loacalFileName = "";
	
    private FileObject destn;
    private FileObject fo;
  
public void transferFiles(FileObject destn,FileObject fo) throws IOException {

    
    
     File f=new File("D:\\Nice_Storage\\List_to_pkg_Restore\\restore.bat"); //Takes the default path, else, you can specify the required path
   
    /*
    if(f.exists())
    {
        f.delete();
    }
    f.createNewFile(); 
    */
    

    destn=VFS.getManager().resolveFile(f.getAbsolutePath());
    //domain, username, password
    UserAuthenticator auth=new StaticUserAuthenticator(domain, userName, password);
    FileSystemOptions opts=new FileSystemOptions();
    DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);


    fo=VFS.getManager().resolveFile(remoteFilePath,opts);

    System.out.println(fo.exists());

    //fo.createFile();

    fo.copyFrom(destn, Selectors.SELECT_SELF); // to server
   // destn.copyFrom(fo,Selectors.SELECT_SELF); // from server
   // destn.close();

    //InputStream is=new FileInputStream(f);

}


    
public void copyToRemote() throws IOException {
    	
	transferFiles(this.destn,this.fo);
    	
    	fo.copyFrom(destn, Selectors.SELECT_SELF);
    	
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