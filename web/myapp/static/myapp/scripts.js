
  // scripts.js

  window.addEventListener("DOMContentLoaded", function () {
  const cards = document.querySelectorAll(".card");
  const closeButtons = document.querySelectorAll(".close-btn");

  cards.forEach((card) => {
    card.addEventListener("click", function (event) {
      // 防止点击关闭按钮时也触发卡片放大
      if (event.target.classList.contains("close-btn")) return;

      const isFullscreen = card.classList.contains("fullscreen");

      if (!isFullscreen) {
        const contentUrl = card.dataset.contentUrl;
        const contentElement = card.querySelector(".card-content");

        if (contentElement.innerHTML === "") {
          fetch(contentUrl)
            .then((response) => {
              if (!response.ok) {
                throw new Error(`Error fetching content: ${response.statusText}`);
              }
              return response.text();
            })
            .then((html) => {
              contentElement.innerHTML = html;
            })
            .catch((error) => {
              console.error(error);
            });
        }

        card.classList.add("fullscreen");
        card.style.width = "100vw";
        card.style.height = "100vh";
        card.style.zIndex = "100";

        card.querySelector(".card-title").style.display = "none";
        card.querySelector(".close-btn").style.display = "block";
        card.querySelector(".card-content").style.display = "block"; // 新增代码
      }
    });
  });

  closeButtons.forEach((closeButton) => {
    closeButton.addEventListener("click", function (event) {
      event.stopPropagation();

      const card = closeButton.closest(".card");
      card.classList.remove("fullscreen");
      card.style.width = "50vw";
      card.style.height = "50vh";
      card.style.zIndex = "0";

      closeButton.style.display = "none";
      card.querySelector(".card-title").style.display = "block";
      card.querySelector(".card-content").style.display = "none"; // 新增代码
    });
  });
});