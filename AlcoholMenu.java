package classes;
import java.util.Scanner;

class AlcoholMenu{
	UserAPI userAPI = new UserAPI();
	Scanner scanner = new Scanner(System.in);
	RegisterDTO[] signDTO;

	// 메인메뉴 -> 사케 : 준마이 긴조 다이긴조 혼죠조
	// 메인메뉴에서 사케, 소주, 맥주 메뉴를 클릭했을 시 나오는 메뉴
	// menuArr(_no) : String 메서드 사용 -> 이전에 입력한 번호를 이용하여 세부 메뉴로 가기 위해 배열로 입력받아 출력 처리
	// if(arr.length) 사용 이유 -> arr.length가 5 : 사케, 4 : 소주, 맥주
	void alcoholMenu(int _no, AlcholDTO[] achDTO){
		while(true){
			String[] arr = menuArr(_no);
			System.out.printf("\n<< %s >>\n",arr[0]);
			userAPI.mLine('=', 50);
			for (int idx = 1 ; idx < arr.length ; idx++ ){
				System.out.printf("\n%d. %s\n", idx, arr[idx]);
			}

			System.out.println("\nQ. 메인메뉴");
			userAPI.mLine('-', 50);
			System.out.print("\n메뉴 선택 : ");
			String input = scanner.nextLine();
			if((input.equalsIgnoreCase("q"))){
				System.out.println("\n\t^ 메인메뉴로 이동합니다. \n");
				break;
			}

			if(arr.length == 5){
				switch (input){
				case "1" :
				case "2" :
				case "3" :
				case "4" :
					alcoList(achDTO, _no, input);
					break;
				default :
					System.out.println("^ 예외처리");
				}
			}else{
				switch (input){
				case "1" :
				case "2" :
				case "3" :
					alcoList(achDTO, _no, input);
					break;
				default :
					System.out.println("^ 예외처리");
				}
			}
		}
	}

