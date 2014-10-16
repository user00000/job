/**
 * Обновление таблицы load_parameters
 * 
 */

package ReadWriteFile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Update_tables {

	
	public void update_load_parameters (Connection connect_to_nice, String date_to_restore, String when_restored ) {
		
		String upd_stm = "UPDATE load_parameters SET restore_result = 1, restore_date = ? WHERE data_for_backup = ? ";
		
		PreparedStatement prepare_upd = null;
		try {
			prepare_upd = connect_to_nice.prepareStatement(upd_stm);
		
		
		prepare_upd.setString(1,when_restored);
		prepare_upd.setString(2,date_to_restore );
		
		prepare_upd.executeUpdate();
		
		//prepare_upd.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println("Не удалось обновить таблицу load_parameters ");
		}finally{
			try {
				prepare_upd.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Не удалось удалить запрос ");
			}
			
		}
		
	}
	
	
}
