---
title: スムージー図鑑
layout: layout.njk
permalink: /smoothies/
---

<div class="smoothie-dex-wrapper">
  <h1>🍹 スムージー図鑑</h1>

<div class="smoothie-list">
  {% for item in collections.smoothies %}
    <div class="post-card">
      <a href="{{ item.url }}">
        <img src="{{ item.data.thumbnail }}" alt="{{ item.data.title }}">
      </a>
      <h3>{{ item.data.title }}</h3>


      <div class="tag-list">
        {% for tag in item.data.tags %}
          <span>#{{ tag }}</span>
        {% endfor %}
      </div>
    </div>
  {% endfor %}
</div>
