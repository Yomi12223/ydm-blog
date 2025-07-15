---
title: 魚図鑑（仮）
layout: layout.njk
permalink: /fishdex/
---

<div class="smoothie-list">
  {% for item in collections.fishdex %}
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
