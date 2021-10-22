package com.tanqui.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.tanqui.DOMHelper;
import com.tanqui.model.Lop;

public class LopDOM {
	private static String PATH = "src\\main\\resources\\lop.xml";
	private static Document DOCUMENT = DOMHelper.getDocument(PATH);
	private static SinhVienDOM sinhVienDOM = new SinhVienDOM();
	
	public List<Lop> layTatCaLop() {
		List<Lop> danhSachLop = new ArrayList<Lop>();
		Node nutDanhSachLop = DOCUMENT.getElementsByTagName("danhSachLop").item(0);
		NodeList danhSachNutLop = nutDanhSachLop.getChildNodes();
		for (int i = 0; i < danhSachNutLop.getLength(); i++) {
			Node nutLop = danhSachNutLop.item(i);
			if (nutLop.getNodeType() == Node.ELEMENT_NODE) {
				Element phanTuLop = (Element) nutLop;
				
				Lop lop = new Lop();
				Node nutMaLop = phanTuLop.getChildNodes().item(1);
				Node nutTenLop = phanTuLop.getChildNodes().item(3);
				lop.setMaLop(nutMaLop.getTextContent());
				lop.setTenLop(nutTenLop.getTextContent());
				lop.setDanhSachSinhVien(sinhVienDOM.layDanhSachSinhVienBangMaLop(nutMaLop.getTextContent()));
				danhSachLop.add(lop);
			}
		}
		return danhSachLop;
	}
	
	public Lop layLopBangMaLop(String maLop) {
		Node nutLop = layNutLopBangMaLop(maLop);
		if (nutLop != null && nutLop.getNodeType() == Node.ELEMENT_NODE) {
			Element phanTuLop = (Element) nutLop;
			
			Lop lop = new Lop();
			Node nutTenLop = phanTuLop.getChildNodes().item(3);
			lop.setMaLop(maLop);
			lop.setTenLop(nutTenLop.getTextContent());
			lop.setDanhSachSinhVien(sinhVienDOM.layDanhSachSinhVienBangMaLop(maLop));
			return lop;
		}		
		return null;
	}
	
	private static Node layNutLopBangMaLop(String maLop) {
		Node nutLop = null;
//		Lấy danh sách nút mã lớp
		NodeList danhSachNutMaLop = DOCUMENT.getElementsByTagName("maLop");
//		Lấy từng nút mã lớp
		for (int i = 0; i < danhSachNutMaLop.getLength(); i++) {
			Node nutMaLop = danhSachNutMaLop.item(i);
			if (nutMaLop.getNodeType() == Node.ELEMENT_NODE) {
				Element phanTuMaLop = (Element) nutMaLop;
//				Kiểm tra mã lớp của phần tử mã lớp khớp với mã truyền vào không
				if (phanTuMaLop.getTextContent().equals(maLop)) {
//					Lấy nút cha của phần tử mã lớp là nút lớp
					nutLop = phanTuMaLop.getParentNode();
				}
			}
		}
		return nutLop;
	}
	
	public void themLop(Lop lop) {
		Node danhSachLop = DOCUMENT.getElementsByTagName("danhSachLop").item(0);
		Element nutLop = DOCUMENT.createElement("lop");
		Element nutMaLop = taoNutVanBan("maLop", lop.getMaLop());
		nutLop.appendChild(nutMaLop);
		Element nutTenLop = taoNutVanBan("tenLop", lop.getTenLop());
		nutLop.appendChild(nutTenLop);
		Element nutDanhSachSinhVien = DOCUMENT.createElement("danhSachSinhVien");		
		nutLop.appendChild(nutDanhSachSinhVien);
		danhSachLop.appendChild(nutLop);
		DOMHelper.saveXMLContent(DOCUMENT, PATH);
	}
	
	private static Element taoNutVanBan(String tenNut, String vanBan) {
		Element nutPhanTu = DOCUMENT.createElement(tenNut);
		Text nutVanBan = DOCUMENT.createTextNode(vanBan);
		nutPhanTu.appendChild(nutVanBan);
		return nutPhanTu;
	}
	
	public void capNhatTenLop(String maLop, Lop lop) {
		Node nutLop = layNutLopBangMaLop(maLop);
		if (nutLop != null && nutLop.getNodeType() == Node.ELEMENT_NODE) {
			Element phanTuLop = (Element) nutLop;
			Node nutTenLop = phanTuLop.getElementsByTagName("tenLop").item(0);
			nutTenLop.setTextContent(lop.getTenLop());
		}
		DOMHelper.saveXMLContent(DOCUMENT, PATH);
	}
	
	public void xoaLopBangMaLop(String maLop) {
		Node nutLop = layNutLopBangMaLop(maLop);
		if (nutLop != null && nutLop.getNodeType() == Node.ELEMENT_NODE) {
			nutLop.getParentNode().removeChild(nutLop);
		} else {
			System.out.println("Không tìm thấy lớp nào có mã lớp là " + maLop);
		}
		DOMHelper.saveXMLContent(DOCUMENT, PATH);
	}
}
