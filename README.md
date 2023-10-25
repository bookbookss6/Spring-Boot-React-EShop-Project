# Spring-Boot-React-EShop-Project

<h1>스프링부트 리액트를 활용한 풀스택 프로젝트 입니다.</h1>
<br/><br/><br/>

![one](https://github.com/bookbookss6/Spring-Boot-React-EShop-Project/assets/118971316/ae53ba46-cacd-4ed5-807c-d34ffae112c4)

![two](https://github.com/bookbookss6/Spring-Boot-React-EShop-Project/assets/118971316/d6cdc424-1369-492c-b731-354210b42356)

![three](https://github.com/bookbookss6/Spring-Boot-React-EShop-Project/assets/118971316/32b60199-c972-48df-a45d-319222d54128)

![four](https://github.com/bookbookss6/Spring-Boot-React-EShop-Project/assets/118971316/7e3267ca-cb74-4559-b2a9-1c7fc404357c)

<br/><br/><br/>

<h1>개발 환경 </h1>
<hr/>

<p>백엔드 : Spring Boot </p>

<p>데이터베이스 : mysql </p>

<p>프론트엔드 : React </p>

<p>자바 버전 : openjdk 17.0.8 </p>

<p>개발 도구 : IntelliJ</p>

<br/><br/><br/>
<hr/>



<br/><br/><br/>
<hr/>

<h1>테이블 명 USERS</h1>

<p>컬럼 : id  </p>
<p>타입 : int , primary key , auto_increment</p>

<p>컬럼 : username </p>
<p>타입 : varchar(100) </p>

<p>컬럼 : password</p>
<p>타입 : varchar(100)</p>

<p>컬럼 : role</p>
<p>타입 : enum('ROLE_ADMIN','ROLE_USER')</p>

<br/><br/><br/>
<hr/>


<h1>테이블 명 PRODUCTS</h1>

<p>컬럼 : id  </p>
<p>타입 : int , primary key , auto_increment</p>

<p>컬럼 : title </p>
<p>타입 : varchar(255) </p>

<p>컬럼 : price</p>
<p>타입 : int </p>

<p>컬럼 : category</p>
<p>타입 : enum('BATHROOM','BEAUTY','ELECTRONICS','FASHION','FOODS','KITCHEN')</p>

<p>컬럼 : image</p>
<p>타입 : mediumblob</p>

<br/><br/><br/>
<hr/>

<h1>테이블 명 ORDERLIST</h1>

<p>컬럼 : id  </p>
<p>타입 : int , primary key , auto_increment</p>

<p>컬럼 : product_count </p>
<p>타입 : bigint </p>

<p>컬럼 : product_id</p>
<p>타입 : bigint </p>

<p>컬럼 : user_id</p>
<p>타입 : bigint </p>

<p>외래어 키 : product_id </p>
<p>타겟 : products (product_id → id)</p>

<p>외래어 키 : user_id </p>
<p>타겟 : users (user_id → id)</p>

<br/><br/><br/>
<hr/>

<h1>테이블 명 CARTVIEW</h1>

<p>컬럼 : id  </p>
<p>타입 : int , primary key , auto_increment</p>

<p>컬럼 : product_count </p>
<p>타입 : bigint </p>

<p>컬럼 : product_id</p>
<p>타입 : bigint </p>

<p>컬럼 : user_id</p>
<p>타입 : bigint </p>

<p>외래어 키 : product_id </p>
<p>타겟 : products (product_id → id)</p>

<p>외래어 키 : user_id </p>
<p>타겟 : users (user_id → id)</p>

<br/><br/><br/>
<hr/>


<h1>API 설계</h1>

<table>
  <tr>
    <td>기능</td>
    <td>URL</td>
    <td>동작 방법</td>
  </tr>
  <tr>
    <td>로그인</td>
    <td>/auth/login</td>
    <td>로그인 후 메인 화면으로 이동.</td>
  </tr>
  <tr>
    <td>로그아웃</td>
    <td>/auth/logout</td>
    <td>로그아웃 후 로그인 페이지로 이동.</td>
  </tr>
  <tr>
    <td>회원가입</td>
    <td>/auth/register</td>
    <td>회원가입후 데이터베이스에 회원정보 저장.</td>
  </tr>
  <tr>
    <td>홈페이지</td>
    <td>/products/{page}</td>
    <td>홈페이지에 모든 상품 불러오기</td>
  </tr>
   <tr>
    <td>상품 정보</td>
    <td>/purchase-detail/${productId}</td>
    <td>해당 상품의 정보 불러오기</td>
  </tr>
  <tr>
    <td>장바구니</td>
    <td>/cartview/username=${username}</td>
    <td>해당 유저의 장바구니에 담긴 상품들 보기</td>
  </tr>
  <tr>
    <td>장바구니 삭제</td>
    <td>/cartview/product-id=${productId}/username=${username}</td>
    <td>해당 유저의 장바구니에 담겨진 상품 삭제</td>
  </tr>
  <tr>
    <td>장바구니 구매</td>
    <td>/order-list/save-all</td>
    <td>해당 유저의 장바구니에 담긴 상품들 구매</td>
  </tr>
  <tr>
    <td>바로구매</td>
    <td>/order-list/save/product-id=${productId}/product-count=${productCount}/username=${username}</td>
    <td>해당 유저가 구매한 상품 등록</td>
  </tr>
  <tr>
    <td>유저 정보</td>
    <td>/auth/user-info/token=${token}</td>
    <td>해당 유저의 정보 가져오기</td>
  </tr>
  <tr>
    <td>유저 삭제</td>
    <td>/auth/delete</td>
    <td>해당 유저를 데이터베이스로 부터 삭제</td>
  </tr>
  <tr>
    <td>장바구니 모두 삭제</td>
    <td>/cartview/username=${username}</td>
    <td>해당 유저의 장바구니에 담긴 모든 상품 삭제 </td>
  </tr>
  <tr>
    <td>상품 등록</td>
    <td>/products/save</td>
    <td>관리자가 상품 등록</td>
  </tr>
  <tr>
    <td>상품 삭제</td>
    <td>/products/delete?id=${productId}</td>
    <td>관리자가 상품 삭</td>
  </tr>
</table>


<br/><br/><br/>
<hr/>

