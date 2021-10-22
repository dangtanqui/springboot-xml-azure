package com.tanqui.xml;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

import com.tanqui.DOMHelper;
import com.tanqui.model.SinhVien;

public class SinhVienDOM {
	private static String PATH = "src\\main\\resources\\lop.xml";
	private static Document DOCUMENT = DOMHelper.getDocument(PATH);
	
	public List<SinhVien> layTatCaSinhVien() {
		List<SinhVien> danhSachTatCaSinhVien = new ArrayList<SinhVien>();
		NodeList danhSachNutSinhVien = DOCUMENT.getElementsByTagName("sinhVien");
//		Lấy từng nút con của nut danh sách sinh viên
		for (int i = 0; i < danhSachNutSinhVien.getLength(); i++) {
			Node nutSinhVien = danhSachNutSinhVien.item(i);
			if (nutSinhVien.getNodeType() == Node.ELEMENT_NODE) {
				Element phanTuSinhVien = (Element) nutSinhVien;
				
				SinhVien sinhVien = new SinhVien();
//				Lấy danh sách các nút con của phần tử sinh viên
				NodeList danhSachNutConSinhVien = phanTuSinhVien.getChildNodes();
				for (int k = 0; k < danhSachNutConSinhVien.getLength(); k++) {
					Node nutConSinhVien = danhSachNutConSinhVien.item(k);
					if (nutConSinhVien.getNodeType() == Node.ELEMENT_NODE) {
						Element phanTuConSinhVien = (Element) nutConSinhVien;
						switch (phanTuConSinhVien.getNodeName()) {
						case "masv":
							sinhVien.setMasv(phanTuConSinhVien.getTextContent());
							break;
						case "hoTen":
							sinhVien.setHoTen(phanTuConSinhVien.getTextContent());
							break;
						case "ngaySinh":
							sinhVien.setNgaySinh(phanTuConSinhVien.getTextContent());
							break;
						case "gioiTinh":
							sinhVien.setGioiTinh(phanTuConSinhVien.getTextContent());
							break;
						case "diemtb":
							sinhVien.setDiemtb(phanTuConSinhVien.getTextContent());
							break;								
						}
					}
				}
				danhSachTatCaSinhVien.add(sinhVien);
			}
		}
		return danhSachTatCaSinhVien;
	}
	