	// Ex ] 준마이 -> 반슈잇콘 준마이 초카라쿠치 메뉴 이동
	// 준마이 : 반슈잇콘 준마이 초카라쿠치, 소쿠 준마이 미야마니시키... 등등 각각의 메뉴의 세부 이름으로 나오게 된다.
	// alcoList() : void로 전달받은 str -> 고유번호 앞자리 2글자로 Numbers에서 판별하여 나온 이름을 출력한다.
	// 판별한 numbers를 arr[cnt] 에 넣어 상품 정보 메뉴로 이동하게 된다.
	void sakeMenu(AlcholDTO[] achDTO, String str, String menuName){
		while (true){
			int cnt = 1;
			String[] arr = new String[5];
			System.out.printf("\n<< %s >>\n", menuName); // menuName : 메뉴 이름
			userAPI.mLine('=',50);
			menuDescription(str); // menuDescription 고유번호 앞에서 2자리로 판별하여 설명 내용 출력
			userAPI.mLine('=',50);
			// achDTO.length만큼 돌려서 Number의 2자리 수가 str 2자리가 모두 같을 때 세부 메뉴 출력문이 나오게 된다.
			for (int idx = 0 ; idx < achDTO.length ; idx++ ){
				if((achDTO[idx].getNumber().charAt(0) == str.charAt(0)) && ((achDTO[idx].getNumber().charAt(1) == str.charAt(1)))){
					System.out.printf("\n%d. %s \n", cnt, achDTO[idx].getName());
					arr[cnt - 1] = achDTO[idx].getNumber();
					cnt++;
					continue;
				}
			}
			System.out.print("\n\nQ. 뒤로가기 \n");
			userAPI.mLine('-', 50);
			System.out.print("\n메뉴 선택 : ");

			String input = scanner.nextLine();
			if((input.equalsIgnoreCase("q"))){
				System.out.println("\n\t^ 이전화면으로 이동합니다. \n");
				break;
			}

			switch (input){
			case "1" :
			case "2" :
			case "3" :
				productMenu(achDTO, arr, input);
				break;
			default :
				System.out.println("^ 예외처리");
			}
		}
	}

// 과실주 세부 메뉴 -> 동작과정은 sakeMenu() 메서드와 동일하다.
// 다만 과실주는 'F' 한글자만 achDTO.length까지 검사하여 맞는 메뉴를 출력하고 arr[cnt] 에 넣어 productMenu() : void 메서드로 보낸다.
	void fruitWineMenu(AlcholDTO[] achDTO){
		while (true){
			int cnt = 1;
			String[] arr = new String[4];
			System.out.println("\n<< 과실주 >>");
			userAPI.mLine('=', 50);
			for (int idx = 0; idx < achDTO.length ; idx++ ){
				if(achDTO[idx].getNumber().charAt(0) == 'F'){
					System.out.printf("\n%d. %s \n", cnt, achDTO[idx].getName());
					arr[cnt - 1] = achDTO[idx].getNumber();
					cnt++;
					continue;
				}
			}
			System.out.print("\n\nQ. 뒤로가기 \n");
			userAPI.mLine('-', 50);
			System.out.print("\n메뉴 선택 : ");

			String input = scanner.nextLine();
			if((input.equalsIgnoreCase("q"))){
				System.out.println("\n\t^ 이전 메뉴로 이동합니다. \n");
				break;
			}

			switch (input){
				case "1" :
				case "2" :
				case "3" :
				case "4" :
					productMenu(achDTO, arr, input);
					break;
				default :
					System.out.println("^ 예외처리");
				}
		}
	}

// productMenu : void 상품 정보가 출력된다. 이름, 주도사, 알콜 도수, 가격
// 이전 메뉴에서 보내온 고유코드로 achDTO.length까지 돌려서 고유번호의 배열의 위치를 알아 해당 변수로 achDTO[] 배열을 이용하여 술 정보를 출력한다.
// 장바구니 구현 미정
	void productMenu(AlcholDTO[] achDTO, String[] arr, String str1){
		while(true){
			String str = arr[Integer.parseInt(str1) - 1];
			int achArrNumber = 0;
			for (int idx = 0; idx < achDTO.length ; idx++ ){
				if(achDTO[idx].getNumber().equalsIgnoreCase(str) == true){
					achArrNumber = idx;
				}
			}
			System.out.printf("\n<< %s >>\n", achDTO[achArrNumber].getName());
			userAPI.mLine('=',50);
			System.out.printf("\n주도사 : %s\n", achDTO[achArrNumber].getMadeloc());
			System.out.printf("\n알콜 도수 : %s\n", achDTO[achArrNumber].getAbv());
			System.out.printf("\n가격 : %d\n", achDTO[achArrNumber].getPrice());
			System.out.printf("\nQ. 뒤로가기 \n");
			userAPI.mLine('-',50);
			System.out.print("\n메뉴 선택 : ");
			String input = scanner.nextLine();
			if((input.equalsIgnoreCase("q"))){
				System.out.println("\n\t^ 이전화면으로 이동합니다. \n");
				break;
			}
		}
	}

