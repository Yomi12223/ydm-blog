---
title: GAME
layout: layout.njk
permalink: /game/dodge/
---
<h1>🎮 GAME</h1>
<p>テスト用ミニゲーム！マウスで●を動かして■をよけよう！</p>

<div id="game-box">
  <canvas id="game-canvas" width="300" height="400"></canvas>
</div>

<style>
#game-box {
  margin-top: 1em;
  background: #111;
  padding: 1em;
  border-radius: 12px;
  text-align: center;
}
canvas {
  background: #000;
  border: 2px solid #444;
  image-rendering: pixelated;
}
</style>

<script>
const canvas = document.getElementById("game-canvas");
const ctx = canvas.getContext("2d");

let player = { x: 150, y: 370, size: 10 };
let enemy = { x: Math.random() * 290, y: 0, size: 10 };
let gameOver = false;

canvas.addEventListener("mousemove", (e) => {
  const rect = canvas.getBoundingClientRect();
  player.x = e.clientX - rect.left;
});

function draw() {
  if (gameOver) {
    ctx.fillStyle = "#fff";
    ctx.font = "20px sans-serif";
    ctx.fillText("GAME OVER", 90, 200);
    return;
  }

  ctx.clearRect(0, 0, 300, 400);
  
  // draw player
  ctx.fillStyle = "#0f0";
  ctx.beginPath();
  ctx.arc(player.x, player.y, player.size, 0, Math.PI * 2);
  ctx.fill();

  // draw enemy
  ctx.fillStyle = "#f00";
  ctx.fillRect(enemy.x, enemy.y, enemy.size, enemy.size);
  enemy.y += 2;

  // collision
  if (
    enemy.y + enemy.size > player.y - player.size &&
    enemy.x < player.x + player.size &&
    enemy.x + enemy.size > player.x - player.size
  ) {
    gameOver = true;
  }

  if (enemy.y > 400) {
    enemy.y = 0;
    enemy.x = Math.random() * 290;
  }

  requestAnimationFrame(draw);
}
draw();
</script>

<div class="back-button-area">
  <a href="/game/" class="back-button">⇐ゲーム一覧に戻る</a>
</div>
