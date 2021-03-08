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

@Entity(name="karyawan")
public class Karyawan implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String nomorInduk;
	private String nama;
	private String alamat;
	@Temporal(TemporalType.TIMESTAMP)
	private Date tanggalLahir;
	@Temporal(TemporalType.TIMESTAMP)
	private Date tanggalGabung;
	@Transient
	private String tanggalLahirText;
	@Transient
	private String tanggalGabungText;
	
	public Karyawan() {}
	
	public Karyawan(String nomorInduk, String nama, String alamat, Date tanggalLahir, Date tanggalGabung) {
		super();
		this.nomorInduk = nomorInduk; 
		this.nama = nama;
		this.alamat = alamat;
		this.tanggalLahir = tanggalLahir;
		this.tanggalGabung = tanggalGabung;
	}

	@Override
	public String toString() {
		return "Karyawan [id=" + id + ", nomor induk= " + nomorInduk + " nama=" + nama + ", alamat=" + alamat + ", "
				+ " tanggal lahir=" + tanggalLahir + ", tanggal gabung =" + tanggalGabung + "]";
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

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getAlamat() {
		return alamat;
	}

	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public Date getTanggalGabung() {
		return tanggalGabung;
	}

	public void setTanggalGabung(Date tanggalGabung) {
		this.tanggalGabung = tanggalGabung;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTanggalLahirText() {
		return tanggalLahirText;
	}

	public void setTanggalLahirText(String tanggalLahirText) {
		this.tanggalLahirText = tanggalLahirText;
	}

	public String getTanggalGabungText() {
		return tanggalGabungText;
	}

	public void setTanggalGabungText(String tanggalGabungText) {
		this.tanggalGabungText = tanggalGabungText;
	}

}