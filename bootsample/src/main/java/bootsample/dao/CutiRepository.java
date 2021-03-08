package bootsample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import bootsample.model.Cuti;

public interface CutiRepository extends CrudRepository<Cuti, Integer> {
	
	List<Cuti> findByNomorInduk(String nomorInduk);
	
}
