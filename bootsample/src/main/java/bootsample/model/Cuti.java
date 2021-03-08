package bootsample.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name="cuti")
public class Cuti implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nomorInduk;
	@Temporal(TemporalType.TIMESTAMP)
	private Date tanggalCuti;
	private Integer lamaCuti;
	private String keterangan;
	@Transient
	private String nama;
	@Transient
	private String tanggalCutiText;

	public Cuti() {}
	
	public Cuti(String nomorInduk, Date tanggalCuti, Integer lamaCuti, String keterangan) {
		super();
		this.nomorInduk = nomorInduk; 
		this.tanggalCuti = tanggalCuti;
		this.lamaCuti = lamaCuti;
		this.keterangan = keterangan;
	}

	@Override
	public String toString() {
		return "Cuti [id=" + id + ", nomor induk= " + nomorInduk 
				+ " tanggal cuti=" + tanggalCuti + ", lama cuti=" + lamaCuti 
				+ " keterangan=" + keterangan + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomorInduk() {
		return nomorInduk;
	}

	public void setNomorInduk(String nomorInduk) {
		this.nomorInduk = nomorInduk;
	}

	public Date getTanggalCuti() {
		return tanggalCuti;
	}

	public void setTanggalCuti(Date tanggalCuti) {
		this.tanggalCuti = tanggalCuti;
	}

	public Integer getLamaCuti() {
		return lamaCuti;
	}

	public void setLamaCuti(Integer lamaCuti) {
		this.lamaCuti = lamaCuti;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTanggalCutiText() {
		return tanggalCutiText;
	}

	public void setTanggalCutiText(String tanggalCutiText) {
		this.tanggalCutiText = tanggalCutiText;
	}

}