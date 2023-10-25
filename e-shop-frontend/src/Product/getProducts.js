export async function getProducts(productInfo) {

    const {category,title,page,setCategory,setTitle} = productInfo;

    try {
      if (category === null && title === null) {
        const response = await fetch(`http://localhost:8080/products/${page}`);
        setCategory("total");
        setTitle("");
        return response.json();
      } else {
        const response = await fetch(
          `http://localhost:8080/products/${page}?category=${category}&title=${title}`
        );
        return response.json();
      }
    } catch (error) {
      console.log(`error: ${error}`);
      return [];
    }
  }