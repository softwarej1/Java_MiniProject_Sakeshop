package classes;
import java.util.Scanner;

class SignInUp{
	UserAPI userAPI = new UserAPI();
	Scanner scanner = new Scanner(System.in);
	// status : 로그인 상태 확인
	boolean status = false;
	// 이름, 생년월일, 아이디, 비밀번호, 비밀번호 다시 입력 : String 입력 변수
	String inputName, inputBirth, inputId, inputPWD, inputAgainPWD;
	
	// 회원가입 메뉴 : 입력받은 회원정보를 유효성 검사 후 회원 DB에 넣는 작업
	// getter, setter : 이전에 입력 받은 정보가 있다면 다시 동기화 시켜 다음 배열에 회원정보 넣어서 업데이트
	// regDTO : RegisterDTO 회원 생성자 매개변수를 받아 데이터 사용
	void signUpMenuPrint(RegisterDTO regDTO, RegisterDTO[] signDTO){

		// 회원가입 화면 Frame
		loop : while(true){
			userAPI.mLine('=', 50);
			System.out.println("\n\t\t<< 회원가입 >>");
			userAPI.mLine('=', 50);
			System.out.println("\n\t\t회원가입 서비스");
			System.out.println("\n\n\n\tQ. 뒤로가기");
			userAPI.mLine('-', 50);

			// Name : 회원 이름
			// Q -> 메뉴로 이동
			// 빈 값, 숫자, 6글자 이내로 입력 받을 수 있다.
			nameChk : while(true){
				System.out.println("숫자를 제외한 6글자 이하로 입력해주세요.");
				System.out.print("\n이름을 입력하세요 : ");
				inputName = scanner.nextLine();
				if((inputName.equalsIgnoreCase("q"))){
					System.out.println("\n\t^ 시스템을 종료합니다. \n");
					break loop;
				}
				char[] inputNameChk = inputName.toCharArray();
				for(int idx = 0; idx <inputNameChk.length; idx++){
					 if (inputNameChk[idx] > 48 && inputNameChk[idx] < 57){
						System.out.println("숫자는 입력 할 수 없습니다.");
						continue nameChk;
					}
				}
				if(inputNameChk.length == 0){
					System.out.println("다시 입력해주세요.");
					continue nameChk;
				}else if(inputNameChk.length > 6){
					System.out.println("6글자 이하로 입력하세요.");
					continue nameChk;
				}
				break;
			}

			// Birth : 생년월일
			// 조건 : 생년월일 8자리 입력
			// 2024년도 기준으로 성인만 입력이 가능하다.
			birthChk : while(true){
				System.out.print("\n생년월일 8자리를 입력하세요 : ");
				inputBirth = scanner.nextLine();

				if((inputBirth.equalsIgnoreCase("q"))){
					System.out.println("\n\t^ 시스템을 종료합니다. \n");
					break loop;
				}
				char[] inputBirthChk = inputBirth.toCharArray();
				for (int idx=0;idx<inputBirthChk.length;idx++){
					if (inputBirthChk[idx]<48 || inputBirthChk[idx]>57){
						System.out.println("숫자만 입력해주세요.");
						continue birthChk;
					}
				}
				if(inputBirthChk.length == 0){
					System.out.println("다시 입력해주세요.");
					continue;
				}else if(inputBirthChk.length != 8){
					System.out.println("생년월일 8글자만 입력하세요.");
					continue;
				}else if (inputBirthChk[0]>=51){
					System.out.println("올바른 생년월일을 입력하세요.");
					continue;
				}
				if(inputBirthChk[0] == 50){
					if(inputBirthChk[1] != 48){
						System.out.println("성인만 가입가능합니다.");
						continue;
					}else if(inputBirthChk[2] != 48){
						System.out.println("성인만 가입가능합니다.");
						continue;
					}else if(inputBirthChk[3]>53){
						System.out.println("성인만 가입가능합니다.");
						continue;
					}
				}
				break;
			}

			// id : 아이디
			// 아이디는 8글자 이상 15글자 이하 입력
			while(true){
				System.out.println(" \n8글자 이상 15 글자 이하의 아이디를 입력해주세요. ");
				System.out.print("\n아이디를 입력하세요 : ");
				inputId = scanner.nextLine();
				if((inputId.equalsIgnoreCase("q"))){
					System.out.println("\n\t^ 시스템을 종료합니다. \n");
					break loop;
				}
				char[] idChk = inputId.toCharArray();
				if (idChk.length == 0){
					System.out.println("다시 입력해주세요.");
					continue;
				}else if (idChk.length < 8){
				System.out.println("아이디는 8글자 이상이어야 합니다.");
				continue;
				}if (idChk.length > 15){
					System.out.println("아이디는 15글자 이하만 가능합니다.");
					continue;
				}
				break;
			}

			// pw : 패스워드
			// 패스워드는 8글자 이상 15글자 이하만 입력한다.
			while(true){
				System.out.println(" \n8글자 이상 15 글자 이하의 비밀번호를 입력해주세요. ");
				System.out.print("\n비밀번호를 입력하세요 : ");
				inputPWD = scanner.nextLine();
				if((inputPWD.equalsIgnoreCase("q"))){
					System.out.println("\n\t^ 시스템을 종료합니다. \n");
					break loop;
				}

				char[] pwdChk = inputPWD.toCharArray();
				if (pwdChk.length == 0){
					System.out.println("다시 입력해주세요.");
					continue;
				}else if (pwdChk.length < 8){
					System.out.println("비밀번호는 8글자 이상이어야 합니다.");
					continue;
				}else if (pwdChk.length > 15){
					System.out.println("비밀번호는 15글자 이하만 가능합니다.");
					continue;
				}

				// AgainPwd : 패스워드 다시 입력 변수
				// 이전에 입력한 비밀번호가 틀렸을 때 다시 입력
				System.out.print("\n비밀번호를 다시 입력하세요 : ");
				inputAgainPWD = scanner.nextLine();
				if((inputAgainPWD.equalsIgnoreCase("q"))){
					System.out.println("\n\t^ 시스템을 종료합니다. \n");
					break loop;
				}
				if(!(inputPWD.equals(inputAgainPWD))){
					System.out.println("비밀번호가 틀렸습니다.");
					continue;
				}
				break;
			}

			// members : 회원수
			int members = regDTO.getMembers();
			
			// 이미 있는 아이디는 회원가입을 하지 못하게 한다.
			if (members != 0){
				for(int idx = 0; idx <= members; idx++){
					 if (signDTO[idx].getId().equalsIgnoreCase(inputId)){
						System.out.println("이미 있는 아이디입니다.");
						continue loop;
						}
					}
			}

			// 유효성을 마친 회원정보 => setter를 통해 저장
			signDTO[members].setName(inputName);
			signDTO[members].setBirth(inputBirth);
			signDTO[members].setId(inputId);
			signDTO[members].setPassword(inputPWD);

			members++;
			regDTO.setMembers(members);

			break;
		}
	}

