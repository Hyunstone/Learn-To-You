## 기능구현

서버 저장내역
문제, 문제 정답
each 학생 점수

[V] 응답받기 ServerController#getResponse

    - 클라이언트 구분 = 교수=0 / 학생=1
    - 프로토콜 구분 = 내려갈수록 하나씩 더해주세요. 0/0 이렇게
        - 교수: 0
            - 문제 제출: 0
            - 문제 불러오기: 1
            - 학생들 답안 불러오기: 2

        - 학생: 1
            - 문제 불러오기: 0
            - 답안 제출하기: 1

- [V] 학생, 교수 문제 불러오기
- [V] 교수가 문제 제출: TeacherUI#requestServer((indicator)"0/0")
    - [V] 문제 서버에 저장: Server#ServerController#TeacherProtocol#setQuiz
- [V] 학생들에게 문제 전달: Server#ServerController#StudentProtocol#pushQuiz
- [V] 학생들이 정답 제출: StudentUI#requestServer((indicator)"1/1")
- [V] 학생이 제출한 정답 받기 (학생들로부터):  Server#ServerController#StudentProtocol#setAnswer
- [V] 멀티 채팅 기능
- [V] 채팅 서버 로그인한 아이디로 자동 로그인
---
- [아직] 학생이 제출한 문제 채점
- [아직] 채점한 문제 점수 서버에 반영(저장)
- [아직] 점수 전달
    - 교수: 모든 학생들의 점수
    - 학생: 학생 개개인의 점수

[V] 채팅 서버 로그인한 아이디로 자동 로그인 +) 부족함
   - 고쳐야함

    - 중복 로그인이 가능. (채팅 창을 여러번 동시에 누르는 경우)
    - 대화 종료를 하지 않은 경우 쓰레드가 남음 (메모리 누수)
---
- 나중에
  - 채팅내용 서버에 기록 
  - 로그인 정보 서버 로그에 기록