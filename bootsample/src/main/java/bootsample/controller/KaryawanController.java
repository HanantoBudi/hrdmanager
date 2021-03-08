package bootsample.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bootsample.model.Karyawan;
import bootsample.service.KaryawanService;

@Controller
public class KaryawanController {

	@Autowired
	KaryawanService karyawanService;
		
	@GetMapping("/all-karyawans")
	public String allKaryawans(HttpServletRequest request) throws ParseException{
		request.setAttribute("karyawans", karyawanService.findAll());
		request.setAttribute("mode", "MODE_KARYAWAN");
		return "index";
	}
	
	@GetMapping("/all-karyawans-tgl-gabung")
	public String allKaryawansOrderByTanggalGabung(HttpServletRequest request){
		request.setAttribute("karyawans", karyawanService.findAllByOrderByTanggalGabungAsc());
		request.setAttribute("mode", "MODE_KARYAWAN_TGL_GABUNG");
		return "index";
	}
	
//	@GetMapping("/all-karyawans-ambil-cuti")
//	public String allKaryawansAmbilCuti(HttpServletRequest request){
//		request.setAttribute("karyawans", karyawanService.findKaryawanAmbilCuti());
//		request.setAttribute("mode", "MODE_KARYAWAN_AMBIL_CUTI");
//		return "index";
//	}
//	
//	@GetMapping("/all-karyawans-ambil-cuti-lebih-dari-satu")
//	public String allKaryawansAmbilCutiLebihDariSatu(HttpServletRequest request){
//		request.setAttribute("karyawans", karyawanService.findKaryawanAmbilCutiLebihDariSatu());
//		request.setAttribute("mode", "MODE_KARYAWAN_AMBIL_CUTI_LEBIH_DARI_SATU");
//		return "index";
//	}
	
	@GetMapping("/all-karyawans-tidak-ambil-cuti")
	public String allKaryawansTidakAmbilCuti(HttpServletRequest request){
		request.setAttribute("karyawans", karyawanService.findKaryawanTidakAmbilCuti());
		request.setAttribute("mode", "MODE_KARYAWAN_TIDAK_AMBIL_CUTI");
		return "index";
	}
	
	@GetMapping("/new-karyawans")
	public String newKaryawans(HttpServletRequest request){
		request.setAttribute("mode", "MODE_NEW_KARYAWAN");
		return "index";
	}
	
	@PostMapping("/save-karyawans")
	public String saveKaryawans(@ModelAttribute Karyawan karyawan, BindingResult bindingResult, HttpServletRequest request){
		karyawanService.save(karyawan);
		request.setAttribute("karyawans", karyawanService.findAll());
		request.setAttribute("mode", "MODE_KARYAWAN");
		return "index";
	}
	
	@GetMapping("/update-karyawans")
	public String updateKaryawans(@RequestParam int id, HttpServletRequest request) throws ParseException{
		Karyawan k = karyawanService.findOne(id);
		
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

		if (k.getTanggalGabung() != null) {
			String format = simpleDateFormat.format(k.getTanggalGabung());
			k.setTanggalGabungText(format);
		}
		
		if (k.getTanggalLahir() != null) {
			String format = simpleDateFormat.format(k.getTanggalLahir());
			k.setTanggalLahirText(format);
		}
		
		request.setAttribute("karyawan", k);
		request.setAttribute("mode", "MODE_UPDATE_KARYAWAN");
		return "index";
	}
	
	@GetMapping("/delete-karyawans")
	public String deleteKaryawans(@RequestParam int id, HttpServletRequest request){
		karyawanService.delete(id);
		request.setAttribute("karyawans", karyawanService.findAll());
		request.setAttribute("mode", "MODE_KARYAWAN");
		return "index";
	}
	
}