	// 로그인 화면 : 기존에 회원가입한 회원정보와 입력한 아이디, 비밀번호를 대조하여 true면 메인화면으로 이동
	//                                                         false면 다시 되돌아옴
	// status : 로그인 화면으로 이동하기 위한 상태변화 switch
	void signInMenuPrint(RegisterDTO regDTO, RegisterDTO[] signDTO){
		loop : while(true){
			status = false; 
			userAPI.mLine('=', 50);
			System.out.println("\n\t\t<< 로그인 >>");
			userAPI.mLine('=', 50);
			System.out.println("\n\t\t로그인 서비스");
			System.out.println("\n\n\n\tQ. 뒤로가기");
			userAPI.mLine('-', 50);
			System.out.print("아이디를 입력하세요 : ");
			String inputId = scanner.nextLine();
			if((inputId.equalsIgnoreCase("q"))){
				System.out.println("\n\t^ 시스템을 종료합니다. \n");
				break;
			}
			System.out.print("비밀번호를 입력하세요 : ");
			String inputPwd = scanner.nextLine();
			if((inputPwd.equalsIgnoreCase("q"))){
				System.out.println("\n\t^ 시스템을 종료합니다. \n");
				break;
			}

			// 관리자 id : admin, pw : 1234 -> default
			if((inputId.equalsIgnoreCase("admin") == true) && (inputPwd.equalsIgnoreCase("1234") == true)){
				adminMenu(regDTO, signDTO);
				break loop;
			}

			// 회원정보를 받아 입력된 아이디와 비밀번호 맞는지 확인
			for (int idx = 0 ; idx < signDTO.length ;idx++ ){
				if((signDTO[idx].getId().equalsIgnoreCase(inputId) == true) && (signDTO[idx].getPassword().equalsIgnoreCase(inputPwd) == true) ){
					System.out.println("\n\n\t\t## 로그인 완료 ##\n");
					userAPI.mLine('=', 50);
					System.out.printf("\n\t\t##%s 님 환영합니다.##\n",signDTO[idx].getName());
					userAPI.mLine('=', 50);
					status = true;
					break loop;
				}else if ((signDTO[idx].getId().equalsIgnoreCase(inputId)== false) && (signDTO[idx].getPassword().equalsIgnoreCase(inputPwd)== true)){
					System.out.println("아이디가 일치하지 않습니다. \n 다시 확인해주세요");
				}else if ((signDTO[idx].getId().equalsIgnoreCase(inputId)== true) && (signDTO[idx].getPassword().equalsIgnoreCase(inputPwd)== false)){
					System.out.println("비밀번호가 일치하지 않습니다. \n 다시 확인해주세요");
				}else{
					System.out.println("아이디와 비밀번호를 다시 확인해주세요");
				}
			}
		}
	}

	// 관리자 계정 메뉴
	// 회원가입한 사용자들을 관리할 수 있는 계정 -> 아직은 계정 삭제, 수정 등등 미구현
	void adminMenu(RegisterDTO regDTO, RegisterDTO[] signDTO){
		while(true){
			userAPI.mLine('=', 50);
			System.out.println("\n\t\t<< 관리자용 메뉴 >>");
			userAPI.mLine('=', 50);
			System.out.println("\n\t\t##관리자 회원관리 메뉴##\n");
			System.out.printf("가입된 회원 수 : %d\n\n", regDTO.getMembers());
			
			for (int idx = 0 ; idx < regDTO.getMembers() ; idx++ ){
				System.out.printf("이름 : %s 생년월일 : %s 아이디 : %s 비밀번호 : %s ",signDTO[idx].getName(), signDTO[idx].getBirth(), signDTO[idx].getId(), signDTO[idx].getPassword());
			}
			System.out.println("\n\n\n\tQ. 뒤로가기");
			userAPI.mLine('-', 50);
			System.out.print("\n메뉴선택 : ");
			String input = scanner.nextLine();

			if((input.equalsIgnoreCase("q"))){
				System.out.println("\n\t^ 시스템을 종료합니다. \n");
				break;
			}
		}
	}
}
