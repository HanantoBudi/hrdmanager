package bootsample.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import bootsample.dao.CutiRepository;
import bootsample.dao.KaryawanRepository;
import bootsample.model.Cuti;
import bootsample.model.Karyawan;

@Service
public class CutiService {

	@Autowired
	private CutiRepository cutiRepo;
	
	@Autowired
	private KaryawanRepository karyawanRepo;
	
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public List<Cuti> findAll() {
		List<Cuti> cutis = (List<Cuti>) cutiRepo.findAll();
		return cutis;
	}
	
	public List<Cuti> findKaryawanAmbilCutiLebihDariSatu() {
		List<String> ambilCuti = findCutiLebihDariSatu();
		List<Cuti> cuti = new ArrayList<>();
		for (String c : ambilCuti) {
			List<Cuti> cu = cutiRepo.findByNomorInduk(c);
			cuti.addAll(cu);
		}
		for (Cuti c : cuti) {
			Karyawan karyawan = karyawanRepo.findByNomorInduk(c.getNomorInduk());
			c.setNama(karyawan.getNama());
		}
		return cuti;
	}
	
	public List<String> findCutiLebihDariSatu() {
		String query = "SELECT nomor_induk FROM cuti GROUP BY nomor_induk HAVING COUNT(*) > 1";
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

	public Cuti findOne(int i) {
		return cutiRepo.findOne(i);
	}

	public void save(Cuti Cuti) {
		cutiRepo.save(Cuti);
	}

	public void delete(int id) {
		cutiRepo.delete(id);
	}

	public Cuti findCuti(int id) {
		return cutiRepo.findOne(id);
	}

}
