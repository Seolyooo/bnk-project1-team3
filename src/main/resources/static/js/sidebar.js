document.addEventListener("DOMContentLoaded", () => {
    const buttons = document.querySelectorAll(".menu-btn");

    buttons.forEach(btn => {
        btn.addEventListener("click", () => {
            const parent = btn.parentElement;

            // 다른 열린 메뉴 닫기
            document.querySelectorAll(".menu-item").forEach(item => {
                if (item !== parent) {
                    item.classList.remove("open");
                    const innerBtn = item.querySelector(".menu-btn");
                    if (innerBtn) innerBtn.classList.remove("active");
                }
            });

            // 현재 메뉴 토글
            parent.classList.toggle("open");
            btn.classList.toggle("active");
        });
    });
});
