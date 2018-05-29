package kt_ctt_xml_exam02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class control {

	public Map<String,String> list;
	
	public List<Map> dbctrl(String state){
		connect connect = new connect();
		
		
		List<Map> list = new ArrayList<>();
		
		String sql = "" + "select * \r\n" + "	from\r\n" + "			(select\r\n" + "				moct_lk_sn, "
				+ "				date_format(realtime,'%y%m%d%h%i%s') as realtime, " + "				realtime_spd, \r\n"
				+ "				case when STNL_REG_CD between 100 and 129 then \"서울특별시\" \r\n"
				+ "					when STNL_REG_CD between 130 and 149 then \"부산광역시\"\r\n"
				+ "					when STNL_REG_CD between 150 and 160 then \"대구광역시\"\r\n"
				+ "					when STNL_REG_CD between 161 and 174 then \"인천광역시\"\r\n"
				+ "					when STNL_REG_CD between 175 and 182 then \"광주광역시\"		\r\n"
				+ "					when STNL_REG_CD between 183 and 191 then \"대전광역시\"\r\n"
				+ "					when STNL_REG_CD between 192 and 199 then \"울산광역시\"		\r\n"
				+ "					when STNL_REG_CD between 200 and 249 then \"경기도\"	\r\n"
				+ "					when STNL_REG_CD between 250 and 269 then \"강원도\"	\r\n"
				+ "					when STNL_REG_CD between 270 and 284 then \"충청북도\"	\r\n"
				+ "					when STNL_REG_CD between 285 and 304 then \"충청남도\"\r\n"
				+ "					when STNL_REG_CD between 305 and 323 then \"전라북도\"\r\n"
				+ "					when STNL_REG_CD between 324 and 349 then \"전라남도\"		\r\n"
				+ "					when STNL_REG_CD between 350 and 378 then \"경상북도\"\r\n"
				+ "					when STNL_REG_CD between 379 and 404 then \"경상남도\"		\r\n"
				+ "					when STNL_REG_CD between 405 and 412 then \"제주특별자치도\"				\r\n"
				+ "					when STNL_REG_CD between 413 and 413 then \"세종특별자치시\"		\r\n"
				+ "					else \"전국여유분\" " + "				end as STNL_REG_NM\r\n" + "			from\r\n"
				+ "			(" + "				SELECT " + "					moct_lk_sn"
				+ "					, realtime" + "					, realtime_spd"
				+ "					, cast(SUBSTRING(moct_lk_sn,1,3) as unsigned) AS STNL_REG_CD	\r\n"
				+ "				FROM kt_api" + "			) s" + "		) s\r\n" + "where stnl_reg_nm = \"" + state
				+ "\"";
		
		try {
			connect.stmt = connect.conn.createStatement();
			connect.rs = connect.stmt.executeQuery(sql);
			
			while(connect.rs.next()) {
				Map<String, Object> map = new HashMap<>();
				map.put("moct_lk_sn", connect.rs.getString("moct_lk_sn"));
				map.put("realtime", connect.rs.getString("realtime"));
				map.put("realtime_spd", connect.rs.getString("realtime_spd"));
				map.put("STNL_REG_NM", connect.rs.getString("STNL_REG_NM"));
				list.add(map);
				
				/* System.out.println("moct_lk_sn : " + connect.rs.getString("moct_lk_sn") + "\n"
				 			+ "realtime : " + connect.rs.getString("realtime") + "\n"
				 			+ "realtime_spd : " + connect.rs.getString("realtime_spd") + "\n"
				 			+ "STNL_REG_NM : " + connect.rs.getString("STNL_REG_NM"));*/
			}
			System.out.println("--------------------------------------------------------" + "\n" + state
					+ " list의 총 개수 : " + list.size() + "개");
			
			
		} catch(Exception e) {
			e.printStackTrace();
		
		} finally {
			try {
				if (connect.conn != null)
					connect.conn.close();
				if (connect.rs != null)
					connect.rs.close();
				if (connect.stmt != null)
					connect.stmt.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}
	
}
