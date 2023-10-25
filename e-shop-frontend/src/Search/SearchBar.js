import "./SearchBar.css";

function SearchBar() {
  return (
    <div className="search-container">
      <select id="category" name="category" form="search-form">
        <option value="total">전체</option>
        <option value="foods">음식</option>
        <option value="beauty">뷰티</option>
        <option value="kitchen">주방용품</option>
        <option value="electronics">전자기기</option>
        <option value="bathroom">욕실</option>
        <option value="fashion">패션</option>
      </select>
      <form method="get" id="search-form">
        <input
          type="text"
          id="title"
          name="title"
          placeholder="검색어를 입력하세요. (검색어 없을 시 해당 카테고리 모두 검색)"
        />
        <input type="submit" value="검색" />
      </form>
    </div>
  );
}

export default SearchBar;
