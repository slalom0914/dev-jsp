package com.pojo1;

import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class MyBatisCommonFactory2 {
	static Logger logger = Logger.getLogger(MyBatisCommonFactory2.class);
	//SqlSessionFactory객체를 생성해 주는 메소드 입니다.
	public static SqlSessionFactory sqlSessionFactory = null;
	public static void init() {
		try {
			String resource = "com/mybatis/MapperConfig.xml";//SQL매핑오픈소스이다 - 라이브러리
			Reader reader = Resources.getResourceAsReader(resource);
			logger.info("before sqlSessionFactory : "+sqlSessionFactory);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader,"development");
			logger.info("after sqlSessionFactory : "+sqlSessionFactory);
		} catch (Exception e) {
			logger.info("[[ Exception ]] "+e.toString());
		}
	}// end of init
	public static SqlSessionFactory getSqlSessionFactory() {
		//직접 인스턴스화를 하지 않더라도 메소드를 경유해서 전역변수에 초기화를 진행하면
		//이런 방식으로도 객체를 주입할 수 있다.
		//왜 메소드를 통해서 객체를 주입받는 걸까? if문을 사용할 수 있으니까 조건에 따라 인스턴스화 되는 상황을 제어할 수있다. - 싱글톤패턴으로 객체관리 가능함.
		init();
		//아래에서 리턴되는 변수는 전역변수이다. - 그러니까 init메소드 경유를 통해서 객체를 생성받을 수 있다
		return sqlSessionFactory;
	}
}
