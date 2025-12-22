package lab.ch01;

import java.io.Reader;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import lab.vo.DeptVO;

public class CrudDeptDao {
	Logger log = Logger.getLogger(CrudDeptDao.class);
	//오라클 드라이버 클래스 정보와 scott계정에 관한 정보들이 있다.
	//쿼리문을 가진 dept.xml문서의 물리적인 위치도 여기 있다.
	String resource = "mybatis/MapperConfig.xml";
	SqlSessionFactory sqlMapper = null;
	
	//부서 수정 처리
	public int deptUpdate(DeptVO dvo) {
		int result = -1;//1이면 수정 성공, 0이면 수정 실패
		SqlSessionFactoryBuilder ssfb = 
				new SqlSessionFactoryBuilder();
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = ssfb.build(reader);
			SqlSession sqlSession = sqlMapper.openSession();
			result = sqlSession.insert("deptUpdate", dvo);
			System.out.println(result);//1
			sqlSession.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}//end of 부서 수정
	
	//부서 입력 처리
	public int deptInsert(DeptVO dvo) {
		int result = -1;//1이면 입력 성공, 0이면 입력 실패
		SqlSessionFactoryBuilder ssfb = 
				new SqlSessionFactoryBuilder();
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = ssfb.build(reader);
			SqlSession sqlSession = sqlMapper.openSession();
			result = sqlSession.insert("deptInsert", dvo);
			System.out.println(result);//1
			sqlSession.commit();
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}//end of 부서 등록
	//부서 목록 조회
	public List<Map<String, Object>> deptList() {
		List<Map<String,Object>> list = null;
		SqlSessionFactoryBuilder ssfb = 
				new SqlSessionFactoryBuilder();
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = ssfb.build(reader);
			SqlSession sqlSession = sqlMapper.openSession();
			list = sqlSession.selectList("deptList");
			log.info(list);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	public void testDB() {
		SqlSessionFactoryBuilder ssfb = 
				new SqlSessionFactoryBuilder();
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = ssfb.build(reader);
			SqlSession sqlSession = sqlMapper.openSession();
			log.info(sqlSession);//커넥션 얻기 성공 여부확인
			String currentDate = sqlSession.selectOne("currentDate");
			log.info(currentDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}//end of testDB
	public static void main(String[] args) {
		CrudDeptDao deptDao = new CrudDeptDao();
		//deptDao.testDB();
		//deptDao.deptList();
		DeptVO dvo = new DeptVO();
		dvo.setDeptno(50);
		dvo.setDname("운영부");
		dvo.setLoc("부산");
		deptDao.deptUpdate(dvo);
	}

}
