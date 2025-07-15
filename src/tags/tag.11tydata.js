module.exports = function () {
  return {
    pagination: {
      data: "collections.tagList",
      size: 1,
      alias: "tag"
    },
    permalink: data => `/tags/${data.tag}/index.html`,
    layout: "tag.njk",
    tags: "tagPages" // 無限ループ防止
  };
};
