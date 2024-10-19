ddd를 활용한 이커머스 예시

도메인
- 사용자
- 결제
- 운송
- 판매
- 물류


사용자

1. 회원가입
   - /api/users/register POST
   - spring validation으로 핸드폰 번호 양식이 맞는지 검증합니다
   - spring security의 BCryptPasswordEncoder로 비밀번호를 인코딩하여 저장합니다 (Argon2 알고리즘이 가장 강력하다고 알려져있지만, 설정이 간단하고 안정성이 비교적 더 오랫동안 검증된 BCrypt를 사용했습니다)
2. 로그인
   - /api/users/login POST
   - spring validation으로 핸드폰 번호 양식이 맞는지 검증합니다
   - 입력된 휴대폰 번호 및 비밀번호로 사용자 인증을 진행하고 Jwt 토큰을 반환합니다
3. 로그아웃
   - /api/users/logout POST
   - 현재 헤더로 전달되는 jwt 토큰을 레디스에 등록하여 spring filter에서 jwt token을 검증할 때 해당 토큰이 레디스에 있으면 로그인이 필요한 상태로 처리했습니다
  
제품

1. 등록/수정
   - /api/products POST
   - 상품 정보를 받을 때 제품 id가 없으면 등록하고, 제품 id가 있으면 JPA의 dirty checking을 이용하여 변경된 부분을 업데이트 합니다
2. 삭제
   - /api/products DELETE
   - 인덱스를 위해 사용자 id와 제품 id로 조회 후 hard delete를 했습니다
3. 단일 조회
   - /api/products/{productId} GET
   - 인덱스를 위해 사용자 id와 제품 id로 조회 후 상세 정보를 전달합니다
4. 페이지 조회
   - /api/products GET
   - 제품 테이블에 초성 검색을 위해 초성 컬럼을 추가했습니다
   - 다른 API 반환 값에서 마지막 페이지인지 여부와 전달 항목 갯수를 전달합니다
