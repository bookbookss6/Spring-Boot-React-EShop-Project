export async function getProductDetail(id) {
    try {
      const response = await fetch(
        `http://localhost:8080/products/detail/${id}`
      );
      return response.json();
    } catch (error) {
      console.log(`error: ${error}`);
      return null;
    }
  }