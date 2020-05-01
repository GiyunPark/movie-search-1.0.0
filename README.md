# 영화 데이터를 이용한 검색엔진 만들기

## 작업단위
구현목록을 작성 후 작업목록 단위로 Commit 해주세요. 
Step이 완료되면 PR 날려주세요.  

커밋 메세지의 종류는 다음과 같이 구분하겠습니다. 
```
commit message 종류를 다음과 같이 구분
feat (feature)
fix (bug fix)
docs (documentation)
style (formatting, missing semi colons, …)
refactor
test (when adding missing tests)
```

## Step 1
[영화 진흥원 API 서비스](http://www.kobis.or.kr/kobisopenapi/homepg/apiservice/searchServiceInfo.do)

객체 정보. 

### 영화
* 영화명 :
* 영화명 영문 : 
* 개봉연도 : 
* 제작 국가 : 
* 장르 : **성인물(에로)** 는 제외한다. 
* 제작상태 : 개봉예정, 개봉 인것만
* 감독 : 
* 배우 : 
* 제작사 : 
* 관람등급 : 

### 배우
* 이름 
* 영문이름
* 성별
* 영화인 분류명

등과 같은 정보가 포함되어야 합니다. 

## Step2: REST API 만들기

REST API를 통해 영화 정보를 찾아올 수 있는 API를 생성한다. 
예시) 
* 올해 개봉예정인 영화
* A 영화에 참여한 배우들

카테고리(장르)에 대해서 질의를 해올 수 있었으면 좋겠습니다. 어떻게 하면 좋을까요?
Ex) 로맨스 : 장르가 로맨스인 영화들을 리턴한다.

/movie?name="광해"

/actor/name="이병헌"

## Step3: Elasticsearch 를 이용한 검색엔진 만들기. 
* DB -> ES
* STep2의 REST API는 DB가 아닌 검색엔진에서 데이터를 가지고 오도록 수정한다.  

# DB설정
DB는 Postgresql를 사용하도록 한다. 
## Docker DB 활용
Postsql  
```SHELL
docker run -e POSTGRES_USER=skcho -e POSTGRES_PASSWORD=skcho -e POSTGRES_DB=test -p 5432:5432 --name test_postgres postgres
```


