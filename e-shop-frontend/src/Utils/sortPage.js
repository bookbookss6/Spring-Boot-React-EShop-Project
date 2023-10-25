export function sortPage(currentPage = 1) {
  let left,
    right,
    one,
    two,
    three,
    four,
    five = 0;

  if (currentPage % 5 === 1) {
    if (currentPage <= 5) {
      left = 1;
      right = 6;
    } else {
      left = currentPage - 1;
      right = currentPage + 5;
    }
    one = currentPage;
    two = currentPage + 1;
    three = currentPage + 2;
    four = currentPage + 3;
    five = currentPage + 4;

  } else if (currentPage % 5 === 2) {
    if (currentPage <= 5) {
      left = 1;
      right = 6;
    } else {
      left = currentPage - 2;
      right = currentPage + 4;
    }
    one = currentPage - 1;
    two = currentPage;
    three = currentPage + 1;
    four = currentPage + 2;
    five = currentPage + 3;

  } else if (currentPage % 5 === 3) {
    if (currentPage <= 5) {
      left = 1;
      right = 6;
    } else {
      left = currentPage - 3;
      right = currentPage + 3;
    }
    one = currentPage - 2;
    two = currentPage - 1;
    three = currentPage;
    four = currentPage + 1;
    five = currentPage + 2;

  } else if (currentPage % 5 === 4) {
    if (currentPage <= 5) {
      left = 1;
      right = 6;
    } else {
      left = currentPage - 4;
      right = currentPage + 2;
    }
    one = currentPage - 3;
    two = currentPage - 2;
    three = currentPage - 1;
    four = currentPage;
    five = currentPage + 1;
    
  } else if (currentPage % 5 === 0) {
    if (currentPage <= 5) {
      left = 1;
      right = 6;
    } else {
      left = currentPage - 5;
      right = currentPage + 1;
    }
    one = currentPage - 4;
    two = currentPage - 3;
    three = currentPage - 2;
    four = currentPage - 1;
    five = currentPage;
  }

  return {
    left: left,
    one: one,
    two: two,
    three: three,
    four: four,
    five: five,
    right: right,
  };
}

