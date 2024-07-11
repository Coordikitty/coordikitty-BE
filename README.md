# coordikitty-BE
## 팀원
FE-WEB : 최은성  
FE-APP : 최서연  
BE : 이태훈, 이호준  
ML/DL : 정민관, 김채일  

### 배포 링크
- BackEnd : http://ec2-3-36-29-29.ap-northeast-2.compute.amazonaws.com:8080/
- FrontEnd : http://ec2-43-202-147-62.ap-northeast-2.compute.amazonaws.com/
- test 계정 : jmg@naver.com  jmg1111!

## 1. 개발 환경
- Front : ReactJS, Flutter
- Back-end : SpringBoot, MySQL(AWS RDS) 
- 버전 및 이슈관리 : Github, Jira Software, Redis(사용예정)
- 협업 툴 : Discord, Jira Software, Github
- 서비스 배포 환경 : AWS EC2, ngrok(ml/dl), Github Actions
- 디자인 : [Figma](https://www.figma.com/design/iAU7tmcVv7jXtm3LBq3oLE/CoordiKitty?node-id=0-1&t=OChq8nxeMpNzMPau-0)
## 주요 기능
- 자신의 옷장에 있는 옷을 등록하여 해당 옷을 기반으로 날씨/스타일에 따라 코디를 추천받는 플랫폼
- 자신의 코디를 post에 게시하여 커뮤니티 기능 활성화(개발중)
- post에 있는 옷들 + 옷장에 있는 옷 기반으로 날씨/스타일에 따라 코디 추천(개발중)
- 기본 auth기능(회원가입/로그인/로그아웃/회원탈퇴)

## 개발전략
### Back-end
- 쿼리 최적화 : FetchType을 LAZY로 사용, LAZY 사용으로 인한 프록시 문제는 fetch join 사용
- DDD 개발
- record 사용 : 반복되는 코드(toString, equals 등)의 번거로움 감소
- Docker(Redis) 사용
### 깃허브 전략
- github-flow를 통해 main, develop, feature로 나누어서 개발
- Jira와 브랜치 및 커밋 연동을 위해 커밋 컨벤션 통일
- 커밋 메시지 컨벤션 활용
- pr 사용
