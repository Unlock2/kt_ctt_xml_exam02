package kt_ctt_xml_exam02;

import java.io.File;
import java.util.List;
import java.util.Map;

public class main {

	public static void main(String[] args) {
		String[] state = { "서울특별시", "부산광역시", "대구광역시", "인천광역시", "광주광역시", "대전광역시", "울산광역시", "경기도", "강원도", "충청북도", "충청남도",
				"전라북도", "전라남도", "경상북도", "경상남도", "제주특별자치도", "전국여유분" };

		connect connect = new connect();
		control control = new control();
		
		int sum = 0;

		File file = new File("c:\\dev\\ParseData\\");
		try {
			if (file.exists())
				file.mkdirs();
			System.out.println("디렉토리 생성이 완료되었습니다.");

		} catch (Exception e) {
			e.printStackTrace();

		}
		
		for (int i = 0; i < state.length; i++) {
			List<Map> list = control.dbctrl(state[i]);
			write.xmlWrite(list, state[i]);
			sum = sum + list.size();
//			System.out.println("size L:" + list.size());
		}
		System.out.println("\n" + "--------------------------------------------------------" + "\n"+ "전체 list의 개수 :: " + sum + "개");
		System.out.println("\n" + "--------------------------------------------------------" + "\n" + "작업이 종료되었습니다! :)");
	}
}
