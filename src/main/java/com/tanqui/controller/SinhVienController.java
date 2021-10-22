package com.tanqui.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tanqui.model.SinhVien;
import com.tanqui.xml.SinhVienDOM;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/")
public class SinhVienController {
	private SinhVienDOM sinhVienDOM = new SinhVienDOM();
	
	@GetMapping("/sinhviens")
	public List<SinhVien> layTatCaSinhVien() {
		return sinhVienDOM.layTatCaSinhVien();
	}
	
	@GetMapping("/sinhviens/{maLop}")
	public List<SinhVien> layTatCaSinhVienBangMaLop(@PathVariable("maLop") String maLop) {
		return sinhVienDOM.layDanhSachSinhVienBangMaLop(maLop);
	}
	
	@GetMapping("/sinhvien/{masv}")
	public ResponseEntity<SinhVien> laySinhVienBangMasv(@PathVariable("masv") String masv) {
		SinhVien sinhVien = sinhVienDOM.laySinhVienBangMasv(masv);
		return new ResponseEntity<SinhVien>(sinhVien, HttpStatus.OK);
	}
	
	@PostMapping("/sinhvien/{maLop}")
	public ResponseEntity<Void> themSinhVienBangMaLop(@PathVariable("maLop") String maLop, @RequestBody SinhVien sinhVien) {
		sinhVienDOM.themSinhVienBangMaLop(maLop, sinhVien);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@PutMapping("/sinhvien/{masv}")
	public ResponseEntity<Void> capNhatSinhVienBangMaSinhVien(@PathVariable("masv") String masv, @RequestBody SinhVien sinhVien) {
		sinhVienDOM.capNhatSinhVien(masv, sinhVien);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/sinhvien/{masv}")
	public ResponseEntity<Void> xoaSinhVienBangMasv(@PathVariable("masv") String masv) {
		sinhVienDOM.xoaSinhVienBangMasv(masv);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
