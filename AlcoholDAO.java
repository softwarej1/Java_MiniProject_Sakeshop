package classes;
import java.util.Scanner;

class AlcoholDAO{
	UserAPI userAPI = new UserAPI();
	SignInUp signInUp = new SignInUp();
	AlcoholMenu ahMenu = new AlcoholMenu();
	Scanner scanner = new Scanner(System.in);
	RegisterDB regdb = new RegisterDB();
	RegisterDTO regDTO = new RegisterDTO(regdb.members);
	RegisterDTO[] signDTO = new RegisterDTO[regdb.registerAry.length];
	AlcoholDB achdb = new AlcoholDB();
	AlcholDTO[] achDTO = new AlcholDTO[achdb.alcoholDB.length];


	RegisterDTO[] signDtoSet(){
		String name;
		String birth;
		String id;
		String password;
		String[] cart;

		for (int idx = 0 ; idx < signDTO.length ; idx++ ){
			name = "";
			birth = "";
			id = "";
			password = "";
			cart = new String[regdb.cart.length];
			signDTO[idx] = new RegisterDTO(name, birth, id, password, cart);
		}
		return signDTO;
	}

	// alchoDTO[] : dtoSet() 메서드 -> 고유번호, 이름, 주도수, 알콜 도수, 가격
	// achDTO[] 배열에 데이터베이스에 해당하는 내용을 전부 포인터화 시켜 넣는다.
	// 접근 방식 : getter -> 데이터 가져옴, setter -> 데이터 동기화
	AlcholDTO[] dtoSet(){
		String number; // 고유번호
		String name;  // 이름 
		String madeloc; // 주도수
		String abv; // 알콜 도수
		int price;  // 가격

		for (int idx = 0 ; idx < achdb.alcoholDB.length ; idx++ ){
			number = achdb.alcoholDB[idx][0];
			name = achdb.alcoholDB[idx][1];
			madeloc = achdb.alcoholDB[idx][2];
			abv = achdb.alcoholDB[idx][3];
			price = achdb.price[idx];
			achDTO[idx] = new AlcholDTO(number, name, madeloc, abv, price);
		}
		return achDTO;
	}

	void mainPrint(){
		signDtoSet();
		menuPrint(regDTO);
	}

	// menuPrint() : void -> 로그인, 회원가입 이동 메서드
	// input : String -> L : 로그인 메뉴, R : 회원가입 메뉴 이동
	// Q : 입력 시 시스템 종료
	// 나머지 예외처리 
	void menuPrint(RegisterDTO regDTO){
		while(true){
			System.out.println("\n\t\t<< 사케 베스트 >>");
			userAPI.mLine('=', 50);
			System.out.println("\n\tL. 로그인");
			System.out.println("\n\tR. 회원가입");
			System.out.println("\n\tQ. 종료");
			userAPI.mLine('-', 50);

			System.out.print("\n메뉴 선택 : ");
			String input = scanner.nextLine();

			if((input.equalsIgnoreCase("q"))){
				System.out.println("\n\t^ 시스템을 종료합니다. \n");
				break;
			}

			switch (input){
			case "L" : case "l" :
				signInUp.signInMenuPrint(regDTO, signDTO);
				if(signInUp.status == true){
					dtoSet();
					alcoholMainMenu(achDTO);
				}
				break;
			case "R" : case "r" :
				signInUp.signUpMenuPrint(regDTO, signDTO);
				break;
			
			default :
				System.out.println("예외처리");
			}
		}
	}

	// alcoholMainMenu() : void 메서드
	// 로그인이 완료하게 되면 나오는 화면
	// Q : 로그아웃, 로그아웃 화면으로 이동
	// exit : 시스템 종료 
	void alcoholMainMenu(AlcholDTO[] achDTO){
		while(true){
			System.out.println("\n\n<< 메인메뉴 >>");
			userAPI.mLine('=',50);
			System.out.println("\n1. 사케(日本酒) \t\t Q. 로그아웃");
			System.out.println("\n2. 소주 \t\t\t EXIT. 시스템 종료");
			System.out.println("\n3. 과실주");
			System.out.println("\n4. 맥주(ビ-ル)");
			userAPI.mLine('-',50);
			System.out.print("\n메뉴 선택 : ");
			String input = scanner.nextLine();

			if (input.equalsIgnoreCase("q")){
				System.out.println("\n\t^ 로그아웃합니다. \n");
				break;
			}

			if (input.equalsIgnoreCase("exit")){
				System.out.println("\n\t^ 시스템을 종료합니다. \n");
				System.exit(0);
			}

			switch (input){
			case "1" :
				// System.out.println("사케 알고리즘");
				ahMenu.alcoholMenu(1, achDTO);
				break;
			case "2" : 
				// System.out.println("소주 알고리즘");
				ahMenu.alcoholMenu(2, achDTO);
				break;
			case "3" : 
				// System.out.println("과실주 알고리즘");
				ahMenu.fruitWineMenu(achDTO);
				break;
			case "4" : 
				// System.out.println("맥주 알고리즘");
				ahMenu.alcoholMenu(4, achDTO);
				break;
			default :
				System.out.println("^ 예외처리");
			}
		}
	}
}
