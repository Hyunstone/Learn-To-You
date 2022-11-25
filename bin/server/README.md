## 기능구현

서버 저장내역
문제, 문제 정답
each 학생 점수

+) 로그인 기록 로그
+) 채팅 기록 로그


[] 응답받기 ServerController#getResponse
    - 클라이언트 구분 = 교수=0 / 학생=1
    - 프로토콜 구분

[] 문제 받기 <- 교수로부터 ServerController#getProblem
    - 문제 서버에 저장 ServerController#addProblem
[] 문제 전달 -> 학생들에게 ServerController#pushProblem

[] 학생이 제출한 정답 받기 <- 학생들로부터 ServerController#
[] 학생이 제출한 문제 채점
[] 채점한 문제 점수 서버에 반영(저장)
[] 점수 전달
    - 교수: 모든 학생들의 점수
    - 학생: 학생 개개인의 점수

[] 채팅 서버 로그인한 아이디로 자동 로그인
[] 채팅내용 서버에 기록
[] 로그인 정보 서버 로그에 기록