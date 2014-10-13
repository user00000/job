/**
 * Выборка данных из таблиц : tblScSamUnit и tblStorageCenter[01-99]
 * 
 * 
 */

package ReadWriteFile;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select_from_tables  {
	
	
	public static ResultSet select_from_table_server_path(Connection connect_to_nice) throws SQLException{
		
		Statement stat_server_path        = connect_to_nice.createStatement();
		ResultSet rs_server_path;
		StringBuilder select_server_paths = new StringBuilder("");
		
		select_server_paths.append(" DECLARE @S NVARCHAR(MAX)=''"); // --STATEMENT FOR SELECT
		select_server_paths.append("SET @S = N'  SELECT DISTINCT t2.nvcUnitPath, t2.siUnitID '+");
		select_server_paths.append("N' FROM '+");
		select_server_paths.append("N' nice_admin.dbo.tblScSamUnit t2'");
		
		//System.out.println(select_server_paths.toString());
		
		select_server_paths.append(" EXEC (@S)");
		
		rs_server_path = stat_server_path.executeQuery(select_server_paths.toString());
		
		
		
		return rs_server_path;
	}
	
	
	
	
	
	public static ResultSet select_local_path_to_file(Connection connect_to_nice,String server_path_ID, String table0,String folderID_field , String select_field, String where_field,String date_to_restore ) throws SQLException  {
	
		Statement stat = connect_to_nice.createStatement();
	
	StringBuilder select_path = new StringBuilder("");

	select_path.append(" DECLARE @K INT = 1");
	select_path.append(" DECLARE @S NVARCHAR(MAX)=''"); // --STATEMENT FOR SELECT
    select_path.append(" DECLARE @P NVARCHAR = '0'");
	//----CHECK ALL TABLES-----------------
    select_path.append(" WHILE (@K <= 99)");
	select_path.append(" BEGIN");
	select_path.append(" IF(@K > 9 ) SET @P = ''");
	 //-----if table exsists----------------------
	select_path.append(" IF OBJECT_ID (N'"+table0+"'+@P+CAST(@K as NVARCHAR), N'U') IS NOT NULL") ;
	select_path.append(" BEGIN"); 
	select_path.append(" SET @S = @S + N'SELECT "+folderID_field+","+select_field+" FROM  '+N'"+table0+"'+@P+CAST(@K as NVARCHAR)+ ' WHERE dateadd(n,DATEDIFF(n,0,"+where_field+"),0) = dateadd(n,DATEDIFF(n,0,'''+N'"+date_to_restore+"'+N'''),0)'");
	select_path.append(" SET @S = @S + N' UNION ALL '");   
	select_path.append(" END");
	select_path.append(" SET @K = @K+1;");
	select_path.append(" END");
	 //-------check all tables----------------- 
	select_path.append(" SET @S = LEFT(@S,LEN(@S)-10)");
	
	select_path.append("SET @S = N'  SELECT t2.nvcUnitPath,t1.vcArchivePath'+");
	select_path.append("N' FROM('+");
	select_path.append(" @S +");
	select_path.append("N')t1'+");
	select_path.append("N' LEFT JOIN'+");
	select_path.append("N' nice_admin.dbo.tblScSamUnit t2'+");
	select_path.append("N' ON t1.siUnitID = t2.siUnitID'+");
	select_path.append("N' WHERE  t2.siUnitID = "+server_path_ID+"'");
	
	select_path.append(" EXEC (@S)");
	 
 
//	System.out.println(select_path.toString());
	ResultSet rs = stat.executeQuery(select_path.toString());
	
	
	
	return rs;
	
	}
	
}
