package bootsample.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bootsample.dao.CutiRepository;
import bootsample.dao.KaryawanRepository;
import bootsample.model.Cuti;
import bootsample.model.Karyawan;

@Service
public class KaryawanService {

	@Autowired
	private KaryawanRepository karyawanRepo;
	
	@Autowired
	private CutiRepository cutiRepo;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	public List<Karyawan> findAll() {
		List<Karyawan> karyawans = (List<Karyawan>) karyawanRepo.findAll(); 
		return karyawans;
	}
	
	public List<Karyawan> findAllByOrderByTanggalGabungAsc() {
		List<Karyawan> karyawans = (List<Karyawan>) karyawanRepo.findAllByOrderByTanggalGabungAsc(); 
		List<Karyawan> findByLimit = new ArrayList<>(); 
		for (int i = 0; i < 3; i++) {  
			findByLimit.add(karyawans.get(i));
		}
		return findByLimit;
	}
	
//	public List<Karyawan> findKaryawanAmbilCuti() {
//		List<Karyawan> karyawans = new ArrayList<>();
//		List<Cuti> ambilCuti = (List<Cuti>) cutiRepo.findAll();
//		for (Cuti c : ambilCuti) {
//			Karyawan karyawan = karyawanRepo.findByNomorInduk(c.getNomorInduk());
//			karyawan.setTanggalCuti(c.getTanggalCuti());
//			karyawan.setKeterangan(c.getKeterangan());
//			karyawans.add(karyawan);
//		}
//		return karyawans;
//	}
//	
//	public List<Karyawan> findKaryawanAmbilCutiLebihDariSatu() {
//		List<Karyawan> karyawans = new ArrayList<>();
//		List<String> ambilCuti = findCutiLebihDariSatu();
//		List<Cuti> cuti = new ArrayList<>();
//		for (String c : ambilCuti) {
//			List<Cuti> cu = cutiRepo.findByNomorInduk(c);
//			cuti.addAll(cu);
//		}
//		for (Cuti c : cuti) {
//			Karyawan karyawan = new Karyawan();
//			karyawan = karyawanRepo.findByNomorInduk(c.getNomorInduk());
//			karyawan.setTanggalCuti(c.getTanggalCuti());
//			karyawan.setKeterangan(c.getKeterangan());
//			karyawans.add(karyawan);
//		}
//		return karyawans;
//	}
//	
//	public List<String> findCutiLebihDariSatu() {
//		String query = "SELECT nomor_induk FROM cuti GROUP BY nomor_induk HAVING COUNT(*) > 1";
//		Map<String, Object> params = new HashMap<String, Object>();
//		try {
//			return namedParameterJdbcTemplate.query(query, params, new RowMapper<String>() {
//
//						@Override
//						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
//							String result = rs.getString("NOMOR_INDUK");
//							return result;
//						}
//
//					});
//		} catch (RuntimeException e) {
//			throw new RuntimeException("Error when get nomor induk. ", e);
//		}
//	}
	
	public List<Karyawan> findKaryawanTidakAmbilCuti() {
		List<Karyawan> karyawans = new ArrayList<>();
		List<String> nomorInduks= findKaryawanTidakCuti();
		for(String n : nomorInduks) {
			Karyawan karyawan = karyawanRepo.findByNomorInduk(n);
			karyawans.add(karyawan);
		}
		return karyawans;
	}
	
	public List<String> findKaryawanTidakCuti() {
		String query = "select k.nomor_induk from karyawan k where not exists(select null from cuti c where k.nomor_induk = c.nomor_induk)";
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			return namedParameterJdbcTemplate.query(query, params, new RowMapper<String>() {

						@Override
						public String mapRow(ResultSet rs, int rowNum) throws SQLException {
							String result = rs.getString("NOMOR_INDUK");
							return result;
						}

					});
		} catch (RuntimeException e) {
			throw new RuntimeException("Error when get nomor induk. ", e);
		}
	}
	
	public Karyawan findOne(int i) {
		return karyawanRepo.findOne(i);
	}
	
	public void save(Karyawan karyawan) {
		karyawanRepo.save(karyawan);
	}
	
	public void delete(int id) {
		karyawanRepo.delete(id);
	}

	public Karyawan findKaryawan(int id){
		return karyawanRepo.findOne(id);
	}
	
	public String findNamaKaryawan(String nomorInduk) {
		return karyawanRepo.findNamaByNomorInduk(nomorInduk);
	}
	
	public Karyawan findByNomorInduk(String nomorInduk) {
		return karyawanRepo.findByNomorInduk(nomorInduk);
	}
	
}