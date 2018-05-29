package kt_ctt_xml_exam02;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

public class write {

	public static Document doc = new Document();
	public static Element root = new Element("CTT_Message");
	
	public static void xmlWrite(List<Map> list, String state) {
		String dateTm = null;

		

		String str = null;

		for (int i = 0; i < list.size(); i++) {
			Element CTT_Messages = new Element("CTT_Messages");
			Element CTTStatus = new Element("CTTStatus");
			Element AverageLinkSpeed = new Element("AverageLinkSpeed");
			Element TPEG_Loc = new Element("TPEG_Loc");
			Element Container = new Element("Container");
			Element LocationCoordinates = new Element("LocationCoordinates");
			Element SingleLinkID = new Element("SingleLinkID");
			Element SLinkID = new Element("SLinkID");

			root.addContent(CTT_Messages);
			CTT_Messages.addContent(CTTStatus);
			CTTStatus.addContent(AverageLinkSpeed);
			CTT_Messages.addContent(TPEG_Loc);
			TPEG_Loc.addContent(Container);
			Container.addContent(LocationCoordinates);
			LocationCoordinates.addContent(SingleLinkID);
			SingleLinkID.addContent(SLinkID);

			AverageLinkSpeed.setAttribute("speed", list.get(i).get("realtime_spd").toString());
			SLinkID.setAttribute("LinkID", list.get(i).get("moct_lk_sn").toString());
			Container.setAttribute("loc41", "65");
			LocationCoordinates.setAttribute("loc01", "10");
			SingleLinkID.setAttribute("loc43", "1");

			dateTm = list.get(i).get("realtime").toString();

			str = "20" + dateTm.substring(0, 2) + "_" + dateTm.substring(2, 4) + "_" + dateTm.substring(4, 6) + "-"
					+ dateTm.substring(6, 8) + "." + dateTm.substring(8, 10) + "." + dateTm.substring(10, 12);

		//	System.out.println(str);

		}
		
			try {
			doc.setRootElement(root);
			FileOutputStream out = new FileOutputStream("c:\\dev\\ParseData\\CTT_" + state + "_" + str);

			XMLOutputter serializer = new XMLOutputter();

			Format f = serializer.getFormat();
			f.setEncoding("UTF-8");
			f.setIndent(" ");
			f.setLineSeparator("\r\n");
			f.setTextMode(Format.TextMode.TRIM);
			
			serializer.setFormat(f);
			serializer.output(doc, out );
			out.flush();
			out.close();

		} catch (IOException e) {
			System.err.println(e);

		}
	}
}
