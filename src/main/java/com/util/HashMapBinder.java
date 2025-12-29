package com.util;

import java.io.File;
import java.util.Enumeration;
import java.util.Map;
import org.apache.log4j.Logger;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import jakarta.servlet.http.HttpServletRequest;

//내 안에는 요청객체가 없다
//공통코드를 뽑아낸다 - 초보는 아니다
//반복되는 코드를 찾아내서.....
//코드의 양은 많아지더라도 복잡도는 증가하지 않게 코딩하기
//재사용이 높은 코드를 작성 하려면 인터페이스 혹은 추상클래스 중심의 코딩을 전개 하기
//상속은 재사용을 위한 모범답안은 아니다 - 왜냐면 결합도가 높아서, 의존적이다


public class HashMapBinder {
	Logger logger = Logger.getLogger(HashMapBinder.class);
	HttpServletRequest req = null;

	//전역변수의 초기화는 생성자가 해주니까 초기화를 생략가능함. 지변은 해주지않으니까 초기화를 반드시 해야함
	//전역변수는 인스턴스변수.변수명으로 호출이 가능하고 지변은 불가함
	public HashMapBinder() {}//디폴트 생성자
	//생성자의  파라미터를 통해서 서블릿 클래스가 톰캣 서버로 부터 주입받은
	//주소번지를 인스턴스화 할 때 생성자를 호출 하니까 그 생성자의 
	//파라미터로 서블릿이 쥐고 있는 요청객체의 원본을 받아온다
	public HashMapBinder(HttpServletRequest req) {
		this.req = req;
	}
	public void bind(Map<String,Object> pMap) {
		pMap.clear();//이미 쥐고 있는 데이터가 있으면 비워라
		//s가 붙으면 복수형 - 자루안에 데이터가 존재하는지 유무를 반환하는 메소드 - hasMoreElements()
		Enumeration<String> en = req.getParameterNames();//mem_id, mem_pw, mem_name
		while(en.hasMoreElements()) {
			String key = en.nextElement();//mem_id, mem_pw, mem_name, gubun, keyword
			logger.info(req.getParameter(key));// 한글깨짐 출력한 코드
			//GET방식의 한글처리 - server.xml -> URIEncoding="utf-8"
			//POST 방식의 한글처리 -> HangulConversion.java -> toUTF
			pMap.put(key, req.getParameter(key));//한글처리끝 - POST방식
		}
	}
}
