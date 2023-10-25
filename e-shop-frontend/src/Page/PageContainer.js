import "./PageContainer.css";

function PageContainer({ pages, category, title }) {
  return (
    <div className="page-container">
      <a href={`/home/${pages["left"]}?category=${category}&title=${title}`}>
        ←
      </a>
      <a href={`/home/${pages["one"]}?category=${category}&title=${title}`}>
        {pages["one"]}
      </a>
      <a href={`/home/${pages["two"]}?category=${category}&title=${title}`}>
        {pages["two"]}
      </a>
      <a href={`/home/${pages["three"]}?category=${category}&title=${title}`}>
        {pages["three"]}
      </a>
      <a href={`/home/${pages["four"]}?category=${category}&title=${title}`}>
        {pages["four"]}
      </a>
      <a href={`/home/${pages["five"]}?category=${category}&title=${title}`}>
        {pages["five"]}
      </a>
      <a href={`/home/${pages["right"]}?category=${category}&title=${title}`}>
        →
      </a>
    </div>
  );
}

export default PageContainer;
