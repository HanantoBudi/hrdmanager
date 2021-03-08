package bootsample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import bootsample.model.Karyawan;

public interface KaryawanRepository extends CrudRepository<Karyawan, Integer> {

	@Query("select nama from karyawan where nomor_induk = :nomor_induk") 
    String findNamaByNomorInduk(@Param("nomor_induk") String nomorInduk);
	
	Karyawan findByNomorInduk(String nomorInduk);
	
	List<Karyawan> findAllByOrderByTanggalGabungAsc();
	
}
