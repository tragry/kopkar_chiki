package tiara.anggreyani.chicken.Model.Home;

public class HomeResponse {
	private int sisa_plafon;
	private int kurangan_pinjaman;
	private int simpanan;
	private String status;

	public int getSisaPlafon(){
		return sisa_plafon;
	}

	public int getKuranganPinjaman(){
		return kurangan_pinjaman;
	}

	public int getSimpanan(){
		return simpanan;
	}

	public String getStatus(){
		return status;
	}
}
