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
	// 오라클 드라이버 클래스 정보와 scott계정에 관한 정보들이 있다.
	// 쿼리문을 가진 dept.xml문서의 물리적인 위치도 여기 있다.
	String resource = "mybatis/MapperConfig.xml";
	SqlSessionFactory sqlMapper = null;

	// 부서 수정 처리
	public int deptUpdate(Map<String,Object> pmap) {    
		int result = -1;// 1이면 수정 성공, 0이면 수정 실패
		SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = ssfb.build(reader);
			SqlSession sqlSession = sqlMapper.openSession();
			result = sqlSession.insert("deptUpdate", pmap);
			System.out.println(result);// 1
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}// end of 부서 수정

	// 부서 입력 처리
	public int deptInsert(Map<String,Object> pmap) {
		int result = -1;// 1이면 입력 성공, 0이면 입력 실패
		SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = ssfb.build(reader);
			SqlSession sqlSession = sqlMapper.openSession();
			result = sqlSession.insert("deptInsert", pmap);
			System.out.println(result);// 1
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}// end of 부서 등록
	//Model계층이다.
	//부서정보 삭제하기 구현
	public int deptDelete(int i_deptno) {//call by value
		int result = -1;
		SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = ssfb.build(reader);
			SqlSession sqlSession = sqlMapper.openSession();
			//SqlSession sqlSession1 = sqlMapper.openSession(true);
			//SqlSession sqlSession2 = sqlMapper.openSession(false);
			result = sqlSession.delete("deptDelete",i_deptno);
			sqlSession.commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}//end of deptDelete
	// 부서 목록 조회
	public List<Map<String, Object>> deptList(String dname) {
		List<Map<String, Object>> list = null;
		SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = ssfb.build(reader);
			SqlSession sqlSession = sqlMapper.openSession();
			DeptVO dvo = new DeptVO();
			dvo.setDname(dname);
			list = sqlSession.selectList("deptList", dvo);
			log.info(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public void testDB() {
		SqlSessionFactoryBuilder ssfb = new SqlSessionFactoryBuilder();
		try {
			Reader reader = Resources.getResourceAsReader(resource);
			sqlMapper = ssfb.build(reader);
			SqlSession sqlSession = sqlMapper.openSession();
			log.info(sqlSession);// 커넥션 얻기 성공 여부확인
			String currentDate = sqlSession.selectOne("currentDate");
			log.info(currentDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// end of testDB

	public static void main(String[] args) {
		CrudDeptDao deptDao = new CrudDeptDao();
		// deptDao.testDB();
		// deptDao.deptList();
		DeptVO dvo = new DeptVO();
		dvo.setDeptno(50);
		dvo.setDname("운영부");
		dvo.setLoc("부산");
		//deptDao.deptUpdate(dvo);
		deptDao.deptList("개발");
	}


}
