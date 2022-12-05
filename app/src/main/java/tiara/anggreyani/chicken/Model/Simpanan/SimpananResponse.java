package tiara.anggreyani.chicken.Model.Simpanan;

public class SimpananResponse {
	private String code;
	private String updated_at;
	private String nilai_potongan;
	private String created_at;
	private String total_simpanan;
	private Object total_penarikan;
	private int id;
	private String anggota_id;
	private String jenis_simpanan_id;
	private String status;
	private String name_js;

	public String getCode(){
		return code;
	}

	public String getUpdatedAt(){
		return updated_at;
	}

	public String getNilaiPotongan(){
		return nilai_potongan;
	}

	public String getCreatedAt(){
		return created_at;
	}

	public String getTotalSimpanan(){
		return total_simpanan;
	}

	public Object getTotalPenarikan(){
		return total_penarikan;
	}

	public int getId(){
		return id;
	}

	public String getAnggotaId(){
		return anggota_id;
	}

	public String getJenisSimpananId(){
		return jenis_simpanan_id;
	}

	public String getStatus(){
		return status;
	}

	public String getNameJs(){
		return name_js;
	}
}
