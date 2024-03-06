package classes;

class AlcholDTO{
	private String number; // 고유번호
	private String name; // 술 이름 
	private String madeloc; // 주조사
	private String abv;  //알코올 도수
	private int price; // 가격

	// Constructor : 생성자 매개변수 5개 
	AlcholDTO(String number, String name, String madeloc, String abv, int price){
		this.number = number;
		this.name = name;
		this.price = price;
		this.madeloc = madeloc;
		this.abv = abv;

	}

	// 고유번호 : getter => String
	public String getNumber(){
		return number;
	}

	// 고유번호 : setter => void
	public void setNumber(String number){
		this.number = number;
	}

	// 술 이름 : getter => String
	public String getName(){
		return name;
	}

	// 술 이름 : setter => void
	public void setName(String name){
		this.name = name;
	}

	// 주조사 : getter => String
	public String getMadeloc(){
		return madeloc;
	}

	// 주조사 : setter => void
	public void setMadeloc(String madeloc){
		this.madeloc = madeloc;
	}
	// 알코올 도수 : getter => String
	public String getAbv(){
		return abv;
	}
	// 알콜올 도수: setter => void
	public void setAbv(String abv){
		this.abv = abv;
	}
	// 가격 : getter => int
	public int getPrice(){
		return price;
	}

	// 가격 : setter => void
	public void setPrice(int price){
		this.price = price;
	}
}
