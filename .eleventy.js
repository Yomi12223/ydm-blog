const { DateTime } = require("luxon");

module.exports = function(eleventyConfig) {
  // 日付フィルター
  eleventyConfig.addFilter("date", (value, format = "yyyy-MM-dd") => {
    return DateTime.fromJSDate(new Date(value), { zone: "utc" }).toFormat(format);
  });

  // 静的ファイルのコピー
  eleventyConfig.addPassthroughCopy({ "src/style.css": "style.css" });
  eleventyConfig.addPassthroughCopy("src/assets");
  eleventyConfig.addPassthroughCopy("src/login.html");

  // CSS自動再読み込み
  eleventyConfig.addWatchTarget("src/style.css");

  // 記事一覧（posts）
  eleventyConfig.addCollection("diary", (collectionApi) =>
    collectionApi.getFilteredByGlob("src/posts/*.md").reverse()
  );

  // 🎯 スムージー図鑑（パスを修正済み）
eleventyConfig.addCollection("smoothies", (collectionApi) =>
  collectionApi.getAll().filter((item) =>
    item.inputPath.includes("src/smoothies/") &&
    !item.inputPath.endsWith("smoothies.md") && 
    item.data.permalink &&
    item.data.permalink.startsWith("/smoothies/")
  ).reverse()
);

// タグ一覧コレクション
eleventyConfig.addCollection("tagList", function (collectionApi) {
  const tagSet = new Set();
  collectionApi.getAll().forEach((item) => {
    if ("tags" in item.data) {
      let tags = Array.isArray(item.data.tags) ? item.data.tags : [item.data.tags];
      tags.forEach((tag) => {
        // ✅ 出力競合回避タグを除外
        if (tag !== "tagPages" && tag !== "tags") {
          tagSet.add(tag);
        }
      });
    }
  });
  return [...tagSet].sort();
});



  // ニュースなどの更新履歴
  eleventyConfig.addCollection("updateHistory", (collectionApi) =>
    collectionApi.getAll().filter((item) =>
      item.inputPath.endsWith(".md") &&
      item.data.updateLabel &&
      item.date
    ).sort((a, b) => new Date(b.date) - new Date(a.date))
  );

  eleventyConfig.addCollection("fishdex", (collectionApi) =>
  collectionApi.getAll().filter((item) =>
    item.inputPath.includes("src/fishdex/") &&
    !item.inputPath.endsWith("fishdex.md") &&
    item.data.permalink &&
    item.data.permalink.startsWith("/fishdex/")
  ).reverse()
);


  return {
    dir: {
      input: "src",
      includes: "_includes",
      layouts: "_includes",
      output: "dist"
    },
    markdownTemplateEngine: "njk",
    htmlTemplateEngine: "njk",
    dataTemplateEngine: "njk"
  };
};
