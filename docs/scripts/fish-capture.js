document.querySelectorAll('.fish-shadow').forEach((fishEl) => {
  fishEl.addEventListener('click', () => {
    const fishId = fishEl.dataset.id || 'fish_001';
    const size = (Math.random() * 12 + 18).toFixed(1);

    const dex = JSON.parse(localStorage.getItem('fishdex') || '{}');

    if (!dex[fishId]) {
      dex[fishId] = {
        count: 0,
        sizes: []
      };
    }

    dex[fishId].count += 1;
    dex[fishId].sizes.push(parseFloat(size));

    localStorage.setItem('fishdex', JSON.stringify(dex));

    // ジャンプ演出 & 消滅
    fishEl.classList.add('jumping');
    setTimeout(() => fishEl.remove(), 1200);
  });
});