	// alcoholMenu() 메서드에서 받은 문자열 고유코드로 SJ... 등의 코드를 판별하여 fruitWineMenu(), sakeMenu()로 술 설명 출력한다.
	void menuDescription(String str){
		if(str == "SJ"){
			System.out.printf("\n쌀과 물만을 사용하여 양조되며, 어떠한 첨가물도 사용되지 않습니다.\n순수한 쌀의 맛과 향이 강조되는 깨끗하고 부드러운 맛을 가지고 있습니다.\n");
		}else if(str == "SG"){
			System.out.printf("\n특정 등급의 쌀과 정제된 물로 양조되며, 발효 및 숙성 기간이 상대적으로 길며,\n고급스러운 맛과 향을 가지고 있습니다.\n");
		}else if(str == "SD"){
			System.out.printf("\n다이긴조는 일본의 고급 사케 중 하나로, 정교한 양조 기술과 엄선된 재료를 사용하여 만들어지며, \n풍부한 향과 깊은 맛을 갖추고 있습니다.\n");
		}else if(str == "SH"){
			System.out.printf("\n단순한 재료를 사용하는 특징이 있으며, 숙성 기간이 짧으며,\n가볍고 깨끗한 맛과 향이 특징입니다.\n");
		}else if(str == "JT"){
			System.out.printf("\n보리를 사용하여 양조된 소주입니다. 보리소주는 감칠맛이 풍부하고 진한 풍미를 가지고 있으며,\n일본의 술 중에서도 고급스러운 맛으로 평가되는 특징이 있습니다.\n");
		}else if(str == "JS"){
			System.out.printf("\n쌀을 주 원료로 사용하여 양조되는 술을 말합니다.\n 쌀소주는 맛이 부드럽고 깔끔하며, 일본 요리와 잘 어울리는 특성을 가지고 있습니다.\n");
		}else if(str == "JA"){
			System.out.printf("\n오키나와 지방에서만 생산되는 특별한 술로, 주로 쌀이나 찹쌀로 만들어지는 술입니다.\n");
		}else if(str == "BA"){
			System.out.printf("\n일본을 대표하는 맥주 브랜드 중 하나로 널리 알려져 있는 브랜드 입니다.\n아사히는 가볍고 시원한 맛으로 유명합니다.\n");
		}else if(str == "BS"){
			System.out.printf("\n고품질의 몰트를 사용하여 깊고 부드러운 맛을 자랑하며,\n부드럽고 깔끔한 특징으로 유명합니다.\n");
		}else if(str == "BG"){
			System.out.printf("\n일본의 대표적인 맥주 브랜드 중 하나입니다.\n부드럽고 깔끔한 맛을 가지고 있으며, 상쾌하고 시원한 맛으로 인기가 있습니다.\n");
		}
	}

	
	// 메인메뉴에서 입력받은 정수형 번호를 이용하여 alcoholMenu() 메서드에서 사케, 소주, 맥주 메뉴를 분활해서 출력을 도와주는 코드
	String[] menuArr(int _no){

		String[] arr = new String[0];
			 
		switch (_no){
		case 1 : 
			arr = new String[5];
			arr[0] = "사케";
			arr[1] = "준마이";
			arr[2] = "긴조";
			arr[3] = "다이긴조";
			arr[4] = "혼죠조";
			break;
		case 2 :
			arr = new String[4];
			arr[0] = "소주";
			arr[1] = "보리소주";
			arr[2] = "쌀소주";
			arr[3] = "아와모리";
			break;
		case 4 :
			arr = new String[4];
			arr[0] = "맥주";
			arr[1] = "아사히";
			arr[2] = "산토리(suntory)";
			arr[3] = "기린(Kirin)";
			break;
		}
		return arr;
	}

// alcoholMenu() 메서드에서 입력받은 input 정보를 이용하여 sakeMenu() 메서드로 str 문자열을 보내 출력해주는 구문
// achDTO[], _no : 메인메뉴에서 입력, input : alcoholMenu() 메서드에서 입력
// 입력받은 정보를 통해 sakeMenu() 메서드로 str, arr[] -> Ex ] 준마이, 긴조, 다이긴조, 혼죠조를 넣기 위해 만든 배열
	void alcoList(AlcholDTO[] achDTO, int _no, String input){
		String str = "";
		String[] arr = menuArr(_no);
		if(_no == 1){
			if(Integer.parseInt(input) == 1){
				str = "SJ";
				sakeMenu(achDTO, str, arr[Integer.parseInt(input)]);
			}
			else if(Integer.parseInt(input) == 2){
				str = "SG";
				sakeMenu(achDTO, str, arr[Integer.parseInt(input)]);
			}
			else if(Integer.parseInt(input) == 3){
				str = "SD";
				sakeMenu(achDTO, str, arr[Integer.parseInt(input)]);
			}
			else if(Integer.parseInt(input) == 4){
				str = "SH";
				sakeMenu(achDTO, str, arr[Integer.parseInt(input)]);
			}
		}else if(_no == 2){
			if(Integer.parseInt(input) == 1){
				str = "JT";
				sakeMenu(achDTO, str, arr[Integer.parseInt(input)]);
			}
			else if(Integer.parseInt(input) == 2){
				str = "JS";
				sakeMenu(achDTO, str, arr[Integer.parseInt(input)]);
			}
			else if(Integer.parseInt(input) == 3){
				str = "JA";
				sakeMenu(achDTO, str, arr[Integer.parseInt(input)]);
			}
		}else if(_no == 4){
			if(Integer.parseInt(input) == 1){
				str = "BA";
				sakeMenu(achDTO, str, arr[Integer.parseInt(input)]);
			}
			else if(Integer.parseInt(input) == 2){
				str = "BS";
				sakeMenu(achDTO, str, arr[Integer.parseInt(input)]);
			}
			else if(Integer.parseInt(input) == 3){
				str = "BG";
				sakeMenu(achDTO, str, arr[Integer.parseInt(input)]);
			}
		}
	}
}
