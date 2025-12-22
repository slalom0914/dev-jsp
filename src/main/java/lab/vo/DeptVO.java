package lab.vo;

public class DeptVO {
	//접근제한자를 private으로 하면 외부에서 접근불가
	private int deptno;//읽기 와 쓰기
	private String dname;//읽기 와 쓰기
	private String loc;// 읽기 와 쓰기
	public int getDeptno() {
		return deptno;
	}
	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}
	public String getDname() {
		return dname;
	}
	public void setDname(String dname) {
		this.dname = dname;
	}
	public String getLoc() {
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
}
