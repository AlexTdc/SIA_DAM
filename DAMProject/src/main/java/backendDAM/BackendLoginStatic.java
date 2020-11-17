package backendDAM;

import claseDAM.Member;

public class BackendLoginStatic {

	public static Member memberStatic= new Member();
	
	
	public BackendLoginStatic() {
		super();
		// TODO Auto-generated constructor stub
	}


	public static Member getMemberStatic() {
		return memberStatic;
	}


	public static void setMemberStatic(Member memberStatic) {
		BackendLoginStatic.memberStatic = memberStatic;
	}


	
	
}
