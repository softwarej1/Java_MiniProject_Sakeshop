package classes;

class RegisterDTO{
	private String name; // 회원 이름
	private String birth; // 회원 생년월일
	private String id; // 회원 아이디
	private String password; // 회원 비밀번호
	private String[] cart; // 장바구니
	// members : int => 회원수
	private int members;

	// RegisterDTO : 생성자 매개변수 1개 members : int -> 회원수
	RegisterDTO(int members){
		this.members = members;
	}

	// RegisterDTO : 생성자 매개변수 5개, name, birth, id, password, cart[] => String
	RegisterDTO(String name, String birth, String id, String password, String[] cart){
		this.name = name;
		this.birth = birth;
		this.id = id;
		this.password = password;
		this.cart = cart;
	}

	// getName 이름 : getter
	public String getName(){
		return name;
	}

	// setName 이름 : setter
	public void setName(String name){
		this.name = name;
	}

	// getBirth 생년월일 : getter
	public String getBirth(){
		return birth;
	}

	// setBirth 생년월일 : setter
	public void setBirth(String birth){
		this.birth = birth;
	}

	// getId 아이디 : getter
	public String getId(){
		return id;
	}

	// setId 아이디 : setter
	public void setId(String id){
		this.id = id;
	}

	// getPassword 비밀번호 : getter
	public String getPassword(){
		return password;
	}

	// setPassowrd 비밀번호 : setter
	public void setPassword(String password){
		this.password = password;
	}

	// getMenbers 회원수 : getter
	public int getMembers(){
		return members;
	}

	// setMembers 회원수 : setter
	public void setMembers(int members){
		this.members = members;
	}
}