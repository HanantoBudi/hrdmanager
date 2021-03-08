package bootsample.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bootsample.model.Cuti;
import bootsample.service.CutiService;
import bootsample.service.KaryawanService;

@Controller
public class CutiController {

	@Autowired
	CutiService cutiService;
	
	@Autowired
	KaryawanService karyawanService;
	
	@GetMapping("/all-cutis")
	public String allCutis(HttpServletRequest request){
		List<Cuti> cutis = cutiService.findAll();
		for (Cuti cuti:cutis) {
			String nama = karyawanService.findByNomorInduk(cuti.getNomorInduk()).getNama();
			cuti.setNama(nama);
		}
		request.setAttribute("cutis", cutis);
		request.setAttribute("mode", "MODE_CUTI");
		return "index";
	}
	
	@GetMapping("/all-karyawans-ambil-cuti")
	public String allKaryawansAmbilCuti(HttpServletRequest request){
		List<Cuti> cutis = cutiService.findAll();
		for (Cuti cuti:cutis) {
			String nama = karyawanService.findByNomorInduk(cuti.getNomorInduk()).getNama();
			cuti.setNama(nama);
		}
		request.setAttribute("karyawans", cutis);
		request.setAttribute("mode", "MODE_KARYAWAN_AMBIL_CUTI");
		return "index";
	}
	
	@GetMapping("/all-karyawans-ambil-cuti-lebih-dari-satu")
	public String allKaryawansAmbilCutiLebihDariSatu(HttpServletRequest request){
		request.setAttribute("karyawans", cutiService.findKaryawanAmbilCutiLebihDariSatu());
		request.setAttribute("mode", "MODE_KARYAWAN_AMBIL_CUTI_LEBIH_DARI_SATU");
		return "index";
	}
	
	@GetMapping("/new-cutis")
	public String newCutis(HttpServletRequest request){
		request.setAttribute("mode", "MODE_NEW_CUTI");
		return "index";
	}
	
	@PostMapping("/save-cutis")
	public String saveCutis(@ModelAttribute Cuti cuti, BindingResult bindingResult, HttpServletRequest request){
		cutiService.save(cuti);
		List<Cuti> cutis = cutiService.findAll();
		for (Cuti c : cutis) {
			String nama = karyawanService.findByNomorInduk(c.getNomorInduk()).getNama();
			c.setNama(nama);
		}
		request.setAttribute("cutis", cutis);
		request.setAttribute("mode", "MODE_CUTI");
		return "index";
	}
	
	@GetMapping("/update-cutis")
	public String updateCutis(@RequestParam int id, HttpServletRequest request){
		Cuti c = cutiService.findOne(id);
		
		String pattern = "MM/dd/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		
		if (c.getTanggalCuti() != null) {
			String format = simpleDateFormat.format(c.getTanggalCuti());
			c.setTanggalCutiText(format);
		}
		
		request.setAttribute("cuti", c);
		request.setAttribute("mode", "MODE_UPDATE_CUTI");
		return "index";
	}
	
	@GetMapping("/delete-cutis")
	public String deleteCutis(@RequestParam int id, HttpServletRequest request){
		cutiService.delete(id);
		List<Cuti> cutis = cutiService.findAll();
		for (Cuti c : cutis) {
			String nama = karyawanService.findByNomorInduk(c.getNomorInduk()).getNama();
			c.setNama(nama);
		}
		request.setAttribute("cutis", cutis);
		request.setAttribute("mode", "MODE_CUTI");
		return "index";
	}
	
}
