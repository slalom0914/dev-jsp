<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Map, java.util.ArrayList, java.util.HashMap" %>        
<%
    List<Map<String, Object>> bList = (List<Map<String, Object>>)request.getAttribute("bList");
    Map<String,Object> rmap = null;
    int size = 0;
    if(bList!=null && bList.size()>0){
    	out.print("b_no가 존재하는 경우");
    	size = bList.size();
    	rmap = bList.get(0);//파라미터에 0을 적었나?
    }
	//insert here - 조회결과가 없으면 널인 상황
	//out.print(bList);브라우저에 쓰기 - 서버사이드 결정된 문자열 출력 - 데이터셋 역할(JSON포맷- Gson API, 단순문자열포맷)
	//rmap = bList.get(0);// null, {}
	//out.print(rmap);//{} 깡통이다
	//List<Map<>> -> [{},{},{},......] -> JSON -> IOS, 안드로이드서비스가능함, Vue.js,   ReactJS
	//공공포털, 클라우드 서비스를 이용한 시스템 고려하고 있다면..... 무조건
	//List<Map> -> JSON
	//JSON -> List<Map>
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항상세</title>
<%@include file="/common/bootstrap_common.jsp"%>
<link rel="stylesheet" href="/css/board.css">
<script type="text/javascript">
    const boardDelete = () => {
       window.location.href="/board/boardDelete.pj2?b_no=<%=rmap.get("B_NO") %>";    
    }
    const boardList = () => {
       window.location.href="/board/boardList.pj2";
    }
    const boardUpdate = () => {
       document.querySelector("#f_board").submit();
    }  
</script>
</head>
<body>
    <!-- header start -->
    <%@include file="/include/header.jsp"%>
    <!-- header end    -->
    <!-- body start    -->
    <div class="container">
       <div class="page-header">
          <h2>게시판 <small>글상세보기</small></h2>
          <hr />
       </div>
       
       <!-- 공지목록 시작 -->


          <div style="width:58rem;">
            <div class="card-body">
              <div class='book-detail'>
                <div class='book-header'>
                
             <div class="input-group mb-3">
             <span class="input-group-text">제목</span>
             <div style="width:500px;">
                <input type="text" class="form-control"  value="<%=rmap.get("B_TITLE") %>" readonly>
             </div>
             </div>
             
             <div class="input-group mb-3">
             <span class="input-group-text">작성자</span>
             <div class="col-xs-3">
                <input type="text" class="form-control"  value="<%=rmap.get("B_WRITER") %>" readonly>
             </div>
             </div>
             
             <div class="input-group mb-3">
             <span class="input-group-text">내용</span>
             <div class="col-xs-8">
                <textarea class="form-control" id="exampleFormControlTextarea1" rows="5" cols="80" readonly><%=rmap.get("B_CONTENT") %></textarea>
             </div>
             </div>
             

                </div>
              </div>
            </div>
          <hr/>
            <div class='detail-link'>
              <button class="btn btn-info" data-bs-toggle="modal" data-bs-target="#boardUpdateForm">
               수정
              </button>
              &nbsp;
              <button class="btn btn-warning" onclick="boardDelete()">
                삭제
              </button>
              &nbsp;
              <button class="btn btn-success" onclick="boardList()">
              공지목록
              </button>
            </div>
          </div>  
       
       <!-- 회원목록   끝  -->    
       
    </div>
    <!-- body end       -->
    <!-- footer start -->
    <%@include file="/include/footer.jsp"%>
    <!-- footer end    --> 
    
    <!-- ========================== [[ 공지사항 수정 Modal ]] ========================== -->
    <div class="modal" id="boardUpdateForm">
      <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">
    
          <!-- Modal Header -->
          <div class="modal-header">
            <h4 class="modal-title">공지사항수정</h4>
            <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
          </div>
    
          <!-- 수정관련 - Modal body -->
          <!-- 기존에 있는 레코드를 변경하는 것이다. pk(b_no)가 있어야만 한다 -->
          <!--  b_no는 사용자로 부터 입력받은 값이 아니다.{시퀀스} 개발자가 필요로 하는 값(파악)-->
          <div class="modal-body">
           <form id="f_board" method="post" action="./boardUpdate.pj2">
           <!--  hidden속성은 화면없이 서버측에 값을 넘길때 사용함 -->
             <input type="hidden" name="b_no" value="<%=rmap.get("B_NO")%>">
              <div class="form-floating mb-3 mt-3">
                <input type="text"  class="form-control" id="b_title" name="b_title" placeholder="Enter 제목"  value="<%=rmap.get("B_TITLE")%>"/>
                <label for="mem_id">제목</label>
              </div>          
              <div class="form-floating mb-3 mt-3">
                <input type="text"  class="form-control" id="b_writer" name="b_writer" placeholder="Enter 작성자"   value="<%=rmap.get("B_WRITER")%>"/>
                <label for="mem_pw">작성자</label>
              </div>               
              <div class="form-floating mb-3 mt-3">
                <textarea rows="5" class="form-control h-25" aria-label="With textarea" id="b_content" name="b_content"><%=rmap.get("B_CONTENT")%></textarea>
              </div>          
           </form>
          </div>
    
          <!-- Modal footer -->
          <div class="modal-footer">
            <input type="button" class="btn btn-warning" data-bs-dismiss="modal" onclick="boardUpdate()"  value="저장">
            <input type="button" class="btn btn-danger" data-bs-dismiss="modal" value="닫기">
          </div>
    
        </div>
      </div>
    </div>
    <!-- ========================== [[ 회원가입 Modal ]] ========================== -->    
    
</body>
</html>