---
title: GAME
layout: layout.njk
permalink: /game/fish/
---

<h1>🎣 YDM フィッシング v2.0 β</h1>
<p>釣り場を選んで、タイミングよく魚を釣り上げよう！釣れた魚は図鑑に登録されるよ！</p>

<div id="game-container">
  <div id="map-select">
    <button onclick="setStage('pier')">桟橋</button>
    <button onclick="setStage('cave')">洞窟</button>
    <button onclick="showZukan()">📖 図鑑</button>
  </div>
  <canvas id="canvas" width="320" height="240"></canvas><br>
  <button id="start-btn">🎣 釣り開始！</button>
  <ul id="log"></ul>
</div>

<style>
#game-container {
  background: #001d2e;
  padding: 1em;
  border-radius: 10px;
  color: #e0ffff;
  font-family: monospace;
  max-width: 360px;
}
#map-select {
  margin-bottom: 1em;
}
#map-select button {
  margin-right: 5px;
  background: #0af;
  border: none;
  padding: 0.4em 0.8em;
  border-radius: 5px;
  color: #000;
  font-weight: bold;
  cursor: pointer;
}
#canvas {
  background: #000;
  border: 2px solid #44c;
  border-radius: 8px;
  margin-bottom: 0.5em;
}
#start-btn {
  padding: 0.4em 1em;
  font-weight: bold;
  background: #0f5;
  color: #000;
  border: none;
  border-radius: 6px;
  cursor: pointer;
}
#log {
  margin-top: 1em;
  padding-left: 1em;
  font-size: 0.9rem;
}
</style>

<script>
const canvas = document.getElementById("canvas");
const ctx = canvas.getContext("2d");
const btn = document.getElementById("start-btn");
const log = document.getElementById("log");

let state = "idle";
let barX = 0;
let barSpeed = 3.5;
let splash = 0;
let stage = "pier";
let sweetSpot = { start: 130, end: 170 };
let zukan = JSON.parse(localStorage.getItem("zukan") || "[]");

const fishList = {
  pier: [
    { name: "アジ", max: 0.6 },
    { name: "サバ", max: 1.1 },
    { name: "ブラックバス", max: 2.5 }
  ],
  cave: [
    { name: "タチウオ", max: 1.8 },
    { name: "シーラカンス", max: 30 },
    { name: "幻のドラゴンフィッシュ", max: 50 }
  ]
};

function setStage(name) {
  stage = name;
  log.innerHTML = `<li>🎯 釣り場を「${name === 'pier' ? '桟橋' : '洞窟'}」に変更！</li>`;
}

function showZukan() {
  alert("📖 魚図鑑\\n\\n" + zukan.join("\\n"));
}

function addToZukan(fish) {
  if (!zukan.includes(fish)) {
    zukan.push(fish);
    localStorage.setItem("zukan", JSON.stringify(zukan));
  }
}

function getFish() {
  const fishData = fishList[stage];
  const selected = fishData[Math.floor(Math.random() * fishData.length)];
  const weight = (Math.random() * selected.max + 0.2).toFixed(2);
  return `${selected.name}（${weight}kg）`;
}

function drawBG() {
  ctx.fillStyle = stage === "pier" ? "#013" : "#210";
  ctx.fillRect(0, 0, 320, 240);
  ctx.fillStyle = stage === "pier" ? "#044" : "#330";
  ctx.fillRect(0, stage === "pier" ? 180 : 160, 320, 80);
}

function drawBar() {
  ctx.fillStyle = "#060";
  ctx.fillRect(sweetSpot.start, 100, sweetSpot.end - sweetSpot.start, 40);
  ctx.fillStyle = "#0ff";
  ctx.fillRect(barX, 90, 5, 60);
}

function drawSplash() {
  ctx.fillStyle = "#fff";
  for (let i = 0; i < 8; i++) {
    ctx.beginPath();
    ctx.arc(Math.random()*320, 120+Math.random()*40, 2+Math.random()*2, 0, Math.PI*2);
    ctx.fill();
  }
}

function update() {
  if (state === "running") {
    barX += barSpeed;
    if (barX < 0 || barX > 315) barSpeed *= -1;
  }
}

function draw() {
  drawBG();
  update(); // ✅ バーの位置を更新する！
  if (state === "running") drawBar();
  if (splash > 0) {
    drawSplash();
    splash--;
  }
  requestAnimationFrame(draw);
}


canvas.addEventListener("click", () => {
  if (state !== "running") return;
  state = "result";
  let li = document.createElement("li");
  if (barX >= sweetSpot.start && barX <= sweetSpot.end) {
    let fish = getFish();
    li.innerHTML = `🎉 <b>釣れた！</b> ${fish}`;
    splash = 10;
    addToZukan(fish.split("（")[0]);
  } else {
    li.innerHTML = `💨 <i>逃げられた…</i>`;
  }
  log.appendChild(li);
});

btn.addEventListener("click", () => {
  barX = 0;
  barSpeed = 3 + Math.random() * 3;
  state = "running";
});

draw();
</script>

<div class="back-button-area">
  <a href="/game/" class="back-button">⇐ゲーム一覧に戻る</a>
</div>
