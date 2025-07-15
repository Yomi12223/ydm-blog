---
title: ニュース
layout: layout.njk
permalink: /news/
pagination:
  data: collections.updateHistory
  size: 15
  reverse: false
  alias: item
---

<h1>📰 更新履歴</h1>

<ul class="update-log">
{% for item in pagination.items %}
  <li>
    <span class="update-date">{{ item.date | date("MM/dd") }}</span>
    <a href="{{ item.url }}">
      {{ item.data.updateLabel or item.data.title }}
    </a>
  </li>
{% endfor %}
</ul>

<div class="pagination">
  {% if pagination.href.previous %}
    <a href="{{ pagination.href.previous }}">← 前のページ</a>
  {% endif %}
  {% for pageEntry in pagination.pages %}
  <a href="{{ pageEntry.href }}" {% if pageEntry.pageNumber === pagination.pageNumber %}class="current"{% endif %}>
    {{ loop.index }}
  </a>
{% endfor %}

  {% if pagination.href.next %}
    <a href="{{ pagination.href.next }}">次のページ →</a>
  {% endif %}
</div>

<hr>

<div class="news-note">
  ※ 各更新項目の内容は、記事やゲーム・ツールなどの修正・追加・変更を記録しています。  
  より詳しい内容はリンク先の記事本文をご覧ください。
</div>

