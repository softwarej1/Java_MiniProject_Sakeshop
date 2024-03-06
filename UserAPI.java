package classes;

class UserAPI{
	static void mLine(char cLine, int iLine){
		for (int i = 0; i <= iLine + 2 ; i++ ){
			if(i == 0 || i == iLine + 2){
				System.out.println();
				continue;
			}
			System.out.print(cLine);
		}
		
	}
	
	static String mLineReturn(char sLine, int iLine){

		String mLineSt = Character.toString(sLine); // char -> String 변환
		String resultSt = ""; 
		for (int i = 0; i <= iLine ; i++ ){
			resultSt = resultSt + mLineSt; //String 빈값에 추가
		}
		return resultSt; //String 값 리턴
	}
}
