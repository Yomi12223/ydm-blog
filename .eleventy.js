const { DateTime } = require("luxon");

module.exports = function(eleventyConfig) {
  // 日付フィルター
  eleventyConfig.addFilter("date", (value, format = "yyyy-MM-dd") => {
    return DateTime.fromJSDate(new Date(value), { zone: "utc" }).toFormat(format);
  });

  // 相対パス計算フィルター - 修正版
  eleventyConfig.addFilter("relativePath", function(targetPath) {
    const currentUrl = this.page.url; // Nunjucksのコンテキストから現在のURLを取得
    
    if (!currentUrl || currentUrl === "/" || currentUrl === "/index.html") {
      return targetPath;
    }
    
    // 現在のページの深さを計算
    let normalizedUrl = currentUrl;
    if (normalizedUrl.endsWith('/') && normalizedUrl !== '/') {
      normalizedUrl = normalizedUrl + 'index.html';
    }
    
    // URLをパーツに分解（空文字を除外）
    const urlParts = normalizedUrl.split('/').filter(part => part !== '');
    // ファイル名を除いた深さを計算
    const depth = urlParts.length - 1;
    
    const relativePath = "../".repeat(Math.max(0, depth));
    return relativePath + targetPath;
  });

  // URLフィルター（GitHub Pages用）
  eleventyConfig.addFilter("url", function(url) {
    const isProd = process.env.NODE_ENV === 'production';
    const baseUrl = isProd ? '/ydm-blog' : '';
    if (!url) return baseUrl + '/';
    if (url.startsWith('http')) return url;
    if (!url.startsWith('/')) url = '/' + url;
    return baseUrl + url;
  });

  // 静的ファイルのコピー
  eleventyConfig.addPassthroughCopy({ "src/style.css": "style.css" });
  eleventyConfig.addPassthroughCopy("src/assets");

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
      output: "docs"
    },
    markdownTemplateEngine: "njk",
    htmlTemplateEngine: "njk",
    dataTemplateEngine: "njk"
  };
};