	public List<SinhVien> layDanhSachSinhVienBangMaLop(String maLop) {
		List<SinhVien> danhSachSinhVien = new ArrayList<SinhVien>();
		Node nutLop = layNutLopBangMaLop(maLop);
		if (nutLop != null) {
//			Lấy nút con danh sách sinh vien trong nút lớp, nó là nút con thứ 5
			Node nutDanhSachSinhVien = nutLop.getChildNodes().item(5);
//			Lấy danh sách nút sinh viên là con của nút danh sách sinh viên
			NodeList danhSachNutSinhVien = nutDanhSachSinhVien.getChildNodes();
//			Lấy từng nút con của nut danh sách sinh viên
			for (int i = 0; i < danhSachNutSinhVien.getLength(); i++) {
				Node nutSinhVien = danhSachNutSinhVien.item(i);
				if (nutSinhVien.getNodeType() == Node.ELEMENT_NODE) {
					Element phanTuSinhVien = (Element) nutSinhVien;
					
					SinhVien sinhVien = new SinhVien();
//					Lấy danh sách các nút con của phần tử sinh viên
					NodeList danhSachNutConSinhVien = phanTuSinhVien.getChildNodes();
					for (int k = 0; k < danhSachNutConSinhVien.getLength(); k++) {
						Node nutConSinhVien = danhSachNutConSinhVien.item(k);
						if (nutConSinhVien.getNodeType() == Node.ELEMENT_NODE) {
							Element phanTuConSinhVien = (Element) nutConSinhVien;
							switch (phanTuConSinhVien.getNodeName()) {
							case "masv":
								sinhVien.setMasv(phanTuConSinhVien.getTextContent());
								break;
							case "hoTen":
								sinhVien.setHoTen(phanTuConSinhVien.getTextContent());
								break;
							case "ngaySinh":
								sinhVien.setNgaySinh(phanTuConSinhVien.getTextContent());
								break;
							case "gioiTinh":
								sinhVien.setGioiTinh(phanTuConSinhVien.getTextContent());
								break;
							case "diemtb":
								sinhVien.setDiemtb(phanTuConSinhVien.getTextContent());
								break;								
							}
						}
					}
					danhSachSinhVien.add(sinhVien);
				}
			}
		} else {
			System.out.println("Không tìm thấy lớp có mã lớp là " + maLop);
		}
		return danhSachSinhVien;
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
	
	public SinhVien laySinhVienBangMasv(String masv) {
		Node nutSinhVien = layNutSinhVienBangMasv(masv);
		if (nutSinhVien != null && nutSinhVien.getNodeType() == Node.ELEMENT_NODE) {
			Element phanTuSinhVien = (Element) nutSinhVien;
			SinhVien sinhVien = new SinhVien();
//			Lấy danh sách các nút con của phần tử sinh viên
			NodeList danhSachNutConSinhVien = phanTuSinhVien.getChildNodes();
			for (int k = 0; k < danhSachNutConSinhVien.getLength(); k++) {
				Node nutConSinhVien = danhSachNutConSinhVien.item(k);
				 if (nutConSinhVien.getNodeType() == Node.ELEMENT_NODE) {
					Element phanTuConSinhVien = (Element) nutConSinhVien;
					switch (phanTuConSinhVien.getNodeName()) {
					case "masv":
						sinhVien.setMasv(phanTuConSinhVien.getTextContent());
						break;
					case "hoTen":
						sinhVien.setHoTen(phanTuConSinhVien.getTextContent());
						break;
					case "ngaySinh":
						sinhVien.setNgaySinh(phanTuConSinhVien.getTextContent());
						break;
					case "gioiTinh":
						sinhVien.setGioiTinh(phanTuConSinhVien.getTextContent());
						break;
					case "diemtb":
						sinhVien.setDiemtb(phanTuConSinhVien.getTextContent());
						break;								
					}
				}
			}
			return sinhVien;
		}
		return null;
	}
	
	private static Node layNutSinhVienBangMasv(String masv) {
//		Lấy danh sách nút sinh viên
		NodeList nutDanhSachSinhVien = DOCUMENT.getElementsByTagName("sinhVien");
		for (int i = 0; i < nutDanhSachSinhVien.getLength(); i++) {
			Node nutSinhVien = nutDanhSachSinhVien.item(i);
			if (nutSinhVien.getNodeType() == Node.ELEMENT_NODE) {
				Element phanTuSinhVien = (Element) nutSinhVien;
				
//				Kiểm tra mã sinh viên có khớp hay không, nếu khớp thì cập nhật
				Node nutMasv = phanTuSinhVien.getElementsByTagName("masv").item(0);
				if (nutMasv.getTextContent().equals(masv)) {
					return phanTuSinhVien;
				}
			}
		}
		return null;
	}
	
	public void themSinhVienBangMaLop(String maLop, SinhVien sinhVien) {
		Node nutLop = layNutLopBangMaLop(maLop);
		if (nutLop != null) {
//			Lấy node con danh sách sinh viên trong node lớp, nó là con nút thứ 5
			Node nutDanhSachSinhVien = nutLop.getChildNodes().item(5);		
			Element phanTuSinhVien = DOCUMENT.createElement("sinhVien");
			Element phanTuMasv = taoNutVanBan("masv", sinhVien.getMasv());
			phanTuSinhVien.appendChild(phanTuMasv);
			Element phanTuHoTen = taoNutVanBan("hoTen", sinhVien.getHoTen());
			phanTuSinhVien.appendChild(phanTuHoTen);
			Element phanTuNgaySinh = taoNutVanBan("ngaySinh", sinhVien.getNgaySinh());
			phanTuSinhVien.appendChild(phanTuNgaySinh);
			Element phanTuGioiTinh = taoNutVanBan("gioiTinh", sinhVien.getGioiTinh());
			phanTuSinhVien.appendChild(phanTuGioiTinh);
			Element phanTuDiemtb = taoNutVanBan("diemtb", sinhVien.getDiemtb());
			phanTuSinhVien.appendChild(phanTuDiemtb);
			nutDanhSachSinhVien.appendChild(phanTuSinhVien);
			DOMHelper.saveXMLContent(DOCUMENT, PATH);
		} else {
			System.out.println("Không tìm thấy lớp có mã lớp là " + maLop);
		}
	}
	
	private static Element taoNutVanBan(String tenNut, String vanBan) {
		Element nutPhanTu = DOCUMENT.createElement(tenNut);
		Text nutVanBan = DOCUMENT.createTextNode(vanBan);
		nutPhanTu.appendChild(nutVanBan);
		return nutPhanTu;
	}
	
	public void capNhatSinhVien(String masv, SinhVien sinhVien) {
		Node nutSinhVien = layNutSinhVienBangMasv(masv);
		if (nutSinhVien != null && nutSinhVien.getNodeType() == Node.ELEMENT_NODE) {
			Element phanTuSinhVien = (Element) nutSinhVien;
			Node nutHoTen = phanTuSinhVien.getElementsByTagName("hoTen").item(0);
			nutHoTen.setTextContent(sinhVien.getHoTen());
			Node nutNgaySinh = phanTuSinhVien.getElementsByTagName("ngaySinh").item(0);
			nutNgaySinh.setTextContent(sinhVien.getNgaySinh());
			Node nutGioiTinh = phanTuSinhVien.getElementsByTagName("gioiTinh").item(0);
			nutGioiTinh.setTextContent(sinhVien.getGioiTinh());
			Node nutDiemtb = phanTuSinhVien.getElementsByTagName("diemtb").item(0);
			nutDiemtb.setTextContent(sinhVien.getDiemtb());
		}
		DOMHelper.saveXMLContent(DOCUMENT, PATH);
	}
	
	public void xoaSinhVienBangMasv(String masv) {
		Node nutSinhVien = layNutSinhVienBangMasv(masv);
		if (nutSinhVien != null && nutSinhVien.getNodeType() == Node.ELEMENT_NODE) {
			nutSinhVien.getParentNode().removeChild(nutSinhVien);
		} else {
			System.out.println("Không tìm thấy sinh viên nào có mã sinh viên là " + masv);
		}
		DOMHelper.saveXMLContent(DOCUMENT, PATH);
	}
}
